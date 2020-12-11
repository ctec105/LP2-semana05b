package aprendamos.java.dao.factory;

import aprendamos.java.dao.interfaces.ClienteDAO;
import aprendamos.java.dao.interfaces.FacturaDAO;
import aprendamos.java.dao.interfaces.ProductoDAO;
import aprendamos.java.dao.xml.XMLClienteDAO;

public class XmlDAOFactory extends DAOFactory {

	@Override
	public ClienteDAO getClienteDAO() {
		// TODO Auto-generated method stub
		return new XMLClienteDAO();
	}

	@Override
	public ProductoDAO getProductoDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FacturaDAO getFacturaDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}