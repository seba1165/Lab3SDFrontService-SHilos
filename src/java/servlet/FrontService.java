/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Seba
 */
@WebServlet(name = "FrontService", urlPatterns = {"/FrontService"})
public class FrontService extends HttpServlet {
    static String respuesta;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String query = request.getParameter("query");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FrontService</title>");            
            out.println("</head>");
            out.println("<body>");
            ArrayList Stopwords = leerStopWords("C:\\Users\\Seba\\Documents\\NetBeansProjects\\FrontServiceServer\\Stopwords.txt");
            File archivo = new File("C:\\Users\\Seba\\Documents\\NetBeansProjects\\FrontServiceServer\\Config.txt");
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            //Lineas del Config.txt
            //Linea 1 tiene la cantidad de particiones de la bd
            String linea1 = br.readLine();
            String linea2 = br.readLine();
            String linea3 = br.readLine();
            String LineaCaching[] = linea1.split(" ");
            String LineaIndex[] = linea2.split(" ");
            String LineaPuerto[] = linea3.split(" ");

            String ipCaching = (LineaCaching[1]);

            String ipIndex = (LineaIndex[1]);

            String puerto = (LineaPuerto[1]);

            fr.close();
            
            //HiloFrontService hilo = new HiloFrontService(query, respuesta, Stopwords, ipCaching, ipIndex, puerto);
            //Thread hilo2 = new Thread(hilo);
            //hilo2.start();
            //out.println(respuesta);
            HiloFrontService busca = new HiloFrontService(query, Stopwords, ipCaching, ipIndex, puerto);
            out.println("<h1>RESULTADOS: "+"</h1>");
            //out.println("<h3>"+busca.respuesta+"</h3>");
            if (busca.desdeIndex.equals(" ")){
                out.println("<h5>"+busca.desdeCaching+"</h5>");
            }else{
                out.println("<h5>"+busca.desdeIndex+"</h5>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    public ArrayList leerStopWords(String ruta) {
        ArrayList Stopwords = new ArrayList();
        FileReader fr = null;
        BufferedReader br = null;
        //Cadena de texto donde se guardara el contenido del archivo
        String contenido = "";
        try {
            fr = new FileReader(ruta);
            br = new BufferedReader(fr);
            String linea;
            //Obtenemos el contenido del archivo linea por linea
            while ((linea = br.readLine()) != null) {
                Stopwords.add(linea);
            }

        } catch (Exception e) {
        } //finally se utiliza para que si todo ocurre correctamente o si ocurre
        //algun error se cierre el archivo que anteriormente abrimos
        finally {
            try {
                br.close();
            } catch (Exception ex) {
            }
        }
        return Stopwords;
    }
  
}
