package realtimeweb.businesssearch.regular;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import realtimeweb.businesssearch.main.BusinessCategory;
import realtimeweb.businesssearch.util.PropertyString;

/**
 * A Business object represents a Business in the real world. It has a large
 * number of properties associated with it to describe the Business.
 * 
 * @author acbart
 * 
 */
public class Business {

	private String id;
	private boolean isClaimed;
	private boolean isClosed;
	private String name;
	private String imageUrl;
	private String url;
	private String mobileUrl;
	private String phone;
	private String displayPhone;
	private int reviewCount;
	private List<BusinessCategory> categories;
	private double distance;
	private float rating;
	private String ratingImageUrl;
	private String ratingImageUrlSmall;
	private String ratingImageUrlLarge;
	private String snippetText;
	private String snippetImageUrl;
	private DetailedLocation location;
	private List<Deal> deals;
	private List<GiftCertificate> giftCertificates;
	private List<Review> reviews;
	private boolean isComplete;

	@SuppressWarnings("unchecked")
	Business(Map<String, Object> raw) {
		this.isComplete = true;
		this.id = (String) raw.get("id");
		this.isClaimed = Boolean.parseBoolean(raw.get("is_claimed").toString());
		this.isClosed = Boolean.parseBoolean(raw.get("is_closed").toString());
		this.name = (String) raw.get("name");
		this.imageUrl = (String) raw.get("image_url");
		this.url = (String) raw.get("url");
		this.mobileUrl = (String) raw.get("mobile_url");
		this.phone = (String) raw.get("phone");
		this.displayPhone = (String) raw.get("display_phone");
		this.reviewCount = Integer
				.parseInt((raw.get("review_count").toString()));

		this.categories = new ArrayList<BusinessCategory>();
		if (raw.get("categories") != null) {
			Iterator<ArrayList<String>> i = ((ArrayList<ArrayList<String>>) raw
					.get("categories")).iterator();
			while (i.hasNext()) {
				this.categories.add(BusinessCategory.getFromAlias(i.next()));
			}
		}

		if (raw.containsKey("distance")) {
			this.distance = Double
					.parseDouble((raw.get("distance").toString()));
		} else {
			this.distance = -1;
		}
		this.rating = Float.parseFloat((raw.get("rating").toString()));

		this.ratingImageUrl = (String) raw.get("rating_image_url");
		this.ratingImageUrlSmall = (String) raw.get("rating_image_url_small");
		this.ratingImageUrlLarge = (String) raw.get("rating_image_url_large");
		this.snippetText = (String) raw.get("snippet_text");
		this.snippetImageUrl = (String) raw.get("snippet_image_url");
		this.location = new DetailedLocation(
				(HashMap<String, Object>) raw.get("location"));

		this.deals = new ArrayList<Deal>();
		if (raw.containsKey("deals")) {
			Iterator<HashMap<String, Object>> d = ((ArrayList<HashMap<String, Object>>) raw
					.get("deals")).iterator();
			while (d.hasNext()) {
				this.deals.add(new Deal(d.next()));
			}
		}

		this.giftCertificates = new ArrayList<GiftCertificate>();
		if (raw.containsKey("gift_certificates")) {
			Iterator<HashMap<String, Object>> g = ((ArrayList<HashMap<String, Object>>) raw
					.get("gift_certificates")).iterator();
			while (g.hasNext()) {
				this.giftCertificates.add(new GiftCertificate(g.next()));
			}
		}

		this.reviews = new ArrayList<Review>();
		if (raw.containsKey("reviews")) {
			Iterator<HashMap<String, Object>> r = ((ArrayList<HashMap<String, Object>>) raw
					.get("reviews")).iterator();
			while (r.hasNext()) {
				this.reviews.add(new Review(r.next()));
			}
		}
	}

	/**
	 * Creates a new String representation of this Business, which will include
	 * the businesses ID and Name.
	 * 
	 * @return A string representing the Business
	 */
	public String toString() {
		return (new PropertyString("Business")).add("id", this.id.toString())
				.add("name", this.name.toString()).toString();
	}

	/**
	 * Provides a list of the categories that this business falls under.
	 * 
	 * @return A list of the categories that this business is in
	 */
	public List<BusinessCategory> getCategories() {
		return categories;
	}

	/**
	 * Provides deal info for this business. If there are no deals, then the
	 * list will be empty.
	 * 
	 * @return A list of the deals for this business
	 */
	public List<Deal> getDeals() {
		return deals;
	}

	/**
	 * Provides a phone number for this business that is especially
	 * human-readable.
	 * 
	 * @return A string representation of this phone number.
	 */
	public String getDisplayPhone() {
		return displayPhone;
	}

	/**
	 * Returns the distance that business is from search location in meters, if
	 * a latitude/longitude was specified in the original search. Otherwise, it
	 * will return -1.
	 * 
	 * @return The distance from the search location, or -1.
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Returns a list of Gift certificates for this business. If there are no
	 * gift certificates, the list will be empty.
	 * 
	 * @return the giftCertificates
	 */
	public List<GiftCertificate> getGiftCertificates() {
		return giftCertificates;
	}

	/**
	 * Returns the Yelp ID for this business. A Yelp ID is a string of
	 * characters that uniquely identifies this business, like
	 * '5Ji5L2GF0ppuwVyC_ET6mg' or 'uyrIgOpomZhXXbYHPx951w'.
	 * 
	 * @return The unique id for this business.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Provides the URL of photo for this business.
	 * 
	 * @return A url expressed as a String.
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * Returns the location of this business. A lot of information is provided,
	 * so a DetailedLocation is returned.
	 * 
	 * @return The detailed location of this business.
	 */
	public DetailedLocation getLocation() {
		return location;
	}

	/**
	 * The URL for the mobile business page on Yelp. This is suitable for
	 * loading the businesses page on a mobile-device.
	 * 
	 * @return A string representing the mobile-friendly URL.
	 */
	public String getMobileUrl() {
		return mobileUrl;
	}

	/**
	 * The human-readable name of this Business.
	 * 
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * The Phone number for this business with international dialing code (e.g.
	 * +442079460000). If you want to display the number to humans, consider
	 * {@link #getDisplayPhone()} instead.
	 * 
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * The URL to the 5-Star Rating image for this business. A business can have
	 * 1-5 stars. The image given by this URL is 84px by 17px.
	 * 
	 * @return The url to the image of the rating.
	 */
	public String getRatingImageUrl() {
		return ratingImageUrl;
	}

	/**
	 * A link to a larger version of the 5-star Rating image. The image linked
	 * will be 166px by 30px. See {@link #getRatingImageUrl} for more
	 * information about the image returned by this function.
	 * 
	 * @return The URL to the image of the rating.
	 */
	public String getRatingImageUrlLarge() {
		return ratingImageUrlLarge;
	}

	/**
	 * A link to a smaller version of the 5-star Rating image. The image linked
	 * will be 50px by 10px. See {@link #getRatingImageUrl} for more information
	 * about the image returned by this function.
	 * 
	 * @return The URL to the image of the rating.
	 */
	public String getRatingImageUrlSmall() {
		return ratingImageUrlSmall;
	}

	/**
	 * Provides the number of reviews for this business.
	 * 
	 * @return The review count.
	 */
	public int getReviewCount() {
		return reviewCount;
	}

	/**
	 * Returns a list of the reviews associated with this business. There will
	 * be up to 3 reviews; if there are none, an empty list is returned.
	 * 
	 * @return The list of reviews.
	 */
	public List<Review> getReviews() {
		return reviews;
	}

	/**
	 * Provides the URL of snippet image associated with this business.
	 * 
	 * @return The URL of snippet image.
	 */
	public String getSnippetImageUrl() {
		return snippetImageUrl;
	}

	/**
	 * Provides the Snippet text associated with this business.
	 * 
	 * @return the Snippet .Text
	 */
	public String getSnippetText() {
		return snippetText;
	}

	/**
	 * Provides the URL for business page on Yelp. If you want to get a version
	 * of the page suited for mobile devices, consider {@link #getMobileUrl()}.
	 * 
	 * @return The URL of the business page on Yelp.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Returns whether the business page has been claimed by the owner.
	 * 
	 * @return Whether the page has been claimed.
	 */
	public boolean isClaimed() {
		return isClaimed;
	}

	/**
	 * Returns whether the business has been permanently closed.
	 * 
	 * @return Whether the business is closed.
	 */
	public boolean isClosed() {
		return isClosed;
	}

	/**
	 * Returns the rating for this business (value ranges from 1, 1.5, ... 4.5,
	 * 5). If you want to get an image representation of this rating, you can
	 * use {@link #getRatingImageUrl()}, and if you want a smaller or larger
	 * version you can use {@link #getRatingImageUrlLarge()} or
	 * {@link #getRatingImageUrlSmall()}.
	 * 
	 * @return
	 */
	public float getRating() {
		return rating;
	}

	/**
	 * An internal method to signal that this Business is only a preview, as
	 * opposed to a full view.
	 * 
	 * @param isComplete
	 */
	void setIsComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	/**
	 * Returns whether this is a fully-defined Business (gotten via a
	 * getBusinessData call), or just a shallow preview returned via a
	 * SearchResponse. If the latter, then you can use
	 * {@link BusinessSearch#getBusinessData getBusinessData} to fill out its
	 * information.
	 * 
	 * @return
	 */
	public boolean isComplete() {
		return this.isComplete;
	}

	/**
	 * <b>This is an internal method, please ignore it.</b><br><br>
	 * 
	 * Fills in any missing data to this Business from a given map. 
	 * @param raw
	 */
	@SuppressWarnings("unchecked")
	void fillIn(Map<String, Object> raw) {
		this.isComplete = true;
		this.id = (String) raw.get("id");
		this.isClaimed = Boolean.parseBoolean(raw.get("is_claimed").toString());
		this.isClosed = Boolean.parseBoolean(raw.get("is_closed").toString());
		this.name = (String) raw.get("name");
		this.imageUrl = (String) raw.get("image_url");
		this.url = (String) raw.get("url");
		this.mobileUrl = (String) raw.get("mobile_url");
		this.phone = (String) raw.get("phone");
		this.displayPhone = (String) raw.get("display_phone");
		this.reviewCount = Integer
				.parseInt((raw.get("review_count").toString()));

		this.categories.clear();
		if (raw.get("categories") != null) {
			Iterator<ArrayList<String>> i = ((ArrayList<ArrayList<String>>) raw
					.get("categories")).iterator();
			while (i.hasNext()) {
				this.categories.add(BusinessCategory.getFromAlias(i.next()));
			}
		}

		if (raw.containsKey("distance")) {
			this.distance = Double
					.parseDouble((raw.get("distance").toString()));
		} else {
			this.distance = -1;
		}
		this.rating = Float.parseFloat((raw.get("rating").toString()));

		this.ratingImageUrl = (String) raw.get("rating_image_url");
		this.ratingImageUrlSmall = (String) raw.get("rating_image_url_small");
		this.ratingImageUrlLarge = (String) raw.get("rating_image_url_large");
		this.snippetText = (String) raw.get("snippet_text");
		this.snippetImageUrl = (String) raw.get("snippet_image_url");
		this.location = new DetailedLocation(
				(HashMap<String, Object>) raw.get("location"));

		this.deals.clear();
		if (raw.containsKey("deals")) {
			Iterator<HashMap<String, Object>> d = ((ArrayList<HashMap<String, Object>>) raw
					.get("deals")).iterator();
			while (d.hasNext()) {
				this.deals.add(new Deal(d.next()));
			}
		}

		this.giftCertificates.clear();
		if (raw.containsKey("gift_certificates")) {
			Iterator<HashMap<String, Object>> g = ((ArrayList<HashMap<String, Object>>) raw
					.get("gift_certificates")).iterator();
			while (g.hasNext()) {
				this.giftCertificates.add(new GiftCertificate(g.next()));
			}
		}

		this.reviews.clear();
		if (raw.containsKey("reviews")) {
			Iterator<HashMap<String, Object>> r = ((ArrayList<HashMap<String, Object>>) raw
					.get("reviews")).iterator();
			while (r.hasNext()) {
				this.reviews.add(new Review(r.next()));
			}
		}
	}
}
