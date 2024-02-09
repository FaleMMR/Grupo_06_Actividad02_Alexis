package controlador;

import java.util.ArrayList;
import java.util.Scanner;

import entidades.Coche;
import entidades.Pasajero;
import modelo.DAOCocheMysql;
import modelo.DAOPasajeroMysql;

public class ControladorPasajero {
	Scanner sc = new Scanner(System.in);
	DAOPasajeroMysql dao = new DAOPasajeroMysql();
	DAOCocheMysql daocoche = new DAOCocheMysql();
	
	public boolean crearPasajero(Pasajero p) {
		
		if(p.getNombre().isEmpty() ) {
			return false;
		}
		
		
		dao.insertarPasajero(p);
		return true;
	}
	
	public boolean  borrarPasajero(int id) {

		Pasajero p = dao.consultarUnPasajero(id);
		if(p != null) {
			dao.borrarPasajero(id);
			return true;
		}else {
			return false;
		}
	}

	
	public Pasajero consultarUnPasajero(int id) {

		Pasajero p = dao.consultarUnPasajero(id);
		if(p != null) {
			return p;
		}
		return null;
	}
	
	
	public 	ArrayList<Pasajero> listarPasajeros() {
		return dao.listarPasajeros();
	}
	
	
	public ArrayList<Pasajero> listarPasajerosDeUnCoche(int idcoche) {
		DAOCocheMysql daocoche = new DAOCocheMysql();
		Coche c = daocoche.consultarUnCoche(idcoche);
		if (c == null) {
			return null;
		}else{
			
			return dao.listarPasajerosDeUnCoche(idcoche);
		}
		
		

	}
	
	public boolean quitarCocheAPasajero(int id) {
		Pasajero p = dao.consultarUnPasajero(id);
		if(p != null) {
			dao.quitarCocheAPasajero(id);
			return true;
		}else {
			return false;
		}
	}

	public boolean anadirPasajeroACoche(int idcoche, int idpasajero) {
		Pasajero p = dao.consultarUnPasajero(idpasajero);
		Coche c = daocoche.consultarUnCoche(idcoche);
		if(p == null) {
			return false;
		}
		if(c == null) {
			return false;
		}
		dao.anadirPasajeroACoche(idcoche,idpasajero);
		return true;
	}

	public void cerrar() {
		dao.cerrar();
	}
}
