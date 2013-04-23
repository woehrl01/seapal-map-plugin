package de.htwg.seapal.maps.views.tui;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import de.htwg.seapal.common.observer.Event;
import de.htwg.seapal.common.observer.IObserver;
import de.htwg.seapal.common.views.tui.StateContext;
import de.htwg.seapal.common.views.tui.TuiState;
import de.htwg.seapal.maps.views.tui.states.InMenuState;

/**
 * The text user interface of the maps component.
 */
public class MapsTUI implements IObserver, StateContext {

	private TuiState currentState = null;

	@Inject
	public MapsTUI(@Named("InMenu") TuiState firstState) {
		this.currentState = firstState;
	}

	public void update(Event e) {
		printTUI();
	}

	public boolean processInputLine(String line) {
		boolean hasHandled = currentState.process(this, line);
		if(hasHandled){
			currentState.print();
		}
		return hasHandled;
	}

	public void printTUI() {
		currentState.print();
	}

	@Override
	public void setState(TuiState state) {
		this.currentState = state;
	}

}
