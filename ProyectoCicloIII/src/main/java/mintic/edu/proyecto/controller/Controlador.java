package mintic.edu.proyecto.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mintic.edu.proyecto.dao.dao_user;
import mintic.edu.proyecto.modelo.modelo_user;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author luisj
 */
@SpringBootApplication(scanBasePackages="controller")
public class Controlador extends HttpServlet {

    modelo_user usuario = new modelo_user();
    dao_user usuarioDao = new dao_user();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("Usuarios")) {
            switch (accion) {
                case "Listar":
                    String tipos[] = {"Administrador", "Cliente"};
                    request.setAttribute("usuarios", usuarioDao.getUsuarios());
                    request.setAttribute("tipos", tipos);
                    request.setAttribute("usuarioEdit", new modelo_user());
                    break;
                case "Buscar":
                    int idb = Integer.valueOf(request.getParameter("txtId"));
                    modelo_user busu = new modelo_user();
                    String[] categoriasb = {"Administrador", "Cliente"};
                    busu = usuarioDao.getUsuarioId(idb);
                    request.setAttribute("usuarioEdit", busu);
                    request.setAttribute("categorias", categoriasb);
                    break;
                case "Agregar":
                    int idUsuario = Integer.parseInt(request.getParameter("txtId"));
                    String clave = request.getParameter("txtClave");
                    String nombreUsuario = request.getParameter("txtNombre");
                    String correo = request.getParameter("txtCorreo");
                    String tipoUsuario = request.getParameter("txtTipo");
                    usuario.setCedulaUsuario(idUsuario);
                    usuario.setClave(clave);
                    usuario.setCorreo(correo);
                    usuario.setNombreUsuario(nombreUsuario);
                    usuario.setTipoUsuario(tipoUsuario);
                    boolean creado = usuarioDao.agregarUsuario(usuario);
                    String mensaje = "";
                    if (creado) {
                        mensaje = "Usuario Creado";
                    } else {
                        mensaje = "Faltan Datos del Usuario";
                    }
                    request.setAttribute("mensaje", mensaje);
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    int ideu = Integer.valueOf(request.getParameter("id"));
                    modelo_user usu = new modelo_user();
                    String[] categorias = {"Administrador", "Cliente"};
                    usu = usuarioDao.getUsuarioId(ideu);
                    request.setAttribute("usuarioEdit", usu);
                    request.setAttribute("categorias", categorias);
                    break;
                case "Actualizar":
                    int idUsuarioa = Integer.parseInt(request.getParameter("txtId"));
                    String clavea = request.getParameter("txtClave");
                    String nombreUsuarioa = request.getParameter("txtNombre");
                    String correoa = request.getParameter("txtCorreo");
                    String tipoUsuarioa = request.getParameter("txtTipo");
                    usuario.setCedulaUsuario(idUsuarioa);
                    usuario.setClave(clavea);
                    usuario.setCorreo(correoa);
                    usuario.setNombreUsuario(nombreUsuarioa);
                    usuario.setTipoUsuario(tipoUsuarioa);
                    usuarioDao.actualizarUsuario(usuario);
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    int idUsuarioe = Integer.valueOf(request.getParameter("id"));
                    usuarioDao.eliminarUsuario(idUsuarioe);
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("jsp/usuarios.jsp").forward(request, response);
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

}