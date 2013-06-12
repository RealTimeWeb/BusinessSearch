package realtimeweb.businesssearch.exceptions;

/**
 * Indicates that API requests are unavailable in the given location.
 * 
 * @author acbart
 * 
 */
public class UnavailableForLocationException extends BusinessSearchException {
	private static final long serialVersionUID = -424816738695829724L;

	/**
	 * Indicates that API requests are unavailable in the given location.
	 * 
	 * @param id
	 *            The ID of the exception
	 * @param message
	 *            Some more information about the exception
	 * @param details
	 *            Specific details about the exception
	 */
	public UnavailableForLocationException(String id, String message, String details) {
		super(id, message, details);
	}

}
