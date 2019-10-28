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
public class VariableCaracter extends VariableConstanteArreglo{
    private char valor='\"';
    
    public VariableCaracter(String id,char valor) {
        super(id,TablaVariablesConstantes.tipocaracter,TablaVariablesConstantes.almacenavari);
        this.valor=valor;
    }
    
    public char get(){
        return valor;
    }
    
    public void set(char v){
        valor=v;
    }
    
    public void ImprimirDatos(){
        System.out.println("Variable caracter -"+id+"- valor="+valor);
    }
}
