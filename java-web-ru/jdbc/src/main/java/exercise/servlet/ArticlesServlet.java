package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.sql.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;


public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        String page = request.getParameter("page");
        int numberPage;

        List<Map<String, String>> articles = new ArrayList<>();


        if (page == null) {
            numberPage = 1;
        } else {
            numberPage = Integer.parseInt(page);
        }

        String query = "SELECT id, title, body FROM articles ORDER BY id LIMIT 10 OFFSET ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            if (numberPage == 1) {
                statement.setInt(1,   0);
            } else if (numberPage == 2) {
                statement.setInt(1, 10);
            } else {
                statement.setInt(1, numberPage * 10);
            }
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                articles.add(Map.of("id", result.getString("id"), "title", result.getString("title"),
                        "body", result.getString("body")));
            }
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        request.setAttribute("articles", articles);
        request.setAttribute("pageNumber", numberPage);
        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        Map<String, String> article = new HashMap<>();
        String id = getId(request);
        String query = "SELECT title, body FROM articles WHERE id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(id));
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                article.put("title", result.getString("title"));
                article.put("body", result.getString("body"));
            }
            System.out.println(article);
        } catch (SQLException e) {
            System.out.println("ERROR!!!");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        if (article.size() == 0) {
            response.sendError(404);

        }
        request.setAttribute("article", article);
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
