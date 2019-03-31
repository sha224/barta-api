package app.barta.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.stereotype.Component;

@Projection(name = "details", types = {User.class})
public interface UserDetailsProjection {
	
	String getCreationTime();
	
	@Value("#{@calculationsProvider.calculatePostUpvotes(@postRepository.findByAuthor(target))}")
	int getPostUpvoteCount();
	
	@Value("#{@calculationsProvider.calculatePostDownvotes(@postRepository.findByAuthor(target))}")
	int getPostDownvoteCount();
	
	@Value("#{@calculationsProvider.calculateCommentUpvotes(@commentRepository.findByAuthor(target))}")
	int getCommentUpvoteCount();
	
	@Value("#{@calculationsProvider.calculateCommentDownvotes(@commentRepository.findByAuthor(target))}")
	int getCommentDownvoteCount();
	
	@Value("#{@calculationsProvider.calculatePostKarma(@postRepository.findByAuthor(target))}")
	int getPostKarma();
	
	@Value("#{@calculationsProvider.calculateCommentKarma(@commentRepository.findByAuthor(target))}")
	int getCommentKarma();
	
	@Value("#{@calculationsProvider.calculateKarma(@postRepository.findByAuthor(target), @commentRepository.findByAuthor(target))}")
	int getKarma();
}

@Component
class CalculationsProvider {
	public int calculatePostUpvotes(List<Post> posts) {
		return posts.stream().mapToInt(p -> p.getUpvoters().size()).sum();
	}
	public int calculatePostDownvotes(List<Post> posts) {
		return posts.stream().mapToInt(p -> p.getDownvoters().size()).sum();
	}
	public int calculateCommentUpvotes(List<Comment> comments) {
		return comments.stream().mapToInt(c -> c.getUpvoters().size()).sum();
	}
	public int calculateCommentDownvotes(List<Comment> comments) {
		return comments.stream().mapToInt(c -> c.getDownvoters().size()).sum();
	}
	public int calculatePostKarma(List<Post> posts) {
		return calculatePostUpvotes(posts) - calculatePostDownvotes(posts);
	}
	public int calculateCommentKarma(List<Comment> comments) {
		return calculateCommentUpvotes(comments) - calculateCommentDownvotes(comments);
	}
	public int calculateKarma(List<Post> posts, List<Comment> comments) {
		return calculatePostKarma(posts) + calculateCommentKarma(comments);
	}
}