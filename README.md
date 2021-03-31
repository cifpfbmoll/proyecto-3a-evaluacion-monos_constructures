![MCE Logo](https://raw.githubusercontent.com/cifpfbmoll/proyecto-3a-evaluacion-monos_constructures/develop/MCE/src/Ventanas/Trash/img/MC_Logo.png)
# MCE - CRUCEROS
#### _Documentación del proyecto_
- - -


Nuestro programa se va a encargar de gestionar los disntintos servicios en relacionados con los cruceros y viajes de la empresa, para ello va a ser necesario crear una base de datos y establecer una conexión con ella.

El objetivo final es tener un programa funcional que cumpla los siguientes requisitos:



## Requisitos del Product Owner
Estos son los requisitos que nos ha descrito el cliente del producto a la hora de realizar la maquetación del mismo, estos elementos tendrán que estar presentes dentro del programa obligatóriamente.



 ### ✭ Niveles de acceso  ✭ 
 - - - -
 ---
| Usuarios | Funciones |
| ------ | ------ |
| Clientes | Los clientes podrán reservar los viajes, generamos una factura, si quiere podemos             generar un fichero txt con el ticket de embarque. |
| Recursos humanos | Todo lo que tiene que ver con los empleados, ya sea contratar, despedir o                      realizar los contratos (roles), también se van a encargar de asignar la                       tripulación a los distintos viajes y cruceros, crear nóminas... |
| Empleados | Tendrán acceso a funciones específicas, ver sus nóminas (ingresos durante el                  año). |
| Administrativo | Consultar el estado de un crucero, acceder a la lista de pasajeros, acceder                    a la lista de tripulación. |

 ### ✭  Requisitos funcionales  ✭ 
 - - - -
 - - - -
| Funcionalidad del programa |
| ------ |
| Escribir ficheros (uso de ficheros para lectura y escritura de información) |
| Listado de pasajeros y empleados en un viaje y crucero concreto |
| Nóminas empleados |
| Roles de empleados - lo asigna RRHH |
| Asignar empleados a barcos - lo asigna RRHH |
| Los empleados podrán ver sus nóminas(ingresos durante el año) para sacar su renta |
| Nóminas empleados |
| Gestionar distintos niveles de acceso (privilegios de usuario) |
| Poder conectarse a la base de datos para introducir o seleccionar información |
| Dar la opción al empleado de asignar a los clientes un viaje en concreto. |





 ### ✭  Base de datos  ✭ 
 - - - -
 - - - -
| Requisitos de la base de datos |
| ------ |
| Tabla cruceros (id, nombre, características…) |
| Tarifas de viaje (precio, tipo de tarifa) |
| Rutas de cruceros (origen, destino) |
