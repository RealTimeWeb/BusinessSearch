package realtimeweb.businesssearch.regular;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import realtimeweb.businesssearch.main.RectangularLocation;
import realtimeweb.businesssearch.util.PropertyString;
import realtimeweb.businesssearch.util.Util;

/**
 * A SearchResponse is returned by a search of businesses around a Location. It
 * has a list of businesses, a suggested region of a map to display those
 * businesses, and the total number of businesses found by the search (which
 * will often be more than the list of businesses in the SearchResponse, since
 * usually only the first 20 results are returned). In order to access these
 * extra businesses, use the
 * {@link realtimeweb.businesssearch.main.BusinessQuery#offsetResults(int)
 * offsetResults} or
 * {@link realtimeweb.businesssearch.main.BusinessQuery#paginate(int, int)
 * paginate} filters in the
 * {@link realtimeweb.businesssearch.main.BusinessQuery BusinessQuery}.
 * 
 * @author acbart
 * 
 */
public class SearchResponse {
	private List<Business> businesses;
	private RectangularLocation suggestedDisplayRegion;
	private int totalFound;

	@SuppressWarnings("unchecked")
	SearchResponse(Map<String, Object> response) {
		this.totalFound = Integer.parseInt((response.get("total").toString()));
		HashMap<String, Object> region = (HashMap<String, Object>) response
				.get("region");
		this.suggestedDisplayRegion = RectangularLocation.fromCenter(region);

		this.businesses = new ArrayList<Business>();
		Iterator<HashMap<String, Object>> i = ((ArrayList<HashMap<String, Object>>) response
				.get("businesses")).iterator();
		while (i.hasNext()) {
			HashMap<String, Object> value = i.next();
			Business b = new Business(value);
			b.setIsComplete(false);
			this.businesses.add(b);
		}
	}

	/**
	 * Returns a string representation of the businesses found and the total
	 * number of businesses found.
	 */
	public String toString() {
		ArrayList<String> businessStrings = new ArrayList<String>();
		for (Business b : businesses) {
			businessStrings.add(b.toString());
		}
		return (new PropertyString("SearchResponse"))
				.add("totalFound", "" + totalFound)
				.add("businesses", "[" + Util.join(businessStrings) + "]")
				.toString();
	}

	/**
	 * Returns a list of the businesses found.
	 * 
	 * @return the businesses
	 */
	public List<Business> getBusinesses() {
		return businesses;
	}

	/**
	 * Returns a RectangularLocation describing the suggested region to display
	 * the results of the search.
	 * 
	 * @return the suggestedDisplayRegion
	 */
	public RectangularLocation getSuggestedDisplayRegion() {
		return suggestedDisplayRegion;
	}

	/**
	 * Returns the total number of businesses found, which may be greater than
	 * the businesses returned by {@link #getBusinesses}. In order to access
	 * these extra businesses, use the
	 * {@link realtimeweb.businesssearch.main.BusinessQuery#offsetResults(int) offsetResults} or
	 * {@link realtimeweb.businesssearch.main.BusinessQuery#paginate(int, int) paginate} filters in the
	 * {@link realtimeweb.businesssearch.main.BusinessQuery BusinessQuery}.
	 * 
	 * @return the totalFound
	 */
	public int getTotalFound() {
		return totalFound;
	}
}
