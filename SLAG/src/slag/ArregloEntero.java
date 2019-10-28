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
public class ArregloEntero extends VariableConstanteArreglo{
    private int[] valor;
    
    public ArregloEntero(String id,int valor) {
        super(id,TablaVariablesConstantes.tipoentero,TablaVariablesConstantes.almacenaarray);
        this.valor=new int[valor];
    }
    
    public int get(int i){
        if(i<valor.length && i>=0)
            return valor[i];
        else
            return -1;
    }
    
    public int size(){
        return valor.length;
    }
    
    public boolean set(int v, int i){
        if(i>=0 && i<valor.length){
            valor[i]=v;
            return true;
        }else
            return false;
    }
    
    public void set(int[] arreglo){
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
