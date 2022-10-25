<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<html>
    <head>
        <meta charset="UFT-8">
        <title>Page ${user.get("lastName")}</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
        crossorigin="anonymous">
    </head>
    <body>
        <table class="table table-success table-striped">
                  <thead>
                    <tr>
                      <th scope="col">id</th>
                      <th scope="col">FirstName</th>
                      <th scope="col">LastName</th>
                      <th scope="col">Email</th>
                      <th scope="col">Delete User</th>
                    </tr>
                  </thead>
                   <tbody>
                    <tr>
                        <th scope="row">${user.get("id")}</th>
                        <td>${user.get("firstName")}</td>
                        <td>${user.get("lastName")}</td>
                        <td>${user.get("email")}</td>
                        <td><button type="button" class="btn btn-outline-danger"><a href='/users/delete?id=${user.get("id")}'>Delete</button></a></td>
                     </tr>
                    </tbody>
                </table>
    </body>
</html>
<!-- END -->
