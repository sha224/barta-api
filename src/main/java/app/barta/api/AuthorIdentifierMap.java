package app.barta.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthorIdentifierMap {

	private List<Integer> identifiers;
	private Map<String, Integer> map;
	
	public AuthorIdentifierMap() {
		this(100);
	}
	
	public AuthorIdentifierMap(int limit) {
		identifiers = new ArrayList<>();
		map = new HashMap<>();
		for(int i = 0; i < limit; i++)
			identifiers.add(i);
		Collections.shuffle(identifiers);
	}
	
	public Integer getIdentifier(User author) {
		if (!map.containsKey(author.getId()))
			map.put(author.getId(), identifiers.get(map.size()));
		return map.get(author.getId());
	}
}
