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
import java.util.ArrayList;

public class AFD{
  String errorc;
  String aux;
  TablaTransiciones t;
  ArrayList<String> s=new ArrayList<String>();
  ArrayList<Token> tokens=new ArrayList<Token>();
  ArrayList<String> palabrasr=new ArrayList<String>();

  public AFD(int n, ArrayList<String> simbolos){
    s=simbolos;
    t=new TablaTransiciones(n,s.size());
  }

  public void AgregarEstadoInicial(int ei){
    t.SetInicial(ei);
  }

  public void AgregarEstadoFinal(int ef, int retro){
    t.SetFinal(ef,retro);
  }

  public void AgregarSimbolo(String ss){
    s.add(ss);
  }

  public void AgregarTransicion(int e1, int e2, String ss){
    if(s.contains(ss)){
      t.AddTransicion(e1,e2,s.indexOf(ss));
    }
  }

  public void AgregarTransicionOtro(int e1, int e2){
    t.AddTransicionOtro(e1,e2);
  }

  public void resetTokens(){
    tokens=new ArrayList<Token>();
  }

  public void resetPalabras(){
    palabrasr=new ArrayList<String>();
  }

  public void resetSimbolos(){
    s=new ArrayList<String>();
  }

  public String getError(){
    return errorc;
  }

  public ArrayList<Token> GetTokens(){
    return tokens;
  }

  public void addPalabrareservada(String p){
    palabrasr.add(p);
  }

  public int Revisartipo(){
    System.out.println("revisando: "+aux.toUpperCase());
    if(palabrasr.contains(aux.toUpperCase()))
      return t.getTipo()+palabrasr.indexOf(aux.toUpperCase());
    else
      return t.CheckTipo(t.GetEstado());
  }
  
  public void addfinArchivo(){
      int iiii=tokens.get(tokens.size()-1).linea;
      tokens.add(new Token(t.getTipo()+palabrasr.size(),"fin archivo",iiii));
  }

  public Boolean RevisarTransicion(char simbolo){
    if(s.contains(simbolo+"") && t.ComprobarTransicion(t.GetEstado(),s.indexOf(simbolo+""))>=0){
      t.SetEstado(t.ComprobarTransicion(t.GetEstado(),s.indexOf(simbolo+"")));
      aux+=simbolo;
      return true;
    }
    else
      return false;
  }

  public Boolean RevisarTransicionOtro(char simbolo){
    if(t.ComprobarTransicionOtro(t.GetEstado())>=0){
      t.SetEstado(t.ComprobarTransicionOtro(t.GetEstado()));
      aux+=simbolo;
      return true;
    }
    else
      return false;
  }

  public int RevisarEstadoActual(){
    return t.CheckFinal(t.GetEstado());
  }

  public void ImprimirListaSimbolos(){
    int num=s.size();
    System.out.println("Lista de simbolos(");
    for(int i=0;i<num;i++)
      System.out.println(s.get(i));
    System.out.println(")");
  }

  public void ImprimirTablaTransisiones(){
    System.out.println("Tabla de transiciones");
    t.imprimirtabla();
    System.out.println("Transiciones Otro");
    t.imprimirOtro();
  }

  public String ImprimirTokens(){
    String salida="";
    int num=tokens.size();
    System.out.println("Lista de tokens");

    for(int i=0;i<num;i++)
      salida+=tokens.get(i).imprimir()+"\n";
    System.out.println(salida);
    return(salida);
  }

  public Boolean ComprobarLinea(String linea, int lin){
    aux="";
    char simbolo;
    int retroseso=0,n=linea.length();
    t.SetEstado(t.GetEstadoInicial());
    Boolean bandera=true;
    int ii=t.GetREtGrand();

    for(int i=0;i<=n+ii && bandera;i++){
      simbolo=(i>=n)?'\n':linea.charAt(i);
      if(simbolo!=' ' &&  simbolo!='\t' && simbolo!='\n'){
        //simbolos no separadores
        if(!RevisarTransicion(simbolo)){
          errorc=s.contains(""+simbolo)?(lin+":ERROR: TOKEN NO ADMITIDO\n"+linea+"\n\" "+(aux+=simbolo)+" \"\n"):(lin+":ERROR: SIMBOLO NO ADMITIDO\n"+linea+"\n\" "+(simbolo)+" \"\n");
          bandera=false;
          System.out.println(errorc);
          break;
        }
        retroseso=RevisarEstadoActual();
        if(retroseso>=0){
          aux=aux.substring(0,(aux.length()-retroseso));
          tokens.add(new Token(Revisartipo(),aux,lin));
          System.out.println("nuevo token:"+Revisartipo()+", "+aux+", "+lin);
          aux="";
          t.SetEstado(t.GetEstadoInicial());
        }else
          retroseso=0;
      }else if(aux.compareTo("")!=0){
        //simbolos separadores
        if(!RevisarTransicionOtro(simbolo)){
          errorc=s.contains(""+simbolo)?(lin+":ERROR: TOKEN NO ADMITIDO\n"+linea+"\n\" "+(aux+=simbolo)+" \"\n"):(lin+":ERROR: SIMBOLO NO ADMITIDO\n"+linea+"\n\" "+(simbolo)+" \"\n");
          bandera=false;
          System.out.println(errorc);
          break;
        }
        retroseso=RevisarEstadoActual();
        if(retroseso>=0){
          aux=aux.substring(0,(aux.length()-retroseso));
          tokens.add(new Token(Revisartipo(),aux,lin));
          System.out.println("nuevo token:"+Revisartipo()+", "+aux+", "+lin);
          aux="";
          t.SetEstado(t.GetEstadoInicial());
        }else
          retroseso=0;
      }
      i-=retroseso;
      retroseso=0;
    }
    
    if(bandera==true && aux.compareTo("")!=0){
        errorc=lin+":ERROR: TOKEN NO ADMITIDO\n"+linea+"\n\" "+(aux)+" \"\n";
        bandera=false;
        System.out.println(errorc);
    }
    return bandera;
  }


  public Boolean ComprobarLinea2(String linea, int lin){
    aux="";
    char simbolo;
    int retroseso=0,n=linea.length();
    t.SetEstado(t.GetEstadoInicial());
    Boolean bandera=true;
    resetTokens();

    for(int i=0;i<=n;i++){
      if(i<n)
        simbolo=linea.charAt(i);
      else
        simbolo='\n';

      i=i-retroseso;
      retroseso=0;

      if(simbolo==' ' ||  simbolo=='\t' || simbolo=='\n'){
        if(aux.compareTo("")!=0)
          if(RevisarTransicionOtro(simbolo)){
            if((retroseso=RevisarEstadoActual())>=0){
              tokens.add(new Token(Revisartipo(),aux.substring(0,(aux.length()-retroseso)),lin));
              System.out.println("nuevo token:"+Revisartipo()+", "+aux+", "+lin);
              aux="";
              t.SetEstado(t.GetEstadoInicial());
            }
          }
          else{
            if(s.contains(""+simbolo))
              errorc="en la linea "+lin+":\n"+linea+"\n la cadena \" "+aux+" \" no pertenese al lenguaje";
            else
              errorc="en la linea "+lin+":\n"+linea+"\n el simbolo \" "+simbolo+" \" no pertenese al alfabeto";
            System.out.println(errorc);
            i=n+1;
            bandera=false;
          }
      }
      else{
        if(RevisarTransicion(simbolo)){
          if((retroseso=RevisarEstadoActual())>=0){
            tokens.add(new Token(Revisartipo(),aux.substring(0,(aux.length()-retroseso)),lin));
            System.out.println("nuevo token:"+Revisartipo()+", "+aux+", "+lin);
            aux="";
            t.SetEstado(t.GetEstadoInicial());

          }
        }
        else{
          System.out.println(linea);
          if(s.contains(""+simbolo))
            errorc="en la linea "+lin+":\n"+linea+"\n la cadena \" "+aux+" \" no pertenese al lenguaje";
          else
            errorc="en la linea "+lin+":\n"+linea+"\n el simbolo \" "+simbolo+" \" no pertenese al alfabeto";
          System.out.println(errorc);
          bandera=false;
          i=n+1;
        }
      }
    }
    System.out.println("remanente: "+aux);
    return bandera;
  }

}
