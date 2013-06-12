package realtimeweb.businesssearch.main;

import java.util.HashMap;
import java.util.Map;

import realtimeweb.businesssearch.util.Util;

/**
 * A location specified primarily by an address - usually describing a larger
 * area (such as "Blacksburg, VA"). Optionally, a latitude and longitude can be
 * specified to disambiguate the region (for example, if the address is
 * "Newark", then the latitude and longitude could be given to make it clear
 * that we're talking about the Newark in Delaware and not the one in New
 * Jersey.
 * 
 * @author acbart
 * 
 */
public class NamedLocation implements Location {
	private String address;
	private double latitudeHint;
	private double longitudeHint;
	private boolean hinting;

	/**
	 * Create a new NamedLocation based on an address. Usually the area
	 * described by the address is broad - for example, "Blacksburg, VA" would
	 * be a better choice than "1313 Mockingbird Lane, Blacksburg VA".
	 * 
	 * @param address
	 */
	public NamedLocation(String address) {
		this.setAddress(address);
		this.hinting = false;
	}

	/**
	 * Create a new NamedLocation based on an address (which usually describes a
	 * broad area), and also specifying a latitude and longitude to disambiguate
	 * the location. So, for instance, you can use:
	 * <code>NamedLocation("Newark", 39, 75)</code> to specify the Newark in
	 * Delaware, as opposed to the Newark in New Jersey.
	 * 
	 * @param address
	 * @param latitudeHint
	 * @param longitudeHint
	 */
	public NamedLocation(String address, double latitudeHint,
			double longitudeHint) {
		this.setAddress(address);
		this.setHint(latitudeHint, longitudeHint);
	}

	/**
	 * Returns the address specified for this location.
	 * 
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address for this location. The address usually describes a broad
	 * area, e.g. "Blacksburg VA".
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Removes the latitude/longitude hint for this location. Only the address
	 * will be used to specify the area.
	 */
	public void removeHint() {
		this.hinting = false;
	}

	/**
	 * Sets a latitude/longitude hint for the address, helping to disambiguate
	 * between similarly named areas. So, for instance, if the address was
	 * "Newark", specifying a latitude and longitude of (39,75) will specify the
	 * Newark in Delaware, as opposed to the Newark in New Jersey.
	 * 
	 * @param latitudeHint
	 * @param longitudeHint
	 */
	public void setHint(double latitudeHint, double longitudeHint) {
		this.latitudeHint = latitudeHint;
		this.longitudeHint = longitudeHint;
		this.hinting = true;
	}

	/**
	 * Returns the latitude that is being used to disambiguate the location.
	 * 
	 * @return
	 */
	public double getLatitudeHint() {
		return latitudeHint;
	}

	/**
	 * Returns the longitude that is being used to disambiguate the location.
	 * 
	 * @return
	 */
	public double getLongitudeHint() {
		return longitudeHint;
	}

	/**
	 * Returns whether a latitude and longitude is being used to disambiguate
	 * this address.
	 * 
	 * @return
	 */
	public boolean hasHint() {
		return hinting;
	}

	/**
	 * <b>This method is for internal use only. Please ignore it.</b><br>
	 * 
	 * Used to create a dictionary of key-value pairs for this location.
	 */
	@Override
	public Map<String, String> getFilter() {
		HashMap<String, String> filter = new HashMap<String, String>();
		filter.put("location", this.address);
		if (this.hinting) {
			filter.put("cll", Util.formatDecimals(this.latitudeHint, 6) + ","
					+ Util.formatDecimals(this.longitudeHint, 6));
		}
		return filter;
	}
}
