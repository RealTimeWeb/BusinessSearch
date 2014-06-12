package realtimeweb.simplebusiness.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;




/**
 * Information about a specific earthquake.
 */
public class Business {
	
    private Double rating;
    private String description;
    private String phone;
    private String location;
    private String id;
    private String name;
    
    
    /*
     * @return Rating for this business (value ranges from 1, 1.5, ... 4.5, 5)
     */
    public Double getRating() {
        return this.rating;
    }
    
    /*
     * @param Rating for this business (value ranges from 1, 1.5, ... 4.5, 5)
     * @return Double
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }
    
    /*
     * @return Snippet text associated with this business
     */
    public String getDescription() {
        return this.description;
    }
    
    /*
     * @param Snippet text associated with this business
     * @return String
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /*
     * @return Phone number for this business formatted for display
     */
    public String getPhone() {
        return this.phone;
    }
    
    /*
     * @param Phone number for this business formatted for display
     * @return String
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /*
     * @return Address for this business formatted for display. Includes all address fields, cross streets and city, state_code, etc.
     */
    public String getLocation() {
        return this.location;
    }
    
    /*
     * @param Address for this business formatted for display. Includes all address fields, cross streets and city, state_code, etc.
     * @return String
     */
    public void setLocation(String location) {
        this.location = location;
    }
    
    /*
     * @return A uniquely identifying id for this business.
     */
    public String getId() {
        return this.id;
    }
    
    /*
     * @param A uniquely identifying id for this business.
     * @return String
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /*
     * @return Name of this business.
     */
    public String getName() {
        return this.name;
    }
    
    /*
     * @param Name of this business.
     * @return String
     */
    public void setName(String name) {
        this.name = name;
    }
    
	
	/**
	 * Creates a string based representation of this Business.
	
	 * @return String
	 */
	public String toString() {
		return "Business[" +rating+", "+description+", "+phone+", "+location+", "+id+", "+name+"]";
	}
	
	/**
	 * Internal constructor to create a Business from a json representation.
	 * @param map The raw json data that will be parsed.
	 * @return 
	 */
    public Business(Map<String, Object> raw) {
        // TODO: Check that the data has the correct schema.
        // NOTE: It's much safer to check the Map for fields than to catch a runtime exception.
        try {
        	if (raw.containsKey("rating")) {
        		this.rating = Double.parseDouble(raw.get("rating").toString());
        	} else {
        		this.rating = new Double(3);
        	}
        	if (raw.containsKey("snippet_text")) {
        		this.description = raw.get("snippet_text").toString();
        	} else {
        		this.description = "";
        	}
        	if (raw.containsKey("display_phone")) {
        		this.phone = raw.get("display_phone").toString();
        	} else {
        		this.phone = "";
        	}
        	if (raw.containsKey("location")) {
        		Map<String, Object> loc = (Map<String, Object>) raw.get("location");
        		if (loc.containsKey("display_address")) {
        			this.location = loc.get("display_address").toString();
        		} else {
        			this.location = "";
        		}
        	} else {
        		this.location ="";
        	}
        	if (raw.containsKey("id")) {
        		this.id = raw.get("id").toString();
        	} else {
        		this.id = "";
        	}
        	if (raw.containsKey("name")) {
        		this.name = raw.get("name").toString();
        	} else {
        		this.name = "";
        	}
        } catch (NullPointerException e) {
    		System.err.println("Could not convert the response to a Business; a field was missing.");
    		e.printStackTrace();
    	} catch (ClassCastException e) {
    		System.err.println("Could not convert the response to a Business; a field had the wrong structure.");
    		e.printStackTrace();
        }
    
	}	
}