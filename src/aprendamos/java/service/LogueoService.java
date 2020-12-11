package aprendamos.java.service;

import aprendamos.java.dao.factory.DAOFactory;
import aprendamos.java.dao.interfaces.ClienteDAO;
import aprendamos.java.dto.ClienteDTO;
import aprendamos.java.util.Constantes;

/* Por cada caso de uso de sistema podremos tener un componente service
 La funcionalidad del caso de uso ser� implementada por el servicio
 Un servicio puede llamar a uno o m�s daos (los que necesite)
 */

public class LogueoService {

	// Referenciamos la f�brica espec�fica de daos y le indicamos nuestro origen de datos (MYSQL)
	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);

	// Le pedimos a la f�brica espec�fica el o los daos que necesitemos
	ClienteDAO objClienteDAO = fabrica.getClienteDAO();

	// Operaciones
	public ClienteDTO validaUsuario(ClienteDTO objcliente) {
		ClienteDTO objCli = null;
		// Antes de llamar al dao podemos tener l�gica de negocio
		objCli = objClienteDAO.buscaUsuarioPorId(objcliente.getEmail());
		// Despu�s de llamar al dao podemos tener tambi�n l�gica de negocio
		System.out.println("Hemos validado con DAO :)");
		return objCli;
	}

}