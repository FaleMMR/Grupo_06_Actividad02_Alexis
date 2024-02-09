package controlador;

import java.util.ArrayList;
import java.util.Scanner;

import entidades.Coche;
import modelo.DAOCocheMysql;

public class ControladorCoche {

	Scanner sc = new Scanner(System.in);
	DAOCocheMysql dao = new DAOCocheMysql();
	
	public boolean crearCoche(Coche coche) {
		if(coche.getMarca().isEmpty() | coche.getModelo().isEmpty()) {
			return false;
		}
		dao.insertarCoche(coche);
		return true;
	}
	
	public boolean borrarCoche(int id) {

		Coche coche = dao.consultarUnCoche(id);
		if(coche != null) {
			dao.borrarCoche(id);
			return true;
		}else {
			return false;
		}
	}
	
	
	public Coche consultarCoche(int id) {

		Coche coche = dao.consultarUnCoche(id);
		if(coche != null) {
			return coche;
		}
		return null;
	}
	
	public int modificarCoche(Coche c) {
		Coche coche = dao.consultarUnCoche(c.getId());
		if(coche != null) {
			if(coche.getMarca().isEmpty() | coche.getModelo().isEmpty()) {
				return 2;
			}
			dao.modificarCoche(c);
			return 0;
		}else {
			return 1;
		}
	}
	
	public 	ArrayList<Coche> listarCoches(){
		return dao.listarCoches();
	}
	
	public void cerrar() {
		dao.cerrar();
	}
}
