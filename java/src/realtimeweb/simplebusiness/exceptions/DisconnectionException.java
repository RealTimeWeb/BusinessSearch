package realtimeweb.simplebusiness.exceptions;

/**
 * An exception thrown when a call to the online Yelp service fails, possibly
 * because of a lost Internet connection. Possibly try using the local client
 * data instead.
 * 
 * @author acbart
 * 
 */
public class DisconnectionException extends BusinessSearchException {

	private static final long serialVersionUID = -5936891507429456619L;

	/**
	 * An exception thrown when a call to the online Yelp service fails,
	 * possibly because of a lost Internet connection. Possibly try using the
	 * local client data instead.
	 * 
	 * @param id
	 *            The ID of the exception
	 * @param message
	 *            Some more information about the exception
	 * @param details
	 *            Specific details about the exception
	 */
	public DisconnectionException(String id, String message, String fields) {
		super(id, message, fields);
	}

}
