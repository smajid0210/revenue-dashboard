package com.example.demo3;


public class Taxpayer {

    int NID;
    String lastName;
    String firstName;
    int age;
    int income;
    int taxamount;
    String contactnumber;

    Taxpayer( String firstName, String lastName, int NID, int age, int income, int taxamount, String contactnumber)
    {
        this.NID = NID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.income = income;
        this.taxamount = taxamount;
        this.contactnumber = contactnumber;
    }

    Taxpayer(int anInt, String string, String string1, int anInt1) {
        this.NID = anInt;
        this.firstName = string;
        this.lastName = string1;
        this.age = anInt1;
    }
}
