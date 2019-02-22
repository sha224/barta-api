package app.barta.api;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Data;

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
	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
	private Point location;
	private int votes;
	@DBRef
	private List<User> upvoters;
	@DBRef
	private List<User> downvoters;
	private String time;
}
