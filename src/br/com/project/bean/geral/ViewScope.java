package br.com.project.bean.geral;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.FacesRequestAttributes;

public class ViewScope implements Scope, Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String VIEW_SCOPE_CALLBACKS = "viewScope.callBacks";

	/**
	 * M�todo respons�vel para retorna um objeto de scopo
	 */
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		Object instance = getViewMap().get(name);
		/*Se a inst�ncia n�o existir*/
		if (instance == null) {
			instance = objectFactory.getObject();
			getViewMap().put(name, instance);
		}
		
		return instance;
	}

	@Override
	public String getConversationId() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesRequestAttributes  facesRequestAttributes = new FacesRequestAttributes(facesContext);
		return facesRequestAttributes.getSessionId() + " - " + facesContext.getViewRoot().getViewId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void registerDestructionCallback(String name, Runnable runnable) {
		Map<String, Runnable> callBacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
		
		if (callBacks != null) {
			//callBacks.put(name, runnable);
			callBacks.put(VIEW_SCOPE_CALLBACKS, runnable);
		}
	}

	/**
	 * M�todo que remove um scopo
	 */
	@Override
	public Object remove(String name) {
		Object instance = getViewMap().remove(name);
		
		if (instance != null) {
			@SuppressWarnings("unchecked")
			Map<String, Runnable> callBacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);

			if (callBacks != null) {
				callBacks.remove(name);
			}
		}
		
		return instance;
	}

	@Override
	public Object resolveContextualObject(String name) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
		return facesRequestAttributes.resolveReference(name);
	}
	
	/**
	 * getViewRoot() Retorna o componente raiz que est� associado a esta solicita��o
	 * tamb�m chamada de (request) 
	 * getViewMap -> Retorna um Map que atua com a
	 * interface para o armazenamento de dados
	 * 
	 * @return
	 */
	private Map<String, Object> getViewMap() {
		return FacesContext.getCurrentInstance() != null ? // <- isso � um if tern�rio
				FacesContext.getCurrentInstance().getViewRoot().getViewMap() : new HashMap<String, Object>();//essa parte inicia um HasMap vazio
	}

}
