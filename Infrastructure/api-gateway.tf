#data "aws_acm_certificate" "phlat_api_certificate" {
#  domain      = var.api_url
#  statuses    = ["ISSUED"]
#  most_recent = true
#}

module "api_gateway" {
  source  = "terraform-aws-modules/apigateway-v2/aws"
  version = "2.2.2"

  name                   = "${var.application}-http-api"
  description            = "HTTP API Gateway"
  protocol_type          = "HTTP"
  create_api_domain_name = false

  domain_name                              = var.api_url
  #domain_name_certificate_arn              = data.aws_acm_certificate.phlat_api_certificate.arn

  integrations = {
    "ANY /{proxy+}" = {
      connection_type    = "VPC_LINK"
      vpc_link           = "phlat-vpc"
      integration_uri    = data.aws_alb_listener.front_end.arn
      integration_type   = "HTTP_PROXY"
      integration_method = "ANY"  

      request_parameters = {
        "append:header.SourceIp"       = "$request.header.X-Forwarded-For"
        "append:header.clientSourceIP" = "$context.identity.sourceIp"
      }

      response_parameters = jsonencode([
        {
          status_code = 200
          mappings = {
            "overwrite:header.Content-Security-Policy" = "default-src 'self'; img-src 'self'; font-src 'self' https://fonts.gstatic.com/; connect-src 'self' https://*.hlth.gov.bc.ca/; style-src 'self' 'unsafe-inline' https://fonts.googleapis.com/; script-src 'self' 'unsafe-eval'; base-uri 'self'; form-action 'self'; frame-src 'self' https://*.hlth.gov.bc.ca/"
          }
        }
      ])

    }
  }

  vpc_links = {
    phlat-vpc = {
      name               = "${var.application}-vpc-link"
      security_group_ids = [data.aws_security_group.web.id]
      subnet_ids         = data.aws_subnets.web.ids
    }
  }
}
