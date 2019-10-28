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
public class ConstanteEntera extends VariableConstanteArreglo{
    private int valor;
    
    public ConstanteEntera(String id,int valor) {
        super(id,TablaVariablesConstantes.tipoentero,TablaVariablesConstantes.almacenaconst);
        this.valor=valor;
    }
    
    public int get(){
        return valor;
    }
    public void ImprimirDatos(){
        System.out.println("Constante entera -"+id+"- valor="+valor);
    }
    
}