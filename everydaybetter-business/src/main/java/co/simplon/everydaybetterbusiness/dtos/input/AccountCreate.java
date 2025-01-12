package co.simplon.everydaybetterbusiness.dtos.input;

public record AccountCreate(String username, String email, String password) {
}
//need: add validation not null, unique, size ...