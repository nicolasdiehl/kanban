package ldap;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class Ldap
{
 private String Host = null;
 private DirContext LdapContext = null;

 /**
  *  Constructor for the Ldap object.
  *  @param Hostname IP and port for LDAP server (ldaps://xx.xx.xx.xx:xxx)
  */
 public Ldap(String Hostname)
 {
	 this.Host = Hostname;
 }

  /**
  * Connect to LDAP Server.
  * @return true connection successful
  * @return false connection error
  */
 public boolean connect()
     throws NamingException
 {
	 try
     {
		 String conntype = "none";
	     Hashtable<String, String> environment = new Hashtable<String, String>();

	     environment.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
	     environment.put(Context.PROVIDER_URL,Host);
	     environment.put("java.naming.ldap.factory.socket", "test.MySSLSocketFactory");
	     environment.put(Context.SECURITY_AUTHENTICATION, conntype);

	     LdapContext = new InitialDirContext(environment);

	     System.out.println("Connection successful");

	     return true;
	  }
	  catch(Exception ex)
	  {
	     System.out.println("Ldap connect - " + ex);
	     return false;
	  }
 }

 /**
  * Disconnect from the server.
  */
 public void disconnect()
 {
     try
     {
     	if (LdapContext != null)
     	{
     		LdapContext.close();
     		LdapContext = null;
     	}
     }
     catch (NamingException ex)
     {
         System.out.println("Ldap disconnect - " + ex);
     }
 }

 /**
  * Filter the data in the ldap directory for the given search base.
  *
  * @param  searchBase   search directory (ou=accounts,dc=linuxmuster-net,dc=lokal)
  * @param  searchFilter filter this value from the base (only HEMS uid)
  */
 public boolean search(String searchBase, String searchFilter)
     throws NamingException
 {
     SearchControls searchcontrols = new SearchControls(SearchControls.SUBTREE_SCOPE,
							         1L, //count limit
							         0,  //time limit
							         null,//attributes (null = all)
							         false,// return object ?
							         false);// dereference links?

     NamingEnumeration<SearchResult> ne =  LdapContext.search(searchBase, "uid=" + searchFilter, searchcontrols);

     if (ne.hasMore())
     {
         SearchResult sr = (SearchResult) ne.next();
		 System.out.println(sr.getAttributes().get("uid"));
	 }
	 else
	 {
		 System.out.println(searchFilter +" not found on Ldap");
	 }
    return ne.hasMore();
 }

}


