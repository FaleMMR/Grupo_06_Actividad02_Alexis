package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;



public interface DaoFlotaCoches {
	
	boolean alta(Coche c);
	boolean baja(int idCoche);
	/**
	 * Metodo que modifica una persona en la BBDD. La modificaci�n ser� a partir
	 * del ID que contenga la persona.
	 * @param p la persona a modificar
	 * @return true en caso de que se haya modificado. False en caso de error
	 * con la BBDD.
	 */
	boolean modificar(Coche c);
	Coche obtener(int idCoche);
	List<Coche> listar();
	// gestion de pasajeros ???

}
