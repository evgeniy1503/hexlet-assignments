package exercise.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exercise.LoggerFactory;
import exercise.TemplateEngineUtil;

public class WelcomeServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionServlet.class);
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {
        LOGGER.log(Level.INFO, "Мы сейчас здесь!!!");
        TemplateEngineUtil.render("index.html", request, response);
    }
}
