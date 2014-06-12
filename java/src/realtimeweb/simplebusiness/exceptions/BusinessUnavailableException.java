package realtimeweb.simplebusiness.exceptions;

/**
 * Indicates that there may not be able to provide details for certain
 * businesses, for example if they don't have any reviews yet.
 * 
 * @author acbart
 * 
 */
public class BusinessUnavailableException extends BusinessSearchException {
	private static final long serialVersionUID = -7764097544743937114L;

	/**
	 * Indicates that there may not be able to provide details for certain
	 * businesses, for example if they don't have any reviews yet.
	 * 
	 * @param id
	 *            The ID of the exception
	 * @param message
	 *            Some more information about the exception
	 * @param details
	 *            Specific details about the exception
	 */
	public BusinessUnavailableException(String id, String message,
			String details) {
		super(id, message, details);
	}

}
