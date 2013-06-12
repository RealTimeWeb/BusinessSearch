package realtimeweb.businesssearch.exceptions;

/**
 * Indicates that the number of requests executed for the given API key has
 * exceeded its daily limit.
 * 
 * @author acbart
 * 
 */
public class ExceededReqsException extends BusinessSearchException {

	private static final long serialVersionUID = 8709139033893600833L;

	/**
	 * Indicates that the number of requests executed for the given API key has
	 * exceeded its daily limit.
	 * 
	 * @param id
	 *            The ID of the exception
	 * @param message
	 *            Some more information about the exception
	 * @param details
	 *            Specific details about the exception
	 */
	public ExceededReqsException(String id, String message, String details) {
		super(id, message, details);
	}

}
