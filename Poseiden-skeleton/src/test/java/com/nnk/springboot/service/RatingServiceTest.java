package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;

@Tag("UnitTest")
@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {
    @Nested
    @DisplayName("Rule: It should add a Rating")
    class addRating {
        private RatingService ratingService;
        @Mock
        private RatingRepository ratingRepository;
        @Captor
        private ArgumentCaptor<Rating> ratingArgumentCaptor;

        @BeforeEach
        void setUp() {
            ratingService = new RatingService(ratingRepository);
        }

        @Test
        void itShouldAddANewRatingIntoRepository() {
            // Given
            Rating ratingToAdd = new Rating("moodys", "sand P", "fitch", 50);
            // When
            Rating savedRating = ratingService.add(ratingToAdd);
            // Then
            then(ratingRepository).should().save(ratingArgumentCaptor.capture());
            assertThat(ratingArgumentCaptor.getValue().getFitchRating()).isEqualTo("fitch");
            assertThat(ratingArgumentCaptor.getValue().getMoodysRating()).isEqualTo("moodys");
            assertThat(ratingArgumentCaptor.getValue().getSandPRating()).isEqualTo("sand P");
            assertThat(ratingArgumentCaptor.getValue().getOrderNumber()).isEqualTo(50);
        }
    }
}
