package realtimeweb.businesssearch.main;

/**
 * A GeoAccuracy specifies at what level a lat/long pair was found. There are
 * several possibilities.
 * 
 * <ol>
 * <li>Unknown location.</li>
 * <li>Country level accuracy.</li>
 * <li>Region (state, province, prefecture, etc.) level accuracy.</li>
 * <li>Sub-region (county, municipality, etc.) level accuracy.</li>
 * <li>Town (city, village) level accuracy.</li>
 * <li>Post code (zip code) level accuracy.</li>
 * <li>Street level accuracy.</li>
 * <li>Intersection level accuracy.</li>
 * <li>Address level accuracy.</li>
 * <li>Premise (building name, property name, shopping center, etc.) level
 * accuracy.</li>
 * </ol>
 * 
 * @author acbart
 * 
 */
public enum GeoAccuracy {
	UNKNOWN_LOCATION, COUNTRY_LEVEL, REGION_LEVEL, SUBREGION_LEVEL, TOWN_LEVEL, POST_CODE_LEVEL, STREET_LEVEL, INTERSECTION_LEVEL, ADDRESS_LEVEL, PREMISE_LEVEL;

	/**
	 * Returns a string representation of the geo-accuracy.
	 */
	public String toString() {
		switch (this) {
		default:
		case UNKNOWN_LOCATION:
			return "Unknown Location";
		case COUNTRY_LEVEL:
			return "Country Level";
		case REGION_LEVEL:
			return "Region Level";
		case SUBREGION_LEVEL:
			return "Subregion Level";
		case TOWN_LEVEL:
			return "Town Level";
		case POST_CODE_LEVEL:
			return "Post code Level";
		case STREET_LEVEL:
			return "Street Level";
		case INTERSECTION_LEVEL:
			return "Intersection Level";
		case ADDRESS_LEVEL:
			return "Address Level";
		case PREMISE_LEVEL:
			return "Premise Level";
		}
	}

	/**
	 * Returns a longer, human-readable explanation of the geo-accuracy.
	 */
	public String getExplanation() {
		switch (this) {
		default:
		case UNKNOWN_LOCATION:
			return "Unknown location.";
		case COUNTRY_LEVEL:
			return "Country level accuracy.";
		case REGION_LEVEL:
			return "Region (state, province, prefecture, etc.) level accuracy.";
		case SUBREGION_LEVEL:
			return "Sub-region (county, municipality, etc.) level accuracy.";
		case TOWN_LEVEL:
			return "Town (city, village) level accuracy.";
		case POST_CODE_LEVEL:
			return "Post code (zip code) level accuracy.";
		case STREET_LEVEL:
			return "Street level accuracy.";
		case INTERSECTION_LEVEL:
			return "Intersection level accuracy.";
		case ADDRESS_LEVEL:
			return "Address level accuracy.";
		case PREMISE_LEVEL:
			return "Premise (building name, property name, shopping center, etc.) level accuracy.";
		}
	}
}
