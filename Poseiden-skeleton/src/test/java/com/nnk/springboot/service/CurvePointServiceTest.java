package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@Tag("UnitTest")
public class CurvePointServiceTest {
    @Nested
    @DisplayName("Rule: It should add a Curve Point")
    class AddCurvePoint {
        private CurvePointService curvePointService;
        @Mock
        private CurvePointRepository curvePointRepository;
        @Mock
        private DateProviderImpl dateProvider;
        @Captor
        private ArgumentCaptor<CurvePoint> curvePointArgumentCaptor;

        @BeforeEach
        void setUp() {
            curvePointService = new CurvePointService(curvePointRepository, dateProvider);
        }

        @Test
        void itShouldAddANewCurvePointIntoRepository() {
            // Given
            CurvePoint curvePointToAdd = new CurvePoint(10, 10d, 20d);
            Timestamp now = Timestamp.valueOf(LocalDateTime.of(2024, 1, 18, 12, 0, 0, 0));
            given(dateProvider.getNow()).willReturn(now);

            // When
            CurvePoint savedCurvePoint = curvePointService.add(curvePointToAdd);

            // Then
            then(curvePointRepository).should().save(curvePointArgumentCaptor.capture());
            assertThat(curvePointArgumentCaptor.getValue().getCurveId()).isEqualTo(10);
            assertThat(curvePointArgumentCaptor.getValue().getAsOfDate()).isEqualTo(now);
            assertThat(curvePointArgumentCaptor.getValue().getValue()).isEqualTo(20d);
            assertThat(curvePointArgumentCaptor.getValue().getTerm()).isEqualTo(10d);
            assertThat(curvePointArgumentCaptor.getValue().getCreationDate()).isEqualTo(now);

        }
    }

    @Nested
    @DisplayName("Rule: it should return all the Curve points")
    class DisplayCurvePoints {
        private CurvePointService curvePointService;
        @Mock
        private CurvePointRepository curvePointRepository;
        @Mock
        private DateProviderImpl dateProvider;
        @Captor
        private ArgumentCaptor<CurvePoint> curvePointArgumentCaptor;

        @BeforeEach
        void setUp() {
            curvePointService = new CurvePointService(curvePointRepository, dateProvider);
        }

        @Test
        void itShouldReturnAllTheCurvePoint() {
            // Given
            Timestamp now = Timestamp.valueOf(LocalDateTime.of(2024, 1, 18, 12, 0, 0, 0));
            List<CurvePoint> givenCurvePoints = List.of(
                    new CurvePoint(1, now, 10d, 20d, now),
                    new CurvePoint(2, now, 30d, 40d, now)
            );
            given(curvePointRepository.findAll()).willReturn(givenCurvePoints);

            // When
            List<CurvePoint> actualCurvePoints = curvePointService.getAll();

            // Then
            assertThat(actualCurvePoints).hasSameElementsAs(givenCurvePoints);
        }

        @Test
        void itShouldReturnCurvePointByItsId() {
            // Given
            Timestamp now = Timestamp.valueOf(LocalDateTime.of(2024, 1, 18, 12, 0, 0, 0));
            List<CurvePoint> givenCurvePoints = List.of(
                    new CurvePoint(1, now, 10d, 20d, now),
                    new CurvePoint(2, now, 30d, 40d, now)
            );
            given(curvePointRepository.findById(2)).willReturn(Optional.of(new CurvePoint(2, now, 30d, 40d, now)));

            // When
            CurvePoint actualCurvePoint = curvePointService.getById(2);

            // Then
            assertThat(actualCurvePoint.getCurveId()).isEqualTo(2);
            assertThat(actualCurvePoint.getTerm()).isEqualTo(30d);
            assertThat(actualCurvePoint.getValue()).isEqualTo(40d);
        }
    }

    @Nested
    @DisplayName("Rule: it should update and delete a Curve Point")
    class CurvePointUpdate {
        private CurvePointService curvePointService;
        @Mock
        private CurvePointRepository curvePointRepository;
        @Mock
        private DateProviderImpl dateProvider;
        @Captor
        private ArgumentCaptor<CurvePoint> curvePointArgumentCaptor;

        @BeforeEach
        void setUp() {
            curvePointService = new CurvePointService(curvePointRepository, dateProvider);
        }

        @Test
        void itShouldUpdateACurvePoint() {
            // Given
            Timestamp now = Timestamp.valueOf(LocalDateTime.of(2024, 1, 18, 12, 0, 0, 0));
            CurvePoint currentCurvePoint = new CurvePoint(1, now, 10d, 10d, now);
            CurvePoint expectedCurvePoint = new CurvePoint(1, now, 20d, 20d, now);
            given(curvePointRepository.findById(1)).willReturn(Optional.of(currentCurvePoint));

            // When
            CurvePoint actualCurvePoint = curvePointService.update(expectedCurvePoint, 1);

            // Then
            then(curvePointRepository).should().save(curvePointArgumentCaptor.capture());
            assertThat(actualCurvePoint.getId()).isEqualTo(currentCurvePoint.getId());
            assertThat(curvePointArgumentCaptor.getValue().getCurveId()).isEqualTo(1);
            assertThat(curvePointArgumentCaptor.getValue().getTerm()).isEqualTo(20d);
            assertThat(curvePointArgumentCaptor.getValue().getValue()).isEqualTo(20d);

        }

        @Test
        void itShouldDeleteACurvePoint() {
            // Given
            Timestamp now = Timestamp.valueOf(LocalDateTime.of(2024, 1, 18, 12, 0, 0, 0));
            CurvePoint currentCurvePoint = new CurvePoint(1, now, 10d, 10d, now);

            // When
            curvePointService.delete(1);
            // Then
            then(curvePointRepository).should().deleteById(1);

        }
    }
}
