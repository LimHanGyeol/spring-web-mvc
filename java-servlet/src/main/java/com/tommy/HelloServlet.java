package com.tommy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Spring Web Mvc
 * Java Servlet Application
 */
public class HelloServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("init");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do Get");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h1>Hello, " + getName() + " Servlet</h1>");
        response.getWriter().println("</body>");
        response.getWriter().println("</head>");
        response.getWriter().println("</html>");
    }

    private Object getName() {
        return getServletContext().getAttribute("name");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
