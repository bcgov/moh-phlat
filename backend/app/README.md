## About
The PHLAT tool is a new tool used to load Healthcare Delivery Site (HDS) data into the PLR.<br> 
The backend application for PHLAT provides APIs for the frontend application to carry out the business logic.<br>
The technical stack includes Spring Boot, Java, Docker, and PostgreSQL.

## Local setup
Following environment variables are required to run the application locally:

| Name                       | Description                                   | Example                             |
|----------------------------|-----------------------------------------------|-------------------------------------|
| KEYCLOAK_PROVIDER_URL      | The URL to KEYCLOAK AUTH                      |
| KEYCLOAK_CLIENT_ID_WEB     | Client ID for web application                 |
| PLR_KEYCLOAK_CLIENT_ID     | Client ID to call PLR API                     |
| DB_USERNAME                | Database username                             | refer to `backend/app/compose.yaml` |
| DB_PASSWORD                | Database password                             | refer to `backend/app/compose.yaml` |
| DB_PORT                    | Database port                                 | refer to `backend/app/compose.yaml` |
| DB_DATABASE                | Database name                                 | refer to `backend/app/compose.yaml` |
| DB_HOST                    | name of the DB container running Postgress DB | my_postgress_db                     |
| SPRING_PROFILES_ACTIVE     | For local provide value 'local'               | local                               |
| PLR_KEYCLOAK_PROVIDER_URL  | PLR Keycloak provider URL                     |
| PLR_KEYCLOAK_CLIENT_SECRET | PLR Keycloak client secret                    |
| PLR_API_HOST               | PLR API host                                  | https://abc.com                     |

## Pre-requisites
1. Keep the PLR trust certificate in the `backend/app/src/main/resources` folder. The certificate is required to call the PLR API.
2. Encode the PLR trust certificate in base64 format to be used as input to build docker image if you are running the application in Docker.
3. Install the Docker Desktop and start the Docker service.

## Running using the IDE
1. For running through your favorite editor Visual Studio Code or IntelliJ, you can run the application by running the main class `PhlatApplication`.
2. Set the above environment variables in the IDE run configuration.
3. The run command will start Postgres DB container and Postgres Admin container which can be opened on [http://localhost:5050](http://localhost:5050).
4. Adjust the path to the `compose.yaml` in the `application-local.yml` file if IDE complains about the path to the `compose.yaml` file.
   ```yaml
   file: "./backend/app/compose.yaml"
   ```
5. The Backend application will be available at [http://localhost:8088/](http://localhost:8088/) and swagger documentation at [http://localhost:8088/swagger-ui/index.html#](http://localhost:8088/swagger-ui/index.html#).

## Running the Application using Spring Boot Maven Plugin
1. Make sure the required environment variables are set in the command window where you are running the application.
2. Adjust the path to the `compose.yaml` in the `application-local.yml` file if the command complains about the path to the `compose.yaml` file if the plugin cannot find it.
   ```yaml
   file: "./compose.yaml"
   ```
3. Switch to `<project root>/backend/app` dir and execute the following command to run the application:
   ```sh
   mvn spring-boot:run
   ```
4. The command will start Postgres DB container and Postgres Admin container which can be opened on [http://localhost:5050](http://localhost:5050).
5. The Backend application will be available at [http://localhost:8088/](http://localhost:8088/) and swagger documentation at [http://localhost:8088/swagger-ui/index.html#](http://localhost:8088/swagger-ui/index.html#).

## Using Docker
It is recommended to run the application in Docker locally also to avoid any issues in cloud deployment, 
especially when any configuration related to Docker or application properties are changed.

### Steps
1. Build the Docker image:
   1. Execute the following command on command line to build the Docker image:
      ```sh
      docker build -t plr/phlat:1.0 -f "<absolute path to your docker file>" --build-arg PLR_TLS_TRUST_CERT=<base 64 encoded PLR trust certificate> " absolute path to <project root>/backend/app"
      ```
   2. Run the Docker image using the following command:
      ```sh
      docker run -d --name phlat --network=<name of the network> -p 8088:8088 --env-file "<name of the env file containing above env variables>" plr/phlat:1.0
      ```
   3. The Backend application will be available at [http://localhost:8088/](http://localhost:8088/) and swagger documentation at [http://localhost:8088/swagger-ui/index.html#](http://localhost:8088/swagger-ui/index.html#).
