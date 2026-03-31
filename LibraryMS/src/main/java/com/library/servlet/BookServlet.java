package com.library.servlet;

import com.library.dao.BookDAO;
import com.library.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
	private BookDAO bookDAO;

    @Override
    public void init() {
        bookDAO = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "list";

        if (action.equals("new")) {
            req.getRequestDispatcher("/WEB-INF/views/book-form.jsp").forward(req, resp);

        } else if (action.equals("edit")) {
            Long id = Long.parseLong(req.getParameter("id"));
            Book book = bookDAO.findById(id);
            req.setAttribute("book", book);
            req.getRequestDispatcher("/WEB-INF/views/book-form.jsp").forward(req, resp);

        } else if (action.equals("delete")) {
            Long id = Long.parseLong(req.getParameter("id"));
            bookDAO.delete(id);
            resp.sendRedirect(req.getContextPath() + "/books");

        } else if (action.equals("search")) {
            String keyword = req.getParameter("keyword");
            List<Book> books = bookDAO.search(keyword);
            req.setAttribute("books", books);
            req.setAttribute("keyword", keyword);
            req.getRequestDispatcher("/WEB-INF/views/books.jsp").forward(req, resp);

        } else {
            List<Book> books = bookDAO.findAll();
            req.setAttribute("books", books);
            req.getRequestDispatcher("/WEB-INF/views/books.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");
        String title   = req.getParameter("title");
        String author  = req.getParameter("author");
        String isbn    = req.getParameter("isbn");
        String genre   = req.getParameter("genre");
        int    copies  = Integer.parseInt(req.getParameter("totalCopies"));

        if (idParam == null || idParam.isEmpty()) {
            Book book = new Book(title, author, isbn, genre, copies);
            bookDAO.save(book);
        } else {
            Book book = bookDAO.findById(Long.parseLong(idParam));
            book.setTitle(title);
            book.setAuthor(author);
            book.setIsbn(isbn);
            book.setGenre(genre);
            book.setTotalCopies(copies);
            bookDAO.update(book);
        }

        resp.sendRedirect(req.getContextPath() + "/books");
    }
}
