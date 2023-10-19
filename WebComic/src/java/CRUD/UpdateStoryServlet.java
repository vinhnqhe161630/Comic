/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package CRUD;

import dal.CRUDDAO;
import dal.StoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Category;
import model.Chapter;
import model.Story;

/**
 *
 * @author HP
 */
@WebServlet(name = "UpdateStoryServlet", urlPatterns = {"/updateSt"})
public class UpdateStoryServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateStoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateStoryServlet at " + request.getContextPath() + "</h1>");
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
        String sid_raw = request.getParameter("id");
        StoryDAO sd = new StoryDAO();
        try {
            int sid = Integer.parseInt(sid_raw);
            Story s = sd.getStories(sid, null, null).get(0);
            List<Chapter> ch = sd.getChap(sid);
            List<Category> listca = sd.getCategory(0);
            List<Category> listcast = sd.getCategory(sid);
            request.setAttribute("s", s);
            request.setAttribute("ch", ch);
            request.setAttribute("ca", listca);
            request.setAttribute("cast", listcast);
            
            request.getRequestDispatcher("updateStory.jsp").forward(request, response);
            
        } catch (Exception e) {
            request.setAttribute("s", e);
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
        String sid_raw = request.getParameter("id");
        String sname = request.getParameter("sname");
        String aname = request.getParameter("aname");
        String country_raw = request.getParameter("country");
        String[] category = request.getParameterValues("category");
        CRUDDAO cd = new CRUDDAO();
        StoryDAO sd = new StoryDAO();
        try {
            int sid = Integer.parseInt(sid_raw);
            int country = Integer.parseInt(country_raw);
            
            cd.EditSt(sid, sname, country);
           cd.deleteCategory_Story(sid_raw);
            for (String caid : category) {
             
                cd.insertCategory_Story(sid, caid);
            }
            
        } catch (Exception e) {
        }
        
         response.sendRedirect("updateSt?id="+sid_raw); 
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
