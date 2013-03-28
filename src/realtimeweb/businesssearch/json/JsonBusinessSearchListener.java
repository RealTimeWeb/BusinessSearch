package realtimeweb.businesssearch.json;

import realtimeweb.businesssearch.exceptions.BusinessSearchException;

/**
 * A listener for handling data received about a list of businesses. On success,
 * a string of JSON containing all the information about the List of Businesses
 * will be passed to the businessSearchCompleted method, which must be
 * overridden in any implementing classes. On failure, an exception is passed to
 * the businessSearchFailed method, which must also be overridden in any
 * implementing class.<br>
 * <br>
 * 
 * Details about the JSON string returned can be found in the <a
 * href='http://www.yelp.com/developers/documentation/v2/search_api'>Yelp API
 * documentation.</a>
 * 
 * @author acbart
 * 
 */
public interface JsonBusinessSearchListener {
	/**
	 * The method that should be overridden to handle the JSON-formatted list of
	 * businesses data. For reference on the data being returned, consult the <a
	 * href='http://www.yelp.com/developers/documentation/v2/search_api'>Yelp
	 * API documentation.</a>
	 * 
	 * @param businessData
	 */
	public abstract void businessSearchCompleted(String searchResponse);

	/**
	 * The method that should be overridden to handle an exception that occurred
	 * while getting the JSON response of the query.
	 * 
	 * @param exception
	 */
	public abstract void businessSearchFailed(BusinessSearchException exception);
}
