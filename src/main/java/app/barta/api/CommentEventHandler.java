package app.barta.api;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
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
	
	@HandleBeforeLinkSave
	public void handleBeforeLinkSave(Comment comment, List<User> voters) {
		Comment oldComment = mongoOperations.findById(comment.getId(), Comment.class);
		if (comment.getUpvoters().size() > oldComment.getUpvoters().size()) {
			retainUniqueVoters(comment.getUpvoters());
			comment.getDownvoters().removeAll(comment.getUpvoters());
		}
		if (comment.getDownvoters().size() > oldComment.getDownvoters().size()) {
			retainUniqueVoters(comment.getDownvoters());
			comment.getUpvoters().removeAll(comment.getDownvoters());
		}
	}
	
	private void retainUniqueVoters(List<User> voters) {
		Set<User> uniqueVoters = new HashSet<>(voters);
		voters.clear();
		voters.addAll(uniqueVoters);
	}
}
