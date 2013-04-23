package de.htwg.seapal.maps.app.module;


import java.util.logging.Level;

import com.google.inject.Binder;
import com.google.inject.multibindings.Multibinder;


import de.devsurf.injection.guice.annotations.features.MultiBindingFeature;
import de.devsurf.injection.guice.scanner.ClasspathScanner;
import de.devsurf.injection.guice.scanner.PackageFilter;
import de.devsurf.injection.guice.scanner.StartupModule;
import de.devsurf.injection.guice.scanner.features.ScannerFeature;
import de.devsurf.injection.guice.scanner.asm.ASMClasspathScanner;

public class ReflectionModule extends StartupModule {

	public ReflectionModule(Class<? extends ClasspathScanner> scanner, PackageFilter... packages) {
		super(scanner, packages);
	}
	
	public ReflectionModule(){
		this(ASMClasspathScanner.class, PackageFilter.create("de.htwg.seapal"));
		
	}

	@Override
	protected Multibinder<ScannerFeature> bindFeatures(Binder binder) {	
		Multibinder<ScannerFeature> listeners = Multibinder.newSetBinder(binder, ScannerFeature.class);
		listeners.addBinding().to(MultiBindingFeature.class);
		return listeners;
	}

}