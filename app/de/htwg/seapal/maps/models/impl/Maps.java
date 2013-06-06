package de.htwg.seapal.maps.models.impl;

import java.awt.Point;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

	@Required
	private boolean menuVisible;
	@Required
	private MapsMenuPositionState menuPositionState;// = MapsMenuPositionState.LEFT;
	@Required
	private MapsPositionState positionState;// = MapsPositionState.FIXED;
	private Point position = new Point();
	@Required
	private MapsType type = MapsType.CARD;

	@Id
	@GeneratedValue
	private Long id;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	/*private String revision;
	@JsonProperty("_rev")
    public String getRevision() {
            return revision;
    }

    @JsonProperty("_rev")
    public void setRevision(String s) {
            revision = s;
    }*/
	
	
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
	public Point getPosition() {
		return this.position;
	}

	@Override
	public void setPosition(Point position) throws IllegalStateException {
		if (getPositionState() != MapsPositionState.FIXED) {
			throw new IllegalStateException(
					"It is not allowed to set the maps position"
							+ "when the maps position state is not FIXED.");
		}

		this.position = position;
	}

	@Override
	public MapsType getType() {
		return this.type;
	}

	@Override
	public void setType(MapsType type) {
		this.type = type;
	}
}
