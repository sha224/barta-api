package app.barta.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Data;

@Data
public class User {

	@Id
	private String id;
	private int karma = 0;
	@DBRef
	private List<Post> posts = new ArrayList<>();
	@DBRef
	private List<Comment> comments = new ArrayList<>();
	private String time;
}
