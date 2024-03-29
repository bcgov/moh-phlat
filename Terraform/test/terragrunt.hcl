include {
  path = find_in_parent_folders()
}

generate "test_tfvars" {
  path              = "dev.auto.tfvars"
  if_exists         = "overwrite"
  disable_signature = true
  contents          = <<-EOF
  fargate_cpu = 512
  fargate_memory = 1024
  app_port = 8088
  fam_console_idp_name = "TEST-IDIR"
  application = "phlat"
  application_url = "phlat-test.hlth.gov.bc.ca"
  api_url = "phlatapi-test.hlth.gov.bc.ca"
  aurora_acu_min = 0.5
  aurora_acu_max = 3
  EOF
}