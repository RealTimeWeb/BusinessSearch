package realtimeweb.businesssearch.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * An internal class that provides a convenient converter for JSON formatted
 * data. This class will convert JSON maps to HashMaps and JSON lists to
 * ArrayLists.
 * 
 * @author acbart
 * 
 */
public class JsonConverter {
	private static ContainerFactory CONTAINER_FACTORY = new ContainerFactory() {
		@SuppressWarnings("rawtypes")
		public List creatArrayContainer() {
			return new ArrayList();
		}

		@SuppressWarnings("rawtypes")
		public Map createObjectContainer() {
			return new HashMap();
		}

	};

	/**
	 * Given a string of JSON data, convert it to a Map.
	 * @param input
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> convertToMap(String input)
			throws ParseException {
		return (Map<String, Object>) (new JSONParser()).parse(input,
				CONTAINER_FACTORY);
	}

	/**
	 * Given a string of JSON data, convert it to a List.
	 * @param input
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> convertToList(String input)
			throws ParseException {
		return (List<Object>) (new JSONParser()).parse(input,
				CONTAINER_FACTORY);
	}
}
