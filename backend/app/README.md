

## Backend Local setup
Following environment variables are required to run the application locally. Ask the team for the values of the following<br> 
environment variables. These values can be retrieved by login in to the cloud dev account and checking the appropriate<br>
secrets in the secret manager. The secret names are mentioned in the description below.

| Name                       | Description                                                                                                              | Example                             |
|----------------------------|--------------------------------------------------------------------------------------------------------------------------|-------------------------------------|
| KEYCLOAK_PROVIDER_URL      | The URL to KEYCLOAK AUTH. Retrieve the value from secret 'phlat_keycloak_provider_url'                                   |
| KEYCLOAK_CLIENT_ID_WEB     | Client ID for web application. Retrieve the value from secret 'phlat_keycloak_client_id_web'                             |
| PLR_KEYCLOAK_CLIENT_ID     | Client ID to call PLR API. Retrieve the value from secret 'phlat_keycloak_client_id'                                     |
| DB_USERNAME                | Database username                                                                                                        | refer to `backend/app/compose.yaml` |
| DB_PASSWORD                | Database password                                                                                                        | refer to `backend/app/compose.yaml` |
| DB_PORT                    | Database port                                                                                                            | refer to `backend/app/compose.yaml` |
| DB_DATABASE                | Database name                                                                                                            | refer to `backend/app/compose.yaml` |
| DB_HOST                    | name of the DB container running Postgress DB. Should match with<br/>the name given in compose.yml file (phlat_postgres) | my_postgress_db                     |
| SPRING_PROFILES_ACTIVE     | For local provide value 'local'                                                                                          | local                               |
| PLR_KEYCLOAK_PROVIDER_URL  | PLR Keycloak provider URL. Retrieve the value from secret 'phlat_plr_keycloak_provider_url'                              |
| PLR_KEYCLOAK_CLIENT_SECRET | PLR Keycloak client secret. Retrieve the value from secret 'phlat_keycloak_client_secret'                                |
| PLR_API_HOST               | PLR API host. Retrieve the value from the secret 'phlat_plr_api_host'                                                    | https://abc.com                     |

## Pre-requisites
1. Keep the PLR trust certificate in the `backend/app/src/main/resources` folder. The certificate is required to call the PLR API. <br>
The name of the certificate should be `plr_tls_trust.cert` or as configured in application.yml file.
2. Encode the PLR trust certificate in base64 format to be used as input to build docker image if you are running the application in Docker.
3. Install the Docker Desktop and start the Docker service.

## Running using the IDE
1. For running through your favorite editor Visual Studio Code or IntelliJ, you can run the application by running the main class `BackendApplication`.
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
1. Start the postgres DB and Postgres Admin containers if not running already using the following command.<br>
This command will also create a network if not existing already, to communicate between the containers. The name of the network<br>
is "folder/project name where compose file is present"_common-network (app_common-network).
   ```sh
   docker-compose -f "<absolute/relative path to your compose file>" up -d
   ```
2. Check the network name by executing the following command:
   ```sh
   docker network ls
   ```
   You must see the network app_common-network in the list.

3. Build the Docker image. Execute the following command on command line to build the Docker image. Giving absolute path works from any directory you can use relative path also.
      ```sh
      docker build -t plr/phlat:1.0 -f "<absolute/relative path to your docker file>" --build-arg PLR_TLS_TRUST_CERT=<base 64 encoded PLR trust certificate> " absolute/relative path to <project root>/backend/app"
      ```
4. Run the Docker image using the following command:
   ```sh
   docker run -d --name phlat --network="app_common_network" -p 8088:8088 --env-file "<name of the env file containing above env variables>" plr/phlat:1.0
   ```
5. The Backend application will be available at [http://localhost:8088/](http://localhost:8088/) and swagger documentation at [http://localhost:8088/swagger-ui/index.html#](http://localhost:8088/swagger-ui/index.html#).
