package de.htwg.seapal.maps.views.tui;

public interface ICommand {
	char getKey();
	String getTitle();
	void execute(String param);
}
