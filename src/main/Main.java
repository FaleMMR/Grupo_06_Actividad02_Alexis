package main;

import java.util.Scanner;
import modelo.entidad.Coche;
import modelo.negocio.GestorCoche;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static GestorCoche gc = new GestorCoche();
	
	public static void main(String[] args) {
				
		int opcion = 0;
		do {
			opcion= Menu.pintarMenu();
			switch (opcion) {
				case 1:
					altaCoche();
				break;
				case 2:
				//	borrar();
				break;
				case 3:
					//consultar();
				break;
				case 4:
				//	modificar();
				break;	
				case 5:
				//	listar();
				break;
				case 6:
				//	gestionPasageros;
				break;
				case 7:
				//	salir;
				break;
			}
		}while (opcion!=6);
		
		System.out.println("Fin del programa");
	}
	
	public static Coche altaCoche() {
		System.out.println("Introduzca los datos del usuario (marca/modelo/fechaFafricacion/kilometros)");
		String marca = sc.next();
		String modelo = sc.next();
		int fechaFabricacion = sc.nextInt();
		int kilometros = sc.nextInt();
		
		Coche c = new Coche();
		c.setMarca(marca);
		c.setModelo(modelo);
		c.setFechaFabricacion(fechaFabricacion);
		c.setKilometros(kilometros);
		
		int alta = gc.alta(c);
		if(alta == 0) {
			System.out.println("Coche dada de alta");
		}else if(alta == 1) {
			System.out.println("Error de conexión con la BBDD");
		}else if(alta == 2){
			System.out.println("El usuario tiene menos de tres carateres");
		}
		
		return c;
	}
}
