package realtimeweb.businesssearch.regular;

import java.util.HashMap;

/**
 * An option for a Gift Certificate - that is, a possible value for a gift
 * certificate to have (for instance, Kohl's might have $10, $20, and $50 gift
 * cards available).
 * 
 * @author acbart
 * 
 */
public class GiftCertificateOption {
	private int price;

	/**
	 * This is an internal method. Please ignore it.
	 * 
	 * @param raw
	 */
	GiftCertificateOption(HashMap<String, Object> raw) {
		this.price = Integer.parseInt((raw.get("price").toString()));
		this.formattedPrice = (String) raw.get("formatted_price");
	}

	private String formattedPrice;

	/**
	 * Returns the price of this Gift Certificate Option in cents ($50 would be
	 * 5000). Cents are used instead of dollars because decimal values cannot be
	 * stored correctly in computers.
	 * 
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Returns a nicely-formatted string representation of this price (e.g.
	 * "$50").
	 * 
	 * @return the formattedPrice
	 */
	public String getFormattedPrice() {
		return formattedPrice;
	}
}
