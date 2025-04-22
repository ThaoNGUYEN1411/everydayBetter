package co.simplon.everydaybetterbusiness.models;

public record ActivityDetailModel(Long id, String name, String description, Boolean positive, Category category) {
public record Category(Long id, String name){}
}
