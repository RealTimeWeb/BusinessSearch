package realtimeweb.businesssearch.regular;

import realtimeweb.businesssearch.exceptions.BusinessSearchException;

/**
 * A listener for handling data received about a list of businesses. On success,
 * an instance of a SearchResponse containing all the information about the List
 * of Businesses will be passed to the onSuccess method, which must be
 * overridden in any implementing classes. On failure, the default behavior is
 * to print any exceptions to the standard error, although the onFailure method
 * can also be overridden.
 * 
 * @author acbart
 * 
 */
public interface BusinessSearchListener {
	/**
	 * The method that should be overridden to handle the SearchResponse.
	 * 
	 * @param businessData
	 */
	public abstract void businessSearchCompleted(SearchResponse searchResponse);
	
	/**
	 * The method that should be overridden to handle an exception that occurred
	 * while getting the SearchResponse.
	 * 
	 * @param exception
	 */
	public abstract void businessSearchFailed(BusinessSearchException  exception);
}
