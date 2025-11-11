package co.simplon.everydaybetterbusiness.services;

import co.simplon.everydaybetterbusiness.repositories.ActivityRepository;
import co.simplon.everydaybetterbusiness.services.adapter.ActivityServiceAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ActivityServiceAdapterTest {
    @Mock
    private ActivityRepository activityRepository;

    @InjectMocks
    private ActivityServiceAdapter activityServiceAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExistByActivityIdAndUserEmail_ReturnsTrue() {
        Long activityId = 1L;
        String email = "user1@example.com";
        when(activityRepository.existByActivityIdAndUserEmail(activityId, email)).thenReturn(true);

        boolean result = activityServiceAdapter.existByActivityIdAndUserEmail(activityId, email);

        assertTrue(result);
        verify(activityRepository, times(1)).existByActivityIdAndUserEmail(activityId, email);
    }

    @Test
    void testExistByActivityIdAndUserEmail_ReturnsFalse() {
        Long activityId = 2L;
        String email = "user2@example.com";
        when(activityRepository.existByActivityIdAndUserEmail(activityId, email)).thenReturn(false);

        boolean result = activityServiceAdapter.existByActivityIdAndUserEmail(activityId, email);

        assertFalse(result);
        verify(activityRepository, times(1)).existByActivityIdAndUserEmail(activityId, email);
    }

}
