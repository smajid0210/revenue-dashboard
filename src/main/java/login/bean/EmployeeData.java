package login.bean;

public class EmployeeData {
    private String firstName = null;
    private String middleName = null;
    private String lastName = null;
    private String mobile = null;
    private String nid = null;
    private String wing = null;
    private String position = null;
    //    private int age = 0;
    private String userName = null;
    private String password = null;
    private String roleType = null;

    EmployeeData(String id, String uid, String pw, String rType, String fName, String lName, String mName,  String mbl, String nid, String wing, String pos)
        {
            this.firstName = fName;
            this.middleName = mName;
            this.lastName = lName;
            this.userName = uid;
            this.password = pw;
            this.mobile = mbl;
            this.nid = nid;
            this.wing = wing;
            this.position = pos;
            this.roleType = rType;
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
    public String getRoleType(){
        return roleType;
    }
}
