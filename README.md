# Chatop API : Facilitez la communication entre locataires et propriétaires

Chatop Backend  est une application conçue pour simplifier la communication entre les locataires potentiels et les propriétaires d'appartements.

## Fonctionnalités 

- User Authentication (JWT)

- Gestion de compte : Consulter les informations relatives à son compte.

- Opér	ations de CRUD pour les locations

## Technologies utilisées

### Back-end

- Java 17
- Spring Boot 3.4.0
- Spring Data JPA
- Spring Security
- Spring Validation
- Spring Web
- Swagger
- MapStruct
- MySQL Connector
- JWT (Java Web Tokens)
- Maven

### Front-end

- Angular 
- TypeScript
- SCSS
- HTML
- Node

## Prérequis

Assurez-vous d'avoir les éléments suivants installés sur votre machine :

Back-end : 
- Java 17 ou version compatible.
- Apache Maven 
- MySQL 

Front-end 
- Node.js
- npm 
### Variable D'environnement
Pour des raison de securité certain élément seront en variable d'environement sont a enrister avant.
Windows (via CMD): setx NOM_VARIABLE "valeur" /M
Linux: echo 'export NOM_VARIABLE="valeur"' | sudo tee -a /etc/profile
Mac: sudo nano /etc/zshenv puis export NOM_VARIABLE="valeur" puis source /etc/zshenv

Les variables a définir:
SERVER_PORT: port pour le serveur
DATABASE_URL: Chemins vers la BDD. ne pas oublié jdbc:mysql:// devant, exemple jdbc:mysql://localhost:3306/Chatop
DATABASE_USER: Username ou identifiant de la BDD
DATABASE_PWD: Mot de passe de la BDD. Si il n'y a pas de mot de passe, pas besoin de créer de variable.
JWT_SECRET: La secret key pour générer des token.

### Installation du Back-end

Étape 1 : Cloner le dépôt

> git clone https://github.com/EvoSix/ChaTop-Backend.git

> cd ChaTop-Backend\chatop

Étape 2 : Configurer la base de données MySQL

Connectez-vous à MySQL en utilisant votre terminal ou un outil comme MySQL Workbench.

Créez une base de données nommée "chatoprental" ou au choix.

Si vous choisissez un nom qui vous convient, dans le fichier application.properties changer a la fin de l'url :spring.datasource.url=jdbc:mysql://localhost:3306/chatoprental

Vérifiez les identifiants de connexion dans le fichier application.properties :

>spring.datasource.username=your_username
> 
>spring.datasource.password=your_password

Étape 3 : Installer les dépendances

>mvn clean install

Étape 5 : Démarrer le serveur


Étape 5 : Vérifier l'installation en lançant le serveur à cette adresse http://localhost:3001


### Installation du Front-end

Étape 1 : Cloner le dépôt

>git clone https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring.git

>cd Developpez-le-back-end-en-utilisant-Java-et-Spring

Étape 2 : Installer les dépendances

> npm install


Étape 3 : Lancer l'application

>ng serve

Étape 4 : Accéder à l'application

Accédez à l'application à cette adresse :http://localhost:4200

## URL Swagger 

http://localhost:3001/swagger-ui/index.html

