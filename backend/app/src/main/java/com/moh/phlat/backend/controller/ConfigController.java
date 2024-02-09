package com.moh.phlat.backend.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Need this endpoint to fulfil configuration from KeyCloak, once we
 * got keycloak setup and running the response will be replaced by 
 * original data.
 */
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class ConfigController {
	@GetMapping("/config")
    public ResponseEntity<String> configEndpoint() {
        String jsonResponse = "{\n" +
                "    \"adminDashboardUrl\": \"\",\n" +
                "    \"apiPath\": \"api\",\n" +
                "    \"basePath\": \"/app\",\n" +
                "    \"keycloak\": {\n" +
                "        \"clientId\": \"chefs-frontend\",\n" +
                "        \"realm\": \"chefs\",\n" +
                "        \"serverUrl\": \"http://localhost:8084\"\n" +
                "    },\n" +
                "    \"uploads\": {\n" +
                "        \"enabled\": \"true\",\n" +
                "        \"fileCount\": \"1\",\n" +
                "        \"fileKey\": \"files\",\n" +
                "        \"fileMaxSize\": \"25MB\",\n" +
                "        \"fileMaxSizeBytes\": \"25000000\",\n" +
                "        \"fileMinSize\": \"0KB\",\n" +
                "        \"path\": \"files\"\n" +
                "    }\n" +
                "}";
        return ResponseEntity.ok(jsonResponse);
    }
}
