package app.barta.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "details", types = {Comment.class})
public interface CommentDetailsProjection {
	
	String getText();
	
	int getAuthorIdentifier();
	
	String getCreationTime();
	
	@Value("#{target.getUpvoters().size()}")
	int getUpvotes();
	
	@Value("#{target.getDownvoters().size()}")
	int getDownvotes();
}
