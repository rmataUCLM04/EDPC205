/*********************************************************************
*
* Class Name: postfija.java
* Author/s name: RMM, HJC, PMS
* Release/Creation date: 1-10-2025
* Class version: 3
* Class description: En esta clase implementamos el funcionamiento del programa que se nos pide mediante pilas.
*
**********************************************************************
*/

package practicas25_26;
import java.util.Scanner;
import java.util.Stack;

public class postfija {
    final static Scanner TECLADO = new Scanner(System.in);
    
    /*********************************************************************
    *
    * Method name: Main
    *
    * Name of the original author (if the module author is different
    * than the author of the file):
    *
    * Description of the Method: Creamos la pila donde almacenamos el texto y
    * los operandos. Imprimimos la solución.
    *
    * Calling arguments:
    *    - String[] args : Array de cadenas que recibe parámetros desde la línea
    *                     de comandos. En este programa no se utiliza, pero se
    *                     incluye por la definición estándar del método main.
    *
    * Return value: Es de tipo void
    *
    * Required Files: N/A
    *
    * List of Checked Exceptions and an indication of when each exception
    * is thrown. N/A
    *
    *********************************************************************/

    
    public static void main(String[] args) {
        Stack<String> pila = new Stack<>(); // Creamos una pila de String que guardará operandos o texto.

        System.out.print("Introduce la expresión a calcular: ");
        String expresion = TECLADO.nextLine(); // Introducimos el texto y el/los operandos por teclado.
        pila = EvaluarPila(pila, expresion);   

        System.out.println("Resultado final: " + pila.peek());
        

    }
    
    /*********************************************************************
    *
    * Method name: operaciones
    *
    * Name of the original author (if the module author is different
    * than the author of the file):
    *
    * Description of the Method: Comprueba si la cadena recibida corresponde
    * a un operador válido dentro de la expresión posfija. Los operadores
    * reconocidos son: '+', '-', '@' y '*'. Si la cadena coincide con alguno
    * de ellos, devuelve true; en caso contrario, devuelve false.
    *
    * Calling arguments:
    *    - String cadena : Cadena que contiene el elemento a evaluar. Puede
    *                      ser un operando (texto) o un operador. Se analiza
    *                      su valor para determinar si es una operación válida.
    *
    * Return value:
    *    - boolean : Devuelve true si la cadena es un operador válido
    *                ('+', '-', '@' o '*'); false en caso contrario.
    *
    * Required Files: N/A
    *
    * List of Checked Exceptions and an indication of when each exception
    * is thrown: No se lanzan excepciones comprobadas en este método.
    *
    *********************************************************************/

	public static boolean operaciones(String cadena) {
		boolean control = false;

		if (cadena.equals("+") || cadena.equals("-") || cadena.equals("@") || cadena.equals("*")) {

			control = true;
		}

		return control;
	}
	
	/*********************************************************************
	 *
	 * Method name: EvaluarPila
	 *
	 * Name of the original author (if the module author is different
	 * than the author of the file):
	 *
	 * Description of the Method:
	 *    Recorre la expresión posfija (tokens separados por un espacio) y la
	 *    evalúa usando una pila de cadenas. Si el token no es un operador,
	 *    se apila como operando. Si es operador:
	 *      - '+' : concatena dos operandos (pop derecho y luego pop izquierdo)
	 *      - '-' : elimina de la cadena izquierda los caracteres presentes en la derecha
	 *      - '@' : invierte una cadena usando una pila auxiliar (operador unario)
	 *      - '*' : mantiene en la cadena izquierda solo los caracteres presentes en la derecha
	 *    El resultado de cada operación se vuelve a apilar. Al finalizar, la pila
	 *    queda con el resultado en su cima (si la expresión es correcta).
	 *
	 * Calling arguments:
	 *    - Stack<String> pila :
	 *         Pila donde se irán apilando operandos y resultados parciales.
	 *         Debe estar inicializada (puede venir vacía).
	 *    - String expresion :
	 *         Cadena con la expresión en notación posfija. Los tokens deben ir
	 *         separados por un espacio simple (" "). Se reconocen los operadores
	 *         '+', '-', '@' y '*'.
	 *
	 * Return value:
	 *    - Stack<String> :
	 *         La misma pila recibida como argumento, tras procesar la expresión.
	 *         Si la expresión es válida, la cima contendrá el resultado final.
	 *
	 * Required Files:
	 *    - N/A (no se usan ficheros).
	 *
	 * List of Checked Exceptions and an indication of when each exception
	 * is thrown:
	 *    - No se lanzan excepciones comprobadas (checked) en este método.
	 *      Nota: si la expresión es incorrecta (p. ej., faltan operandos),
	 *      podrían producirse excepciones no comprobadas como
	 *      java.util.EmptyStackException al hacer pop/peek sobre una pila vacía.
	 *
	 *********************************************************************/


    public static Stack<String> EvaluarPila( Stack<String> pila, String expresion) {
        
        String[] trozosPila; // String donde guardamos cada operando (operador u operando).
    
        trozosPila = expresion.split(" "); // Parte la expresión por espacios
                
        for (int i = 0; i < trozosPila.length; i++) { 

            if (!operaciones(trozosPila[i])) {
                
                pila.push(trozosPila[i]); // Si no es operacion lo apilo

            } else {
                
                String opDer, opIzq; // Declaramos dos variables donde almacenaremos los operandos derecho y izquierdo
               
                switch (trozosPila[i]) {
                
                    case "+":
                    	// primero derecha, luego izquierda
                        opDer = pila.pop();
                        opIzq = pila.pop();
                        
                        String resultado_cat = concatenar(opIzq, opDer);
                        pila.push(resultado_cat);

                        break;
                        
                    case "-":
                        // primero derecha, luego izquierda
                        opDer = pila.pop();
                        opIzq = pila.pop();
                        String resultado_res = eliminar(opIzq, opDer);
                        pila.push(resultado_res);
                        break;

                        
                    case "@":
                        // Este caso es unario                       
                        String op = pila.pop();  // saco el operando encima de la pila        
                        String resultado_inv = invertir(op); // invierto el operando
                        pila.push(resultado_inv);                
                        break;

                    case "*":
                        // primero derecha, luego izquierda
                        opDer = pila.pop();
                        opIzq = pila.pop();
                        String resultado_intersec = interseccion(opIzq, opDer);
                        pila.push(resultado_intersec);  
                        break;

                    default:
                        break;
                }         
            }
        }
        return pila;

    }
    
    /*********************************************************************
    *
    * Method name: concatenar
    *
    * Name of the original author (if the module author is different
    * than the author of the file):
    *
    * Description of the Method:
    *    Une dos cadenas de texto (operandos) en una sola, colocando el
    *    segundo texto (op2) inmediatamente después del primero (op1).
    *    Este método implementa la operación de concatenación asociada
    *    al operador '+' de la notación posfija.
    *
    * Calling arguments:
    *    - String op1 : Primer operando (cadena izquierda) que se colocará
    *                   al inicio del resultado.
    *    - String op2 : Segundo operando (cadena derecha) que se añadirá
    *                   al final del resultado.
    *
    * Return value:
    *    - String : Devuelve la concatenación de las dos cadenas, es decir,
    *               el texto resultante de unir op1 y op2 en ese orden.
    *
    * Required Files: N/A
    *
    * List of Checked Exceptions and an indication of when each exception
    * is thrown:
    *    - No se lanzan excepciones comprobadas (checked exceptions).
    *
    *********************************************************************/

	public static String concatenar(String op1, String op2) {
		return op1 + op2;
	}
    
	/*********************************************************************
	 *
	 * Method name: eliminar
	 *
	 * Name of the original author (if the module author is different
	 * than the author of the file):
	 *
	 * Description of the Method:
	 *    Elimina de la primera cadena (op1) todos los caracteres que estén
	 *    presentes en la segunda cadena (op2). Mantiene el orden original
	 *    de los caracteres restantes en op1. Este método implementa la
	 *    operación asociada al operador '-' en la notación posfija.
	 *
	 * Calling arguments:
	 *    - String op1 : Primer operando (cadena izquierda) de la que se
	 *                   eliminarán los caracteres coincidentes.
	 *    - String op2 : Segundo operando (cadena derecha) que contiene los
	 *                   caracteres que deben eliminarse de op1.
	 *
	 * Return value:
	 *    - String : Devuelve una nueva cadena formada por los caracteres
	 *               de op1 que no aparecen en op2, en el mismo orden en
	 *               que estaban originalmente.
	 *
	 * Required Files: N/A
	 *
	 * List of Checked Exceptions and an indication of when each exception
	 * is thrown:
	 *    - No se lanzan excepciones comprobadas (checked exceptions).
	 *
	 *********************************************************************/

	public static String eliminar(String op1, String op2) {
	    String resultado = ""; // Aqui almacenaremos el resultado

	    for (int i = 0; i < op1.length(); i++) {
	        char c1 = op1.charAt(i); // Almacenamos el caracter actual de op1
	        boolean encontrado = false;

	        for (int j = 0; j < op2.length(); j++) {
	            if (c1 == op2.charAt(j)) { // Comprobamos si el caracter de op1 está en op2
	                encontrado = true; // el caracter de op1 está en op2
	            }
	        }

	        if (!encontrado) {
	            resultado = resultado + c1; // solo añadimos los que no están en op2
	        }
	    }

	    return resultado;
	}

	/*********************************************************************
	 *
	 * Method name: invertir
	 *
	 * Name of the original author (if the module author is different
	 * than the author of the file):
	 *
	 * Description of the Method:
	 *    Invierte el orden de los caracteres de la cadena recibida como
	 *    parámetro. Para realizar la inversión utiliza una pila auxiliar
	 *    de caracteres: primero apila todos los caracteres en orden y
	 *    después los desapila uno a uno, formando así la cadena invertida.
	 *    Este método implementa la operación asociada al operador '@' en
	 *    la notación posfija.
	 *
	 * Calling arguments:
	 *    - String a : Cadena original que se desea invertir. Cada uno de sus
	 *                 caracteres se apilará en orden y luego se desapilará
	 *                 para formar la cadena invertida. En este método no 
	 *                 tendremos String b dado que es unario.
	 *
	 * Return value:
	 *    - String : Devuelve una nueva cadena que contiene los mismos
	 *               caracteres de la original, pero en orden inverso.
	 *
	 * Required Files: N/A
	 *
	 * List of Checked Exceptions and an indication of when each exception
	 * is thrown:
	 *    - No se lanzan excepciones comprobadas (checked exceptions).
	 *
	 *********************************************************************/


    public static String invertir(String a) {
        Stack<Character> aux = new Stack<>(); // Pila auxiliar para invertir la cadena
        // Meter caracteres en la pila auxiliar
        for (int i = 0; i < a.length(); i++) {
            aux.push(a.charAt(i));
        }
        // Sacar y armar la cadena invertida
        String invertida = "";
        while (!aux.isEmpty()) {
            invertida = invertida + aux.pop(); // concatenación
        }
        return invertida;
    }
    
    /*********************************************************************
    *
    * Method name: interseccion
    *
    * Name of the original author (if the module author is different
    * than the author of the file):
    *
    * Description of the Method:
    *    Compara los caracteres de dos cadenas (op1 y op2) y construye una
    *    nueva cadena formada únicamente por los caracteres de op1 que
    *    también están presentes en op2. El orden de los caracteres se
    *    mantiene igual al de la cadena op1. Este método implementa la
    *    operación asociada al operador '*' en la notación posfija.
    *
    * Calling arguments:
    *    - String op1 : Primer operando (cadena izquierda). De esta cadena
    *                   se tomarán los caracteres que estén también en op2.
    *    - String op2 : Segundo operando (cadena derecha). Sirve como
    *                   referencia para decidir qué caracteres se conservan.
    *
    * Return value:
    *    - String : Devuelve una nueva cadena con los caracteres de op1
    *               que aparecen en op2, respetando el orden original de op1.
    *
    * Required Files: N/A
    *
    * List of Checked Exceptions and an indication of when each exception
    * is thrown:
    *    - No se lanzan excepciones comprobadas (checked exceptions).
    *
    *********************************************************************/

    public static String interseccion(String op1, String op2) {
        String resultado = ""; // Cadena vacia auxliar donde almacenaremos el resultado

        for (int i = 0; i < op1.length(); i++) { // Iteramos y sacamos los caracteres de la cadena uno a uno
            char c1 = op1.charAt(i);

            for (int j = 0; j < op2.length(); j++) {
                if (c1 == op2.charAt(j)) {
                    resultado = resultado + c1; // si el caracter de op1 está en op2, lo guardamos
                }
            }
        }

        return resultado;
    }
}
