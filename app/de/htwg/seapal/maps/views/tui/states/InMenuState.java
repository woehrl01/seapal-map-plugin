package de.htwg.seapal.maps.views.tui.states;

import java.util.LinkedHashMap;
import java.util.Map;
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
		Map<Character, String> menuMap = new LinkedHashMap<Character, String>();
		menuMap.put('q', "Quit");
		
		for (Plugin plugin : plugins) {
			menuMap.put(plugin.getMenuKey(), plugin.getMenuEntry());
		}

		for(Map.Entry<Character, String> entry : menuMap.entrySet()){
			System.out.print(entry.getKey() + " - ");
			System.out.print(entry.getValue());
			System.out.println("");
		}

	}

	@Override
	public boolean process(StateContext context, String input) {
		if(input.equalsIgnoreCase("q")){
			return false;
		}else{
			for (Plugin plugin : plugins) {
				if (input.toLowerCase().charAt(0) == plugin.getMenuKey()) {
					context.setState(stateFactory.createInPlugin(plugin));
					break;
				}
			}
		}
		return true;
	}

}
