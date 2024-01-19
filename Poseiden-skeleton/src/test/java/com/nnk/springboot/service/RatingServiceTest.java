package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
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

    @Nested
    @DisplayName("Rule: it should return all the Ratings")
    class DisplayRatings {
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
        void itShouldReturnAllTheRatings() {
            // Given
            List<Rating> givenRatings = List.of(
                    new Rating("para1", "para2", "para3", 1),
                    new Rating("para10", "para20", "para30", 2)
            );
            given(ratingRepository.findAll()).willReturn(givenRatings);

            // When
            List<Rating> actualRatings = ratingService.getAll();

            // Then
            assertThat(actualRatings).hasSameElementsAs(givenRatings);
        }

        @Test
        void itShouldReturnCurvePointByItsId() {
            // Given
            given(ratingRepository.findById(2)).willReturn(Optional.of(new Rating("moodys", "sand P", "fitch", 50)));
            // When
            Rating actualRating = ratingService.getById(2);
            // Then
            assertThat(actualRating.getSandPRating()).isEqualTo("sand P");
            assertThat(actualRating.getFitchRating()).isEqualTo("fitch");
            assertThat(actualRating.getMoodysRating()).isEqualTo("moodys");
            assertThat(actualRating.getOrderNumber()).isEqualTo(50);
        }
    }

    @Nested
    @DisplayName("Rule: it should update and delete a Rating")
    class UpdateAndDeleteRating {
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
        void itShouldUpdateARating() {
            // Given
            Rating currentRating = new Rating("moodys", "sand P", "fitch", 50);
            Rating expectedRating = new Rating("moodys 2", "sand P 2", "fitch 2", 51);
            given(ratingRepository.findById(1)).willReturn(Optional.of(currentRating));
            // When
            Rating actualRating = ratingService.update(expectedRating, 1);
            // Then
            then(ratingRepository).should().save(ratingArgumentCaptor.capture());
            assertThat(ratingArgumentCaptor.getValue().getFitchRating()).isEqualTo("fitch 2");
            assertThat(ratingArgumentCaptor.getValue().getMoodysRating()).isEqualTo("moodys 2");
            assertThat(ratingArgumentCaptor.getValue().getSandPRating()).isEqualTo("sand P 2");
            assertThat(ratingArgumentCaptor.getValue().getOrderNumber()).isEqualTo(51);

        }

        @Test
        void itShouldDeleteARating() {
            // Given
            // When
            ratingService.delete(1);
            // Then
            then(ratingRepository).should().deleteById(1);

        }
    }
}
