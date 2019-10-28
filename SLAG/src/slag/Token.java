/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slag;

/**
 *
 * @author STEWART
 */
public class Token{
  int tipo;
  String valor;
  int linea;

  public Token(int tipo, String valor, int linea){
    this.tipo=tipo;
    this.valor=valor;
    this.linea=linea;
  }

  public void settipo(int tipo){
    this.tipo=tipo;
  }

  public void setvalor(String valor){
    this.valor=valor;
  }

  public void setlinea(int linea){
    this.linea=linea;
  }

  public int gettipo(){
    return tipo;
  }

  public String setvalor(){
    return valor;
  }

  public int setlinea(){
    return linea;
  }

  public String imprimir(){
    String salida="<"+tipo+","+valor+","+linea+">";
    System.out.println(salida);
    return salida;
  }
}

