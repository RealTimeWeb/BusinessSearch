package realtimeweb.simplebusiness;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import realtimeweb.simplebusiness.domain.*;
import realtimeweb.simplebusiness.exceptions.BusinessSearchException;
import realtimeweb.stickyweb.EditableCache;
import realtimeweb.stickyweb.StickyWeb;
import realtimeweb.stickyweb.StickyWebRequest;
import realtimeweb.stickyweb.StickyWebResponse;
import realtimeweb.stickyweb.exceptions.StickyWebDataSourceNotFoundException;
import realtimeweb.stickyweb.exceptions.StickyWebDataSourceParseException;
import realtimeweb.stickyweb.exceptions.StickyWebInternetException;
import realtimeweb.stickyweb.exceptions.StickyWebInvalidPostArguments;
import realtimeweb.stickyweb.exceptions.StickyWebInvalidQueryString;
import realtimeweb.stickyweb.exceptions.StickyWebJsonResponseParseException;
import realtimeweb.stickyweb.exceptions.StickyWebLoadDataSourceException;
import realtimeweb.stickyweb.exceptions.StickyWebNotInCacheException;

/**
 * Get information about businesses in America.
 */
public class SimpleBusiness {
	private StickyWeb connection;
	private boolean online;

	public static void main(String[] args) {
		SimpleBusiness simpleBusiness = new SimpleBusiness();

		// The following pre-generated code demonstrates how you can
		// use StickyWeb's EditableCache to create data files.
		try {
			// First, you create a new EditableCache, possibly passing in an
			// existing cache to add to it
			EditableCache recording = new EditableCache(new FileInputStream("cache.json"));
			recording.addData(simpleBusiness.searchRequest("chinese food", "kansas city"));
			recording.addData(simpleBusiness.searchRequest("chinese food", "newark, de"));
			System.out.println(recording.getKeys());
			
			/*
			 * // First you get a request object
		     * StickyWebRequest request = SimpleBusiness.searchRequest(...); 
		     * // Then you can get the request's hash and value, and add it to the EditableCache
			 * recording.addData(request.getHashedRequest(), request.execute().asText());
			 */
			// Then you can save the expanded cache over the original
			recording.saveToStream(new FileOutputStream("cache.json"));
		} catch (StickyWebDataSourceNotFoundException e) {
			System.err
					.println("The given FileStream was not able to be found.");
		} catch (StickyWebDataSourceParseException e) {
			System.err
					.println("The given FileStream could not be parsed; possibly the structure is incorrect.");
		} catch (StickyWebLoadDataSourceException e) {
			System.err.println("The given data source could not be loaded.");
		} catch (FileNotFoundException e) {
			System.err
					.println("The given cache.json file was not found, or could not be opened.");
		} catch (StickyWebNotInCacheException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StickyWebInternetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StickyWebInvalidQueryString e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StickyWebInvalidPostArguments e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create a new, online connection to the service
	 */
	public SimpleBusiness() {
		this("em86viPSqwmfF2PFfNsPEQ", "K7Dq24NKDMNNk-sz_-JMlAvDmSU",
				"hbML2QjyBfh-fvw5PsiF71pVLt2m3AbZ",
				"ggqII8lp1foy0ttolsYrTIUAm7c");
	}

	public SimpleBusiness(String consumerKey, String consumerSecret,
			String token, String tokenSecret) {
		this.online = true;
		try {
			this.connection = new StickyWeb(null).setAuthentication(
					consumerKey, consumerSecret, token, tokenSecret);
		} catch (StickyWebDataSourceNotFoundException e) {
			System.err.println("The given datastream could not be loaded.");
		} catch (StickyWebDataSourceParseException e) {
			System.err.println("The given datastream could not be parsed");
		} catch (StickyWebLoadDataSourceException e) {
			System.err.println("The given data source could not be loaded");
		}
	}

	/**
	 * Create a new, offline connection to the service.
	 * 
	 * @param cache
	 *            The filename for this cache.
	 */
	public SimpleBusiness(String cache) {
		try {
			this.online = false;
			this.connection = new StickyWeb(new FileInputStream(cache));
		} catch (StickyWebDataSourceNotFoundException e) {
			System.err.println("The given data source could not be found.");
			System.exit(1);
		} catch (StickyWebDataSourceParseException e) {
			System.err
					.println("Could not read the data source. Perhaps its format is incorrect?");
			System.exit(1);
		} catch (StickyWebLoadDataSourceException e) {
			System.err.println("The given data source could not be read.");
			System.exit(1);
		} catch (FileNotFoundException e) {
			System.err.println("The given cache file could not be found. Make sure it is in the right folder.");
			System.exit(1);
		}
	}

	private StickyWebRequest searchRequest(String term, String location) {
		try {
			final String url = String.format("http://api.yelp.com/v2/search");
			HashMap<String, String> parameters = new HashMap<String, String>();
			// TODO: Validate the inputs here
			parameters.put("term", String.valueOf(term.toLowerCase()));
			parameters.put("location", String.valueOf(location.toLowerCase()));
			ArrayList<String> indexList = new ArrayList<String>();
			indexList.add("term");
			indexList.add("location");

			return connection.get(url, parameters).setOnline(online)
					.setIndexes(indexList);
		} catch (StickyWebDataSourceNotFoundException e) {
			System.err.println("Could not find the data source.");
		}
		return null;
	}

	/**
	 * Retrieves information about the businesses that include the given term
	 * for the given area
	 * 
	 * @param cache
	 *            Search term (e.g. "food", "restaurants").
	 * @param cache
	 *            Specifies the combination of
	 *            "address, neighborhood, city, state or zip, optional country"
	 *            to be used when searching for businesses.
	 * @return a business[]
	 */
	public ArrayList<Business> search(String term, String location) {
		StickyWebResponse response = null;
		try {
			term = term.toLowerCase();
			location = location.toLowerCase();
			StickyWebRequest request = searchRequest(term, location);

			ArrayList<Business> result = new ArrayList<Business>();
			response = request.execute();
			// TODO: Validate the output here using response.isNull,
			// response.asText, etc.
			if (response.isNull())
				return result;
			if (response.asText().equals("")) {
				System.err.println("No response recieved from the internet. Your internet connection might be down.");
				System.exit(1);
			}
			Map<String, Object> jsonResponse = response.asJSON();
			if (jsonResponse.containsKey("error")) {
				Map<String, String> errorDetails = (Map<String, String>) jsonResponse
						.get("error");
				System.err.println(errorDetails.get("id") + ":"
						+ errorDetails.get("text"));
			}
			Iterator<Object> resultIter = ((ArrayList<Object>) jsonResponse
					.get("businesses")).iterator();
			while (resultIter.hasNext()) {
				result.add(new Business((Map<String, Object>) resultIter.next()));
			}
			return result;
		} catch (StickyWebNotInCacheException e) {
			System.err
					.println("There is no query in the cache for the given inputs. Perhaps something was mispelled?");
		} catch (StickyWebInternetException e) {
			System.err
					.println("Could not connect to the web service. It might be your internet connection, or a problem with the web service.");
		} catch (StickyWebInvalidQueryString e) {
			System.err
					.println("The given arguments were invalid, and could not be turned into a query.");
		} catch (StickyWebInvalidPostArguments e) {
			System.err
					.println("The given arguments were invalid, and could not be turned into a query.");
		} catch (StickyWebJsonResponseParseException e) {
			System.err
					.println("The response from the server couldn't be understood.");
			System.err.println("Recieved: " + response.asText());
		}
		return null;
	}

}