package app.barta.api;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Data;

@Data
public class Post {

	@Id
	private String id;
	private String text;
	@DBRef
	private User author;
	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
	private Point location;
	private List<Comment> comments;
	private int votes;
	private List<User> upvoters;
	private List<User> downvoters;
	private String creationTime;
	
	public Post() {
		comments = new ArrayList<>();
		votes = 0;
		upvoters = new ArrayList<>();
		downvoters = new ArrayList<>();
		creationTime = OffsetDateTime.now(ZoneId.of("UTC")).toString();
	}
}
