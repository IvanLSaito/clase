package ejercicios;
import java.util.Scanner;

public class barquitos {
	private static final int TAMANIO = 5;
	private static final int TOTALBARCOS = 5;
	public static void main (String[] args) {
		
		barquitos programa = new barquitos();
		programa.inicio();
		
	}
	
	private void inicio() {
		Scanner teclado = new Scanner(System.in);
		String jugador1Nombre, jugador2Nombre;
		char condicion;
		boolean estancia;
		int barcosUndidosJ1, barcosUndidosJ2;
		
		System.out.println("Estais a punto de jugar al juego de los barquitos simples, primero de todo, debereis poner la posición de los barcos.\n"
				+ "En el lienzo tendreis esas notaciones: \n"
				+ "- a = agua.\n"
				+ "- x = desconocimiento.\n"
				+ "- b = barco alcanzado.\n");

		char[][] marJ1 = new char [TAMANIO + 2][TAMANIO + 2];
		char[][] comparadorJ1 = new char [TAMANIO][TAMANIO];
		char[][] marJ2 = new char [TAMANIO + 2][TAMANIO + 2];
		char[][] comparadorJ2 = new char [TAMANIO][TAMANIO];
		
		jugador1Nombre = nombreJ1();
		jugador2Nombre = nombreJ2();
		
		estancia = true;
		while(estancia)
		{
			llenarArrays(marJ1, marJ2, comparadorJ1, comparadorJ2);
			
			pedirBarcosJ1(marJ1, jugador1Nombre);
			pedirBarcosJ2(marJ2, jugador2Nombre);
			
			barcosUndidosJ1 = 0;
			barcosUndidosJ2 = 0;
			while(barcosUndidosJ1 != 5 || barcosUndidosJ2 != 5)
			{
				barcosUndidosJ1 = jugador1Dispara(marJ2, comparadorJ2, jugador1Nombre);
				barcosUndidosJ2 = jugador2Dispara(marJ1, comparadorJ1, jugador2Nombre);
			}
			
			if(barcosUndidosJ1 == 5)
			{
				System.out.println(jugador1Nombre + " ¡Has ganado la batalla!");
			}
			else
			{
				if(barcosUndidosJ2 == 5)
				{
					System.out.println(jugador1Nombre + " ¡Has ganado la batalla!");
				}
			}
			
			System.out.println(jugador1Nombre + ", " + jugador2Nombre + " ¿quereis volver a jugar? (Y/N)");
			
			condicion = teclado.next().charAt(0);
			
			if((condicion != 'y' || condicion != 'Y') || (condicion != 'n' || condicion != 'N'))
			{
				System.out.println("Introduce 'Y' para seguir jugando, introduce 'N' para dejar de jugar.");
				condicion = teclado.next().charAt(0);
			}
			
			if(condicion == 'y' || condicion == 'Y')
			{
				estancia = true;
			}
			else
			{
				if(condicion == 'n' || condicion == 'N')
				{
					estancia = false;
				}
			}
		}
	}

	private int jugador1Dispara(char[][] marJ2, char[][] comparadorJ2, String jugador1Nombre) {
		Scanner teclado = new Scanner(System.in);
		int contadorFilas, contadorColumnas, coordenadasX, coordenadasY, contadorBarcosJ1;
		
		System.out.println("Este es el lienzo sobre el cual tienes que disparar: ");
		
		for(contadorFilas = 0;contadorFilas < TAMANIO;contadorFilas++)
		{
			for(contadorColumnas = 0;contadorColumnas < TAMANIO;contadorColumnas++)
			{
				System.out.print("|" + comparadorJ2[contadorFilas][contadorColumnas] + "|");
			}
			System.out.println();
		}
		
		System.out.println(jugador1Nombre + ", es tu turno ¡dispara!");
		
		System.out.println("Introduce la fila: ");
		coordenadasX = teclado.nextInt();
		teclado.nextLine();
		
		System.out.println("Introduce la columna: ");
		coordenadasY = teclado.nextInt();
		teclado.nextLine();	
		
		if(marJ2 [coordenadasX][coordenadasY] == 'b')
		{
			System.out.println("¡Has dado a un barco!");
			comparadorJ2 [coordenadasX][coordenadasY] = 'b';
		}
		else
		{
			if(marJ2 [coordenadasX][coordenadasY] == 'a')
			{
				System.out.println("¡Has dado al agua!");
				comparadorJ2 [coordenadasX][coordenadasY] = 'a';
			}
		}
		
		contadorBarcosJ1 = 0;
		for(contadorFilas = 0;contadorFilas < TAMANIO;contadorFilas++)
		{
			for(contadorColumnas = 0;contadorColumnas < TAMANIO;contadorColumnas++)
			{
				if(comparadorJ2[contadorFilas][contadorColumnas] == 'b')
				{
					contadorBarcosJ1++;
				}
			}
		}
		
		System.out.println("Estado actual del lienzo: ");
		
		for(contadorFilas = 0;contadorFilas < TAMANIO;contadorFilas++)
		{
			for(contadorColumnas = 0;contadorColumnas < TAMANIO;contadorColumnas++)
			{
				System.out.print("|" + comparadorJ2[contadorFilas][contadorColumnas] + "|");
			}
			System.out.println();
		}
		
		
		return contadorBarcosJ1;
	}

	private int jugador2Dispara(char[][] marJ1, char[][] comparadorJ1, String jugador2Nombre) {
		Scanner teclado = new Scanner(System.in);
		int contadorFilas, contadorColumnas, coordenadasX, coordenadasY, contadorBarcosJ2;
		
		System.out.println("Este es el lienzo sobre el cual tienes que disparar: ");
		
		for(contadorFilas = 0;contadorFilas < TAMANIO;contadorFilas++)
		{
			for(contadorColumnas = 0;contadorColumnas < TAMANIO;contadorColumnas++)
			{
				System.out.print("|" + comparadorJ1[contadorFilas][contadorColumnas] + "|");
			}
			System.out.println();
		}
		
		System.out.println(jugador2Nombre + ", es tu turno ¡dispara!");
		
		System.out.println("Introduce la fila: ");
		coordenadasX = teclado.nextInt();
		teclado.nextLine();
		
		System.out.println("Introduce la columna: ");
		coordenadasY = teclado.nextInt();
		teclado.nextLine();	
		
		if(marJ1 [coordenadasX][coordenadasY] == 'b')
		{
			System.out.println("¡Has dado a un barco!");
			comparadorJ1 [coordenadasX][coordenadasY] = 'b';
		}
		else
		{
			if(marJ1 [coordenadasX][coordenadasY] == 'a')
			{
				System.out.println("¡Has dado al agua!");
				comparadorJ1 [coordenadasX][coordenadasY] = 'a';
			}
		}
		
		contadorBarcosJ2 = 0;
		for(contadorFilas = 0;contadorFilas < TAMANIO;contadorFilas++)
		{
			for(contadorColumnas = 0;contadorColumnas < TAMANIO;contadorColumnas++)
			{
				if(comparadorJ1[contadorFilas][contadorColumnas] == 'b')
				{
					contadorBarcosJ2++;
				}
			}
		}
		
		System.out.println("Estado actual del lienzo: ");
		
		for(contadorFilas = 0;contadorFilas < TAMANIO;contadorFilas++)
		{
			for(contadorColumnas = 0;contadorColumnas < TAMANIO;contadorColumnas++)
			{
				System.out.print("|" + comparadorJ1[contadorFilas][contadorColumnas] + "|");
			}
			System.out.println();
		}
		
		return contadorBarcosJ2;
	}

	private void pedirBarcosJ2(char[][] marJ2, String jugador2Nombre) {
		Scanner teclado = new Scanner(System.in);
		int posicionXJ2, posicionYJ2, contadorJ2;
		
		System.out.println(jugador2Nombre + ", introduce tus barcos en el mar: ");
		
		System.out.println("Introduce la fila: ");
		posicionXJ2 = teclado.nextInt();
		teclado.nextLine();
		
		System.out.println("Introduce la columna: ");
		posicionYJ2 = teclado.nextInt();
		teclado.nextLine();
		
		for(contadorJ2 = 0;contadorJ2 < TOTALBARCOS - 1;contadorJ2++)
		{
			marJ2 [posicionXJ2][posicionYJ2] = 'b';
			
			System.out.println("Introduce la fila: ");
			posicionXJ2 = teclado.nextInt();
			teclado.nextLine();
			
			System.out.println("Introduce la columna: ");
			posicionYJ2 = teclado.nextInt();
			teclado.nextLine();
			
			if(marJ2 [posicionXJ2][posicionYJ2] == 'b')
			{
				System.out.println("Ya has introducido en barco en esta posición " + jugador2Nombre + " introduce otra posicion:");
				System.out.println("Introduce la fila: ");
				posicionXJ2 = teclado.nextInt();
				teclado.nextLine();
				
				System.out.println("Introduce la columna: ");
				posicionYJ2 = teclado.nextInt();
				teclado.nextLine();
			}
			else
			{
				if(marJ2 [posicionXJ2 - 1][posicionYJ2] == 'b')
				{
					System.out.println("Has introducido un barco en una casilla colindante, introduce nuevamente una posición:");
					System.out.println("Introduce la fila: ");
					posicionXJ2 = teclado.nextInt();
					teclado.nextLine();
					
					System.out.println("Introduce la columna: ");
					posicionYJ2 = teclado.nextInt();
					teclado.nextLine();
				}
				
				if(marJ2 [posicionXJ2 - 1][posicionYJ2 + 1] == 'b')
				{
					System.out.println("Has introducido un barco en una casilla colindante, introduce nuevamente una posición:");
					System.out.println("Introduce la fila: ");
					posicionXJ2 = teclado.nextInt();
					teclado.nextLine();
					
					System.out.println("Introduce la columna: ");
					posicionYJ2 = teclado.nextInt();
					teclado.nextLine();
				}
				
				if(marJ2 [posicionXJ2][posicionYJ2 + 1] == 'b')
				{
					System.out.println("Has introducido un barco en una casilla colindante, introduce nuevamente una posición:");
					System.out.println("Introduce la fila: ");
					posicionXJ2 = teclado.nextInt();
					teclado.nextLine();
					
					System.out.println("Introduce la columna: ");
					posicionYJ2 = teclado.nextInt();
					teclado.nextLine();
				}
				
				if(marJ2 [posicionXJ2 + 1][posicionYJ2 + 1] == 'b')
				{
					System.out.println("Has introducido un barco en una casilla colindante, introduce nuevamente una posición:");
					System.out.println("Introduce la fila: ");
					posicionXJ2 = teclado.nextInt();
					teclado.nextLine();
					
					System.out.println("Introduce la columna: ");
					posicionYJ2 = teclado.nextInt();
					teclado.nextLine();
				}
				
				if(marJ2 [posicionXJ2 + 1][posicionYJ2] == 'b')
				{
					System.out.println("Has introducido un barco en una casilla colindante, introduce nuevamente una posición:");
					System.out.println("Introduce la fila: ");
					posicionXJ2 = teclado.nextInt();
					teclado.nextLine();
					
					System.out.println("Introduce la columna: ");
					posicionYJ2 = teclado.nextInt();
					teclado.nextLine();
				}
				
				if(marJ2 [posicionXJ2 + 1][posicionYJ2 - 1] == 'b')
				{
					System.out.println("Has introducido un barco en una casilla colindante, introduce nuevamente una posición:");
					System.out.println("Introduce la fila: ");
					posicionXJ2 = teclado.nextInt();
					teclado.nextLine();
					
					System.out.println("Introduce la columna: ");
					posicionYJ2 = teclado.nextInt();
					teclado.nextLine();
				}
				
				if(marJ2 [posicionXJ2][posicionYJ2 - 1] == 'b')
				{
					System.out.println("Has introducido un barco en una casilla colindante, introduce nuevamente una posición:");
					System.out.println("Introduce la fila: ");
					posicionXJ2 = teclado.nextInt();
					teclado.nextLine();
					
					System.out.println("Introduce la columna: ");
					posicionYJ2 = teclado.nextInt();
					teclado.nextLine();
				}
				
				if(marJ2 [posicionXJ2 - 1][posicionYJ2 - 1] == 'b')
				{
					System.out.println("Has introducido un barco en una casilla colindante, introduce nuevamente una posición:");
					System.out.println("Introduce la fila: ");
					posicionXJ2 = teclado.nextInt();
					teclado.nextLine();
					
					System.out.println("Introduce la columna: ");
					posicionYJ2 = teclado.nextInt();
					teclado.nextLine();
				}
			}
			
		}
		
	}

	private void pedirBarcosJ1(char[][] marJ1, String jugador1Nombre) {
		Scanner teclado = new Scanner(System.in);
		int posicionXJ1, posicionYJ1, contadorJ1;
		
		System.out.println(jugador1Nombre + ", introduce tus barcos en el mar: ");
		
		System.out.println("Introduce la fila: ");
		posicionXJ1 = teclado.nextInt();
		teclado.nextLine();
		
		System.out.println("Introduce la columna: ");
		posicionYJ1 = teclado.nextInt();
		teclado.nextLine();
		
		for(contadorJ1 = 0;contadorJ1 < TOTALBARCOS - 1;contadorJ1++)
		{
			marJ1 [posicionXJ1][posicionYJ1] = 'b';
			
			System.out.println("Introduce la fila: ");
			posicionXJ1 = teclado.nextInt();
			teclado.nextLine();
			
			System.out.println("Introduce la columna: ");
			posicionYJ1 = teclado.nextInt();
			teclado.nextLine();
			
			if(marJ1 [posicionXJ1][posicionYJ1] == 'b')
			{
				System.out.println("Ya has introducido en barco en esta posición " + jugador1Nombre + " introduce otra posicion:");
				System.out.println("Introduce la fila: ");
				posicionXJ1 = teclado.nextInt();
				teclado.nextLine();
				
				System.out.println("Introduce la columna: ");
				posicionYJ1 = teclado.nextInt();
				teclado.nextLine();
			}
			else
			{
				if(marJ1 [posicionXJ1 - 1][posicionYJ1] == 'b')
				{
					System.out.println("Has introducido un barco en una casilla colindante, introduce nuevamente una posición:");
					System.out.println("Introduce la fila: ");
					posicionXJ1 = teclado.nextInt();
					teclado.nextLine();
					
					System.out.println("Introduce la columna: ");
					posicionYJ1 = teclado.nextInt();
					teclado.nextLine();
				}
				
				if(marJ1 [posicionXJ1 - 1][posicionYJ1 + 1] == 'b')
				{
					System.out.println("Has introducido un barco en una casilla colindante, introduce nuevamente una posición:");
					System.out.println("Introduce la fila: ");
					posicionXJ1 = teclado.nextInt();
					teclado.nextLine();
					
					System.out.println("Introduce la columna: ");
					posicionYJ1 = teclado.nextInt();
					teclado.nextLine();
				}
				
				if(marJ1 [posicionXJ1][posicionYJ1 + 1] == 'b')
				{
					System.out.println("Has introducido un barco en una casilla colindante, introduce nuevamente una posición:");
					System.out.println("Introduce la fila: ");
					posicionXJ1 = teclado.nextInt();
					teclado.nextLine();
					
					System.out.println("Introduce la columna: ");
					posicionYJ1 = teclado.nextInt();
					teclado.nextLine();
				}
				
				if(marJ1 [posicionXJ1 + 1][posicionYJ1 + 1] == 'b')
				{
					System.out.println("Has introducido un barco en una casilla colindante, introduce nuevamente una posición:");
					System.out.println("Introduce la fila: ");
					posicionXJ1 = teclado.nextInt();
					teclado.nextLine();
					
					System.out.println("Introduce la columna: ");
					posicionYJ1 = teclado.nextInt();
					teclado.nextLine();
				}
				
				if(marJ1 [posicionXJ1 + 1][posicionYJ1] == 'b')
				{
					System.out.println("Has introducido un barco en una casilla colindante, introduce nuevamente una posición:");
					System.out.println("Introduce la fila: ");
					posicionXJ1 = teclado.nextInt();
					teclado.nextLine();
					
					System.out.println("Introduce la columna: ");
					posicionYJ1 = teclado.nextInt();
					teclado.nextLine();
				}
				
				if(marJ1 [posicionXJ1 + 1][posicionYJ1 - 1] == 'b')
				{
					System.out.println("Has introducido un barco en una casilla colindante, introduce nuevamente una posición:");
					System.out.println("Introduce la fila: ");
					posicionXJ1 = teclado.nextInt();
					teclado.nextLine();
					
					System.out.println("Introduce la columna: ");
					posicionYJ1 = teclado.nextInt();
					teclado.nextLine();
				}
				
				if(marJ1 [posicionXJ1][posicionYJ1 - 1] == 'b')
				{
					System.out.println("Has introducido un barco en una casilla colindante, introduce nuevamente una posición:");
					System.out.println("Introduce la fila: ");
					posicionXJ1 = teclado.nextInt();
					teclado.nextLine();
					
					System.out.println("Introduce la columna: ");
					posicionYJ1 = teclado.nextInt();
					teclado.nextLine();
				}
				
				if(marJ1 [posicionXJ1 - 1][posicionYJ1 - 1] == 'b')
				{
					System.out.println("Has introducido un barco en una casilla colindante, introduce nuevamente una posición:");
					System.out.println("Introduce la fila: ");
					posicionXJ1 = teclado.nextInt();
					teclado.nextLine();
					
					System.out.println("Introduce la columna: ");
					posicionYJ1 = teclado.nextInt();
					teclado.nextLine();
				}
			}
			
		}
		
	}
	
	private String nombreJ1() {
		Scanner teclado = new Scanner(System.in);
		String nombre;
		
		System.out.println("Jugador 1, introduce tu nombre: ");
		nombre = teclado.nextLine();
		
		return nombre;
	}

	private String nombreJ2() {
		Scanner teclado = new Scanner(System.in);
		String nombre;
		
		System.out.println("Jugador 2, introduce tu nombre: ");
		nombre = teclado.nextLine();
		
		return nombre;
	}

	private void llenarArrays(char[][] marJ1, char[][] marJ2, char[][] comparadorJ1, char[][] comparadorJ2) {
		int contadorFilas, contadorColumnas;
		
		for(contadorFilas = 0;contadorFilas < TAMANIO;contadorFilas++)
		{
			for(contadorColumnas = 0; contadorColumnas < TAMANIO;contadorColumnas++)
			{
				comparadorJ1[contadorFilas][contadorColumnas] = 'x';
				comparadorJ2[contadorFilas][contadorColumnas] = 'x';
			}
		}
		
		for(contadorFilas = 0;contadorFilas < TAMANIO + 2;contadorFilas++)
		{
			for(contadorColumnas = 0; contadorColumnas < TAMANIO + 2;contadorColumnas++)
			{
				marJ1[contadorFilas][contadorColumnas] = 'a';
				marJ2[contadorFilas][contadorColumnas] = 'a';
			}
		}
	}
}