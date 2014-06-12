package realtimeweb.simplebusiness.exceptions;

/**
 * Indicates that the Yelp account has not yet been activated.
 * 
 * @author acbart
 * 
 */
public class AccountUnconfirmedException extends BusinessSearchException {
	private static final long serialVersionUID = 5121649280322572611L;

	/**
	 * Indicates that the Yelp account has not yet been activated.
	 * 
	 * @param id
	 *            The ID of the exception
	 * @param message
	 *            Some more information about the exception
	 * @param details
	 *            Specific details about the exception
	 */
	public AccountUnconfirmedException(String id, String message, String details) {
		super(id, message, details);
	}

}
