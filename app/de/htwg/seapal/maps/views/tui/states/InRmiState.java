package de.htwg.seapal.maps.views.tui.states;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import de.htwg.seapal.common.plugin.Plugin;
import de.htwg.seapal.common.views.tui.StateContext;
import de.htwg.seapal.common.views.tui.TuiState;
import de.htwg.seapal.person.controllers.IPersonController;

public class InRmiState implements TuiState {

	private StateFactory stateFactory;
	private IPersonController personController;
	
	@Inject
	public InRmiState(StateFactory stateFactory){
		this.stateFactory = stateFactory;
		
		Registry reg;
		try {
			reg = LocateRegistry.getRegistry("sunja.htwg-konstanz.de");
			this.personController = (IPersonController)reg.lookup("de.htwg.seapal.person");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void print() {
		System.out.println("q - Quit");
		System.out.println("a - Any command");
	}

	@Override
	public boolean process(StateContext context, String input) {
		if(input.equalsIgnoreCase("a")){
			personController.addPerson();
			System.out.println("Person was added...");
			return true;
		}else if(input.equalsIgnoreCase("q")){
			context.setState(stateFactory.createInMenu());
			return true;
		}
		
		return false;
	}

}
