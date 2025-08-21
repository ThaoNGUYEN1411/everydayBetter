package co.simplon.everydaybetterbusiness.dtos;

import co.simplon.everydaybetterbusiness.validators.ActivityCreateUnique;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@ActivityCreateUnique
public record ActivityCreate(
        @NotBlank @Size(max = 200) String name,
        @Size(max = 2000) String description,
        @NotNull Boolean positive,
        @NotBlank String categoryId) {
}
