package p2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Page01 extends Sint101P2 {
  public void phase01 (HttpServletRequest request, HttpServletResponse response, String pphase) throws IOException, ServletException {
    String passwd = request.getParameter("p");

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    //HTML DE LA PAGINA phase 01
    //
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
    out.println("<h2>Bienvenido a este servicio</h2>");
    out.println("<form name=\"form\" method=\"get\" >");
    out.println("<a href=\"?pphase=02\">Ver los ficheros err&oacuteneos</a>");
    out.println("<input type=\"hidden\" id=\"pphase\" name=\"pphase\" value=\"02\">");
    // out.println("<div id=\"openModal\" class=\"modalDialog\">");
    // out.println("<div><input type=\"password\" id=\"passwd\" name=\"passwd\" value=\"" + passwd + "\">");
    // request.setAttribute("password", passwd);
    // out.println("<a href=\"?pphase=02\" title=\"Send\" class=\"send\">Enviar</a></div>");
    // out.println("</div>");
    out.println("</form>");
    out.println("</section>");
    out.println("<section>");
    out.println("<h3>Selecciona una consulta:</h3>");
    out.println("<p>Consulta 1: Ver los alumnos de una asignatura de una titulaci&oacuten</p>");
    out.println("</section>");
    out.println("<footer>");
    out.println("<p>&copy Adri&aacuten Alonso Vilar (2020-2021)</p>");
    out.println("</footer>");
    out.println("</body>");
    out.println("</html>");
  }
}
