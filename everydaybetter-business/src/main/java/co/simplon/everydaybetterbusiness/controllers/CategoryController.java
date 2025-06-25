package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.models.CategoryModel;
import co.simplon.everydaybetterbusiness.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all activities", description = "Get all activities")
    public ResponseEntity<List<CategoryModel>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }
}
