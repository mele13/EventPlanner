# Event Planner - Event Management Application

Event Planner is a platform designed to simplify event planning. 

## Table of Contents
- [Description](#description)
- [User Manual](#user-manual)
- [Getting Started](#getting-started)
- [Project Dependencies](#project-dependencies)
- [Application Ports](#application-ports)
- [Basic Docker commands](#basic-docker-commands)
- [Contributing](#contributing)
- [Disclaimer](#disclaimer)

## Description
Event Planner is a functional prototype designed to provide a versatile solution for a wide variety of event planning needs, facilitating decision-making processes. The platform aims to centralize event management, eliminating the need to consult multiple individual platforms.


## User Manual
This manual will guide you through the system's functionalities and provide a clear vision of how to make the most of this tool.

Users may:

** If the user is not registered
- Filter events by year, name, or type of event.
- View free access events created by other users.
- Register to access specific content according to their needs.

![user-authenticated](https://github.com/mele13/EventPlanner/assets/87198060/b1316e72-8eb3-4862-b02d-be7de1508bb9)


** If the user is registered
- Create and manage multiple events from a single dashboard.
- Customize information and options according to the type of event.
- Analyze detailed statistics for each event.

![git-inicio-con-auth](https://github.com/mele13/EventPlanner/assets/87198060/29eaa022-a7b4-4aa2-91f1-5f7e78542c4e)


1. Login and Sign up
To access the full functionalities of Event Planner, users must register.

Steps for registration:
- Access registration from the navigation bar.
- Fill out the form with your personal details.
- Verify your email.

![git-sign-up](https://github.com/mele13/EventPlanner/assets/87198060/60e26ed0-c7b2-4720-9a34-643bd952b4ad)


Steps to log in:
- Access login from the navigation bar.
- Enter your email and password.
- Log in.

![git-login](https://github.com/mele13/EventPlanner/assets/87198060/a6bbe38b-2257-49e1-99ec-a32eed685ce2)


2. Event Creation
Event creation is limited to registered users.

Steps to create an event:
- Select the type of event.
- Enter the general details of the event (name, description, dates, etc.).
- Configure the event location.
- Manage financial aspects (ticket price, refund policies, etc.).
- Add frequently asked questions.
- Configure the event menu.
- Confirm the details and select the template and color palette.

https://github.com/mele13/EventPlanner/assets/87198060/ea55fd19-951b-49c0-a5b4-b89055611bf8


3. Event Management
Users can manage their created events from a centralized dashboard.

Dashboard functionalities:
- View information about the events a user is associated with.

![git-my-events](https://github.com/mele13/EventPlanner/assets/87198060/31897818-1ad4-407a-a907-868c83ff4186)


Event management functionalities:
- Update dates and configurations.
- View event and participant statistics.
- Assign roles and permissions to other users.

![git-user-management](https://github.com/mele13/EventPlanner/assets/87198060/cc01cc8b-3eab-4adc-bbe3-62f033496e2d)


4. Languages
Event Planner offers multilingual support with a total of five translations, allowing users to choose from various available languages.

Steps to change the language:
- Access the language selector in the navigation bar.
- Select the desired language.
- The interface will automatically update to the selected language.

![language-change](https://github.com/mele13/EventPlanner/assets/87198060/f240eb88-74fc-4f07-b39c-315658a3d824)


5. Logout
You can log out from the navigation bar as long as the user is registered and logged in.

![git-navbar-logout](https://github.com/mele13/EventPlanner/assets/87198060/2b624700-ebb9-4e17-b0eb-4c1edf025f94)

--------------------------------------------------

This manual provides a detailed guide to using Event Planner, ensuring that users can make the most of all the platform's functionalities and features.


## Getting Started
To get started with the project, follow these steps:
1. Clone the repository:
   ```bash
   https://github.com/mele13/EventPlanner
   ```
2. Open the project in a suitable development environment or web server.
3. Begin working! You can start by navigating to the project folders.


## Project Dependencies
This project utilizes Docker to manage its dependencies, simplifying the setup and deployment process. The following key technologies and services are used within Docker containers:

- Vue.js: A progressive JavaScript framework used for building interactive user interfaces and single-page applications (SPAs).

- Vite: A build tool that provides a fast development environment and efficient build process for modern web applications, particularly those using Vue.js.

- Nginx: Used as a web server and reverse proxy to handle requests and serve static files efficiently.

- Spring Boot: A framework for building Java-based backend services and APIs. It simplifies the development of production-ready applications.

- PostgreSQL: A powerful, open-source relational database management system. It handles the storage and management of event-related data.

- MongoDB: A NoSQL database used for storing flexible, document-oriented data. It's ideal for handling complex, hierarchical data structures.


## Application Ports
- Front-end application: http://localhost:5173/
- API: http://localhost:3004/


## Basic Docker commands
Here are some basic Docker commands that everyone should know:
- Build all the necessary Docker images:
    ```
    docker compose build
    ```
- (Re)Start all the Docker containers:
    ```
    docker compose up -d
    ```
- Rebuild the image before starting the container:
    ```
    docker compose up --build -d
    ```
    This command can be slower than docker compose up -d because it rebuilds the image every time. However, it can be helpful if you have made changes to the Dockerfile or if the image was not built correctly the first time.
- Stop the application and remove all the Docker containers:
    ```
    docker compose down
    ```
- Opens a terminal inside the container with the specified name:
    ```
    docker exec -it <container_name> 
    ```
- Lists all running services defined in the docker compose file:
    ```
    docker compose ps
    ```
- List all the Docker networks on the system:
    ```
    docker network ls
    ```
- Stop all the Docker containers immediately:
    ```
    docker compose kill
    ```
- Remove all unused Docker networks:
    ```
    docker network prune
    ```
- List all running containers:
    ```
    docker ps
    ```
- Display the name of each container and its associated IP address:
    ```
    docker inspect -f '{{.Name}} - {{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(docker ps -aq)
    ```
** These are just a few examples of Docker commands. Check the documentation for more information: https://docs.docker.com/engine/reference/commandline/ (Docker CLI).


## Contributing
If you would like to contribute to this repository, please follow these steps:

1. Fork this repository.
2. Create a new branch for your changes: `git checkout -b feature/new-feature`.
3. Make your changes and commit them: `git commit -m "Add new feature"`.
4. Push your changes to your forked repository: `git push origin feature/new-feature`.
5. Create a pull request explaining your changes.

Enjoy exploring the project!

## Disclaimer
This repository is developed exclusively for educational purposes as part of a university course. 

--------------------------------------------------

Feel free to explore, learn, and have fun with this project! If you have any questions or suggestions, please open an issue on this repository.


