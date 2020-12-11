package aprendamos.java.dao.interfaces;

import java.util.List;

import aprendamos.java.dto.ClienteDTO;

/*
 Una interfaz es una clase completamente abstracta, es decir es una clase sin implementaci�n
 No es necesario ponerle abstract ya que lo son implicitamente
 Si adicionalmente tiene miembros datos, estos seran constantes static y final
 */

public interface ClienteDAO {

	// METODO QUE ELIMINAR UN CLIENTE POR EMAIL
	public abstract void eliminaClientePorId(String email);

	// M�TODO QUE REGISTRA UN NUEVO CLIENTE
	public void registraNuevoCliente(ClienteDTO objCli);
	
	// M�TODO QUE MODIFICA UN CLIENTE
	public void modificaCliente(ClienteDTO objCli);

	// METODO QUE BUSCA CLIENTES POR SU ID
	public ClienteDTO buscaUsuarioPorId(String email);

	// METODO PARA LISTAR CLIENTES POR UN DETERMINADO NOMBRE O LETRA
	public List<ClienteDTO> listadoDeClientesPorNombre(String nombre);

}