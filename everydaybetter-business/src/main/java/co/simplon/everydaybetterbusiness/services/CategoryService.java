package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.dtos.output.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();
}
