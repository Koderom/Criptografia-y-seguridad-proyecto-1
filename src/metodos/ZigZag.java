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
public class ZigZag {
    private int rieles, longPalabra, nOlas, tOla;
    private String texto;

    public ZigZag() {
        this.rieles = 0;
        this.longPalabra = 0;
        this.nOlas = 0;
        this.tOla = 0;
        this.texto = "";
    }
    private void cargarDatos(String texto, int clave){
        //texto = texto.replaceAll("[^\\S]", "");  //descomentar para omitir espacions
        texto = texto.toUpperCase();
        this.rieles = clave;
        this.tOla = (2*clave)-2;
        int longitudDePalabra = texto.length();
        double tempNolas = (double)longitudDePalabra/this.tOla;
        int nOlas = (int)Math.ceil(tempNolas);
        this.nOlas = nOlas;
        if((tOla*this.nOlas)>longitudDePalabra)
            for(int i = 0; i < ((tOla*this.nOlas)-longitudDePalabra); i++)
                texto = texto + "X";
        this.longPalabra = texto.length();
        this.texto = texto;
    }
    //-------------------proceso de cifrado-------------------------------------
    public String cifrar(String textoClaro, int clave){
        this.cargarDatos(textoClaro, clave);
        return this.cifrar();
    }
    private String cifrar(){
        char matriz[][] = new char[this.rieles][nOlas*2];
        matriz = rellenarOla(matriz, 0, 0, 0);
        return leerMatriz(matriz);
    }
    private char[][] rellenarOla(char matriz[][], int fil, int col, int posLetra){
        if(fil == 0 && col == matriz[fil].length -1) return matriz;
        if(fil == 0 && col%2 == 1) return rellenarOla(matriz, 0 , col + 1, posLetra);
        if(fil == matriz.length && col%2 == 0) return rellenarOla(matriz, matriz.length - 2 , col + 1, posLetra);
        if(col%2 == 0){            
            matriz[fil][col] = this.texto.charAt(posLetra);
            return rellenarOla(matriz, fil + 1, col, posLetra + 1);
        }else{
            matriz[fil][col] = this.texto.charAt(posLetra);
            return rellenarOla(matriz, fil - 1, col, posLetra + 1);
        }
    }
    private String leerMatriz(char matriz[][]){
        String res = "";
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if(matriz[i][j] != Character.MIN_VALUE)
                res = res + Character.toString(matriz[i][j]);
            }
        }
        return res;
    }
    //-------------------proceso de desifrado-----------------------------------
    public String descifrar(String textoCifrado, int clave){
        this.cargarDatos(textoCifrado, clave);
        return this.descifrar();
    }
    private String descifrar(){
        char matriz[][] = new char[this.rieles][nOlas*2];
        matriz = rellenarOlaDescf(matriz);
        return leerMatrizDesf(matriz, 0, 0);
    }
    private char[][] rellenarOlaDescf(char matriz[][]){
        int posLetra = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length;j++) {
                if(j%2 == 1 && (i == 0 || i == matriz.length -1)) continue;
                matriz[i][j] = this.texto.charAt(posLetra);
                posLetra++;
            }
        }
        return matriz;
    }
    private String leerMatrizDesf(char[][] matriz, int fil, int col){
        if(fil == 0 && col == matriz[fil].length -1) return "";
        if(fil == 0 && col%2 == 1) return ""+ leerMatrizDesf(matriz, fil, col+1);
        if(fil == matriz.length && col%2 == 0) return ""+ leerMatrizDesf(matriz, fil - 1, col+1);
        if(col%2 == 0) return Character.toString(matriz[fil][col]) + leerMatrizDesf(matriz, fil + 1, col);
        else return Character.toString(matriz[fil][col]) + leerMatrizDesf(matriz, fil - 1, col);
    }
    //-------------------auxiliares---------------------------------------------
    @Override
    public String toString(){
        return "texto claro : " + this.texto+"\n"+
                "tamaño ola : " + this.tOla+"\n"+
                "texto rieles : " + this.rieles+"\n"+
                "longitud palabra : " + this.longPalabra+"\n"+
                "numero de Olas : " + this.nOlas+"\n";
    }
    private void verMatriz(char matriz[][]){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                
                if(matriz[i][j]== Character.MIN_VALUE)System.out.print("X");
                else System.out.print(matriz[i][j]);
            }
            System.out.println("");
        }
    }
}
