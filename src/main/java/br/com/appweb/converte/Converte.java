package br.com.appweb.converte;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.appweb.entity.AppBaseEntity;

@FacesConverter(forClass = AppBaseEntity.class)
public class Converte implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		if (value != null && !value.isEmpty()) {
			return (AppBaseEntity) uiComponent.getAttributes().get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		if (value instanceof AppBaseEntity) {
			AppBaseEntity app = (AppBaseEntity) value;
			if (app != null && app instanceof AppBaseEntity && app.getId() != null) {
				uiComponent.getAttributes().put(app.getId().toString(), app);
				return app.getId().toString();
			}
		}
		return "";
	}
}