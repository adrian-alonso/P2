package p2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Page11 extends Sint101P2 {
  public void phase11 (HttpServletRequest request, HttpServletResponse response, String pphase) throws IOException, ServletException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    //HTML DE LA PAGINA phase 11
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
    out.println("<h2>Consulta 1: Fase 1</h2>");
    out.println("<p>Selecciona :</p>");
    out.println("</section>");
    out.println("<section>");
    // out.println("<form name=\"form\" method=\"get\">");
    // out.println("<input type=\"submit\" class=\"homeButton\" value=\"Inicio\" onclick=\"form.pphase.value=01\">");
    // out.println("</form>");
    out.println("<a href=\"?pphase=01\">Inicio</a>");
    out.println("</section>");
    out.println("<footer>");
    out.println("<p>&copy Adri&aacuten Alonso Vilar (2020-2021)</p>");
    out.println("</footer>");
    out.println("</body>");
    out.println("</html>");
  }
}
