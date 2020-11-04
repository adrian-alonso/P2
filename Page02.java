package p2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Page02 extends Sint101P2 {
  public void phase02 (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String passwd = (String)request.getAttribute("password");

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>P2EA</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<header>");
    out.println("<h1>Servicio de consulta de expedientes acad&eacutemicos</h1>");
    out.println("</header>");
    out.println("<section>");
    out.println("<h2>warnings:" + passwd +"</h2>");
    out.println("<p>errores</p>");
    out.println("<form method=\"get\">");
    out.println("<input type=\"submit\" class=\"backButton\" value=\"Atrás\" onclick=\"form.pphase.value=01\">");
    out.println("</form>");
    out.println("</section>");
    out.println("<footer>");
    out.println("<p>&copy Adri&aacuten Alonso Vilar (2020-2021)</p>");
    out.println("</footer>");
    out.println("</body>");
    out.println("</html>");
  }
}
