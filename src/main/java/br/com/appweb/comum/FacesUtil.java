package br.com.appweb.comum;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ApplicationScoped
public class FacesUtil implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public static boolean isPostback() {
		return FacesContext.getCurrentInstance().isPostback();
	}

	public static boolean isNotPostback() {
		return !isPostback();
	}

	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}
	
	public static void addErrorMessageFatal(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, message, message));
	}
	
	public static void addInfoMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}
	
	public static void addInfoMessageWarn(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, message, message));
	}
	
	public static void addInfoMessageWarnLogin() {
		String message = "Usuário e Senha Invalidos";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, message, message));
	}
	
	public static void addInfoMessageInf() {
		String message = "Registro gravado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}
	
	public static void addInfoMessageRemovido() {
		String message = "Registro removido com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}
	
	public static void addInfoMessageException(Exception e) {
		if (e.getMessage().contains("ConstraintViolationException")) {
			FacesUtil.addInfoMessageWarn("Registro não pode ser removido, porque está sendo usado em outro lugar no sistema.");	
		}else {
			FacesUtil.addErrorMessage("Erro "+e.getMessage());
		}
	}
	
	public static void redirecionarPagina(String pagina) {
        FacesContext context = FacesContext.getCurrentInstance();
        String url = context.getExternalContext().getRequestContextPath();
        try {
            context.getExternalContext().redirect(url + "/" + pagina);
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
