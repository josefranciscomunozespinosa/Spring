# Spring Boot API Sample Project

Para ejecutar este proyecto, debe seguir los siguientes pasos:

1. Instalar MySQL (si no lo tienes ya):
   ```text
      https://dev.mysql.com/downloads/installer/
   ```
2. Conectar a la base de datos MySQL y crear un usuario específico para esta aplicación:
    ```bash
      $ sudo mysql --password
    ```
3. Para crear la nueva base de datos, usuario y los permisos del usuario en la base de datos puedes lanzar los siguientes comandos desde la consola de mysql:
    ```bash
        mysql> create database db_example; -- Creates the new database
        mysql> create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
        mysql> grant all on db_example.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database      
    ```

## Referencias
   ```
      - https://spring.io/guides/gs/accessing-data-mysql/
   ```

## Revisa el código

Si nos fijamos en `UserController` hemos introducido una anotación nueva. Si hacemos solicitudes desde Postman no tendremos ningún problema sin esta anotación. Pero sin embargo si hacemos una llamada desde el javascript de [index.html](index.html) nos daría un error CORS si no ponemos esta anotación con la ruta a habilitar (en este caso todas las rutas).

```java
@CrossOrigin( "*" ) 
@RestController
@RequestMapping("user")
public class UserController{
  // --
}
```

## Spring Boot y la anotación @CrossOrigin
El intercambio de recursos de origen cruzado (CORS) es un protocolo estándar que define la interacción entre un navegador y un servidor para manejar de forma segura las solicitudes HTTP de origen cruzado.

En pocas palabras, una solicitud HTTP de origen cruzado es una solicitud a un recurso específico, que se encuentra en un origen diferente, es decir, un dominio, protocolo y puerto, que el del cliente que realiza la solicitud.

Por razones obvias, los navegadores pueden solicitar varios recursos de origen cruzado, incluidas imágenes, CSS, archivos JavaScript, etc. Sin embargo, de forma predeterminada, el modelo de seguridad de un navegador denegará cualquier solicitud HTTP de origen cruzado realizada por scripts del lado del cliente.

Si bien este comportamiento es deseable, por ejemplo, para evitar diferentes tipos de ataques basados en Ajax, a veces necesitamos indicar al navegador que permita las solicitudes HTTP de origen cruzado de los clientes de JavaScript con CORS.

Para comprender mejor por qué CORS es útil en ciertos casos de uso, consideremos el siguiente ejemplo: un cliente JavaScript que se ejecuta en http://localhost:4200, y una API de servicio web RESTful de spring boot que escucha en https://mipagina.com/api .

En tal caso, el cliente debería poder consumir la API REST, que de forma predeterminada estaría prohibida. Para lograr esto, podemos habilitar fácilmente CORS para estos dos dominios específicos en el navegador simplemente anotando los métodos de la API del servicio web RESTful responsable de manejar las solicitudes de los clientes con la anotación @CrossOrigin.

Otro ejemplo podría ser habilitar sólo ciertas url. Es decir, sólo los que me llamen de la url que yo diga pasará el filtro y no le devolverá un error CORS. Por ejemplo:


```java
@RestController
@CrossOrigin(origins = "http://localhost:8383")
@RequestMapping("user")
public class UserController{
  // --
}
```

Por supuesto, el detalle más relevante que vale la pena resaltar aquí es el uso de la anotación @CrossOrigin (origins = “http://localhost:8383”). Esto permite que el navegador maneje de forma segura las solicitudes HTTP de origen cruzado de un cliente cuyo origen es http://localhost:8383.

```java
@CrossOrigin(origins = {"http://mipaginaweb.com:8383", "http://unservidor.pro:4200"})
@RestController
@RequestMapping("user")
public class UserController{
  // --
}
```

También podríamos especificar @CrossOrigin a nivel de método si lo deseasemos

```java
@RestController
@RequestMapping("user")
public class UsuarioController {
 
    // ...
     
    @GetMapping("/all")
    @CrossOrigin(origins = "http://mipaginaweb.com:8383")
    public Iterable<Usuario> getUsuarios() {
        // ...
    }
}
```
