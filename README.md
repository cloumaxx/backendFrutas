
# backendFrutas
 Este repositorio contiene una aplicación desarrollada en Spring Boot como parte del proceso de selección para la vacante de Desarrollador Fullstack en OPTIMAL TECHNOLOGY. La aplicación consiste en un formulario que permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar).

## Instalación 

#### Clonar el repositorio
Primero, clona este repositorio en tu máquina local usando Git:

```bash
git clone https://github.com/cloumaxx/backendFrutas.git
cd tu-repositorio
```

#### Construir la imagen de Docker

Una vez que hayas clonado el repositorio, navega a la raíz del proyecto y ejecuta el siguiente comando para construir la imagen de Docker:
```bash
docker build -t backendFrutas .

```
#### Ejecutar la aplicación
Una vez que la imagen haya sido construida, puedes ejecutar un contenedor basado en esa imagen usando el siguiente comando:

```bash 
docker run -d -p 8987:8987 backendFrutas

```
## Acceder a la aplicación

Después de que el contenedor esté en ejecución, puedes acceder a la aplicación a través de la siguiente URL en tu navegador web:
```bash 
http://localhost:8987

```


