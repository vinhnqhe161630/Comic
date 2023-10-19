/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerstory;

import dal.StoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import editString.Follow;
import model.Category;
import model.Story;

/**
 *
 * @author HP
 */
@WebServlet(name = "FollowServlet", urlPatterns = {"/follow"})
public class FollowServlet extends HttpServlet {

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
            out.println("<title>Servlet FollowServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FollowServlet at " + request.getContextPath() + "</h1>");
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
        String sid = request.getParameter("id");
        
        Follow f = new Follow();
        StoryDAO d = new StoryDAO();
Cookie[] cookies = request.getCookies();
        String txt = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("follow")) {
                    txt += cookie.getValue();
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        if (sid != null) {
            txt = f.removesidFollow(txt, sid);
        }
        Cookie c = new Cookie("follow", txt);
        c.setMaxAge(2 * 24 * 60 * 60);

        response.addCookie(c);
        List<Story> list = f.getFollowCookie(txt);
        //List Category
        List<Category> ca = d.getCategory(0);
        //Top view
        List<Story> topweek = d.topView();
        //new Comment
        List<Story> newcmt = d.newComment();
        request.setAttribute("topweek", topweek);
        request.setAttribute("newcmt", newcmt);
        request.setAttribute("txt", txt);
        request.setAttribute("list", list);
         request.setAttribute("ca", ca);
        request.getRequestDispatcher("follow.jsp").forward(request, response);
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
