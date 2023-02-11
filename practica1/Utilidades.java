package practica1;

import java.util.Scanner;

/**
 * 
 * Clase que contiene metodos auxiliares necesarios para los procesos vinculados
 * con la administracion del catalogo.
 * 
 * @author Miguel Lopez Rodriguez
 *
 */

public class Utilidades {

	/**
	 * Permite saber si hay o no registros en el catalogo
	 * 
	 * @param catalogo Catalogo que se esta administrando
	 * @return booleano que indica si el catalogo esta o no vacio
	 */

	public static boolean hayRegistros(Catalogo catalogo) {
		boolean encontrado = false;
		int contador = 0;
		do {
			if (catalogo.getNombresProductos()[contador] != null) {
				encontrado = true;
			} else {
				contador++;
			}
		} while (contador < catalogo.getCantidadProductos() && !encontrado);

		if (contador < catalogo.getCantidadProductos() && encontrado) {
			return true;
		} else {
			System.out.println("Lo siento, pero no hay ningun registro.");
			return false;
		}
	}

	/**
	 * Utilizado en procesos de busqueda, comprueba si el dato introducido por el
	 * usuario es un nombre valido
	 * 
	 * @param catalogo       que se esta administrando
	 * @param nombreProducto nombre que el usuario pretende asignar a un producto en
	 *                       un registro
	 * @return verdadera si es un nombre válido, false si no
	 */

	public static boolean validarNombre(Catalogo catalogo, String nombreProducto) {
		int contador = 0;
		boolean encontrado = false;
		
		try {
			Integer.parseInt(nombreProducto);
			System.out.println("Lo sieto, pero debe introducir un nombre valido.");
			return false;
		} catch (NumberFormatException nfe) {
			if (nombreProducto.length() < 2 || nombreProducto.length() > 20) {
				System.out.println("Lo siento, pero el nombre es demasiado corto/ largo.");
				return false;
			}
			return true;
		}
	}

	/**
	 * Utilizado en procesos de insercion de datos, comprueba si el dato introducido
	 * es un nombre valido y si ese nombre no se encuentra ya asignado a un producto
	 * existente en la base de datos
	 * 
	 * @param catalogo       Catalogo que se esta administrando
	 * @param nombreProducto nombre que el usuario pretende asignar a un producto en
	 *                       un registro
	 * @param indice indice en el que se pretende insertar el nombre
	 * @return true si es un nombre válido, false si no
	 */

	public static boolean validarNombre(Catalogo catalogo, String nombreProducto, int indice) {
		int contador = 0;
		boolean encontrado = false;
		
		try {
			Integer.parseInt(nombreProducto);
			System.out.println("Lo sieto, pero debe introducir un nombre valido.");
			return false;
		} catch (Exception e) {
			if (nombreProducto.length() < 2 || nombreProducto.length() > 20) {
				System.out.println("Lo siento, pero el nombre es demasiado corto/ largo.");
				return false;
			} else {
				do {
					if (catalogo.getNombresProductos()[contador] != null) {
						if (catalogo.getNombresProductos()[contador].equals(nombreProducto)
								&& !(catalogo.getNombresProductos()[contador]
										.equals(catalogo.getNombresProductos()[indice]))) {
							encontrado = true;
						} else {
							contador++;
						}
					} else {
						contador++;
					}
				} while (contador < catalogo.getCantidadProductos() && !encontrado);

				if (encontrado) {
					System.out.println("Lo siento, pero ese producto ya se encuentra en el catalogo.");
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * Pide al usuario un numero entero y valida el dato introducido
	 * 
	 * @param texto Texto a mostrar al usuario
	 * @return numero entero dado por el ususario
	 */

	public static int pedirInt(String texto) {
		int retorno;
		String retornoString;
		boolean inputValido;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println(texto);
			retornoString = scanner.nextLine();

			try {
				retorno = Integer.parseInt(retornoString);
				inputValido = true;
			} catch (NumberFormatException nfe) {
				System.out.println("Debe introducir un caracter numerico.");
				retorno = 0;
				inputValido = false;
			}
		} while (!inputValido);
		return retorno;
	}

	/**
	 * Pide al usuario un numero entero comprendido en un rango y valida el dato
	 * introducido
	 * 
	 * @param texto Texto a mostrar al usuario
	 * @param min Límite inferior del rango en el que debe situarse el número que se solicita
	 * @param max Límite superior del rango en el que debe situarse el número que se solicita
	 * @return numero entero dado por el ususario
	 */

	public static int pedirInt(String texto, int min, int max) {
		int retorno;
		String retornoString;

		Scanner scanner = new Scanner(System.in);

		max = correctorMayorMenor(max, min)[0];
		min = correctorMayorMenor(max, min)[1];

		do {
			System.out.println(texto);
			retornoString = scanner.nextLine();

			try {
				retorno = Integer.parseInt(retornoString);
				if (retorno < min || retorno > max) {
					System.out.println("Numero fuera de rango.");
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Debe introducir un numero.");
				retorno = min - 1;
			}
		} while (retorno < min || retorno > max);
		return retorno;
	}

	/**
	 * Pide al usuario un numero decimal y valida el dato introducido
	 * 
	 * @param texto Texto a mostrar al usuario
	 * @return numero decimal dado por el ususario
	 */

	public static double pedirDouble(String texto) {
		double retorno;
		Scanner scanner = new Scanner(System.in);
		System.out.println(texto);
		retorno = scanner.nextInt();
		scanner.nextLine();
		return retorno;
	}

	/**
	 * Pide al usuario un numero decimal entre un rango determinado y valida el dato
	 * introducido
	 * 
	 * @param texto Texto a mostrar al usuario
	 * @param min Límite inferior del rango en el que debe situarse el número que se solicita
	 * @param max Límite superior del rango en el que debe situarse el número que se solicita
	 * @return numero decimal dado por el ususario
	 */

	public static double pedirDouble(String texto, double min, double max) {
		double retorno;
		String retornoString;
		Scanner scanner = new Scanner(System.in);
		max = correctorMayorMenor(max, min)[0];
		min = correctorMayorMenor(max, min)[1];
		do {
			System.out.println(texto);
			retornoString = scanner.nextLine();
			
			try {
				retorno = Integer.parseInt(retornoString);
				if (retorno < min || retorno > max) {
					System.out.println("Numero fuera de rango.");
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Numero no valido.");
				retorno = min - 1;
			}

		} while (retorno < min || retorno > max);
		return retorno;
	}

	/**
	 * Pide al usuario una cadena de texto
	 * 
	 * @param texto Texto a mostrar al usuario
	 * @return cadena de texto dada por el ususario
	 */

	public static String pedirString(String texto) {
		String retorno;
		Scanner scanner = new Scanner(System.in);
		System.out.println(texto);
		retorno = scanner.nextLine();
		return retorno;
	}

	/**
	 * Pide al usuario una confirmacion sobre una accion dada
	 * 
	 * @param texto Texto a mostrar al usuario
	 * @return la confirmacion o no confirmacion del usuario
	 * 
	 */

	public static boolean confirmacion(String texto) {
		char confirmacion;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println(texto);
			confirmacion = scanner.next().charAt(0);
		} while (confirmacion != 's' && confirmacion != 'n');

		if (confirmacion == 's') {
			return true;
		}
		return false;
	}

	/**
	 * Asegura que se cumple una relacion 'numero mayor - mayor que - numero menor' entre dos
	 * numeros enteros, intercambiando los valores de las variables si fuera
	 * necesario
	 * 
	 * @param max Numero entero que deberia ser mayor
	 * @param min Numero entero que deberia ser menor
	 * @return array con los numeros mayor y menor corregidos
	 */	

	public static int[] correctorMayorMenor(int max, int min) {
		int[] retorno = new int[2];
		int corrector;
		if (min > max) {
			corrector = max;
			max = min;
			min = corrector;
		}
		retorno[0] = max;
		retorno[1] = min;
		return retorno;
	}

	/**
	 * Asegura que se cumple una relacion 'numero mayor - menor que - numero menor' entre dos
	 * numeros decimales, intercambiando los valores de las variables si fuera
	 * necesario
	 * 
	 * @param max Numero decimal que deberia ser mayor
	 * @param min Numero decimal que deberia ser menor
	 * @return array con los numeros mayor y menor corregidos
	 */

	public static double[] correctorMayorMenor(double max, double min) {
		double[] retorno = new double[2];
		double corrector;
		if (min > max) {
			corrector = max;
			max = min;
			min = corrector;
		}
		retorno[0] = max;
		retorno[1] = min;
		return retorno;
	}

	/**
	 * Compara dos numeros entero y devuelve el mayor
	 * 
	 * @param numero1 Numero entero a comparar
	 * @param numero2 Numero entero a comparar
	 * @return Numero mayor
	 */

	public static int devolverMayor(int numero1, int numero2) {
		int numeroMenor;
		if (numero1 != numero2) {
			numeroMenor = (numero1 > numero2) ? numero1 : numero2;
		} else {
			numeroMenor = numero1;
		}
		return numeroMenor;
	}

	/**
	 * Compara dos numeros enteros y devuelve el menor
	 * 
	 * @param numero1 Numero entero a comparar
	 * @param numero2 Numero entero a comparar
	 * @return Numero menor
	 */

	public static int devolverMenor(int numero1, int numero2) {
		int numeroMenor;
		if (numero1 != numero2) {
			numeroMenor = (numero1 < numero2) ? numero1 : numero2;
		} else {
			numeroMenor = numero1;
		}
		return numeroMenor;
	}

	/**
	 * Compara dos numeros decimales y devuelve el mayor
	 * 
	 * @param numero1 Numero decimal a comparar
	 * @param numero2 Numero decimal a comparar
	 * @return Numero mayor
	 */

	public static double devolverMayor(double numero1, double numero2) {
		double numeroMenor;
		if (numero1 != numero2) {
			numeroMenor = (numero1 > numero2) ? numero1 : numero2;
		} else {
			numeroMenor = numero1;
		}
		return numeroMenor;
	}

	/**
	 * Compara dos numeros decimales y devuelve el menor
	 * 
	 * @param numero1 Numero decimal a comparar
	 * @param numero2 Numero decimal a comparar
	 * @return Numero menor
	 */

	public static double devolverMenor(double numero1, double numero2) {
		double numeroMenor;
		if (numero1 != numero2) {
			numeroMenor = (numero1 < numero2) ? numero1 : numero2;
		} else {
			numeroMenor = numero1;
		}
		return numeroMenor;
	}
}


