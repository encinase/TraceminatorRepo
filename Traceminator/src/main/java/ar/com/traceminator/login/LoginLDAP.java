package ar.com.traceminator.login;

import java.util.*;
import javax.naming.*;
import javax.naming.directory.*;

public class LoginLDAP {

    @SuppressWarnings("finally")
	public static String loginLDAP(String url, String domainName, String principalName, String password) throws Exception {
    	
    	String resultado = "";
    	// En el banco hay 2 dominions diferentes, algunos usuarios son @gscorp.ad y otros @supervielle.com.ar
    	// Primero pruebo con gscorp que es el nuevo, pero si no trae la info, conecto de nuevo con supervielle
    	String principalNameWithDomain = principalName + "@" + domainName;
    	if (domainName==null || "".equals(domainName)) {
            int delim = principalNameWithDomain.indexOf('@');
            domainName = principalNameWithDomain.substring(delim+1);
        }

        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        props.put(Context.PROVIDER_URL, url); 
        props.put(Context.REFERRAL, "follow"); 
        props.put(Context.SECURITY_PRINCIPAL, principalNameWithDomain); 
        props.put(Context.SECURITY_CREDENTIALS, password); // secretpwd
        if (url.toUpperCase().startsWith("LDAPS://")) {  // Si usa SSL usa la clase DummySSLSocketFactory que está en este mismo package, pero no lo probé nunca.
            props.put(Context.SECURITY_PROTOCOL, "ssl");
            props.put(Context.SECURITY_AUTHENTICATION, "simple");
            props.put("java.naming.ldap.factory.socket", "test.DummySSLSocketFactory");         
        }

        InitialDirContext context = new InitialDirContext(props);
        try {
            SearchControls ctrls = new SearchControls();
            ctrls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration<SearchResult> results = context.search(toDC(domainName),"(& (userPrincipalName="+principalNameWithDomain+")(objectClass=user))", ctrls);
            // Si no trajo la info del usuario con gscorp.ad intento con supervilele.com.ar
            if(!results.hasMore()) {
            	context.close();
            	principalNameWithDomain = principalName + "@supervielle.com.ar";
            	props.put(Context.SECURITY_PRINCIPAL, principalNameWithDomain);
            	context = new InitialDirContext(props);
            	results = null;
            	results = context.search(toDC(domainName),"(& (userPrincipalName="+principalNameWithDomain+")(objectClass=user))", ctrls);
            	if(!results.hasMore()) {
            		throw new AuthenticationException("Nombre de usuario no encontrado");
            	}
            }

            SearchResult result = results.next();
//        	System.out.println("Nombre de usuario: " + result.getNameInNamespace().substring(result.getNameInNamespace().indexOf("CN=")+3,result.getNameInNamespace().indexOf(",")) );
        	resultado = result.getNameInNamespace().substring(result.getNameInNamespace().indexOf("CN=")+3,result.getNameInNamespace().indexOf(","));
//            System.out.println("distinguisedName: " + result.getNameInNamespace() ); // CN=Firstname Lastname,OU=Mycity,DC=mydomain,DC=com

            Attribute memberOf = result.getAttributes().get("memberOf");
            if(memberOf!=null) {
            	resultado += ";memberOf:";
                for(int idx=0; idx<memberOf.size(); idx++) {
                	resultado += memberOf.get(idx).toString();
//                    System.out.println("memberOf: " + memberOf.get(idx).toString() ); // CN=Mygroup,CN=Users,DC=mydomain,DC=com
                    Attribute att = context.getAttributes(memberOf.get(idx).toString(), new String[]{"CN"}).get("CN");
//                    System.out.println( att.get().toString() ); //  CN part of groupname
                }
            }
        } finally {
            try { 
            	context.close(); 
            } catch(Exception ex) {
            	resultado = "error";
            }
            return resultado;
        }       
    }

    /**
     * Create "DC=sub,DC=mydomain,DC=com" string
     * @param domainName    sub.mydomain.com
     * @return
     */
    private static String toDC(String domainName) {
        StringBuilder buf = new StringBuilder();
        for (String token : domainName.split("\\.")) {
            if(token.length()==0) continue;
            if(buf.length()>0)  buf.append(",");
            buf.append("DC=").append(token);
        }
        return buf.toString();
    }

    private static Map<String,String> createParams(String[] args) {
        Map<String,String> params = new HashMap<String,String>();  
        for(String str : args) {
            int delim = str.indexOf('=');
            if (delim>0) params.put(str.substring(0, delim).trim(), str.substring(delim+1).trim());
            else if (delim==0) params.put("", str.substring(1).trim());
            else params.put(str, null);
        }
        return params;
    }

}