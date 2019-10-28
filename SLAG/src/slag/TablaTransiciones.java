package slag;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author STEWART
 */
public class TablaTransiciones{
  int[][] transiciones;
  int[] transicionotro;
  int[][] estadosfinales;
  int estadoinicial;
  int estadoactual;
  int n,m,tipo;


  public TablaTransiciones(int n, int m){
    this.n=n;
    this.m=m;
    transicionotro=new int[n];
    transiciones=new int[n][m];
    estadosfinales=new int[n][2];
    ReiniciarTransiciones();
  }

  public void ReiniciarTransiciones(){
    for (int i=0;i<n;i++){
      for (int j=0;j<m;j++)
        transiciones[i][j]=-1;
      estadosfinales[i][0]=-1;
      transicionotro[i]=-1;
    }
    estadoinicial=estadoactual=0;
    tipo=0;
  }


  public int ComprobarTransicion(int i, int t){
    if(i>=0 && i<n && t>=0 && t<m)
      return transiciones[i][t];
    return -1;
  }

  public void incTipo(){
    tipo++;
  }

  public int getTipo(){
    return tipo;
  }

  public int ComprobarTransicionOtro(int i){
    if(i>=0 && i<n)
      return transicionotro[i];
    return -1;
  }

  public void AddTransicion(int i, int j, int t){
    if(i>=0 && i<n && t>=0 && t<m)
      transiciones[i][t]=j;
  }

  public void AddTransicionOtro(int i, int j){
    if(i>=0 && i<n){
      for(int t=0;t<m;t++){
        if(transiciones[i][t]==-1)
          transiciones[i][t]=j;
      }
      transicionotro[i]=j;
    }
  }

  public void SetTransiciones(int[][] array){
    transiciones=array;
  }

  public void SetInicial(int i){
    estadoinicial=i;
  }

  public void SetFinales(int[][] i){
    estadosfinales=i;
  }

  public void SetFinal(int i, int r){
    if (i>=0 && i<n && r>=0){
        estadosfinales[i][0]=r;
        estadosfinales[i][1]=tipo;
        tipo++;
    }
  }

  public int GetREtGrand(){
    int i=0;
    for(int j=0;j<n;j++)
      if(i<estadosfinales[i][0])
        i=estadosfinales[i][0];
    return i;
  }

  public int CheckFinal(int i){
    if (i>=0 && i<n)
      return estadosfinales[i][0];
    else
      return -1;
  }

  public int CheckTipo(int i){
    if (i>=0 && i<n && estadosfinales[i][0]>=0)
      return estadosfinales[i][1];
    else
      return -1;
  }

  public void SetEstado(int i) {
    if (i>=0 && i<n)
      estadoactual=i;
  }
  public int GetEstado(){
      return(estadoactual);
  }
  public int GetEstadoInicial(){
      return(estadoinicial);
  }

  public void imprimirtabla(){
    for (int i=0;i<n;i++){
      for (int j=0;j<m;j++){
        System.out.print(transiciones[i][j]+"\t");
      }
      System.out.println();
    }
  }

  public void imprimirOtro(){
    for (int i=0;i<n;i++)
        System.out.print(transicionotro[i]+"\t");
      System.out.println();

  }

}
