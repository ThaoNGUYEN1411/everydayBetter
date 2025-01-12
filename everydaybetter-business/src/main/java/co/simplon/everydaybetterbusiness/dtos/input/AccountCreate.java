package co.simplon.everydaybetterbusiness.dtos.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AccountCreate(@NotBlank @Size(max = 225)String username, String email, String password) {

    @Override
    public String toString() {
        return "{username=" + username + ", password=[PROTECTED]}";
    }

}
//need: add validation not null, unique, size ...