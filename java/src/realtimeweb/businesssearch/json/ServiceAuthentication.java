package realtimeweb.businesssearch.json;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

/**
 * <b>This class is for internal purposes only. Please ignore it.</b>
 * @author acbart
 *
 */
public class ServiceAuthentication extends DefaultApi10a {
	/**
	 * Service provider for "2-legged" OAuth10a for Yelp API (version 2).
	 */

	@Override
	public String getAccessTokenEndpoint() {
		return null;
	}

	@Override
	public String getAuthorizationUrl(Token arg0) {
		return null;
	}

	@Override
	public String getRequestTokenEndpoint() {
		return null;
	}

}