package com.example.demo3;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Logger;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

//  [ID][LastName][FirstName][Age][Salary][Cell][Email]

@WebServlet(name = "listData", value = "/list-data")
public class ListData extends HttpServlet {
    private ArrayList<Taxpayer> IncomeList = new ArrayList<>();
    private ArrayList<Taxpayer> TaxList = new ArrayList<>();

    private String message;
    static Logger logger = Logger.getLogger("HelloServlet");
    public void init() {

        try{
            message = "";
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Connection con =DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=testdb;user=shahana;password=123;encrypt=true;trustServerCertificate=true");

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con =DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=testdb;user=user22;password=123;encrypt=true;trustServerCertificate=true");


            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from Persons1");

            while(rs.next()) {
                logger.info(rs.getString(1));
                Taxpayer ps = new Taxpayer(rs.getString(1),rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),rs.getInt(6),rs.getString(7));
                //Taxpayer ps = new Taxpayer(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),rs.getInt(6),rs.getString(7));

                //Taxpayer ps = new Taxpayer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));

                IncomeList.add(ps);

                logger.info(ps.firstName);
            }
            IncomeList.addAll(TaxList);

            Collections.sort(IncomeList, new Comparator<Taxpayer>(){
                public int compare(Taxpayer p1, Taxpayer p2)
                {
                    return p1.income - p2.income;
                }
            });

            Collections.sort(TaxList, new Comparator<Taxpayer>(){
                public int compare(Taxpayer p1, Taxpayer p2)
                {
                    return p1.taxamount - p2.taxamount;
                }
            });

            for(Taxpayer b:IncomeList){
                message += b.NID + " "+ b.firstName+" " + b.lastName+" " + b.age+" " + b.income+ " " + b.taxamount + "<br>";
            }

            con.close();
        }
        catch(Exception e){ System.out.println(e); }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<h1>");
        out.println("Raw Personal data");
        out.println("</h1>");

        out.println("<body>");
        out.println("<p>");
        //out.println("<br>" + message + "<br>");

        for(Taxpayer b:IncomeList){
            out.println(b.NID + " "+ b.firstName+" " + b.lastName+" " + b.age+" " + b.income+ " " + b.taxamount + "<br>");
        }

        out.println("<br>Person with Highest Income " + IncomeList.get(0).income + "<br>");
        out.println("Person with Lowest Income " + IncomeList.get(IncomeList.size()-1).income + "<br>");
        //out.println("Person with Highest Tax " + TaxList.get(0).taxamount + "<br>");
        //out.println("Person with Lowest Income " + TaxList.get(IncomeList.size()-1).taxamount + "<br>");

    }

    public void destroy(){
    }
}