package realtimeweb.businesssearch.regular;

import java.util.HashMap;

/**
 * A User is someone who uses the Yelp service. They have a unique ID, a name,
 * and a profile picture.
 * 
 * @author acbart
 * 
 */
public class User {
	private String id;
	private String imageUrl;
	private String name;

	/**
	 * <b>This method is for internal use only. Please do not use it.</b>
	 * @param raw
	 */
	User(HashMap<String, Object> raw) {
		this.id = (String) raw.get("id");
		this.imageUrl = (String) raw.get("image_url");
		this.name = (String) raw.get("name");
	}

	/**
	 * Return the user's unique identifier.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Return the URL to the user's profile picture.
	 * 
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * Return the user's name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
