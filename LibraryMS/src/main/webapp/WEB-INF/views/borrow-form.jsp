<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>
<html>
<head>
    <title>Issue Book — LibraryMS</title>
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
        <h1>Issue Book</h1>
        <a href="${pageContext.request.contextPath}/borrow" class="btn btn-warning">← Back</a>
    </div>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <div class="card" style="max-width: 520px;">
        <form method="post" action="${pageContext.request.contextPath}/borrow">

            <div class="form-group">
                <label>Select Book *</label>
                <select name="bookId" required>
                    <option value="">-- Choose a Book --</option>
                    <c:forEach var="b" items="${books}">
                        <option value="${b.id}"
                            ${b.availableCopies == 0 ? 'disabled' : ''}>
                            ${b.title} — ${b.author}
                            (${b.availableCopies} available)
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label>Select Member *</label>
                <select name="memberId" required>
                    <option value="">-- Choose a Member --</option>
                    <c:forEach var="m" items="${members}">
                        <option value="${m.id}">${m.name} (${m.membershipType})</option>
                    </c:forEach>
                </select>
            </div>

            <p style="font-size:13px; color:#718096; margin-bottom:1rem;">
                Due date will be set automatically to <strong>14 days</strong> from today.
            </p>

            <button type="submit" class="btn btn-success">Issue Book</button>
        </form>
    </div>
</div>

</body>
</html>
