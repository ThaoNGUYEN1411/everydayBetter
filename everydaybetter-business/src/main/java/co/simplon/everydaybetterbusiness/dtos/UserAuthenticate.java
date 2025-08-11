package co.simplon.everydaybetterbusiness.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserAuthenticate(@NotBlank @Size(max = 340) @Email String email,
        @NotBlank @Size(max = 255) String password) {
    @Override
    public String toString() {
        return "{email=" + email + ", password=[PROTECTED]}";
    }
}
