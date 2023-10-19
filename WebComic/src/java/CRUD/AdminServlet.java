package CRUD;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dal.AccountDAO;
import dal.AdminDAO;
import dal.CommentDAO;
import dal.StoryDAO;
import editString.EditDate;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import model.Account;
import model.Chapter;
import model.Comment;
import model.Story;
import model.StoryViews;

/**
 *
 * @author HP
 */
@WebServlet(urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

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
            out.println("<title>Servlet AdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminServlet at " + request.getContextPath() + "</h1>");
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
        StoryDAO sd = new StoryDAO();
        EditDate ed = new EditDate();
        AccountDAO acc = new AccountDAO();
        CommentDAO cd = new CommentDAO();
        AdminDAO ad = new AdminDAO();

        int totalStory = sd.getTotalStory(0, null);
        int totalViews = sd.getTotalViews();
        int totalTrans = ad.getTotalTrans();
        int totalUser = ad.getTotaUser();

        //Chart
        List<Account> listacc = ad.getTranlator();
        LinkedHashMap<String, Double> chartTrans = new LinkedHashMap<>();
        for (Account account : listacc) {
            chartTrans.put(account.getFname() + " " + account.getLname(), ad.getTotalStbyTrans(account.getAc_id()));
        }

        List<StoryViews> listsv = sd.getListStorieViews();

        //newly update
        List<Story> st = sd.getStories(0, null, null);
        LinkedHashMap<Story, Chapter> trending = new LinkedHashMap<>();

        for (int i = 0; i < st.size(); i++) {
            List<Chapter> ch = sd.getChap(st.get(i).getS_id());
            trending.put(st.get(i), ch.get(ch.size() - 1));
        }
        //Top  S view
        List<Story> topview = sd.topView();
        
        //Top user
        List<Account> listuser = acc.TopuserActive();
        
        LinkedHashMap<Account, Integer> topuser = new LinkedHashMap<>();
        for (Account ac : listuser) {
            topuser.put(ac, cd.getTotalCommentByAcid(ac.getAc_id()));
           
           

        }

        //Comment
        String currentTime = ed.getCurrentTime();
        List<Comment> listcmt = cd.getComment2();
        LinkedHashMap<Comment, String> cmt = new LinkedHashMap<>();
        for (Comment comment : listcmt) {
            String create_at = comment.getCreate_at().substring(0, 19);
            cmt.put(comment, ed.getDistanceDate(create_at, currentTime));

        }

        //Total
        request.setAttribute("totalStory", totalStory);
        request.setAttribute("totalTrans", totalTrans);
        request.setAttribute("totalViews", totalViews);
        request.setAttribute("totalUser", totalUser);

        //comment
        request.setAttribute("cmt", cmt);
        //newly story
        request.setAttribute("st", trending);

        //Top view 
        request.setAttribute("topview", topview);
        
        request.setAttribute("topuser", topuser);

        //chart
        request.setAttribute("chartTrans", chartTrans);
        request.setAttribute("listsv", listsv);

        request.getRequestDispatcher("admin.jsp").forward(request, response);
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
