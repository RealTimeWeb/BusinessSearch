package realtimeweb.businesssearch.exceptions;

/**
 * Indicates that an input parameter value in the request is invalid. The details field
 * will include the field that was invalid.
 * 
 * @author acbart
 * 
 */
public class InvalidParameterException extends BusinessSearchException {
	private static final long serialVersionUID = -834984149371757440L;

	/**
	 * Indicates that an input parameter value in the request is invalid. The
	 * details field will include the field that was invalid.
	 * 
	 * @param id
	 *            The ID of the exception
	 * @param message
	 *            Some more information about the exception
	 * @param details
	 *            Specific details about the exception
	 */
	public InvalidParameterException(String id, String message,
			String details) {
		super(id, message, details);
	}

}
