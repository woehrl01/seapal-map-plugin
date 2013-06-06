package de.htwg.seapal.maps.views.tui.states;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import de.htwg.seapal.common.command.ICommand;
import de.htwg.seapal.common.command.RelayCommand;
import de.htwg.seapal.common.views.tui.StateContext;
import de.htwg.seapal.common.views.tui.TuiState;
import de.htwg.seapal.maps.controllers.IMapsController;
import de.htwg.seapal.maps.models.IMaps;
import de.htwg.seapal.maps.models.MapsMenuPositionState;
import de.htwg.seapal.maps.models.MapsPositionState;
import de.htwg.seapal.maps.models.MapsType;

public class InMapsSettingsState implements TuiState {

	/**
	 * The state factory.
	 */
	private StateFactory stateFactory;
	
	/**
	 * The maps controller.
	 */
	private IMapsController mapsController;
	
	/**
	 * The states commands.
	 */
	private List<ICommand> commands = new ArrayList<ICommand>();
	
	private ICommand currentCommand = null;
	
	@Inject
	public InMapsSettingsState(StateFactory stateFactory, IMapsController controller){
		this.stateFactory = stateFactory;
		this.mapsController = controller;
		this.initializeCommands();
	}
	
	private void initializeCommands() {
		commands.add(new RelayCommand('s', "Show settings") {
			@Override
			public void execute(String input){		
				try {
					IMaps settings = mapsController.getMapsSettings();
					if (settings != null) {
						System.out.printf("menuVisible: %b%n", settings.getMenuVisible());
						System.out.printf("menuPositionState: %s%n", settings.getMenuPositionState().toString());
						System.out.printf("positionState: %s%n", settings.getPositionState().toString());
						System.out.printf("type: %s%n", settings.getType().toString());
						String pos = settings.getPosition();
						System.out.printf("position: %s%n", pos);
					} else {
						System.out.println("No settings available");
					}
					
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		
		commands.add(new RelayCommand('v', "Set menu visible [true|false]") {
			@Override
			public void execute(String input){
				boolean visible = Boolean.parseBoolean(input);
				
				try {
					if (visible) {
						mapsController.showMenu();
					} else {
						mapsController.hideMenu();
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		
		commands.add(new RelayCommand('m', "Set menu position state [value: "+ getEnumValuesBySampleValue(MapsMenuPositionState.LEFT) +"]") {
			@Override
			public void execute(String input){
				MapsMenuPositionState state = MapsMenuPositionState.valueOf(input);
				
				try {
					mapsController.setMenuPositionState(state);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		
		commands.add(new RelayCommand('p', "Set position state [value: "+ getEnumValuesBySampleValue(MapsPositionState.FIXED) +"]") {
			@Override
			public void execute(String input){
				MapsPositionState state = MapsPositionState.valueOf(input);
				
				try {
					mapsController.setPositionState(state);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		
		commands.add(new RelayCommand('t', "Set maps type [value: "+ getEnumValuesBySampleValue(MapsType.CARD) +"]") {
			@Override
			public void execute(String input){
				MapsType state = MapsType.valueOf(input);
				
				try {
					mapsController.setType(state);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		
		commands.add(new RelayCommand('x', "Set position [value: x,y]") {
			@Override
			public void execute(String input){
				try {
					mapsController.setPosition(input);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public void print() {
		StringBuilder sb = new StringBuilder();
		
		if(currentCommand == null) {
			
			sb.append("\n##### MAPS SETTINGS #####\n");
			
			for(ICommand command : commands) {
				sb.append(command.getKey()).append(" - ").append(command.getTitle()).append("\n");
			}
			
			sb.append("\n");
			sb.append(">>> Command: ");
		} else {
			sb.append("\n");
			sb.append(">>> Enter value (or press ENTER): ");
		}

		System.out.print(sb.toString());
	}

	@Override
	public boolean process(StateContext context, String input) {
		
		if (currentCommand == null) {
			char cmd = input.charAt(0);
			
			// handle exit
			if(cmd == 'q'){
				currentCommand = null;
				context.setState(stateFactory.createInMenu());
				return true;
			}
			
			boolean found = false;
			
			for (ICommand command : commands) {
				if (command.getKey() == cmd) {
					currentCommand = command;
					found = true;
					break;
				}
			}
			
			if (!found) {
				System.out.println("Unknown Command!");
			}
		} else {
			currentCommand.execute(input);
			currentCommand = null;
		}
		
		return true;
	}

	private String getEnumValuesBySampleValue(Enum e) {
		Object[] possibleValues = e.getDeclaringClass().getEnumConstants();
		StringBuilder sb = new StringBuilder();
		boolean notFirst = false;
		for (Object value : possibleValues) {
			if (notFirst) {
				sb.append('|');
			} else {
				notFirst = true;
			}
			
			sb.append(value.toString());
		}
		return sb.toString();
	}
}
