package realtimeweb.businesssearch.main;

/**
 * If the mode is BEST_MATCHED, a search may retrieve an additional 20
 * businesses past the initial limit of the first 20 results. This is done by
 * specifying an offset and limit of 20. Sort by distance is only supported for
 * a location or geographic search. The rating sort is not strictly sorted by
 * the rating value, but by an adjusted rating value that takes into account the
 * number of ratings, similar to a bayesian average. This is so a business with
 * 1 rating of 5 stars doesn't immediately jump to the top.
 * 
 * @author acbart
 * 
 */
public enum SortMode {
	BEST_MATCHED, DISTANCE, HIGHEST_RATED;
	
	public String toString() {
		switch (this) {
		default: case BEST_MATCHED: return "Best Matched";
		case DISTANCE: return "Distance";
		case HIGHEST_RATED: return "Highest Rated";
		}
	}
}
