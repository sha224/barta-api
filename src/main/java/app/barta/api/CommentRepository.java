package app.barta.api;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = CommentDetailsProjection.class)
public interface CommentRepository extends MongoRepository<Comment, String> {
	List<Comment> findByPost(Post post);
	List<Comment> findByAuthor(User user);
}
