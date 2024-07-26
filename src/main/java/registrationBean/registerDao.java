package registrationBean;

import login.bean.ConnectionProvider;
import login.bean.LoginBean;
import login.bean.messageAlert;
import registrationBean.registerBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class registerDao {
    public registerDao() {
    }

    public static boolean saveDataRegister(registerBean bean) {
        boolean status = false;

        try {
            Connection con = ConnectionProvider.getCon();
//            PreparedStatement ps = con.prepareStatement("select * from user_auth where email=? and pass=?");
            PreparedStatement ps = con.prepareStatement("INSERT INTO user_auth (userid,password, role_type, first_name, last_name, middle_name,mobile,nid,wing, position) VALUES (?,?,?,?,?,?,?,?,?,?)");
            FixedData.setRegBean(bean); //To hold the registration data given by user.
            ps.setString(1, bean.getUserName());
            ps.setString(2, bean.getPassword());
            ps.setString(3, bean.getWing()); //role_type
            ps.setString(4, bean.getFirstName());
            ps.setString(5, bean.getMiddleName());
            ps.setString(6, bean.getLastName());
            ps.setString(7, bean.getMobile());
            ps.setString(8, bean.getNid());
            ps.setString(9, bean.getWing());
            FixedData fd = new FixedData();
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&& registerDao"+fd);
            String pos = bean.getPosition();
            int a = Integer.parseInt(pos)-1;
            System.out.println("position: "+a);
            ps.setString(10, fd.getPositionTable(a));

//            ResultSet rs = ps.executeQuery();
            int row =ps.executeUpdate();
            // rows affected
            System.out.println(row); //1
            status = true;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s: %s", e.getSQLState(), e.getMessage(),  e.getErrorCode());
            messageAlert.setRegistrationMsgID(1); //duplicate userid

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}
