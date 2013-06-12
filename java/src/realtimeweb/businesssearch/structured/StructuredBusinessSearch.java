package realtimeweb.businesssearch.structured;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.ParseException;

import realtimeweb.businesssearch.exceptions.BusinessSearchException;
import realtimeweb.businesssearch.exceptions.DataUnreadableException;
import realtimeweb.businesssearch.json.JsonBusinessDataListener;
import realtimeweb.businesssearch.json.JsonBusinessSearch;
import realtimeweb.businesssearch.json.JsonBusinessSearchListener;
import realtimeweb.businesssearch.main.AbstractBusinessSearch;
import realtimeweb.businesssearch.main.BusinessQuery;
import realtimeweb.businesssearch.util.JsonConverter;

/**
 * The StructuredBusinessSearch class is used to connect to the Yelp service and
 * retrieve data as a Map. All data is returned asynchronously - your program
 * will continue to run while the data is being fetched, and the result will be
 * sent to a callback function that you specify.<br>
 * <br>
 * 
 * There will only be one instance of the StructuredBusinessSearch, because it
 * follows the Singleton pattern. In order to use it, first get the instance of
 * the class.
 * 
 * <pre>
 * StructuredBusinessSearch sbs = StructuredBusinessSearch.getInstance();
 * </pre>
 * 
 * Then you can either immediately use the service offline, or connect to the online service:
 * 
 * <pre>
 * sbs.connect(&quot;key&quot;, &quot;secret&quot;, &quot;token&quot;, &quot;secrettoken&quot;);
 * </pre>
 * 
 * To disconnect from the online service, simply:
 * 
 * <pre>
 * sbs.disconnectLocal();
 * </pre>
 * 
 * @author acbart
 * 
 */
public class StructuredBusinessSearch implements AbstractBusinessSearch {
	private static StructuredBusinessSearch instance;

	/**
	 * Retrieves the singleton instance of the JsonBusinessSearch.
	 * 
	 * @return
	 */
	public static StructuredBusinessSearch getInstance() {
		if (instance == null) {
			synchronized (StructuredBusinessSearch.class) {
				if (instance == null) {
					instance = new StructuredBusinessSearch();
				}
			}
		}
		return instance;
	}

	/**
	 * Protected constructor to guard against instantiation.
	 */
	protected StructuredBusinessSearch() {
		jsonBusinessSearch = JsonBusinessSearch.getInstance();
	}

	private JsonBusinessSearch jsonBusinessSearch;

	/**
	 * Returns data about a business given its businessId (which is a sequence
	 * of characters, symbols, and numbers, e.g. "I_7cQmHxx6LokP9yr7Fx-w").
	 * 
	 * @param businessId
	 *            A string identifying the business.
	 * @param listener
	 *            a callback function that will consume the data (or error)
	 *            about the business.
	 */
	public void getBusinessData(String businessId,
			final StructuredBusinessDataListener listener) {
		this.jsonBusinessSearch.getBusinessData(businessId,
				new JsonBusinessDataListener() {

					@Override
					public void businessDataCompleted(String business) {
						HashMap<String, Object> structuredBusiness = null;
						if (business == "") {
							listener.businessDataCompleted(StructuredBusinessSearch.getEmptyBusiness());
							return;
						}
						try {
							structuredBusiness = (HashMap<String, Object>) JsonConverter
									.convertToMap(business);
						} catch (ParseException e) {
							listener.businessDataFailed(new DataUnreadableException(
									"INVALID_DATA",
									"The JSON data received from the server couldn't be parsed.",
									e.toString()));
						}
						listener.businessDataCompleted(structuredBusiness);
					}

					@Override
					public void businessDataFailed(BusinessSearchException exception) {
						listener.businessDataFailed(exception);
					}

				});
	}

	@SuppressWarnings("rawtypes")
	protected static Map<String, Object> getEmptyBusiness() {
		HashMap<String, Object> empty = new HashMap<String, Object>();
		empty.put("categories", new ArrayList());
		empty.put("deals", new ArrayList());
		empty.put("gift_certificates", new ArrayList());
		empty.put("reviews", new ArrayList());
		empty.put("id", "");
		empty.put("display_phone", "");
		empty.put("image_url", "");
		empty.put("is_claimed", false);
		empty.put("is_closed", false);
		empty.put("mobile_url", "");
		empty.put("name", "");
		empty.put("phone", "");
		empty.put("rating", 1.0);
		empty.put("rating_img_url", "");
		empty.put("rating_img_url_large", "");
		empty.put("rating_img_url_small", "");
		empty.put("review_count", 0);
		empty.put("snippet_image_url", "");
		empty.put("snippet_text", "");
		empty.put("url", "");
		
		HashMap<String, Object> location = new HashMap<String, Object>();
		location.put("address", new ArrayList());
		location.put("display_address", new ArrayList());
		location.put("neighborhoods", new ArrayList());
		location.put("city", "");
		location.put("country_code", "");
		location.put("cross_streets", "");
		location.put("geo_accuracy", 4);
		location.put("postal_code", "");
		location.put("state_code", "");
		HashMap<String, Object> coordinate= new HashMap<String, Object>();
		coordinate.put("latitude", 0.0);
		coordinate.put("longitude", 0.0);
		location.put("coordinate", coordinate);
		
		empty.put("location", location);
		
		return empty;
	}

	/**
	 * Queries the Yelp service for businesses that match the given
	 * BusinessQuery. Returns the data (or error) through the listener object.
	 * 
	 * @param query
	 *            A BusinessQuery object
	 * @param listener
	 *            A listener object that should contain methods for handling the
	 *            data and, optionally, any errors.
	 */
	public void searchBusinesses(BusinessQuery query,
			final StructuredBusinessSearchListener listener) {
		this.jsonBusinessSearch.searchBusinesses(query,
				new JsonBusinessSearchListener() {

					@Override
					public void businessSearchCompleted(String response) {
						HashMap<String, Object> structuredResponse = null;
						if (response == "") {
							listener.businessSearchCompleted(StructuredBusinessSearch.getEmptySearchReponse());
							return;
						}
						try {
							structuredResponse = (HashMap<String, Object>) JsonConverter
									.convertToMap(response);
						} catch (ParseException e) {
							listener.businessSearchFailed(new DataUnreadableException(
									"INVALID_DATA",
									"The JSON data received from the server couldn't be parsed.",
									e.toString()));
						}
						listener.businessSearchCompleted(structuredResponse);
					}

					public void businessSearchFailed(BusinessSearchException exception) {
						listener.businessSearchFailed(exception);
					}

				});
	}

	protected static Map<String, Object> getEmptySearchReponse() {
		HashMap<String, Object> empty = new HashMap<String, Object>();
		empty.put("businesses", new ArrayList<String>());
		HashMap<String, Object> region = new HashMap<String, Object>();
		HashMap<String, Object> center= new HashMap<String, Object>();
		center.put("latitude", 0.0);
		center.put("longitude", 0.0);
		HashMap<String, Object> span= new HashMap<String, Object>();
		span.put("latitude_delta", 0.0);
		span.put("longitude_delta", 0.0);
		region.put("center", center);
		region.put("span", span);
		empty.put("region", region);
		empty.put("total", 0);
		return empty;
	}

	/**
	 * Establishes a connection to the online Business Search service.
	 * dataservice. This requires an internet connection.<br><br>
	 * 
	 * Requires registration information from Yelp. To get your key go to
	 * <a href='http://www.yelp.com/developers'>http://www.yelp.com/developers</a>
	 * 
	 * @param consumerKey
	 * @param consumerSecret
	 * @param token
	 * @param tokenSecret
	 */
	public void connect(String consumerKey, String consumerSecret,
			String token, String tokenSecret) {
		this.jsonBusinessSearch.connect(consumerKey, consumerSecret, token,
				tokenSecret);
	}

	/**
	 * Establishes that Business Search data should be retrieved locally. This
	 * does not require an internet connection.<br><br>
	 * 
	 * If data is being retrieved locally, you must be sure that your parameters
	 * match locally stored data. Otherwise, you will get nothing in return.
	 * 
	 * @param local
	 */
	public void disconnect() {
		this.jsonBusinessSearch.disconnect();
	}

}
