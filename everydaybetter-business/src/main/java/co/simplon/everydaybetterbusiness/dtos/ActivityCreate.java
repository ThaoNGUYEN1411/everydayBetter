package co.simplon.everydaybetterbusiness.dtos;

import co.simplon.everydaybetterbusiness.validators.UniqueUserActivity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@UniqueUserActivity
public record ActivityCreate(
        @NotBlank @Size(max = 200) String name,
        @Size(max = 2000) String description,
        @NotNull Boolean positive,
        String categoryId) {
}
