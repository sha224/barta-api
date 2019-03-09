package app.barta.api;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Post.class)
public class PostEventHandler {

	@Autowired
	private MongoOperations mongoOperations;
	
	@HandleBeforeCreate
	public void handleBeforeCreate(Post post) {
		post.setComments(new ArrayList<>());
		post.setUpvoters(new ArrayList<>());
		post.setDownvoters(new ArrayList<>());
		post.setCreationTime(OffsetDateTime.now(ZoneId.of("UTC")).toString());
		post.setAuthorIdentifierMap(new AuthorIdentifierMap());
	}
	
	@HandleAfterCreate
	public void handleAfterCreate(Post post) {
		User author = post.getAuthor();
		author.getPosts().add(post);
		mongoOperations.save(author);
	}
}
