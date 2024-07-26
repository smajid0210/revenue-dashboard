<%--
  Created by IntelliJ IDEA.
  User: Sedna
  Date: 12/19/2022
  Time: 3:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>User Details</title>
</head>
<body >
<h3>Fill in the Form</h3>

<form action="FormData" method="post">
  <table>

    <tr>
      <td>First Name:</td>
      <td><input type="text" name="firstname" /></td>
      <td>Last Name:</td>
      <td><input type="text" name="lastname" /></td>
    </tr>
    <tr>
      <td>NID:</td>
      <td><input type="text" name="nid" /></td>
    </tr>
    <tr>
      <td>Age:</td>
      <td><input type="text" name="age" /></td>
    </tr>
    <tr>
      <td>Salary:</td>
      <td><input type="text" name="salary" /></td>
    </tr>
    <tr>
      <td>Tax Amount:</td>
      <td><input type="text" name="taxAmount" /></td>
    </tr>
    <tr>
      <td>Contact:</td>
      <td><input type="text" name="contact" /></td>
    </tr>
<%--    <tr>--%>
<%--      <td>Gender:</td>--%>
<%--      <td><input type="radio" name="gender" value="male" />Male--%>
<%--        <input type="radio" name="gender" value="female" />Female</td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--      <td>Select Programming Languages to learn:</td>--%>
<%--      <td><input type="checkbox" name="language" value="java" />Java--%>
<%--        <input type="checkbox" name="language" value="python" />Python--%>
<%--        <input type="checkbox" name="language" value="sql" />SQL--%>
<%--        <input type="checkbox" name="language" value="php" />PHP</td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--      <td>Select Course duration:</td>--%>
<%--      <td><select name="duration">--%>
<%--        <option value="3months">3 Months</option>--%>
<%--        <option value="6months">6 Months</option>--%>
<%--        <option value="9months">9 Months</option></select></td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--      <td>Anything else you want to share:</td>--%>
<%--      <td><textarea rows="5" cols="40" name="comment"></textarea></td>--%>
<%--    </tr>--%>

  </table>

  <input type="submit" value="Submit Details">

</form>

</body>
</html>