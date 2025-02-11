package co.simplon.everydaybetterbusiness.dtos.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ActivityCreate(@NotBlank @Size(max = 200) String name, @Size(max = 5000) String description, @NotNull Boolean positive, String categoryId) {
}