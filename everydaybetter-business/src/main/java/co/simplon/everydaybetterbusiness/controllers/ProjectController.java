package co.simplon.everydaybetterbusiness.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @GetMapping("/ping")
    public ResponseEntity<String> apiTest() {
        System.out.println("test");
        return ResponseEntity.status(HttpStatus.OK).body("api test");
    }
}
