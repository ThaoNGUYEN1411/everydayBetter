package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.entities.Category;
import co.simplon.everydaybetterbusiness.models.CategoryModel;

import java.util.List;

public interface CategoryService {
    List<CategoryModel> getAll();

    Category findById(Long id);
}
