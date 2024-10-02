resource "aws_ecs_cluster" "phlat_cluster" {
  name = "${var.application}_cluster"
}

resource "aws_ecs_cluster_capacity_providers" "phlat_cluster" {
  cluster_name       = aws_ecs_cluster.phlat_cluster.name
  capacity_providers = ["FARGATE"]

  default_capacity_provider_strategy {
    capacity_provider = "FARGATE"
    weight            = 100

  }
}

resource "aws_ecs_task_definition" "phlat_td" {
  family                   = "${var.application}-${var.target_env}-task"
  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn
  task_role_arn            = aws_iam_role.ecs_task_execution_role.arn
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = var.fargate_cpu
  memory                   = var.fargate_memory
  tags                     = local.common_tags
  container_definitions    = jsonencode([
    {
      essential    = true
      name = "${var.application}-${var.target_env}-definition"
      #change to variable to env. for GH Actions
      image        = "${data.aws_caller_identity.current.account_id}.dkr.ecr.ca-central-1.amazonaws.com/phlat:latest"
      cpu          = var.fargate_cpu
      memory       = var.fargate_memory
      networkMode  = "awsvpc"
      portMappings = [
        {
          protocol      = "tcp"
          containerPort = var.app_port
          hostPort      = var.app_port
        }
      ]
      secrets = [
        {
          name      = "DB_USERNAME",
          valueFrom = "${aws_secretsmanager_secret_version.rds_credentials.arn}:username::"
        },
        {
          name      = "DB_PASSWORD"
          valueFrom = "${aws_secretsmanager_secret_version.rds_credentials.arn}:password::"
        },
        {
          name      = "DB_HOST"
          valueFrom = "${aws_secretsmanager_secret_version.rds_credentials.arn}:host::"
        },
        {
          name      = "DB_PORT"
          valueFrom = "${aws_secretsmanager_secret_version.rds_credentials.arn}:port::"
        },
        {
          name      = "DB_DATABASE"
          valueFrom = aws_secretsmanager_secret_version.phlat_db_database.arn
        },
        {
          name      = "KEYCLOAK_PROVIDER_URL"
          valueFrom = aws_secretsmanager_secret_version.phlat_keycloak_provider_url.arn
        },
        {
          name      = "PLR_KEYCLOAK_PROVIDER_URL"
          valueFrom = aws_secretsmanager_secret_version.phlat_plr_keycloak_provider_url.arn
        },

        {
          name      = "PLR_KEYCLOAK_CLIENT_ID"
          valueFrom = aws_secretsmanager_secret_version.phlat_keycloak_client_id.arn
        },
        {
          name      = "PLR_KEYCLOAK_CLIENT_SECRET"
          valueFrom = aws_secretsmanager_secret_version.phlat_keycloak_client_secret.arn
        },
        {
          name      = "KEYCLOAK_CLIENT_ID_WEB"
          valueFrom = aws_secretsmanager_secret_version.phlat_keycloak_client_id_web.arn
        },
        {
          name      = "PLR_API_HOST"
          valueFrom = aws_secretsmanager_secret_version.phlat_plr_api_host.arn
        },
      ]
      environment = [
        {
          name  = "TZ"
          value = var.timezone
        },
      ]
      #change awslog group
      logConfiguration = {
        logDriver = "awslogs"
        options = {
          awslogs-create-group  = "true"
          awslogs-group         = "/ecs/${var.application}"
          awslogs-region        = var.aws_region
          awslogs-stream-prefix = "ecs"
        }
      }
    }
  ])
}

resource "aws_ecs_service" "main" {
  name                              = "${var.application}-${var.target_env}-service"
  cluster                           = aws_ecs_cluster.phlat_cluster.arn
  task_definition                   = aws_ecs_task_definition.phlat_td.arn
  desired_count                     = var.app_count
  health_check_grace_period_seconds = 75 # should be 3 times the ALB health check interval
  wait_for_steady_state             = false
  force_new_deployment              = true

  triggers = {
    redeployment = var.timestamp
  }

  network_configuration {
    security_groups  = [data.aws_security_group.app.id]
    subnets          = data.aws_subnets.app.ids
    assign_public_ip = false
  }

  load_balancer {
    target_group_arn = aws_alb_target_group.app.id
    container_name   = "${var.application}-${var.target_env}-definition"
    container_port   = var.app_port
  }

  depends_on = [data.aws_alb_listener.front_end, aws_iam_role_policy_attachment.ecs_task_execution_role]

  lifecycle {
    ignore_changes = [capacity_provider_strategy]
  }

}
