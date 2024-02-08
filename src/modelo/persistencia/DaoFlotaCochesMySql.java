package modelo.persistencia;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;



import modelo.entidad.Coche;
import modelo.persistencia.interfaces.DaoFlotaCoches;

public class DaoFlotaCochesMySql implements DaoFlotaCoches {
	
private Connection conexion;
private Properties properties;
private String url,usuario, password;
	
	public boolean abrirConexion(){
		/* esto hay que meterlo en un "fichero"
		String url = "jdbc:mysql://localhost:3306/bbdd";
		String usuario = "root";
		String password = "";
		*/
		try {
			// Cargamos el properties
			// tiene que estar dentro de una ruta de classpath
			properties = new Properties();
			properties.load(new FileInputStream(new File("config.properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			conexion = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("usuario"),
					properties.getProperty("password"));
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean alta(Coche c) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "insert into coches (MARCA,MODELO,FABRICACION,KILOMETROS) "
				+ " values(?,?,?,?)";
		try {
			//preparamos la query con valores parametrizables(?)
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, c.getMarca());
			ps.setString(2, c.getModelo());
			ps.setInt(3, c.getFechaFabricacion());
			ps.setDouble(4, c.getKilometros());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				alta = false;
			}
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + c);
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return alta;
	}

	@Override
	public boolean baja(int idCoche) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(Coche c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Coche obtener(int idCoche) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coche> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
	
	
	
	

