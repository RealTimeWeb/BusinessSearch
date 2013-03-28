package realtimeweb.businesssearch.regular;

import java.util.HashMap;

/**
 * A Review contains user-supplied information about a Business.
 * @author acbart
 *
 */
public class Review {
	private String id;
	private int rating;
	private String ratingImageUrl;
	private String ratingImageUrlSmall;
	private String ratingImageUrlLarge;
	private String excerpt;
	private int timeCreated;
	private User user;

	
	@SuppressWarnings("unchecked")
	Review(HashMap<String, Object> raw) {
		this.id = (String) raw.get("id");
		this.rating = Integer.parseInt((raw.get("rating").toString()));
		this.ratingImageUrl = (String) raw.get("rating_image_url");
		this.ratingImageUrlSmall = (String) raw.get("rating_image_small_url");
		this.ratingImageUrlLarge = (String) raw.get("rating_image_large_url");
		this.excerpt = (String) raw.get("excerpt");
		this.timeCreated = Integer.parseInt((raw.get("time_created").toString()));
		this.user = new User((HashMap<String, Object>)raw.get("user"));
	}

	/**
	 * Returns a unique identifier for the Review.
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * Returns a value from 1-5 rating the Business.
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}
	/**
	 * Returns a URL that links to an image of stars for this business (size = 84x17).
	 * @return the ratingImageUrl
	 */
	public String getRatingImageUrl() {
		return ratingImageUrl;
	}
	/**
	 * Returns a URL that links to a smaller image of stars for this business (size = 50x10).
	 * @return the ratingImageUrlSmall
	 */
	public String getRatingImageUrlSmall() {
		return ratingImageUrlSmall;
	}
	/**
	 * Returns a URL that links to a larger image of stars for this business (size = 166x30).
	 * @return the ratingImageUrlLarge
	 */
	public String getRatingImageUrlLarge() {
		return ratingImageUrlLarge;
	}
	/**
	 * Returns an excerpt of the text of this review. 
	 * @return the excerpt
	 */
	public String getExcerpt() {
		return excerpt;
	}
	/**
	 * Returns a Unix Timestamp indicating the date/time that this Review was created.
	 * @return the timeCreated
	 */
	public int getTimeCreated() {
		return timeCreated;
	}
	/**
	 * Returns the User who wrote this review.
	 * @return the user
	 */
	public User getUser() {
		return user;
	}	
}
