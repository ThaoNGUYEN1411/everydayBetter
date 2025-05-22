package co.simplon.everydaybetterbusiness.dtos;

import co.simplon.everydaybetterbusiness.validators.UniqueEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreate(@Size(max = 255) String nickname, @UniqueEmail @NotBlank @Size(max = 340) String email, @NotBlank @Size(max = 255) String password) {

    @Override
    public String toString() {
        return "UserCreate{" +
                "nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", password=[PROTECTED]}'" + '\'' +
                '}';
    }
}
//todo 1: test handle exception email exist
