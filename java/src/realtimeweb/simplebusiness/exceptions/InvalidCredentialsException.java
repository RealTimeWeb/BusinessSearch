package realtimeweb.simplebusiness.exceptions;

/**
 * Indicates that the email/password for Yelp account authorization is invalid.
 * 
 * @author acbart
 * 
 */
public class InvalidCredentialsException extends BusinessSearchException {
	private static final long serialVersionUID = 1668098427418698391L;

	/**
	 * Indicates that the email/password for Yelp account authorization is invalid.
	 * 
	 * @param id
	 *            The ID of the exception
	 * @param message
	 *            Some more information about the exception
	 * @param details
	 *            Specific details about the exception
	 */
	public InvalidCredentialsException(String id, String message,
			String details) {
		super(id, message, details);
	}

}
