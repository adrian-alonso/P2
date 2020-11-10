package p2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Page02 extends Sint101P2 {
  public void phase02 (HttpServletRequest request, HttpServletResponse response, String auto, String pphase) throws IOException, ServletException {
    if(auto==null){
      this.page02HTML(request, response, pphase);
    } else if (!auto.equals("si")) {
      this.page02HTML(request, response, pphase);
    } else {
      this.page02XML(request, response, pphase);
    }
  }

  public void page02HTML(HttpServletRequest request, HttpServletResponse response, String pphase) throws IOException, ServletException {
    String passwd = (String)request.getAttribute("password");


    //HTML DE LA PAGINA phase 02
    //
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
    out.println("</section>");
    out.println("<section>");
    // out.println("<form name=\"form\" method=\"get\">");
    // out.println("<input type=\"submit\" class=\"backButton\" name=\"back\" value=\"Atrás\" onclick=\"?pphase=01\">");
    // out.println("</form>");
    out.println("<a href=\"?pphase=01\">Atr&aacutes</a>");
    out.println("</section>");
    out.println("<footer>");
    out.println("<p>&copy Adri&aacuten Alonso Vilar (2020-2021)</p>");
    out.println("</footer>");
    out.println("</body>");
    out.println("</html>");
  }

  public void page02XML(HttpServletRequest request, HttpServletResponse response, String pphase) throws IOException, ServletException {
  }

}
