package modelo;

import java.util.ArrayList;

import entidades.Coche;
import entidades.Pasajero;

public interface IDAOPasajero {

	void borrarPasajero(int x);
	Pasajero consultarUnPasajero(int x);
	void insertarPasajero(Pasajero c);
	ArrayList<Pasajero> listarPasajeros() ;
	void anadirPasajeroACoche(int idcoche, int idpasajero);
	void quitarCocheAPasajero(int idpasajero);
	ArrayList<Pasajero> listarPasajerosDeUnCoche(int idcoche) ;

	void cerrar() ;
	
}
