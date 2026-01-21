locals {
  s3_origin_id = "phlat-${var.target_env}"
}

provider "aws" {
  alias  = "us-east-1"
  region = "us-east-1"
}

#data "aws_acm_certificate" "phlat_certificate" {
#  provider    = aws.us-east-1
#  domain      = var.application_url
#  statuses    = ["ISSUED"]
#  most_recent = true
#}

data "aws_cloudfront_cache_policy" "Managed-CachingOptimized" {
  name = "Managed-CachingOptimized"
}

resource "aws_cloudfront_origin_access_control" "phlat" {
  name                              = "phlat-${var.target_env}"
  origin_access_control_origin_type = "s3"
  signing_behavior                  = "always"
  signing_protocol                  = "sigv4"
}

resource "aws_cloudfront_response_headers_policy" "response_security_headers" {
  name    = "Response-Security-Headers"
  comment = "Defines response headers policy for CloudFront with enhanced security headers"
  security_headers_config {
    content_security_policy {
      #Override with this if the origin is setting the same header.
      override                = true
      content_security_policy = "default-src 'self'; img-src 'self'; font-src 'self' https://fonts.gstatic.com/; connect-src 'self' https://*.hlth.gov.bc.ca/; style-src 'self' 'unsafe-inline' https://fonts.googleapis.com/; script-src 'self' 'unsafe-eval'; base-uri 'self'; form-action 'self'; frame-src 'self' https://*.hlth.gov.bc.ca/"
    }

    strict_transport_security {
      access_control_max_age_sec = 31536000 // 1 year
      override = true
    }
  }

  custom_headers_config {
    items {
      header = "Permissions-Policy"
      # Restricts access to geolocation, microphone, and camera features.
      value    = "geolocation=(), microphone=(), camera=()"
      override = true
    }
  }

}

resource "aws_cloudfront_distribution" "s3_distribution" {
  origin {
    domain_name              = aws_s3_bucket.static.bucket_regional_domain_name
    origin_id                = local.s3_origin_id
    origin_access_control_id = aws_cloudfront_origin_access_control.phlat.id
    origin_shield {
      enabled              = true
      origin_shield_region = "us-east-1"
    }
  }
  default_root_object = "index.html"
  enabled             = true
  is_ipv6_enabled     = true
  aliases             = [var.application_url]

  default_cache_behavior {
    target_origin_id       = local.s3_origin_id
    allowed_methods        = ["GET", "HEAD"]
    cached_methods         = ["GET", "HEAD"]
    viewer_protocol_policy = "redirect-to-https"
    cache_policy_id        = data.aws_cloudfront_cache_policy.Managed-CachingOptimized.id
    compress               = true
    min_ttl     = 0
    default_ttl = 3600
    max_ttl     = 86400
    # associate CSP header policy
    response_headers_policy_id = aws_cloudfront_response_headers_policy.response_security_headers.id
  }

  ordered_cache_behavior {
    path_pattern           = "/app*"
    target_origin_id       = aws_s3_bucket.static.id
    allowed_methods        = ["GET", "HEAD"]
    cached_methods         = ["GET", "HEAD"]
    viewer_protocol_policy = "redirect-to-https"
    cache_policy_id        = data.aws_cloudfront_cache_policy.Managed-CachingOptimized.id
    compress               = true
    # associate CSP header policy
    response_headers_policy_id = aws_cloudfront_response_headers_policy.response_security_headers.id
  }

  price_class = "PriceClass_100"
  custom_error_response {
    error_code         = 404
    response_page_path = "/app/index.html"
    response_code      = 200
  }
  restrictions {
    geo_restriction {
      restriction_type = "whitelist"
      locations        = ["CA"]
    }
  }

  viewer_certificate {
     cloudfront_default_certificate = true
#     acm_certificate_arn      = data.aws_acm_certificate.phlat_certificate.arn
     minimum_protocol_version = "TLSv1.2_2021"
     ssl_support_method       = "sni-only"
  }
}

# to get the Cloud front URL if domain/alias is not configured
output "cloudfront_domain_name" {
  value = aws_cloudfront_distribution.s3_distribution.domain_name
}
