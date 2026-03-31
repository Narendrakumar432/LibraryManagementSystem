<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${empty member ? 'Add Member' : 'Edit Member'} — LibraryMS</title>
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
        <h1>${empty member ? 'Add New Member' : 'Edit Member'}</h1>
        <a href="${pageContext.request.contextPath}/members" class="btn btn-warning">← Back</a>
    </div>

    <div class="card" style="max-width: 520px;">
        <form method="post" action="${pageContext.request.contextPath}/members">
            <input type="hidden" name="id" value="${member.id}">

            <div class="form-group">
                <label>Full Name *</label>
                <input type="text" name="name" value="${member.name}" required>
            </div>
            <div class="form-group">
                <label>Email *</label>
                <input type="email" name="email" value="${member.email}" required>
            </div>
            <div class="form-group">
                <label>Phone</label>
                <input type="text" name="phone" value="${member.phone}">
            </div>
            <div class="form-group">
                <label>Membership Type *</label>
                <select name="membershipType" required>
                    <option value="">-- Select Type --</option>
                    <option value="STUDENT"  ${member.membershipType == 'STUDENT'  ? 'selected' : ''}>Student</option>
                    <option value="FACULTY"  ${member.membershipType == 'FACULTY'  ? 'selected' : ''}>Faculty</option>
                    <option value="PUBLIC"   ${member.membershipType == 'PUBLIC'   ? 'selected' : ''}>Public</option>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">
                ${empty member ? 'Add Member' : 'Update Member'}
            </button>
        </form>
    </div>
</div>

</body>
</html>
