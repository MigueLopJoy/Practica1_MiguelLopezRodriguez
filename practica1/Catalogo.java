package practica1;

import java.util.Arrays;

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

	public void anadirNombreProducto(int indice, String nombre) {
		nombresProductos[indice] = nombre;
	}

	public void anadirPrecioProducto(int indice, double precio) {
		preciosProductos[indice] = precio;
	}

	public void anadirUnidadesProducto(int indice, int unidades) {
		unidadesProductos[indice] = unidades;
	}	

	public String informarSobreProducto(int indice) {
		return "id: " + idsProductos[indice] + "\n" + "Producto: " + nombresProductos[indice] + "\n" + "Precio: "
				+ preciosProductos[indice] + "\n" + "Unidades: " + unidadesProductos[indice];
	}

	private void ponerIds() {
		for (int i = 0; i < cantidadProductos; i++) {
			idsProductos[i] = i + 1;
		}
	}
}
