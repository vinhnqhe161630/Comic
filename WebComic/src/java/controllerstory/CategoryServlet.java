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
@WebServlet(name = "CategoryServlet", urlPatterns = {"/category"})
public class CategoryServlet extends HttpServlet {

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
            out.println("<title>Servlet CategoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryServlet at " + request.getContextPath() + "</h1>");
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

        String caid_raw = request.getParameter("id");
        String country = request.getParameter("country");
        String page_raw = request.getParameter("page");

        StoryDAO d = new StoryDAO();

        try {
            int caid = Integer.parseInt(caid_raw);
            //Top view
            List<Story> topweek = d.topView();
            //new Comment
            List<Story> newcmt = d.newComment();
            //List category
            List<Category> ca = d.getCategory(0);
            //List story
            List<Story> st = d.getStorybyCaID(caid, country, page_raw);

            LinkedHashMap<Story, List<Chapter>> story = new LinkedHashMap<>();

            for (int i = 0; i < st.size(); i++) {

                story.put(st.get(i), d.getChap(st.get(i).getS_id()));
            }
            int numberPage = d.getNumberPage(d.getTotalStory(caid, country),12);
            request.setAttribute("topweek", topweek);
            request.setAttribute("newcmt", newcmt);
            request.setAttribute("caid", caid);
            request.setAttribute("country", country);
            request.setAttribute("stories", story);
            request.setAttribute("ca", ca);
            request.setAttribute("numberPage", numberPage);
            request.getRequestDispatcher("category.jsp").forward(request, response);
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
