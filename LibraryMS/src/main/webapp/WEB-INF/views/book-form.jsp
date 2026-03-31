<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${empty book ? 'Add Book' : 'Edit Book'} — LibraryMS</title>
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
        <h1>${empty book ? 'Add New Book' : 'Edit Book'}</h1>
        <a href="${pageContext.request.contextPath}/books" class="btn btn-warning">← Back</a>
    </div>

    <div class="card" style="max-width: 560px;">
        <form method="post" action="${pageContext.request.contextPath}/books">
            <input type="hidden" name="id" value="${book.id}">

            <div class="form-group">
                <label>Title *</label>
                <input type="text" name="title" value="${book.title}" required>
            </div>
            <div class="form-group">
                <label>Author *</label>
                <input type="text" name="author" value="${book.author}" required>
            </div>
            <div class="form-group">
                <label>ISBN *</label>
                <input type="text" name="isbn" value="${book.isbn}" required>
            </div>
            <div class="form-group">
                <label>Genre</label>
                <select name="genre">
                    <option value="">-- Select Genre --</option>
                    <option value="Fiction"     ${book.genre == 'Fiction'     ? 'selected' : ''}>Fiction</option>
                    <option value="Non-Fiction" ${book.genre == 'Non-Fiction' ? 'selected' : ''}>Non-Fiction</option>
                    <option value="Science"     ${book.genre == 'Science'     ? 'selected' : ''}>Science</option>
                    <option value="History"     ${book.genre == 'History'     ? 'selected' : ''}>History</option>
                    <option value="Technology"  ${book.genre == 'Technology'  ? 'selected' : ''}>Technology</option>
                    <option value="Biography"   ${book.genre == 'Biography'   ? 'selected' : ''}>Biography</option>
                    <option value="Fantasy"     ${book.genre == 'Fantasy'     ? 'selected' : ''}>Fantasy</option>
                    <option value="Mystery"     ${book.genre == 'Mystery'     ? 'selected' : ''}>Mystery</option>
                </select>
            </div>
            <div class="form-group">
                <label>Total Copies *</label>
                <input type="number" name="totalCopies" value="${empty book ? 1 : book.totalCopies}" min="1" required>
            </div>

            <button type="submit" class="btn btn-primary">
                ${empty book ? 'Add Book' : 'Update Book'}
            </button>
        </form>
    </div>
</div>

</body>
</html>
