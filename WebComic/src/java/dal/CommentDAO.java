/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Account;
import model.Comment;

/**
 *
 * @author HP
 */
public class CommentDAO extends DBContext {

    public List<Comment> getComment(int sid) {
        List<Comment> list = new ArrayList<>();
        String sql = "select* from [Comment1] c\n"
                + "inner join [Stories] s on s.s_id = c.s_id\n"
                + "inner join [Account] a on a.ac_id = c.ac_id\n"
                + "where s.s_id=? and s.status=1 order by c.[create_at] DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sid);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getInt("ac_id"), rs.getString("user"),
                        rs.getString("password"), rs.getString("email"),rs.getString("firstname"),
                        rs.getString("Lastname"), rs.getInt("status"), rs.getInt("role"),rs.getInt("gender"));

                Comment c = new Comment(rs.getInt("commentid"), rs.getString("content"), rs.getString("create_at"), sid, a);
                list.add(c);
            }
        } catch (SQLException e) {
        }

        return list;

    }
     public List<Comment> getComment2() {
        List<Comment> list = new ArrayList<>();
        String sql = "select TOp 5 * from [Comment1] c\n"
                + "inner join [Stories] s on s.s_id = c.s_id\n"
                + "inner join [Account] a on a.ac_id = c.ac_id\n"
                + "where s.status=1 order by c.[create_at] DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
          

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getInt("ac_id"), rs.getString("user"),
                        rs.getString("password"), rs.getString("email"),rs.getString("firstname"),
                        rs.getString("Lastname"), rs.getInt("status"), rs.getInt("role"),rs.getInt("gender"));

                Comment c = new Comment(rs.getInt("commentid"), rs.getString("content"), rs.getString("create_at"), rs.getInt("s_id"), a);
                list.add(c);
            }
        } catch (SQLException e) {
        }

        return list;

    }

    public void addNewComment(int sid, int ac_id, String comment) {
        String sql = "INSERT INTO Comment1(content, s_id, ac_id)\n"
                + "VALUES (?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, comment);
            st.setInt(2, sid);
            st.setInt(3, ac_id);

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("e");

        }
    }
      public int getTotalCommentByAcid(int acid) {

        String sql = "select count(c.ac_id) As num \n" +
"               from Account a\n" +
"              inner join Comment1 c on a.ac_id = c.ac_id\n" +
"                 where a.role=1 and a.ac_id = ?\n" +
"             group by a.ac_id,a.email,a.[user],a.[password],a.gender,a.[status],a.[role],a.firstname,a.lastname \n" +
"              order by num Desc";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, acid);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;

    }


    public static void main(String[] args) {
        CommentDAO cd = new CommentDAO();
        AccountDAO acc = new AccountDAO();
        List<Account> listuser = acc.TopuserActive();
        
        LinkedHashMap<Account, Integer> topuser = new LinkedHashMap<>();
        for (Account ac : listuser) {
            topuser.put(ac, cd.getTotalCommentByAcid(ac.getAc_id()));
           
           

        }
        for (Map.Entry<Account, Integer> entry : topuser.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(val);
            
        }

    }
}
