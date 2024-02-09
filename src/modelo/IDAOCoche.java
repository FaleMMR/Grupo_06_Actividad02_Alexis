package modelo;

import java.util.ArrayList;

import entidades.Coche;

public interface IDAOCoche {

	void borrarCoche(int x);
	Coche consultarUnCoche(int x);
	void insertarCoche(Coche c);
	ArrayList<Coche> listarCoches() ;
	void modificarCoche( Coche uncoche);
	void cerrar() ;
	
}
