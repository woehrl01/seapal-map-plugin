package de.htwg.seapal.maps.views.tui.states;

import de.htwg.seapal.common.plugin.Plugin;

public interface StateFactory {
	InMenuState createInMenu();
	InPluginState createInPlugin(Plugin plugin);
	InRmiState createInRmiState();
}
