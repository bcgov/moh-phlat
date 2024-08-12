include {
  path = find_in_parent_folders()
}

generate "stag_tfvars" {
  path              = "dev.auto.tfvars"
  if_exists         = "overwrite"
  disable_signature = true
  contents          = <<-EOF
  fargate_cpu = 512
  fargate_memory = 1024
  app_port = 21000
  fam_console_idp_name = "PROD-IDIR"
  application = "phlat-stag"
  application_url = "phlat-stg.hlth.gov.bc.ca"
  api_url = "phlatapi-stg.hlth.gov.bc.ca"
  aurora_acu_min = 0.5
  aurora_acu_max = 1
  EOF
}
