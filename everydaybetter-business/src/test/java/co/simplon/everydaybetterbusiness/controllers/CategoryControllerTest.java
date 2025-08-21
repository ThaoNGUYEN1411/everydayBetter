package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.services.CategoryService;
import co.simplon.everydaybetterbusiness.utilsTest.MockMvcSetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest extends MockMvcSetup {

    @Autowired
    private CategoryController controller;

    @MockitoBean
    private CategoryService service;

    @Override
    protected CategoryController getController() {
        return controller;
    }

    @Test
    void shouldGetAll() throws Exception {
        final var url = "/categories";
        mockMvc.perform(get(url))
                .andExpect(status().isOk());
        verify(service).getAll();
    }

}
