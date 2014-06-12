package realtimeweb.simplebusiness.exceptions;

/**
 * An exception thrown when the data received from a Yelp API call is
 * unreadable. Check the "details" field for more information.
 * 
 * @author acbart
 * 
 */
public class DataUnreadableException extends BusinessSearchException {

	private static final long serialVersionUID = 7392527835098919927L;

	/**
	 * An exception thrown when the data received from a Yelp API call is
	 * unreadable. Check the "details" field for more information.
	 * 
	 * @param id
	 *            The ID of the exception
	 * @param message
	 *            Some more information about the exception
	 * @param details
	 *            Specific details about the exception
	 */
	public DataUnreadableException(String id, String message, String details) {
		super(id, message, details);
	}

}
