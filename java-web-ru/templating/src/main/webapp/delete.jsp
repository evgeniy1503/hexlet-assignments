<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<html>
    <head>
        <meta charset="UFT-8">
        <title>Delete ${user.get("lastName")}</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
        crossorigin="anonymous">
    </head>
    <body>
        <div class="alert alert-danger" role="alert">
          You want to delete a user from ${user.get("firstName")} ${user.get("lastName")}, with id = ${user.get("id")};
        </div>
       <form action="/users/delete?id=${user.get("id")}" method="post">
       <button type="submit" class="btn btn-outline-danger">Delete</button>
       </form>
       <button type="button" class="btn btn-outline-success"><a href='/users/show?id=${user.get("id")}'>Cancel</a></button>
    </body>
</html>
<!-- END -->
