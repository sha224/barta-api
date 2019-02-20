package app.barta.api;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import lombok.Data;

@Data
public class Post {

	@Id
	private String id;
	private String text;
	private User author;
	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
	private Point location;
	private List<Comment> comments;
	private int votes;
	private List<User> upvoters;
	private List<User> downvoters;
	private String time;
}
