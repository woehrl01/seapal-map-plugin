package org.xtext.de.htwg.jvmmodel;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend.lib.Property;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor.IPostIndexingInitializing;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.xtext.de.htwg.plugin.Controller;
import org.xtext.de.htwg.plugin.Database;
import org.xtext.de.htwg.plugin.Enumeration;
import org.xtext.de.htwg.plugin.Feature;
import org.xtext.de.htwg.plugin.Method;
import org.xtext.de.htwg.plugin.Model;

/**
 * <p>Infers a JVM model from the source model.</p>
 * 
 * <p>The JVM model should contain all elements that would appear in the Java code
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>
 */
@SuppressWarnings("all")
public class PluginJvmModelInferrer extends AbstractModelInferrer {
  /**
   * a builder API to programmatically create Jvm elements
   * in readable way.
   */
  @Inject
  @Extension
  private JvmTypesBuilder _jvmTypesBuilder;
  
  @Inject
  @Extension
  private IQualifiedNameProvider _iQualifiedNameProvider;
  
  protected void _infer(final Model element, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPrelinkingPhase) {
    QualifiedName _fullyQualifiedName = this._iQualifiedNameProvider.getFullyQualifiedName(element);
    JvmGenericType _class = this._jvmTypesBuilder.toClass(element, _fullyQualifiedName);
    IPostIndexingInitializing<JvmGenericType> _accept = acceptor.<JvmGenericType>accept(_class);
    final Procedure1<JvmGenericType> _function = new Procedure1<JvmGenericType>() {
        public void apply(final JvmGenericType it) {
          String _documentation = PluginJvmModelInferrer.this._jvmTypesBuilder.getDocumentation(element);
          PluginJvmModelInferrer.this._jvmTypesBuilder.setDocumentation(it, _documentation);
          JvmTypeReference _superType = element.getSuperType();
          boolean _notEquals = (!Objects.equal(_superType, null));
          if (_notEquals) {
            EList<JvmTypeReference> _superTypes = it.getSuperTypes();
            JvmTypeReference _superType_1 = element.getSuperType();
            JvmTypeReference _cloneWithProxies = PluginJvmModelInferrer.this._jvmTypesBuilder.cloneWithProxies(_superType_1);
            PluginJvmModelInferrer.this._jvmTypesBuilder.<JvmTypeReference>operator_add(_superTypes, _cloneWithProxies);
          }
          EList<Feature> _features = element.getFeatures();
          for (final Feature feature : _features) {
            boolean _matched = false;
            if (!_matched) {
              if (feature instanceof Property) {
                final Property _property = (Property)feature;
                _matched=true;
                EList<JvmMember> _members = it.getMembers();
                String _name = ((Feature)_property).getName();
                JvmTypeReference _type = ((Feature)_property).getType();
                JvmField _field = PluginJvmModelInferrer.this._jvmTypesBuilder.toField(((Feature)_property), _name, _type);
                PluginJvmModelInferrer.this._jvmTypesBuilder.<JvmMember>operator_add(_members, _field);
                EList<JvmMember> _members_1 = it.getMembers();
                String _name_1 = ((Feature)_property).getName();
                JvmTypeReference _type_1 = ((Feature)_property).getType();
                JvmOperation _getter = PluginJvmModelInferrer.this._jvmTypesBuilder.toGetter(((Feature)_property), _name_1, _type_1);
                PluginJvmModelInferrer.this._jvmTypesBuilder.<JvmMember>operator_add(_members_1, _getter);
                EList<JvmMember> _members_2 = it.getMembers();
                String _name_2 = ((Feature)_property).getName();
                JvmTypeReference _type_2 = ((Feature)_property).getType();
                JvmOperation _setter = PluginJvmModelInferrer.this._jvmTypesBuilder.toSetter(((Feature)_property), _name_2, _type_2);
                PluginJvmModelInferrer.this._jvmTypesBuilder.<JvmMember>operator_add(_members_2, _setter);
              }
            }
            if (!_matched) {
              if (feature instanceof Method) {
                final Method _method = (Method)feature;
                _matched=true;
                EList<JvmMember> _members = it.getMembers();
                String _name = _method.getName();
                JvmTypeReference _type = _method.getType();
                final Procedure1<JvmOperation> _function = new Procedure1<JvmOperation>() {
                    public void apply(final JvmOperation it) {
                      String _documentation = PluginJvmModelInferrer.this._jvmTypesBuilder.getDocumentation(_method);
                      PluginJvmModelInferrer.this._jvmTypesBuilder.setDocumentation(it, _documentation);
                      EList<JvmFormalParameter> _params = _method.getParams();
                      for (final JvmFormalParameter p : _params) {
                        EList<JvmFormalParameter> _parameters = it.getParameters();
                        String _name = p.getName();
                        JvmTypeReference _parameterType = p.getParameterType();
                        JvmFormalParameter _parameter = PluginJvmModelInferrer.this._jvmTypesBuilder.toParameter(p, _name, _parameterType);
                        PluginJvmModelInferrer.this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
                      }
                    }
                  };
                JvmOperation _method_1 = PluginJvmModelInferrer.this._jvmTypesBuilder.toMethod(_method, _name, _type, _function);
                PluginJvmModelInferrer.this._jvmTypesBuilder.<JvmMember>operator_add(_members, _method_1);
              }
            }
          }
        }
      };
    _accept.initializeLater(_function);
  }
  
  protected void _infer(final Database element, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPrelinkingPhase) {
    QualifiedName _fullyQualifiedName = this._iQualifiedNameProvider.getFullyQualifiedName(element);
    JvmGenericType _class = this._jvmTypesBuilder.toClass(element, _fullyQualifiedName);
    IPostIndexingInitializing<JvmGenericType> _accept = acceptor.<JvmGenericType>accept(_class);
    final Procedure1<JvmGenericType> _function = new Procedure1<JvmGenericType>() {
        public void apply(final JvmGenericType it) {
          String _documentation = PluginJvmModelInferrer.this._jvmTypesBuilder.getDocumentation(element);
          PluginJvmModelInferrer.this._jvmTypesBuilder.setDocumentation(it, _documentation);
          JvmTypeReference _superType = element.getSuperType();
          boolean _notEquals = (!Objects.equal(_superType, null));
          if (_notEquals) {
            EList<JvmTypeReference> _superTypes = it.getSuperTypes();
            JvmTypeReference _superType_1 = element.getSuperType();
            JvmTypeReference _cloneWithProxies = PluginJvmModelInferrer.this._jvmTypesBuilder.cloneWithProxies(_superType_1);
            PluginJvmModelInferrer.this._jvmTypesBuilder.<JvmTypeReference>operator_add(_superTypes, _cloneWithProxies);
          }
          EList<Feature> _features = element.getFeatures();
          for (final Feature feature : _features) {
            boolean _matched = false;
            if (!_matched) {
              if (feature instanceof Property) {
                final Property _property = (Property)feature;
                _matched=true;
                EList<JvmMember> _members = it.getMembers();
                String _name = ((Feature)_property).getName();
                JvmTypeReference _type = ((Feature)_property).getType();
                JvmField _field = PluginJvmModelInferrer.this._jvmTypesBuilder.toField(((Feature)_property), _name, _type);
                PluginJvmModelInferrer.this._jvmTypesBuilder.<JvmMember>operator_add(_members, _field);
                EList<JvmMember> _members_1 = it.getMembers();
                String _name_1 = ((Feature)_property).getName();
                JvmTypeReference _type_1 = ((Feature)_property).getType();
                JvmOperation _getter = PluginJvmModelInferrer.this._jvmTypesBuilder.toGetter(((Feature)_property), _name_1, _type_1);
                PluginJvmModelInferrer.this._jvmTypesBuilder.<JvmMember>operator_add(_members_1, _getter);
                EList<JvmMember> _members_2 = it.getMembers();
                String _name_2 = ((Feature)_property).getName();
                JvmTypeReference _type_2 = ((Feature)_property).getType();
                JvmOperation _setter = PluginJvmModelInferrer.this._jvmTypesBuilder.toSetter(((Feature)_property), _name_2, _type_2);
                PluginJvmModelInferrer.this._jvmTypesBuilder.<JvmMember>operator_add(_members_2, _setter);
              }
            }
            if (!_matched) {
              if (feature instanceof Method) {
                final Method _method = (Method)feature;
                _matched=true;
                EList<JvmMember> _members = it.getMembers();
                String _name = _method.getName();
                JvmTypeReference _type = _method.getType();
                final Procedure1<JvmOperation> _function = new Procedure1<JvmOperation>() {
                    public void apply(final JvmOperation it) {
                      String _documentation = PluginJvmModelInferrer.this._jvmTypesBuilder.getDocumentation(_method);
                      PluginJvmModelInferrer.this._jvmTypesBuilder.setDocumentation(it, _documentation);
                      EList<JvmFormalParameter> _params = _method.getParams();
                      for (final JvmFormalParameter p : _params) {
                        EList<JvmFormalParameter> _parameters = it.getParameters();
                        String _name = p.getName();
                        JvmTypeReference _parameterType = p.getParameterType();
                        JvmFormalParameter _parameter = PluginJvmModelInferrer.this._jvmTypesBuilder.toParameter(p, _name, _parameterType);
                        PluginJvmModelInferrer.this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
                      }
                    }
                  };
                JvmOperation _method_1 = PluginJvmModelInferrer.this._jvmTypesBuilder.toMethod(_method, _name, _type, _function);
                PluginJvmModelInferrer.this._jvmTypesBuilder.<JvmMember>operator_add(_members, _method_1);
              }
            }
          }
        }
      };
    _accept.initializeLater(_function);
  }
  
  protected void _infer(final Controller element, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPrelinkingPhase) {
    QualifiedName _fullyQualifiedName = this._iQualifiedNameProvider.getFullyQualifiedName(element);
    JvmGenericType _class = this._jvmTypesBuilder.toClass(element, _fullyQualifiedName);
    IPostIndexingInitializing<JvmGenericType> _accept = acceptor.<JvmGenericType>accept(_class);
    final Procedure1<JvmGenericType> _function = new Procedure1<JvmGenericType>() {
        public void apply(final JvmGenericType it) {
          String _documentation = PluginJvmModelInferrer.this._jvmTypesBuilder.getDocumentation(element);
          PluginJvmModelInferrer.this._jvmTypesBuilder.setDocumentation(it, _documentation);
          JvmTypeReference _superType = element.getSuperType();
          boolean _notEquals = (!Objects.equal(_superType, null));
          if (_notEquals) {
            EList<JvmTypeReference> _superTypes = it.getSuperTypes();
            JvmTypeReference _superType_1 = element.getSuperType();
            JvmTypeReference _cloneWithProxies = PluginJvmModelInferrer.this._jvmTypesBuilder.cloneWithProxies(_superType_1);
            PluginJvmModelInferrer.this._jvmTypesBuilder.<JvmTypeReference>operator_add(_superTypes, _cloneWithProxies);
          }
          EList<Feature> _features = element.getFeatures();
          for (final Feature feature : _features) {
            boolean _matched = false;
            if (!_matched) {
              if (feature instanceof Property) {
                final Property _property = (Property)feature;
                _matched=true;
                EList<JvmMember> _members = it.getMembers();
                String _name = ((Feature)_property).getName();
                JvmTypeReference _type = ((Feature)_property).getType();
                JvmField _field = PluginJvmModelInferrer.this._jvmTypesBuilder.toField(((Feature)_property), _name, _type);
                PluginJvmModelInferrer.this._jvmTypesBuilder.<JvmMember>operator_add(_members, _field);
                EList<JvmMember> _members_1 = it.getMembers();
                String _name_1 = ((Feature)_property).getName();
                JvmTypeReference _type_1 = ((Feature)_property).getType();
                JvmOperation _getter = PluginJvmModelInferrer.this._jvmTypesBuilder.toGetter(((Feature)_property), _name_1, _type_1);
                PluginJvmModelInferrer.this._jvmTypesBuilder.<JvmMember>operator_add(_members_1, _getter);
                EList<JvmMember> _members_2 = it.getMembers();
                String _name_2 = ((Feature)_property).getName();
                JvmTypeReference _type_2 = ((Feature)_property).getType();
                JvmOperation _setter = PluginJvmModelInferrer.this._jvmTypesBuilder.toSetter(((Feature)_property), _name_2, _type_2);
                PluginJvmModelInferrer.this._jvmTypesBuilder.<JvmMember>operator_add(_members_2, _setter);
              }
            }
            if (!_matched) {
              if (feature instanceof Method) {
                final Method _method = (Method)feature;
                _matched=true;
                EList<JvmMember> _members = it.getMembers();
                String _name = _method.getName();
                JvmTypeReference _type = _method.getType();
                final Procedure1<JvmOperation> _function = new Procedure1<JvmOperation>() {
                    public void apply(final JvmOperation it) {
                      String _documentation = PluginJvmModelInferrer.this._jvmTypesBuilder.getDocumentation(_method);
                      PluginJvmModelInferrer.this._jvmTypesBuilder.setDocumentation(it, _documentation);
                      EList<JvmFormalParameter> _params = _method.getParams();
                      for (final JvmFormalParameter p : _params) {
                        EList<JvmFormalParameter> _parameters = it.getParameters();
                        String _name = p.getName();
                        JvmTypeReference _parameterType = p.getParameterType();
                        JvmFormalParameter _parameter = PluginJvmModelInferrer.this._jvmTypesBuilder.toParameter(p, _name, _parameterType);
                        PluginJvmModelInferrer.this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
                      }
                    }
                  };
                JvmOperation _method_1 = PluginJvmModelInferrer.this._jvmTypesBuilder.toMethod(_method, _name, _type, _function);
                PluginJvmModelInferrer.this._jvmTypesBuilder.<JvmMember>operator_add(_members, _method_1);
              }
            }
          }
        }
      };
    _accept.initializeLater(_function);
  }
  
  protected void _infer(final Enumeration element, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPrelinkingPhase) {
    QualifiedName _fullyQualifiedName = this._iQualifiedNameProvider.getFullyQualifiedName(element);
    JvmGenericType _class = this._jvmTypesBuilder.toClass(element, _fullyQualifiedName);
    acceptor.<JvmGenericType>accept(_class);
  }
  
  public void infer(final EObject element, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPrelinkingPhase) {
    if (element instanceof Controller) {
      _infer((Controller)element, acceptor, isPrelinkingPhase);
      return;
    } else if (element instanceof Database) {
      _infer((Database)element, acceptor, isPrelinkingPhase);
      return;
    } else if (element instanceof Enumeration) {
      _infer((Enumeration)element, acceptor, isPrelinkingPhase);
      return;
    } else if (element instanceof Model) {
      _infer((Model)element, acceptor, isPrelinkingPhase);
      return;
    } else if (element != null) {
      _infer(element, acceptor, isPrelinkingPhase);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(element, acceptor, isPrelinkingPhase).toString());
    }
  }
}
