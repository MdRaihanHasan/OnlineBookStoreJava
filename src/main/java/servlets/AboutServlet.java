package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bittercode.model.UserRole;
import com.bittercode.util.StoreUtil;
//Http Servlet extended class for showing the about information
public class AboutServlet extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        //If the store is logged in as customer or seller show about info
        if (StoreUtil.isLoggedIn(UserRole.CUSTOMER, req.getSession())) {
            RequestDispatcher rd = req.getRequestDispatcher("CustomerHome.html");
            rd.include(req, res);
            StoreUtil.setActiveTab(pw, "about");
            // Static content displaying online bookstore information
            pw.println("<div class=\"container\" style='text-align: center; margin: 100px; padding: 20px; font-family: Arial, sans-serif;'>");
            
            pw.println("<p style=\" font-size: 20px; \">Welcome to <strong>OnlineBookstore</strong>, your one-stop destination for all your reading needs!</p>");
            pw.println("<table style='width: 50%; margin: 20px auto; border-collapse: collapse; font-size: 18px;'>");
            pw.println("    <tr>");
            pw.println("        <td style='font-weight: bold; padding: 10px; border: 1px solid #ddd;'>Name:</td>");
            pw.println("        <td style='padding: 10px; border: 1px solid #ddd;'>OnlineBookstore</td>");
            pw.println("    </tr>");
            pw.println("    <tr>");
            pw.println("        <td style='font-weight: bold; padding: 10px; border: 1px solid #ddd;'>Email:</td>");
            pw.println("        <td style='padding: 10px; border: 1px solid #ddd;'>sales@onlinebookstore</td>");
            pw.println("    </tr>");
            pw.println("    <tr>");
            pw.println("        <td style='font-weight: bold; padding: 10px; border: 1px solid #ddd;'>Phone:</td>");
            pw.println("        <td style='padding: 10px; border: 1px solid #ddd;'>12345678</td>");
            pw.println("    </tr>");
            pw.println("</table>");
            pw.println("<p style='text-align: center; font-size: 16px;'>Thank you for choosing <strong>OnlineBookstore</strong>! Happy reading!</p>");
            pw.println("</div>");
        } else if (StoreUtil.isLoggedIn(UserRole.SELLER, req.getSession())) {
            RequestDispatcher rd = req.getRequestDispatcher("SellerHome.html");
            rd.include(req, res);
            StoreUtil.setActiveTab(pw, "about");
            // Static content displaying online bookstore information
            pw.println("<div class=\"container\" style=' text-align: center; margin: 100px; padding: 20px; font-family: Arial, sans-serif;'>");
            
            pw.println("<p style=\" font-size: 20px; \">Welcome to <strong>OnlineBookstore</strong>, your one-stop destination for all your reading needs!</p>");
            pw.println("<table style='width: 50%; margin: 20px auto; border-collapse: collapse; font-size: 18px;'>");
            pw.println("    <tr>");
            pw.println("        <td style='font-weight: bold; padding: 10px; border: 1px solid #ddd;'>Name:</td>");
            pw.println("        <td style='padding: 10px; border: 1px solid #ddd;'>OnlineBookstore</td>");
            pw.println("    </tr>");
            pw.println("    <tr>");
            pw.println("        <td style='font-weight: bold; padding: 10px; border: 1px solid #ddd;'>Email:</td>");
            pw.println("        <td style='padding: 10px; border: 1px solid #ddd;'>sales@onlinebookstore</td>");
            pw.println("    </tr>");
            pw.println("    <tr>");
            pw.println("        <td style='font-weight: bold; padding: 10px; border: 1px solid #ddd;'>Phone:</td>");
            pw.println("        <td style='padding: 10px; border: 1px solid #ddd;'>12345678</td>");
            pw.println("    </tr>");
            pw.println("</table>");
            pw.println("<p style='text-align: center; font-size: 16px;'>Thank you for choosing <strong>OnlineBookstore</strong>! Happy reading!</p>");
            pw.println("</div>");

        } else {
            //If the user is not logged in, ask to login first
            //Proceed only if logged in or forword to login page
            RequestDispatcher rd = req.getRequestDispatcher("login.html");
            rd.include(req, res);
            pw.println("<table class=\"tab\"><tr><td>Please Login First to Continue!!</td></tr></table>");
        }

    }

}
