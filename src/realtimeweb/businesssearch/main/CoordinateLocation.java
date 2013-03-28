package realtimeweb.businesssearch.main;

import java.util.HashMap;
import java.util.Map;

import realtimeweb.businesssearch.util.Util;

/**
 * A Location that is given by a latitude and longitude. You can also specify an
 * accuracy for the location, and an altitude (and the accuracy of that
 * altitude).
 * 
 * @author acbart
 * 
 */
public class CoordinateLocation implements Location {

	private double latitude;
	private double longitude;
	private double accuracy;
	private double altitude;
	private double altitudeAccuracy;

	private boolean hasAccuracy;
	private boolean hasAltitude;
	private boolean hasAltitudeAccuracy;

	/**
	 * Returns whether an accuracy has been specified for the latitude and
	 * longitude.
	 * 
	 * @return
	 */
	public boolean hasAccuracy() {
		return hasAccuracy;
	}

	/**
	 * Returns whether an altitude has been specified for this location.
	 * 
	 * @return
	 */
	public boolean hasAltitude() {
		return hasAltitude;
	}

	/**
	 * Returns whether the accuracy of the altitude has been specified for this
	 * location.
	 * 
	 * @return
	 */
	public boolean hasAltitudeAccuracy() {
		return hasAltitudeAccuracy;
	}

	/**
	 * Create a new CoordinateLocation based on a latitude and a longitude.
	 * 
	 * @param latitude
	 * @param longitude
	 */
	public CoordinateLocation(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.hasAccuracy = false;
		this.hasAltitude = false;
		this.hasAltitudeAccuracy = false;
	}

	/**
	 * Create a new CoordinateLocation based on a latitude longitude, and with a
	 * given accuracy.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param accuracy
	 */
	public CoordinateLocation(double latitude, double longitude, double accuracy) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.accuracy = accuracy;
		this.hasAltitude = false;
		this.hasAltitudeAccuracy = false;
	}

	/**
	 * Create a new CoordinateLocation based on a latitude, longitude, and
	 * altitude. The longitude and latitude will be given an accuracy.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param accuracy
	 * @param altitude
	 */
	public CoordinateLocation(double latitude, double longitude,
			double accuracy, double altitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.accuracy = accuracy;
		this.altitude = altitude;
		this.hasAltitudeAccuracy = false;
	}

	/**
	 * Create a new CoordinateLocation based on a latitude, longitude, and
	 * altitude. The longitude and latitude will be given an accuracy, as will
	 * the altitude.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param accuracy
	 * @param altitude
	 * @param altitudeAccuracy
	 */
	public CoordinateLocation(double latitude, double longitude,
			double accuracy, double altitude, double altitudeAccuracy) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.accuracy = accuracy;
		this.altitude = altitude;
		this.altitudeAccuracy = altitudeAccuracy;
	}

	/**
	 * Returns the latitude of this CoordinateLocation.
	 * 
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Sets the latitude of this CoordinateLocation.
	 * 
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Sets the longitude of this CoordinateLocation.
	 * 
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Sets the latitude of this CoordinateLocation.
	 * 
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Gets the accuracy of the latitude and longitude of this
	 * CoordinateLocation.
	 * 
	 * @return the accuracy
	 */
	public double getAccuracy() {
		return accuracy;
	}

	/**
	 * Removes the accuracy of the location.
	 */
	public void removeAccuracy() {
		this.hasAccuracy = false;
	}

	/**
	 * Sets the accuracy of the latitude and longitude of this location.
	 * 
	 * @param accuracy
	 *            the accuracy to set
	 */
	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
		this.hasAccuracy = true;
	}

	/**
	 * Gets the altitude of this location.
	 * 
	 * @return the altitude
	 */
	public double getAltitude() {
		return altitude;
	}

	/**
	 * Sets the altitude of this location.
	 * 
	 * @param altitude
	 *            the altitude to set
	 */
	public void setAltitude(double altitude) {
		this.altitude = altitude;
		this.hasAltitude = true;
	}

	/**
	 * Removes the altitude of this location.
	 */
	public void removeAltitude() {
		this.hasAltitude = false;
		this.hasAltitudeAccuracy = false;
	}

	/**
	 * Sets the altitude of this location, along with the accuracy of that
	 * altitude.
	 * 
	 * @param altitude
	 *            the altitude to set
	 */
	public void setAltitude(double altitude, double altitudeAccuracy) {
		this.altitude = altitude;
		this.altitudeAccuracy = altitudeAccuracy;
		this.hasAltitude = true;
		this.hasAltitudeAccuracy = true;
	}

	/**
	 * Gets the accuracy of the altitude for this location.
	 * 
	 * @return the altitudeAccuracy
	 */
	public double getAltitudeAccuracy() {
		return altitudeAccuracy;
	}

	/**
	 * Removes the accuracy associated with this location.
	 */
	public void removeAltitudeAccuracy() {
		this.hasAltitudeAccuracy = false;
	}

	/**
	 * <b>This is an internal method, you should not use it.</b><br>
	 * 
	 * Returns a dictionary representing this Location that is suitable for
	 * using as a filter.
	 */
	@Override
	public Map<String, String> getFilter() {
		HashMap<String, String> filters = new HashMap<String, String>();
		String locationString = Util.formatDecimals(latitude, 6) + ","
				+ Util.formatDecimals(longitude, 6);
		if (this.hasAccuracy) {
			locationString += "," + Util.formatDecimals(this.accuracy, 6);
		}
		if (this.hasAltitude) {
			locationString += "," + Util.formatDecimals(this.altitude, 6);
		}
		if (this.hasAltitudeAccuracy) {
			locationString += ","
					+ Util.formatDecimals(this.altitudeAccuracy, 6);
		}
		filters.put("ll", locationString);
		return filters;
	}
}
