package realtimeweb.simplebusiness.exceptions;

/**
 * Indicates that an input parameter is missing from the request. The details
 * field will include the field that was missing.
 * 
 * @author acbart
 * 
 */
public class MissingParameterException extends BusinessSearchException {
	private static final long serialVersionUID = 2449871212769999588L;

	/**
	 * Indicates that an input parameter is missing from the request. The details
	 * field will include the field that was missing.
	 * 
	 * @param id
	 *            The ID of the exception
	 * @param message
	 *            Some more information about the exception
	 * @param details
	 *            Specific details about the exception
	 */
	public MissingParameterException(String id, String message,
			String details) {
		super(id, message, details);
	}

}
