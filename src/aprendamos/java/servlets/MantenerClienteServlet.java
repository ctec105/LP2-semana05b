package aprendamos.java.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aprendamos.java.dto.ClienteDTO;
import aprendamos.java.service.MantenerClienteService;

/**
 * Servlet implementation class GestionaMantenimientosServlet
 */

public class MantenerClienteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	// Instanciamos los servicios utilizados por este servlet (por ahora solo uno)
	MantenerClienteService servicio = new MantenerClienteService();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Asumimos que llega un parámetro oculto llamado operacion
		String voperacion = request.getParameter("operacion");
		if (voperacion.equals("listaClientes")) {
			this.listarClientes(request, response);
		}
		if (voperacion.equals("registraProductos")) {

		}
		if (voperacion.equals("registraCliente")) {
			this.registrarCliente(request, response);
		}
		if (voperacion.equals("cargaCliente")) {
			this.cargarCliente(request, response);
		}
		if (voperacion.equals("modificaCliente")) {
			this.modificarCliente(request, response);
		}
		if (voperacion.equals("eliminaCliente")) {
			this.eliminarCliente(request, response);
		}
	}

	private void listarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recuperamos el nombre ingresado en el jsp
		String vnombre = request.getParameter("nombre");
		// invocamos al servicio para ejecutar nuestra lógica
		List<ClienteDTO> listado = servicio.listadoDeClientesPorNombre(vnombre);
		// Cargamos al request el listado de clientes
		request.setAttribute("listadito", listado);
		// invocamos a la página listado.jsp para visualizar los datos obtenidos
		request.getRequestDispatcher("/listado.jsp").forward(request, response);
	}

	private void registrarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recuperamos los parámetros que llegan desde nuevoCliente.jsp
		String vfecha = request.getParameter("fecnac");
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		Date laFecha = null;
		try {
			laFecha = formateador.parse(vfecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ClienteDTO objCliente = null;
		objCliente = new ClienteDTO(request.getParameter("email"),
				request.getParameter("clave"), request.getParameter("nombre"),
				request.getParameter("direccion"), laFecha,
				Long.parseLong(request.getParameter("telefono")));
		// Registramos un nuevo cliente
		servicio.registraNuevoCliente(objCliente);
		// Listamos clientes
		List<ClienteDTO> listado = servicio.listadoDeClientesPorNombre("");
		request.setAttribute("listadito", listado);
		// Invocamos a un componente de la capa view
		request.getRequestDispatcher("/listado.jsp").forward(request, response);
	}

	private void modificarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recuperamos los parámetros que llegan desde modificaCliente.jsp
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		Date laFecha = null;
		try {
			laFecha = f.parse(request.getParameter("fecnac"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ClienteDTO objCli = new ClienteDTO(request.getParameter("email"),
				request.getParameter("clave"), request.getParameter("nombre"),
				request.getParameter("direccion"), laFecha,
				Integer.parseInt(request.getParameter("telefono")));
		// Actualiza el cliente
		servicio.modificaCliente(objCli);
		// Invocamos a un componente de la capa view
		List<ClienteDTO> ggg = servicio.listadoDeClientesPorNombre("");
		// Invocamos a un componente de la capa view
		request.setAttribute("listadito", ggg);
		request.getRequestDispatcher("/listado.jsp").forward(request, response);
	}

	private void cargarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recupera el parámetro (email)
		String vemail = request.getParameter("email");
		// Buscar el email en la BD y retorna el objeto encontrado
		ClienteDTO objCli = servicio.buscaUsuarioPorId(vemail);
		// Carga el cliente encontrado al request
		request.setAttribute("clie", objCli);
		// Invocamos a un componente de la capa view
		RequestDispatcher rd = request.getRequestDispatcher("/modificaCliente.jsp");
		rd.forward(request, response);
	}
	
	private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recupera el parámetro (email)
		String vemail = request.getParameter("email");
		// Buscar el email en la BD y retorna el objeto encontrado
		servicio.eliminaCliente(vemail);
		// Listamos clientes
		List<ClienteDTO> listado = servicio.listadoDeClientesPorNombre("");
		request.setAttribute("listadito", listado);
		// Invocamos a un componente de la capa view
		request.getRequestDispatcher("/listado.jsp").forward(request, response);
	}

}