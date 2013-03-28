package realtimeweb.businesssearch.regular;

import realtimeweb.businesssearch.exceptions.BusinessSearchException;

/**
 * A listener for handling data received about a specific Business. On success,
 * an instance of a Business will be passed to the onSuccess method, which must
 * be overridden in any implementing classes. On failure, the default behavior
 * is to print any exceptions to the standard error, although the onFailure
 * method can also be overridden.
 * 
 * @author acbart
 * 
 */
public interface BusinessDataListener {
	/**
	 * The method that should be overridden to handle the Business instance.
	 * 
	 * @param businessData
	 */
	public abstract void businessDataCompleted(Business business);

	/**
	 * The method that should be overridden to handle an exception that occurred
	 * while getting the Business Instance
	 * 
	 * @param exception
	 */
	public abstract void businessDataFailed(BusinessSearchException exception);
}
