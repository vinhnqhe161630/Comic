/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package CRUD;

import dal.CRUDDAO;
import dal.StoryDAO;
import editString.EditDate;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Author;
import model.Category;
import model.Story;

/**
 *
 * @author HP
 */
@WebServlet(name="AddStoryServlet", urlPatterns={"/addSt"})
public class AddStoryServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddStoryServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddStoryServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
          StoryDAO sd = new StoryDAO();
          List<Category> ca = sd.getCategory(0);
          request.setAttribute("ca", ca);
           request.getRequestDispatcher("addStory.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         String sname = request.getParameter("sname");
        String aname = request.getParameter("aname");
        String img = request.getParameter("img");
        String country_raw = request.getParameter("country");
        String[] category = request.getParameterValues("category");
        CRUDDAO cd = new CRUDDAO();
        StoryDAO sd = new StoryDAO();
        EditDate ed = new EditDate();
        String currentTime = ed.getCurrentTime();
         int a_id = 0;
          if (aname == null) {
            a_id = 10;
        }
          if(category.length==0){
              category[0] = "1";
          }
        try {
            int country = Integer.parseInt(country_raw);

            List<Author> au = cd.getAuthor();
            for (Author author : au) {
                if (author.getA_name().equals(aname)) {
                    a_id = author.getA_id();
                }
            }

            if (a_id == 0) {
             
                cd.insertAuthor(aname);
                  Author a = cd.getAuthorByName(aname);
                  a_id = a.hashCode();

            }

           //add story
            cd.insertStory(sname, img, currentTime, country, a_id);
            Story s = cd.getStoriesByName(sname,country,a_id);
            cd.insertChap(0, currentTime, s.getS_id());
            //add category-stiry
            for (String string : category) {
            cd.insertCategory_Story(s.getS_id(), string);
            }
            //add translate
            
              try{
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account"); 
        if(account!=null){
          cd.insertTranslate(s.getS_id(), account.getAc_id());
        }
          
        }catch(Exception e){
                }
            
             request.getRequestDispatcher("updateSt?id="+s.getS_id()).forward(request, response);
           
        } catch (Exception e) {
            System.out.println(e);
        }

        
       
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
