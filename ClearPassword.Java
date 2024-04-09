public class ClearPassword implements PasswordPair {
	
 private String Username ;
 private String Password;
 
 
 
 public ClearPassword(String Username, String Password){
	 this.Username = Username;
	 this.Password = Password;
	 
 }
 
 @Override
public boolean Verify(String Password){
	 if(Password.equals(this.Password))
		 return true;
	 else {
		 return false;
	 }
	
 }
/**
 * @return
 */
@Override
public String getUsername() {
	return Username;
}
/**
 * @param username
 */
@Override
public void setUsername(String username) {
	Username = username;
}

/**
 * @return
 */
@Override
public String getPassword() {
	return Password;
}
@Override
public void setPassword(String password) {
	Password = password;
}

/**
 * @param Username
 * @param Password
 * @return
 */
@Override
public void setPW(String currentPassword , String newPassword) {
	
	if(currentPassword.equals(Password) ) {
		Password = newPassword;
	}
	
	
	
}
@Override
public String toString() {
	return "<" + Username + "," + Password + ">";
}


 
 
 
		
	 
	 
}

