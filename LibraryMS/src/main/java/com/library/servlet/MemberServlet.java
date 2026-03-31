package com.library.servlet;

import com.library.dao.MemberDAO;
import com.library.model.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/members")
public class MemberServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
	private MemberDAO memberDAO;

    @Override
    public void init() {
        memberDAO = new MemberDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "list";

        if (action.equals("new")) {
            req.getRequestDispatcher("/WEB-INF/views/member-form.jsp").forward(req, resp);

        } else if (action.equals("edit")) {
            Long id = Long.parseLong(req.getParameter("id"));
            Member member = memberDAO.findById(id);
            req.setAttribute("member", member);
            req.getRequestDispatcher("/WEB-INF/views/member-form.jsp").forward(req, resp);

        } else if (action.equals("delete")) {
            Long id = Long.parseLong(req.getParameter("id"));
            memberDAO.delete(id);
            resp.sendRedirect(req.getContextPath() + "/members");

        } else {
            List<Member> members = memberDAO.findAll();
            req.setAttribute("members", members);
            req.getRequestDispatcher("/WEB-INF/views/members.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");
        String name    = req.getParameter("name");
        String email   = req.getParameter("email");
        String phone   = req.getParameter("phone");
        String type    = req.getParameter("membershipType");

        if (idParam == null || idParam.isEmpty()) {
            memberDAO.save(new Member(name, email, phone, type));
        } else {
            Member member = memberDAO.findById(Long.parseLong(idParam));
            member.setName(name);
            member.setEmail(email);
            member.setPhone(phone);
            member.setMembershipType(type);
            memberDAO.update(member);
        }

        resp.sendRedirect(req.getContextPath() + "/members");
    }
}
