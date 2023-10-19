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
import jakarta.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import model.Account;
import model.Category;
import model.Chapter;
import model.Story;

/**
 *
 * @author HP
 */
@WebServlet(name = "ManageStoryServlet", urlPatterns = {"/manageSto"})
public class ManageStoryServlet extends HttpServlet {

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
            out.println("<title>Servlet ManageStoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageStoryServlet at " + request.getContextPath() + "</h1>");
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
        String searchname = request.getParameter("name");
        String caid = request.getParameter("category");
        String country = request.getParameter("country");
        String page_raw = request.getParameter("page");
        StoryDAO sd = new StoryDAO();
        CRUDDAO cd = new CRUDDAO();

         String username=null;
        try{
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
       
        if(account!=null){
            if(account.getRole()==2){
                username = account.getUser();
            }
        }
          
        }catch(Exception e){
          

  
                }

      

        int page = 1;
        try {
            if (page_raw != null) {
                page = Integer.parseInt(page_raw);
            }
        } catch (Exception e) {

        }

        List<Story> st = cd.getStManage(searchname, caid, country,username , page);

        List<Category> ca = sd.getCategory(0);
        LinkedHashMap<Story, List<Chapter>> list = new LinkedHashMap<>();

        for (int i = 0; i < st.size(); i++) {

            list.put(st.get(i), sd.getChap(st.get(i).getS_id()));
        }
        int numberPage = sd.getNumberPage(cd.getTotal(searchname, caid, country,username), 12);
        request.setAttribute("st", list);
        request.setAttribute("ca", ca);
        request.setAttribute("caid", caid);
        request.setAttribute("country", country);
        request.setAttribute("searchname", searchname);

        request.setAttribute("numberPage", numberPage);
        request.setAttribute("page", page);

        request.getRequestDispatcher("manageStory.jsp").forward(request, response);
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
        String searchname = request.getParameter("name");
        String caid = request.getParameter("category");
        String country = request.getParameter("country");
        String page_raw = request.getParameter("page");
        StoryDAO sd = new StoryDAO();
        CRUDDAO cd = new CRUDDAO();
        int page = 1;
        try {
            if (page_raw != null) {
                page = Integer.parseInt(page_raw);
            }
        } catch (Exception e) {

        }

        String username = null;

        List<Story> st = cd.getStManage(searchname, caid, country, username, page);

        List<Category> ca = sd.getCategory(0);
        LinkedHashMap<Story, List<Chapter>> list = new LinkedHashMap<>();

        for (int i = 0; i < st.size(); i++) {

            list.put(st.get(i), sd.getChap(st.get(i).getS_id()));
        }
        int numberPage = sd.getNumberPage(sd.getTotalStory(0, null), 12);
        request.setAttribute("st", list);
        request.setAttribute("ca", ca);
        request.setAttribute("numberPage", numberPage);
        request.setAttribute("page", page);

        request.getRequestDispatcher("manageStory.jsp").forward(request, response);

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
