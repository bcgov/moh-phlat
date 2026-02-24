include {
  path = find_in_parent_folders()
}

generate "dev_tfvars" {
  path              = "dev.auto.tfvars"
  if_exists         = "overwrite"
  disable_signature = true
  contents          = <<-EOF
  fargate_cpu = 512
  fargate_memory = 1024
  app_port = 8088
  fam_console_idp_name = "DEV-IDIR"
  application = "phlat"
  application_url = "phlat-dev.hlth.gov.bc.ca"
  api_url = "phlatapi-dev.hlth.gov.bc.ca"
  aurora_acu_min = 0.5
  aurora_acu_max = 1
  EOF
}
