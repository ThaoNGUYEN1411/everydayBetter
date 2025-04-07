package co.simplon.everydaybetterbusiness.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreate(@NotBlank @Size(max = 225)String nickname,@NotBlank String email,@NotBlank String password) {

    @Override
    public String toString() {
        return "UserCreate{" +
                "nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", password=[PROTECTED]}'" + '\'' +
                '}';
    }
}
//need: add validation not null, unique, size ...