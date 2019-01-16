package io.github.benwhitehead.gcp.appengine.helloworld;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloWorld", value = "/hi")
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setStatus(200);
    response.setContentType("text/plain");
    response.setCharacterEncoding("utf-8");
    response.getWriter().write("Hello World!");
  }

}
