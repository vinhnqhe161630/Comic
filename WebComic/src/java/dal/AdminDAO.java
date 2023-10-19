/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import com.sun.xml.internal.ws.util.xml.CDATA;
import editString.EditDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Account;

/**
 *
 * @author HP
 */
public class AdminDAO extends DBContext {

    public List<Account> getTranlator() {
        List<Account> list = new ArrayList<>();
        String sql = "select*from Account \n"
                + "where  role=2";
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
    public int getTotalTrans() {

        String sql = "select count(*) from Account \n"
                + "where  role=2";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
     public int getTotaUser() {

        String sql = "select count(*) from Account \n"
                + "where  role=1";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                 return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public double getTotalStbyTrans(int ac_id) {

        String sql = "select count(*) from Stories s\n"
                + "inner join [Translate] tr on tr.s_id = s.s_id\n"
                + " inner join [Account] ac on ac.ac_id = tr.ac_id \n"
                + " where ac.ac_id = " + ac_id;

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public double getPercentTrans(int ac_id) {
        StoryDAO sd = new StoryDAO();
        AdminDAO ad = new AdminDAO();
        double totalSt = sd.getTotalStory(0, null);
        double st = ad.getTotalStbyTrans(ac_id);

        return (st * 100) / totalSt;
    }

    public static void main(String[] args) {
        AdminDAO ad = new AdminDAO();
        StoryDAO sd = new StoryDAO();
             List<Account> listacc  = ad.getTranlator();
         LinkedHashMap<String, Double> chartTrans = new LinkedHashMap<>();
         for (Account account : listacc) {
            chartTrans.put(account.getFname()+" "+account.getLname(), ad.getPercentTrans(account.getAc_id()));
        }
         
         for (Map.Entry<String, Double> entry : chartTrans.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
             System.out.println(val);
            
        }


    }
}
