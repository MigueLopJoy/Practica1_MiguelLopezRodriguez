package practica1;

import java.util.Scanner;

public class Utilidades {

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

	public static double pedirDouble(String texto) {
		double retorno;
		Scanner scanner = new Scanner(System.in);
		System.out.println(texto);
		retorno = scanner.nextInt();
		scanner.nextLine();
		return retorno;
	}

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

	public static String pedirString(String texto) {
		String retorno;
		Scanner scanner = new Scanner(System.in);
		System.out.println(texto);
		retorno = scanner.nextLine();
		return retorno;
	}

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

	public static int devolverMayor(int numero1, int numero2) {
		int numeroMenor;
		if (numero1 != numero2) {
			numeroMenor = (numero1 > numero2) ? numero1 : numero2;
		} else {
			numeroMenor = numero1;
		}
		return numeroMenor;
	}

	public static int devolverMenor(int numero1, int numero2) {
		int numeroMenor;
		if (numero1 != numero2) {
			numeroMenor = (numero1 < numero2) ? numero1 : numero2;
		} else {
			numeroMenor = numero1;
		}
		return numeroMenor;
	}

	public static double devolverMayor(double numero1, double numero2) {
		double numeroMenor;
		if (numero1 != numero2) {
			numeroMenor = (numero1 > numero2) ? numero1 : numero2;
		} else {
			numeroMenor = numero1;
		}
		return numeroMenor;
	}

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

