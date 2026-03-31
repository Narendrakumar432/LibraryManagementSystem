package com.library.servlet;

import com.library.dao.BookDAO;
import com.library.dao.BorrowRecordDAO;
import com.library.dao.MemberDAO;
import com.library.model.Book;
import com.library.model.BorrowRecord;
import com.library.model.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/borrow")
public class BorrowServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
	private BorrowRecordDAO borrowDAO;
    private BookDAO bookDAO;
    private MemberDAO memberDAO;

    @Override
    public void init() {
        borrowDAO = new BorrowRecordDAO();
        bookDAO   = new BookDAO();
        memberDAO = new MemberDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "list";

        borrowDAO.updateOverdueStatus();

        if (action.equals("new")) {
            req.setAttribute("books",   bookDAO.findAll());
            req.setAttribute("members", memberDAO.findAll());
            req.getRequestDispatcher("/WEB-INF/views/borrow-form.jsp").forward(req, resp);

        } else if (action.equals("return")) {
            Long id = Long.parseLong(req.getParameter("id"));
            BorrowRecord record = borrowDAO.findById(id);
            record.setReturnDate(LocalDate.now());
            record.setStatus("RETURNED");

            Book book = record.getBook();
            book.setAvailableCopies(book.getAvailableCopies() + 1);
            bookDAO.update(book);
            borrowDAO.update(record);

            resp.sendRedirect(req.getContextPath() + "/borrow");

        } else {
            List<BorrowRecord> records = borrowDAO.findAll();
            req.setAttribute("records", records);
            req.getRequestDispatcher("/WEB-INF/views/borrow-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long bookId   = Long.parseLong(req.getParameter("bookId"));
        Long memberId = Long.parseLong(req.getParameter("memberId"));

        Book book = bookDAO.findById(bookId);
        if (book.getAvailableCopies() <= 0) {
            req.setAttribute("error", "No copies available for \"" + book.getTitle() + "\".");
            req.setAttribute("books",   bookDAO.findAll());
            req.setAttribute("members", memberDAO.findAll());
            req.getRequestDispatcher("/WEB-INF/views/borrow-form.jsp").forward(req, resp);
            return;
        }

        Member member = memberDAO.findById(memberId);
        BorrowRecord record = new BorrowRecord(book, member);
        borrowDAO.save(record);

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookDAO.update(book);

        resp.sendRedirect(req.getContextPath() + "/borrow");
    }
}
