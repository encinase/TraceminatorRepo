package ar.com.traceminator.login;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import ar.com.traceminator.misc.Utils;
import ar.com.traceminator.variables.Global;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "/pages/*" , "/index.xhtml" })
public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();
			// si la sesión es valida verifico que el grupo al que pertenece pueda acceder a esa url
			if ((ses != null && ses.getAttribute("username") != null)) {
//				String grupo = obtenerGrupoProfile(ses.getAttribute("user").toString());
				String grupo = ses.getAttribute("memberOf").toString();
				
				// Si el grupo es administrador puede acceder a cualquier página sino valido
				switch(grupo){
					case "administrador" :
						chain.doFilter(request, response);
						break;
					case "pasajes" :
						if ((reqURI.contains("/pages/supervielle/pasajes")) || (reqURI.contains("/publico"))){
							chain.doFilter(request, response);
						} else {
							resp.sendRedirect(reqt.getContextPath() + "/index.xhtml");
						}
						break;
					case "rechazado" :
						if (reqURI.contains("/publico")) {
							chain.doFilter(request, response);
						} else {
							reqt.setAttribute("errorMessage", "No está autorizado para acceder a esta opción");
//							resp.sendRedirect(reqt.getContextPath() + "/menuEntrada.xhtml");
							reqt.getRequestDispatcher("/index.xhtml").forward(reqt, resp);
//							FacesContext.getCurrentInstance().addMessage("iconMenu", new FacesMessage("No está autorizado para acceder a esta opción"));
						}
						break;
				}
//				if (grupo.contains("administrador")){
//					chain.doFilter(request, response);
//				} else {
//					// por el momento si no es administrador, solo lo dejo ver las paginas de pasajes
//					if (reqURI.contains("/pages/supervielle/pasajes")){
//						chain.doFilter(request, response);
//					} else {
//						resp.sendRedirect(reqt.getContextPath() + "/menuEntrada.xhtml");
//					}
//				}
				
//				if (reqURI.indexOf("/Traceminator/menuEntrada.xhtml") >= 0
//				|| (ses != null && ses.getAttribute("username") != null)
//				|| reqURI.indexOf("/public/") >= 0
//				|| reqURI.contains("javax.faces.resource"))
			
//			chain.doFilter(request, response);
			} else {
				System.out.println(reqt.getContextPath());
				resp.sendRedirect(reqt.getContextPath() + "/login.xhtml");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void destroy() {

	}
	
}
