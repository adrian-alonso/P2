package p2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Page12 extends Sint101P2 {
  public void phase12 (HttpServletRequest request, HttpServletResponse response, String pdegree) throws IOException, ServletException {
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
    out.println("<h2>Consulta 1: Fase 2 (Titulaci&oacuten=" + pdegree + ")</h2>");
    out.println("<p>Selecciona una asignatura:</p>");
    out.println("</section>");
    out.println("<footer>");
    out.println("<p>&copy Adri&aacuten Alonso Vilar (2020-2021)</p>");
    out.println("</footer>");
    out.println("</body>");
    out.println("</html>");
  }
}
