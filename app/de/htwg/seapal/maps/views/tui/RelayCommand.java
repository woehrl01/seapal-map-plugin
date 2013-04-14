package de.htwg.seapal.maps.views.tui;

public abstract class RelayCommand implements ICommand {

	protected char key;
	protected String title;
	
	public RelayCommand(char key, String title) {
		this.key = key;
		this.title = title;
	}
	
	@Override
	public char getKey() {
		return this.key;
	}

	@Override
	public String getTitle() {
		return this.title;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		
		if (!(other instanceof RelayCommand)) {
			return false;
		}
		
		RelayCommand command = (RelayCommand)other;
		return this.key == command.key;
	}
}
