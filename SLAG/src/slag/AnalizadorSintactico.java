/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slag;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

/**
 *
 * @author STEWART
 */
public class AnalizadorSintactico {
    
    boolean banderaentrada;
    String entrada;
    SLAG_VISTA terminal;
    Token preanalisis,preanalisisprev;
    AnalizadorLexico analizadorl;
    String Error="";
    final static int suma=0;
    final static int resta=1;
    final static int por=2;
    final static int entre=3;
    final static int menoroigual=4;
    final static int menor=5;
    final static int mayoroigual=6;
    final static int mayor=7;
    final static int igual=8;
    final static int asignacion=9;
    final static int diferente=10;
    final static int caracter=11;
    final static int coma=12;
    final static int llavesA=13;
    final static int llavesC=14;
    final static int parenteA=15;
    final static int parenteC=16;
    final static int pyc=17;
    final static int corcheteA=18;
    final static int corcheteC=19;
    final static int entero=20;
    final static int id=21;
    final static int punto=22;
    final static int programa=23;
    final static int constantes=24;
    final static int arreglos=25;
    final static int inicio=26;
    final static int fin=27;
    final static int length=28;
    final static int para=29;
    final static int hasta=30;
    final static int paso=31;
    final static int hacer=32;
    final static int si=33;
    final static int entonces=34;
    final static int sino=35;
    final static int escribe=36;
    final static int lee=37;
    final static int mod=38;
    final static int finarchivo=39;
    
    int espera1=-1;
    int espera2=-1;
    int espera3=-1;
    int espera4=-1;
    int espera5=-1;
    int espera6=-1;
    int espera7=-1;
    int espera8=-1;
    int espera9=-1;
    int espera10=-1;
    int espera11=-1;
    
    boolean banderaarreglo=false;
    
    int valorrr;
    boolean tipooo;
    
    String idd;
    
    ArrayList<Token> auxiliar=new ArrayList<Token>();
    
    TablaVariablesConstantes tabla=new TablaVariablesConstantes();

    
    public AnalizadorSintactico(AnalizadorLexico a, SLAG_VISTA termi){
        analizadorl=a;
        preanalisis=null;
        this.terminal=termi;
    }
    
    
    public Boolean ComprobarArchivo()throws FileNotFoundException, IOException, BadLocationException{
        Boolean b = analizadorl.LeerArchivoAnalizar();
        if(b){
            analizadorl.ResetListaToken();
            preanalisis=analizadorl.GetToken();
            InicialisarError();
            terminal.ActualizarTerminal("SLAG:");
            System.out.println("analisis sintactico iniciado");
            tabla.a.clear();
            b=S();
            terminal.PintarTabladatos(tabla,0);
            System.out.println("analisis sintactico terminado");
            tabla.ImprimirTablaVariables();
        }else{
           Error=analizadorl.getErroranalisis();
        }
        return b;
    }
    
    public String getError(){
        return Error;
    }
    
    public void InicialisarError(){
        espera1=-1;
        espera2=-1;
        espera3=-1;
        espera4=-1;
        espera5=-1;
        espera6=-1;
        espera7=-1;
        espera8=-1;
        espera9=-1;
        espera10=-1;
        espera11=-1;
    }
    
    public boolean Emparejar(int i){
        if(preanalisis!=null && i==preanalisis.tipo){
            preanalisisprev=preanalisis;
            preanalisis=analizadorl.GetToken();
            return true;
        }else{
            espera1=i;
            return false;
        }
    }
    
    public boolean S() throws BadLocationException{
    switch(preanalisis.tipo){
    case programa:if(Emparejar(programa) && Emparejar(id) && DC() && DA() && Emparejar(inicio) && INS(true) && Emparejar(fin) && Emparejar(finarchivo)){
                        GenerarError();
                        return true;
                    }   else{
                            
                        GenerarError();
                        return false;
                    }
            
    default:    espera1=programa;
                GenerarError();
                return false;
    }
    }
   
    public boolean DC(){
        switch(preanalisis.tipo){
            case constantes:
                            if(Emparejar(constantes) && Emparejar(id)){
                                String ddd=preanalisisprev.valor;
                                
                                if(Emparejar(asignacion) && VAL()){
                                    if(preanalisisprev.tipo==entero){
                                        if(tabla.SetConstanteEntera(ddd,Integer.parseInt(preanalisisprev.valor))){
                                            return(CONS());
                                        }else{
                                            espera11=preanalisisprev.linea;
                                            return false;
                                        }
                                            
                                    }else if(preanalisisprev.tipo==caracter){
                                        if(tabla.SetConstanteCaracter(ddd,preanalisisprev.valor.charAt(1))){
                                            return(CONS());
                                        }else{
                                            espera11=preanalisisprev.linea;
                                            return false;
                                        }
                                    }
                                }else{
                                    return false;
                                }
                            }else{
                                return false;
                            }
            
            case arreglos: 
            case inicio:    //epsilon
                            return true;
            
            default:        espera1=constantes;
                            espera2=arreglos;
                            espera3=inicio;
                            return false;
        }
    }
    
    public boolean CONS(){
        switch(preanalisis.tipo){
            case id:        if(Emparejar(id)){
                                String ddd=preanalisisprev.valor;
                                
                                if(Emparejar(asignacion) && VAL()){
                                    if(preanalisisprev.tipo==entero){
                                        if(tabla.SetConstanteEntera(ddd,Integer.parseInt(preanalisisprev.valor))){
                                            return(CONS());
                                        }else{
                                            espera11=preanalisisprev.linea;
                                            return false;
                                        }
                                            
                                    }else if(preanalisisprev.tipo==caracter){
                                        if(tabla.SetConstanteCaracter(ddd,preanalisisprev.valor.charAt(1))){
                                            return(CONS());
                                        }else{
                                            espera11=preanalisisprev.linea;
                                            return false;
                                        }
                                    }
                                }else{
                                    return false;
                                }
                            }else{
                                return false;
                            }
            
            case inicio:
            case arreglos:    //epsilon
                            return true;
            
            default:        espera1=id;
                            espera2=arreglos;
                            espera3=inicio;
                            return false;
        }
    }
    
    public boolean DA() throws BadLocationException{
        switch(preanalisis.tipo){
            case arreglos:  if(Emparejar(arreglos) && Emparejar(id)){
                                String dddd=preanalisisprev.valor;
                                auxiliar.clear();
                                this.banderaarreglo=true;
                                
                                if(Emparejar(asignacion) && Emparejar(llavesA) && ARVAL() && Emparejar(llavesC)){
                                    int auxtipo=tabla.ComprobarTipo(auxiliar);
                                    System.out.println(" tipo arreglo "+dddd+" "+auxtipo);
                                    this.banderaarreglo=false;
                                    switch(auxtipo){
                                        case entero:
                                            if(tabla.SetArregloEntero(dddd, tabla.s)){
                                                System.out.println("arreglo "+dddd+" creado");
                                                
                                                return ARR();
                                                
                                            }else{
                                                System.out.println("arreglo "+dddd+" NO creado");
                                                espera11=preanalisisprev.linea;
                                                return false;
                                            }
                                            
                                        case caracter:
                                            if(tabla.SetArregloCaracter(dddd, tabla.s2)){
                                                System.out.println("arreglo "+dddd+" creado");
                                                return ARR();
                                            }else{
                                                System.out.println("arreglo "+dddd+" NO creado");
                                                espera11=preanalisisprev.linea;
                                                return false;
                                            }
                                            
                                        default:
                                            espera11=preanalisisprev.linea;
                                            return false;
                                    }
                                    
                                    //return ARR();
                                }else{
                                    return false;
                                }
                            }else{
                                return false;
                            }
            
            case inicio:    //epsilon
                            //ñlksjdflñkasjdflñkjasdlñkfjañslkdjflñaksjdflñkajsdñflkjasdlñkfjlaskdjf
                            terminal.PintarTabladatos(tabla,preanalisis.linea);
                            return true;
            
            default:        espera1=arreglos;
                            espera2=inicio;
                            return false;
        }
    }
    
    public boolean ARR() throws BadLocationException{
        switch(preanalisis.tipo){
            case id:        
                            if(Emparejar(id)){
                                String dddd=preanalisisprev.valor;
                                auxiliar.clear();
                                this.banderaarreglo=true;
                                
                                if(Emparejar(asignacion) && Emparejar(llavesA) && ARVAL() && Emparejar(llavesC)){
                                    int auxtipo=tabla.ComprobarTipo(auxiliar);
                                    System.out.println(" tipo arreglo "+dddd+" "+auxtipo);
                                    this.banderaarreglo=false;
                                    switch(auxtipo){
                                        case entero:
                                            if(tabla.SetArregloEntero(dddd, tabla.s)){
                                                System.out.println("arreglo "+dddd+" creado");
                                                return ARR();
                                            }else{
                                                System.out.println("arreglo "+dddd+" NO creado");
                                                espera11=preanalisisprev.linea;
                                                return false;
                                            }
                                            
                                        case caracter:
                                            if(tabla.SetArregloCaracter(dddd, tabla.s2)){
                                                System.out.println("arreglo "+dddd+" creado");
                                                return ARR();
                                            }else{
                                                System.out.println("arreglo "+dddd+" NO creado");
                                                espera11=preanalisisprev.linea;
                                                return false;
                                            }
                                            
                                        default:
                                            espera11=preanalisisprev.linea;
                                            return false;
                                    }
                                    
                                    //return ARR();
                                }else{
                                    return false;
                                }
                            }else{
                                return false;
                            }
            
            case inicio:    //epsilon
                            //ñlksjdflñkasjdflñkjasdlñkfjañslkdjflñaksjdflñkajsdñflkjasdlñkfjlaskdjf
                            terminal.PintarTabladatos(tabla,preanalisis.linea);
                            return true;
            
            default:        espera1=id;
                            espera2=inicio;
                            return false;
        }
    }
    
    
    public boolean ARVAL(){
        switch(preanalisis.tipo){
            case entero:
            case caracter:  return VAL() && ARVAL2();
            
            default:        espera1=entero;
                            espera2=caracter;
                            return false;
        }
    }
    
    public boolean ARVAL2(){
        switch(preanalisis.tipo){
            case coma:      return Emparejar(coma) && VAL() && ARVAL2();
            
            case llavesC:   //epsilon
                            return true;
                            
            default:        espera1=coma;
                            espera2=llavesC;
                            return false;
        }
    }
    
    public boolean VAL(){
        switch(preanalisis.tipo){
            case caracter:  if(Emparejar(caracter)){
                                if(banderaarreglo)
                                    auxiliar.add(preanalisisprev);
                                else{
                                    valorrr=preanalisisprev.valor.charAt(1);
                                    tipooo=false;
                                }
                                return true;
                            }else{
                                return false;
                            }
            
            case entero:    if(Emparejar(entero)){
                                if(banderaarreglo)
                                    auxiliar.add(preanalisisprev);
                                else{
                                    valorrr=Integer.parseInt(preanalisisprev.valor);
                                    tipooo=true;
                                }
                                return true;
                            }else{
                                return false;
                            }
                            
            default:        espera1=caracter;
                            espera2=entero;
                            return false;
        }
    }
    
    public boolean INS(boolean ejecutar) throws BadLocationException{
        switch(preanalisis.tipo){
            case id:        if(EXP(ejecutar)){
                                //ñlksjdflñkasjdflñkjasdlñkfjañslkdjflñaksjdflñkajsdñflkjasdlñkfjlaskdjf
                                if(ejecutar)
                                    terminal.PintarTabladatos(tabla,preanalisisprev.linea);
                                return INS(ejecutar);
                            }else{
                                return false;
                            }
            
            case si:        if(SI(ejecutar)){
                                //ñlksjdflñkasjdflñkjasdlñkfjañslkdjflñaksjdflñkajsdñflkjasdlñkfjlaskdjf
                                if(ejecutar)
                                terminal.PintarTabladatos(tabla,preanalisisprev.linea);
                                return INS(ejecutar);
                            }else{
                                return false;
                            }
            
            case para:      if(PARA(ejecutar)){
                                //ñlksjdflñkasjdflñkjasdlñkfjañslkdjflñaksjdflñkajsdñflkjasdlñkfjlaskdjf
                                if(ejecutar)
                                    terminal.PintarTabladatos(tabla,preanalisisprev.linea);
                                return INS(ejecutar);
                            }else{
                                return false;
                            }
            
            case lee:       if(LEE(ejecutar)){
                                //ñlksjdflñkasjdflñkjasdlñkfjañslkdjflñaksjdflñkajsdñflkjasdlñkfjlaskdjf
                                if(ejecutar)
                                    terminal.PintarTabladatos(tabla,preanalisisprev.linea);
                                return INS(ejecutar);
                            }else{
                                return false;
                            }
            
            case escribe:   if(ESCRIBE(ejecutar)){
                                //ñlksjdflñkasjdflñkjasdlñkfjañslkdjflñaksjdflñkajsdñflkjasdlñkfjlaskdjf
                                if(ejecutar)
                                    terminal.PintarTabladatos(tabla,preanalisisprev.linea);
                                return INS(ejecutar);
                            }else{
                                return false;
                            }
            
            case fin:       //epsilon
            /*case sino: El terminal 'sino' no va en esta parte*/      return true;
            
            default:        espera1=id;
                            espera2=si;
                            espera3=para;
                            espera4=lee;
                            espera5=escribe;
                            espera6=fin;
                            //espera7=sino;
                            return false;
        }
    }
    
    public boolean EXP(boolean ejecutar){
        Datos variable=new Datos();
        Datos expresion=new Datos();
        boolean b=false;
        switch(preanalisis.tipo){
            case id:        if(VARIABLE(variable) && Emparejar(asignacion) && EXPRESION(expresion) && Emparejar(pyc)){
                                System.out.println(variable.id+"--"+expresion.valor);
                                //System.out.println(expresion.valor+"--"+expresion.tipodato); FALLA DEL 
                                if(variable.existe){
                                    if(expresion.tipodato==variable.tipodato){
                                            if(variable.tipodato){
                                                b=(variable.hayi)? (tabla.setValorInt(variable.id,variable.indice,expresion.valor,ejecutar)) : (tabla.setValorInt(variable.id, expresion.valor,ejecutar)) ;
                                                if(!b)
                                                    espera11=preanalisisprev.linea;
                                                return b;   
                                            }else{
                                                b=(variable.hayi)? (tabla.setValorChar(variable.id,variable.indice,(char)expresion.valor,ejecutar)) : (tabla.setValorChar(variable.id, (char)expresion.valor,ejecutar)) ;
                                                if(!b)
                                                    espera11=preanalisisprev.linea;
                                                return b;  
                                            }
           
                                    }else{
                                        espera11=preanalisisprev.linea;
                                        tabla.Error="Los tipos de la expresion y la variable a asignar son diferentes";
                                        return false;
                                    }
                                }else{
                                    if(expresion.tipodato)
                                        tabla.setValorInt(variable.id,expresion.valor,ejecutar);
                                    else
                                        tabla.setValorChar(variable.id,(char) expresion.valor,ejecutar);
                                    return true;
                                }
                               
                            }else{
                                return false;
                            }
                        
            default:        espera1=id;
                            return false;
        }
    }
    
    
    public boolean VARIABLE(Datos datos){
        int tipovv;
        switch(preanalisis.tipo){
            case id:        datos.id=preanalisis.valor;
                            if(Emparejar(id) && INDI(datos)){
                                tipovv=tabla.ComprobarTipo(datos.id);
                                datos.tipodato = tipovv==entero;
                                datos.existe=(tipovv!=-1);
                                return true;
                            }
                            else
                                return false;
                        
            default:        espera1=id;
                            return false;
        }
    }
    
    public boolean INDI(Datos datos){
        Datos expresion=new Datos();
        switch(preanalisis.tipo){
            case corcheteA: datos.hayi=true;
                            if(Emparejar(corcheteA) && EXPRESION(expresion) && Emparejar(corcheteC)){
                                if(expresion.tipodato){
                                    datos.indice=expresion.valor;
                                    return true;
                                }else{
                                    espera11=preanalisisprev.linea;
                                    tabla.Error="EL indice de \" "+id+" \" es de tipo character";
                                    return false;
                                }
                            }else{
                                return false;
                            }
            
            //case parenteC: NO PERTENECE
            case asignacion:return true;
                        
            default:        espera1=corcheteA;
                            espera2=asignacion;
                            return false;
        }
    }
    
    public boolean EXPRESION(Datos resultado){
        Datos v1=new Datos();
        Datos v2=new Datos();
        
        switch(preanalisis.tipo){
            case entero:
            case caracter:
            case id:         if(VARCON(v1) && EXPRESION2(v2)){
                                if(v2.existeop2){
                                    if(v1.tipodato && v2.tipodato){
                                        resultado.tipodato=true;
                                        switch(v2.op){
                                            case suma:
                                                resultado.valor=v1.valor+v2.valor;
                                                if(resultado.valor>=0)
                                                    return true;
                                                else{
                                                    espera11=preanalisisprev.linea;
                                                    tabla.Error="El resultado es negativo";
                                                    return false;
                                                }
                                            case resta:
                                                resultado.valor=v1.valor-v2.valor;
                                                if(resultado.valor>=0)
                                                    return true;
                                                else{
                                                    espera11=preanalisisprev.linea;
                                                    tabla.Error="El resultado es negativo";
                                                    return false;
                                                }
                                            case por:
                                                resultado.valor=v1.valor*v2.valor;
                                                if(resultado.valor>=0)
                                                    return true;
                                                else{
                                                    espera11=preanalisisprev.linea;
                                                    tabla.Error="El resultado es negativo";
                                                    return false;
                                                }
                                            case entre:
                                                resultado.valor=v1.valor/v2.valor;
                                                if(resultado.valor>=0)
                                                    return true;
                                                else{
                                                    espera11=preanalisisprev.linea;
                                                    tabla.Error="El resultado es negativo";
                                                    return false;
                                                }
                                            case mod:
                                                resultado.valor=v1.valor%v2.valor;
                                                if(resultado.valor>=0)
                                                    return true;
                                                else{
                                                    espera11=preanalisisprev.linea;
                                                    tabla.Error="El resultado es negativo";
                                                    return false;
                                                }
                                            default:espera11=preanalisisprev.linea;
                                                    tabla.Error="Operando desconocido";
                                                    return false;
                                        }
                                    }else{
                                        espera11=preanalisisprev.linea;
                                        tabla.Error="No todos los operandos son de tipo entero";
                                        return false;
                                    }
                                }else{
                                    resultado.valor=v1.valor;
                                    resultado.tipodato=v1.tipodato;
                                }
                                return true;
                            }else{
                                return false;
                            }
                            
                        
            default:        espera1=entero;
                            espera2=caracter;
                            espera3=id;
                            return false;
        }
    }
    
    public boolean EXPRESION2(Datos v2){
        switch(preanalisis.tipo){
            case suma:
            case resta:
            case por: 
            case entre:
            case mod:       v2.existeop2=true;
                            return OP(v2) && VARCON(v2);
            
            case pyc:
            case corcheteC: v2.existeop2=false;
                            return true;
                        
            default:        espera1=suma;
                            espera2=resta;
                            espera3=por;
                            espera4=entre;
                            espera5=mod;
                            espera6=pyc;
                            espera7=corcheteC;
                            return false;
        }
    }
    
    public boolean VARCON(Datos varcon){
        int auxxx;
        boolean b=false;
        switch(preanalisis.tipo){
            case id:        varcon.id=preanalisis.valor;
                            if(Emparejar(id) && VC(varcon)){
                                auxxx=tabla.ComprobarTipo(varcon.id);
                                if(auxxx!=-1){
                                    //System.out.print("aslkdjfalskdjflajkdsf__________      "+varcon.id);
                                    //System.out.print(""+((varcon.hayi)? ("___indice__"+varcon.indice) :("") ));
                                    //System.out.println(""+((varcon.hayl)? ("___largo__") :("") ));
                                    varcon.tipodato=(auxxx==entero);
                                    if(varcon.tipodato){
                                        b=(varcon.hayi)? (tabla.getValoInt(varcon.id,varcon.indice)) : ((varcon.hayl)? (tabla.getlargo(varcon.id)) : (tabla.getValorInt(varcon.id)));
                                        varcon.valor=tabla.ss;
                                        varcon.tipodato=(varcon.hayl)? true:varcon.tipodato;
                                        if(!b)
                                            espera11=preanalisisprev.linea;
                                        return b;
                                    }else{
                                        b=(varcon.hayi)? (tabla.getValoChar(varcon.id, varcon.indice)) : ((varcon.hayl)? (tabla.getlargo(varcon.id)) : (tabla.getValoChar(varcon.id)));
                                        varcon.valor=tabla.ss2;
                                        varcon.tipodato=(varcon.hayl)? true:varcon.tipodato;
                                        if(!b)
                                            espera11=preanalisisprev.linea;
                                        return b;
                                    }
                                }else{
                                    
                                    espera11=preanalisisprev.linea;
                                    return false;
                                }
                                
                            }else{
                                return false;
                            }
            
            case entero:
            case caracter:  banderaarreglo=false;
                            if(VAL()){
                                varcon.valor=valorrr;
                                varcon.tipodato=tipooo;
                                return true;
                            }else{
                                return false;
                            }
                        
            default:        espera1=id;
                            espera2=entero;
                            espera3=caracter;
                            return false;
        }
    }
    
    public boolean VC(Datos var){
        Datos expresion=new Datos();
        switch(preanalisis.tipo){
            case corcheteA: var.hayl=false;
                            var.hayi=true;
                            if(Emparejar(corcheteA) && EXPRESION(expresion) && Emparejar(corcheteC)){
                                if(expresion.tipodato){
                                    var.indice=expresion.valor;
                                    return true;
                                }else{
                                    espera11=preanalisisprev.linea;
                                    tabla.Error="EL indice de \" "+id+" \" es de tipo character";
                                    return false;
                                }
                            }else{
                                return false;
                            }
            
            case punto:     var.hayl=true;
                            var.hayi=false;
                            return Emparejar(punto) && Emparejar(length);
            
            //case parenteC: NO PERTENECE, NO NECESARIO Xd
            case corcheteC:
            case igual:
            case diferente:
            case mayor:
            case mayoroigual:
            case menor:
            case menoroigual:               
            case suma:
            case resta:
            case por:
            case entre:
            case mod:
            case pyc:
            case hasta:
            case paso:      var.hayl=false;
                            var.hayi=false;
                            return true;
                        
            default:        espera1=corcheteA;
                            espera2=punto;
                            espera3=suma;
                            espera4=resta;
                            espera5=por;
                            espera6=entre;
                            espera7=mod;
                            espera8=pyc;
                            espera9=hasta;
                            espera10=paso;
                            return false;
        }
    }
    
    
    public boolean P(Datos ope){
        switch(preanalisis.tipo){
            case suma:      ope.op=suma;
                            return Emparejar(suma);
            
            case resta:     ope.op=resta;
                            return Emparejar(resta);
                        
            default:        espera1=suma;
                            espera2=resta;
                            return false;
        }
    }
    
    public boolean OP(Datos op){
        switch(preanalisis.tipo){
            case resta:
            case suma:      return P(op);
            
            case por:       op.op=por;
                            return Emparejar(por);
            
            case entre:     op.op=entre;
                            return Emparejar(entre);
            
            case mod:       op.op=mod;
                            return Emparejar(mod);
                        
            default:        espera1=suma;
                            espera2=resta;
                            espera3=por;
                            espera4=entre;
                            espera5=mod;
                            return false;
        }
    }
    
    public boolean SI(boolean ejecutar) throws BadLocationException{
        Datos v1=new Datos();
        Datos v2=new Datos();
        Datos v3=new Datos();
        switch(preanalisis.tipo){
            case si:        if(Emparejar(si) && Emparejar(parenteA) && CONDICION(v1,v2) && Emparejar(parenteC) && Emparejar(entonces) ){
                                if(v1.tipodato==v2.tipodato){
                                    switch(v2.op){
                                        case igual:
                                            if(v1.valor==v2.valor)
                                                return INS(true && ejecutar) && SINO(v3,false);
                                            else
                                                return INS(false) && SINO(v3,true && ejecutar);
                                        case diferente:
                                            if(v1.valor!=v2.valor)
                                                return INS(true && ejecutar) && SINO(v3,false);
                                            else
                                                return INS(false) && SINO(v3,true && ejecutar);
                                        case menor:
                                            if(v1.valor<v2.valor)
                                                return INS(true && ejecutar) && SINO(v3,false);
                                            else
                                                return INS(false) && SINO(v3,true && ejecutar);
                                        case menoroigual:
                                            if(v1.valor<=v2.valor)
                                                return INS(true && ejecutar) && SINO(v3,false);
                                            else
                                                return INS(false) && SINO(v3,true && ejecutar);
                                        case mayor:
                                            if(v1.valor>v2.valor)
                                                return INS(true && ejecutar) && SINO(v3,false);
                                            else
                                                return INS(false) && SINO(v3,true && ejecutar);
                                        case mayoroigual:
                                            if(v1.valor>=v2.valor)
                                                return INS(true && ejecutar) && SINO(v3,false);
                                            else
                                                return INS(false) && SINO(v3,true && ejecutar);
                                        default:
                                            espera11=preanalisisprev.linea;
                                            tabla.Error="condicion no aceptada";
                                            return false;
                                    }
                                }else{
                                    espera11=preanalisisprev.linea;
                                    tabla.Error="Los tipos de los argumentos de condicion deben de ser igual";
                                    return false;
                                }
                            }else{
                                return false;
                            }
                        
            default:        espera1=si;
                            return false;
        }
    }
    
    public boolean SINO(Datos sinov,boolean ejecutar) throws BadLocationException{
        switch(preanalisis.tipo){
            case sino:      sinov.existe=true;
                            return Emparejar(sino) && INS(ejecutar) && Emparejar(fin);
            
            case fin:       sinov.existe=false;
                            return Emparejar(fin);
                        
            default:        espera1=sino;
                            espera2=fin;
                            return false;
        }
    }
    
    public boolean CONDICION(Datos v1, Datos v2){
        switch(preanalisis.tipo){
            case id:
            case entero:
            case caracter:  return VARCON(v1) && CON(v2) && VARCON(v2);
                        
            default:        espera1=id;
                            espera2=entero;
                            espera3=caracter;
                            return false;
        }
    }
    
    public boolean CON(Datos opera){
        switch(preanalisis.tipo){
            case igual:     opera.op=igual;
                            return Emparejar(igual);
            
            case diferente: opera.op=diferente;
                            return Emparejar(diferente);
            
            case menoroigual:opera.op=menoroigual;
                            return Emparejar(menoroigual);
            
            case menor:     opera.op=menor;
                            return Emparejar(menor);
                        
            case mayoroigual:opera.op=mayoroigual;
                            return Emparejar(mayoroigual);
            
            case mayor:     opera.op=mayor;
                            return Emparejar(mayor);
            
            default:        espera1=igual;
                            espera2=diferente;
                            espera3=menoroigual;
                            espera4=menor;
                            espera5=mayoroigual;
                            espera6=mayor;
                            return false;
        }
    }
    
    public boolean PARA(boolean ejecutar) throws BadLocationException{
        Datos variable=new Datos();
        Datos lim1=new Datos();
        Datos lim2=new Datos();
        Datos salto=new Datos();
        int empieza;
        boolean b;
        Token aux,aux1;
        
        
        switch(preanalisis.tipo){
            case para:      if(Emparejar(para) && VARIABLE(variable) && Emparejar(asignacion) && INTEGER(lim1) && Emparejar(hasta) && INTEGER(lim2) && Emparejar(paso) && P(salto) && Entero(salto) && Emparejar(hacer)){
                                empieza=analizadorl.indice;
                                //asignacion del valor inicial
                                aux=preanalisis;
                                aux1=preanalisisprev;
                                                                    if(variable.existe){
                                                                        if(variable.tipodato){
                                                                                b=(variable.hayi)? (tabla.setValorInt(variable.id,variable.indice,lim1.valor,ejecutar)) : (tabla.setValorInt(variable.id, lim1.valor,ejecutar)) ;
                                                                                if(!b){
                                                                                    espera11=preanalisisprev.linea;
                                                                                    return false;
                                                                                }
                                                                        }else{
                                                                            espera11=preanalisisprev.linea;
                                                                            tabla.Error="El elemento con id \" "+variable.id+" \" usada en el para debe ser tipo entera";
                                                                            return false;
                                                                        }
                                                                    }else{
                                                                            tabla.setValorInt(variable.id,lim1.valor,ejecutar);

                                                                    }
                                
                                if(ejecutar){
                                    tabla.getValorInt(variable.id);
                                    //System.out.println("indice token "+preanalisis.imprimir());
                                    while(tabla.ss<=lim2.valor){
                                        System.out.println("vuelta "+tabla.ss+" saltos "+salto.valor);
                                        analizadorl.indice=empieza;
                                        preanalisis=aux;
                                        preanalisisprev=aux1;
                                        b=INS(ejecutar);
                                        if(!b)
                                            return false;
                                        b=(variable.hayi)? (tabla.getValoInt(variable.id,variable.indice)) : (tabla.getValorInt(variable.id)) ;
                                        b=(variable.hayi)? (tabla.setValorInt(variable.id,variable.indice,(tabla.ss+((salto.op==suma)? (salto.valor) : (-salto.valor))),ejecutar)) : (tabla.setValorInt(variable.id,(tabla.ss+((salto.op==suma)? (salto.valor) : (-salto.valor))),ejecutar)) ;
                                        if(!b){
                                            espera11=preanalisisprev.linea;
                                            return false;
                                        }
                                        b=(variable.hayi)? (tabla.getValoInt(variable.id,variable.indice)) : (tabla.getValorInt(variable.id)) ;
                                        
                                    }
                                    return Emparejar(fin);
                                }else{
                                    return INS(ejecutar) && Emparejar(fin);
                                }
                            }else{
                                return false;
                            }
            default:        
                            espera1=this.para;
                            return false;
        }
    }
    
    public boolean Entero(Datos enterov){
        switch(preanalisis.tipo){
            case entero: enterov.valor=Integer.parseInt(preanalisis.valor);
                         return Emparejar(entero);
            default:    
                espera1=entero;    
                return false;
                    
                         
        }
    }
    
    public boolean INTEGER(Datos varcon){
        Datos datos=new Datos();
        int auxxx;
        boolean b;
        switch(preanalisis.tipo){
            case id:        varcon.id=preanalisis.valor;
                            if(Emparejar(id) && VC(varcon)){
                                auxxx=tabla.ComprobarTipo(varcon.id);
                                if(auxxx!=-1){
                                    //System.out.print("aslkdjfalskdjflajkdsf__________      "+varcon.id);
                                    //System.out.print(""+((varcon.hayi)? ("___indice__"+varcon.indice) :("") ));
                                    //System.out.println(""+((varcon.hayl)? ("___largo__") :("") ));
                                    varcon.tipodato=(auxxx==entero);
                                    if(varcon.tipodato){
                                        b=(varcon.hayi)? (tabla.getValoInt(varcon.id,varcon.indice)) : ((varcon.hayl)? (tabla.getlargo(varcon.id)) : (tabla.getValorInt(varcon.id)));
                                        varcon.valor=tabla.ss;
                                        varcon.tipodato=(varcon.hayl)? true:varcon.tipodato;
                                        if(!b)
                                            espera11=preanalisisprev.linea;
                                        return b;
                                    }else{
                                        espera11=preanalisisprev.linea;
                                        tabla.Error="El id \" "+varcon.id+" \" no esta relacionado al tipo entero y se estera un tipo entero";
                                        return false;
                                    }
                                }else{
                                    
                                    espera11=preanalisisprev.linea;
                                    return false;
                                }
                                
                            }else{
                                return false;
                            }
            
            case entero:    varcon.valor=Integer.parseInt(preanalisis.valor);
                            varcon.tipodato=true;
                            return Emparejar(entero);
            
            default:        espera1=id;
                            espera2=entero;
                            return false;
        }
    }
    
    public boolean LEE(boolean ejecutar){
        Datos variable=new Datos();
        String aux="";
        int leidoi;
        boolean b;
        switch(preanalisis.tipo){
            case lee:       
                            if(Emparejar(lee) && Emparejar(parenteA) && VARIABLE(variable) && Emparejar(parenteC) && Emparejar(pyc)){
                                if(ejecutar){
                                    if(variable.existe){
                                        aux=JOptionPane.showInputDialog("Ingrese datos para la variable "+variable.id+((variable.tipodato)? (" de tipo entero") : (" de tipo caracter") ));
                                        System.out.println("valor leido +"+aux);
                                        if(variable.tipodato){
                                            leidoi=Integer.parseInt(aux);
                                            b=(variable.hayi)? (tabla.setValorInt(variable.id,variable.indice,leidoi,ejecutar)) : (tabla.setValorInt(variable.id, leidoi,ejecutar)) ;
                                            if(!b)
                                                espera11=preanalisisprev.linea;
                                            return b;
                                        }else{
                                            leidoi=aux.charAt(0);
                                            b=(variable.hayi)? (tabla.setValorChar(variable.id,variable.indice,(char)leidoi,ejecutar)) : (tabla.setValorChar(variable.id, (char)leidoi,ejecutar)) ;
                                            if(!b)
                                                espera11=preanalisisprev.linea;
                                            return b;
                                        }
                                    }else{
                                        espera11=preanalisisprev.linea;
                                      
                                        return false;
                                    }
                                }
                                return true;
                            }else{
                                return false;
                            }
            
            default:        espera1=lee;
                            return false;
        }
    }
    
    public boolean ESCRIBE(boolean ejecutar){
        String aux;
        Datos dato=new Datos();
        switch(preanalisis.tipo){
            case escribe:   if(Emparejar(escribe) && Emparejar(parenteA) && VARCON(dato) && Emparejar(parenteC) && Emparejar(pyc)){
                                if(ejecutar){
                                    aux=terminal.ObtenerTerminal();
                                    if(dato.tipodato){
                                        aux+=""+dato.valor;
                                    }else{
                                        aux+=""+(char)dato.valor;
                                    }
                                    terminal.ActualizarTerminal(aux);
                                }
                                System.out.println("aux");
                                return true;
                            }else{
                                return false;
                            }
            
            default:        espera1=escribe;
                            return false;
        }
    }
    
    public void GenerarError(){
        String aux=terminal.ObtenerTerminal();
        if(espera1!=-1){
            Error=preanalisis.linea+":ERROR SINTACTICO\n"+analizadorl.getLinea(preanalisis.linea)+"\n aparece \" "+preanalisis.valor+" \" y se espera "+TokenEsperado(this.espera1);
            if(espera2!=-1)
                Error+=" o "+TokenEsperado(this.espera2);
            if(espera3!=-1)
                Error+=" o "+TokenEsperado(this.espera3);
            if(espera4!=-1)
                Error+=" o "+TokenEsperado(this.espera4);
            if(espera5!=-1)
                Error+=" o "+TokenEsperado(this.espera5);
            if(espera6!=-1)
                Error+=" o "+TokenEsperado(this.espera6);
            if(espera7!=-1)
                Error+=" o "+TokenEsperado(this.espera7);
            if(espera8!=-1)
                Error+=" o "+TokenEsperado(this.espera8);
            if(espera9!=-1)
                Error+=" o "+TokenEsperado(this.espera9);
            if(espera10!=-1)
                Error+=" o "+TokenEsperado(this.espera10);
            
        }else{
            if(espera11!=-1){
                Error=espera11+":ERROR\n"+analizadorl.getLinea(espera11)+"\n"+tabla.Error;
                //Error=espera11+tabla.Error;
            }
            else
                Error="archivo valido";
        }
        
        aux+="\n"+Error;
        terminal.ActualizarTerminal(aux);
    }
    
    public String TokenEsperado(int tespe){
       switch(tespe){
           case suma: return "un mas \" + \"";
           case resta: return "un menos \" - \"";
           case por: return "un asterisco \" * \"";
           case entre: return "una diagonal \" / \"";
           case menoroigual: return "un menor o igual \" <= \"";
           case menor: return "un menor \" < \"";
           case mayoroigual: return "un mayor o igual \" >= \"";
           case mayor: return "un mayor \" > \"";
           case igual: return "un igual \" == \"";
           case asignacion: return "una asignacion \" = \"";
           case diferente: return "un diferente \" != \"";
           case caracter: return "un caracter \" '#letra#' \"";
           case coma: return "una coma \" , \"";
           case llavesA: return "una llave que abre \" { \"";
           case llavesC: return "una llave que cierra \" } \"";
           case parenteA: return "un parentesis que abre \" ( \"";
           case parenteC: return "un parentesis que cierra \" ) \"";
           case pyc: return "un punto y coma \" ; \"";
           case corcheteA: return "un corchete que abre \" [ \"";
           case corcheteC: return "un corchete que cierra \" ] \"";
           case entero: return "un \" entero \"";
           case id: return "un \" id \"";
           case punto: return "un punto \" . \"";
           case programa: return "la palabra reservada \"programa\"";
           case constantes: return "la palabra reservada \"constantes\"";
           case arreglos: return "la palabra reservada \"arreglos\"";
           case inicio: return "la palabra reservada \"inicio\"";
           case fin: return "la palabra reservada \"fin\"";
           case length: return "la palabra reservada \"length\"";
           case para: return "la palabra reservada \"para\"";
           case hasta: return "la palabra reservada \"hasta\"";
           case paso: return "la palabra reservada \"paso\"";
           case hacer: return "la palabra reservada \"hacer\"";
           case si: return "la palabra reservada \"si\"";
           case entonces: return "la palabra reservada \"entonces\"";
           case sino: return "la palabra reservada \"sino\"";
           case escribe: return "la palabra reservada \"escribe\"";
           case lee: return "la palabra reservada \"lee\"";
           case mod: return "la palabra reservada \"mod\"";
           case finarchivo: return "fin de archivo";
           default: return "nada";
       }
    }
    
    
    
}
