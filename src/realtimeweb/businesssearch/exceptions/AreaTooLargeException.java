package realtimeweb.businesssearch.exceptions;

/**
 * Indicates that the geographical area is too large for this search request.
 * Max search area is 2,500 square miles.
 * 
 * @author acbart
 * 
 */
public class AreaTooLargeException extends BusinessSearchException {
	private static final long serialVersionUID = 2107531978823262208L;

	/**
	 * Indicates that the geographical area is too large for this search
	 * request. Max search area is 2,500 square miles.
	 * 
	 * @param id
	 *            The ID of the exception
	 * @param message
	 *            Some more information about the exception
	 * @param details
	 *            Specific details about the exception
	 */
	public AreaTooLargeException(String id, String message, String details) {
		super(id, message, details);
	}

}
