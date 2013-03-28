package realtimeweb.businesssearch.regular;

import realtimeweb.businesssearch.exceptions.BusinessSearchException;

/**
 * A listener for handling data received about a specific Business. On success,
 * an instance of a Business will be passed to the businessDataCompleted method,
 * which must be overridden in any implementing classes. On failure, an
 * exception is passed to the businessDataFailed method, which must also be
 * overridden in any implementing class.
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
