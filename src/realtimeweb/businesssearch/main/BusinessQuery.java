package realtimeweb.businesssearch.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * A query object used to make a search for businesses. A BusinessQuery is
 * built-up by chaining together filters.<br>
 * For example:<br>
 * <br>
 * <code>BusinessQuery myQuery = BusinessQuery("Blacksburg, VA");<br>
 * myQuery.limitRadius(100).filterCategories("restaurants").sortBy(SortMode.DISTANCE);</code>
 * <br>
 * <br>
 * ... will create a query that will search for restaurants within 100 meters of
 * Blacksburg, and sort the results by Distance.
 * 
 * If you wish to remove a filter after it's been added, you can do so easily: <br>
 * <br>
 * <code>myQuery.removeRadiusLimit().removeCategoriesFilter();</code><br>
 * <br>
 * ... will change the query to search all around Blacksburg for any businesses.
 * and just sort the results.
 * 
 * @author acbart
 * 
 */
public class BusinessQuery {

	private int radius;
	private boolean hasRadius;
	private String term;
	private BusinessCategory[] categories;
	private boolean deals;
	private SortMode sortMode;
	private int limit;
	private int offset;
	private Location location;
	private boolean hasTerm;
	private boolean hasCategories;
	private boolean hasDeals;
	private boolean hasSortMode;
	private boolean hasLimit;
	private boolean hasOffset;

	/**
	 * Returns whether there is a Term Filter.
	 * 
	 * @return
	 */
	public boolean hasTermFilter() {
		return hasTerm;
	}

	/**
	 * Returns whether there is a Categories Filter.
	 * 
	 * @return
	 */
	public boolean hasCategoriesFilter() {
		return hasCategories;
	}

	/**
	 * Returns whether there is a Deals filter
	 * 
	 * @return
	 */
	public boolean hasDealsFilter() {
		return hasDeals;
	}

	/**
	 * Returns whether the results will be sorted.
	 * 
	 * @return
	 */
	public boolean hasSortMode() {
		return hasSortMode;
	}

	/**
	 * Returns whether the results will be limited.
	 * 
	 * @return
	 */
	public boolean hasLimit() {
		return hasLimit;
	}

	/**
	 * Returns whether the results will be offset.
	 * 
	 * @return
	 */
	public boolean hasOffset() {
		return hasOffset;
	}

	/**
	 * Create a new BusinessQuery that will search within the bounds of the
	 * Location.
	 * 
	 * @param location
	 *            The location that will bound the search.
	 */
	public BusinessQuery(Location location) {
		this.location = location;
	}

	/**
	 * Create a new BusinessQuery that will search around the location. The
	 * Location should be a string representing a named area, e.g.
	 * "Blacksburg, VA 24060".
	 * 
	 * @param location
	 */
	public BusinessQuery(String location) {
		this(new NamedLocation(location));
	}

	/**
	 * Limit the radius of this query. Results will only be returned if they are
	 * within <b>radius</b> meters of the given location. If the value is too
	 * large, an
	 * {@link realtimeweb.businesssearch.exceptions.AreaTooLargeException#AreaTooLargeException
	 * AreaTooLargeException} error may be returned. The max value is 40000
	 * meters (25 miles).
	 * 
	 * @param radius
	 *            The distance in meters
	 * @return This BusinessQuery instance, allowing you to chain together other
	 *         filters.
	 */
	public BusinessQuery limitRadius(int radius) {
		this.radius = radius;
		this.hasRadius = false;
		return this;
	}

	/**
	 * Removes the radius limit filter, so results will be returned regardless
	 * of their distance from the location.
	 * 
	 * @return This BusinessQuery instance, allowing you to chain together other
	 *         filters.
	 */
	public BusinessQuery removeRadiusLimit() {
		this.hasRadius = false;
		return this;
	}

	/**
	 * Sets the search term (e.g. "food", "restaurants"), so only results
	 * involving this term will be returned.
	 * 
	 * @param term
	 * @return This BusinessQuery instance, allowing you to chain together other
	 *         filters.
	 */
	public BusinessQuery filterTerm(String term) {
		this.term = term;
		this.hasTerm = true;
		return this;
	}

	/**
	 * Removes the term filter, so any results can be returned.
	 * 
	 * @return This BusinessQuery instance, allowing you to chain together other
	 *         filters.
	 */
	public BusinessQuery removeTermFilter() {
		this.hasTerm = false;
		return this;
	}

	/**
	 * Filters the results by categories, returning only results that fall into
	 * the given categories. BusinessCategories can be found via the
	 * {@link BusinessCategory#getCategories getCategories} method. An arbitrary
	 * number of categories can be included. You can also search via the
	 * category's alias by passing in Strings, but you cannot mix
	 * BusinessCategories and Strings.
	 * 
	 * @param categories
	 * @return This BusinessQuery instance, allowing you to chain together other
	 *         filters.
	 */
	public BusinessQuery filterCategories(BusinessCategory... categories) {
		this.categories = categories.clone();
		this.hasCategories = true;
		return this;
	}

	/**
	 * Filters the results by categories given by their alias (e.g. General
	 * Dentistry would be "generaldentistry") - so only results that businesses
	 * that are in the category will be returned. An arbitrary number of aliases
	 * can be given. A complete list of all the categories and their aliases is
	 * at <a
	 * href="http://www.yelp.com/developers/documentation/all_category_list"
	 * >http://www.yelp.com/developers/documentation/all_category_list</a>.
	 * 
	 * @param categories
	 * @return This BusinessQuery instance, allowing you to chain together other
	 *         filters.
	 */
	public BusinessQuery filterCategories(String... categories) {
		this.categories = new BusinessCategory[categories.length];
		for (int i = 0; i < categories.length; i++) {
			this.categories[i] = BusinessCategory.getFromAlias(categories[i],
					"");
		}
		this.hasCategories = true;
		return this;
	}

	/**
	 * Remove any categories that are being filtered.
	 * 
	 * @return This BusinessQuery instance, allowing you to chain together other
	 *         filters.
	 */
	public BusinessQuery removeCategoriesFilter() {
		this.hasCategories = false;
		return this;
	}

	/**
	 * Remove a list of categories from the filter.
	 * 
	 * @param categories
	 * @return This BusinessQuery instance, allowing you to chain together other
	 *         filters.
	 */
	public BusinessQuery removeCategoriesFilter(BusinessCategory... categories) {
		List<BusinessCategory> removingCategories = Arrays.asList(categories);
		List<BusinessCategory> entireCategories = Arrays
				.asList(this.categories);
		entireCategories.removeAll(removingCategories);
		this.categories = entireCategories
				.toArray(new BusinessCategory[entireCategories.size()]);
		if (entireCategories.isEmpty()) {
			this.hasCategories = false;
		}
		return this;
	}

	/**
	 * Remove a list of categories from the filter, given as alias strings.
	 * 
	 * @param categories
	 * @return This BusinessQuery instance, allowing you to chain together other
	 *         filters.
	 */
	public BusinessQuery removeCategoriesFilter(String... categories) {
		List<BusinessCategory> removingCategories = new ArrayList<BusinessCategory>();
		for (int i = 0; i < categories.length; i++) {
			removingCategories.add(BusinessCategory.getFromAlias(categories[i],
					""));
		}
		List<BusinessCategory> entireCategories = Arrays
				.asList(this.categories);
		entireCategories.removeAll(removingCategories);
		this.categories = entireCategories
				.toArray(new BusinessCategory[entireCategories.size()]);
		if (entireCategories.isEmpty()) {
			this.hasCategories = false;
		}
		return this;
	}

	/**
	 * Filter the results to only include businesses that offer a deal.
	 * 
	 * @return This BusinessQuery instance, allowing you to chain together other
	 *         filters.
	 */
	public BusinessQuery filterDeals() {
		this.deals = true;
		this.hasDeals = true;
		return this;
	}

	/**
	 * If requireDeal is true, only return businesses that offer a deal.
	 * 
	 * @param requireDeal
	 * @return This BusinessQuery instance, allowing you to chain together other
	 *         filters.
	 */
	public BusinessQuery filterDeals(boolean requireDeal) {
		this.deals = requireDeal;
		this.hasDeals = true;
		return this;
	}

	/**
	 * Remove the current deals filter, returning all businesses even if they
	 * don't offer a deal.
	 * 
	 * @return This BusinessQuery instance, allowing you to chain together other
	 *         filters.
	 */
	public BusinessQuery removeDealsFilter() {
		this.hasDeals = false;
		return this;
	}

	/**
	 * Change the way that results are sorted. For sorting options, see
	 * {@link SortMode#SortMode SortMode}.
	 * 
	 * @param sortMode
	 * @return This BusinessQuery instance, allowing you to chain together other
	 *         filters.
	 */
	public BusinessQuery sortBy(SortMode sortMode) {
		this.sortMode = sortMode;
		this.hasSortMode = true;
		return this;
	}

	/**
	 * Remove the sorting option, so that results are sorted by the default
	 * (SortMode.BEST_MATCHED).
	 * 
	 * @return This BusinessQuery instance, allowing you to chain together other
	 *         filters.
	 */
	public BusinessQuery removeSortBy() {
		this.hasSortMode = false;
		return this;
	}

	/**
	 * Limit the number of results returned to <b>limit</b>.
	 * 
	 * @param limit
	 * @return This BusinessQuery instance, allowing you to chain together other
	 *         filters.
	 */
	public BusinessQuery limitResults(int limit) {
		this.limit = limit;
		this.hasLimit = true;
		return this;
	}

	/**
	 * Allow any number of results to be returned.
	 * 
	 * @return This BusinessQuery instance, allowing you to chain together other
	 *         filters.
	 */
	public BusinessQuery removeResultLimit() {
		this.hasLimit = false;
		return this;
	}

	/**
	 * Offset the list of returned business results by <b>offset</b>.
	 * 
	 * @param offset
	 * @return This BusinessQuery instance, allowing you to chain together other
	 *         filters.
	 */
	public BusinessQuery offsetResults(int offset) {
		this.offset = offset;
		this.hasOffset = true;
		return this;
	}

	/**
	 * Remove any offset of the results, so results will be returned starting
	 * with the first.
	 * 
	 * @return This BusinessQuery instance, allowing you to chain together other
	 *         filters.
	 */
	public BusinessQuery removeResultOffset() {
		this.hasOffset = false;
		return this;
	}

	/**
	 * "Paginate" the results, that is return a certain number at a certain
	 * point. This is the same as setting a limit and an offset.
	 * 
	 * @param offset
	 * @param limit
	 * @return This BusinessQuery instance, allowing you to chain together other
	 *         filters.
	 */
	public BusinessQuery paginate(int offset, int limit) {
		this.offsetResults(offset);
		this.limitResults(limit);
		return this;
	}

	/**
	 * Remove any "pagination". This is the same as removing a result limit and
	 * any offset.
	 * 
	 * @return This BusinessQuery instance, allowing you to chain together other
	 *         filters.
	 */
	public BusinessQuery removePagination() {
		this.removeResultLimit();
		this.removeResultOffset();
		return this;
	}

	/**
	 * This method is intended for internal use only.<Br>
	 * <br>
	 * 
	 * Converts the internal list of filters into a dictionary.
	 * 
	 * @return
	 */
	public HashMap<String, String> getFilters() {
		HashMap<String, String> filters = new HashMap<String, String>();

		filters.putAll(this.location.getFilter());

		if (this.hasTerm) {
			filters.put("term", this.term);
		}

		if (this.hasLimit) {
			filters.put("limit", "" + this.limit);
		}

		if (this.hasOffset) {
			filters.put("offset", "" + this.offset);
		}

		if (this.hasCategories && this.categories.length >= 1) {
			StringBuilder csl = new StringBuilder();
			csl.append(this.categories[0]);
			if (this.categories.length > 1) {
				for (int i = 1; i < this.categories.length; i++) {
					csl.append("," + this.categories[i]);
				}
			}
			filters.put("category_filter", csl.toString());
		}

		if (this.hasRadius) {
			filters.put("radius_filter", "" + this.radius);
		}

		if (this.hasDeals) {
			filters.put("deals_filter", (this.deals ? "1" : "0"));
		}

		if (this.hasSortMode) {
			filters.put("sort", "" + this.sortMode.ordinal());
		}

		return filters;
	}
}
