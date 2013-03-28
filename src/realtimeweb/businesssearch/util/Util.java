package realtimeweb.businesssearch.util;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Contains several functions that seriously should have been added to Java
 * core.
 * 
 * @author acbart
 * 
 */
public class Util {

	/**
	 * Concatenate a list of objects into a single string (using their
	 * "toString" method), joining them with a ",".
	 * 
	 * @param objects A list of objects to concatenate.
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String join(List objects) {
		return join(",", objects);
	}

	/**
	 * Concatenate a list of objects into a single string (using their
	 * "toString" method), joining them with a specified glue string.
	 * 
	 * @param glue A string to use to join the objects.
	 * @param objects A list of objects to concatenate.
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String join(String glue, List objects) {
		StringBuilder sb = new StringBuilder();
		for (Object o : objects) {
			if (sb.length() > 0)
				sb.append(glue);
			sb.append(o.toString());
		}
		return sb.toString();
	}

	/**
	 * Change the precision of a double value, according to the new mantissa.
	 * @param d The double value you want re-precisioned.
	 * @param mantissa The number of digits after the decimal place.
	 * @return
	 */
	public static double formatDecimals(double d, int mantissa) {
		String m = "";
		for (int i = 0; i < mantissa; i++) {
			m += "#";
		}
		DecimalFormat decimalForm = new DecimalFormat("#." + m);
		return Double.valueOf(decimalForm.format(d));
	}
}
