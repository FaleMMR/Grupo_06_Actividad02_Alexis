package modelo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionSingletonMysql {

	private static Connection conexion;
	private static ConexionSingletonMysql conexionSingleton;
	private Properties properties;
	private String nombreficheropropiedades = "acceso_basededatos.properties";

	private ConexionSingletonMysql() {
	}

	public static ConexionSingletonMysql getInstance() {
		if (conexionSingleton == null) {
			conexionSingleton = new ConexionSingletonMysql();
		}
		return conexionSingleton;
	}

	public Connection getConnection() {

		try (InputStream ficheropropiedades = ConexionSingletonMysql.class.getClassLoader().getResourceAsStream(nombreficheropropiedades)) {
			properties = new Properties();
			properties.load(ficheropropiedades);

			String conexionDB = properties.getProperty("URL");
			String nombreBD = properties.getProperty("BASEDEDATOS");
			String usuarioDB = properties.getProperty("USUARIO");
			String passwordDB = properties.getProperty("PASSWORD");

			conexion = DriverManager.getConnection(conexionDB + nombreBD, usuarioDB, passwordDB);
		
		} catch (SQLException e) {
			System.out.println("Error en la conexion con la base de datos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return conexion;
	}

}
