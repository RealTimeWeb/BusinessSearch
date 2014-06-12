package realtimeweb.simplebusiness.exceptions;

/**
 * Indicates that the oauth_signature for the request is invalid. If your Yelp
 * API credentials (token, secret, etc.) are correct, check that the clock on
 * your computer is correct.
 * 
 * @author acbart
 * 
 */
public class InvalidSignatureException extends BusinessSearchException {
	private static final long serialVersionUID = -1220028526936518170L;

	/**
	 * Indicates that the oauth_signature for the request is invalid. If your
	 * Yelp API credentials (token, secret, etc.) are correct, check that the
	 * clock on your computer is correct.
	 * 
	 * @param id
	 *            The ID of the exception
	 * @param message
	 *            Some more information about the exception
	 * @param details
	 *            Specific details about the exception
	 */
	public InvalidSignatureException(String id, String message,
			String details) {
		super(id, message, details);
	}

}
