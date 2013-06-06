package de.htwg.seapal.maps.models.mock;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonProperty;

import de.htwg.seapal.maps.models.IMaps;
import de.htwg.seapal.maps.models.MapsMenuPositionState;
import de.htwg.seapal.maps.models.MapsPositionState;
import de.htwg.seapal.maps.models.MapsType;

/**
 * Mock implementation of the Maps class.
 * @author Benjamin
 */
public class Maps implements IMaps {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private String id;
	
	private String rev;
	
	@Override
	@JsonProperty("_id")
	public String getId() {
		return this.id;
	}

	@Override
	@JsonProperty("_id")
	public void setId(String id) {
		this.id = id;
	}

	@Override
	@JsonProperty("_rev")
	public String getRevision() {
		return this.rev;
	}

	@Override
	@JsonProperty("_rev")
	public void setRevision(String rev) {
		this.rev = rev;
	}
	
	@Override
	public boolean getMenuVisible() {
		return true;
	}

	@Override
	public MapsMenuPositionState getMenuPositionState() {
		return MapsMenuPositionState.LEFT;
	}

	@Override
	public MapsPositionState getPositionState() {
		return MapsPositionState.FIXED;
	}

	@Override
	public String getPosition() {
		return "44.44,55.55";
	}

	@Override
	public MapsType getType() {
		return MapsType.CARD;
	}

	@Override
	public void setMenuVisible(boolean visible) {
		
	}

	@Override
	public void setMenuPositionState(MapsMenuPositionState menuPositionState) {
		
	}

	@Override
	public void setPositionState(MapsPositionState positionState) {
		
	}

	@Override
	public void setPosition(String position) throws IllegalStateException {
		
	}

	@Override
	public void setType(MapsType type) {
		
	}
}
