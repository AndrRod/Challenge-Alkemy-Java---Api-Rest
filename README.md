# CHALLENGE BACKEND - Java - Spring Boot (API) 🚀

### Objetivo

Desarrollar una API para explorar el mundo de Disney, la cual permitirá conocer y modificar los personajes que lo componen y entender en qué películas estos participaron. Por otro lado, deberá exponer la información para que cualquier frontend pueda consumirla.
👉 Utilizar Spring Boot.
👉 No es necesario armar el Frontend.
👉 Las rutas deberán seguir el patrón REST.
👉 Utilizar la librería Spring Security.

### Requerimientos técnicos
## 1. Modelado de Base de Datos
-**Personaje:**  deberá tener,
○ Imagen.
○ Nombre.
○ Edad.
○ Peso.
○ Historia.
○ Películas o series asociadas.

-**Película o Serie:**  deberá tener,
○ Imagen.
○ Título.
○ Fecha de creación.
○ Calificación (del 1 al 5).
○ Personajes asociados.

-**Género:** deberá tener,
○ Nombre.
○ Imagen.
○ Películas o series asociadas.

## 2. Autenticación de Usuarios
El usuario despues de registrarse y logearse, obteniene un token, el cual es necesario y requerido para acceder a los demás path

### POST
	http://localhost:8080/auth/login

### POST
	http://localhost:8080/auth/register


## 3. Listado de Personajes

Con el siguiente endpoint se muestra los personajes, pero solamente se filtra a traves de un DTO los datos de: imagen y nombre.

## 4. Personajes (CRUD)

### POST
	http://localhost:8080/crearPersonaje/

### PUT by ID
	http://localhost:8080/modificarPersonaje/{id}

### DELETE by ID
	http://localhost:8080/borrarPersonaje/{id}

## 5. Personaje detalle

En el detalle se alistan todos los atributos del personaje, como así también sus películas relacionadas.
### GET By ID
	http://localhost:8080/detallePersonaje/{id}




