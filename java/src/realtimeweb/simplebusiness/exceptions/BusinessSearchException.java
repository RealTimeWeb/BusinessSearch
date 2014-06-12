package realtimeweb.simplebusiness.exceptions;

/**
 * A generic exception thrown when there is an error with the Yelp service. The
 * ID indicates if it is a local, client side error (e.g. no internet
 * connection) or if it's a Yelp error. More information about Yelp errors can
 * be found on the <a
 * href='http://www.yelp.com/developers/documentation/v2/errors'>Yelp help
 * page</a>
 * 
 * Additional information can also sometimes be found by inspecting the "fields"
 * property.
 * 
 * @author acbart
 * 
 */
public class BusinessSearchException extends Exception {
	private static final long serialVersionUID = 1933785601010800054L;

	/** An alphanumeric string that defines the class of exception **/
	private String id;
	/** A string that has more information about the exception. **/
	private String message;
	/**
	 * A string that, if present, can give very specific and detailed
	 * information about the exception.
	 **/
	private String details;

	/**
	 * @param id
	 *            The ID of the exception
	 * @param message
	 *            Some more information about the exception
	 * @param details
	 *            Specific details about the exception
	 */
	public BusinessSearchException(String id, String message, String details) {
		super(message);
		this.id = id;
		this.message = message;
		this.details = details;
	}

	/**
	 * Returns a string representation of the exception (specifically, its ID
	 * and message, not its details).
	 */
	@Override
	public String toString() {
		return "ERROR " + this.id + ": " + this.message;
	}

	/**
	 * Gets the ID of the exception. More information about this ID may be
	 * available on <a
	 * href='http://www.yelp.com/developers/documentation/v2/errors'>the Yelp
	 * error page</a>, unless it is "local", which indicates a client error (in
	 * which case you should check the Message).
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets more information about the exception. Usually a single sentence
	 * briefly describing this type of exception.
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * Optional. Might contain more detailed information about the specific kind
	 * of exception that occurred.
	 * 
	 * @return
	 */
	public String getDetails() {
		return details;
	}

	public static BusinessSearchException generateSpecificException(String id,
			String message, String details) {
		if (id.equals("INTERNAL_ERROR")) {
			return new InternalErrorException(id, message, details);
		} else if (id.equals("EXCEEDED_REQS")) {
			return new ExceededReqsException(id, message, details);
		} else if (id.equals("MISSING_PARAMETER")) {
			return new MissingParameterException(id, message, details);
		} else if (id.equals("INVALID_PARAMETER")) {
			return new InvalidParameterException(id, message, details);
		} else if (id.equals("INVALID_SIGNATURE")) {
			return new InvalidSignatureException(id, message, details);
		} else if (id.equals("INVALID_CREDENTIALS")) {
			return new InvalidCredentialsException(id, message, details);
		} else if (id.equals("INVALID_OAUTH_CREDENTIALS")) {
			return new InvalidOAuthCredentialsException(id, message, details);
		} else if (id.equals("INVALID_OAUTH_USER")) {
			return new InvalidOAuthUserException(id, message, details);
		} else if (id.equals("ACCOUNT_UNCONFIRMED")) {
			return new AccountUnconfirmedException(id, message, details);
		} else if (id.equals("PASSWORD_TOO_LONG")) {
			return new PasswordTooLongException(id, message, details);
		} else if (id.equals("UNAVAILABLE_FOR_LOCATION")) {
			return new UnavailableForLocationException(id, message, details);
		} else if (id.equals("AREA_TOO_LARGE")) {
			return new AreaTooLargeException(id, message, details);
		} else if (id.equals("MULTIPLE_LOCATIONS")) {
			return new MultipleLocationsException(id, message, details);
		} else if (id.equals("BUSINESS_UNAVAILABLE")) {
			return new BusinessUnavailableException(id, message, details);
		} else {
			return new BusinessSearchException(id, message, details);
		}
	}
}
