<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>
<html>
<head>
    <title>Borrow Records — LibraryMS</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<nav>
    <a class="brand" href="${pageContext.request.contextPath}/">📚 LibraryMS</a>
    <a href="${pageContext.request.contextPath}/books">Books</a>
    <a href="${pageContext.request.contextPath}/members">Members</a>
    <a href="${pageContext.request.contextPath}/borrow">Borrow / Return</a>
</nav>

<div class="container">
    <div class="page-header">
        <h1>Borrow Records</h1>
        <a href="${pageContext.request.contextPath}/borrow?action=new" class="btn btn-primary">+ Issue Book</a>
    </div>

    <div class="card">
        <table>
            <thead>
                <tr>
                    <th>#</th>
                    <th>Book</th>
                    <th>Member</th>
                    <th>Borrow Date</th>
                    <th>Due Date</th>
                    <th>Return Date</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="r" items="${records}" varStatus="s">
                <tr>
                    <td>${s.count}</td>
                    <td><strong>${r.book.title}</strong></td>
                    <td>${r.member.name}</td>
                    <td>${r.borrowDate}</td>
                    <td>${r.dueDate}</td>
                    <td>${empty r.returnDate ? '—' : r.returnDate}</td>
                    <td>
                        <c:choose>
                            <c:when test="${r.status == 'RETURNED'}">
                                <span class="badge badge-green">RETURNED</span>
                            </c:when>
                            <c:when test="${r.status == 'OVERDUE'}">
                                <span class="badge badge-red">OVERDUE</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-blue">BORROWED</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:if test="${r.status != 'RETURNED'}">
                            <a href="${pageContext.request.contextPath}/borrow?action=return&id=${r.id}"
                               class="btn btn-success btn-sm"
                               onclick="return confirm('Mark this book as returned?')">Return</a>
                        </c:if>
                    </td>
                </tr>
                </c:forEach>
                <c:if test="${empty records}">
                    <tr><td colspan="8" style="text-align:center; color:#718096; padding:2rem;">No borrow records yet.</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
