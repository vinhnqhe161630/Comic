/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package CRUD;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author HP
 */
@WebServlet(name = "AddAccServlet", urlPatterns = {"/add"})
public class AddAccServlet extends HttpServlet {

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
            out.println("<title>Servlet AddAccServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddAccServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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

        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String email = request.getParameter("email");
        String role_raw = request.getParameter("role");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
         String gender_raw = request.getParameter("gender");
        AccountDAO ad = new AccountDAO();
        
     
     try {
           int role = Integer.parseInt(role_raw);
           int gender = Integer.parseInt(gender_raw);
             
           
            String u = ad.checkSignUp(user, "user");
            String e = ad.checkSignUp(email, "email");
            if (ad.checkSignUp(user, "user").equals("") == false || ad.checkSignUp(email, "email").equals("") == false) {

             String alertMessage = u+" "+e+" Add fail!";
               String redirectUrl = "manageAcc";
              
             
                String script = "<script type='text/javascript'>alert('" + alertMessage + "');window.location.href='" + redirectUrl + "';</script>";
                response.getWriter().println(script);
            
            } else {
                //insert account
               ad.SignUp(user, pass, email, role, fname, lname, gender);
                
              

                  //move in home page
                 String alertMessage = "ok";
                String redirectUrl = "manageAcc";

                String script = "<script type='text/javascript'>alert('" + alertMessage + "');window.location.href='" + redirectUrl + "';</script>";
                response.getWriter().println(script);
            }
        } catch (Exception e) {
        }
        
     
     
        
        
        
    
        
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
