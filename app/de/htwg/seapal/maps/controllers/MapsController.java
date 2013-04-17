package de.htwg.seapal.maps.controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.htwg.seapal.boat.controllers.IBoatController;
import de.htwg.seapal.maps.database.IMapsDatabase;
import de.htwg.seapal.maps.models.IMaps;
import de.htwg.seapal.person.controllers.IPersonController;

/**
 * The controller of the maps component.
 * @author Benjamin
 */
@Singleton
public class MapsController extends AbstractMapsController {

	@Inject
	public MapsController(IMaps maps, IMapsDatabase db, IBoatController boatController, IPersonController personController) {
		this.maps = maps;
		this.mapsDatabase = db;
		this.boatController = boatController;
		this.personController = personController;
	}
}
