package aprendamos.java.service;

import aprendamos.java.dao.factory.DAOFactory;
import aprendamos.java.dao.interfaces.ClienteDAO;
import aprendamos.java.dto.ClienteDTO;
import aprendamos.java.util.Constantes;

import java.util.ArrayList;
import java.util.List;

/* Por cada caso de uso de sistema podremos tener un componente service
 La funcionalidad del caso de uso ser� implementada por el servicio
 Un servicio puede llamar a uno o m�s daos (los que necesite)
 */

public class MantenerClienteService {

	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
	ClienteDAO objClienteDAO = fabrica.getClienteDAO();

	public ClienteDTO buscaUsuarioPorId(String email) {
		ClienteDTO objCli = null;
		objCli = objClienteDAO.buscaUsuarioPorId(email);
		System.out.println("Buscamos usando DAO");
		return objCli;
	}

	public List<ClienteDTO> listadoDeClientesPorNombre(String nombre) {
		List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		clientes = objClienteDAO.listadoDeClientesPorNombre(nombre);
		System.out.println("Listamos usando DAO");
		return clientes;
	}

	public void registraNuevoCliente(ClienteDTO objCli){
		objClienteDAO.registraNuevoCliente(objCli);
		System.out.println("Registramos usando DAO");
	}
	
	public void modificaCliente(ClienteDTO objCli){
		objClienteDAO.modificaCliente(objCli);
		System.out.println("Modificamos usando DAO");
	}
	
	public void eliminaCliente(String email){
		objClienteDAO.eliminaClientePorId(email);
		System.out.println("Eliminamos usando DAO");
	}
}