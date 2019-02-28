package app.barta.api;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(User.class)
public class UserEventHandler {

	@HandleBeforeCreate
	public void handleBeforeCreate(User user) {
		user.setKarma(0);
		user.setPosts(new ArrayList<>());
		user.setComments(new ArrayList<>());
		user.setCreationTime(OffsetDateTime.now(ZoneId.of("UTC")).toString());
	}
}
