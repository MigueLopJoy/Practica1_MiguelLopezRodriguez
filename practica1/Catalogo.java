package practica1;

import java.util.Arrays;

/**
 * Clase modelo para la creacion de catalogos
 * 
 * @author Miguel Lopez Rodriguez
 * 
 */

public class Catalogo {
	private int cantidadProductos;
	private int[] idsProductos;
	private String[] nombresProductos;
	private double[] preciosProductos;
	private int[] unidadesProductos;

	public Catalogo() {
		super();
		this.cantidadProductos = 4;
		this.idsProductos = new int[cantidadProductos];
		this.nombresProductos = new String[cantidadProductos];
		this.preciosProductos = new double[cantidadProductos];
		this.unidadesProductos = new int[cantidadProductos];
		ponerIds();
	}

	public int getCantidadProductos() {
		return cantidadProductos;
	}

	public int[] getIdsProductos() {
		return idsProductos;
	}

	public String[] getNombresProductos() {
		return nombresProductos;
	}

	public double[] getPreciosProductos() {
		return preciosProductos;
	}

	public int[] getUnidadesProductos() {
		return unidadesProductos;
	}

	/**
	 * Permite introducir el nombre de un registro en la posicion en memoria
	 * indicada.
	 * 
	 * @param indice posicion en memoria en la que se introducira el nuevo nombre
	 * @param nombre nombre a introducir en el registro
	 * 
	 */

	public void anadirNombreProducto(int indice, String nombre) {
		nombresProductos[indice] = nombre;
	}

	/**
	 * Permite introducir el precio de un registro en la posicion en memoria
	 * indicada.
	 * 
	 * @param indice Posicion en memoria en la que se introducira el nuevo precio
	 * @param precio Precio a introducir en el registro
	 * 
	 */

	public void anadirPrecioProducto(int indice, double precio) {
		preciosProductos[indice] = precio;
	}

	/**
	 * Permite introducir el numero de unidades disponibles de un registro en la
	 * posicion en memoria indicada.
	 * 
	 * @param indice   Posicion en memoria en la que se introducira el nuevo numero
	 *                 de unidades
	 * @param unidades Unidades a introducir en el registro
	 * 
	 */

	public void anadirUnidadesProducto(int indice, int unidades) {
		unidadesProductos[indice] = unidades;
	}	

	/**
	 * Ofrece toda la informacion sobre un producto ubicado en una psocion en
	 * memoria senalada
	 * 
	 * @param indice Posicion en memoria del producto buscado
	 * @return String con la informacion del producto asociado al registro
	 */

	public String informarSobreProducto(int indice) {
		return "id: " + idsProductos[indice] + "\n" + "Producto: " + nombresProductos[indice] + "\n" + "Precio: "
				+ preciosProductos[indice] + "\n" + "Unidades: " + unidadesProductos[indice];
	}

	/**
	 * Asigna a cada posicion en memoria de la base de datos un identificador unico
	 * 
	 */

	private void ponerIds() {
		for (int i = 0; i < cantidadProductos; i++) {
			idsProductos[i] = i + 1;
		}
	}
}

