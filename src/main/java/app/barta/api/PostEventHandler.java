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
@RepositoryEventHandler(Post.class)
public class PostEventHandler {

	@Autowired
	private MongoOperations mongoOperations;
	
	@HandleBeforeCreate
	public void handleBeforeCreate(Post post) {
		post.setUpvoters(new ArrayList<>());
		post.setDownvoters(new ArrayList<>());
		post.setCreationTime(OffsetDateTime.now(ZoneId.of("UTC")).toString());
		post.setAuthorIdentifierMap(new AuthorIdentifierMap());
	}
	
	@HandleBeforeLinkSave
	public void handleBeforeLinkSave(Post post, List<User> voters) {
		Post oldPost = mongoOperations.findById(post.getId(), Post.class);
		if (post.getUpvoters().size() > oldPost.getUpvoters().size()) {
			retainUniqueVoters(post.getUpvoters());
			post.getDownvoters().removeAll(post.getUpvoters());
		}
		if (post.getDownvoters().size() > oldPost.getDownvoters().size()) {
			retainUniqueVoters(post.getDownvoters());
			post.getUpvoters().removeAll(post.getDownvoters());
		}
	}
	
	private void retainUniqueVoters(List<User> voters) {
		Set<User> uniqueVoters = new HashSet<>(voters);
		voters.clear();
		voters.addAll(uniqueVoters);
	}
}
