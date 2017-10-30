package chapter2;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * created by xdCao on 2017/10/30
 */

public class PrimitiveServlet implements Servlet {


    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("from service");
        PrintWriter out=servletResponse.getWriter();
        String header="HTTP/1.1 200 OK\r\n"+
                "Content-Type: text/html\r\n"+
                "Content-Length: "+6+"\r\n"+
                "\r\n"+"Hello!";
        out.println(header);

    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        System.out.println("destroy'");
    }
}
