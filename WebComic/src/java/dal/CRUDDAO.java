/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Author;
import model.Category;
import model.Story;

/**
 *
 * @author HP
 */
public class CRUDDAO extends DBContext {

    public List<Story> getStManage(String name, String caid, String country, String user, int page) {
        List<Story> list = new ArrayList<>();
        String sql = "select *\n"
                + "from Stories s \n"
                + "inner join Authors a on (s.a_id = a.a_id)  "
                + " inner join [Translate] tr on tr.s_id = s.s_id\n"
                + "inner join [Account] ac on ac.ac_id = tr.ac_id where 1=1";

        if (caid != null) {
            if (caid.equals("0") == false) {
                sql = "select *\n"
                        + "   from Stories s \n"
                        + "   inner join Authors a on (s.a_id = a.a_id) \n"
                        + "inner join Category_stories  cs on cs.s_id = s.s_id\n"
                        + "  inner join [Translate] tr on tr.s_id = s.s_id\n"
                        + " inner join [Account] ac on ac.ac_id = tr.ac_id "
                        + "inner join Categories ca on ca.ca_id = cs.ca_id where ca.ca_id=" + caid;
            }
        }
        if (user != null) {
            sql += " and ac.[user] = '" + user + "'";
        }
        if (name != null) {
            sql += " and s.s_name like '%" + name + "%'";
        }
        if (country != null) {
            if (country.equals("0") == false) {
                sql += " and country=" + country;
            }
        }

        sql += " ORDER BY create_at DESC,[view]DESC\n"
                + "OFFSET " + (page - 1) * 12 + " ROWS\n"
                + "FETCH FIRST 12 ROWS Only";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Author a = new Author(rs.getInt("a_id"), rs.getString("a_name"));
                Story s = new Story(rs.getInt("s_id"), rs.getInt("view"), rs.getString("s_name"),
                        rs.getString("image"), rs.getString("create_at"),
                        rs.getInt("country"), rs.getInt("status"), a);
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public int getTotal(String name, String caid, String country, String user) {
        List<Story> list = new ArrayList<>();
        String sql = "select count(*)\n"
                + "from Stories s \n"
                + "inner join Authors a on (s.a_id = a.a_id)   inner join [Translate] tr on tr.s_id = s.s_id\n"
                + "					   inner join [Account] ac on ac.ac_id = tr.ac_id where 1=1";

        if (caid != null) {
            if (caid.equals("0") == false) {
                sql = "select count(*)\n"
                        + "   from Stories s \n"
                        + "   inner join Authors a on (s.a_id = a.a_id) \n"
                        + "inner join Category_stories  cs on cs.s_id = s.s_id\n"
                        + "inner join Categories ca on ca.ca_id = cs.ca_id   inner join [Translate] tr on tr.s_id = s.s_id\n"
                        + "					   inner join [Account] ac on ac.ac_id = tr.ac_id where ca.ca_id=" + caid;
            }
        }
        if (user != null) {
            sql += " and ac.[user] = '" + user + "'";
        }
        if (name != null) {
            sql += " and s.s_name like '%" + name + "%'";
        }
        if (country != null) {
            if (country.equals("0") == false) {
                sql += " and country=" + country;
            }
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 0;
    }

    public void deleteStory(String sid) {
        String sql = "DELETE FROM [dbo].[Stories]\n"
                + "WHERE [s_id] = " + sid;

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.executeUpdate();

        } catch (SQLException e) {

        }
    }

    public void deleteChapter(String sid) {
        String sql = "DELETE FROM [dbo].[Chapters]\n"
                + "WHERE [s_id] = " + sid;

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.executeUpdate();

        } catch (SQLException e) {

        }
    }

    public void deleteChapterByID(String chid) {
        String sql = "DELETE FROM [dbo].[Chapters]\n"
                + "WHERE [ch_id] = " + chid;

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.executeUpdate();

        } catch (SQLException e) {

        }
    }
    public void deleteTranslate(String sid) {
        String sql = "DELETE FROM [dbo].[Translate]\n"
                + "WHERE [s_id] = " + sid;

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.executeUpdate();

        } catch (SQLException e) {

        }
    }

    public void deleteCategory_Story(String sid) {
        String sql = "DELETE FROM [dbo].[Category_stories]\n"
                + "WHERE [s_id] = " + sid;

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.executeUpdate();

        } catch (SQLException e) {

        }
    }
     public void deleteTranslateBysid(String sid) {
        String sql = "delete [Translate] Where s_id " + sid;

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.executeUpdate();

        } catch (SQLException e) {

        }
    }
      public void deleteCommentBysid(String sid) {
        String sql = "delete [Comment1] Where s_id " + sid;

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.executeUpdate();

        } catch (SQLException e) {

        }
    }

    public void insertChap(int ch_number, String upchap_at, int sid) {
        String sql = "INSERT INTO [dbo].[Chapters]\n"
                + "           ([ch_number]\n"
                + "           ,[upchap_at]\n"
                + "           ,[s_id])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, ch_number);
            st.setString(2, upchap_at);
            st.setInt(3, sid);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("e");

        }
    }
     public void SetStUpdate_at(String date ,int sid) {
        String sql = "UPdate [dbo].[Stories]\n" +
"             \n" +
"              set       upchap_at = ?\n" +
"			   where  s_id = "+sid;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
           st.setString(1, date);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("e");

        }
    }

    public void insertCategory_Story(int sid, String caid) {
        String sql = "INSERT INTO [dbo].[Category_stories]\n"
                + "           ([s_id]\n"
                + "           ,[ca_id])\n"
                + "     VALUES\n"
                + "           (?," + caid + ")";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sid);

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);

        }
    }

    public void upchap_atST(int id, String currenttime) {
        String sql = "UPDATE [dbo].[Stories]\n"
                + "             SET [upchap_at] = ?\n"
                + "			 where s_id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, id);
            st.setString(2, currenttime);
            st.executeUpdate();

        } catch (SQLException e) {

        }

    }

    public void EditSt(int id, String s_name, int country) {
        String sql = "UPDATE [dbo].[Stories]\n"
                + "   SET [s_name] = ?\n"
                + "      ,[country] = ?\n"
                + "   \n"
                + " WHERE [s_id] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, s_name);
            st.setInt(2, country);
            st.setInt(3, id);

            st.executeUpdate();

        } catch (SQLException e) {

        }

    }

    public void insertStory(String sname, String img, String create_at, int country, int aid) {
        String sql = "INSERT INTO [dbo].[Stories]\n"
                + "           ([s_name],[image],\n"
                + "         \n"
                + "           [view],[create_at]\n"
                + "           ,[country]\n"
                + "           ,[a_id])\n"
                + "        \n"
                + "     VALUES\n"
                + "           (?,?,1,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, sname);
            st.setString(2, img);
            st.setString(3, create_at);
            st.setInt(4, country);
            st.setInt(5, aid);

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);

        }
    }

    public int getLastStID() {

        String sql = "select TOp 1 s_id from Stories \n"
                + "order by s_id DESC";

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

    public int getLastAuID() {

        String sql = "select TOp 1 a_id from Authors \n"
                + "order by a_id DESC";

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

    public List<Author> getAuthor() {
        List<Author> list = new ArrayList<>();
        String sql = "select * from Authors ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Author a = new Author(rs.getInt("a_id"), rs.getString("a_name"));

                list.add(a);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void insertAuthor(String aname) {
        String sql = "INSERT [dbo].[Authors] ([a_name]) VALUES (?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, aname);

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("e");

        }
    }
    public void insertTranslate(int sid,int acid) {
        String sql = "INSERT [dbo].[Translate] ([s_id],[ac_id]) VALUES (?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sid);
             st.setInt(2, acid);

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("e");

        }
    }

    public Story getStoriesByName(String sid, int country, int a_id) {

        String sql = "select *\n"
                + "from Stories s \n"
                + "inner join Authors a on (s.a_id = a.a_id) where 1=1";

        sql += " and s_name = ? and country = ? and s.a_id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, sid);
            st.setInt(2, country);
            st.setInt(3, a_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Author a = new Author(rs.getInt("a_id"), rs.getString("a_name"));
                Story s = new Story(rs.getInt("s_id"), rs.getInt("view"), rs.getString("s_name"),
                        rs.getString("image"), rs.getString("create_at"),
                        rs.getInt("country"), rs.getInt("status"), a);
                return s;
            }
        } catch (Exception e) {
        }

        return null;
    }

    public Author getAuthorByName(String name) {

        String sql = "Select * from Authors where a_name=?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Author a = new Author(rs.getInt("a_id"), rs.getString("a_name"));

                return a;
            }
        } catch (Exception e) {
        }

        return null;
    }

    public static void main(String[] args) {
        CRUDDAO cd = new CRUDDAO();
        List<Story> list = cd.getStManage(null, "1", null, "translator1", 1);
        for (Story story : list) {
            System.out.println(story.getS_name());
        }

        cd.deleteStory("36");

    }

}
