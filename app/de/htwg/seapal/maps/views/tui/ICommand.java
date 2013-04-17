package de.htwg.seapal.maps.views.tui;

public interface ICommand {
	
	/**
	 * Gets the command key.
	 * @return The command key.
	 */
	char getKey();
	
	/**
	 * Gets the command title.
	 * @return The command title.
	 */
	String getTitle();
	
	/**
	 * Executes the command.
	 * @param param The command parameter.
	 */
	void execute(String param);
}
