<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Library Management System</title>
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
        <h1>Dashboard</h1>
    </div>

    <div class="stats-grid">
        <div class="stat-card">
            <div class="stat-num">📖</div>
            <div class="stat-label">Manage Books — add, edit, search, delete</div>
            <br>
            <a href="${pageContext.request.contextPath}/books" class="btn btn-primary btn-sm">Go to Books</a>
        </div>
        <div class="stat-card">
            <div class="stat-num">👤</div>
            <div class="stat-label">Manage Members — register &amp; update members</div>
            <br>
            <a href="${pageContext.request.contextPath}/members" class="btn btn-primary btn-sm">Go to Members</a>
        </div>
        <div class="stat-card">
            <div class="stat-num">🔄</div>
            <div class="stat-label">Issue &amp; Return — track borrow records</div>
            <br>
            <a href="${pageContext.request.contextPath}/borrow" class="btn btn-success btn-sm">Go to Borrow</a>
        </div>
    </div>
</div>

</body>
</html>
