package registrationBean;

public class registerBean implements java.io.Serializable {
    private String firstName = null;
    private String middleName = null;
    private String lastName = null;
    private String mobile = null;
    private String nid = null;
    private String wing = null;
    private String position = "1";
//    private int age = 0;
    private String userName = null;
    private String password = null;

    public registerBean() {
    }

    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }

    public String getFirstName(){
        return firstName;
    }
    public String getMiddleName(){
        return middleName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getMobile(){
        return mobile;
    }
    public String getNid(){
        return nid;
    }
    public String getWing(){
        return wing;
    }
    public String getPosition(){
        return position;
    }

    public void setUserName(String username){
        this.userName = username;
    }
    public void setPassword(String password){
        System.out.println("RegisterBean.........###########"+password);
        this.password = password;
    }

    public void setFirstName(String firstname){
        this.firstName = firstname;
    }
    public void setMiddleName(String middleName){
        this.middleName = middleName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    public void setNid(String nid){
        this.nid = nid;
    }
    public void setWing(String wing){
        this.wing = wing;
    }
    public void setPosition(String position){
        this.position = position;
    }

}