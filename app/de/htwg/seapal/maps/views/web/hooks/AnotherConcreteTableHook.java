package de.htwg.seapal.maps.views.web.hooks;

import de.devsurf.injection.guice.annotations.Bind;

@Bind(multiple=true)
public class AnotherConcreteTableHook implements TableHook {

	@Override
	public String print(int id) {
		String str = "bla" + id + "dd";
		return str;
	}

}
