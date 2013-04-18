package de.htwg.seapal.maps.views.tui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.htwg.seapal.common.command.RelayCommand;
import de.htwg.seapal.common.views.tui.PluginTextUI;
import com.google.inject.Inject;
import de.htwg.seapal.person.controllers.IPersonController;

public class PersonTextUI extends PluginTextUI {

	private final static String personId = "PERSON-1";
	private IPersonController controller;

	@Inject
	public PersonTextUI(IPersonController controller) {
		super('p', "Person");
		this.controller = controller;
		//controller.addObserver(this); TODO uncomment this (this is not worken because PersonController is still using de.htwg.util)
	}
	
	@Override
	public void initializeCommands() {
		addCommand(new RelayCommand('x', "Add new Person") {
			@Override
			public void execute(String param) {
				controller.addPerson();
			}
		});
		addCommand(new RelayCommand('f', "Set firstname") {
			@Override
			public void execute(String param) {
				controller.setPersonFirstname(personId, param);
			}
		});
		addCommand(new RelayCommand('l', "Set lastname") {
			@Override
			public void execute(String param) {
				controller.setPersonLastname(personId, param);
			}
		});
		addCommand(new RelayCommand('a', "Set person age") {
			@Override
			public void execute(String param) {
				controller.setPersonAge(personId, Integer.valueOf(param).intValue());
			}
		});
		addCommand(new RelayCommand('r', "Set person registration") {
			@Override
			public void execute(String param) {
				controller.setPersonRegistration(personId, parseDate(param));
			}
		});
	}

	private Date parseDate(String input) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

		Date date = null;

		try {
			date = dateFormat.parse(input);
		} catch (ParseException e) {
			date = new Date();
		}

		return date;
	}
}
