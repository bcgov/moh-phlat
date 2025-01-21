resource "aws_secretsmanager_secret" "phlat_proxy_user" {
  name = "${var.application}_user"
}

resource "aws_secretsmanager_secret" "phlat_db_database" {
  name = "${var.application}_db_database"
}

resource "aws_secretsmanager_secret" "phlat_keycloak_provider_url" {
  #This Keycloak URL facilitates credential exchange for the Frontend (FE) and token verification for the Backend (BE),
  # validating tokens sent in the API header by the FE or any other client
  name = "${var.application}_keycloak_provider_url"
}

resource "aws_secretsmanager_secret" "phlat_plr_keycloak_provider_url" {
  # PLR keycloak URL, used to retrieve token for calling PLR API
  name = "${var.application}_plr_keycloak_provider_url"
}

resource "aws_secretsmanager_secret" "phlat_keycloak_client_id" {
  # PLR Keycloak Client ID: Used to retrieve tokens for authentication and authorization when calling the PLR API
  name = "${var.application}_keycloak_client_id"
}

resource "aws_secretsmanager_secret" "phlat_keycloak_client_secret" {
  # used to retrieve token for calling PLR API
  name = "${var.application}_keycloak_client_secret"
}

resource "aws_secretsmanager_secret" "phlat_keycloak_client_id_web" {
  # used by FE for Authentication
  name = "${var.application}_keycloak_client_id_web"
}

resource "aws_secretsmanager_secret" "phlat_plr_api_host" {
  name = "${var.application}_plr_api_host"
}

resource "aws_secretsmanager_secret" "phlat_address_doctor_host" {
  name = "${var.application}_address_doctor_host"
}

resource "aws_secretsmanager_secret" "phlat_address_doctor_certificates" {
  name = "${var.application}_address_doctor_certificates"
}

resource "aws_secretsmanager_secret_version" "phlat_db_database" {
  secret_id     = aws_secretsmanager_secret.phlat_db_database.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_keycloak_provider_url" {
  secret_id     = aws_secretsmanager_secret.phlat_keycloak_provider_url.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_plr_keycloak_provider_url" {
  secret_id     = aws_secretsmanager_secret.phlat_plr_keycloak_provider_url.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_keycloak_client_id" {
  secret_id     = aws_secretsmanager_secret.phlat_keycloak_client_id.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_keycloak_client_secret" {
  secret_id     = aws_secretsmanager_secret.phlat_keycloak_client_secret.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_keycloak_client_id_web" {
  secret_id     = aws_secretsmanager_secret.phlat_keycloak_client_id_web.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_plr_api_host" {
  secret_id     = aws_secretsmanager_secret.phlat_plr_api_host.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_address_doctor_host" {
  secret_id     = aws_secretsmanager_secret.phlat_address_doctor_host.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "rds_credentials" {
  secret_id     = aws_secretsmanager_secret.phlat_proxy_user.id
  secret_string = <<EOF
{
  "username": "phlat",
  "password": "changeme",
  "engine": "${data.aws_rds_engine_version.postgresql.version}",
  "host": "${module.aurora_postgresql_v2.cluster_endpoint}",
  "port": ${module.aurora_postgresql_v2.cluster_port},
  "dbClusterIdentifier": "${module.aurora_postgresql_v2.cluster_id}"
}
EOF
  lifecycle {
    ignore_changes = [secret_string]
  }
}

resource "aws_secretsmanager_secret_version" "phlat_address_doctor_certificates" {
  secret_id     = aws_secretsmanager_secret.phlat_address_doctor_certificates.id
  secret_string = <<EOF
{
  "truststore_password": "changeme",
  "keystore_password": "changeme",
  "key_password": "changeme"
}
EOF
  lifecycle {
    ignore_changes = [secret_string]
  }
}
