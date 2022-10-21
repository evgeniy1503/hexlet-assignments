package exercise.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("src/main/resources/users.json"), new TypeReference<>() {
        });
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List users = getUsers();
        StringBuilder showUsers = new StringBuilder();
        for (Object o : users) {
            Map user = (Map) o;
            String id = (String) user.get("id");
            String fullName = (user.get("firstName") + " " + user.get("lastName"));
            showUsers.append("""
                        <table>
                         <tr>
                        """);
            showUsers.append(" <td>" + id + "</td>");
            showUsers.append("<td>");
            showUsers.append("<a href=\"/users/" + id + "\">" + fullName + "</a>");
            showUsers.append("</td>");
            showUsers.append("""
                         
                         </tr>
                        </table>
                        """);
        }
        PrintWriter out = response.getWriter();
        out.println(showUsers.toString());
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List users = getUsers();

        StringBuilder showUser = new StringBuilder();
        PrintWriter out = response.getWriter();

        for (Object o : users) {
            Map user = (Map) o;
            if (user.get("id").equals(id)) {

                String fullName = (user.get("firstName") + " " + user.get("lastName"));
                String email = (String) user.get("email");

                showUser.append("""
                        <table>
                        """);
                showUser.append(" <tr><td><b> id: </b>" + id + "</td></tr>");
                showUser.append(" <tr><td><b> FullName: </b>" + fullName + "</td></tr>");
                showUser.append(" <tr><td><b> email: </b>" + email + "</td></tr>");
                showUser.append("""
                         </tr>
                        </table>
                        """);
                out.println(showUser.toString());
                break;
            }
            // END
        }
        if (showUser.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Not found");
        }
    }
}
