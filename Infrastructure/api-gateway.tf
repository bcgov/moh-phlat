#data "aws_acm_certificate" "phlat_api_certificate" {
#  domain      = var.api_url
#  statuses    = ["ISSUED"]
#  most_recent = true
#}

locals {
  response_headers = {
    "Content-Security-Policy"   = "'default-src 'self'; img-src 'self'; font-src 'self' https://fonts.gstatic.com/; connect-src 'self' https://*.hlth.gov.bc.ca/; style-src 'self' 'unsafe-inline' https://fonts.googleapis.com/; script-src 'self' 'unsafe-eval'; base-uri 'self'; form-action 'self'; frame-src 'self' https://*.hlth.gov.bc.ca/'"
    "Strict-Transport-Security" = "max-age=31536000"  # one year
    # Restricts access to geolocation, microphone, and camera features.
    "Permissions-Policy"        = "geolocation=(), microphone=(), camera=()"
  }
  # for the http codes, return above security headers
  http_status_codes = [200, 201, 204, 301, 302, 304, 400, 401, 403, 404, 429, 500, 502, 503, 504]
}

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
        for status_code in local.http_status_codes : {
          # generate this block for each status code
          status_code = status_code
          mappings = {
            for k, v in local.response_headers :
            # overwrite same header if coming from back end
            "overwrite:header.${k}" => v
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
