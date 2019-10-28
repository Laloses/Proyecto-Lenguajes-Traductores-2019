/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slag;

import java.util.ArrayList;

/**
 *
 * @author STEWART
 */
public class TablaVariablesConstantes {
    //tipos de valores
    final static int tipoentero=0;
    final static int tipocaracter=1;
    //tipos de almacenamiento
    final static int almacenavari=0;
    final static int almacenaconst=1;
    final static int almacenaarray=3;
    ArrayList<VariableConstanteArreglo> a;
    String Error;
    int[] s;
    char[] s2;
    
    int ss;
    char ss2;
    boolean valorObtenido=false;
    
    public TablaVariablesConstantes(){
        a=new ArrayList<VariableConstanteArreglo>();
    }
    
    public boolean SetConstanteEntera(String id, int valor){
        
        if(a.size()==0){
            a.add(new ConstanteEntera(id,valor));
            return true;
        }else{
            boolean b=true;
            for(int i=0;i<a.size();i++){
                if(a.get(i).id.compareTo(id)==0){
                    b=false;
                    Error="El id \" "+id+" \" ya esta asociado a otro elemento";
                    //System.out.println("tabla "+ Error);
                }
            }
            if(b)
              a.add(new ConstanteEntera(id,valor));
            return b;
        }
    }
    
    
    public boolean SetConstanteCaracter(String id, char valor){
        
        if(a.size()==0){
            a.add(new ConstanteCaracter(id,valor));
            return true;
        }else{
            boolean b=true;
            for(int i=0;i<a.size();i++){
                if(a.get(i).id.compareTo(id)==0){
                    b=false;
                    Error="El id \" "+id+" \" ya esta asociado a otro elemento";
                    //System.out.println("tabla "+ Error);
                }
            }
            if(b)
              a.add(new ConstanteCaracter(id,valor));
            return b;
        }
    }
    
   public boolean SetArregloEntero(String id, int[] valor){
        
        if(a.size()==0){
            ArregloEntero aux=new ArregloEntero(id,valor.length);
            aux.set(valor);
            a.add(aux);
            return true;
        }else{
            boolean b=true;
            for(int i=0;i<a.size();i++){
                if(a.get(i).id.compareTo(id)==0){
                    b=false;
                    Error="El id \" "+id+" \" ya esta asociado a otro elemento";
                    //System.out.println("tabla "+ Error);
                }
            }
            if(b){
                ArregloEntero aux=new ArregloEntero(id,valor.length);
                aux.set(valor);
                a.add(aux);
            }
            return b;
        }
    }
    
    
    public boolean SetArregloCaracter(String id, char[] valor){
        
        if(a.size()==0){
            ArregloCaracter aux=new ArregloCaracter(id,valor.length);
            aux.set(valor);
            a.add(aux);
            return true;
        }else{
            boolean b=true;
            for(int i=0;i<a.size();i++){
                if(a.get(i).id.compareTo(id)==0){
                    b=false;
                    Error="El id \" "+id+" \" ya esta asociado a otro elemento";
                    //System.out.println("tabla "+ Error);
                }
            }
            if(b){
                ArregloCaracter aux=new ArregloCaracter(id,valor.length);
                aux.set(valor);
                a.add(aux);
            }
            return b;
        }
    }
    
    public boolean setValorChar(String id, int indice, char valor, boolean ejecutar){
        boolean b=false,b2=false;
        ArregloCaracter auz;
        for(int i=0;i<a.size();i++){
            if(a.get(i).id.compareTo(id)==0){
                if(a.get(i).tipo==TablaVariablesConstantes.almacenaarray){
                    if(a.get(i).tipovalor==TablaVariablesConstantes.tipocaracter){
                        auz=(ArregloCaracter)a.get(i);
                        if(ejecutar){
                            b=auz.set(valor,indice);
                            auz.cambio=true;
                            auz.indiceCambio=indice;
                        }else{
                            b=true;
                        }
                        if(!b)
                            Error="El indice de \" "+id+" \" excede sus limites";
                    }
                    else{
                        Error="El arreglo \" "+id+" \" no es de tipo caracter";
                    }
                }else{
                    Error="\" "+id+" \" no es un arreglo";
                    if(a.get(i).tipo==TablaVariablesConstantes.almacenavari)
                        Error+=" es una variable";
                    else
                        Error+=" es una constante";
                }
                b2=true;
                break;
            }
        }
        if(b2==false)
            Error="No existe el arreglo \" "+id+" \"";
        return b;
    }
    
    public boolean setValorInt(String id, int indice, int valor, boolean ejecutar){
        boolean b=false,b2=false;
        ArregloEntero auz;
        for(int i=0;i<a.size();i++){
            if(a.get(i).id.compareTo(id)==0){
                if(a.get(i).tipo==TablaVariablesConstantes.almacenaarray){
                    if(a.get(i).tipovalor==TablaVariablesConstantes.tipoentero){
                        auz=(ArregloEntero)a.get(i);
                        if(ejecutar){
                            b=auz.set(valor,indice);
                            auz.cambio=true;
                            auz.indiceCambio=indice;
                        }else{
                            b=true;
                        }
                        if(!b)
                            Error="El indice de \" "+id+" \" excede sus limites";
                    }
                    else{
                        Error="El arreglo \" "+id+" \" no es de tipo entero";
                    }
                }else{
                    Error="\" "+id+" \" no es un arreglo";
                    if(a.get(i).tipo==TablaVariablesConstantes.almacenavari)
                        Error+=" es una variable";
                    else
                        Error+=" es una constante";
                }
                b2=true;
                break;
            }
        }
        if(b2==false)
            Error="No existe el arreglo \" "+id+" \"";
        return b;
    }
    
    public boolean setValorChar(String id, char valor, boolean ejecutar){
        boolean b=false,b2=false;
        
        VariableCaracter auz;
        
        for(int i=0;i<a.size();i++){
            if(a.get(i).id.compareTo(id)==0){
                if(a.get(i).tipo==TablaVariablesConstantes.almacenavari){
                    if(a.get(i).tipovalor==TablaVariablesConstantes.tipocaracter){
                        auz=(VariableCaracter)a.get(i);
                        if(ejecutar){
                            auz.set(valor);
                            auz.cambio=true;
                        }
                        b=true;
                    }
                    else{
                        Error="La variable \" "+id+" \" no es de tipo caracter";
                    }
                }else{
                    Error="\" "+id+" \" no es una variable";
                    if(a.get(i).tipo==TablaVariablesConstantes.almacenaconst)
                        Error+=" es una constante";
                    else
                        Error+=" es un arreglo";
                }
                b2=true;
                break;
            }
        }
        
        if(b2==false && ejecutar){
            VariableCaracter aaa=new VariableCaracter(id,valor);
            aaa.cambio=true;
            a.add(aaa);
        }
        return b;
    }
    
    public boolean setValorInt(String id, int valor, boolean ejecutar){
        boolean b=false,b2=false;
        
        VariableEntera auz;
        
        for(int i=0;i<a.size();i++){
            if(a.get(i).id.compareTo(id)==0){
                if(a.get(i).tipo==TablaVariablesConstantes.almacenavari){
                    if(a.get(i).tipovalor==TablaVariablesConstantes.tipoentero){
                        auz=(VariableEntera)a.get(i);
                        if(ejecutar){
                            auz.set(valor);
                            auz.cambio=true;
                        }
                        b=true;
                    }
                    else{
                        Error="La variable \" "+id+" \" no es de tipo entero";
                    }
                }else{
                    Error="\" "+id+" \" no es una Variable";
                    if(a.get(i).tipo==TablaVariablesConstantes.almacenaconst)
                        Error+=" es una constante";
                    else
                        Error+=" es un arreglo";
                }
                b2=true;
                break;
            }
        }
        
        if(b2==false && ejecutar){
            VariableEntera aaa=new VariableEntera(id,valor);
            aaa.cambio=true;
            a.add(aaa);
        }
        
        return b;
        
    }
    
    public boolean getValorInt(String id){
        boolean b=false,b2=false;
        VariableEntera auz;
        ConstanteEntera auz2;
        this.valorObtenido=false;
                
        for(int i=0;i<a.size();i++){
            if(a.get(i).id.compareTo(id)==0){
                switch(a.get(i).tipo){
                    case TablaVariablesConstantes.almacenaconst:
                        if(a.get(i).tipovalor==TablaVariablesConstantes.tipoentero){
                            b=true;
                            auz2=(ConstanteEntera) a.get(i);
                            this.ss=auz2.get();
                        }else{
                            Error="la constante \" "+id+" \" no es entera";
                        }
                        break;
                    case TablaVariablesConstantes.almacenavari:
                        if(a.get(i).tipovalor==TablaVariablesConstantes.tipoentero){
                            b=true;
                            auz=(VariableEntera) a.get(i);
                            this.ss=auz.get();
                        }else{
                            Error="la variable \" "+id+" \" no es entera";
                        }
                        break;
                    default:
                            Error="\" "+id+" \" no es una variable o constante";
                }
                b2=true;
                break;
            }
        }
        
        if(b2==false){
            Error="No existe una variable o contante con el id \" "+id+" \"";
        }
        
        return b;
    }
    
    
    public boolean getValoChar(String id){
        boolean b=false,b2=false;
        VariableCaracter auz;
        ConstanteCaracter auz2;
        this.valorObtenido=false;
                
        for(int i=0;i<a.size();i++){
            if(a.get(i).id.compareTo(id)==0){
                switch(a.get(i).tipo){
                    case TablaVariablesConstantes.almacenaconst:
                        if(a.get(i).tipovalor==TablaVariablesConstantes.tipocaracter){
                            b=true;
                            auz2=(ConstanteCaracter) a.get(i);
                            this.ss2=auz2.get();
                        }else{
                            Error="la constante \" "+id+" \" no es entera";
                        }
                        break;
                    case TablaVariablesConstantes.almacenavari:
                        if(a.get(i).tipovalor==TablaVariablesConstantes.tipocaracter){
                            b=true;
                            auz=(VariableCaracter) a.get(i);
                            this.ss2=auz.get();
                        }else{
                            Error="la variable \" "+id+" \" no es entera";
                        }
                        break;
                    default:
                            Error="\" "+id+" \" no es una variable o constante";
                }
                b2=true;
                break;
            }
        }
        
        if(b2==false){
            Error="No existe una variable o contante con el id \" "+id+" \"";
        }
        
        return b;
    }
    
    public boolean getValoChar(String id, int indice){
        boolean b=false,b2=false;
        ArregloCaracter auz;
        this.valorObtenido=false;
                
        for(int i=0;i<a.size();i++){
            if(a.get(i).id.compareTo(id)==0){
                switch(a.get(i).tipo){
                    case TablaVariablesConstantes.almacenaarray:
                        if(a.get(i).tipovalor==TablaVariablesConstantes.tipocaracter){
                            auz=(ArregloCaracter) a.get(i);
                            this.ss2=auz.get(indice);
                            if(ss2!=2555){
                                b=true;
                            }else{
                                Error="El inidice de \" "+id+" \" esta fuera de los limites del arreglo ["+indice+"]";
                            }
                        }else{
                            Error="El arreglo \" "+id+" \" no es tipo caracter";
                        }
                        break;
                    default:
                            Error="\" "+id+" \" no es un arreglo";
                }
                b2=true;
                break;
            }
        }
        
        if(b2==false){
            Error="No existe un arreglo con el id \" "+id+" \"";
        }
        
        return b;
    }
    
    public boolean getValoInt(String id, int indice){
        boolean b=false,b2=false;
        ArregloEntero auz;
        this.valorObtenido=false;
                
        for(int i=0;i<a.size();i++){
            if(a.get(i).id.compareTo(id)==0){
                switch(a.get(i).tipo){
                    case TablaVariablesConstantes.almacenaarray:
                        if(a.get(i).tipovalor==TablaVariablesConstantes.tipoentero){
                            auz=(ArregloEntero) a.get(i);
                            this.ss=auz.get(indice);
                            if(ss!=-1){
                                b=true;
                            }else{
                                Error="El inidice de \" "+id+" \" esta fuera de los limites del arreglo ["+indice+"]";
                            }
                        }else{
                            Error="El arreglo \" "+id+" \" no es tipo entero";
                        }
                        break;
                    default:
                            Error="\" "+id+" \" no es un arreglo";
                }
                b2=true;
                break;
            }
        }
        
        if(b2==false){
            Error="No existe un arreglo con el id \" "+id+" \"";
        }
        
        return b;
    }
    
    public boolean getlargo(String id){
        boolean b=false,b2=false;
        ArregloCaracter auz;
        ArregloEntero auz2;
        this.valorObtenido=false;
                
        for(int i=0;i<a.size();i++){
            if(a.get(i).id.compareTo(id)==0){
                switch(a.get(i).tipovalor){
                    case TablaVariablesConstantes.tipoentero:
                        if(a.get(i).tipo==TablaVariablesConstantes.almacenaarray){
                            b=true;
                            auz2=(ArregloEntero) a.get(i);
                            this.ss=auz2.size();
                        }else{
                            Error="\" "+id+" \" no es un arreglo";
                        }
                        break;
                    case TablaVariablesConstantes.tipocaracter:
                        if(a.get(i).tipo==TablaVariablesConstantes.almacenaarray){
                            b=true;
                            auz=(ArregloCaracter) a.get(i);
                            this.ss=auz.size();
                        }else{
                            Error="\" "+id+" \" no es un arreglo";
                        }
                        break;
                    default:Error="";
                }
                b2=true;
                break;
            }
        }
        
        if(b2==false){
            Error="No existe un arreglo el id \" "+id+" \"";
        }
        
        System.out.println("comprobado "+id);
        
        return b;
    }
    
    public void ImprimirTablaVariables(){
        System.out.println("Esta es la tabla de valores");
        System.out.println("Constantes:");
        for(VariableConstanteArreglo elemento: a){
            if(elemento.tipo==TablaVariablesConstantes.almacenaconst){
                elemento.ImprimirDatos();
            }
        }
        System.out.println("Arreglos:");
        for(VariableConstanteArreglo elemento: a){
            if(elemento.tipo==TablaVariablesConstantes.almacenaarray){
                elemento.ImprimirDatos();
            }
        }
        
        System.out.println("Variables:");
        for(VariableConstanteArreglo elemento: a){
            if(elemento.tipo==TablaVariablesConstantes.almacenavari){
                elemento.ImprimirDatos();
            }
        }
    }
    
    public int ComprobarTipo(ArrayList<Token> auxiliar){
        if(auxiliar.size()>0){
            int tipoaa=auxiliar.get(0).tipo;
            if(tipoaa==AnalizadorSintactico.entero){
                
                s=new int[auxiliar.size()];
                for(int i=0;i<auxiliar.size();i++){
                    if(auxiliar.get(i).tipo!=AnalizadorSintactico.entero)
                        tipoaa=-1;
                    else
                        s[i]=Integer.parseInt(auxiliar.get(i).valor);
                }
                
            }else if(tipoaa==AnalizadorSintactico.caracter){
                
                s2=new char[auxiliar.size()];
                
                for(int i=0;i<auxiliar.size();i++){
                    if(auxiliar.get(i).tipo!=AnalizadorSintactico.caracter)
                        tipoaa=-1;
                    else
                        s2[i]=auxiliar.get(i).valor.charAt(1);
                }
                
            }
            if(tipoaa==-1)
                Error="los tipos no concuerdan";
            return tipoaa;
        }
        return -1;
    }
    
    
    public int ComprobarTipo(String id){
        int tipo=-1;
        for(VariableConstanteArreglo elemento: a){
            if(elemento.id.compareTo(id)==0)
                if(elemento.tipovalor==TablaVariablesConstantes.tipoentero)
                    tipo=AnalizadorSintactico.entero;                    
                else
                    tipo=AnalizadorSintactico.caracter;  
        }
        if(tipo==-1){
            Error="\" "+id+" \" no es variable o arreglo o constante";
            System.out.println(Error);
        }
        return tipo;
    }
    
}
