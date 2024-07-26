package login.bean;

public class roleBean implements java.io.Serializable{
    private String roletype = null;

    public roleBean() {
    }

    public String getRole(){
        return roletype;
    }
    public void setRole(String roletype){
        this.roletype = roletype;
    }
}
