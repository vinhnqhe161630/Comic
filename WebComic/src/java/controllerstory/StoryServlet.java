/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerstory;

import dal.CommentDAO;
import dal.StoryDAO;
import editString.EditDate;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import model.Account;
import model.Category;
import model.Chapter;
import model.Comment;
import editString.Follow;
import editString.History;
import model.Story;

/**
 *
 * @author HP
 */
@WebServlet(name = "StoryServlet", urlPatterns = {"/story"})
public class StoryServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StoryServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String s_id_raw = request.getParameter("id");
        String fo = request.getParameter("fo");
        StoryDAO d = new StoryDAO();
        Follow f = new Follow();
         CommentDAO cd =  new CommentDAO();
         EditDate ed = new EditDate();
         String currentTime  = ed.getCurrentTime();
      

        //
        try {
            int s_id = Integer.parseInt(s_id_raw);
            List<Story> st = d.getStories(s_id, null, null);
            List<Category> ca = d.getCategory(0);
            List<Category> st_ca = d.getCategory(s_id);
            List<Chapter> ch = d.getChap(s_id);
             //popular story
            List<Story> popularSt = d.popularStory();
            
            // List Comment
            List<Comment> listcmt = cd.getComment(s_id);
              LinkedHashMap<Comment,String> cmt=new LinkedHashMap<>();
              for (Comment comment : listcmt) {
              String create_at =   comment.getCreate_at().substring(0, 19);
              cmt.put(comment, ed.getDistanceDate(create_at,currentTime ));
                
            }

            //Save follow in cookie
            Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("follow")) {
                    txt += o.getValue();
                    o.setMaxAge(0);
                    response.addCookie(o);
                }
            }
        }
        if(fo!=null){
            if(fo.equals("1")){
        if (s_id_raw != null) {
            if (txt.isEmpty()) {
                txt = s_id_raw ;
            } else {
                txt = txt + "/" + s_id_raw ;
            }
        }
        }else{
               txt= f.removesidFollow(txt, s_id_raw);
            }
        }
       
        Cookie c = new Cookie("follow", txt);
        c.setMaxAge(2 * 24 * 60 * 60);

        response.addCookie(c);
            
            boolean isStoryincookie = f.isStoryinCfollow(txt, s_id_raw);
            request.setAttribute("isStoryincookie", isStoryincookie);
            request.setAttribute("ca", ca);
            request.setAttribute("stca", st_ca);
            request.setAttribute("chap", ch);
            request.setAttribute("cmt", cmt);
            request.setAttribute("story", st.get(0));
              request.setAttribute("popular", popularSt);
            request.getRequestDispatcher("story-details.jsp").forward(request, response);
            
        } catch (Exception e) {
            request.setAttribute("err", e);
            request.getRequestDispatcher("test.jsp").forward(request, response);
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
        String comment = request.getParameter("comment");
        try{
             Account acc = (Account) session.getAttribute("account");
        comment +=acc.getAc_id(); 
        }catch(Exception e){
            
            }
        
        request.setAttribute("txt", comment);
        request.getRequestDispatcher("test.jsp").forward(request, response);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
