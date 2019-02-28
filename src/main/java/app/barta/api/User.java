package app.barta.api;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class User {

	@Id
	private String id;
	private int karma;
	@DBRef
	@EqualsAndHashCode.Exclude
	private List<Post> posts;
	@DBRef
	@EqualsAndHashCode.Exclude
	private List<Comment> comments;
	private String creationTime;
}
