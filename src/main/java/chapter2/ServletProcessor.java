package chapter2;

import chapter1.Request;
import chapter1.Response;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * created by xdCao on 2017/10/30
 */

public class ServletProcessor implements Processor{
    public void process(Request request, Response response) {
        String uri=request.getUri();
        String servletName=uri.substring(uri.lastIndexOf("/")+1);
//        URLClassLoader loader=null;
        ClassLoader classLoader = ServletProcessor.class.getClassLoader();
//        try {
//            URL[] urls=new URL[1];
//            URLStreamHandler streamHandler=null;
//            File classpath=new File(Constants.WEB_ROOT);
//            String repo=(new URL("file",null,classpath.getCanonicalPath()+File.separator)).toString();
//            urls[0]=new URL(null,repo,streamHandler);
//            loader=new URLClassLoader(urls);
//        }catch (Exception e){
//            System.out.println(e.toString());
//        }

        Class myClass=null;
        try {
            myClass=classLoader.loadClass("chapter2."+servletName);
        }catch (ClassNotFoundException e){
            System.out.println(e.toString());
        }

        Servlet servlet=null;
        try {
            servlet=(Servlet) myClass.newInstance();
            RequestFacade requestFacade=new RequestFacade(request);
            ResponseFacade responseFacade=new ResponseFacade(response);
            servlet.service(requestFacade,responseFacade);
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }
}
