package de.htwg.seapal.maps.controllers.mock;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.htwg.seapal.boat.controllers.IBoatController;
import de.htwg.seapal.maps.controllers.AbstractMapsController;
import de.htwg.seapal.maps.models.IMaps;
import de.htwg.seapal.person.controllers.IPersonController;

/**
 * Mock controller of the maps component.
 * @author Benjamin
 */
@Singleton
public class MapsController extends AbstractMapsController {

	@Inject
	public MapsController(IMaps maps, IBoatController boatController, IPersonController personController) {
		this.maps = maps;
		this.boatController = boatController;
		this.personController = personController;
	}
}
