/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Account;

/**
 *
 * @author HP
 */
public class AccountDAO extends DBContext {

    public Account checkAcc(String username, String pass) {
        String sql = "select*from Account \n"
                + "where  [user]= ? and [password]= ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Account(rs.getInt("ac_id"), username, pass, rs.getString("email"),
                        rs.getString("firstname"), rs.getString("Lastname"), rs.getInt("status"), rs.getInt("role"), rs.getInt("gender"));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    //Check user and email When sign up 
    public String checkSignUp(String s1, String s2) {
        String sql = "select*from Account \n"
                + "where  ";

        if (s2.equals("user")) {
            sql += "[user]= ? ";
        }
        if (s2.equals("email")) {
            sql += "[email]= ? ";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, s1);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account acc = new Account(rs.getInt("ac_id"), s1, rs.getString("password"), rs.getString("email"), rs.getString("firstname"), rs.getString("Lastname"), rs.getInt("status"), rs.getInt("role"), rs.getInt("gender"));
                if (acc != null) {
                    if (s2.equals("user")) {
                        return "Username exited! ";
                    }
                    if (s2.equals("email")) {
                        return "Email exited! ";
                    }
                }

            }
        } catch (SQLException e) {
        }
        return "";
    }

    public boolean SignUp(String user, String pass, String email, int role, String fname, String lname, int gender) {

        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([user]\n"
                + "           ,[password]"
                + "           ,[firstname]\n"
                + "           ,[lastname]\n"
                + "           ,[gender]\n"
                + "           ,[email] ,[role])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            st.setString(3, fname);
            st.setString(4, lname);
            st.setInt(5, gender);
            st.setString(6, email);
            st.setInt(7, role);

            st.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("e");
            return false;
        }

    }

    public boolean UpdateAcc(String fname, String lname, int gender, int ac_id) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET\n"
                + "      [firstname] = ?\n"
                + "      ,[lastname] =  ?\n"
                + "      ,[gender] = ?\n"
                + "     \n"
                + " WHERE ac_id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fname);
            st.setString(2, lname);

            st.setInt(3, gender);
            st.setInt(4, ac_id);

            st.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("e");
            return false;
        }

    }

    public void changepass(String user, String password) {
        String sql = " UPDATE [dbo].[Account]\n"
                + "                   SET [password] = ?\n"
                + "                WHERE [user]= ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, password);
            st.setString(2, user);

            st.executeUpdate();

        } catch (SQLException e) {

        }

    }

    public List<Account> getAllAcc(String user, String role, String status) {
        List<Account> list = new ArrayList<>();
        String sql = "select * from Account where 1=1 ";
        if (user != null) {
            sql += "and [user] like '%" + user + "%'";
        }
        if (role != null) {
            sql += " and role =" + role;
        }
        if (status != null) {
            sql += " and status =" + status;

        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getInt("ac_id"), rs.getString("user"), rs.getString("password"), rs.getString("email"),
                        rs.getString("firstname"), rs.getString("Lastname"), rs.getInt("status"), rs.getInt("role"), rs.getInt("gender"));
                list.add(a);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public void EditAcc(int id, String pass, int status, int role) {
        String sql = "  UPDATE [dbo].[Account]\n"
                + "	 set [password] = ?,\n"
                + "	 [role] = ?,\n"
                + "	 [status] = ?\n"
                + "	 where ac_id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pass);
            st.setInt(2, role);
            st.setInt(3, status);
            st.setInt(4, id);

            st.executeUpdate();

        } catch (SQLException e) {

        }

    }

    public void addNewAcc(String user, String pass, String email, int role) {
        String sql = "  INSERT INTO [dbo].[Account]\n"
                + "           ([user]\n"
                + "           ,[password]\n"
                + "           ,[email]        \n"
                + "           ,[role])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            st.setString(3, email);

            st.setInt(4, role);

            st.executeUpdate();

        } catch (SQLException e) {

        }

    }

    public List<Account> TopuserActive() {
        List<Account> list = new ArrayList<>();
        String sql = "  select Top 5 a.ac_id,a.email,a.[user],a.[password],a.gender,a.[status],a.[role],a.firstname,a.lastname ,count(c.ac_id) As num \n"
                + " from Account a\n"
                + " inner join Comment1 c on a.ac_id = c.ac_id\n"
                + " where a.role=1\n"
                + " group by a.ac_id,a.email,a.[user],a.[password],a.gender,a.[status],a.[role],a.firstname,a.lastname \n"
                + " order by num Desc";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getInt("ac_id"), rs.getString("user"), rs.getString("password"), rs.getString("email"),
                        rs.getString("firstname"), rs.getString("Lastname"), rs.getInt("status"), rs.getInt("role"), rs.getInt("gender"));
                list.add(a);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public static void main(String[] args) {
        AccountDAO ac = new AccountDAO();
        List<Account> a = ac.TopuserActive();
        for (Account account : a) {
            System.out.println(account.getFname());
        }

    }
}
