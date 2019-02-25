package app.barta.api;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Data;

@Data
public class User {

	@Id
	private String id;
	private int karma;
	@DBRef
	private List<Post> posts;
	@DBRef
	private List<Comment> comments;
	private String creationTime;
	
	public User() {
		karma = 0;
		posts = new ArrayList<>();
		comments = new ArrayList<>();
		creationTime = OffsetDateTime.now(ZoneId.of("UTC")).toString();
	}
}
