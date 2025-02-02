package co.simplon.everydaybetterbusiness.models;

import java.util.List;

public record ActivityModel(Long id, String name, String description, Boolean positive, List<Category> categories ) {
public record Category(Long id, String name){}
}
