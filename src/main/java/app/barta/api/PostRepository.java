package app.barta.api;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = PostDetailsProjection.class)
public interface PostRepository extends PagingAndSortingRepository<Post, String> {
	List<Post> findByAuthor(User user);
	List<Post> findByLocationNear(Point location, Distance distance);
}
