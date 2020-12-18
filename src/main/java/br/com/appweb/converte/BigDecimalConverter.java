package br.com.appweb.converte;

import java.math.BigDecimal;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("bigDecimalConverter")
public class BigDecimalConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,String value) {
    	//considera que tem um validador no front
        return new BigDecimal(value.replace(".", "").replace(",", "."));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,Object value) {
        return ((BigDecimal) value).toString();
    }
}
