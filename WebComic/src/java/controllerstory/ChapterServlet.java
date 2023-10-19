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
import java.util.List;
import model.Category;
import editString.History;
import java.util.LinkedHashMap;
import model.Chapter;
import model.Comment;
import model.Story;
import model.StoryViews;

/**
 *
 * @author HP
 */
@WebServlet(name = "ChapterServlet", urlPatterns = {"/chapter"})
public class ChapterServlet extends HttpServlet {

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
            out.println("<title>Servlet ChapterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChapterServlet at " + request.getContextPath() + "</h1>");
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
        StoryDAO d = new StoryDAO();
        String sid_raw = request.getParameter("sid");
        String chapid_raw = request.getParameter("chapid");
        List<Category> ca = d.getCategory(0);
        CommentDAO cd = new CommentDAO();
        EditDate ed = new EditDate();
        String currentTime = ed.getCurrentTime();
        //Save history by cookie
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("history")) {
                    txt += o.getValue();
                    o.setMaxAge(0);
                    response.addCookie(o);
                }
            }
        }
        if ((sid_raw != null) && (chapid_raw != null)) {
            if (txt.isEmpty()) {
                txt = sid_raw + ":" + chapid_raw;
            } else {
                txt = txt + "/" + sid_raw + ":" + chapid_raw;
            }
        }
        History h = new History();
        txt = h.processString(txt);
        Cookie c = new Cookie("history", txt);
        c.setMaxAge(2 * 24 * 60 * 60);

        response.addCookie(c);
      
        
            int sid = Integer.parseInt(sid_raw);
           int chapid = Integer.parseInt(chapid_raw);
           
           //updateView
           
            d.Updateview(sid);
            StoryViews sv = d.getStorieViews();
            
            long ditanceDate = ed.getDistanceDate2(sv.getView_date().substring(0, 19), currentTime);
            if(ditanceDate>=7){
                d.insertStory_views(d.getTotalViews()-sv.getView_count(), currentTime);
            }
            
            
       
        Story s = d.getStoriesbySID(sid);
        Chapter ch = d.getChapbyId(chapid);
        List<Chapter> listch = d.getChap(sid);
        List<Comment> listcmt = cd.getComment(sid);
        LinkedHashMap<Comment, String> cmt = new LinkedHashMap<>();
        for (Comment comment : listcmt) {
            String create_at = comment.getCreate_at().substring(0, 19);
            cmt.put(comment, ed.getDistanceDate(create_at, currentTime));

        }
  request.setAttribute("cmt", cmt);
    request.setAttribute("listch", listch);
        request.setAttribute("ca", ca);
        request.setAttribute("s", s);
        request.setAttribute("ch", ch);
        request.getRequestDispatcher("chapter.jsp").forward(request, response);
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
        processRequest(request, response);
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
