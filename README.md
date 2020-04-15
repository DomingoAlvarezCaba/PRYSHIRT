PRYHIRT
=======

PRYHIRT es un proyecto que se va desarrollar durante las prácticas que voy a realizar en la empresa ICIRED, el objetivo es desarrollar 
una tienda de camisetas dónde el backend se va a realizar con el framework de Java Spring y el frontend se va a hacer con el framework 
de JavaScript Vue.js.

Tecnologías involucradas
------------------
###### GIT
Para poder llevar un control de las versiones se va a usar GIT.

Ramas que se van a usar:

* Master -> rama de producción.
* Develop -> rama que contiene la versión más reciente a master, una vez probadas todas las features del Spring, se integrarán aquí.
* Sprint-XX -> rama del sprint que se esté haciendo.
* Feature-YYY-<nombre> -> rama que se va a crear por cada característica a conseguir. Cuando se ha probado se integra en la rama del
Sprint en cuestión.

###### Java 8 LTS

Es el kit de desarrollo para Java.

###### Postman

Para poder mandar peticiones y poder testear la API REST, se va a necesitar una herramienta como POSTMAN.

###### Maven 3

Maven es una herramienta para el manejo de dependencias automáticas. Basa su configuración en un archivo XML llamado POM
(Project Object Model), el cuál permite guardar la información de todas las dependecias de la aplicación dentro del elemento
XML dependencies.

Para que maven funcione en windows, se debe de crear una variable de entorno llamada "MAVEN_HOME", con el valor de la ruta de la carpeta dist del archivo maven descomprimido.

###### WinScp

WinSCP es un cliente SFTP gráfico para Windows que emplea SSH.

###### Vue.js

Vue.js es un framework de JavaScript progresivo para construir interfaces de usuario. Permite hacer SPA (Single-Page Applications)
cuando se utiliza en combinación con herramientas modernas y librerías de apoyo.

###### Spring framework

Spring es un framework para el desarrollo de aplicaciones  JEE (Java Enterprise Edition). Es el encargado de generar beans a partir
de objetos básicos de Java, o clases POJOs (Plain Old Java Objects). Para poder relacionar los beans, Spring usa el concepto de 
inyección de dependencia a través de la inversión de control. La configuración de los beans se describe mediante ficheros xml. 
Spring usa anotaciones para poder hacer uso de los propios.

###### Eclipse

Eclipse un IDE para desarrollar aplicaciones en varios lenguajes, aunque se suele usar para desarrollar aplicaciones con Java.

###### Visual Studio Code

Visual Studio Code es un IDE completo para programar, depurar, probar e implementar soluciones en cualquier plataforma

Requisitos previos a la ejecución de la app
------------------
###### Postgresql

Es necesario tener el sistema gestor de base de datos postgresql en tu dispositivo.

Configuración de la base de datos:

* Para poder cambiar algunos parámetros de la base de datos, se tiene que acceder al fichero ``` src/main/resources/application.properties ```

* Aquí se puede modificar la base de datos, el usuario, y la contraseña de la misma.
```
spring.datasource.url= jdbc:postgresql://localhost/shop
spring.datasource.username= postgres
spring.datasource.password= admin
```
Cómo ejecutar la app
--------------------

Para ejecutar la app, esta se debe de construir, para ello debemos de ejecutar el comando ``` mvn package ```, en esta caso, vamos saltar los tests porque están creados para casos en concreto, por lo tanto, debemos de ejecutar el comando con el parámetro ``` mvn package -DskipTests ```


Una vez construido, se debe de ejecutar el jar ``` target/PRYSHIRT-0.0.1-SNAPSHOT.jar ```
```
java  -jar PRYSHIRT.jar
```

Rutas para acceder a la API REST
--------------------------------
Ejemplo de las rutas para los productos.

* GET /pryhirt/products -> Devuelve todos los productos.
* GET /pryhirt/product/{id} -> Devuelve el producto que corresponda al id que se le pasa.
* GET /pryhirt/product?state={estado} -> Devuelve los productos que correspondan al estado que se le pasa.
* POST /pryhirt/product -> Crea un pedido y lo devuelve.
* PUT /pryhirt/product/{id} -> Modifica un pedido y lo devuelve.
* DELETE /pryhirt/product/{id} -> Borra un pedido y devuelve true en caso de haber tenido éxito y false en caso contrario.

Principales problemas y su resolución
-------------------------------------
* Error de recursión infinita: Para evitar un problema de recursión infinita en un relación entre tablas, se debe de añadir la 
anotación @JsonBackReference para indicar la parte de la relación que no se quiere Serializar, este problema lo causa la librería
de JSON "Jackson".

* Problema al usar palabras reservadas: Al usar una palabra reservada en postgresql como 'order' para crear una tabla produce un error,
para poder solucionar esto se debe de escribir el nombre de la tabla entre comillas dobles "order".

* Conflictos al realizar un merge: Surgen cuando los commits de la rama a fusionar y de la rama actual modifican la misma parte en un
archivo en particular y git no sabe con qué versión quedarse. Antes de hacer un commit en la rama que se ha hecho el merge se debe
de elegir la versión que se quiere dejar en los archivos que tengan conflictos.
