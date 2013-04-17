package de.htwg.seapal.maps.database;

import de.htwg.seapal.maps.models.IMaps;

public interface IMapsDatabase {
	/**
	 * Saves the maps settings.
	 * @param maps The maps model.
	 */
	void save(IMaps maps);
	
	/**
	 * Loads the maps settings.
	 * @return The maps model.
	 */
	IMaps load();
	
	/**
	 * Closes the database.
	 * @return True, if everything was fine.
	 */
	boolean close();
}
