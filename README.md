# REST_API

La API tiene las siguientes operaciones:

* Crear, editar, actualizar y eliminar registros (Entidades: Cliente, Cuenta y Movimientos).
Los endpoints son:
* /cuentas
* /clientes
* /movimientos
* /reportes

## Notas
  * los valores cuando son creditos son positivos, y los debitos son negativos. Debe almacenarse el saldo disponible en cada transaccion dependiendo del tipo de movimiento. (suma o resta)
  * * si el saldo es cero, y va a realizar un debito, debe desplegar el mensaje "Saldo no disponible"
  * Se debe tener un parametro limite diario de retiro (vlor tope $1000)
  * Si el cupo disponible ya se cumplio no debe permitir realizar un debito y debe desplegar el mensaje "Cupo Diario Excedido"
  * Crear un endpoint para retornar un reporte (estado de cuenta) especificando respectivos saldos y el total de debitos y creditos realizados durantes las fechas de ese cliente.

## Consumo EndPoint ``` /clientes ```

#### Add new client

  ``` javascript
  URL: http://localhost:8080/clientes,
  METHOD: POST,
  BODY:JSON = {
      "cedula": "1400919407",
      "nombre": "DEV SU README",
      "genero": "FEMALE",
      "edad": 25,
      "direccion": "Ecuador",
      "numeroTelefono": "0998952718",
      "contrasena": "Abc123!df",
      "estado": true
    }
  EXPECTED_OK_RESPONSE: 201 CREATED, 
    {
      "cedula": "1400919407",
      "nombre": "DEV SU README",
      "genero": "FEMALE",
      "edad": 25,
      "direccion": "Ecuador",
      "numeroTelefono": "0998952718",
      "contrasena": "Abc123!df",
      "estado": true
    }
```
#### Delete Client by Cedula
```javascript
  URL: http://localhost:8080/clientes/1400919407
  METHOD: DELETE
  PATH_VARIABLE: 1400919407
  EXPECTED_OK_RESPONSE: TRANSACTION COMPLETED SUCCESSFULLY
```
#### Update method: update Client Name
``` javascript
  URL: http://localhost:8080/clientes/1400919407?nombre=Dev Su README UPDATED
  METHOD: PATCH
  PATH_VARIABLE: STRING cedula, 
  REQUEST_PARAM: STRING nombre
  EXPECTED_OK_RESPONSE: 200 OK,
  {
    "data": {
        "cedula": "1400919407",
        "nombre": "Dev Su README UPDATED",
        "genero": "FEMALE",
        "edad": 25,
        "direccion": "Ecuador",
        "numeroTelefono": "0998952718",
        "contrasena": "Abc123!df",
        "estado": true
    },
    "message": "TRANSACTION COMPLETED SUCCESSFULLY",
    "status": "OK"
}

```
## Consumo EndPoint ``` /cuentas ```

#### Add new cuenta
```javascript
  URL: http://localhost:8080/cuentas
  METHOD: Post
  BODY:JSON = {
    "numeroCuenta":"478754",
    "tipoCuenta":"AHORROS",
    "saldoInicial":60,
    "estado":true,
    "clienteCedula":"1400919407"
}
  EXPECTED_OK_RESPONSE: 201 CREATED,
    {
      "numeroCuenta": "478754",
      "tipoCuenta": "AHORROS",
      "saldoInicial": 60,
      "estado": true,
      "clienteCedula": "1400919405"
    }

```
#### GET CUENTA BY ACCOUNT NUMBER
```javascript
  URL: http://localhost:8080/cuentas/cuenta/478754
  METHOD: GET
  PATH_VARIABLE: String numeroCuenta
```
#### DELETE CUENTA BY ACCOUNT NUMBER
```javascript
  URL: http://localhost:8080/cuentas/numeroCuenta/478752
  METHOD: DELETE
  PATH_VARIABLE: STRING numeroCuenta, 
```

### UPDATE CUENTA
```javascript
  URL: http://localhost:8080/cuentas/numeroCuenta/478754
  METHOD: PUT
  BODY:
  {
    "numeroCuenta":"478754",
    "tipoCuenta":"CORRIENTE",
    "saldoInicial":60,
    "estado":true,
    "clienteCedula":"1400919405"
  }
  EXPECTED_OK_RESPONSE: 200 OK,
  {
    "numeroCuenta": "478754",
    "tipoCuenta": "CORRIENTE",
    "saldoInicial": 60,
    "estado": true,
    "clienteCedula": "1400919405"
}
```

## Consumo EndPoint ``` /movimientos ```
#### HACER MOVIMIENTO DEPOSITO
```javascript
  URL: http:localhost:8080/movimientos
  METHOD: POST
  BODY:
  {
    "numeroCuenta": "478754",
    "fechaMovimiento": "12-07-2023",
    "tipoMovimiento": "DEPOSIT",
    "valor": 5,
    "saldoInicial": 60.00,
    "saldoDisponible": 60.00,
    "clienteCedula": "1400919405",
    "estado": true
  }
  EXPECTED_OK_RESPONSE: 200 OK,
  {
    "numeroCuenta": "478754",
    "fechaMovimiento": "12-07-2023",
    "tipoMovimiento": "DEPOSIT",
    "valor": 5,
    "saldoInicial": 60.00,
    "saldoDisponible": 65.00,
    "clienteCedula": "1400919405",
    "estado": true
  }
```
#### HACER MOVIMIENTO DEBITO
```javascript
  URL: http:localhost:8080/movimientos
  METHOD: POST
  BODY:
  {
    "numeroCuenta": "478753",
    "fechaMovimiento": "12-07-2023",
    "tipoMovimiento": "DEBIT",
    "valor": 20,
    "saldoInicial": 55.00,
    "saldoDisponible": 55.00,
    "clienteCedula": "1400919404",
    "estado": true
}
  EXPECTED_OK_RESPONSE: 200 OK,
  {
    "numeroCuenta": "478753",
    "fechaMovimiento": "13-07-2023",
    "tipoMovimiento": "DEBIT",
    "valor": -20,
    "saldoInicial": 55.00,
    "saldoDisponible": 35.00,
    "clienteCedula": "1400919404",
    "estado": true
}
```
#### OBTENER TODAS LAS TRANSACCIONES POR NUMERO DE CUENTA
```javascript
  URL: http:localhost:8080/movimientos/cuenta/{accountNumber}
  PATH_VARIABLE: String accountNumber
  METHOD: GET
```

## Consumo endpoint ```/reportes```
#### OBTENER REPORTE ESTADO DE CUENTA
```javascript
  URL: http://localhost:8080/reportes?identification={identification}&initDate={initDate}&endDate={endDate}
  METHOD: GET
  PARAMS: String identification, String initDate, String endDate
  OBSERVATION: initDate y endDate patron: dd-MM-yyyy. Ej: 12-07-2023 
```