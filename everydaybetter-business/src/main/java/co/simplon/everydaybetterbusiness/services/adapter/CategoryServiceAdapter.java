package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.dtos.output.CategoryDto;
import co.simplon.everydaybetterbusiness.repositories.CategoryRepository;
import co.simplon.everydaybetterbusiness.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceAdapter implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceAdapter(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CategoryDto> getAll(){
        return repository.findAll().stream().map(c ->
                   new CategoryDto(c.getId(), c.getName())
        ).toList();
    }

}
