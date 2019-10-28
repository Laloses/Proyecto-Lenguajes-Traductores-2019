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
public class ArregloCaracter extends VariableConstanteArreglo{
    private char[] valor;
    
    public ArregloCaracter(String id,int valor) {
        super(id,TablaVariablesConstantes.tipocaracter,TablaVariablesConstantes.almacenaarray);
        this.valor=new char[valor];
    }
    
    public char get(int i){
        if(i<valor.length && i>=0)
            return valor[i];
        else
            return 2555;
    }
    
    public int size(){
        return valor.length;
    }
    
    public boolean set(char v, int i){
        if(i>=0 && i<valor.length){
            valor[i]=v;
            return true;
        }else
            return false;
    }
    
    public void set(char[] arreglo){
        valor=arreglo;
    }
    
    public void ImprimirDatos(){
        System.out.print("Arreglo entero -"+id+"- valor={");
        for(int i=0;i<valor.length;i++){
            System.out.print(valor[i]+",");
        }
        System.out.println("}");
    }   
    
}
