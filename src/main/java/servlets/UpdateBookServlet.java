package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bittercode.constant.BookStoreConstants;
import com.bittercode.constant.ResponseCode;
import com.bittercode.constant.db.BooksDBConstants;
import com.bittercode.model.Book;
import com.bittercode.model.UserRole;
import com.bittercode.service.BookService;
import com.bittercode.service.impl.BookServiceImpl;
import com.bittercode.util.StoreUtil;

public class UpdateBookServlet extends HttpServlet {
    BookService bookService = new BookServiceImpl();

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter pw = res.getWriter();
        res.setContentType(BookStoreConstants.CONTENT_TYPE_TEXT_HTML);

        if (!StoreUtil.isLoggedIn(UserRole.SELLER, req.getSession())) {
            RequestDispatcher rd = req.getRequestDispatcher("SellerLogin.html");
            rd.include(req, res);
            pw.println("<table class=\"tab\"><tr><td>Please Login First to Continue!!</td></tr></table>");
            return;
        }

        RequestDispatcher rd = req.getRequestDispatcher("SellerHome.html");
        rd.include(req, res);
        StoreUtil.setActiveTab(pw, "storebooks");
        pw.println("<div class='container my-2'>");

        try {
            if (req.getParameter("updateFormSubmitted") != null) {
                String bName = req.getParameter(BooksDBConstants.COLUMN_NAME);
                String bCode = req.getParameter(BooksDBConstants.COLUMN_BARCODE);
                String bAuthor = req.getParameter(BooksDBConstants.COLUMN_AUTHOR);
                double bPrice = Double.parseDouble(req.getParameter(BooksDBConstants.COLUMN_PRICE));
                int bQty = Integer.parseInt(req.getParameter(BooksDBConstants.COLUMN_QUANTITY));

                Book book = new Book(bCode, bName, bAuthor, bPrice, bQty);
                String message = bookService.updateBook(book);
                if (ResponseCode.SUCCESS.name().equalsIgnoreCase(message)) {
                    pw.println(
                            "<table class=\"tab\"><tr><td>Book Detail Updated Successfully!</td></tr></table>");
                } else {
                    pw.println("<table class=\"tab\"><tr><td>Failed to Update Book!!</td></tr></table>");
                    // rd.include(req, res);
                }

                return;
            }

            String bookId = req.getParameter("bookId");

            if (bookId != null) {
                Book book = bookService.getBookById(bookId);
                showUpdateBookForm(pw, book);
            }

        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<table class=\"tab\"><tr><td>Failed to Load Book data!!</td></tr></table>");
        }
    }

    private static void showUpdateBookForm(PrintWriter pw, Book book) {
        String form = "<table class=\"table table-bordered my-5\" style=\"width:50%; margin:auto; background-color:#f8f9fa; box-shadow: 0 4px 8px rgba(0,0,0,0.2); border-radius:10px;\">\r\n"
                + "        <tr>\r\n"
                + "            <td style=\"padding:20px;\">\r\n"
                + "                <form action=\"updatebook\" method=\"post\" style=\"font-family:'Poppins', sans-serif;\">\r\n"
                + "                    <div class=\"mb-3\">\r\n"
                + "                        <label for=\"bookCode\" class=\"form-label\">Book Code:</label>\r\n"
                + "                        <input type=\"text\" name=\"barcode\" id=\"bookCode\" class=\"form-control\" placeholder=\"Enter Book Code\" value=\'" + book.getBarcode() + "\' readonly>\r\n"
                + "                    </div>\r\n"
                + "                    <div class=\"mb-3\">\r\n"
                + "                        <label for=\"bookName\" class=\"form-label\">Book Name:</label>\r\n"
                + "                        <input type=\"text\" name=\"name\" id=\"bookName\" class=\"form-control\" placeholder=\"Enter Book's name\" value=\'" + book.getName() + "\' required>\r\n"
                + "                    </div>\r\n"
                + "                    <div class=\"mb-3\">\r\n"
                + "                        <label for=\"bookAuthor\" class=\"form-label\">Book Author:</label>\r\n"
                + "                        <input type=\"text\" name=\"author\" id=\"bookAuthor\" class=\"form-control\" placeholder=\"Enter Author's Name\" value=\'" + book.getAuthor() + "\' required>\r\n"
                + "                    </div>\r\n"
                + "                    <div class=\"mb-3\">\r\n"
                + "                        <label for=\"bookPrice\" class=\"form-label\">Book Price:</label>\r\n"
                + "                        <input type=\"number\" name=\"price\" id=\"bookPrice\" class=\"form-control\" placeholder=\"Enter the Price\" value=\'" + book.getPrice() + "\' required>\r\n"
                + "                    </div>\r\n"
                + "                    <div class=\"mb-3\">\r\n"
                + "                        <label for=\"bookQuantity\" class=\"form-label\">Book Quantity:</label>\r\n"
                + "                        <input type=\"number\" name=\"quantity\" id=\"bookQuantity\" class=\"form-control\" placeholder=\"Enter the quantity\" value=\'" + book.getQuantity() + "\' required>\r\n"
                + "                    </div>\r\n"
                + "                    <div class=\"text-center\">\r\n"
                + "                        <input class=\"btn btn-primary\" type=\"submit\" name=\'updateFormSubmitted\' value=\"Update Book\">\r\n"
                + "                    </div>\r\n"
                + "                </form>\r\n"
                + "            </td>\r\n"
                + "        </tr>\r\n"
                + "    </table>";
        pw.println(form);
    }
}
