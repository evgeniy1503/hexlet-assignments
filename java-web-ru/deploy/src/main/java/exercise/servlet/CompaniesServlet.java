package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        PrintWriter out = response.getWriter();

        List<String> resultSearch;

        String paramSearch = request.getParameter("search") == null ? "" : request.getParameter("search");
        String queryString = request.getQueryString();

        if (paramSearch.equals("") || queryString == null) {
            resultSearch = new ArrayList<>(getCompanies());
            for (String company : resultSearch) {
                out.println(company);
            }
            return;
        }

        resultSearch = getCompanies().stream()
                    .filter(x -> x.contains(paramSearch))
                    .collect(Collectors.toList());

        if (resultSearch.isEmpty()) {
            out.println("Companies not found");
            return;
        }

        for (String company: resultSearch) {
            out.println(company);
        }
        // END
    }
}
