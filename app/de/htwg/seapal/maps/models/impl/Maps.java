package de.htwg.seapal.maps.models.impl;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonProperty;
import org.ektorp.support.CouchDbDocument;

import play.data.validation.Constraints.Required;

import de.htwg.seapal.maps.models.IMaps;
import de.htwg.seapal.maps.models.MapsMenuPositionState;
import de.htwg.seapal.maps.models.MapsPositionState;
import de.htwg.seapal.maps.models.MapsType;

/**
 * The model of the maps component.
 * @author Benjamin
 *
 */
@Entity
public class Maps implements IMaps {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private String id;
	
	private String rev;
	
	@Required
	private boolean menuVisible;
	@Required
	private MapsMenuPositionState menuPositionState;
	@Required
	private MapsPositionState positionState;
	private String position = "0.0,0.0";
	@Required
	private MapsType type;
	
	@Override
	@JsonProperty("_id")
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	@JsonProperty("_id")
	public String getId() {
		//return this.id;
		return "978c2d55-a7c5-485f-9584-f16f0c9ee25f";
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
		return this.menuVisible;
	}
	
	@Override
	public void setMenuVisible( boolean visible ) {
		this.menuVisible = visible;
	}

	@Override
	public MapsMenuPositionState getMenuPositionState() {
		return this.menuPositionState != null ? this.menuPositionState : MapsMenuPositionState.LEFT;
	}

	@Override
	public void setMenuPositionState(MapsMenuPositionState menuPositionState) {
		this.menuPositionState = menuPositionState;
	}

	@Override
	public MapsPositionState getPositionState() {
		return this.positionState != null ? this.positionState : MapsPositionState.FIXED;
	}

	@Override
	public void setPositionState(MapsPositionState positionState) {
		this.positionState = positionState;
	}

	@Override
	public String getPosition() {
		return this.position;
	}

	@Override
	public void setPosition(String position) throws IllegalStateException {
		if (getPositionState() != MapsPositionState.FIXED) {
			throw new IllegalStateException(
					"It is not allowed to set the maps position"
							+ "when the maps position state is not FIXED.");
		}

		this.position = position;
	}

	@Override
	public MapsType getType() {
		return this.type != null ? this.type : MapsType.CARD;
	}

	@Override
	public void setType(MapsType type) {
		this.type = type;
	}
}
