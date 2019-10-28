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
public class VariableEntera extends VariableConstanteArreglo{
    private int valor;
    
    public VariableEntera(String id,int valor) {
        super(id,TablaVariablesConstantes.tipoentero,TablaVariablesConstantes.almacenavari);
        this.valor=valor;
    }
    
    public int get(){
        return valor;
    }
    
    public void set(int v){
        valor=v;
    }
    
    public void ImprimirDatos(){
        System.out.println("Variable entera -"+id+"- valor="+valor);
    }
    
}
