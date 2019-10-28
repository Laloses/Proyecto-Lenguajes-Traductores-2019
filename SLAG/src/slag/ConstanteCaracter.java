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
public class ConstanteCaracter extends VariableConstanteArreglo{
    private char valor;
    
    public ConstanteCaracter(String id,char valor) {
        super(id,TablaVariablesConstantes.tipocaracter,TablaVariablesConstantes.almacenaconst);
        this.valor=valor;
    }
    
    public char get(){
        return valor;
    }
    
    public void ImprimirDatos(){        
        System.out.println("Constante caracter -"+id+"- valor="+valor);
    }
    
}
