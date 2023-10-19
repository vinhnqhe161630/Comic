/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerlogin;

import model.Account;
import dal.AccountDAO;
import dal.StoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Category;

/**
 *
 * @author HP
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        request.setAttribute("ca", ca);
        request.getRequestDispatcher("login.jsp").forward(request, response);
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

        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String r = request.getParameter("rem");

        //create Cookie 
        Cookie cuser = new Cookie("cuser", user);
        Cookie cpass = new Cookie("cpass", pass);
        Cookie cr = new Cookie("crem", r);
        //choose
        if (r != null) {
            cuser.setMaxAge(60 * 60 * 24 * 7);
            cpass.setMaxAge(60 * 60 * 24 * 7);
            cr.setMaxAge(60 * 60 * 24 * 7);
         // no choose
        } else {
            cuser.setMaxAge(0);
            cpass.setMaxAge(0);
            cr.setMaxAge(0);
        }
        //Save browser
        response.addCookie(cuser);
        response.addCookie(cpass);
        response.addCookie(cr);
        
        
        AccountDAO d = new AccountDAO();
        Account a = d.checkAcc(user, pass);
        if(a!=null && a.getStatus()==0){
            //move in home page
            String alertMessage = "Your account is currently inactive";
            String redirectUrl = "home";

            String script = "<script type='text/javascript'>alert('" + alertMessage + "');window.location.href='" + redirectUrl + "';</script>";
            response.getWriter().println(script);
        }else{
           if (a == null) {
            //chua co account
            request.setAttribute("err", "Incorrect account");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            //co account roi
            //tao session
            HttpSession session = request.getSession();
            session.setAttribute("account", a);

            //move in home page
            
               String redirectUrl ="";
            if(a.getRole()==1){
            
             redirectUrl = "home";

            
            }else{
              redirectUrl = "admin";
            }
            String alertMessage = "Login successfully.";
            String script = "<script type='text/javascript'>alert('" + alertMessage + "');window.location.href='" + redirectUrl + "';</script>";
            response.getWriter().println(script);

        }  
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
