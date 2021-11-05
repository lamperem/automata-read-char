package com.chars;

	public class example {
		
		static String[][] tablaTokens = new String[200][3]; // matriz de 200 files y 3 columnas
		static int posicionTablaTokens = 0; // posicion donde estoy en la tabla de tokens
		
	
		public static void main(String[] args) {
			
			String cadena = "int i = 5;"; // linea a evaluar		
			
			String cadenaTemporal = ""; // esta cadena guarda el token actual que se analiza
			
			// recorrer la linea caracter por caracter
			for(int i = 0; i < cadena.length(); i++) {
				String separadores = " ;"; // separadores de tokens
				char letra = cadena.charAt(i);
				String caracterActual = "";
				
				if (letra != separadores.charAt(0) && letra != separadores.charAt(1) ) {
					caracterActual = cadenaTemporal + letra;
					cadenaTemporal = caracterActual;
					System.out.println("PASO 0: concatenar: " + caracterActual);
				} else {

					System.out.println("PASO 1: Cadena a buscar: " + cadenaTemporal);
					
					// ahora buscar entre todos los tipos de tokens
					boolean esPalabraReservada = buscarParalabraReservada(cadenaTemporal);
					boolean esOperador = buscarOperadores(cadenaTemporal);
					boolean esIdentificador = buscarIdentificador(cadenaTemporal);
					
					// validar si la cadena es paralabra reservada, operador o identificador
					if (esPalabraReservada) {					
						System.out.println("---------------------");
						System.out.println("PALABRA RESERVADA: " + cadenaTemporal);
						System.out.println("---------------------");
						
						agregarATablaDeTokens("Reservada", cadenaTemporal); // agregar a tabla de tokens
						cadenaTemporal = "";
					} else if (esOperador) {
						System.out.println("---------------------");
						System.out.println("OPERADOR: " + cadenaTemporal);
						System.out.println("---------------------");
						agregarATablaDeTokens("Operador", cadenaTemporal); // agregar a tabla de tokens
						cadenaTemporal = "";
					} else if (esIdentificador) {
						System.out.println("---------------------");
						System.out.println("IDENTIFICADOR: " + cadenaTemporal);
						System.out.println("---------------------");
						agregarATablaDeTokens("Identificador", cadenaTemporal); // agregar a tabla de tokens
						cadenaTemporal = "";
					} else {
						// como ninguno es valido se vuelve a iterar hasta encontrar uno valido
						// cadenaTemporal = caracterActual;
					}
					
					
				}								
				
			}
			
			// Mostrar tabla de tokens
			mostrarTablaDeTokens();
	
		}
		
		public static boolean buscarParalabraReservada(String cadena) {
			String[] palabrasReservadas = {"int", "Float"};
			boolean existe = false;
			
			// Buscar en el array si existe el valor cadena
			for(int i = 0; i < palabrasReservadas.length; i++) {
				
				// Validar si la cadena existe entre las palabras reservadas
				if ( cadena.equals(palabrasReservadas[i]) ) {					
					existe = true;
				}
			}			
			
			return existe;
		}
	
		public static boolean buscarOperadores(String cadena) {
			String[] operadores = {"=", "<", ">"};
			boolean existe = false;
			
			// Buscar en el array si existe el valor cadena
			for(int i = 0; i < operadores.length; i++) {
				
				// Validar si la cadena existe entre los operadores
				if ( cadena.equals(operadores[i]) ) {					
					existe = true;
				}
			}			
			
			return existe;
		}
		
		public static boolean buscarIdentificador(String cadena) {
			// Expresion regular para validar el nombre valido de un identificador para JAVA
			String expresionRegular = "[A-za-z0-9]*";
			boolean esIdentificadorValido = cadena.matches(expresionRegular);
			
			return esIdentificadorValido;
		}
		
		// Metodo para llenar la matriz que tendra todos los tokens
		public static void agregarATablaDeTokens(String tipo, String valor) {
			tablaTokens[posicionTablaTokens][0] = "token";
			tablaTokens[posicionTablaTokens][1] = tipo;
			tablaTokens[posicionTablaTokens][2] = valor;
			
			posicionTablaTokens++; // mover posicion para recibir nuevo valor
		}
		
		// Mostrar tabla de tokens
		public static void mostrarTablaDeTokens() {
			System.out.println("[Nombre][Tipo][Valor]");
			for(int i = 0; i < tablaTokens.length; i++) { // recorrer filas
				String[] fila = tablaTokens[i]; // fila actual	
				
				if (fila[0] != null) { // solo mostrar los arrays que tiene datos
					System.out.println("[" + fila[0] + "]" + "[" + fila[1] + "]" + "[" + fila[2] + "]" );
				}				
				
			}
		}
		
	}
