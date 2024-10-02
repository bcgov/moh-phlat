include {
  path = find_in_parent_folders()
}

generate "prod_tfvars" {
  path              = "prod.auto.tfvars"
  if_exists         = "overwrite"
  disable_signature = true
  contents          = <<-EOF
  fargate_cpu = 512
  fargate_memory = 1024
  app_port = 8088
  fam_console_idp_name = "PROD-IDIR"
  application = "phlat"
  application_url = "phlat.hlth.gov.bc.ca"
  api_url = "phlatapi.hlth.gov.bc.ca"
  aurora_acu_min = 0.5
  aurora_acu_max = 3
  EOF
}
