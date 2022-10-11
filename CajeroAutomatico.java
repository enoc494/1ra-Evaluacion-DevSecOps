//Soy un comentario que será pusheado

import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  

public class CajeroAutomatico {
  static int saldo = 1000;
  static ArrayList<String> historicoDate = new ArrayList<String>();
  static ArrayList<Integer> historicoOldSaldo = new ArrayList<Integer>();
  static ArrayList<Integer> historicoNewSaldo = new ArrayList<Integer>();
  
  //Formateador de date
  static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
  static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

  //Validador entero
  public static boolean isNumeric(String strNum) {
    if (strNum == null) {
      return false;
    }
    try {
      int d = Integer.parseInt(strNum);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  //Rutina submenu
  public static int subMenu(){
    Scanner input = new Scanner(System.in);
    int validador = 0;
    while(validador == 0){
      System.out.println("Elija una opción del submenú:");
      System.out.println("1.- Menú principal");
      System.out.println("2.- Salir");
      String var = input.next();
      if( isNumeric(var) ){
        switch(var){
          case "1":
            return 1;
          case "2":
            return 2;
          default:
            System.out.println("Por favor, intente de nuevo");
        }
      }else{
        System.out.println("Tipo de dato invalido");
      }
    }

    return 0;
  }

  ///Main principal///
  public static void main(String [] args){
    Scanner input = new Scanner(System.in);
    int validado = 0;
    int patience = 3;
    while(validado == 0){
      System.out.println("Bienvenido, favor de identificarte con el PIN:");
      String var = input.next();
      if( isNumeric(var) ){
        if( Integer.parseInt(var) == 1235){
          System.out.println("Usuario reconocido, \n Bienvenido José Ángel Avelar");
          validado = 1;
        }else{
          System.out.println("Pin incorrecto");
          patience -=1;
        }
      }else{
        System.out.println("Tipo de dato invalido");
        patience -=1;
      }
      if( patience==0){
        System.out.println("Ha excedido el número de intentos permitidos en una ejecución");
        System.out.println("Hasta luego");
        validado = 2;
      }
    }

    if(validado == 1){
      //Menús
      while(validado == 1){

        System.out.println("Elija una opción del menú:");
        System.out.println("1.- Consultar saldo");
        System.out.println("2.- Retirar saldo");
        System.out.println("3.- Historico de movimientos saldo");
        String var = input.next();
        if( isNumeric(var) ){
          switch(var){
            case "1":
              System.out.println("Usted dispone de: "+ saldo +" pesos");
              validado = subMenu();
            break;
            case "2":
              boolean incorrecto = true;
              while(incorrecto){
                System.out.println("Ingrese la cantidad que desea retirar:");
                String amount = input.next();
                if(isNumeric(amount)){
                  if( Integer.parseInt(amount)>saldo ){
                    System.out.println("No dispone de esa cantidad, le quedan "+saldo);
                    System.out.println("Por favor intente de nuevo");
                  }else{
                    //movimiento
                    LocalDateTime now = LocalDateTime.now();  
                    String nowFormatted = now.format(dtf);
                    historicoDate.add(nowFormatted);
                    historicoOldSaldo.add(saldo);
                    historicoNewSaldo.add(saldo-Integer.parseInt(amount));

                    saldo = saldo - Integer.parseInt(amount);
                    System.out.println("Ha retirado "+amount+" le restan "+saldo+" en su cuenta");
                    incorrecto = false;
                  }
                }else{
                  System.out.println("Debe ingresar un número, intente de nuevo");
                }
              }
              if( saldo == 0){
                System.out.println("No se tienen fondos suficientes, saldo actual "+saldo+" pesos");
              }
              validado = subMenu();
            break;
            case "3":
              if(historicoDate.size()==0){
                System.out.println("Historico vacio");
              }else{
                for(int i=0;i<historicoDate.size();i++){
                  System.out.println("Fecha del movimiento: "+historicoDate.get(i) +
                  "\tSaldo anterior: "+historicoOldSaldo.get(i) +
                  "\tNuevo actual: "+historicoNewSaldo.get(i) );
                }
              }
              validado = subMenu();
            break;
            default:
              System.out.println("Por favor, intente de nuevo");

          }

        }else{
          System.out.println("Tipo de dato invalido");
        }

      }
    }
  }
}
