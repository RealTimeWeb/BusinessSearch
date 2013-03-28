package realtimeweb.businesssearch.main;

/**
 * An abstract version of the Business Searchers. The implementations are:
 * <ul>
 * <li>JsonBusinessSearch: Returns json-formatted strings.</li>
 * <li>StructuredBusinessSearch: Returns maps.</li>
 * <li>BusinessSearch: Returns well-structured classes.</li>
 * </ul> 
 * @author acbart
 *
 */
public interface AbstractBusinessSearch {
	/**
	 * Connects an existing Business Searcher to the online Business Search service.
	 * @param consumerKey
	 * @param consumerSecret
	 * @param token
	 * @param tokenSecret
	 */
	public void connect(String consumerKey, String consumerSecret, String token, String tokenSecret);

	/**
	 * Establishes that data should be retrieved locally.
	 * 
	 * If data is being retrieved locally, you must be sure that your parameters
	 * match locally stored data. Otherwise, you will get nothing in return.
	 * @param spoofing
	 */
	public void setLocal();

}
