package realtimeweb.simplebusiness.exceptions;

/**
 * Indicates that the given search location could not be disambiguated - that
 * is, there were multiple possible locations, and the Yelp service which one
 * you wanted!
 * 
 * @author acbart
 * 
 */
public class MultipleLocationsException extends BusinessSearchException {
	private static final long serialVersionUID = 8084951429433922046L;

	/**
	 * Indicates that the given search location could not be disambiguated -
	 * that is, there were multiple possible locations, and the Yelp service
	 * which one you wanted!
	 * 
	 * @param id
	 *            The ID of the exception
	 * @param message
	 *            Some more information about the exception
	 * @param details
	 *            Specific details about the exception
	 */
	public MultipleLocationsException(String id, String message, String details) {
		super(id, message, details);
	}

}
