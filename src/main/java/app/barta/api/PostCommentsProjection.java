package app.barta.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "comments", types = {Post.class})
public interface PostCommentsProjection {
	
	String getText();
	
	Point getLocation();
	
	String getCreationTime();
	
	@Value("#{target.getUpvoters().size()}")
	int getUpvotes();
	
	@Value("#{target.getDownvoters().size()}")
	int getDownvotes();
	
	@Value("#{@commentRepository.findByPost(target).size()}")
	int getCommentCount();
	
	@Value("#{@commentRepository.findByPost(target)}")
	List<CommentDetailsProjection> getComments();
}
