package realtimeweb.businesssearch.regular;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import realtimeweb.businesssearch.main.GeoAccuracy;
import realtimeweb.businesssearch.main.Location;
import realtimeweb.businesssearch.util.Util;

/**
 * A location that contains highly detailed information, including both the
 * latitude/longitude coordinates and address.
 * 
 * @author acbart
 * 
 */
public class DetailedLocation implements Location {

	private double latitude;
	private double longitude;
	private ArrayList<String> address;
	private ArrayList<String> displayAddress;
	private String city;
	private String stateCode;
	private String postalCode;
	private String countryCode;
	private String crossStreets;
	private ArrayList<String> neighborhoods;
	private GeoAccuracy geoAccuracy;

	/**
	 * Create a new DetailedLocation.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param address
	 *            A list of fields describing the address, e.g.
	 *            ["1313 MockingBird Lane", "Blacksburg", "VA"]
	 * @param displayAddress
	 *            A list of detailed strings representing the postal address,
	 *            with more human-friendly information about the address.
	 * @param city
	 * @param stateCode
	 * @param postalCode
	 * @param countryCode
	 * @param crossStreets
	 * @param neighborhoods
	 * @param geoAccuracy
	 */
	DetailedLocation(double latitude, double longitude,
			ArrayList<String> address, ArrayList<String> displayAddress,
			String city, String stateCode, String postalCode,
			String countryCode, String crossStreets,
			ArrayList<String> neighborhoods, GeoAccuracy geoAccuracy) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.displayAddress = displayAddress;
		this.city = city;
		this.stateCode = stateCode;
		this.postalCode = postalCode;
		this.countryCode = countryCode;
		this.crossStreets = crossStreets;
		this.neighborhoods = neighborhoods;
		this.geoAccuracy = geoAccuracy;
	}

	@SuppressWarnings("unchecked")
	DetailedLocation(HashMap<String, Object> hashMap) {
		HashMap<String, Object> coordinates = (HashMap<String, Object>) hashMap
				.get("coordinate");
		this.latitude = Double.parseDouble((coordinates.get("latitude")
				.toString()));
		this.longitude = Double.parseDouble((coordinates.get("longitude")
				.toString()));

		this.address = (ArrayList<String>) hashMap.get("address");
		this.displayAddress = (ArrayList<String>) hashMap
				.get("display_address");

		this.city = (String) hashMap.get("city");
		this.stateCode = (String) hashMap.get("state_code");
		this.postalCode = (String) hashMap.get("postal_code");
		this.countryCode = (String) hashMap.get("country_code");
		this.crossStreets = (String) hashMap.get("cross_streets");
		this.neighborhoods = (ArrayList<String>) hashMap.get("neighborhoods");

		this.geoAccuracy = GeoAccuracy.values()[(Integer.parseInt((hashMap
				.get("geo_accuracy").toString())))];
	}

	/**
	 * Returns a string representation of this Location based on it's address
	 * field.
	 */
	public String toString() {
		return Arrays.deepToString(this.address.toArray()).toString();
	}

	/**
	 * Returns the latitude of the location of this business.
	 * 
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Returns the longitude of the geocoordinate of this location.
	 * 
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Returns the address of this location as a list of strings.
	 * 
	 * @return the address
	 */
	public ArrayList<String> getAddress() {
		return address;
	}

	/**
	 * Returns a more human-readable address of this location as a list of
	 * strings.
	 * 
	 * @return the displayAddress
	 */
	public ArrayList<String> getDisplayAddress() {
		return displayAddress;
	}

	/**
	 * Returns the city that this location is in.
	 * 
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Returns the <a href='http://en.wikipedia.org/wiki/ISO_3166-2'>two-letter
	 * State code</a> that this location is in (e.g. Virginia is "VA").
	 * 
	 * @return the stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * Returns the postal (or zip) code of this location (e.g. Blacksburg is
	 * 24060). Remember that sometimes zip codes are represented as 9-characters
	 * instead of 5.
	 * 
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Returns the <a
	 * href='http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2'>two-letter country
	 * code</a> for this location.
	 * 
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Returns the Cross streets for the business that this location describes.
	 * A cross street is the street closest to a location that intersects the
	 * locations's street.
	 * 
	 * @return the crossStreets
	 */
	public String getCrossStreets() {
		return crossStreets;
	}

	/**
	 * List that provides neighborhood(s) information for this location.
	 * 
	 * @return the neighborhoods
	 */
	public ArrayList<String> getNeighborhoods() {
		return neighborhoods;
	}

	/**
	 * Returns the {@link realtimeweb.businesssearch.main.GeoAccuracy GeoAccuracy} of this location.
	 * 
	 * @return the geoAccuracy
	 */
	public GeoAccuracy getGeoAccuracy() {
		return geoAccuracy;
	}

	/**
	 * <b>This method is for internal purposes only.</b><br><br>
	 * 
	 */
	@Override
	public Map<String, String> getFilter() {
		HashMap<String, String> filter = new HashMap<String, String>();
		String namedLocation = Util.join(this.address);
		filter.put("location", namedLocation);
		filter.put(
				"cll",
				Util.formatDecimals(this.latitude, 6) + ","
						+ Util.formatDecimals(this.longitude, 6));
		return filter;
	}
}
