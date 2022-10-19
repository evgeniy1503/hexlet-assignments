package exercise.servlet;

import exercise.Data;

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
        PrintWriter pw = response.getWriter();
        List<String> resultSearch;
        String paramSearch = request.getParameter("search");
        String queryString = request.getQueryString();
        if (paramSearch == null || paramSearch.equals("") || queryString == null) {
            resultSearch = new ArrayList<>(getCompanies());
            for (String company: resultSearch) {
                pw.write(company + "\n");
            }
        } else {
            resultSearch = getCompanies().stream()
                    .filter(x -> x.contains(paramSearch)).collect(Collectors.toList());
            if (resultSearch.isEmpty()) {
                pw.write("Companies not found");
            }
            for (String company: resultSearch) {
                pw.write(company + "\n");
            }
        }

        pw.close();
        // END
    }
}
