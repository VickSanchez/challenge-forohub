<h1 align="center"> Challenge Foro Hub </h1>
<p>
    <img src="https://img.shields.io/badge/LICENSE-MIT-green">
    <img src="https://img.shields.io/badge/VERSION-1.0.0-blue">
    <img src="https://img.shields.io/badge/STATUS-Released-green">
</p>

### API REST para Registrar Dudas en un Foro Desarrollada Para el Programa ONE Latam  ([Funcionalidades](#funcionalidades) )

## Configuración

<ul>
  <li>
    <img src="https://img.shields.io/badge/JAVA-17-green">
  </li>
  <li>
    <img src="https://img.shields.io/badge/Maven-4-green">
  </li>
  <li>
   <img src="https://img.shields.io/badge/Spring Boot-3.3.0-green">
      <ul>
        <li>
          <img src="https://img.shields.io/badge/Lombok-blue">  
        </li>
        <li>
          <img src="https://img.shields.io/badge/Spring Web-green">
        </li>
        <li>
          <img src="https://img.shields.io/badge/Spring Boot DevTools-green">
        </li>
        <li>
          <img src="https://img.shields.io/badge/Spring Data JPA-green">
        </li>
        <li>
          <img src="https://img.shields.io/badge/Flyway Migration-red">
        </li>
        <li>
         <img src="https://img.shields.io/badge/MySQL Driver-gray">
        </li>
        <li>
          <img src="https://img.shields.io/badge/Bean Validation-yellow">
        </li>
        <li>
          <img src="https://img.shields.io/badge/Spring Security-red">
        </li>     
      </ul>
  </li>
</ul>

## Funcionalidades

:heavy_check_mark: `Funcionalidad 1:` Autenticación de usuarios

:heavy_check_mark: `Funcionalidad 2:` Registrar topicos

:heavy_check_mark: `Funcionalidad 3:` Listar Topicos Registrados

:heavy_check_mark: `Funcionalidad 4:` Listar un Topico por Id

:heavy_check_mark: `Funcionalidad 5:` Actualizar topicos

:heavy_check_mark: `Funcionalidad 6:` Eliminar topicos


## EndPoints

- **Autenticación de Usuarios**
  - `POST /login`
  - Body:
  ```json
    {
      "login": "username",
      "clave" : "password"
    }
    ```
- **Registrar Topicos**
  - `POST /topicos`
  - Auth: Bearer Token
  - Body:
  ```json
    {
    	"titulo":"titulo",
    	"mensaje": "mensaje",
    	"autor":"autor",
    	"curso":"curso"
    }  
    ```
- **Listar Topicos**
  - `GET /topicos`
  - Auth: Bearer Token

- **Listar Un Topico por ID**
  - `GET /topicos/{id}`
  - Auth: Bearer Token
    
- **Actualizar Topicos**
  - `PUT /topicos`
  - Auth: Bearer Token
  - Body:
  ```json
    {
    	"id": "id",
    	"titulo":"titulo",
    	"mensaje": "mensaje"
    }
    ```
- **Eliminar Topicos**
  - `DELETE /topicos/{id}`
  - Auth: Bearer Token
  
