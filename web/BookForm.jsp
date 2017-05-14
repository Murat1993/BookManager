<html>
<head>
    <title>Books Store Application</title>
</head>
<body>
<center>
    <h1>Books Management</h1>
    <h2>
        <a href="new">Add New Book</a>
        &nbsp;&nbsp;&nbsp;
        <a href="list">List All Books</a>

    </h2>
</center>
<div align="center">
    <if test="${book != null}">
        <form action="update" method="post">
    </if>

    <if test="${book == null}">
        <form action="insert" method="post">
    </if>
    <table border="1" cellpadding="5">
        <caption>
            <h2>
                <if test="${book != null}">
                    Edit Book
                </if>
                <if test="${book == null}">
                    Add New Book
                </if>
            </h2>
        </caption>
        <if test="${book != null}">
            <input type="hidden" name="id" value="${book.id}"/>
        </if>
        <tr>
            <th>Title:</th>
            <td>
                <input type="text" name="title" size="45" value="${book.title}"/>
            </td>
        </tr>
        <tr>
            <th>Author:</th>
            <td>
                <input type="text" name="author" size="45" value="${book.author}"/>
            </td>
        </tr>
        <tr>
            <th>Price:</th>
            <td>
                <input type="text" name="price" size="5" value="${book.price}"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Save"/>
            </td>
        </tr>
    </table>
    </form>
</div>
</body>
</html>