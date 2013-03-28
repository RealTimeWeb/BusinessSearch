package realtimeweb.businesssearch.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.json.simple.parser.ParseException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import realtimeweb.businesssearch.exceptions.BusinessSearchException;
import realtimeweb.businesssearch.main.AbstractBusinessSearch;
import realtimeweb.businesssearch.main.BusinessCategory;
import realtimeweb.businesssearch.main.BusinessQuery;
import realtimeweb.businesssearch.util.JsonConverter;

/**
 * The JsonBusinessSearch class is used to connect to the Yelp service and
 * retrieve data JSON formatted data. All data is returned asynchronously - your
 * program will continue to run while the data is being fetched, and the result
 * will be sent to a callback function that you specify.<br>
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
 * You can use the service offline immediately, or connect to the online
 * service:
 * 
 * <pre>
 * sbs.connect(&quot;key&quot;, &quot;secret&quot;, &quot;token&quot;, &quot;secrettoken&quot;);
 * </pre>
 * 
 * If you need to disconnect, it is simple:
 * 
 * <pre>
 * sbs.disconnect();
 * </pre>
 * 
 * @author acbart
 * 
 */
public class JsonBusinessSearch implements AbstractBusinessSearch {

	protected boolean local;
	protected OAuthService service;
	protected Token accessToken;
	private ClientStore clientStore;

	private static JsonBusinessSearch instance;

	private boolean storing = false;

	/**
	 * <b>This method is for internal use only.</b><br>
	 * <br>
	 * 
	 * When the system is storing, any requests made to the online Yelp service
	 * will be stored for future use. However, those requests will not be saved
	 * between runs of your program.
	 */
	public void setStoring(boolean storing) {
		this.storing = storing;
	}

	/**
	 * Retrieves the singleton instance of the JsonBusinessSearch.
	 * 
	 * @return
	 */
	public static JsonBusinessSearch getInstance() {
		if (instance == null) {
			synchronized (JsonBusinessSearch.class) {
				if (instance == null) {
					instance = new JsonBusinessSearch(false);
				}
			}
		}
		return instance;
	}

	/**
	 * <b>This method is for internal use only.</b><br>
	 * <br>
	 * 
	 * Retrieves the singleton instance of the JsonBusinessSearch, optionally
	 * turning recording on or off.
	 * 
	 * @return
	 */
	static JsonBusinessSearch getRecordingInstance() {
		if (instance == null) {
			synchronized (JsonBusinessSearch.class) {
				if (instance == null) {
					instance = new JsonBusinessSearch(true);
				}
			}
		}
		return instance;
	}

	/**
	 * A protected constructor to defeat instantation of this singleton.
	 */
	protected JsonBusinessSearch(boolean recording) {
		disconnect();
		setStoring(recording);
		this.service = null;
		this.accessToken = null;
		this.clientStore = new ClientStore(recording);
		BusinessCategory.getCategories();
	}

	/**
	 * Establishes a connection to the online Business Search service.
	 * dataservice. This does not require an internet connection.<br><br>
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
		this.local = false;
		this.service = new ServiceBuilder()
				.provider(ServiceAuthentication.class).apiKey(consumerKey)
				.apiSecret(consumerSecret).build();
		this.accessToken = new Token(token, tokenSecret);
	}

	/**
	 * Establishes that data should be retrieved locally instead of from the
	 * online service. This is the default behavior for the BusinessSearch.<br>
	 * <br>
	 * 
	 * If data is being retrieved locally, you must be sure that your parameters
	 * match locally stored data. Otherwise, you will get nothing in return.
	 * 
	 * @param local
	 */
	public void disconnect() {
		this.local = true;
	}

	/**
	 * The function is used to create a request to the server. By converting a
	 * request to a string, you can get an accurate and storeable version of it.
	 * 
	 * @param destination
	 * @param hashMap
	 * @return
	 */
	OAuthRequest makeRequest(String destination, HashMap<String, String> hashMap) {
		OAuthRequest request = new OAuthRequest(Verb.GET,
				"http://api.yelp.com/v2/" + destination);
		for (Entry<String, String> parameter : hashMap.entrySet()) {
			String key = parameter.getKey();
			String value = parameter.getValue();
			request.addQuerystringParameter(key, value);
		}
		if (!this.local) {
			this.service.signRequest(this.accessToken, request);
		}
		return request;
	}

	/**
	 * This function is used to send a request to the server.
	 * 
	 * @param request
	 * @return
	 */
	String sendRequest(OAuthRequest request) throws OAuthException {
		String hashedRequest = hashRequest(request);
		if (local) {
			return clientStore.getData(hashedRequest);
		} else {
			String response = request.send().getBody();
			if (storing) {
				clientStore.putData(hashedRequest, response);
			}
			return response;
		}
	}

	/**
	 * Convert a Request object to an injective hash.<br><br>
	 * 
	 * An injective hash has no collisions. Hopefully that can't occur with
	 * this.
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static String hashRequest(OAuthRequest request) {
		String url = request.getUrl();
		String queryStrings = (new TreeMap(request.getQueryStringParams()))
				.toString();
		String bodyParams = (new TreeMap(request.getBodyParams())).toString();
		return url + "%" + queryStrings + "%" + bodyParams;
	}

	/**
	 * A method that will convert a Yelp error to a Java exception, if there is
	 * one.
	 * 
	 * @param response
	 * @return
	 */
	BusinessSearchException checkErrors(String response) {
		if (response.isEmpty()) {
			if (local) {
				return null;
			} else {
				return new BusinessSearchException(
						"Local",
						"Could not connect to the online Yelp service. Try using a local connection instead.",
						"");
			}
		}
		try {
			HashMap<String, Object> r = (HashMap<String, Object>) JsonConverter
					.convertToMap(response);
			if (r.containsKey("error")) {
				@SuppressWarnings("unchecked")
				HashMap<String, Object> error = (HashMap<String, Object>) r
						.get("error");
				return new BusinessSearchException((String) error.get("id"),
						(String) error.get("text"), (String) error.get("field"));
			}
		} catch (ParseException e) {
			return new BusinessSearchException(
					"Local",
					"The response from the Yelp Service was not valid JSON. Check the fields for more information on what was returned.",
					response);
		}
		return null;
	}

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
	public void getBusinessData(final String businessId,
			final JsonBusinessDataListener listener) {
		final JsonBusinessSearch _this = this;
		Thread thread = new Thread() {
			public void run() {
				OAuthRequest request = _this.makeRequest("business/"
						+ businessId, new HashMap<String, String>());
				String response = _this.sendRequest(request);
				BusinessSearchException error = _this.checkErrors(response);
				if (error == null) {
					listener.businessDataCompleted(response);
				} else {
					listener.businessDataFailed(error);
				}
			}
		};
		thread.start();
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
	public void searchBusinesses(final BusinessQuery query,
			final JsonBusinessSearchListener listener) {
		final JsonBusinessSearch _this = this;
		Thread thread = new Thread() {
			public void run() {
				OAuthRequest request = _this.makeRequest("search",
						query.getFilters());
				String response = _this.sendRequest(request);
				BusinessSearchException error = _this.checkErrors(response);
				if (error == null) {
					listener.businessSearchCompleted(response);
				} else {
					listener.businessSearchFailed(error);
				}
			}
		};
		thread.start();
	}

	/**
	 * <b>This is an internal method. Do not use it!</b><br>
	 * <br>
	 * 
	 * Internal method to access this instance's local data.
	 * 
	 * @return
	 */
	public ClientStore getClientStore() {
		return clientStore;
	}

	/**
	 * <b>This is an internal method. Do not use it.</b><br>
	 * 
	 * Internal method to save the current data store to a file.
	 * 
	 * @param file
	 * @param b
	 * @throws IOException
	 */
	public void save(String source, boolean append) throws IOException {
		this.clientStore.save(source, append);
	}

	/*
	 * public static void main(String[] args) { final JsonBusinessSearch jbs =
	 * JsonBusinessSearch.getRecordingInstance();
	 * jbs.connect("em86viPSqwmfF2PFfNsPEQ", "K7Dq24NKDMNNk-sz_-JMlAvDmSU",
	 * "hbML2QjyBfh-fvw5PsiF71pVLt2m3AbZ", "ggqII8lp1foy0ttolsYrTIUAm7c");
	 * jbs.searchBusinesses(new BusinessQuery("Blacksburg, VA"), new
	 * JsonBusinessSearchListener() {
	 * 
	 * @Override public void onSuccess(String searchResponse) {
	 * jbs.getBusinessData("gillies-cuisine-blacksburg", new
	 * JsonBusinessDataListener() {
	 * 
	 * @Override public void onSuccess(String businessData) { try {
	 * jbs.save("cache.json", false); System.out.println("Saved"); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * } }
	 * 
	 * }); } }); }
	 */

}
