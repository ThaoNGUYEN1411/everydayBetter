package co.simplon.everydaybetterbusiness.dtos.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record HabitCreate(@NotBlank @Size(max = 200) String habitName, @Size(max = 5000) String description, @NotNull Boolean positive, String labelName) {
}