package realtimeweb.businesssearch.main;

import java.util.HashMap;
import java.util.Map;

import realtimeweb.businesssearch.util.Util;

/**
 * A RectangularLocation is a location specified by two latitude/longitude
 * pairs. The first pair is in the southwest (bottom-left) and the second is in
 * the northeast (top-right).
 * 
 * @author acbart
 * 
 */
public class RectangularLocation implements Location {
	private double south;
	private double west;
	private double north;
	private double east;

	/**
	 * Specifies a location via a rectangular bounding box using two
	 * latitude/longitude pairs.
	 * 
	 * @param south
	 *            The latitude-component of the lower-leftmost part of the box
	 * @param west
	 *            The longitude-component of the lower-leftmost part of the box
	 * @param north
	 *            The latitude-component of the upper-rightmost part of the box
	 * @param east
	 *            The longitude-component of the upper-rightmost part of the box
	 */
	public RectangularLocation(double south, double west, double north,
			double east) {
		this.setSouth(south);
		this.setNorth(north);
		this.setWest(west);
		this.setEast(east);
	}

	/**
	 * <b>
	 * This is an internal method. Please ignore it.</b><br><br>
	 * 
	 * A method for converting the Location to a representation that can be used
	 * to construct a query.
	 */
	@Override
	public Map<String, String> getFilter() {
		HashMap<String, String> filter = new HashMap<String, String>();
		String sw = Util.formatDecimals(this.south, 6) + ","
				+ Util.formatDecimals(this.west, 6);
		String ne = Util.formatDecimals(this.north, 6) + ","
				+ Util.formatDecimals(this.east, 6);
		filter.put("bounds", sw + "|" + ne);
		return filter;
	}

	/**
	 * Modifies this RectangularLocation so that it is centered at the given
	 * latitude and longitude, with a rectangular area given by dimensions
	 * latitudeDelta by longitudeDelta.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param latitudeDelta
	 * @param longitudeDelta
	 */
	public void center(double latitude, double longitude, double latitudeDelta,
			double longitudeDelta) {
		this.south = latitude - latitudeDelta / 2.0;
		this.west = longitude - longitudeDelta / 2.0;
		this.north = latitude + latitudeDelta / 2.0;
		this.east = longitude + longitudeDelta / 2.0;
	}

	/**
	 * Return the latitude of the center of this RectangularLocation.
	 * 
	 * @return
	 */
	public double getLatitudeCenter() {
		return (this.south + this.north) / 2.0;
	}

	/**
	 * Return the longitude of the center of this RectangularLocation.
	 * 
	 * @return
	 */
	public double getLongitudeCenter() {
		return (this.west + this.east) / 2.0;
	}

	/**
	 * Get the height of the bounding box of this RectangularLocation.
	 * 
	 * @return
	 */
	public double getLatitudeDelta() {
		return Math.abs(this.south - this.north);
	}

	/**
	 * Get the width of the bounding box of this RectangularLocation.
	 * 
	 * @return
	 */
	public double getLongitudeDelta() {
		return Math.abs(this.west - this.east);
	}
	
	/**
	 * Returns a CoordinateLocation specifying the southwest corner of this bounding box.
	 * @return
	 */
	public CoordinateLocation getSouthWest() {
		return new CoordinateLocation(getSouth(), getWest());
	}
	
	/**
	 * Returns a CoordinateLocation specifying the northeast corner of this bounding box.
	 * @return
	 */
	public CoordinateLocation getNorthEast() {
		return new CoordinateLocation(getNorth(), getEast());
	}


	/**
	 * Returns a CoordinateLocation specifying the center of this bounding box.
	 * 
	 * @return
	 */
	public CoordinateLocation getCenter() {
		return new CoordinateLocation(getLatitudeCenter(), getLongitudeCenter());
	}

	/**
	 * @return The southern side of the bounding box (the latitude of the bottom left).
	 */
	public double getSouth() {
		return south;
	}

	/**
	 * Sets the southern side of the bounding box (the latitude of the bottom left).
	 * @param south
	 */
	public void setSouth(double south) {
		this.south = south;
	}

	/**
	 * @return The western side of the bounding box (the longitude of the bottom-left).
	 */
	public double getWest() {
		return west;
	}

	/**
	 * Sets the western side of the bounding box (the longitude of the bottom-left).
	 * @param west
	 */
	public void setWest(double west) {
		this.west = west;
	}

	/**
	 * @return The northern side of the bounding box (the latitude of the top-right).
	 */
	public double getNorth() {
		return north;
	}

	/**
	 * Sets the northern side of the bounding box (the latitude of the top-right).
	 * @param north
	 */
	public void setNorth(double north) {
		this.north = north;
	}

	/**
	 * @return The eastern side of the bounding box (the longitude of the top-right).
	 */
	public double getEast() {
		return east;
	}

	/**
	 * Sets the eastern side of the bounding box (the longitude of the top-right).
	 * @param east
	 *            the east to set
	 */
	public void setEast(double east) {
		this.east = east;
	}

	/**
	 * <b>This is an internal method. Please ignore it.</b><br><br>
	 * Creates a new RectangularLocation from a dictionary of key-value pairs.
	 * 
	 * @param region
	 * @return
	 */
	public static RectangularLocation fromCenter(HashMap<String, Object> region) {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> center = (HashMap<String, Object>) region
				.get("center");
		@SuppressWarnings("unchecked")
		HashMap<String, Object> span = (HashMap<String, Object>) region
				.get("span");
		double latitude = Double.parseDouble(center.get("latitude").toString());
		double longitude = Double.parseDouble(center.get("longitude").toString());
		double latitudeDelta = Double.parseDouble(span.get("latitude_delta").toString());
		double longitudeDelta = Double.parseDouble(span.get("longitude_delta").toString());
		RectangularLocation newLocation = new RectangularLocation(0, 0, 0, 0);
		newLocation.center(latitude, longitude, latitudeDelta, longitudeDelta);
		return newLocation;
	}

}
