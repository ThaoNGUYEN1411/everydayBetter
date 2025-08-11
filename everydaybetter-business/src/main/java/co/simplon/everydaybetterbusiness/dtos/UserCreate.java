package co.simplon.everydaybetterbusiness.dtos;

import co.simplon.everydaybetterbusiness.validators.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreate(
        @Size(max = 255) String nickname,
        @UniqueEmail @NotBlank @Size(max = 340) @Email String email,
        @NotBlank @Size(max = 255)
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial parmi @$!%*?&."
        ) String password) {

    @Override
    public String toString() {
        return "UserCreate{" +
               "nickname='" + nickname + '\'' +
               ", email='" + email + '\'' +
               ", password=[PROTECTED]}'" + '\'' +
               '}';
    }
}
