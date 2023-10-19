/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import editString.EditDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Author;
import model.Category;
import model.Chapter;
import model.Story;
import model.StoryViews;

/**
 *
 * @author HP
 */
public class StoryDAO extends DBContext {

    public List<Story> getStories(int sid, String country, String page_raw) {
        List<Story> list = new ArrayList<>();
        String sql = "select *\n"
                + "from Stories s \n"
                + "inner join Authors a on (s.a_id = a.a_id) where 1=1";
        if (sid != 0) {
            sql += " and s.s_id=" + sid;
        }
        if (country != null) {
            sql += " and country=" + country;
        }
        if (page_raw == null) {
            page_raw = "1";
        }
        try {
            int page = Integer.parseInt(page_raw);

            sql += " ORDER BY upchap_at DESC,[view]DESC\n"
                    + "OFFSET " + (page - 1) * 12 + " ROWS\n"
                    + "FETCH FIRST 12 ROWS Only";

        } catch (Exception e) {

        }

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
        } catch (Exception e) {
        }

        return list;
    }

    public Story getStoriesbySID(int sid) {

        String sql = "select *\n"
                + "from Stories s \n"
                + "inner join Authors a on (s.a_id = a.a_id) where 1=1";
        if (sid != 0) {
            sql += " and s.s_id=" + sid;
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);

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
// Lấy list category 

    public List<Category> getCategory(int sid) {
        List<Category> list = new ArrayList<>();
        String sql = "select c.ca_id,c.ca_name\n"
                + "from Categories c\n"
                + "inner join Category_stories  cs on cs.ca_id = c.ca_id\n"
                + "inner join Stories s on s.s_id = cs.s_id where 1=1";

        if (sid != 0) {
            sql += " and s.s_id=" + sid;
        } else {
            sql = "select*from Categories";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category ca = new Category(rs.getInt("ca_id"), rs.getString("ca_name"));
                list.add(ca);
            }
        } catch (Exception e) {
        }

        return list;
    }

    //List strory loc the loai and dat nuoc
    public List<Story> getStorybyCaID(int ca_id, String country, String page_raw) {
        List<Story> list = new ArrayList<>();
        String sql = "select s.s_id,s.s_name,s.image,s.[view],s.create_at,s.country,s.a_id,s.status, au.a_name from Stories s\n"
                + "inner join Category_stories  cs on cs.s_id = s.s_id\n"
                + "inner join Authors  au  on au.a_id = s.a_id\n"
                + "inner join Categories ca on ca.ca_id = cs.ca_id where ca.ca_id=?";

        if (country != null) {
            sql = sql + " and s.country = " + country;
        }

        if (page_raw == null) {
            page_raw = "1";
        }
        try {
            int page = Integer.parseInt(page_raw);

            sql += " ORDER BY create_at DESC,[view]DESC\n"
                    + "OFFSET " + (page - 1) * 12 + " ROWS\n"
                    + "FETCH FIRST 12 ROWS Only";

        } catch (Exception e) {

        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, ca_id);

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

    //List chapter của 1 story
    public List<Chapter> getChap(int sid) {
        List<Chapter> list = new ArrayList<>();
        String sql = "select c.ch_id , c.ch_number,c.upchap_at,c.s_id\n"
                + "from [Chapters] c\n"
                + "Inner join [Stories] s on s.s_id = c.s_id \n"
                + "where 1=1 ";
        if (sid != 0) {
            sql += "and s.s_id=" + sid;
        }
        sql += "  order by c.ch_number ASC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Chapter c = new Chapter(rs.getInt("ch_id"), rs.getInt("ch_number"), rs.getString("upchap_at"), rs.getInt("s_id"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    //get 1 chap 
    public Chapter getChapbyId(int chapid) {
        List<Chapter> list = new ArrayList<>();
        String sql = "select * from Chapters where ch_id = " + chapid;

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Chapter c = new Chapter(rs.getInt("ch_id"), rs.getInt("ch_number"), rs.getString("upchap_at"), rs.getInt("s_id"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Story> searchByName(String name) {
        String sql = "select *\n"
                + "from Stories s \n"
                + "inner join Authors a on (s.a_id = a.a_id) where s.s_name like ?";
        List<Story> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + name + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Author a = new Author(rs.getInt("a_id"), rs.getString("a_name"));
                Story s = new Story(rs.getInt("s_id"), rs.getInt("view"), rs.getString("s_name"),
                        rs.getString("image"), rs.getString("create_at"),
                        rs.getInt("country"), rs.getInt("status"), a);
                list.add(s);
            }
        } catch (Exception e) {
        }

        return list;
    }

    public int getTotalStory(int caid, String country) {
        List<Story> list = new ArrayList<>();
        String sql = "select count(*) from Stories s";
        if (caid != 0) {
            sql = sql + "  inner join Category_stories  cs on cs.s_id = s.s_id\n"
                    + "             inner join Categories ca on ca.ca_id = cs.ca_id where ca.ca_id=" + caid;
        }
        if (caid == 0 && country != null) {
            sql = sql + " where s.country= " + country;
        }
        if (country != null) {
            sql = sql + " and s.country= " + country;
        }
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

    public int getNumberPage(int total, int numentries) {

        try {

            int countPage = 0;
            countPage = total / numentries;
            if (total % numentries != 0) {
                countPage++;
            }
            return countPage;

        } catch (Exception e) {
        }

        return 0;
    }

    //Update view
    public void Updateview(int sid) {
        String sql = "UPDATE Stories \n"
                + "SET [view] = [view] + 1\n"
                + "where s_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sid);

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("e");

        }
    }

    //popular story
    public List<Story> popularStory() {
        List<Story> list = new ArrayList<>();
        String sql = " select Top 12 s.s_id , s.s_name , s.[view] ,s.image ,s.create_at ,s.upchap_at,s.country,s.a_id,s.status ,a.a_name, COUNT(*) as num\n"
                + "  from Stories s\n"
                + "   inner join Authors a on (s.a_id = a.a_id)\n"
                + "     inner join Comment1 c on s.s_id = c.s_id where 1=1";

        sql += "  group by s.s_id , s.s_name , s.[view] ,s.image ,s.create_at ,s.upchap_at,s.country,s.a_id,s.status ,a.a_name\n"
                + "	 Order by s.[view]  DESC, num DESC\n";

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
        } catch (Exception e) {
        }

        return list;
    }

    //New Comment
    public List<Story> newComment() {
        List<Story> list = new ArrayList<>();
        String sql = " select TOP 4 *from Stories s \n"
                + "  inner join Authors a on (s.a_id = a.a_id)\n"
                + "  inner join Comment1 c on s.s_id = c.s_id\n"
                + "  Order by c.create_at DESC";

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
        } catch (Exception e) {
        }

        return list;
    }
    // TOp View

    public List<Story> topView() {
        List<Story> list = new ArrayList<>();
        String sql = " select TOP 5 *from Stories s \n"
                + "  inner join Authors a on (s.a_id = a.a_id)\n";

        sql += "  Order by s.[view]  DESC";
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
        } catch (Exception e) {
        }

        return list;
    }

    public StoryViews getStorieViews() {

        String sql = "select Top 1 * from [StoryViews] \n"
                + " order by view_date DESC";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                StoryViews s = new StoryViews(rs.getInt("sv_id"), rs.getInt("view_count"), rs.getString("view_date"));
                return s;
            }
        } catch (Exception e) {
        }

        return null;
    }

    public List<StoryViews> getListStorieViews() {
        List<StoryViews> list = new ArrayList<>();
        String sql = "select TOp 5 * from [StoryViews] \n"
                + " order by view_date ASC";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                StoryViews s = new StoryViews(rs.getInt("sv_id"), rs.getInt("view_count"), rs.getString("view_date"));
                list.add(s);
            }
        } catch (Exception e) {
        }

        return list;
    }

    public void insertStory_views(int views, String date) {
        String sql = "INSERT INTO [dbo].[StoryViews]\n"
                + "           ([view_date]\n"
                + "           ,[view_count])\n"
                + "     VALUES\n"
                + "           (?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, date);
            st.setInt(2, views);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("e");

        }
    }

    public int getTotalViews() {

        String sql = "select sum([view]) from Stories s";

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

    public static void main(String[] args) {

        StoryDAO d = new StoryDAO();
        EditDate ed = new EditDate();
       List<Category> ca = d.getCategory(0);
        //Trending
        List<Story> st = d.getStories(0, null, null);

        //popular story
        List<Story> popularSt = d.popularStory();
        //Top view
        List<Story> topweek = d.topView();
        //new Comment
        List<Story> newcmt = d.newComment();

       
        
        LinkedHashMap<Story, List<Chapter>> popular = new LinkedHashMap<>();

        for (int i = 0; i < popularSt.size(); i++) {

            popular.put(popularSt.get(i), d.getChap(st.get(i).getS_id()));
        }
        }
   
    }
