package de.htwg.seapal.maps.views.tui.states;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import de.htwg.seapal.common.plugin.Plugin;
import de.htwg.seapal.common.views.tui.StateContext;
import de.htwg.seapal.common.views.tui.TuiState;

public class InPluginState implements TuiState {

	private Plugin plugin;
	private InMenuStateFactory menuStateFactory;
	
	@Inject
	public InPluginState(InMenuStateFactory menuStateFactory, @Assisted Plugin plugin){
		this.plugin = plugin;
		this.menuStateFactory = menuStateFactory;
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
			context.setState(menuStateFactory.create());
			return true;
		}
		
		return false;
	}

}
