package login.bean;

public class LoginBean implements java.io.Serializable{
    private String userName = null;
    private String password = null;

    public LoginBean() {
    }

    public String getUserName(){
        return userName;
    }
    public void setUserName(String username){
        this.userName = username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        System.out.println("LoginBean: password.........###########"+password);
        this.password = password;
    }
}
