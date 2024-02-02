resource "aws_secretsmanager_secret" "phlat_proxy_user" {
  name = "${var.application}_user"
}

resource "aws_secretsmanager_secret" "phlat_application_port" {
  name = "${var.application}_application_port"
}

resource "aws_secretsmanager_secret" "phlat_aws_env" {
  name = "${var.application}_aws_env"
}

resource "aws_secretsmanager_secret" "phlat_bc_direction_api_key" {
  name = "${var.application}_bc_direction_api_key"
}

resource "aws_secretsmanager_secret" "phlat_close_location_cron_time" {
  name = "${var.application}_close_location_cron_time"
}

resource "aws_secretsmanager_secret" "phlat_cron_job_names" {
  name = "${var.application}_cron_job_names"
}

resource "aws_secretsmanager_secret" "phlat_db_database" {
  name = "${var.application}_db_database"
}

resource "aws_secretsmanager_secret" "phlat_db_schema" {
  name = "${var.application}_db_schema"
}

resource "aws_secretsmanager_secret" "phlat_email_generic_notification_template_id" {
  name = "${var.application}_email_generic_notification_template_id"
}

resource "aws_secretsmanager_secret" "phlat_enable_subscription" {
  name = "${var.application}_enable_subscription"
}

resource "aws_secretsmanager_secret" "phlat_enable_text_messages" {
  name = "${var.application}_enable_text_messages"
}

resource "aws_secretsmanager_secret" "phlat_ga_key" {
  name = "${var.application}_ga_key"
}

resource "aws_secretsmanager_secret" "phlat_heapsnapshot_enabled" {
  name = "${var.application}_heapsnapshot_enabled"
}

resource "aws_secretsmanager_secret" "phlat_keycloak_auth_url" {
  name = "${var.application}_keycloak_auth_url"
}

resource "aws_secretsmanager_secret" "phlat_keycloak_client" {
  name = "${var.application}_keycloak_client"
}

resource "aws_secretsmanager_secret" "phlat_keycloak_data_auth_url" {
  name = "${var.application}_keycloak_data_auth_url"
}

resource "aws_secretsmanager_secret" "phlat_keycloak_data_client" {
  name = "${var.application}_keycloak_data_client"
}

resource "aws_secretsmanager_secret" "phlat_keycloak_data_realm" {
  name = "${var.application}_keycloak_data_realm"
}

resource "aws_secretsmanager_secret" "phlat_keycloak_port" {
  name = "${var.application}_keycloak_port"
}

resource "aws_secretsmanager_secret" "phlat_keycloak_realm" {
  name = "${var.application}_keycloak_realm"
}

resource "aws_secretsmanager_secret" "phlat_load_certs" {
  name = "${var.application}_load_certs"
}

resource "aws_secretsmanager_secret" "phlat_logs_path" {
  name = "${var.application}_logs_path"
}

resource "aws_secretsmanager_secret" "phlat_map_box_access_token" {
  name = "${var.application}_map_box_access_token"
}

resource "aws_secretsmanager_secret" "phlat_noi_expiry_date" {
  name = "${var.application}_noi_expiry_date"
}

resource "aws_secretsmanager_secret" "phlat_sales_report_end_date" {
  name = "${var.application}_sales_report_end_date"
}

resource "aws_secretsmanager_secret" "phlat_text_api_key" {
  name = "${var.application}_text_api_key"
}

resource "aws_secretsmanager_secret" "phlat_text_generic_notification_template_id" {
  name = "${var.application}_text_generic_notification_template_id"
}

resource "aws_secretsmanager_secret" "phlat_vaping_notification_email" {
  name = "${var.application}_vaping_notification_email"
}

resource "aws_secretsmanager_secret_version" "phlat_application_port" {
  secret_id     = aws_secretsmanager_secret.phlat_application_port.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_aws_env" {
  secret_id     = aws_secretsmanager_secret.phlat_aws_env.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_bc_direction_api_key" {
  secret_id     = aws_secretsmanager_secret.phlat_bc_direction_api_key.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_close_location_cron_time" {
  secret_id     = aws_secretsmanager_secret.phlat_close_location_cron_time.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_cron_job_names" {
  secret_id     = aws_secretsmanager_secret.phlat_cron_job_names.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_db_database" {
  secret_id     = aws_secretsmanager_secret.phlat_db_database.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_db_schema" {
  secret_id     = aws_secretsmanager_secret.phlat_db_schema.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_email_generic_notification_template_id" {
  secret_id     = aws_secretsmanager_secret.phlat_email_generic_notification_template_id.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_enable_subscription" {
  secret_id     = aws_secretsmanager_secret.phlat_enable_subscription.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_enable_text_messages" {
  secret_id     = aws_secretsmanager_secret.phlat_enable_text_messages.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_ga_key" {
  secret_id     = aws_secretsmanager_secret.phlat_ga_key.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_heapsnapshot_enabled" {
  secret_id     = aws_secretsmanager_secret.phlat_heapsnapshot_enabled.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_keycloak_auth_url" {
  secret_id     = aws_secretsmanager_secret.phlat_keycloak_auth_url.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_keycloak_client" {
  secret_id     = aws_secretsmanager_secret.phlat_keycloak_client.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_keycloak_data_auth_url" {
  secret_id     = aws_secretsmanager_secret.phlat_keycloak_data_auth_url.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_keycloak_data_client" {
  secret_id     = aws_secretsmanager_secret.phlat_keycloak_data_client.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_keycloak_data_realm" {
  secret_id     = aws_secretsmanager_secret.phlat_keycloak_data_realm.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_keycloak_port" {
  secret_id     = aws_secretsmanager_secret.phlat_keycloak_port.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_keycloak_realm" {
  secret_id     = aws_secretsmanager_secret.phlat_keycloak_realm.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_load_certs" {
  secret_id     = aws_secretsmanager_secret.phlat_load_certs.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_logs_path" {
  secret_id     = aws_secretsmanager_secret.phlat_logs_path.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_map_box_access_token" {
  secret_id     = aws_secretsmanager_secret.phlat_map_box_access_token.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_noi_expiry_date" {
  secret_id     = aws_secretsmanager_secret.phlat_noi_expiry_date.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_sales_report_end_date" {
  secret_id     = aws_secretsmanager_secret.phlat_sales_report_end_date.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_text_api_key" {
  secret_id     = aws_secretsmanager_secret.phlat_text_api_key.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_text_generic_notification_template_id" {
  secret_id     = aws_secretsmanager_secret.phlat_text_generic_notification_template_id.id
  secret_string = "changeme"
}

resource "aws_secretsmanager_secret_version" "phlat_vaping_notification_email" {
  secret_id     = aws_secretsmanager_secret.phlat_vaping_notification_email.id
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
