package realtimeweb.businesssearch.regular;

import java.util.HashMap;

/**
 * A DealOption is an item in a Deal.
 * 
 * @author acbart
 * 
 */
public class DealOption {
	private String title;
	private String purchaseUrl;
	private int price; // in cents
	private String priceFormatted;
	private int originalPrice;
	private String originalPriceFormatted;
	private boolean isQuantityLimited;
	private int remainingCount;

	DealOption(HashMap<String, Object> raw) {
		this.title = (String) raw.get("title");
		this.purchaseUrl = (String) raw.get("purchase_url");
		this.price = Integer.parseInt((raw.get("price").toString()));
		this.priceFormatted = (String) raw.get("formatted_price");
		this.originalPrice = Integer.parseInt((raw.get("original_price")
				.toString()));
		this.originalPriceFormatted = (String) raw
				.get("formatted_original_price");
		this.isQuantityLimited = Boolean.parseBoolean(raw.get("is_quantity_limited").toString());
		this.remainingCount = -1;
		if (raw.containsKey("remaining_count")) {
			this.remainingCount = Integer.parseInt((raw.get("remaining_count")
					.toString()));
		}
	}

	/**
	 * Returns the original price of this option, before the deal is applied, in
	 * cents.
	 * 
	 * @return the originalPrice
	 */
	public int getOriginalPrice() {
		return originalPrice;
	}

	/**
	 * Returns the original price of this option, before the deal is applied, as
	 * a nicely formatted string.
	 * 
	 * @return the originalPriceFormatted
	 */
	public String getOriginalPriceFormatted() {
		return originalPriceFormatted;
	}

	/**
	 * Returns the price offered by this deal, in cents.
	 * 
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Returns the price offered by this deal as a nicely formatted string.
	 * 
	 * @return the priceFormatted
	 */
	public String getPriceFormatted() {
		return priceFormatted;
	}

	/**
	 * Returns the URL for this purchase option.
	 * 
	 * @return the purchaseUrl
	 */
	public String getPurchaseUrl() {
		return purchaseUrl;
	}

	/**
	 * Returns the remaining deal options available for purchase, if this deal
	 * is limited. If it is not limited, -1 is returned.
	 * 
	 * @return the remainingCount
	 */
	public int getRemainingCount() {
		return remainingCount;
	}

	/**
	 * Returns the title of this option.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns whether the quantity of this deal is limited. 
	 * 
	 * @return the isQuantityLimited
	 */
	public boolean isQuantityLimited() {
		return isQuantityLimited;
	}
}
