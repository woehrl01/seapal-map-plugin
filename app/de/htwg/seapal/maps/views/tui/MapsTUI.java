package de.htwg.seapal.maps.views.tui;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.google.inject.Inject;

import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObserver;
import de.htwg.util.plugin.Plugin;

public class MapsTUI implements IObserver {

	Set<Plugin> plugins;
	Scanner scanner = new Scanner(System.in);

	@Inject
	public MapsTUI(Set<Plugin> plugins) {
		this.plugins = plugins;
	}

	public void update(Event e) {
		printTUI();
	}

	public boolean processInputLine(String line) {
		boolean active = true;
		if (line.equalsIgnoreCase("q")) {
			active = false;
		}
		Iterator<Plugin> itr = plugins.iterator();
		while (itr.hasNext()) {
			Plugin plugin = itr.next();
			if (line.toLowerCase().charAt(0) == plugin.getMenuKey()) {
				plugin.printTUI();
				plugin.processInputLine(scanner.next());
			}
		}
		return active;
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
