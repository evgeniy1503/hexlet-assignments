<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<html>
    <head>
        <meta charset="UFT-8">
        <title>Show Users</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
        crossorigin="anonymous">
    </head>
    <body>
        <table class="table table-striped table-hover">
          <thead>
            <tr>
              <th scope="col">id</th>
              <th scope="col">FullName</th>
            </tr>
          </thead>
           <tbody>
           <c:forEach var="user" items="${users}">
            <tr>
                <th scope="row">${user.get("id")}</th>
                <td><a href='/users/show?id=${user.get("id")}'>${user.get("firstName")} ${user.get("lastName")}</a></td>
             </tr>
             </c:forEach>
            </tbody>
        </table>
    </body>
</html>
<!-- END -->
