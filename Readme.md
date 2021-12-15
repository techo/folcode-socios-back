A continuación se detallan algunas especificaciones técnicas desde la aplicación Spring Boot, para su configuración y correcto funcionamiento.
# Techo

This project was generated with spring boot v 2.5.2 [https://spring.io/projects/spring-boot] in IntelliJ IDEA 2021.2.2(Community Edition) Build #IC-212.5284.40, built on September 14, 2021
Runtime version: 11.0.12+7-b1504.28 amd64 VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.



# Dependecias/Librerías

Las siguientes dependencias/librerías utilizadas son:

firebase 6.8.1 [https://firebase.google.com/docs/build]
maven 1.8

ver archivo pom.xml para obtener la lista completa de dependencias



# Instalación
1° Tener instalado 'Java',https://www.java.com/es/download/
2° Tener instalado Git,https://git-scm.com/downloads
3° Clonamos el repositorio al local, y abrimos el código en el editor de código
4° Abrimos consola, y escribimos clean install spring-boot:run para correr el programa y cargar las dependencias

# Firebase

Para poder acceder al sistema como administrador o socio, se deberá generar las credenciales de autenticación de Firebase.
1° Se deberá crear una cuenta de Firebase de Google (https://firebase.google.com/)
2° Una vez realizado el login, se tiene que crear un proyecto siguiendo los pasos: 
    a) Ingresando el nombre del proyecto a "Techo"
    b) Puedes agregar Google Analytics (opcional)
    c) (opcional si se agrego Google Analytics) Elegir la cuenta "Default Account for Firebase"
    d) Por último "Crear Proyecto"
3° Abrimos el proyecto, y creamos una `APP WEB` 
    a) Registramos nuestra app con el nombre de "Techo"
  	b)Abrimos la configuracion del proyecto en Descripcion general del proyecto
	c)Seleccionamos Cuentas de servicio,en fragmento de configuracion de Admin SDK seleccionamos Java
	d)Copiamos el codigo que nos da,seleccionamos Generar nueva clave privada
	e)Descargamos el archivo json,y copiamos la clave en la variable secret,ubicada en el archivo UsersService.
	f)Seleccionamos en la consola de firebase Realtime Database,copiamos el link provisto y lo pegamos en setDataBaseUrl,en el metodo options,del archivo UsersService

Documentación (https://firebase.google.com/docs/build)



#AplicationProperties
En el archivo application.properties copiamos las credenciales correspondientes a la base de datos a utilizar

