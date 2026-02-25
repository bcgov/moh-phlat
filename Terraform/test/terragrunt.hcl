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
  application_url = "https://dpzij59svcn5q.cloudfront.net"
  api_url = "https://9qgq8wv9x8.execute-api.ca-central-1.amazonaws.com"
  aurora_acu_min = 0.5
  aurora_acu_max = 3
  EOF
}
