package de.htwg.seapal.maps.app;

import java.util.Set;

import com.google.inject.Inject;

import play.api.templates.Html;
import scala.collection.mutable.StringBuilder;

import de.htwg.seapal.common.plugin.HookHandler;
import de.htwg.seapal.common.plugin.HookRegistry;
import de.htwg.seapal.common.plugin.Initializable;

public class AppInitilizer {

	@Inject
	HookRegistry registry;
	
	@Inject
	Set<Initializable> inits;
	
	public void init(){
		for(Initializable init : inits){
			init.initHooks(registry);
		}
	}
	
	
}
