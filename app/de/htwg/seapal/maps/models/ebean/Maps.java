package de.htwg.seapal.maps.models.ebean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import de.htwg.seapal.maps.models.AbstractMaps;

@Entity
public class Maps extends AbstractMaps {

	@Id
	@GeneratedValue
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
