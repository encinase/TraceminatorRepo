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
	private String user;
	private String password;
	private String userName;
	private String sessionUser;
	private String sessionMember;
	private boolean remember;
	
	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public String getSessionMember() {
		try{
			sessionMember = SessionUtils.getMemberOf();
			return sessionMember;
		} catch (Exception e) {
			return null;
		}
	}

	public void setSessionMember(String sessionMember) {
		this.sessionMember = sessionMember;
	}

	public String getSessionUser() {
		try{
			sessionUser = SessionUtils.getUserName();
			return sessionUser;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public void setSessionUser(String sessionUser) {
		this.sessionUser = sessionUser;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String login() {
		
		// Si usa LDAP valido, sino al usuario ingresado lo dejo pasar sin importar la contraseña
//		String LDAPEnable = Utils.ObtenerProp(Global.DirWork + "traceminator.properties", "LDAP", "enable");
		String LDAPEnable = "false"; //TODO:  Sacar esto y poner correcto la validacion de LDAP
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if (LDAPEnable.equals("true")) {
//			String resultado = null;
//			try {
//				resultado = LoginLDAP.loginLDAP(LDAPUrl,domainName,user,password);
//				if ((resultado != null) && (resultado != "error")){
//					userName = resultado.substring(0,resultado.indexOf(";memberOf"));
//					String grupos = resultado.substring(resultado.indexOf("memberOf:")+9);
//					
//					// Para el grupo SOA le doy permiso de administrador, puede acceder a todas las paginas
//					if (grupos.contains("CN=SOA_Developers")){
//						session.setAttribute("memberOf", "administrador");
//					} else if (grupos.contains("CN=GGARE_AdminInfra")){ // al grupo de infra le doy permisos solo para hacer deploy
//						session.setAttribute("memberOf", "pasajes");
//					} else {  // si no tiene ninguno de esos 2 grupos lo busco en el archivo de usuario y si no está lo pongo rechazo
//						grupos = obtenerGrupoProfile(user);
//						session.setAttribute("memberOf", grupos);
//					}
//					
//					session.setAttribute("usernauserNameuario);
//					session.setAttribute("user", user);
//					render = false;
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				FacesContext.getCurrentInstance().addMessage("j_idt5:formLogin:passwordLogin", new FacesMessage("Usuario y/o password incorrectos."));
////				e.printStackTrace();
//			}
		} else {
			session.setAttribute("username", user);
			session.setAttribute("user", user);
			session.setAttribute("memberOf", "administrador");
		}
		
		Faces.getFlash().setKeepMessages(true);
//	    Messages.addGlobalInfo("Bienvenido al Traceminator!");
	    return "/index.xhtml?faces-redi=trrectue";
	}
	
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.removeAttribute("username");
		session.removeAttribute("user");
		session.invalidate();
		return "/login.xhtml?faces-redi=trrectue";
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
