package practica1;

/**
 * 
 * Clase que crea el catalogo a administrar y contiene todos los metodos necesarios para la administracion de la
 * base de datos dicho catalogo.
 * 
 * @author Miguel Lopez Rodriguez
 *
 */

public class Administrador {

	private static Catalogo catalogo = new Catalogo();

	/**
	 * Metodo central de la interfaz, llamado desde la clase ejecutable, desde el
	 * cual se accede al resto de funciones de 'Administrador'.
	 * 
	 */

	public static void administrarCatalogo() {
		int opcionMenu;
		boolean confirmar;
		do {
			confirmar = false;
			opcionMenu = Utilidades.pedirInt(
					"Que operacion desea realizar? (1 - Crear; 2 - Consultar registro; 3 - Actualizar; 4 - Eliminar; 5 - Salir).",
					1, 5);
			switch (opcionMenu) {
			case 1: {
				crearRegistro();
				break;
			}
			case 2: {
				if (Utilidades.hayRegistros(catalogo)) {
					consultarRegistro();
				}
				break;
			}
			case 3: {
				if (Utilidades.hayRegistros(catalogo)) {
					actualizarRegistro();
				}
				break;
			}
			case 4: {
				if (Utilidades.hayRegistros(catalogo)) {
					eliminarRegistro();
				}
				break;
			}
			case 5: {
				confirmar = Utilidades
						.confirmacion("Si sale del programa, perdera todos sus datos. Realmente quiere salir? (s / n)");
				break;
			}
			}
		} while (!confirmar);
		System.out.println("Hasta Pronto!");
	}

	/**
	 * Busca una posicion libre en memoria. Si la encuentra, llama al metodo
	 * "introducirDatos()", al que pasa dicha posicion. Si no, lo hace saber al
	 * usuario.
	 * 
	 */

	private static void crearRegistro() {
		int contador = 0;
		boolean encontrado = false;
		do {
			if (catalogo.getNombresProductos()[contador] == null) {
				encontrado = true;
			} else {
				contador++;
			}
		} while (contador < catalogo.getCantidadProductos() && !encontrado);

		if (encontrado) {
			introducirDatos(contador);
		} else {
			System.out.println("Lo siento, pero el catalogo esta completo. No puede anadir mas registros.");
		}
	}

	/**
	 * Busca el/los registros indicados por el usuario y llama al metodo
	 * 'informarSobreProducto()' de la clase Catalogo, al que pasa la posicion en
	 * memoria de dichos registros.
	 * 
	 */

	private static void consultarRegistro() {
		int[] indices;
		System.out.println("Busque el/los registro/s que desea consultar.");
		indices = menuBusqueda();
		for (int i = 0; i < indices.length; i++) {
			if (indices[i] != -1) {
				System.out.println(catalogo.informarSobreProducto(indices[i]));
				System.out.println("-----------------------------------------------------");
			}
		}

	}

	/**
	 * 
	 * Obtiene las posiciones en memoria de los registros que el usuario desea
	 * modificar, pregunta al usuario los datos sobre el producto que desea
	 * modificar, y llama al metodo 'introducirDatos()', al que pasa estos datos.
	 * 
	 */

	private static void actualizarRegistro() {
		int[] indices;
		int datosModificar;

		System.out.println("Busque el/los registro/s que desea actualizar");
		indices = menuBusqueda();

		datosModificar = Utilidades.pedirInt(
				"Inique ahora qu� campos desea modificar: 1 - Nombre del producto; 2 - Precio del Producto; 3 - Unidades del producto; 4 - Todos los campos.",
				1, 4);

		for (int i = 0; i < indices.length; i++) {
			if (indices[i] != -1) {
				introducirDatos(indices[i], datosModificar);
			}
		}
	}

	/**
	 * 
	 * Llama a los metodos necesarios para encontrar los indices de los registros
	 * indicados por el usuario y los elimina.
	 * 
	 */

	private static void eliminarRegistro() {
		int[] indices;
		System.out.println("Busque el registro que desea eliminar.");
		indices = menuBusqueda();
		if (Utilidades
				.confirmacion("Si elimina el registro, no podra recuperarlo mas tarde. Desea continuar? (s / n)")) {
			for (int i = 0; i < indices.length; i++) {
				if (indices[i] != -1) {
					catalogo.anadirNombreProducto(indices[i], null);
					catalogo.anadirPrecioProducto(indices[i], 0);
					catalogo.anadirUnidadesProducto(indices[i], 0);
				}
			}
			System.out.println("Registro/s eliminado/s con exito.");
		}
	}

	/**
	 * 
	 * Usado para la creacion de nuevos productos, llama a los metodos necesarios de
	 * la clase Catalogo para introducir todos los datos del producto pasados por el
	 * usuario en la posicion libre en memoria indicada.
	 * 
	 * @param indice posicion libre en memoria en la que se insertaran los datos
	 *               introducidos por el usuario.
	 * 
	 */

	private static void introducirDatos(int indice) {
		String nombreProducto;

		do {
			nombreProducto = Utilidades.pedirString("Introduzca el nombre del nuevo producto.");

			// Convierte en mayuscula la primera letra de la cadena de texto introducida

			if (nombreProducto.length() > 0) {
				nombreProducto = nombreProducto.toUpperCase().charAt(0)
						+ nombreProducto.substring(1, nombreProducto.length());
			}

		} while (!Utilidades.validarNombre(catalogo, nombreProducto, indice));

		catalogo.anadirNombreProducto(indice, nombreProducto);

		catalogo.anadirPrecioProducto(indice,
				Utilidades.pedirDouble("Introduzca el precio del nuevo producto.", 5, 100000));

		catalogo.anadirUnidadesProducto(indice,
				Utilidades.pedirInt("Introduzca la cantidad de unidades disponibles.", 1, 100000));

		System.out.println("Datos introducidos con exito.");
	}

	/**
	 * Usado para la modificacion de productos existentes, llama a los metodos
	 * necesarios de la clase Catalogo para introducir en uno o todos los campos los
	 * nuevos datos del producto pasados por el usuario en la posicion libre en
	 * memoria
	 * 
	 * @param indice    posicion libre en la base de datos en la que se insertaran
	 *                  los datos introducidos por el usuario.
	 * 
	 * @param operacion numero entero entre 1 y 4 que indica los campos de la base
	 *                  de datos a modificar.
	 */

	private static void introducirDatos(int indice, int operacion) {
		String nombreProducto;

		if (operacion == 1 || operacion == 4) {
			do {
				nombreProducto = Utilidades.pedirString("Introduzca el nuevo nombre del producto.");

				// Convierte en mayuscula la primera letra de la cadena de texto introducida

				nombreProducto = nombreProducto.toUpperCase().charAt(0)
						+ nombreProducto.substring(1, nombreProducto.length());
			} while (!Utilidades.validarNombre(catalogo, nombreProducto, indice));

			catalogo.anadirNombreProducto(indice, nombreProducto);
		}

		if (operacion == 2 || operacion == 4) {
			catalogo.anadirPrecioProducto(indice,
					Utilidades.pedirDouble("Introduzca el nuevo precio del producto.", 5, 100000));
		}

		if (operacion == 3 || operacion == 4) {
			catalogo.anadirUnidadesProducto(indice,
					Utilidades.pedirInt("Introduzca las unidades del nuevo producto.", 1, 100000));
		}

		System.out.println("Datos introducidos con exito.");
	}

	/**
	 * Permite al usuario escoger un criterio de busqueda y llama a los metodos de
	 * busqueda pertinentes en funcion de dicho criterio
	 * 
	 * @return array con los indices de los registros buscados por el usuario
	 */

	private static int[] menuBusqueda() {
		int criterioBusqueda;
		int[] indices = new int[catalogo.getCantidadProductos()];

		//
		// Para todos los metodos de busqueda:
		// -1 = posicion en memoria por defecto => no existen coincidencias de busqueda
		// o los resultados no han llegado a ocupar esa posicion
		// (no hay tantos resultados como espacio tiene el array).

		for (int i = 0; i < indices.length; i++) {
			indices[i] = -1;
		}

		criterioBusqueda = Utilidades.pedirInt(
				"Indique el criterio de busqueda: 1 - id del producto; 2 - Nombre de producto; 3 - Precio; 4 - Unidades de producto; 5 - Mostrar todos.",
				1, 5);

		switch (criterioBusqueda) {
		case 1: {
			indices = buscarPorId(modalidadBusqueda(1));
			break;
		}
		case 2: {
			indices[0] = buscarPorNombre();
			break;
		}
		case 3: {
			indices = buscarPorPrecio(modalidadBusqueda(2));
			break;
		}
		case 4: {
			indices = buscarPorUnidades(modalidadBusqueda(3));
			break;
		}
		case 5: {
			indices = mostrarTodo();
			break;
		}
		}
		return indices;
	}

	/**
	 * En funcion de la modalidad de busqueda: 1- pide al usuario el indice asociado
	 * a un registro, busca y retorna la posicion en memoria del mismo; 2 - pide al
	 * usuario que establezca un rango de indices, busca y retorna las posiciones en
	 * meoria de los registros cuyos indices estan comprendidos en ese rango; 3 -
	 * Busca el registro con el indice mayor y retorna su posicion en memoria; 4 -
	 * Busca el registro con el indice menor y retorna su posicion en memoria. En
	 * caso de no encontrar coindicencias lo hace saber al usuario.
	 * 
	 * @param modalidadBusqueda numero entero entre 1 y 4 que indica la modalidad de
	 *                          busqueda elegida por el usuario
	 * @return array con las posiciones en memoria de los registros buscados por el
	 *         usuario, o vacio en caso de no encontrar coincidencias.
	 */

	private static int[] buscarPorId(int modalidadBusqueda) {
		int idProducto;
		int idMasAlto;
		int idMasBajo;
		int[] indices;
		boolean encontrado = false;
		int contador = 0;
		int[] corrector = new int[2];

		// Buscar dato concreto

		if (modalidadBusqueda == 1) {

			indices = new int[1];

			idProducto = Utilidades.pedirInt("Introduzca el id del producto que desea buscar (Total de prductos: "
					+ catalogo.getCantidadProductos() + ").", 1, catalogo.getCantidadProductos());

			if (catalogo.getNombresProductos()[idProducto - 1] == null) {
				System.out.println("Lo siento, pero el registro asociado a ese identificador se encuentra vacio.");
				indices[0] = -1;
				return indices;
			}
			indices[0] = idProducto - 1;
			return indices;

			// Buscar por rango

		} else if (modalidadBusqueda == 2) {
			indices = new int[catalogo.getCantidadProductos()];

			// Todas las posiciones sin resultados por defecto

			for (int i = 0; i < indices.length; i++) {
				indices[i] = -1;
			}

			do {
				idMasAlto = Utilidades.pedirInt(
						"Introduzca el id mas alto (Total de prductos: " + catalogo.getCantidadProductos() + ").", 1,
						catalogo.getCantidadProductos());
				idMasBajo = Utilidades.pedirInt(
						"Introduzca el id mas bajo (Total de prductos: " + catalogo.getCantidadProductos() + ").", 1,
						catalogo.getCantidadProductos());

				if (idMasAlto == idMasBajo) {
					System.out.println("Los identificadores introducidos no pueden ser iguales.");
				}

			} while (idMasAlto == idMasBajo);

			// Intercambia los valores de las variables si es necesario

			corrector = Utilidades.correctorMayorMenor(idMasAlto, idMasBajo);
			idMasAlto = corrector[0];
			idMasBajo = corrector[1];

			// Buscar productos con identificadores en rango
			
			for (int i = idMasBajo - 1; i < idMasAlto; i++) {
				if (catalogo.getNombresProductos()[i] != null) {
					indices[contador] = i;
					encontrado = true;
					contador++;
				}
			}

			// Advertir inexistencia de productos con identificadores en rango

			if (!encontrado) {
				System.out.println(
						"Lo siento, pero no existen registros cuyos identificadores se encuentren comprendidos entre ese rango.");
			}

			return indices;

			// Buscar id mas alto (solo uno)

		} else if (modalidadBusqueda == 3) {
			indices = new int[1];

			for (int i = 0; i < catalogo.getCantidadProductos(); i++) {
				if (catalogo.getNombresProductos()[i] != null) {
					indices[0] = i;
				}
			}

			if (indices.length != 1) {
				indices[0] = -1;
			}

			return indices;

			// Buscar id mas bajo (solo uno)

		} else {
			indices = new int[1];

			do {
				if (catalogo.getNombresProductos()[contador] != null) {
					encontrado = true;
				} else {
					contador++;
				}
			} while (contador < catalogo.getCantidadProductos() && !encontrado);

			if (contador < catalogo.getCantidadProductos() && encontrado) {
				indices[0] = contador;
			}
			return indices;
		}
	}

	/**
	 * Busca el registro cuyo nombre coincida con el indicado por el usuario y
	 * retorna la posicion en memoria del mismo. En caso de no encontrar
	 * coincidencias, lo hace saber.
	 * 
	 * @return numero entero que representa la posicion en memoria del registro
	 *         buscado por el usuario
	 */

	private static int buscarPorNombre() {
		String nombreProducto;
		int contador = 0;
		boolean encontrado = false;
		
		// Pide y valida el nombre a buscar

		do {
			nombreProducto = Utilidades.pedirString("Introduzca el nombre del producto que desea buscar.");

			// Convierte en mayuscula la primera letra de la cadena de texto introducida

			nombreProducto = nombreProducto.toUpperCase().charAt(0)
					+ nombreProducto.substring(1, nombreProducto.length());
		} while (!Utilidades.validarNombre(catalogo, nombreProducto));

		// Busca el producto con el nombre indicado 
		
		do {
			if (catalogo.getNombresProductos()[contador] != null) {
				if (catalogo.getNombresProductos()[contador].equals(nombreProducto)) {
					encontrado = true;
				} else {
					contador++;
				}
			} else {
				contador++;
			}
		} while (contador < catalogo.getCantidadProductos() && !encontrado);

		if (encontrado) {
			return contador;
		} else {
			System.out.println("Lo siento, pero no se ha encontrado ningun producto con ese nombre en el catalogo.");
		}
		return -1;
	}

	/**
	 * En funcion de la modalidad de busqueda: 1- pide al usuario el precio asociado
	 * a uno o varios registros, busca y retorna sus posiciones en memoria; 2 - pide
	 * al usuario que establezca un rango de precios, busca y retorna las posiciones
	 * en memoria de los registgros comprendidos en ese rango; 3 - Busca y retorna
	 * la posicion en memoria del registro con el precio mas alto -uno o varios-; 4
	 * - Busca y retorna la posicion en memoria del registro con el precio mas bajo
	 * -uno o varios-. En caso de no encontrar coindicencias lo hace saber al
	 * usuario.
	 * 
	 * @param modalidadBusqueda numero entero entre 1 y 4 que indica la modalidad de
	 *                          busqueda elegida por el usuario
	 * @return array con los indices de los registros buscados por el usuario o
	 *         vacio en caso de no encontrar coincidencias.
	 */

	private static int[] buscarPorPrecio(int modalidadBusqueda) {
		double precioProducto;
		double precioMaximo;
		double precioMinimo;
		int[] indices;
		boolean encontrado = false;
		int contador = 0;
		double[] corrector = new double[2];
		double comparador;

		// Buscar dato concreto

		if (modalidadBusqueda == 1) {
			indices = new int[1];
			precioProducto = Utilidades.pedirDouble("Introduzca el precio del producto que desea buscar.", 5, 100000);

			do {
				if (catalogo.getPreciosProductos()[contador] == precioProducto) {
					encontrado = true;
				} else {
					contador++;
				}
			} while (contador < catalogo.getCantidadProductos() && !encontrado);

			if (contador < catalogo.getCantidadProductos() && encontrado) {
				indices[0] = contador;
				return indices;
			} else {
				System.out.println("Lo siento, pero el precio buscado no se corresponde con el de ningun producto.");
				indices[0] = -1;
			}
			return indices;

			// Buscar por rango

		} else if (modalidadBusqueda == 2) {
			indices = new int[catalogo.getCantidadProductos()];

			// Sin resultados en ninguna posicion por defecto

			for (int i = 0; i < indices.length; i++) {
				indices[i] = -1;
			}

			do {
				precioMaximo = Utilidades.pedirDouble("Introduzca el precio mas alto.", 5, 100000);
				precioMinimo = Utilidades.pedirDouble("Introduzca el precio mas bajo.", 5, 100000);
				if (precioMaximo == precioMinimo) {
					System.out.println("Los precios introducidos no pueden ser iguales.");
				}
			} while (precioMaximo == precioMinimo);

			// Intercambiar valores si es necesario

			corrector = Utilidades.correctorMayorMenor(precioMaximo, precioMinimo);
			precioMaximo = corrector[0];
			precioMinimo = corrector[1];
			
			// Buscar indices de productos con precio en rango

			for (int i = 0; i < catalogo.getCantidadProductos(); i++) {
				if (catalogo.getPreciosProductos()[i] >= precioMinimo
						&& catalogo.getPreciosProductos()[i] <= precioMaximo) {
					indices[contador] = i;
					encontrado = true;
					contador++;
				}
			}

			// Advertir inexistencia de productos con precio en rango
			
			if (!encontrado) {
				System.out.println("Lo siento, pero no existe ningun producto cuyo precio se situe en ese rango.");
				return indices;
			}

			// Buscar precio mas alto (Puede haber varios resultados)

		} else if (modalidadBusqueda == 3) {
			indices = new int[catalogo.getCantidadProductos()];

			// Sin resultados en ninguna posicion por defecto

			for (int i = 0; i < catalogo.getCantidadProductos(); i++) {
				indices[i] = -1;
			}

			comparador = catalogo.getPreciosProductos()[0];

			for (int i = 0; i < catalogo.getCantidadProductos(); i++) {
				if (catalogo.getNombresProductos()[i] != null) {
					if (Utilidades.devolverMayor(comparador,
							catalogo.getPreciosProductos()[i]) == catalogo.getPreciosProductos()[i]) {
						comparador = catalogo.getPreciosProductos()[i];
					}
				}
			}

			for (int i = 0; i < catalogo.getCantidadProductos(); i++) {
				if (catalogo.getNombresProductos()[i] != null) {
					if (comparador == catalogo.getPreciosProductos()[i]) {
						indices[contador] = i;
						contador++;
					}
				}
			}

			return indices;

			// Buscar precio mas bajo (Puede haber varios resultados)

		} else {
			indices = new int[catalogo.getCantidadProductos()];

			for (int i = 0; i < catalogo.getCantidadProductos(); i++) {
				indices[i] = -1;
			}

			comparador = catalogo.getPreciosProductos()[0];

			for (int i = 0; i < catalogo.getCantidadProductos(); i++) {
				if (catalogo.getNombresProductos()[i] != null) {
					if (Utilidades.devolverMenor(comparador,
							catalogo.getPreciosProductos()[i]) == catalogo.getPreciosProductos()[i]) {
						comparador = catalogo.getPreciosProductos()[i];
					}
				}
			}

			for (int i = 0; i < catalogo.getCantidadProductos(); i++) {
				if (catalogo.getNombresProductos()[i] != null) {
					if (comparador == catalogo.getPreciosProductos()[i]) {
						indices[contador] = i;
						contador++;
					}
				}
			}
		}
		return indices;
	}

	/**
	 * En funcion de la modalidad de busqueda: 1- pide al usuario el numero de
	 * unidades disponibles asociado a uno o varios registros, busca y retorna sus
	 * posiciones en memoria; 2 - pide al usuario que establezca un rango de
	 * unidades disponibles, busca y retorna las posiciones en memoria de los
	 * registgros comprendidos en ese rango; 3 - Busca y retorna la posicion en
	 * memoria del registro con el numero mas alto de unidades disponibles -uno o
	 * varios-; 4 - Busca y retorna la posicion en memoria del registro con el
	 * numero mas bajo de unidades - uno o varios-. En caso de no encontrar
	 * coindicencias lo hace saber al usuario.
	 * 
	 * @param modalidadBusqueda numero entero entre 1 y 4 que indica la modalidad de
	 *                          busqueda elegida por el usuario
	 * @return array con los indices de los registros buscados por el usuario o
	 *         vacio en caso de no encontrar coincidencias.
	 */

	private static int[] buscarPorUnidades(int modalidadBusqueda) {
		int unidades;
		int maximoUnidades;
		int minimoUnidades;
		int[] indices;
		boolean encontrado = false;
		int contador = 0;
		int[] corrector = new int[2];
		int comparador;

		// Buscar un dato concreto

		if (modalidadBusqueda == 1) {
			indices = new int[1];
			unidades = Utilidades.pedirInt("Introduzca las unidades disponibles del producto que desea buscar.", 1,
					100000);

			do {
				if (catalogo.getPreciosProductos()[contador] == unidades) {
					encontrado = true;
				} else {
					contador++;
				}
			} while (contador < catalogo.getCantidadProductos() && !encontrado);

			if (contador < catalogo.getCantidadProductos() && encontrado) {
				indices[0] = contador;
				return indices;
			} else {
				System.out.println("Lo siento, pero no hay ningun producto con esas unidades disponibles.");
				indices[0] = -1;
			}
			return indices;

			// Buscar por rango

		} else if (modalidadBusqueda == 2) {
			indices = new int[catalogo.getCantidadProductos()];

			// Sin resultados en ninguna posicion por defecto

			for (int i = 0; i < indices.length; i++) {
				indices[i] = -1;
			}

			do {
				maximoUnidades = Utilidades.pedirInt("Introduzca la cantidad mas alta de unidades disponibles", 1,
						100000);
				minimoUnidades = Utilidades.pedirInt("Introduzca la cantidad mas baja de unidades disponibles.", 1,
						100000);
				if (maximoUnidades == minimoUnidades) {
					System.out.println("Las cantidades introducidas no pueden ser iguales.");
				}
			} while (maximoUnidades == minimoUnidades);

			// Intercambia los valores si es encesario

			corrector = Utilidades.correctorMayorMenor(maximoUnidades, minimoUnidades);
			maximoUnidades = corrector[0];
			minimoUnidades = corrector[1];

			// Buscar indices de productos con numero de unidades disponibles en rango
			
			for (int i = 0; i < catalogo.getCantidadProductos(); i++) {
				if (catalogo.getUnidadesProductos()[i] >= minimoUnidades
						&& catalogo.getUnidadesProductos()[i] <= maximoUnidades) {
					indices[contador] = i;
					encontrado = true;
					contador++;
				}
			}

			// Advertir inexistencia de productos con numero de unidades disponibles en rango
			
			if (!encontrado) {
				System.out.println("Lo siento, pero no existe ningun producto cuyo precio se situe en ese rango.");
				return indices;
			}
			return indices;

			// Buscar numero mas alto de unidades (Puede haber varios resultados)

		} else if (modalidadBusqueda == 3) {
			indices = new int[catalogo.getCantidadProductos()];

			// Sin resultados en ninguna posicion por defecto

			for (int i = 0; i < catalogo.getCantidadProductos(); i++) {
				indices[i] = -1;
			}

			comparador = catalogo.getUnidadesProductos()[0];

			for (int i = 0; i < catalogo.getCantidadProductos(); i++) {
				if (catalogo.getNombresProductos()[i] != null) {
					if (Utilidades.devolverMayor(comparador,
							catalogo.getUnidadesProductos()[i]) == catalogo.getUnidadesProductos()[i]) {
						comparador = catalogo.getUnidadesProductos()[i];
					}
				}
			}

			for (int i = 0; i < catalogo.getCantidadProductos(); i++) {
				if (catalogo.getNombresProductos()[i] != null) {
					if (comparador == catalogo.getUnidadesProductos()[i]) {
						indices[contador] = i;
						contador++;
					}
				}
			}

			return indices;

			// Buscar el numero mas bajo de unidades (Puede haber varios resultados)

		} else {
			indices = new int[catalogo.getCantidadProductos()];

			// Sin resultados en ninguna posicion por defecto

			for (int i = 0; i < catalogo.getCantidadProductos(); i++) {
				indices[i] = -1;
			}

			comparador = catalogo.getUnidadesProductos()[0];

			for (int i = 0; i < catalogo.getCantidadProductos(); i++) {
				if (catalogo.getNombresProductos()[i] != null) {
					if (Utilidades.devolverMenor(comparador,
							catalogo.getUnidadesProductos()[i]) == catalogo.getUnidadesProductos()[i]) {
						comparador = catalogo.getUnidadesProductos()[i];
					}
				}
			}

			for (int i = 0; i < catalogo.getCantidadProductos(); i++) {
				if (catalogo.getNombresProductos()[i] != null) {
					if (comparador == catalogo.getUnidadesProductos()[i]) {
						indices[contador] = i;
						contador++;
					}
				}
			}
		}
		return indices;
	}

	/**
	 * Busca todos los registros de la base de datos y retorna sus posiciones en
	 * memoria.
	 * 
	 * @return array con las posiciones en memoria de todos los registros
	 *         disponibles en la base de datos
	 */

	public static int[] mostrarTodo() {
		int contador = 0;
		int[] indices = new int[catalogo.getCantidadProductos()];

		for (int i = 0; i < indices.length; i++) {
			indices[i] = -1;
		}

		for (int i = 0; i < catalogo.getCantidadProductos(); i++) {
			if (catalogo.getNombresProductos()[i] != null) {
				indices[contador] = i;
				contador++;
			}
		}
		return indices;
	}

	/**
	 * Permite al usuario escoger una modalidad de busqueda - dato concreto, rango,
	 * dato mas alto, dato mas bajo- para sus busquedas por id, precio o unidades
	 * disponibles.
	 * 
	 * @param criterioBusqueda numero entero que representa la opcion de busqueda
	 *                         del usuario -id, precio o unidades-.
	 * @return numero entero que establece la modalidad de busqeuda elegida por el
	 *         usuario.
	 */

	private static int modalidadBusqueda(int criterioBusqueda) {
		int opcionBusqueda = 0;
		if (criterioBusqueda == 1) {
			opcionBusqueda = Utilidades.pedirInt(
					"Escoja la opcion de busqueda deseada: 1 - Buscar un id concreto; 2 - Buscar un rango de identificadores; 3 - Buscar el id mas alto; 4 - Buscar el id mas bajo.",
					1, 4);
		} else if (criterioBusqueda == 2) {
			opcionBusqueda = Utilidades.pedirInt(
					"Escoja la opcion de busqueda deseada: 1 - Buscar un precio concreto; 2 - Buscar un rango de precios; 3 - Buscar el precio mas alto; 4 - Buscar el precio mas bajo.",
					1, 4);
		} else if (criterioBusqueda == 3) {
			opcionBusqueda = Utilidades.pedirInt(
					"Escoja la opcion de busqueda deseada: 1 - Buscar una cantidad concreta de unidades; 2 - Buscar un rango de unidades; 3 - Buscar el numero mas alto de unidades; 4 - Buscar el numero mas bajo de unidades.",
					1, 4);
		}
		return opcionBusqueda;
	}
}
