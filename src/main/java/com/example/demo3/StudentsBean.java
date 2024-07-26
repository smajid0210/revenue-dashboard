package com.example.demo3;

public class StudentsBean implements java.io.Serializable {
//    private String firstName = null;
//    private String lastName = null;
//    private int age = 0;

    private String userName = null;
    private String password = null;

    public StudentsBean() {
    }

    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
//    public String getLastName(){
//        return lastName;
//    }
//    public int getAge(){
//        return age;
//    }
    public void setUserName(String username){
        this.userName = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
//    public void setAge(Integer age) {
//        this.age=age;
//    }
}