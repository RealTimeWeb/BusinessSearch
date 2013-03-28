package realtimeweb.businesssearch.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;

import realtimeweb.businesssearch.util.JsonConverter;
import realtimeweb.businesssearch.util.Util;

/**
 * A business category is used to organize and filter related businesses.
 * Business categories are arranged in a hierachical fashion, with some
 * categories having subcategories (and sometimes those subcategories have
 * subsubcategories of their own!). A business can be in more than one category
 * at a time.
 * 
 * The static method <i>getCategories</i> is provided to get a complete listing
 * of the entire Business Category hierarchy.
 * 
 * @author acbart
 * 
 */
public class BusinessCategory {
	private String title;

	private String alias;
	private List<String> languages;
	private List<BusinessCategory> subcategories;

	private static Map<String, BusinessCategory> aliasLookup;
	private static List<BusinessCategory> rootCategory;

	/**
	 * Returns a list of all the Business Categories, which is hierarchical
	 * (categories can have subcategories, which in turn can have
	 * subsubcategories, and so on).
	 * 
	 * @return
	 */
	public static List<BusinessCategory> getCategories() {
		if (rootCategory == null) {
			synchronized (BusinessCategory.class) {
				if (rootCategory == null) {
					rootCategory = BusinessCategory.loadCategories();
				}
			}
		}
		return rootCategory;
	}

	/**
	 * Opens up the categories.json file, a file in the jar that stores all the
	 * categories and their associated metadata, and returns it as a string. All
	 * data is arranged hierarchically.
	 * 
	 * Yes. It is simply reading in a file and returning it as a string.
	 * 
	 * @return
	 */
	private static String readCategoryFile() {
		BufferedReader clientData = new BufferedReader(new InputStreamReader(
				BusinessCategory.class
						.getResourceAsStream("../categories.json")));
		StringBuffer fileContents = new StringBuffer();
		try {
			String line;
			while ((line = clientData.readLine()) != null) {
				fileContents.append(line);
			}
			clientData.close();
		} catch (IOException e) {
			System.err
					.println("Couldn't find the Categories file. Your Jar might be corrupt!");
			e.printStackTrace();
			System.exit(1);
		}
		return fileContents.toString();
	}

	/**
	 * Converts each element of a list of Map-structured business categories
	 * into a proper list of BusinessCategory objects.
	 * 
	 * @param categories
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static List<BusinessCategory> convertListOfRawCategories(
			List<Object> categories) {
		Iterator<Object> bcsIterator = categories.iterator();
		ArrayList<BusinessCategory> bcs = new ArrayList<BusinessCategory>();
		while (bcsIterator.hasNext()) {
			BusinessCategory bc = new BusinessCategory(
					(HashMap) bcsIterator.next());
			bcs.add(bc);
		}
		return bcs;
	}

	/**
	 * Internal method to build a lookup table for categories, based on their
	 * alias. It's just a map between an alias and it's instance.
	 * 
	 * @param categories
	 */
	private static void buildAliasLookupMap(List<BusinessCategory> categories) {
		for (BusinessCategory bc : categories) {
			aliasLookup.put(bc.getAlias(), bc);
			buildAliasLookupMap(bc.subcategories);
		}
	}

	/**
	 * An internal method to load the category information from the
	 * "categories.json" file and parse it.
	 * 
	 * @return
	 */
	private static List<BusinessCategory> loadCategories() {
		String file = readCategoryFile();
		try {
			ArrayList<Object> rawCategories = new ArrayList<Object>(
					JsonConverter.convertToList(file));
			List<BusinessCategory> categories = convertListOfRawCategories(rawCategories);
			aliasLookup = new HashMap<String, BusinessCategory>();
			buildAliasLookupMap(categories);
			return categories;
		} catch (ParseException e) {
			System.out
					.println("Could't parse the Categories file. Your Jar might be corrupt!");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Internal Copy Constructor
	 * 
	 * @param bc
	 */
	private BusinessCategory(BusinessCategory bc) {
		this.title = bc.title;
		this.alias = bc.alias;
		this.subcategories = bc.getSubcategories();
		this.languages = bc.languages;
	}

	/**
	 * Internal constructor to create a new BusinessCategory from a title and
	 * alias. This should only be used for making dummy BusinessCategories,
	 * since it clearly doesn't fit into the official hierarchy.
	 * 
	 * @param title
	 * @param alias
	 */
	private BusinessCategory(String title, String alias) {
		this.title = title;
		this.alias = alias;
		this.subcategories = new ArrayList<BusinessCategory>();
		this.languages = new ArrayList<String>();
	}

	/**
	 * Internal constructor to convert a Map representing a BusinessCategory
	 * into a proper BusinessCategory instance.
	 * 
	 * @param structuredBusinessCategory
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private BusinessCategory(Map structuredBusinessCategory) {
		this.title = (String) structuredBusinessCategory.get("title");
		this.alias = (String) structuredBusinessCategory.get("alias");
		this.languages = ((List) structuredBusinessCategory.get("languages"));
		this.subcategories = convertListOfRawCategories((ArrayList) structuredBusinessCategory
				.get("subcategories"));
	}

	/**
	 * Static method to get the official BusinessCategory based on an alias
	 * string. If there is no official data found, then a new BusinessCategory
	 * is created (although it will not be added to the official hierarchy).
	 * 
	 * @param alias
	 *            A concise string representing the BusinessCategory.
	 * @param title
	 *            A longer, human-readable string representing the
	 *            BusinessCategory.
	 * @return
	 */
	public static BusinessCategory getFromAlias(String alias, String title) {
		if (aliasLookup.containsKey(alias)) {
			return aliasLookup.get(alias);
		} else {
			return new BusinessCategory(title, alias);
		}
	}

	/**
	 * Returns the alias of this BusinessCategory, a concise string uniquely
	 * identifying it.
	 * 
	 * @return
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Returns a list of all the subcategories of this BusinessCategory.
	 * 
	 * @return
	 */
	public List<BusinessCategory> getSubcategories() {
		ArrayList<BusinessCategory> bcSubcategoriesCopy = new ArrayList<BusinessCategory>();
		for (BusinessCategory bc : this.subcategories) {
			bcSubcategoriesCopy.add(new BusinessCategory(bc));
		}
		return bcSubcategoriesCopy;
	}

	/**
	 * Returns the Title of this BusinessCategory, which is a human-readable
	 * string.
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns a representation of this BusinessCategory from its alias. Any
	 * subcategories it has will also be included.
	 */
	public String toString() {
		if (this.subcategories.isEmpty()) {
			return this.alias;
		} else {
			return this.alias + " [" + Util.join(subcategories) + "]";
		}
	}

	/**
	 * For internal use only. Returns the BusinessCategory associated with the
	 * title and alias stored in the Tuple passed in.
	 * 
	 * @param titleAliasPair
	 * @return
	 */
	public static BusinessCategory getFromAlias(ArrayList<String> titleAliasPair) {
		return getFromAlias((String) titleAliasPair.get(1),
				(String) titleAliasPair.get(0));
	}

}
