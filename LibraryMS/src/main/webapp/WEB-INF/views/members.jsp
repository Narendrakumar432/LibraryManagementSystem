<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Members — LibraryMS</title>
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
        <h1>Members</h1>
        <a href="${pageContext.request.contextPath}/members?action=new" class="btn btn-primary">+ Add Member</a>
    </div>

    <div class="card">
        <table>
            <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Membership Type</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="m" items="${members}" varStatus="s">
                <tr>
                    <td>${s.count}</td>
                    <td><strong>${m.name}</strong></td>
                    <td>${m.email}</td>
                    <td>${m.phone}</td>
                    <td>
                        <c:choose>
                            <c:when test="${m.membershipType == 'FACULTY'}">
                                <span class="badge badge-blue">${m.membershipType}</span>
                            </c:when>
                            <c:when test="${m.membershipType == 'STUDENT'}">
                                <span class="badge badge-green">${m.membershipType}</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-yellow">${m.membershipType}</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/members?action=edit&id=${m.id}"
                           class="btn btn-warning btn-sm">Edit</a>
                        <a href="${pageContext.request.contextPath}/members?action=delete&id=${m.id}"
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('Delete this member?')">Delete</a>
                    </td>
                </tr>
                </c:forEach>
                <c:if test="${empty members}">
                    <tr><td colspan="6" style="text-align:center; color:#718096; padding:2rem;">No members found.</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
