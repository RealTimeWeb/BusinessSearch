package realtimeweb.businesssearch.json;

import realtimeweb.businesssearch.exceptions.BusinessSearchException;

/**
 * A listener for handling data received about a specific Business. On success,
 * a string of JSON containing all the information about the Business will be
 * passed to the businessDataCompleted method, which must be overridden in any
 * implementing classes. On failure, an exception is passed to the
 * businessDataFailed method, which must also be overridden in any implementing
 * class.<br>
 * <br>
 * 
 * Details about the JSON string returned can be found in the Yelp API
 * documentation: http://www.yelp.com/developers/documentation/v2/business
 * 
 * @author acbart
 * 
 */
public interface JsonBusinessDataListener {
	/**
	 * The method that should be overridden to handle the JSON-formatted
	 * business data. For reference on the data being returned, consult the Yelp
	 * API documentation:
	 * http://www.yelp.com/developers/documentation/v2/business
	 * 
	 * @param businessData
	 */
	public abstract void businessDataCompleted(String businessData);

	/**
	 * The method that should be overridden to handle the Exception that occurs
	 * while getting the Business Data.
	 * 
	 * @param exception
	 */
	public abstract void businessDataFailed(BusinessSearchException exception);
}
