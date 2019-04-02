package app.barta.api;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class Comment {

	@Id
	private String id;
	private String text;
	@DBRef
	private Post post;
	@DBRef
	private User author;
	private int authorIdentifier;
	@DBRef
	@EqualsAndHashCode.Exclude
	private List<User> upvoters;
	@DBRef
	@EqualsAndHashCode.Exclude
	private List<User> downvoters;
	private String creationTime;
}
