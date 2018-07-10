package ar.com.traceminator.login;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

//import ar.com.traceminator.misc.Utils;
import ar.com.traceminator.variables.Global;			

@ManagedBean(name="login")
@SessionScoped

public class Login implements Serializable  {
	
//	private String LDAPUrl = Utils.ObtenerProp(Global.DirWork + "traceminator.properties", "LDAP", "url");
//	private String domainName = Utils.ObtenerProp(Global.DirWork + "traceminator.properties", "LDAP", "domain");
	private String usuario;
	private String password;
	private String nombreUsuario;
	private String sessionUsuario;
	private boolean render;
	private boolean remember;
	
	public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
	
	public String getSessionUsuario() {
		try{
			sessionUsuario = SessionUtils.getUserName();
			return sessionUsuario;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public void setSessionUsuario(String sessionUsuario) {
		this.sessionUsuario = sessionUsuario;
	}
	
	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String iniciarSesion() {
		
		// Si usa LDAP valido, sino al usuario ingresado lo dejo pasar sin importar la contraseña
//		String LDAPEnable = Utils.ObtenerProp(Global.DirWork + "traceminator.properties", "LDAP", "enable");
		String LDAPEnable = "false"; //TODO:  Sacar esto y poner correcto la validacion de LDAP
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if (LDAPEnable.equals("true")) {
//			String resultado = null;
//			try {
//				resultado = LoginLDAP.loginLDAP(LDAPUrl,domainName,usuario,password);
//				if ((resultado != null) && (resultado != "error")){
//					nombreUsuario = resultado.substring(0,resultado.indexOf(";memberOf"));
//					String grupos = resultado.substring(resultado.indexOf("memberOf:")+9);
//					
//					// Para el grupo SOA le doy permiso de administrador, puede acceder a todas las paginas
//					if (grupos.contains("CN=SOA_Developers")){
//						session.setAttribute("memberOf", "administrador");
//					} else if (grupos.contains("CN=GGARE_AdminInfra")){ // al grupo de infra le doy permisos solo para hacer deploy
//						session.setAttribute("memberOf", "pasajes");
//					} else {  // si no tiene ninguno de esos 2 grupos lo busco en el archivo de usuario y si no está lo pongo rechazo
//						grupos = obtenerGrupoProfile(usuario);
//						session.setAttribute("memberOf", grupos);
//					}
//					
//					session.setAttribute("username", nombreUsuario);
//					session.setAttribute("user", usuario);
//					render = false;
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				FacesContext.getCurrentInstance().addMessage("j_idt5:formLogin:passwordLogin", new FacesMessage("Usuario y/o password incorrectos."));
////				e.printStackTrace();
//			}
		} else {
			session.setAttribute("username", usuario);
			session.setAttribute("user", usuario);
			session.setAttribute("memberOf", "administrador");
			render = false;
		}
		
		Faces.getFlash().setKeepMessages(true);
	    Messages.addGlobalInfo("Logged in successfully!");
	    return "/index.xhtml?faces-redirect=true";
	}
	
	public String cerrarSesion() {
		HttpSession session = SessionUtils.getSession();
		session.removeAttribute("username");
		session.removeAttribute("user");
		session.invalidate();
		render = false;
		return "/index.xhtml";
	}
	
	public String obtenerGrupoProfile(String usuario) throws FileNotFoundException, IOException{
		Properties prop = new Properties();
		prop.load(new FileInputStream(Global.DirWork + "/grupoxusuario.properties"));

		String grupo = prop.getProperty("usuario." + usuario);
		// si grupo es null le doy el perfil rechazado
		if (grupo==null) {
			grupo = "rechazado";
		}
		return grupo;
	}
}
