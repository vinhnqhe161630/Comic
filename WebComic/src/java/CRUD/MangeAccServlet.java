/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package CRUD;

import dal.AccountDAO;
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
import model.Account;

/**
 *
 * @author HP
 */
@WebServlet(name="mangerAccServlet", urlPatterns={"/manageAcc"})
public class MangeAccServlet extends HttpServlet {
   
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
            out.println("<title>Servlet mangerAccServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet mangerAccServlet at " + request.getContextPath () + "</h1>");
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
        AccountDAO cd = new AccountDAO();
        
        List<Account> acc = cd.getAllAcc(null,null,null);
        
        request.setAttribute("acc", acc);
        request.getRequestDispatcher("manageAcc.jsp").forward(request, response);
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
      String user = request.getParameter("user");
      String role_raw = request.getParameter("role");
      String status = request.getParameter("status");
      
  
      AccountDAO cd = new AccountDAO();
      StoryDAO sd = new StoryDAO();
      if(role_raw.equals("3")){
          role_raw = null;
      }
      if(status.equals("3")){
      status=null;    
      }
     
     List<Account> acc = cd.getAllAcc(user, role_raw, status);
     
      request.setAttribute("acc", acc);
       request.setAttribute("role", role_raw);
        request.setAttribute("status", status);
        request.setAttribute("user", user);
       
        request.getRequestDispatcher("manageAcc.jsp").forward(request, response);
      
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
