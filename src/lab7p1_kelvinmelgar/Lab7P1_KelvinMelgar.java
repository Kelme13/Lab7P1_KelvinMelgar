package lab7p1_kelvinmelgar;

/**
 *
 * @author kelvi
 */

import javax.swing.JOptionPane;
import java.security.SecureRandom;
import java.util.Scanner;
public class Lab7P1_KelvinMelgar {

    /**
     * @param args the command line arguments
     */
    static Scanner sc = new Scanner(System.in);
    static SecureRandom num_ram = new SecureRandom();
    
    public static void main(String[] args) {
        int opcion;
        
        do{
            opcion = menu();
            
            switch(opcion){
                
                case 1 -> {
                    int[] filCol = obtenerFilCol();
                    
                    int[][] matriz = lecturaMatriz(filCol[0], filCol[1]);
                    
                    if (filCol[0] != filCol[1]) {

                        JOptionPane.showMessageDialog(null, "Matriz Base\n" + 
                                imprimir(matriz) + "\nMatriz rotada\n" + imprimir(portrait(matriz)) + "\n"
                                , "Portrait", 1);

                    }else{
                        JOptionPane.showMessageDialog(null,
                            "\nLa matriz no puede ser cuadrada\n\n",
                            "Error", 0);
                    }
                    
                }
                
                case 2 -> {
                    int[] filCol = obtenerFilCol();
                    
                    int[][] matriz = lecturaMatriz(filCol[0], filCol[1]);
                    
                    int[] result = numeroMagico(matriz);
                    
                    JOptionPane.showMessageDialog(null, "Matriz\n" + 
                                imprimir(matriz) + "\nSuma exteriores: " + result[0] +
                                "\nMultiplicacion interiores: " + result[1] +
                                "\nNumero Magico: " + result[2] + "\n"
                                , "Numero Magico", 1);
                    
                    
                }
                
                case 3 -> {
                    String cadena1 = JOptionPane.showInputDialog(null,
                            "Ingrese la cadena 1",
                            "Subsecuencia - Cadena 1", 1);
                    
                    String cadena2 = JOptionPane.showInputDialog(null,
                            "Ingrese la cadena 2",
                            "Subsecuencia - Cadena 2", 1);
                    
                    cadena1 = cadena1.toLowerCase();
                    cadena2 = cadena2.toLowerCase();
                    
                    
                    int tam_sub = subSecuencia(cadena1, cadena2);

                    JOptionPane.showMessageDialog(null, "Cadena 1: " + 
                                cadena1 + "\nCadena 2: " + cadena2 + "\n\n" + 
                                "Tamanio Subsecuencia mas grande: \n" + tam_sub
                                , "Subsecuencia", 1);

                }
                
                case 4 -> {
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    
                }
                
                default -> {
                    JOptionPane.showMessageDialog(null,
                            "\nOpcion Invalida...\n\n",
                            "Error", 0);
                }
                
            }

        }while(opcion != 4);
        
        
        
        
    }
    
    
    public static int menu(){
        int opcion;
        
        String menu = """
                         <-      Laboratorio 7    -->
                         
                          1. Portrait
                          2. Numero Magico
                          3. Subsecuencia
                          
                          4. salir.
                         
                        """;
        
        opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                    menu, "Menu", 1));
        
        return opcion;
    }
    
    public static int[][] lecturaMatriz(int fila, int colum){
        int[][] temp = new int[fila][colum];
        
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < colum; j++) {
                temp[i][j] = num_ram.nextInt(10);
            }
            
        }
        
        return temp;
    }
    
    public static String imprimir(int[][] matris){
        String cadena = "";
        
        for (int i = 0; i < matris.length; i++) {
            
            for (int j = 0; j < matris[0].length; j++) {
                cadena += matris[i][j] + " ";
                
            }
            
            cadena += "\n";
        }
        return cadena;
    }
    
    public static int[][] portrait(int[][] matriz){
        //intercambia el tamanio de la matriz
        int[][] temp = new int[matriz[0].length][matriz.length];
        
        for (int i = 0; i < temp.length; i++) {
            
            for (int j = 0; j < temp[0].length; j++) {
                
                //obtiene los valores originales de la matriz
                temp[i][j] = matriz[(matriz.length - 1) - j][i]; //obtiene el ultimo valor de la matriz base
            }
        }
        
        return temp;
    }
    
    public static int[] numeroMagico(int[][] matriz){
        //los devuelve en un arreglo los respectivos datos.
        int[] datos = new int[3];
        int n_generado;
        int acum_sum = 0;
        int acum_mult = 1;//si inicia en cero siempre sera cero
        
        for (int i = 0; i < matriz.length; i++) {
            
            for (int j = 0; j < matriz[0].length; j++) {
                
                if(i == 0 || j == 0 || matriz.length - i == 1 || matriz[0].length - j == 1 )//comprueba los exteriores
                    acum_sum += matriz[i][j];
                else
                    acum_mult *= matriz[i][j];
            }
        }
        
        n_generado = acum_sum + acum_mult;
        
        
        //agrega los datos al arreglo;
        datos[0] = acum_sum;
        datos[1] = acum_mult;
        datos[2] = n_generado;
        
        return datos;
    }
    
    public static int subSecuencia(String cad1, String cad2){
        
        cad1 = "-"+ cad1;
        cad2 = "-"+ cad2;
        
        //obtiene el tamnio de la matriz en base a las cadenas
        int[][] matriz = new int[cad2.length()][cad1.length()];
        
        for (int i = 0; i < matriz.length; i++) {
            
            for (int j = 0; j < matriz[0].length; j++) {
                
                if(cad1.charAt(j) == '-' || cad2.charAt(i) == '-')
                    matriz[i][j] = 0;
                
                else if(cad1.charAt(j) == cad2.charAt(i)){
                    matriz[i][j] = 1 + matriz[i-1][j-1];
                }
                else{
                    matriz[i][j] = Math.max(matriz[i-1][j],matriz[i][j-1]);
                }
                
            }
        }
        
        
        int tam = matriz[cad2.length()-1][cad1.length()-1];
        
        //imprimir la matriz
        System.out.println(imprimir(matriz));
        
        return tam;
        
    }
    
    public static int[] obtenerFilCol(){
        //Para no estar copiando el mismo codigo, los devueve en un arreglo los respectivos datos.
        int[] temp = new int[2];
                
                temp[0] = Integer.parseInt(JOptionPane.showInputDialog(null,
                              "Ingrese el tamanio de la fila",
                              "Fila Matriz", 3));
                temp[1] = Integer.parseInt(JOptionPane.showInputDialog(null,
                              "Ingrese el tamanio de las columnas",
                              "Columna Matriz", 3));
        return temp;
    }
}
