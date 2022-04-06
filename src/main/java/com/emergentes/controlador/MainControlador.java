package com.emergentes.controlador;

import com.emergentes.modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainControlador", urlPatterns = {"/MainControlador"})
public class MainControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        if (ses.getAttribute("listapro") == null) {
            ArrayList<Producto> listaux = new ArrayList<Producto>();
            ses.setAttribute("listapro", listaux);
        }
        
        ArrayList<Producto> lista = (ArrayList<Producto>) ses.getAttribute("listapro") ;
        String op = request.getParameter("op");
        String opcion = (op != null) ? op : "view";
        Producto obj1 = new Producto();
        int id, pos;
        
        switch(opcion) {
            case "nuevo":
                request.setAttribute("miPersona", obj1);
                request.getRequestDispatcher("edicion.jsp").forward(request, response);
                break;
            case "editar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarIndice(request,id);
                obj1 = lista.get(pos);
                request.setAttribute("miPersona", obj1);
                request.getRequestDispatcher("edicion.jsp").forward(request, response);
                break;
            case "eliminar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarIndice(request,id);
                lista.remove(pos);
                //ses.setAttribute("listapro", lista);
                response.sendRedirect("index.jsp");
            case "view":
                response.sendRedirect("index.jsp");
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession ses = request.getSession();
        ArrayList<Producto> lista = (ArrayList<Producto>) ses.getAttribute("listapro") ;
        Producto obj1 = new Producto();
        obj1.setId(Integer.parseInt(request.getParameter("id")));
        obj1.setDescripcion(request.getParameter("descripcion"));
        obj1.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        obj1.setPrecio(Integer.parseInt(request.getParameter("precio")));
        obj1.setCategoria(request.getParameter("categoria"));
        
        int idt = obj1.getId();
        if (idt == 0) {
            // Nuevo
            int ultID;
            ultID = ultimoId(request);
            obj1.setId(ultID);
            lista.add(obj1);
        }
        else {
            // edicion
            lista.set(buscarIndice(request,idt), obj1);
            
        }
        ses.setAttribute("listapro", lista);
        response.sendRedirect("index.jsp");
    }
    private int buscarIndice(HttpServletRequest request, int id) {
        HttpSession ses = request.getSession();
        ArrayList<Producto> lista = (ArrayList<Producto>) ses.getAttribute("listapro") ;
        int i = 0 ;
        if (lista.size() > 0) {
            while (i < lista.size()) {
                if (lista.get(i).getId() == id) {
                    break;
                }
                else {
                    i++;
                }
            }
        }
        return i;
        
    }
    
    private int ultimoId(HttpServletRequest request) {
        HttpSession ses = request.getSession();
        ArrayList<Producto> lista = (ArrayList<Producto>) ses.getAttribute("listapro") ;
        int idaux = 0 ;
        for (Producto item: lista) {
            idaux = item.getId();
        }
        return idaux + 1;
        
    }


}
