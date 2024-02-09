package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Coche;
import entidades.Pasajero;

public class DAOPasajeroMysql implements IDAOPasajero {

	Connection conexion = ConexionSingletonMysql.getInstance().getConnection();
	DAOCocheMysql daocoche = new DAOCocheMysql();

	public void insertarPasajero(Pasajero unpasajero) {

		String ordensql = "INSERT INTO pasajero VALUES (0,?,?,?,null)";

		try {
			PreparedStatement pre = conexion.prepareStatement(ordensql);
			pre.setString(1, unpasajero.getNombre());
			pre.setInt(2, unpasajero.getEdad());
			pre.setDouble(3, unpasajero.getPeso());
			pre.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void borrarPasajero(int idpasajero) {

		String ordensql = "DELETE FROM pasajero WHERE id = ?";

		try {
			PreparedStatement pre = conexion.prepareStatement(ordensql);
			pre.setInt(1, idpasajero);
			pre.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Pasajero> listarPasajeros() {
		ArrayList<Pasajero> lista = new ArrayList<>();

		String ordensql = "SELECT * FROM pasajero";

		try {
			Statement pre = conexion.createStatement();
			ResultSet registros = pre.executeQuery(ordensql);
			while (registros.next()) {
				String nombre = registros.getString("nombre");
				int edad = registros.getInt("edad");
				double peso = registros.getInt("peso");
				int id = registros.getInt("id");
				Pasajero c = new Pasajero(id, nombre, edad, peso);

				int idcoche = registros.getInt("idcoche");
				if (idcoche != 0) {
					Coche cocheasociado = daocoche.consultarUnCoche(idcoche);
					c.setCoche(cocheasociado);
				}

				lista.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;

	}

	public Pasajero consultarUnPasajero(int elidquebusco) {
		Pasajero c = null;

		String ordensql = "SELECT * FROM pasajero where id = ?";

		try {
			PreparedStatement pre = conexion.prepareStatement(ordensql);

			pre.setInt(1, elidquebusco);

			ResultSet registros = pre.executeQuery();
			while (registros.next()) {
				String nombre = registros.getString("nombre");
				int edad = registros.getInt("edad");
				double peso = registros.getInt("peso");
				int id = registros.getInt("id");
				c = new Pasajero(id, nombre, edad, peso);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;

	}

	@Override
	public void anadirPasajeroACoche(int idcoche, int idpasajero) {

		String ordensql = "UPDATE pasajero SET idcoche=? WHERE id = ?";

		try {
			PreparedStatement pre = conexion.prepareStatement(ordensql);
			pre.setInt(1, idcoche);
			pre.setInt(2, idpasajero);
			pre.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void quitarCocheAPasajero(int idpasajero) {
		String ordensql = "UPDATE pasajero SET idcoche=null WHERE id = ?";

		try {
			PreparedStatement pre = conexion.prepareStatement(ordensql);
			pre.setInt(1, idpasajero);
			pre.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Pasajero> listarPasajerosDeUnCoche(int idcoche) {
		ArrayList<Pasajero> lista = new ArrayList<>();

		Coche cocheasociado = daocoche.consultarUnCoche(idcoche);

		String ordensql = "SELECT * FROM pasajero where idcoche=?";

		try {

			PreparedStatement pre = conexion.prepareStatement(ordensql);
			pre.setInt(1, idcoche);
			ResultSet registros = pre.executeQuery();
			while (registros.next()) {
				String nombre = registros.getString("nombre");
				int edad = registros.getInt("edad");
				double peso = registros.getInt("peso");
				int id = registros.getInt("id");
				Pasajero c = new Pasajero(id, nombre, edad, peso);
				c.setCoche(cocheasociado);

				lista.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public void cerrar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
