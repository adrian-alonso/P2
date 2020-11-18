package p2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;

public class FrontEnd {

  //FASE 01
  //Seleccion modo pantalla (auto/browser)
  public void phase01 (HttpServletRequest request, HttpServletResponse response, String pphase) throws IOException, ServletException {
    String auto = request.getParameter("auto");

    if(auto==null){
      this.page01HTML(request, response, pphase);
    } else if (!auto.equals("true")) {
      this.page01HTML(request, response, pphase);
    } else {
      this.page01XML(request, response, pphase);
    }
  }

  //HTML DE LA PAGINA
  public void page01HTML(HttpServletRequest request, HttpServletResponse response, String pphase) throws IOException, ServletException {
    String password = request.getParameter("p");

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
    out.println("<h2><a href=\"\">Bienvenido a este servicio</a></h2>");
    out.println("<form name=\"form\" method=\"get\" >");
    out.println("<a href=\"?pphase=02&p=" + password + "\">Ver los ficheros err&oacuteneos</a>");
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
    out.println("<a href=\"?pphase=11&p=" + password + "\">Consulta 1: Ver los alumnos de una asignatura de una titulaci&oacuten</a>");
    out.println("</section>");
    out.println("<footer>");
    out.println("<hr>");
    out.println("<p>&copy Adri&aacuten Alonso Vilar (2020-2021)</p>");
    out.println("</footer>");
    out.println("</body>");
    out.println("</html>");
  }

  //MODO AUTO: PRESENTACION EN XML
  public void page01XML(HttpServletRequest request, HttpServletResponse response, String pphase) throws IOException, ServletException {
    response.setContentType("text/xml");
    PrintWriter out = response.getWriter();

    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    out.println("<service>");
    out.println("<status>OK</status>");
    out.println("</service>");
  }

  //FASE 02
  //Seleccion modo pantalla (auto/browser)
  public void phase02 (HttpServletRequest request, HttpServletResponse response, String pphase) throws IOException, ServletException {
    String auto = request.getParameter("auto");

    if(auto==null){
      this.page02HTML(request, response, pphase);
    } else if (!auto.equals("true")) {
      this.page02HTML(request, response, pphase);
    } else {
      this.page02XML(request, response, pphase);
    }
  }

  //HTML DE LA PAGINA
  public void page02HTML(HttpServletRequest request, HttpServletResponse response, String pphase) throws IOException, ServletException {
    String password = request.getParameter("p");

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
    out.println("<h2>warnings:" + password +"</h2>");
    out.println("<p>errores</p>");
    out.println("</section>");
    out.println("<section>");
    // out.println("<form name=\"form\" method=\"get\">");
    // out.println("<input type=\"submit\" class=\"backButton\" name=\"back\" value=\"Atrás\" onclick=\"?pphase=01\">");
    // out.println("</form>");
    out.println("<a href=\"?pphase=01&p=" + password + "\">Atr&aacutes</a>");
    out.println("</section>");
    out.println("<footer>");
    out.println("<hr>");
    out.println("<p>&copy Adri&aacuten Alonso Vilar (2020-2021)</p>");
    out.println("</footer>");
    out.println("</body>");
    out.println("</html>");
  }

  //MODO AUTO: PRESENTACION EN XML
  public void page02XML(HttpServletRequest request, HttpServletResponse response, String pphase) throws IOException, ServletException {
    response.setContentType("text/xml");
    PrintWriter out = response.getWriter();

    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    out.println("");
  }

  //FASE 11
  //Seleccion modo pantalla (auto/browser)
  public void phase11 (HttpServletRequest request, HttpServletResponse response, String pphase) throws IOException, ServletException {
    String auto = request.getParameter("auto");

    ArrayList<String> degrees = new ArrayList<String>();
    degrees.add("Teleco");
    degrees.add("Industriales");
    if(auto==null){
      this.page11HTML(request, response, pphase);
    } else if (!auto.equals("true")) {
      this.page11HTML(request, response, pphase);
    } else {
      this.page11XML(request, response, pphase, degrees);
    }
  }

  //HTML DE LA PAGINA
  public void page11HTML(HttpServletRequest request, HttpServletResponse response, String pphase) throws IOException, ServletException {
    String password = request.getParameter("p");

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
    out.println("<h2>Consulta 1: Fase 1</h2>");
    out.println("<p>Selecciona :</p>");
    out.println("</section>");
    out.println("<section>");
    // out.println("<form name=\"form\" method=\"get\">");
    // out.println("<input type=\"submit\" class=\"homeButton\" value=\"Inicio\" onclick=\"form.pphase.value=01\">");
    // out.println("</form>");
    out.println("<a href=\"?pphase=01&p=" + password + "\">Inicio</a>");
    out.println("</section>");
    out.println("<footer>");
    out.println("<hr>");
    out.println("<p>&copy Adri&aacuten Alonso Vilar (2020-2021)</p>");
    out.println("</footer>");
    out.println("</body>");
    out.println("</html>");
  }

  //MODO AUTO: PRESENTACION EN XML
  public void page11XML(HttpServletRequest request, HttpServletResponse response, String pphase, ArrayList<String> degrees) throws IOException, ServletException {
    response.setContentType("text/xml");
    PrintWriter out = response.getWriter();
    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    out.println("<degrees>");
    for (int i=0; i<degrees.size(); i++){
      out.println("<degree>" + degrees.get(i) + "</degree>");
    }
    out.println("</degrees>");
  }

  //FASE 12
  //Seleccion modo pantalla (auto/browser)
  public void phase12 (HttpServletRequest request, HttpServletResponse response, String pphase, String pdegree) throws IOException, ServletException {
    String auto = request.getParameter("auto");

    if (pdegree == null) {
      if(auto==null){
        this.noParamHTML(request, response, "pdegree");
      } else if (!auto.equals("true")) {
        this.noParamHTML(request, response, "pdegree");
      } else {
        this.noParamXML(request, response, "pdegree");
      }
    } else {
      if(auto==null){
        this.page12HTML(request, response, pphase, pdegree);
      } else if (!auto.equals("true")) {
        this.page12HTML(request, response, pphase, pdegree);
      } else {
        this.page12XML(request, response, pphase, pdegree);
      }
    }
  }

  //HTML DE LA PAGINA
  public void page12HTML(HttpServletRequest request, HttpServletResponse response, String pphase, String pdegree) throws IOException, ServletException {
    String password = request.getParameter("p");

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
    out.println("<section>");
    // out.println("<form name=\"form\" method=\"get\">");
    // out.println("<input type=\"submit\" class=\"homeButton\" value=\"Inicio\" onclick=\"form.pphase.value=01\">");
    // out.println("<input type=\"submit\" class=\"backButton\" value=\"Atrás\" onclick=\"form.pphase.value=11\">");
    // out.println("</form>");
    out.println("<a href=\"?pphase=01&p=" + password + "\">Inicio</a>");
    out.println("<a href=\"?pphase=11&p=" + password + "\">Atr&aacutes</a>");
    out.println("</section>");
    out.println("<footer>");
    out.println("<hr>");
    out.println("<p>&copy Adri&aacuten Alonso Vilar (2020-2021)</p>");
    out.println("</footer>");
    out.println("</body>");
    out.println("</html>");
  }

  //MODO AUTO: PRESENTACION EN XML
  public void page12XML(HttpServletRequest request, HttpServletResponse response, String pphase, String pdegree/*, ArrayList<Subject> subjects*/) throws IOException, ServletException {
    response.setContentType("text/xml");
    PrintWriter out = response.getWriter();

    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    out.println("<subjects>");
    /*for (int i=0; i<subjects.size(); i++){
      out.println("<subject course=\"" + subjects.get(i).getCourse + "\" type=\"" + subjects.get(i).getType + "\">" + subjects.get(i).getSubjectName + "</subject>");
    }*/
    out.println("</subjects>");
  }

  //FASE 13
  //Seleccion modo pantalla (auto/browser)
  public void phase13 (HttpServletRequest request, HttpServletResponse response, String pphase, String pdegree, String psubject) throws IOException, ServletException {
    String auto = request.getParameter("auto");
    if (pdegree == null) {
      if(auto==null){
        this.noParamHTML(request, response, "pdegree");
      } else if (!auto.equals("true")) {
        this.noParamHTML(request, response, "pdegree");
      } else {
        this.noParamXML(request, response, "pdegree");
      }
    } else {
      if (psubject == null){
        if(auto==null){
          this.noParamHTML(request, response, "psubject");
        } else if (!auto.equals("true")) {
          this.noParamHTML(request, response, "psubject");
        } else {
          this.noParamXML(request, response, "psubject");
        }
      } else {
        if(auto==null){
          this.page13HTML(request, response, pphase, pdegree, psubject);
        } else if (!auto.equals("true")) {
          this.page13HTML(request, response, pphase, pdegree, psubject);
        } else {
          this.page13XML(request, response, pphase, pdegree, psubject);
        }
      }
    }
  }

  //HTML DE LA PAGINA
  public void page13HTML(HttpServletRequest request, HttpServletResponse response, String pphase, String pdegree, String psubject) throws IOException, ServletException {
    String password = request.getParameter("p");

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
    out.println("<h2>Consulta 1: Fase 3 (Titulaci&oacuten=" + pdegree + ", Asignatura=" + psubject + ")</h2>");
    out.println("<p>Este es el resultado:</p>");
    out.println("</section>");
    out.println("<section>");
    // out.println("<form name=\"form\" method=\"get\">");
    // out.println("<input type=\"submit\" class=\"homeButton\" value=\"Inicio\" onclick=\"form.pphase.value=\"01\"\">");
    // out.println("<input type=\"submit\" class=\"backButton\" value=\"Atrás\" onclick=\"pphase.value=\"12\"\">");
    // out.println("</form>");
    out.println("<a href=\"?pphase=01&p=" + password + "\">Inicio</a>");
    out.println("<a href=\"?pphase=12&p=" + password + "&pdegree=" + pdegree + "\">Atr&aacutes</a>");
    out.println("</section>");
    out.println("<footer>");
    out.println("<hr>");
    out.println("<p>&copy Adri&aacuten Alonso Vilar (2020-2021)</p>");
    out.println("</footer>");
    out.println("</body>");
    out.println("</html>");
  }

  //MODO AUTO: PRESENTACION EN XML
  public void page13XML(HttpServletRequest request, HttpServletResponse response, String pphase, String pdegree, String psubject/*, ArrayList<Student> students*/) throws IOException, ServletException {
    response.setContentType("text/xml");
    PrintWriter out = response.getWriter();

    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    out.println("<students>");
    /*for (int i=0; i<student.size(); i++){
      out.println("<student course=\"" + student.get(i).getCourse + "\" type=\"" + student.get(i).getType + "\">" + student.get(i).getSubjectName + "</student>");
    }*/
    out.println("</students>");
  }


  //ERRORES

  //Falta contraseña
  //HTML DE LA PAGINA
  public void noPasswordHTML(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
    out.println("<p>Wrong Request: no passwd</p>");
    out.println("<p>Intruduzca el par&aacute;metro p con la correspondiente contrase&ntilde;a.</p>");
    out.println("</section>");
    out.println("<footer>");
    out.println("<hr>");
    out.println("<p>&copy Adri&aacuten Alonso Vilar (2020-2021)</p>");
    out.println("</footer>");
    out.println("</body>");
    out.println("</html>");
  }

  //MODO AUTO: PRESENTACION EN XML
  public void noPasswordXML(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    response.setContentType("text/xml");
    PrintWriter out = response.getWriter();

    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    out.println("<wrongRequest>no passwd</wrongRequest>");
  }

  //Contraseña erronea
  //HTML DE LA PAGINA
  public void badPasswordHTML(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
    out.println("<p>Wrong Request: bad passwd</p>");
    out.println("<p>Contrase&ntilde;a incorrecta. Intruduzca el par&aacute;metro p correcto.</p>");
    out.println("</section>");
    out.println("<footer>");
    out.println("<hr>");
    out.println("<p>&copy Adri&aacuten Alonso Vilar (2020-2021)</p>");
    out.println("</footer>");
    out.println("</body>");
    out.println("</html>");
  }

  //MODO AUTO: PRESENTACION EN XML
  public void badPasswordXML(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    response.setContentType("text/xml");
    PrintWriter out = response.getWriter();

    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    out.println("<wrongRequest>bad passwd</wrongRequest>");
  }

  //Falta parametro obligatorio (pdegree/psubject)
  //HTML DE LA PAGINA
  public void noParamHTML(HttpServletRequest request, HttpServletResponse response, String param) throws IOException, ServletException {
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
    out.println("<p>Wrong Request: no param:" + param + "</p>");
    out.println("<p>Par&aacute;metro " + param + " necesario. Introduzca su valor.</p>");
    out.println("</section>");
    out.println("<footer>");
    out.println("<hr>");
    out.println("<p>&copy Adri&aacuten Alonso Vilar (2020-2021)</p>");
    out.println("</footer>");
    out.println("</body>");
    out.println("</html>");
  }

  //MODO AUTO: PRESENTACION EN XML
  public void noParamXML(HttpServletRequest request, HttpServletResponse response, String param) throws IOException, ServletException {
    String pdegree = request.getParameter("pdegree");
    String psubject = request.getParameter("psubject");

    response.setContentType("text/xml");
    PrintWriter out = response.getWriter();

    out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    out.println("<wrongRequest>bad passwd</wrongRequest>");
  }
}
