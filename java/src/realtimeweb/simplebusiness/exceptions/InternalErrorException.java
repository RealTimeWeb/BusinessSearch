package realtimeweb.simplebusiness.exceptions;

/**
 * Indicates an internal server error has occurred. You should probably just try
 * making the call again, and hope for the best. If it happens more than twice,
 * contact the API developer!
 * 
 * @author acbart
 * 
 */
public class InternalErrorException extends BusinessSearchException {

	private static final long serialVersionUID = 2652326352396426516L;

	/**
	 * Indicates an internal server error has occurred. You should probably just
	 * try making the call again, and hope for the best. If it happens more than
	 * twice, contact the API developer!
	 * 
	 * @param id
	 *            The ID of the exception
	 * @param message
	 *            Some more information about the exception
	 * @param details
	 *            Specific details about the exception
	 */
	public InternalErrorException(String id, String message, String details) {
		super(id, message, details);
	}

}
