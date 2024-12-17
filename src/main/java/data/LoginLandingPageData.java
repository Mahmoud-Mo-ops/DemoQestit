package data;

public class LoginLandingPageData {
    private String username;
    private String password;
    private String usernameWrong="eid";
    private String passwordWrong="no";
    // Default constructor (implicit if no other constructors exist)
    public LoginLandingPageData() {}
    
    // Getters and Setters
    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    //for error validation class
    public String getpasswordWrong() {
    	return passwordWrong;
    }
    public void setpasswordWrong(String passwordWrong) {
    	this.passwordWrong=passwordWrong;
    }
    
    public String getusernameWrong() {
    	return usernameWrong;
    }
    
    public void setusernameWrong(String usernameWrong) {
    	this.usernameWrong=usernameWrong;
    }
    
}
