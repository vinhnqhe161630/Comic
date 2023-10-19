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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import model.Category;
import model.Chapter;
import model.Story;

/**
 *
 * @author HP
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

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
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
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
        
        
        List<Category> ca = d.getCategory(0);
        //Trending
        List<Story> st = d.getStories(0, null, null);

        //popular story
        List<Story> popularSt = d.popularStory();
        //Top view
        List<Story> topweek = d.topView();
        //new Comment
        List<Story> newcmt = d.newComment();

       
        LinkedHashMap<Story, List<Chapter>> trending = new LinkedHashMap<>();

        for (int i = 0; i < st.size(); i++) {

            trending.put(st.get(i), d.getChap(st.get(i).getS_id()));
        }
        LinkedHashMap<Story, List<Chapter>> popular = new LinkedHashMap<>();

        for (int i = 0; i < popularSt.size(); i++) {

            popular.put(popularSt.get(i), d.getChap(st.get(i).getS_id()));
        }
        request.setAttribute("ca", ca);
        request.setAttribute("st", trending);
        request.setAttribute("popular", popular);
        request.setAttribute("topweek", topweek);
        request.setAttribute("newcmt", newcmt);

       
        request.getRequestDispatcher("home.jsp").forward(request, response);
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
