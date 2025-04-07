package co.simplon.everydaybetterbusiness.dtos;

public record UserAuthenticate(String email, String password) {
    @Override
    public String toString() {
        return "{email=" + email + ", password=[PROTECTED]}";
    }
}
