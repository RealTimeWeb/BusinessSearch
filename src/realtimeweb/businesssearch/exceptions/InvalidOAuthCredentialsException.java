package realtimeweb.businesssearch.exceptions;

/**
 * The request requires a user and the user specified is missing or invalid. The
 * user may be invalid if it is associated with a developer account.
 * 
 * @author acbart
 * 
 */
public class InvalidOAuthCredentialsException extends
		BusinessSearchException {
	private static final long serialVersionUID = -4280761775746395145L;

	/**
	 * The request requires a user and the user specified is missing or invalid.
	 * The user may be invalid if it is associated with a developer account.
	 * 
	 * @param id
	 *            The ID of the exception
	 * @param message
	 *            Some more information about the exception
	 * @param details
	 *            Specific details about the exception
	 */
	public InvalidOAuthCredentialsException(String id, String message,
			String details) {
		super(id, message, details);
	}

}
