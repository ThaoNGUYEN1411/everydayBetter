package co.simplon.everydaybetterbusiness.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ActivityUpdate(@NotBlank @Size(max = 200) String name, @Size(max = 5000) String description, @NotNull Boolean positive, List<Long> categoryIds) {

}