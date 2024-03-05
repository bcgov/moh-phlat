package com.moh.phlat.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Just using this URL for health check as of now. Can be removed in future
 */
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class ConfigController {
    @GetMapping("/config")
    public ResponseEntity<String> configEndpoint() {

        return ResponseEntity.ok("Ok");
    }
}
