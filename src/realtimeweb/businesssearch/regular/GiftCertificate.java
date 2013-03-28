package realtimeweb.businesssearch.regular;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * A GiftCertificate is something you can buy to give as a present to someone
 * else, and will have the value of however much you bought.
 * 
 * @author acbart
 * 
 */
public class GiftCertificate {
	private String id;
	private String url;
	private String imageUrl;
	private String currencyCode;
	private String unusedBalances;
	private List<GiftCertificateOption> options;

	@SuppressWarnings("unchecked")
	GiftCertificate(HashMap<String, Object> raw) {
		this.id = (String) raw.get("id");
		this.url = (String) raw.get("url");
		this.imageUrl = (String) raw.get("image_url");
		this.currencyCode = (String) raw.get("currency_code");
		this.unusedBalances = (String) raw.get("unused_balance");

		this.options = new ArrayList<GiftCertificateOption>();
		if (raw.containsKey("options")) {
			Iterator<HashMap<String, Object>> o = ((ArrayList<HashMap<String, Object>>) raw
					.get("options")).iterator();
			while (o.hasNext()) {
				this.options.add(new GiftCertificateOption(o.next()));
			}
		}
	}

	/**
	 * Returns a unique identifier for this GiftCertificate.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the Gift certificate landing page url.
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Returns the Gift certificate image url.
	 * 
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
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
	 * Returns how unused balances are returned - either as cash ("CASH") or
	 * store credit ("CREDIT").
	 * 
	 * @return the unusedBalances
	 */
	public String getUnusedBalances() {
		return unusedBalances;
	}

	/**
	 * Returns a list of Gift Certificate options that are available, or how
	 * much you can purchase a gift certificate for (e.g. $10, $20, $50, ...).
	 * 
	 * @return the options
	 */
	public List<GiftCertificateOption> getOptions() {
		return options;
	}

}
