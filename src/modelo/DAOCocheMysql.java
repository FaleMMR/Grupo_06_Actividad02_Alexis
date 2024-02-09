package modelo;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Coche;

public class DAOCocheMysql implements IDAOCoche {
	Connection conexion = ConexionSingletonMysql.getInstance().getConnection();
		
	public void insertarCoche( Coche uncoche ) {
	
		String ordensql = "INSERT INTO coche VALUES (0,?,?,?,?)";
		
		try {
			PreparedStatement pre = conexion.prepareStatement(ordensql);
			pre.setString(1, uncoche.getMarca());
			pre.setString(2, uncoche.getModelo());
			pre.setInt(3, uncoche.getAnio());
			pre.setInt(4, uncoche.getKm());
			pre.execute();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void borrarCoche( int idcoche ) {
		
		
		String ordensql = "DELETE FROM coche WHERE id = ?";
		
		try {
			PreparedStatement pre = conexion.prepareStatement(ordensql);
			pre.setInt(1, idcoche);
			pre.execute();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void modificarCoche( Coche uncoche) {
	
		
		String ordensql = "UPDATE coche SET marca=?, modelo=?, anio=?, km=? WHERE id = ?";
		
		try {
			PreparedStatement pre = conexion.prepareStatement(ordensql);
			pre.setString(1, uncoche.getMarca());
			pre.setString(2, uncoche.getModelo());
			pre.setInt(3, uncoche.getAnio());
			pre.setInt(4, uncoche.getKm());
			pre.setInt(5, uncoche.getId());
			pre.execute();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public 	ArrayList<Coche> listarCoches() {
		ArrayList<Coche> lista = new ArrayList<>();
		
		String ordensql = "SELECT * FROM coche";
		
		try {
			Statement pre = conexion.createStatement();
			ResultSet registros = pre.executeQuery(ordensql);
			while(registros.next()) {
				String marca = registros.getString("marca");				
				String modelo = registros.getString("modelo");				
				int anio = registros.getInt("anio");	
				int km = registros.getInt("km");		
				int id = registros.getInt("id");	
				Coche c = new Coche(id,marca, modelo, anio, km);
				lista.add(c);
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
		
	}
	
	public 	Coche consultarUnCoche(int elidquebusco) {
	    Coche c = null;
		
		String ordensql = "SELECT * FROM coche where id=?";
		
		try {
			PreparedStatement pre = conexion.prepareStatement(ordensql);
		
			pre.setInt(1, elidquebusco);
					
			ResultSet registros = pre.executeQuery();
			while(registros.next()) {
				String marca = registros.getString("marca");				
				String modelo = registros.getString("modelo");				
				int anio = registros.getInt("anio");	
				int km = registros.getInt("km");		
				int id = registros.getInt("id");	
				c = new Coche(id,marca, modelo, anio, km);
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;

	}

	
	public void cerrar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
