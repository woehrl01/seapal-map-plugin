package de.htwg.seapal.maps.controllers.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.htwg.seapal.boat.controllers.IBoatController;
import de.htwg.seapal.maps.controllers.AbstractMapsController;
import de.htwg.seapal.maps.models.IMaps;

/**
 * The controller of the maps component.
 * @author Benjamin
 */
@Singleton
public class MapsController extends AbstractMapsController {

	@Inject
	public MapsController(IMaps maps, IBoatController boatController) {
		this.maps = maps;
		this.boatController = boatController;
	}
}
