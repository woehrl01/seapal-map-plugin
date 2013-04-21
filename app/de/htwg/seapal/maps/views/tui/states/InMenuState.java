package de.htwg.seapal.maps.views.tui.states;

import java.util.Iterator;
import java.util.Set;

import com.google.inject.Inject;

import de.htwg.seapal.common.plugin.Plugin;
import de.htwg.seapal.common.views.tui.StateContext;
import de.htwg.seapal.common.views.tui.TuiState;


public class InMenuState implements TuiState {
	
	private Set<Plugin> plugins;
	
	private StateFactory stateFactory;
	
	@Inject
	public InMenuState(StateFactory stateFactory, Set<Plugin> plugins){
		this.plugins = plugins;
		this.stateFactory = stateFactory;
	}

	@Override
	public void print() {
		System.out.println("Maps: ");
		System.out.println("q - Quit");

		for (Plugin plugin : plugins) {
			System.out.print(plugin.getMenuKey() + " - ");
			System.out.println(plugin.getMenuEntry());
		}
	}

	@Override
	public boolean process(StateContext context, String input) {
		if(input.equalsIgnoreCase("q")){
			System.out.println("Shuting down...");
			return false;
		}else{
			for (Plugin plugin : plugins) {
				if (input.toLowerCase().charAt(0) == plugin.getMenuKey()) {
					context.setState(stateFactory.createInPluginState(plugin));
					break;
				}
			}
		}
		return true;
	}

}
