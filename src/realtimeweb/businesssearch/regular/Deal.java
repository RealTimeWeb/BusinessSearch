package realtimeweb.businesssearch.regular;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * A Deal is an offer made by the Business, such as a "2-for-1 Sale".
 * 
 * @author acbart
 * 
 */
public class Deal {
	private String id;
	private String title;
	private String url;
	private String imageUrl;
	private String currencyCode;
	private int timeStart;
	private int timeEnd;
	private boolean isPopular;
	private String details;
	private String importantRestrictions;
	private String additionalRestrictions;

	private List<DealOption> options;

	@SuppressWarnings("unchecked")
	Deal(HashMap<String, Object> raw) {
		this.id = (String) raw.get("id");
		this.title = (String) raw.get("title");
		this.url = (String) raw.get("url");
		this.imageUrl = (String) raw.get("image_url");
		this.currencyCode = (String) raw.get("currency_code");
		this.timeStart = Integer.parseInt((raw.get("time_start").toString()));
		this.timeEnd = -1;
		if (raw.containsKey("time_end")) {
			this.timeEnd = Integer.parseInt((raw.get("time_end").toString()));
		}
		this.isPopular = false;
		if (raw.containsKey("this.isPopular")) {
			this.isPopular = (boolean) raw.get("is_popular");
		}
		this.details = (String) raw.get("what_you_get");
		this.importantRestrictions = (String) raw.get("important_restrictions");
		this.additionalRestrictions = (String) raw
				.get("additional_restrictions");

		this.options = new ArrayList<DealOption>();
		if (raw.containsKey("options")) {
			Iterator<HashMap<String, Object>> d = ((ArrayList<HashMap<String, Object>>) raw
					.get("options")).iterator();
			while (d.hasNext()) {
				this.options.add(new DealOption(d.next()));
			}
		}
	}

	/**
	 * Returns a string with information about any additional restrictions.
	 * 
	 * @return the additionalRestrictions
	 */
	public String getAdditionalRestrictions() {
		return additionalRestrictions;
	}

	/**
	 * Returns the <a href='http://en.wikipedia.org/wiki/ISO_4217'>ISO-4217
	 * Currency Code</a>
	 * 
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * Provides additional details for the Deal, separated by newlines
	 * 
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * A unique identifier for the Deal.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * A URL of the image for the deal.
	 * 
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * Provides any important restrictions for the Deal, separated by newlines
	 * 
	 * @return the importantRestrictions
	 */
	public String getImportantRestrictions() {
		return importantRestrictions;
	}

	/**
	 * Provides the options for this Deal. If there are no options, an empty
	 * list is returned.
	 * 
	 * @return the options
	 */
	public List<DealOption> getOptions() {
		return options;
	}

	/**
	 * Returns when the deal ends as a Unix Timestamp. If the deal does not end,
	 * returns -1.
	 * 
	 * @return the timeEnd
	 */
	public int getTimeEnd() {
		return timeEnd;
	}

	/**
	 * Returns when the deal started as a Unix Timestamp.
	 * 
	 * @return the timeStart
	 */
	public int getTimeStart() {
		return timeStart;
	}

	/**
	 * Returns the title of the deal.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the URL of the deal's page.
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Returns whether the deal is popular. If popularity information was not
	 * available, false is returned.
	 * 
	 * @return the isPopular
	 */
	public boolean isPopular() {
		return isPopular;
	}
}
