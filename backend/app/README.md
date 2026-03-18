

## Backend Local setup
Following environment variables are required to run the application locally in .env file. The .env file should be<br>
placed in the backend/app/ folder. Ask the team for the values of the following environment variables.<br>
These values can be retrieved by login in to the cloud dev account and checking the appropriate secrets in the secret<br>
manager. The secret names are mentioned in the description below.

| Name                       | Description                                                                                                              |
|----------------------------|--------------------------------------------------------------------------------------------------------------------------|
| KEYCLOAK_PROVIDER_URL      | The URL to KEYCLOAK AUTH. Retrieve the value from secret 'phlat_keycloak_provider_url'                                   |
| KEYCLOAK_CLIENT_ID_WEB     | Client ID for web application. Retrieve the value from secret 'phlat_keycloak_client_id_web'                             |
| PLR_KEYCLOAK_CLIENT_ID     | Client ID to call PLR API. Retrieve the value from secret 'phlat_keycloak_client_id'                                     |
| DB_USERNAME                | Database username. Refer to `backend/app/compose.yaml`                                                                   |
| DB_PASSWORD                | Database password. Refer to `backend/app/compose.yaml`                                                                   |
| DB_PORT                    | Database port. Refer to `backend/app/compose.yaml`                                                                       |
| DB_DATABASE                | Database name. Refer to `backend/app/compose.yaml`                                                                       |
| DB_HOST                    | name of the DB container running Postgress DB. Should match with<br/>the name given in compose.yml file (phlat_postgres) |
| SPRING_PROFILES_ACTIVE     | For local provide value 'local'                                                                                          |
| PLR_KEYCLOAK_PROVIDER_URL  | PLR Keycloak provider URL. Retrieve the value from secret 'phlat_plr_keycloak_provider_url'                              |
| PLR_KEYCLOAK_CLIENT_SECRET | PLR Keycloak client secret. Retrieve the value from secret 'phlat_keycloak_client_secret'                                |
| PLR_API_HOST               | PLR API host. Retrieve the value from the secret 'phlat_plr_api_host'                                                    |
| PLR_TLS_TRUST_CERT         | PLR Health Registries Certificate                                                                                        |
| ADDRESS_DOCTOR_KEYSTORE    | PLR Address Doctor Keystore                                                                                              |

## Pre-requisites
1. Put the value of the PLR trust certificate in .env(ENV var) file in the `backend/app/` folder. The certificate is required to call the PLR API. <br>
   The name of the certificate should be `PLR_TLS_TRUST_CERT` or as configured in Dockerfile.
2. Put the value of the AddressDoctor Keystore in same .env(ENV var) file in the `backend/app/` folder. The certificate is required to call the AddressDoctor API. <br>
   The name of the certificate should be `ADDRESS_DOCTOR_KEYSTORE` or as configured in Dockerfile.
3. Encode the PLR trust certificate in base64 format to be used as input to build docker image if you are running the application in Docker.
4. Install the Docker/Rancher Desktop and start the Docker service.
<br><br>
**Note:** When you run the application, or follow the instructions for running it in Docker, a database named phlat, with
the user phlat, and a host/container named phlat_postgres will be created. These details are required to connect to the
database using the pgAdmin client, which is accessible from another container at http://localhost:5050/. 

## Running application
1. Set the above environment variables.
2. The run/compose command will start Postgres DB container and Postgres Admin container which can be opened on [http://localhost:5050](http://localhost:5050).
3. Locate the `compose.yaml` file.
   ```yaml
   file: "./backend/app/compose.yaml"
   ```
   
## Using Docker
It is recommended to run the application in Docker locally also to avoid any issues in cloud deployment, 
especially when any configuration related to Docker or application properties are changed.

### Steps
Method A.
 1. Put the environment variables in .env file and run the following command from where the `compose.yml` is located.
    ```sh
    docker-compose up -d
    ```

Method B.
1. Start the postgres DB and Postgres Admin containers if not running already using the following command.<br>
This command will also create a network if not existing already, to communicate between the containers. 
The network name follows the format: "folder/project name where the compose file is located"_common-network (in our case it should be app_common-network)
as compose file is located in the app folder. Remove the 'phlat' container section from `compose.yaml` file.
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
   docker run -d --name phlat --network="app_common-network" -p 8088:8088 --env-file "<name of the env file containing above env variables>" plr/phlat:1.0
   ```
5. The Backend application will be available at [http://localhost:8088/](http://localhost:8088/) and swagger documentation at [http://localhost:8088/swagger-ui/index.html#](http://localhost:8088/swagger-ui/index.html#).
