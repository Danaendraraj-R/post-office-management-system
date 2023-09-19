import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.*;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class BytesLoungeLoginModule implements LoginModule {

	private CallbackHandler handler;
	private Subject subject;
	private UserPrincipal userPrincipal;
	private RolePrincipal rolePrincipal;
	private String login;
	private List<String> userGroups;
					String username1="username";
					String password1="password";
					ArrayList std=new ArrayList();
					String name="name";
					String address="address";
					String pincode="pincode";
					String phonenumber="phonenumber";
					int m=0;
		Connection conn = null;
            Statement stmt = null;
			ResultSet rs;
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {

		handler = callbackHandler;
		this.subject = subject;
	}

	@Override
	public boolean login() throws LoginException {

		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("login");
		callbacks[1] = new PasswordCallback("password", true);

		try {
			handler.handle(callbacks);
			String  username = ((NameCallback) callbacks[0]).getName();
			String password = String.valueOf(((PasswordCallback) callbacks[1])
					.getPassword());
        try{   
		Class.forName("org.postgresql.Driver");
         conn = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/library",
            "postgres", "Rajdr039*");
			int allow = 0;
			stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM USERDETAILS;");
		}
		catch(Exception e)
		{
        throw new LoginException(e.getMessage());
		}
try
{
            while(rs.next()){
                 
                username1 = rs.getString("username");
				password1 = rs.getString("password");
                if(username1.equals(username) && password1.equals(password)){
				name = rs.getString("name");
			    address = rs.getString("address");
			    pincode = rs.getString("pincode");
				phonenumber = rs.getString("phonenumber");
				m++;
				break;
                }
            }
		            rs.close();
            stmt.close();
			conn.close();
}
		catch(Exception e)
		{
        throw new LoginException(e.getMessage());
		}
    if(m>0)
	{
		        login = username;
				userGroups = new ArrayList<String>();
				userGroups.add("user");
				return true;
	}

	// Here we validate the credentials against some
			// authentication/authorization provider.
			// It can be a Database, an external LDAP, a Web Service, etc.
			// For this tutorial we are just checking if user is "user123" and
			// password is "pass123"
			else if (username != null && username.equals("Admin") && password != null
					&& password.equals("Admin")) {
				login = username;
				userGroups = new ArrayList<String>();
				userGroups.add("admin");
				return true;
			}

			// If credentials are NOT OK we throw a LoginException
			throw new LoginException("Authentication failed");

		} catch (IOException e) {
			throw new LoginException(e.getMessage());
		} catch (UnsupportedCallbackException e) {
			throw new LoginException(e.getMessage());
		}

	}

	@Override
	public boolean commit() throws LoginException {

		userPrincipal = new UserPrincipal(login);
		subject.getPrincipals().add(userPrincipal);

		if (userGroups != null && userGroups.size() > 0) {
			for (String groupName : userGroups) {
				rolePrincipal = new RolePrincipal(groupName);
				subject.getPrincipals().add(rolePrincipal);
			}
		}

		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		subject.getPrincipals().remove(userPrincipal);
		subject.getPrincipals().remove(rolePrincipal);
		return true;
	}

}
