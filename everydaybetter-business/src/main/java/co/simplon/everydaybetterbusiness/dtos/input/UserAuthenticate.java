package co.simplon.everydaybetterbusiness.dtos.input;

public record UserAuthenticate(String email, String password) {
    @Override
    public String toString() {
        return "{email=" + email + ", password=[PROTECTED]}";
    }
}
