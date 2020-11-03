import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Sint101P2 extends HttpServlet {

  public void init (ServletConfig config) throws ServletException {
    try {

    } catch(Exception e) {

    }
  }

  public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String pphase = request.getParameter("pphase");
    String passwd = request.getParameter("p");

    if (pphase == null) {
      this.phase01(request, response);
    } else {

    }

    // out.println("");
    // out.println("");
    // out.println("");
    // out.println("");
    // out.println("");
    // out.println("");
    // out.println("");
    // out.println("");
    // out.println("");
    // out.println("");
    // out.println("");
    // out.println("");
    // out.println("");
    // out.println("");
    // out.println("");

  }

  public void phase01 (HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

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
    out.println("<p>Bienvenido a este servicio</p>");
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
