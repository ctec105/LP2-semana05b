package aprendamos.java.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aprendamos.java.dao.interfaces.ClienteDAO;
import aprendamos.java.dto.ClienteDTO;
import aprendamos.java.util.MySqlConexion;

/*Por cada entidad de nuestro modelo de datos normalmente tendremos un DAO

 Un DAO define todas las operaciones que necesitemos realizar sobre una entidad

 Un DAO expone sus métodos al mundo a través de su interface

 La clase nos dice "cómo" vamos a hacerlo :)*/

public class MySqlClienteDAO implements ClienteDAO {

	// METODO QUE ELIMINAR UN CLIENTE POR EMAIL
	@Override
	public void eliminaClientePorId(String email) {
		// Obtenemos la conexión
		Connection cn = MySqlConexion.obtenerConexion();
		try {
			// Definimos la sentencia a ejecutar
			String sql = "delete from tb_cliente where mail = ?";
			// La preparamos
			PreparedStatement st = cn.prepareStatement(sql);
			// Asignamos valores a las interrogantes
			st.setString(1, email);
			// Ejecutamos la sentencia
			st.executeUpdate();
			// Cerrar la conexión
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// MÉTODO QUE REGISTRA UN NUEVO CLIENTE
	@Override
	public void registraNuevoCliente(ClienteDTO objCli) {
		Connection cn = MySqlConexion.obtenerConexion();
		try {
			// Definimos la sentecia
			String sentencia = "insert into tb_cliente(mail,clave,"
					+ "nombre,direccion,fecnac,telefono ) "
					+ " values (?,?,?,?,?,?) ";
			PreparedStatement pst = cn.prepareStatement(sentencia);
			// Asignamos valores a las interrogantes
			pst.setString(1, objCli.getEmail());
			pst.setString(2, objCli.getClave());
			pst.setString(3, objCli.getNombre());
			pst.setString(4, objCli.getDireccion());
			java.sql.Date laFecha = new java.sql.Date(objCli.getFecnac().getTime());
			pst.setDate(5, laFecha);
			pst.setLong(6, objCli.getTelefono());
			// Ejecutamos
			pst.executeUpdate();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// MÉTODO QUE MODIFICA UN CLIENTE
	@Override
	public void modificaCliente(ClienteDTO objCli) {
		Connection cn = MySqlConexion.obtenerConexion();
		try {
			// Definimos la sentencia
			String sentencia = "update tb_cliente set clave=?, nombre=?, "
					+ "direccion=?, fecnac=?, telefono=? where mail=?";
			// La preparamos
			PreparedStatement pst = cn.prepareStatement(sentencia);
			// Asignamos valores a las interrogantes
			pst.setString(1, objCli.getClave());
			pst.setString(2, objCli.getNombre());
			pst.setString(3, objCli.getDireccion());
			java.sql.Date laFecha = new java.sql.Date(objCli.getFecnac().getTime());
			pst.setDate(4, laFecha);
			pst.setLong(5, objCli.getTelefono());
			pst.setString(6, objCli.getEmail());
			// Ejecutamos
			pst.executeUpdate();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// METODO QUE BUSCA CLIENTES POR SU ID
	@Override
	public ClienteDTO buscaUsuarioPorId(String email) {
		ClienteDTO objCli = null;
		Connection cn = MySqlConexion.obtenerConexion();
		try {
			// Definimos la sentencia
			String sentencia = "select mail,clave,nombre,"
					+ "direccion,fecnac,telefono "
					+ "from tb_cliente where mail = ? ";
			// La preparamos
			PreparedStatement pst = cn.prepareStatement(sentencia);
			// Asignamos valores a las interrogantes
			pst.setString(1, email);
			// Ejecutamos
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				// caso de exito, si existe el email
				objCli = new ClienteDTO(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getLong(6));
			}
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objCli;
	}

	// METODO PARA LISTAR CLIENTES POR UN DETERMINADO NOMBRE O LETRA
	@Override
	public List<ClienteDTO> listadoDeClientesPorNombre(String nombre) {
		Connection cn = MySqlConexion.obtenerConexion();
		List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		try {
			// Definimos la sentencia
			String sentencia = "select mail,clave,nombre,"
					+ "direccion,fecnac,telefono "
					+ "from tb_cliente where nombre like ? ";
			// La preparamos
			PreparedStatement pst = cn.prepareStatement(sentencia);
			// Asignamos valores a las interrogantes
			pst.setString(1, "%" + nombre + "%");
			// Ejecutamos
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// Caso de exito, si existe el email
				ClienteDTO objCli = new ClienteDTO(rs.getString(1),
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDate(5), rs.getLong(6));
				clientes.add(objCli);
			}
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	
}