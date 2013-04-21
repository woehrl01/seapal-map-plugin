package de.htwg.seapal.maps.views.tui.states;

import com.google.inject.Inject;

import de.htwg.seapal.common.plugin.Plugin;
import de.htwg.seapal.common.views.tui.StateContext;
import de.htwg.seapal.common.views.tui.TuiState;

public class InPluginState implements TuiState {

	private Plugin plugin;
	
	@Inject
	private StateFactory stateFactory;
	
	public InPluginState(Plugin plugin){
		this(null, plugin);
	}
	
	public InPluginState(StateFactory stateFactory, Plugin plugin){
		this.plugin = plugin;
		this.stateFactory = stateFactory;
	}
	
	@Override
	public void print() {
		plugin.printTUI();
	}

	@Override
	public boolean process(StateContext context, String input) {
		if(plugin.processInputLine(input)){
			return true;
		}else if(input.equalsIgnoreCase("q")){
			context.setState(stateFactory.createInMenuState());
			return true;
		}
		
		return false;
	}

}
