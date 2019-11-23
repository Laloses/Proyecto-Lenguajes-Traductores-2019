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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AnalizadorLexico{
  String archivo_analizar;
  String archivo_especificacion;
  AFD automata;
  int indice=0;

  public AnalizadorLexico(){
    archivo_analizar=null;
    archivo_especificacion=null;
    automata=null;
  }

  public AnalizadorLexico(String s1){
    archivo_analizar=null;
    archivo_especificacion=s1;
    automata=null;
  }

  public AnalizadorLexico(String s1, String s2){
    archivo_analizar=s2;
    archivo_especificacion=s1;
    automata=null;
  }

  public void SetArchivoAnalizar(String s){
    archivo_analizar=s;
  }

  public void SetArchivoEspecificacion(String s){
    archivo_especificacion=s;
  }

  public void DropArchivoAnalizar(){
    archivo_analizar=null;
  }

  public void DropArchivoEspecificacion(){
    archivo_especificacion=null;
  }

  public String getErroranalisis(){
    return automata.getError();
  }

  public void SetEstadoFinal(String s){
    String aux="";
    int e1,e2,n=s.length(),i=0;

    while(s.charAt(i)!='-'){
      aux=aux+s.charAt(i)+"";
      i++;
    }

    e1=Integer.parseInt(aux);
    aux="";
    i++;

    while(i<n){
      aux=aux+s.charAt(i)+"";
      i++;
    }

    e2=Integer.parseInt(aux);
    aux="";
    i++;
    automata.AgregarEstadoFinal(e1,e2);
  }

  public void DecodificarTransicion(String s){
    String aux="";
    int e1,e2;
    int n=s.length();
    int i=0;

    while(s.charAt(i)!='-'){
      aux=aux+s.charAt(i)+"";
      i++;
    }

    e1=Integer.parseInt(aux);
    aux="";
    i++;

    while(s.charAt(i)!='-'){
      aux=aux+s.charAt(i)+"";
      i++;
    }

    e2=Integer.parseInt(aux);
    aux="";
    i++;

    while(i<n){
      aux=aux+s.charAt(i)+"";
      i++;
    }
    if(aux.compareTo("otro")==0)
      automata.AgregarTransicionOtro(e1,e2);
    else{
      if(aux.compareTo("numeros")==0)
        TransicionNumeros(e1,e2);
      else{
        if(aux.compareTo("letras")==0)
          TransicionLetras(e1,e2);
        else
          automata.AgregarTransicion(e1,e2,aux);
      }
    }
  }

  public void TransicionLetras(int e1, int e2){
    for(char i='a';i<='z';i++)
      automata.AgregarTransicion(e1,e2,""+i);
    for(char i='A';i<='Z';i++)
      automata.AgregarTransicion(e1,e2,""+i);
  }

  public void TransicionNumeros(int e1, int e2){
    for(int i=0;i<10;i++)
      automata.AgregarTransicion(e1,e2,""+i);
  }

  public void LeerArchivoEspecificacion()throws FileNotFoundException, IOException{
    ArrayList<String> sim=new ArrayList<String>();
    int n;
    String cadena;
    System.out.println(archivo_especificacion);
    FileReader f=new FileReader(archivo_especificacion);
    System.out.println(archivo_especificacion);
    BufferedReader b = new BufferedReader(f);

    //Aqui esta el formato de la especificacion del lexico, el TXT que no tiene comentarios
    //Esto hay que pasarselo a Jorge, para que lo meta al documento
    
    //leemos los simbolos disponibles
    while((cadena=b.readLine()).compareTo("---F---")!=0){
        sim.add(cadena);
    }
    //leemos el numero de estados
    n=Integer.parseInt(b.readLine());
    automata=new AFD(n,sim);

    //leemos los estados finales y su retroseso
    while((cadena=b.readLine()).compareTo("---F---")!=0){
      SetEstadoFinal(cadena);
    }

    //leemos las palabaras reservadas
    while((cadena=b.readLine()).compareTo("---F---")!=0){
      automata.addPalabrareservada(cadena);
    }

    //leemos las transisiones
    while((cadena = b.readLine())!=null) {
            DecodificarTransicion(cadena);
    }
    b.close();
    System.out.println("especificacion cargada");
    automata.ImprimirListaSimbolos();
    automata.ImprimirTablaTransisiones();
    System.out.println("numero de tokens "+this.automata.t.tipo);
    //automata.Imp
  }

  public void reiniciarTokens(){
    automata.resetTokens();
  }

  public ArrayList<Token> ObtenerTokens(){
    return automata.GetTokens();
  }

  public String ImprimirListaTokens(){
    return automata.ImprimirTokens();
  }


  public Boolean LeerArchivoAnalizar()throws FileNotFoundException, IOException{
    Boolean Noerror=true;
    String cadena;
    FileReader f=new FileReader(archivo_analizar);
    BufferedReader b = new BufferedReader(f);
    int i=1;
    automata.resetTokens();

    while((cadena = b.readLine())!=null && Noerror) {
      Noerror=automata.ComprobarLinea(cadena,i);
      i++;
    }
    System.out.println("Archivo a analizar: "+archivo_analizar);
    b.close();
    automata.addfinArchivo();
    return Noerror;
  }
  
  public String getLinea(int linear){
      int lineac=0;
      String cadena="";
      try{
          FileReader f=new FileReader(archivo_analizar);
          BufferedReader b = new BufferedReader(f);
          while(lineac<linear){
              cadena=b.readLine();
              System.out.println("linea a comprobar__"+cadena);
              lineac++;
          }
          b.close();
      }catch(Exception e){}
      return cadena;
  }
  
  public Token GetToken(){
      if(indice<automata.tokens.size()){
        indice++;
        return(automata.tokens.get(indice-1));
      }else
        return null;
  }
  
  public void ResetListaToken(){
      indice=0;
  }  
}

