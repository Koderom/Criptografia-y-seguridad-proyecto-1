/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

/**
 *
 * @author Mirko
 */
public class Columnas {
    private String texto;
    public Columnas(){
        this.texto = "";
    }
    private void cargarTexto(String texto, int clave){
        texto = texto.toUpperCase();
        //texto = texto.replaceAll("[^\\S]", ""); //descomentar para omitir espacions
        for(int i = 0; i < texto.length()%clave; i++)
            texto = texto + "X";
        this.texto = texto;
    }
    //---------------------cifrado---------------------------------------------------
    public String cifrar(String textoClaro, int clave){
        this.cargarTexto(textoClaro, clave);
        char matriz[][] = new char[this.texto.length()/clave][clave];
        matriz = this.cifrar(matriz, 0, 0, 0);
        return leer(matriz);
    }
    private String leer(char matriz[][]){
        String res = "";
        for (int col = 0; col < matriz[0].length; col++) {
            for (int fil = 0; fil < matriz.length; fil++) {
                res = res + Character.toString(matriz[fil][col]);
            }
        }
        return res;
    }
    private char[][] cifrar(char matriz[][], int fil, int col, int pos){
        if(fil == matriz.length -1 && col == matriz[fil].length -1){
            matriz[fil][col] = this.texto.charAt(pos);
            return matriz;
        }
        if(col > matriz[fil].length - 1) return cifrar(matriz, fil + 1, 0, pos);
        matriz[fil][col] = this.texto.charAt(pos);
        return cifrar(matriz, fil, col + 1, pos + 1);
    }
    //---------------------descifrado-------------------------------------------
    public String descifrar(String textoCifrado, int clave){
        cargarTexto(textoCifrado, clave);
        char matriz[][] = new char[this.texto.length()/clave][clave];
        matriz = this.descifrar(matriz, 0, 0, 0);
        return leerDescifrar(matriz);
    }
    private char[][] descifrar(char matriz[][], int fil, int col, int pos){
        if(fil == matriz.length -1 && col == matriz[fil].length -1){
            matriz[fil][col] = this.texto.charAt(pos);
            return matriz;
        }
        if(fil > matriz.length - 1) return descifrar(matriz, 0, col + 1, pos);
        matriz[fil][col] = this.texto.charAt(pos);
        return descifrar(matriz, fil + 1, col, pos + 1);
    }
    private String leerDescifrar(char matriz[][]){
        String res = "";
        for (int fil = 0; fil < matriz.length; fil++) {
            for (int col = 0; col < matriz[fil].length; col++) {
                res = res + Character.toString(matriz[fil][col]);
            }
        }
        return res;
    }
    
}
