/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerlogin;

import dal.AccountDAO;
import dal.StoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
@WebServlet(name = "SignUpServlet", urlPatterns = {"/signup"})
public class SignUpServlet extends HttpServlet {

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
            out.println("<title>Servlet SignUpServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpServlet at " + request.getContextPath() + "</h1>");
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
        
         List<Category>  ca= d.getCategory(0);
        request.setAttribute("ca", ca);
        request.getRequestDispatcher("signup.jsp").forward(request, response);
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
        String email = request.getParameter("email");
        user = user.trim();
        email = email.trim();

        AccountDAO d = new AccountDAO();
        if (user == null || pass == null || email == null) {
            request.setAttribute("mess", " Please re-input!");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
        try {
            String u = d.checkSignUp(user, "user");
            String e = d.checkSignUp(email, "email");
            if (d.checkSignUp(user, "user").equals("") == false || d.checkSignUp(email, "email").equals("") == false) {

                request.setAttribute("mess", u + e + " Please re-input!");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            } else {
                //insert account
              d.SignUp(user, pass, email, 1, null, null, 1);
                //Save account by session   
                HttpSession session = request.getSession();
                session.setAttribute("account", d.checkAcc(user, pass));

                  //move in home page
                String alertMessage = "Sign Up successfully.";
                String redirectUrl = "home";

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
