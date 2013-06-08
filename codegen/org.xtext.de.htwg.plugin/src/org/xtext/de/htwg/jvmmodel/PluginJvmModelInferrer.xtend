package org.xtext.de.htwg.jvmmodel

import com.google.inject.Inject
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.xtext.de.htwg.plugin.Model
import org.xtext.de.htwg.plugin.Method
import org.xtext.de.htwg.plugin.Enumeration
import org.xtext.de.htwg.plugin.Database
import org.xtext.de.htwg.plugin.Controller

/**
 * <p>Infers a JVM model from the source model.</p> 
 *
 * <p>The JVM model should contain all elements that would appear in the Java code 
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>     
 */
class PluginJvmModelInferrer extends AbstractModelInferrer {

	/**
	 * a builder API to programmatically create Jvm elements 
	 * in readable way.
	 */
	@Inject extension JvmTypesBuilder

	@Inject extension IQualifiedNameProvider

	def dispatch void infer(Model element, IJvmDeclaredTypeAcceptor acceptor, boolean isPrelinkingPhase) {
		acceptor.accept(
			element.toClass( element.fullyQualifiedName )
		).initializeLater [
			documentation = element.documentation
			if (element.superType != null)
				superTypes += element.superType.cloneWithProxies
			for (feature : element.features) {
				switch feature {
	
					Property : {
						members += feature.toField(feature.name, feature.type)
						members += feature.toGetter(feature.name, feature.type)
						members += feature.toSetter(feature.name, feature.type)
					}
	
					Method : {
						members += feature.toMethod(feature.name, feature.type) [
							documentation = feature.documentation
							for (p : feature.params) {
								parameters += p.toParameter(p.name, p.parameterType)
							}
						]
					}
				}
			}
		]
	}
	
	def dispatch void infer(Database element, IJvmDeclaredTypeAcceptor acceptor, boolean isPrelinkingPhase) {
		acceptor.accept(
			element.toClass( element.fullyQualifiedName )
		).initializeLater [
			documentation = element.documentation
			if (element.superType != null)
				superTypes += element.superType.cloneWithProxies
			for (feature : element.features) {
				switch feature {
	
					Property : {
						members += feature.toField(feature.name, feature.type)
						members += feature.toGetter(feature.name, feature.type)
						members += feature.toSetter(feature.name, feature.type)
					}
	
					Method : {
						members += feature.toMethod(feature.name, feature.type) [
							documentation = feature.documentation
							for (p : feature.params) {
								parameters += p.toParameter(p.name, p.parameterType)
							}
						]
					}
				}
			}
		]
	}
	
	def dispatch void infer(Controller element, IJvmDeclaredTypeAcceptor acceptor, boolean isPrelinkingPhase) {
		acceptor.accept(
			element.toClass( element.fullyQualifiedName )
		).initializeLater [
			documentation = element.documentation
			if (element.superType != null)
				superTypes += element.superType.cloneWithProxies
			for (feature : element.features) {
				switch feature {
	
					Property : {
						members += feature.toField(feature.name, feature.type)
						members += feature.toGetter(feature.name, feature.type)
						members += feature.toSetter(feature.name, feature.type)
					}
	
					Method : {
						members += feature.toMethod(feature.name, feature.type) [
							documentation = feature.documentation
							for (p : feature.params) {
								parameters += p.toParameter(p.name, p.parameterType)
							}
						]
					}
				}
			}
		]
	}
	
	def dispatch void infer(Enumeration element, IJvmDeclaredTypeAcceptor acceptor, boolean isPrelinkingPhase) {
		acceptor.accept(
			element.toClass( element.fullyQualifiedName )
		)
	}
}

