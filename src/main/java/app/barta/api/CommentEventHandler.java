package app.barta.api;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeLinkSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Comment.class)
public class CommentEventHandler {

	@Autowired
	private MongoOperations mongoOperations;
	
	@HandleBeforeCreate
	public void handleBeforeCreate(Comment comment) {
		Post post = comment.getPost();
		User author = comment.getAuthor();
		comment.setAuthorIdentifier(post.getAuthorIdentifierMap().getIdentifier(author));
		comment.setUpvoters(new ArrayList<>());
		comment.setDownvoters(new ArrayList<>());
		comment.setCreationTime(OffsetDateTime.now(ZoneId.of("UTC")).toString());
	}
	
	@HandleAfterCreate
	public void handleAfterCreate(Comment comment) {
		Post post = comment.getPost();
		User author = comment.getAuthor();
		post.getComments().add(comment);
		author.getComments().add(comment);
		mongoOperations.save(post);
		mongoOperations.save(author);
	}
	
	@HandleBeforeLinkSave
	public void handleBeforeLinkSave(Comment comment, List<User> voters) {
		comment.getUpvoters().removeAll(voters);
		comment.getDownvoters().removeAll(voters);
	}
}
