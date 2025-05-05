package co.simplon.everydaybetterbusiness.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ActivityUpdate(
        @NotBlank @Size(max = 200) String name,
        @Size(max = 2000) String description,
        @NotNull Boolean positive,
        String categoryId) {
}

//todo: create anotation ActivityUpdate
