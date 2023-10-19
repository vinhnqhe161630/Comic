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
@WebServlet(name = "ChangepassServlet", urlPatterns = {"/change"})
public class ChangepassServlet extends HttpServlet {

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
            out.println("<title>Servlet ChangepassServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangepassServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("changepass.jsp").forward(request, response);

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
        String cupass = request.getParameter("cupass");
        String newpass = request.getParameter("newpass");
        String copass = request.getParameter("copass");
        AccountDAO d = new AccountDAO();
        if (user == null || newpass == null || copass == null || cupass == null) {
            request.setAttribute("mess", " Please re-input!");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
        if (copass.equals(newpass) == false) {
            request.setAttribute("mess", "Please re-enter the password to confirm."
                    + " The password must match the password you entered above ");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
        if (d.checkAcc(user, cupass) == null) {
            request.setAttribute("mess", "Incorect account! Please re-input!");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
        try {

            //change pass
            d.changepass(user, newpass);

            //Save account by session
            HttpSession session = request.getSession();
            session.setAttribute("account", d.checkAcc(user, newpass));
            
              //move in home page
            String alertMessage = "Password changed successfully.";
            String redirectUrl = "profile";

            String script = "<script type='text/javascript'>alert('" + alertMessage + "');window.location.href='" + redirectUrl + "';</script>";
            response.getWriter().println(script);

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
