package com.example.demo3;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

//  [ID][LastName][FirstName][Age][Salary][Cell][Email]

@WebServlet(name = "LoadIndividualData", value = "/loadindividual-data")
public class LoadIndividualData extends HttpServlet {
    private ArrayList<Taxpayer> message = new ArrayList<>();
    private ArrayList<Taxpayer> messageTwo = new ArrayList<>();
    static Logger logger = Logger.getLogger("LoadIndividualData");
    public void init() {

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con =DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=testdb;user=user22;password=123;encrypt=true;trustServerCertificate=true");

            Statement stmt=con.createStatement();
            Statement stmt1=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from Persons1 where NID =1111");

            while(rs.next()) {
//  [ID][LastName][FirstName][Age][Salary][Cell][Email]
                Taxpayer ps = new Taxpayer(rs.getString(1),rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),rs.getInt(6),rs.getString(7));
                message.add(ps);

                logger.info(rs.getString(2));
            }


            messageTwo.sort((p1, p2)->(p1.income>p2.income)?-1:(p1.income<p2.income)?1:(p1.age>p2.age)?-1:(p1.age<p2.age)?1:(p1.firstName.compareTo(p2.firstName)));


            //System.out.println(message);
            /*while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            */
            con.close();
        }
        catch(Exception e){ System.out.println(e); }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
// sorted Data
        PrintWriter outTwo = response.getWriter();
        out.println("<br><br><br><br><br>");
        out.println("<h1>");
        out.println("Sorted Personal data");
        out.println("</h1>");

        out.println("<head>");
        out.println("<style>");
        out.println("table, th, td {");
        out.println("border: 1px solid black; }");
        out.println("</style>");
        out.println("</head>");

        out.println("<html><body>");
        out.println("<table");
        out.println("<tr>");
        out.println("<th>NID</th>");
        out.println("<th>First Name</th>");
        out.println("<th>Last Name</th>");
        out.println("<th>Age</th>");
        out.println("<th>Salary</th>");
        out.println("<th>TaxAmount</th>");
        out.println("<th>Contact</th>");
        out.println("<th>Remark</th>");
        out.println("</tr>");

        for(Taxpayer s1: messageTwo) {
            out.println("<tr>");
            out.println("<td>" + s1.NID +"</td>");
            out.println("<td>" + s1.firstName +"</td>");
            out.println("<td>" + s1.lastName +"</td>");
            out.println("<td>" + s1.age +"</td>");
            out.println("<td>" + s1.income +"</td>");
            out.println("<td>" + s1.taxamount +"</td>");
            out.println("<td>" + s1.contactnumber +"</td>");
            //out.println("<td>" + "<a href="+ "individualform.jsp" +">Edit</a>" +"</td>");
            out.println("<td><button type = 'button' onclick = 'individualform.jsp' name='bt" + s1.NID + "'" + "value=" + s1.NID + ">Update</button></td>");
            out.println("</tr>");

        }
        out.println("</table");
        out.println("</body></html>");


    }

    public void destroy() {
    }
}
