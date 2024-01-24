Arquitectura Hexagonal o de puertos y adaptadores

Datos de entrada del servicio (En mi caso use PostMan): { "applicationDate": "2020-06-14", "productId": "35455", "brandId": "1" }

Datos para ingresar al H2 cuando se levante la app

http://localhost:8080/h2-console/login.do 
usuario: sa
contraseña: password

Estructura:

aplicacion 
 servicios 
   puertos 
     entrada 
     salida 
  adaptadores 
      entrada 
      salida 
  dominio 
  comunes




