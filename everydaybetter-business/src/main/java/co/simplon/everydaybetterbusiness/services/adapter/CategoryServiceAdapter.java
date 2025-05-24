package co.simplon.everydaybetterbusiness.services.adapter;

import co.simplon.everydaybetterbusiness.entities.Category;
import co.simplon.everydaybetterbusiness.models.CategoryModel;
import co.simplon.everydaybetterbusiness.repositories.CategoryRepository;
import co.simplon.everydaybetterbusiness.services.CategoryService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceAdapter implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceAdapter(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CategoryModel> getAll(){
        return repository.findAll().stream().map(c ->
                   new CategoryModel(c.getId(), c.getName())
        ).toList();
    }

    //todo: handle exception
    @Override
    public Category findById(final Long id) {
        return repository.findById(id).orElseThrow(() -> new ServiceException("Not found category with id:"+id));
    }
}
