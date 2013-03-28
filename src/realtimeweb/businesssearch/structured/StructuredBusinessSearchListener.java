package realtimeweb.businesssearch.structured;

import java.util.Map;

import realtimeweb.businesssearch.exceptions.BusinessSearchException;

/**
 * A listener for handling data received about a list of businesses. On success,
 * a Map containing all the information about the List of Businesses will be
 * passed to the businessSearchCompleted method, which must be overridden in any
 * implementing classes. On failure, an exception is passed to
 * the businessSearchFailed method, which must also be overridden in any
 * implementing class.<br>
 * <br>
 * 
 * Details about the fields of the Map returned can be found in the Yelp API
 * documentation: http://www.yelp.com/developers/documentation/v2/search_api
 * 
 * @author acbart
 * 
 */
public interface StructuredBusinessSearchListener {
	/**
	 * The method that should be overridden to handle the Map of the list of
	 * businesses. For reference on the data being returned, consult the Yelp
	 * API documentation:
	 * http://www.yelp.com/developers/documentation/v2/search_api
	 * 
	 * @param businessData
	 */
	public abstract void businessSearchCompleted(
			Map<String, Object> searchResponse);

	/**
	 * The method that should be overridden to handle an exception that occurred
	 * while getting the search response.
	 * 
	 * @param exception
	 */
	public abstract void businessSearchFailed(BusinessSearchException exception);
}
