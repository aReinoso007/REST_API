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

### Add new client

  ``` javascript
  URL: http://localhost:8080/clientes,
  METHOD: POST,
  PAYLOAD:JSON = {
      "cedula":"1400919406",
      "nombre":"DEV SU TEST TRES",
      "genero":"FEMALE",
      "edad":25,
      "direccion":"Ecuador",
      "numeroTelefono":"0998952718",
      "contrasena":"Abc123!df",
      "estado":true
    }

```
