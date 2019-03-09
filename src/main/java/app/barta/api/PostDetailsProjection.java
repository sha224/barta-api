package app.barta.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "details", types = {Post.class})
public interface PostDetailsProjection {
	
	String getText();
	
	Point getLocation();
	
	String getCreationTime();
	
	@Value("#{target.getUpvoters().size()}")
	int getUpvotes();
	
	@Value("#{target.getDownvoters().size()}")
	int getDownvotes();
	
	@Value("#{target.getComments().size()}")
	int getCommentCount();
}
