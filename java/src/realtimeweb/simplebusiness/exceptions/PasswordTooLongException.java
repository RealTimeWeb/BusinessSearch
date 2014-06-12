package realtimeweb.simplebusiness.exceptions;

/**
 * Indicates that the password for the Yelp account is too long. Passwords
 * should be 16 characters or less.
 * 
 * @author acbart
 * 
 */
public class PasswordTooLongException extends BusinessSearchException {
	private static final long serialVersionUID = 7794691201496383206L;

	/**
	 * Indicates that the password for the Yelp account is too long. Passwords
	 * should be 16 characters or less.
	 * 
	 * @param id
	 *            The ID of the exception
	 * @param message
	 *            Some more information about the exception
	 * @param details
	 *            Specific details about the exception
	 */
	public PasswordTooLongException(String id, String message, String details) {
		super(id, message, details);
	}

}
