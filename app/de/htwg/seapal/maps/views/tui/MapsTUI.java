package de.htwg.seapal.maps.views.tui;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.google.inject.Inject;

import de.htwg.seapal.maps.controllers.IMapsController;
import de.htwg.seapal.common.observer.Event;
import de.htwg.seapal.common.observer.IObserver;
import de.htwg.seapal.common.plugin.Plugin;

/**
 * The text user interface of the maps component.
 */
public class MapsTUI implements IObserver {

	IMapsController mapsController;
	Set<Plugin> plugins;
	Scanner scanner = new Scanner(System.in);
	Plugin currentPlugin = null;

	@Inject
	public MapsTUI(IMapsController mapsController, Set<Plugin> plugins) {
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

		Iterator<Plugin> itr = plugins.iterator();
		while (itr.hasNext()) {
			Plugin plugin = itr.next();
			System.out.print(plugin.getMenuKey() + " - ");
			System.out.println(plugin.getMenuEntry());
		}

	}

}
