<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>
<html>
<head>
    <title>Books — LibraryMS</title>
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
        <h1>Books</h1>
        <a href="${pageContext.request.contextPath}/books?action=new" class="btn btn-primary">+ Add Book</a>
    </div>

    <div class="card">
        <form method="get" action="${pageContext.request.contextPath}/books" class="search-bar">
            <input type="hidden" name="action" value="search">
            <input type="text" name="keyword" placeholder="Search by title, author, or ISBN..."
                   value="${keyword}">
            <button type="submit" class="btn btn-primary">Search</button>
            <a href="${pageContext.request.contextPath}/books" class="btn btn-warning">Clear</a>
        </form>

        <table>
            <thead>
                <tr>
                    <th>#</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>ISBN</th>
                    <th>Genre</th>
                    <th>Available / Total</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="book" items="${books}" varStatus="s">
                <tr>
                    <td>${s.count}</td>
                    <td><strong>${book.title}</strong></td>
                    <td>${book.author}</td>
                    <td>${book.isbn}</td>
                    <td>${book.genre}</td>
                    <td>
                        <c:choose>
                            <c:when test="${book.availableCopies == 0}">
                                <span class="badge badge-red">${book.availableCopies} / ${book.totalCopies}</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-green">${book.availableCopies} / ${book.totalCopies}</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/books?action=edit&id=${book.id}"
                           class="btn btn-warning btn-sm">Edit</a>
                        <a href="${pageContext.request.contextPath}/books?action=delete&id=${book.id}"
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('Delete this book?')">Delete</a>
                    </td>
                </tr>
                </c:forEach>
                <c:if test="${empty books}">
                    <tr><td colspan="7" style="text-align:center; color:#718096; padding:2rem;">No books found.</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
