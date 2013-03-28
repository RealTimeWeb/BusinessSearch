package realtimeweb.businesssearch.main;

import java.util.Map;

/**
 * An interface representing a Location. The different locations possible are
 * listed below, from least specific to most.
 * 
 * <ul>
 * <li>{@link NamedLocation} - Represented by a single string (e.g.
 * "Blacksburg, VA")</li>
 * <li>{@link CoordinateLocation} - Represented by a single lat/long (e.g. 42,
 * 45)</li>
 * <li>{@link RectangularLocation} - Represented by a pair of lat/longs (e.g.
 * 42, 45 to 33, 37)</li>
 * <li>{@link realtimeweb.businesssearch.regular.DetailedLocation
 * DetailedLocation} - Represented by a coordinate and a postal address</li>
 * </ul>
 * 
 * @author acbart
 * 
 */
public interface Location {

	/**
	 * <b> This is an internal method. Please ignore it.</b><br><br>
	 * 
	 * A method for converting the Location to a representation that can be used
	 * to construct a query.
	 * 
	 * @return A mapping between Yelp arguments and the values of the location.
	 */
	Map<String, String> getFilter();

}
