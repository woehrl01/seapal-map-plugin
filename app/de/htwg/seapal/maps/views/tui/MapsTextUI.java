package de.htwg.seapal.maps.views.tui;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.google.inject.Inject;

import de.htwg.seapal.maps.controllers.IMapsController;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObserver;
import de.htwg.util.plugin.Plugin;

/**
 * The text user interface of the maps component.
 */
public class MapsTextUI implements IObserver {

	IMapsController mapsController;
	Set<Plugin> plugins;
	Scanner scanner = new Scanner(System.in);
	Plugin currentPlugin = null;

	@Inject
	public MapsTextUI(IMapsController mapsController, Set<Plugin> plugins) {
		this.mapsController = mapsController;
		this.plugins = plugins;
	}

	public void update(Event e) {
		printTUI();
	}

	public boolean processInputLine(String line) {
		if (line.equalsIgnoreCase("q")) {
			if(currentPlugin == null){
				return false;
			}else{
				currentPlugin = null;
				this.printTUI();
			}
		}else if(currentPlugin == null){
			for (Plugin plugin : plugins) {
				if (line.toLowerCase().charAt(0) == plugin.getMenuKey()) {
					currentPlugin = plugin;
					currentPlugin.printTUI();
					break;
				}
			}
		}else{
			currentPlugin.processInputLine(line);
		}
		return true;
	}

	public void printTUI() {
		System.out.println("Maps: ");
		System.out.println("q - Quit");

		for(Plugin plugin : plugins) {
			System.out.print(plugin.getMenuKey() + " - ");
			System.out.println(plugin.getMenuEntry());
		}

	}

}
