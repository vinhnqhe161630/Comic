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
import java.util.LinkedHashMap;
import java.util.List;
import model.Category;
import model.Chapter;
import model.Story;

/**
 *
 * @author HP
 */
@WebServlet(name = "TrendingStoryServlet", urlPatterns = {"/trending"})
public class TrendingStoryServlet extends HttpServlet {

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
            out.println("<title>Servlet TrendingStoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TrendingStoryServlet at " + request.getContextPath() + "</h1>");
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
        String country = request.getParameter("country");
        String page_raw = request.getParameter("page");
        StoryDAO d = new StoryDAO();

        try {
            //Top view
            List<Story> topweek = d.topView();
            //new Comment
            List<Story> newcmt = d.newComment();
            List<Story> st = d.getStories(0, country, page_raw);
            List<Category> ca = d.getCategory(0);
            LinkedHashMap<Story, List<Chapter>> linkedHashMap = new LinkedHashMap<>();

            for (int i = 0; i < st.size(); i++) {

                linkedHashMap.put(st.get(i), d.getChap(st.get(i).getS_id()));
            }
            int numberPage = d.getNumberPage(d.getTotalStory(0, country),12);
            request.setAttribute("ca", ca);
            request.setAttribute("topweek", topweek);
            request.setAttribute("newcmt", newcmt);
            request.setAttribute("country", country);
            request.setAttribute("stories", linkedHashMap);
            request.setAttribute("numberPage", numberPage);
            request.getRequestDispatcher("trending.jsp").forward(request, response);
        } catch (Exception e) {
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
