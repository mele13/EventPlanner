# Event Planner Platform
This repository contains the source code and documentation for an event planning platform. The platform aims to simplify the organization of events, offering a comprehensive solution for various occasions, from birthdays to weddings and corporate retreats.

**Please Note: This project is developed solely for a university project.**

## Table of Contents
- [Event Planner Platform](#event-planner-platform)
  - [Table of Contents](#table-of-contents)
  - [Technologies Used](#technologies-used)
  - [Prerequisites](#prerequisites)
  - [Project Overview](#project-overview)
    - [Customizable Modules](#customizable-modules)
    - [Budget Management](#budget-management)
    - [Collaboration Features](#collaboration-features)
    - [External Service Integration](#external-service-integration)
  - [Objectives](#objectives)
  - [Methodology](#methodology)
  - [Technology Stack](#technology-stack)
  - [Deployment](#deployment)
  - [Commands](#commands)
  - [Disclaimer](#disclaimer)

## Technologies Used
The project involves the use of the following technologies:
- Frontend: React Native (for web deployment and future mobile application)
- Backend: Node.js
- Database: PostgreSQL

## Prerequisites
Ensure that you have the following dependencies installed to run the project:
- Node.js
- Docker
- Docker Compose

## Project Overview
The Event Planner Platform addresses the complexities of event management and provides a user-friendly interface for planning and organizing events. Key features include:

### Customizable Modules
Highly customizable modules cater to different event types, allowing users to create unique and tailored experiences. Modules include invitations, music playlists, and menu management.

### Budget Management
An essential feature is the budget management tool, allowing users to track and control expenses associated with each event. Customized budget suggestions based on the event's nature and size provide valuable financial guidance.

### Collaboration Features
The platform enables effective collaboration among multiple organizers by assigning roles and specific tasks. Integrated chat and messaging functions facilitate seamless communication for coordinated and efficient planning.

### External Service Integration
The application stands out by integrating external service providers for event-related services. This feature assists users in choosing providers and services based on location and preferences.

## Objectives
The main objectives of the project are:
- Create a web platform for users to plan and manage various aspects of their events.
- Develop customizable modules to adapt to the specific needs of different event types.
- Integrate external providers for event-related services to facilitate user choices.
- Implement a budget management system to help users control expenses during event planning.
- Facilitate collaboration among organizers through role assignment and integrated messaging.
- Incorporate review options, allowing users to leave feedback on providers and services.

## Methodology
The development will follow an agile methodology, specifically utilizing the Scrum framework. The application will be built modularly, effectively separating frontend and backend logic to ensure flexibility and scalability. Cutting-edge technologies, including HTML, CSS, and JavaScript, will be employed for frontend development.

## Technology Stack
HTML, CSS, and JavaScript for frontend development; Node.js for backend; PostgreSQL for the database; JWT (JSON Web Token) or a similar approach for secure user authentication.

## Deployment
A careful evaluation will be conducted during the application deployment phase, selecting efficient web servers and cloud storage options based on rigorous criteria such as performance, scalability, and security.

## Commands
- To test the database connection:
  ```bash
  cd server
  ./node_modules/.bin/ts-node-dev ./src/sql/testConnection.ts
  ```
- To create a migration:
  ```bash
  cd server
  npm run typeorm migration:create src/sql/migrations/<migration_name>
  ```
- To run a migration:
  ```bash
  cd server
  npm run migrate:up
  ```

## Disclaimer
This project is developed exclusively for educational purposes as part of a university course. The information provided in this repository is based on a fictional scenario and does not represent a real-world application.

--------------------------------------------------

Feel free to explore, learn, and have fun with these tasks! If you have any questions or suggestions, please open an issue on the repository.
