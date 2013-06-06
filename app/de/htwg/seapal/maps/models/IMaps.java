package de.htwg.seapal.maps.models;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public interface IMaps extends Serializable {
	
	/**
	 * Gets the ID.
	 * @return The ID.
	 */
	@JsonProperty("_id")
    String getId();
	 
    /**
     * Sets the ID.
     * @param id The ID.
     */
	@JsonProperty("_id")
    void setId(String id);
    
	/**
	 * Gets the revision.
	 * @return The revision.
	 */
    @JsonProperty("_rev")
    String getRevision();
    
    /**
     * Sets the revision.
     * @param rev The revision.
     */
    @JsonProperty("_rev")
    void setRevision(String rev);
	
	/**
	 * Indicates whether the menu of the maps menu is visible or not.
	 * @return Returns true, if the maps menu is visible.
	 */
	boolean getMenuVisible();
	
	/**
	 * Sets the Menu to visible.
	 */
	void setMenuVisible( boolean visible );
	
	/**
	 * Gets the maps position state.
	 * @return The maps position state.
	 */
	MapsMenuPositionState getMenuPositionState();
	
	/**
	 * Sets the position state of the menu.
	 * @param menuPositionState The menu position state.
	 */
	void setMenuPositionState(MapsMenuPositionState menuPositionState);
	
	/**
	 * Gets the position state of the map.
	 * @return The maps position state.
	 */
	MapsPositionState getPositionState();
	
	/**
	 * Sets the maps position state.
	 * @param positionState The maps position state.
	 */
	void setPositionState(MapsPositionState positionState);
	
	/**
	 * Gets the maps center position.
	 * @return The position in form: lat,lng
	 */
	String getPosition();
	
	/**
	 * Sets the maps center position
	 * @param position The position where the maps center should be located (lat,lng).
	 * @throws IllegalStateException If getPositionState() is not MapsPositionState.FIXED.
	 */
	void setPosition(String position) throws IllegalStateException;
	
	/**
	 * Gets the maps type.
	 * @return The current maps type.
	 */
	MapsType getType();
	
	/**
	 * Sets the preferred maps type.
	 * @param type The maps type.
	 */
	void setType(MapsType type);
}
