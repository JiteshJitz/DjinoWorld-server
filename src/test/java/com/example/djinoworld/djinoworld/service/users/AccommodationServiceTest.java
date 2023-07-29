package com.example.djinoworld.djinoworld.service.users;

import com.example.djinoworld.djinoworld.model.Accommodation;
import com.example.djinoworld.djinoworld.repository.users.AccommodationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class AccommodationServiceTest {

    @Autowired
    private AccommodationService accommodationService;

    @MockBean
    private AccommodationRepository accommodationRepository;

    // @MockBean
    // private MongoTemplate mongoTemplate;

    private Accommodation accommodation;

    private String generatedAccommodationID;

    @BeforeEach
    public void setUp() {
        accommodation = new Accommodation();
        accommodation.setOwnerID("testOwnerID");
        // Set other properties
        accommodation.setAddress("123 Test Street");
        accommodation.setCity("Test City");
        accommodation.setCountry("Test Country");
        accommodation.setAccommodationType("Test Type");
        accommodation.setDescription("Test Description");
        accommodation.setPrice(100F);
        accommodation.setPhoneNumber("123-456-7890");
        accommodation.setResidenceName("Dont know");

        // Capture the generated accommodationID during save
        when(accommodationRepository.save(any(Accommodation.class))).thenAnswer(invocation -> {
            Accommodation saved = invocation.getArgument(0);
            generatedAccommodationID = saved.getAccommodationID(); // Capture the generated ID
            saved.setAccommodationID("randomGeneratedID"); // Hardcoded ID for now, not relevant in the test
            return saved;
        });

        when(accommodationRepository.findAll()).thenReturn(Arrays.asList(accommodation));

        // Use the actual generatedAccommodationID in the findById method
        when(accommodationRepository.findById(generatedAccommodationID)).thenReturn(Optional.of(accommodation));
    }

    @Test
    public void testFindAllAccommodation() {
        List<Accommodation> result = accommodationService.findAllAccommodation();
        assertEquals(1, result.size());
        assertEquals(generatedAccommodationID, result.get(0).getAccommodationID());
    }


    @Test
    public void testAddAccommodation() {
        Accommodation result = accommodationService.addAccommodation(accommodation);
        assertNotNull(result.getAccommodationID()); // Assuming UUID is not null
    }




    // Additional tests for the other methods in AccommodationService...
}
