package app.barta.api;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class User {

	@Id
	private String id;
	private int karma;
	private List<Post> posts;
	private List<Comment> comments;
	private String time;
}
