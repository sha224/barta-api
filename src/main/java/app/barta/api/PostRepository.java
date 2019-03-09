package app.barta.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = PostDetailsProjection.class)
public interface PostRepository extends PagingAndSortingRepository<Post, String> {
	Page<Post> findByLocationNear(Point location, Distance distance, Pageable pageable);
}
