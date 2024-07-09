
# Event Planner - Aplicación de Gestión de Eventos

Event Planner es una plataforma diseñada para simplificar la planificación de eventos.

## Tabla de Contenidos
- [Descripción](#descripción)
- [Manual de Usuario](#manual-de-usuario)
- [Primeros Pasos](#primeros-pasos)
- [Dependencias del Proyecto](#dependencias-del-proyecto)
- [Puertos de la Aplicación](#puertos-de-la-aplicación)
- [Comandos Básicos de Docker](#comandos-básicos-de-docker)
- [Contribuir](#contribuir)
- [Descargo de Responsabilidad](#descargo-de-responsabilidad)

## Descripción
Event Planner es un prototipo funcional diseñado para proporcionar una solución versátil para una amplia variedad de necesidades de planificación de eventos, facilitando los procesos de toma de decisiones. La plataforma tiene como objetivo centralizar la gestión de eventos, eliminando la necesidad de consultar múltiples plataformas individuales.


## Manual de Usuario
Event Planner es una plataforma diseñada para simplificar la planificación de eventos. Este manual le guiará a través de las funcionalidades del sistema y le proporcionará una visión clara de cómo aprovechar al máximo esta herramienta.

Los usuarios pueden:

** Si el usuario no está registrado
- Filtrar eventos por año, nombre o tipo de evento.
- Ver eventos de acceso libre creados por otros usuarios.
- Registrarse para acceder a contenidos específicos según sus necesidades.

{CAPTURA DEL INICIO SIN REGISTRAR}


** Si el usuario está registrado
- Crear y gestionar múltiples eventos desde un mismo panel.
- Personalizar la información y las opciones según el tipo de evento.
- Analizar estadísticas detalladas de cada evento.

{CAPTURA DEL INICIO REGISTRADO}



1. Registro y Inicio de Sesión
Para acceder a las funcionalidades completas de Event Planner, los usuarios deben registrarse.

Pasos para el registro:
- Acceda al registro desde la barra de navegación.
- Rellene el formulario con tus datos personales.
- Verifica tu correo electrónico.

{CAPTURA SIGNUP}

Pasos para iniciar sesión:
- Acceda al inicio de sesión desde la barra de navegación.
- Introduzca su correo electrónico y contraseña.
- Inicie sesión.

{CAPTURA LOGIN}



2. Creación de Eventos
La creación de eventos se limita a usuarios registrados.

Pasos para crear un evento:
- Seleccione el tipo de evento.
- Introduzca los datos generales del evento (nombre, descripción, fechas, etc.).
- Configure la localización del evento.
- Gestione los aspectos financieros (precio de entrada, políticas de reembolso, etc.).
- Añada preguntas frecuentes.
- Configure el menú del evento.
- Confirme los detalles y selecciona la plantilla y la paleta de colores.

{VIDEO DE CREACIÓN DE EVENTO}


3. Gestión de Eventos
Los usuarios pueden gestionar sus eventos creados desde un panel centralizado.

Funcionalidades del panel gestor:
- Ver la información de los eventos a los que está asociado un usuario.

{CAPTURA DE MY_EVENTS}

Funcionalidades de la gestión de eventos:
- Actualizar fechas y configuraciones.
- Visualizar estadísticas del evento y de los participantes.
- Asignar roles y permisos a otros usuarios.

{CAPTURA DE GESTIÓN DE USUARIOS EN UN EVENTO}


4. Idiomas
Event Planner ofrece soporte multilingüe con un total de cinco traducciones, permitiendo a los usuarios elegir entre varios idiomas disponibles.

Pasos para cambiar el idioma:
- Acceda al selector de idioma en la barra de navegación.
- Seleccione el idioma deseado.
- La interfaz se actualizará automáticamente al idioma seleccionado.

{VIDEO CAMBIO DE IDIOMA}


5. Cierre de la sesión
Se podrá cerrar la sesión desde la barra de navegación siempre y cuando el usuario esté registrado y con la sesión iniciada.

{CAPTURA DE PANTALLA BARRA NAVEGACIÓN}


--------------------------------------------------

Este manual proporciona una guía detallada para utilizar Event Planner, asegurando que los usuarios puedan aprovechar al máximo todas las funcionalidades y características de la plataforma.


## Primeros Pasos
Para comenzar con el proyecto, siga estos pasos:
1. Clone el repositorio:
   ```bash
   https://github.com/mele13/EventPlanner
   ```
2. Abra el proyecto en un entorno de desarrollo adecuado o servidor web.
3. ¡Empiece a trabajar! Puede comenzar revisando a las carpetas del proyecto.


## Dependencias del Proyecto
Este proyecto utiliza Docker para gestionar sus dependencias, simplificando el proceso de configuración e implementación. Las siguientes tecnologías y servicios clave se utilizan dentro de los contenedores Docker:

- Vue.js: Un framework progresivo de JavaScript utilizado para construir interfaces de usuario interactivas y aplicaciones de una sola página (SPA).

- Vite: Una herramienta de construcción que proporciona un entorno de desarrollo rápido y un proceso de construcción eficiente para aplicaciones web modernas, particularmente aquellas que utilizan Vue.js.

- Nginx: Utilizado como servidor web y proxy inverso para manejar solicitudes y servir archivos estáticos de manera eficiente.

- Spring Boot: Un framework para construir servicios backend y API basados en Java. Simplifica el desarrollo de aplicaciones listas para producción.

- PostgreSQL: Un sistema de gestión de bases de datos relacional, potente y de código abierto. Maneja el almacenamiento y la gestión de datos relacionados con eventos.

- MongoDB: Una base de datos NoSQL utilizada para almacenar datos flexibles y orientados a documentos. Es ideal para manejar estructuras de datos complejas y jerárquicas.


## Puertos de la Aplicación
- Front-end application: http://localhost:5173/
- API: http://localhost:3004/


## Comandos Básicos de Docker
Aquí hay algunos comandos básicos de Docker que todos deberían conocer:
- Construir todas las imágenes de Docker necesarias:
    ```
    docker compose build
    ```
- (Re)Iniciar todos los contenedores de Docker:
    ```
    docker compose up -d
    ```
- Reconstruir la imagen antes de iniciar el contenedor:
    ```
    docker compose up --build -d
    ```
    Este comando puede ser más lento que docker compose up -d porque reconstruye la imagen cada vez. Sin embargo, puede ser útil si ha realizado cambios en el Dockerfile o si la imagen no se construyó correctamente la primera vez.
- Detener la aplicación y eliminar todos los contenedores de Docker:
    ```
    docker compose down
    ```
- Abrir una terminal dentro del contenedor con el nombre especificado:
    ```
    docker exec -it <container_name> 
    ```
- Listar todos los servicios en ejecución definidos en el archivo docker compose:
    ```
    docker compose ps
    ```
- Listar todas las redes Docker en el sistema:
    ```
    docker network ls
    ```
- Detener todos los contenedores Docker inmediatamente:
    ```
    docker compose kill
    ```
- Eliminar todas las redes Docker no utilizadas:
    ```
    docker network prune
    ```
- Listar todos los contenedores en ejecución:
    ```
    docker ps
    ```
- Mostrar el nombre de cada contenedor y su dirección IP asociada:
    ```
    docker inspect -f '{{.Name}} - {{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(docker ps -aq)
    ```
** Estos son solo algunos ejemplos de comandos de Docker. Consulte la documentación para obtener más información: https://docs.docker.com/engine/reference/commandline/ (Docker CLI).


## Contribuir
Si desea contribuir a este repositorio, siga estos pasos:

1. Haga un fork de este repositorio.
2. Cree una nueva rama para sus cambios: `git checkout -b feature/new-feature`.
3. Realice sus cambios y haga commit de ellos: `git commit -m "Add new feature"`.
4. Pushee sus cambios a su repositorio fork: `git push origin feature/new-feature`.
5. Cree un pull request explicando sus cambios.

¡Disfrute explorando el proyecto!


## Descargo de Responsabilidad
Este repositorio se desarrolla exclusivamente con fines educativos como parte de un curso universitario.
--------------------------------------------------

Siéntase libre de explorar, aprender y divertirse con este proyecto. Si tiene alguna pregunta o sugerencia, por favor, abra un issue en este repositorio.
