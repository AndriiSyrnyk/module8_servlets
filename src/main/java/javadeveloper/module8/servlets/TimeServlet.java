package javadeveloper.module8.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javadeveloper.module8.utils.FormattedZonedDateTime;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String currentFormattedZonedDateTime;
        String parameterName = "timezone";

        if (req.getParameter(parameterName) != null) {
            String parameterValue = URLDecoder
                    .decode(req.getParameter(parameterName), StandardCharsets.UTF_8)
                    .replace(' ', '+');
            currentFormattedZonedDateTime = FormattedZonedDateTime
                    .getCurrentFormattedZonedDateTime(parameterValue);
        }
        else {
            currentFormattedZonedDateTime = FormattedZonedDateTime
                    .getCurrentFormattedZonedDateTime();
        }

        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write("<h1>"
                + currentFormattedZonedDateTime + "</h1>");
        resp.getWriter().close();
    }
}
