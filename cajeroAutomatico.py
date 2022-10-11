#Código de cajero automático

#Soy un comentario que será pusheado
import datetime

def Autentificador():
    validado = False
    patience = 3
    while(validado==False):

        var = input(f'Bienvenido, favor de identificarte con el PIN:')
        
        if( not(var.isnumeric()) ):
            patience -=1
            print(f'Tipo de dato invalido, le queda {patience} intentos')
        elif( int(var) != 1235):
            patience -=1
            print(f'Pin incorrecto, le queda {patience} intentos')
        elif( int(var) == 1235):
            print(f'Usuario reconocido, \n Bienvenido José Ángel Avelar')
            validado = 'valido'
        else:
            print('logger de situación no prevista')
        if( patience == 0 ):
            print(f'Ha excedido el número de intentos permitidos en una ejecución')
            print(f'Hasta luego')
            validado = True
    return validado

def MenuIterativo(saldo = 1000, historico = []):
   
    print(f'Elija una opción del menú:')
    print(f'1.- Consultar saldo')
    print(f'2.- Retirar saldo')
    print(f'3.- Historico de movimientos saldo')
    var = input(f'opción: ') 
    if var == '1':
        print(f'Usted dispone de: {saldo} pesos')
        Submenu(saldo, historico)
    elif var == '2':       
        incorrecto = True
        while incorrecto:
            print(f'Ingrese la cantidad que desea retirar:')
            amount = input(f'cantidad: ')
            if amount.isnumeric():
                if int(amount) > saldo:
                    print(f'No dispone de esa cantidad, le quedan {saldo}')
                    print('Por favor, intente de nuevo')
                else:
                    movimiento = [datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"),saldo, saldo -int(amount)]
                    historico.append(movimiento)
                    saldo = saldo - int(amount)
                    print(f'Ha retirado {int(amount)} le restan {saldo} en su cuenta')
                    incorrecto = False
            else:
                print('Debe ingresar un número, intente de nuevo')
            if saldo == 0:
                print(f'No se tienen fondos suficientes, saldo actual {saldo} pesos')
        Submenu(saldo, historico)
    elif var == '3':
        if historico == []:
            print('Historico vacio')
        else:
            for row in historico:
                print( f'Fecha del movimiento: {row[0]}\tSaldo anterior: {row[1]}\tSaldo actual: {row[2]}' )
        Submenu(saldo, historico)
    else: 
        print('Por favor, intente de nuevo')
        MenuIterativo(saldo = saldo, historico = historico)

def Submenu(saldo, historico):
    print(f'Elija una opción del submenú:')
    print(f'1.- Menú principal')
    print(f'2.- Salir')
    var = input(f'opción: ') 
    if var == '1':
        MenuIterativo(saldo = saldo, historico = historico)
    elif var == '2':
        print('Ha sido un gusto, hasta luego')
    else:
        print('Por favor, intente de nuevo')
        Submenu(saldo=saldo, historico=historico)
    

if( Autentificador() == 'valido' ):
    MenuIterativo()