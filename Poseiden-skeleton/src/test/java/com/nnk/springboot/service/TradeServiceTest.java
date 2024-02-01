package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@Tag("UnitTest")
public class TradeServiceTest {
    @Nested
    @DisplayName("Rule: it should add a trade")
    class ItShouldAddATrade {
        private TradeService tradeService;
        @Mock
        private DateProviderImpl dateProvider;
        @Mock
        private TradeRepository tradeRepository;
        @Captor
        private ArgumentCaptor<Trade> tradeArgumentCaptor;

        @BeforeEach
        void setUp() {
            tradeService = new TradeService(tradeRepository, dateProvider);
        }

        @Test
        void itShouldAddANewTradeIntoRepository() {
            // Given
            Timestamp now = Timestamp.valueOf(LocalDateTime.of(2024, 1, 18, 12, 0, 0, 0));
            given(dateProvider.getNow()).willReturn(now);
            Trade tradeToAdd = new Trade(
                    "account",
                    "type",
                    10d,
                    20d,
                    30d,
                    40d,
                    "benchmark",
                    "commentary",
                    "security",
                    "status",
                    "trader",
                    "book",
                    "creationName",
                    "revisionName",
                    "dealName",
                    "dealType",
                    "sourceListId",
                    "side"
            );

            // When
            tradeService.add(tradeToAdd);

            // Then
            then(tradeRepository).should().save(tradeArgumentCaptor.capture());
            assertThat(tradeArgumentCaptor.getValue().getTradeDate()).isEqualTo(now);
            assertThat(tradeArgumentCaptor.getValue().getBenchmark()).isEqualTo("benchmark");
            assertThat(tradeArgumentCaptor.getValue().getBuyPrice()).isEqualTo(30d);
            assertThat(tradeArgumentCaptor.getValue().getBuyQuantity()).isEqualTo(10d);
            assertThat(tradeArgumentCaptor.getValue().getType()).isEqualTo("type");
            assertThat(tradeArgumentCaptor.getValue().getSellPrice()).isEqualTo(40d);
            assertThat(tradeArgumentCaptor.getValue().getSellQuantity()).isEqualTo(20d);
            assertThat(tradeArgumentCaptor.getValue().getAccount()).isEqualTo("account");
            assertThat(tradeArgumentCaptor.getValue().getCommentary()).isEqualTo("commentary");
            assertThat(tradeArgumentCaptor.getValue().getSecurity()).isEqualTo("security");
            assertThat(tradeArgumentCaptor.getValue().getTrader()).isEqualTo("trader");
            assertThat(tradeArgumentCaptor.getValue().getBook()).isEqualTo("book");
            assertThat(tradeArgumentCaptor.getValue().getCreationName()).isEqualTo("creationName");
            assertThat(tradeArgumentCaptor.getValue().getCreationDate()).isEqualTo(now);
            assertThat(tradeArgumentCaptor.getValue().getRevisionName()).isEqualTo("revisionName");
            assertThat(tradeArgumentCaptor.getValue().getRevisionDate()).isNull();
            assertThat(tradeArgumentCaptor.getValue().getDealName()).isEqualTo("dealName");
            assertThat(tradeArgumentCaptor.getValue().getDealType()).isEqualTo("dealType");
            assertThat(tradeArgumentCaptor.getValue().getSourceListId()).isEqualTo("sourceListId");
            assertThat(tradeArgumentCaptor.getValue().getSide()).isEqualTo("side");
        }
    }

    @Nested
    @DisplayName("Rule: it should return all the trades")
    class ItShouldReturnAllTheTrades {
        private TradeService tradeService;
        @Mock
        private DateProviderImpl dateProvider;
        @Mock
        private TradeRepository tradeRepository;

        @BeforeEach
        void setUp() {
            tradeService = new TradeService(tradeRepository, dateProvider);
        }

        @Test
        void itShouldReturnAllTheTrades() {
            // Given
            List<Trade> trades = List.of(
                    new Trade(
                            "account",
                            "type",
                            10d,
                            20d,
                            30d,
                            40d,
                            "benchmark"
                    ),
                    new Trade(
                            "account2",
                            "type2",
                            102d,
                            202d,
                            302d,
                            402d,
                            "benchmark2"
                    )
            );
            given(tradeRepository.findAll()).willReturn(trades);

            // When
            List<Trade> actualTrades = tradeService.getAll();

            // Then
            assertThat(actualTrades).hasSameElementsAs(trades);

        }

        @Test
        void itShouldTradeByItsId() {
            // Given
            given(tradeRepository.findById(2)).willReturn(Optional.of(new Trade(
                    "account2",
                    "type2",
                    102d,
                    202d,
                    302d,
                    402d,
                    "benchmark2"
            )));

            // When
            Trade actualTrade = tradeService.getById(2);
            // Then
            assertThat(actualTrade.getAccount()).isEqualTo("account2");
            assertThat(actualTrade.getType()).isEqualTo("type2");
            assertThat(actualTrade.getBuyQuantity()).isEqualTo(102d);
            assertThat(actualTrade.getSellQuantity()).isEqualTo(202d);
            assertThat(actualTrade.getBuyPrice()).isEqualTo(302d);
            assertThat(actualTrade.getSellPrice()).isEqualTo(402d);
            assertThat(actualTrade.getBenchmark()).isEqualTo("benchmark2");

        }
    }

    @Nested
    @DisplayName("Rule: it should update or delete Trade")
    class ItShouldUpdateOrDeleteTrade {
        private TradeService tradeService;
        @Mock
        private DateProviderImpl dateProvider;
        @Mock
        private TradeRepository tradeRepository;
        @Captor
        private ArgumentCaptor<Trade> tradeArgumentCaptor;

        @BeforeEach
        void setUp() {
            tradeService = new TradeService(tradeRepository, dateProvider);
        }

        @Test
        void itShouldUpdateATrade() {
            // Given
            Timestamp creationDate = Timestamp.valueOf(LocalDateTime.of(2023, 12, 25, 12, 0, 0, 0));
            Timestamp now = Timestamp.valueOf(LocalDateTime.of(2024, 1, 18, 12, 0, 0, 0));
            given(dateProvider.getNow()).willReturn(now);
            Trade currentTrade = new Trade("account",
                    "type",
                    10d,
                    20d,
                    30d,
                    40d,
                    "benchmark",
                    creationDate,
                    "commentary",
                    "security",
                    "status",
                    "trader",
                    "book",
                    "creationName",
                    creationDate,
                    "revisionName",
                    null,
                    "dealName",
                    "dealType",
                    "sourceListId",
                    "side"
            );

            Trade expectedTrade = new Trade(
                    "account update",
                    "type update",
                    10d,
                    20d,
                    307d,
                    407d,
                    "benchmark",
                    now,
                    "commentary 2",
                    "security 2",
                    "status 2",
                    "trader 2",
                    "book 2",
                    "creationName 2",
                    creationDate,
                    "revisionName 2",
                    now,
                    "dealName 2",
                    "dealType 2",
                    "sourceListId 2",
                    "side 2"
            );
            given(tradeRepository.findById(1)).willReturn(Optional.of(currentTrade));
            // When
            Trade actualTrade = tradeService.update(expectedTrade, 1);
            // Then
            then(tradeRepository).should().save(tradeArgumentCaptor.capture());
            assertThat(tradeArgumentCaptor.getValue().getTradeId()).isEqualTo(currentTrade.getTradeId());
            assertThat(tradeArgumentCaptor.getValue().getAccount()).isEqualTo("account update");
            assertThat(tradeArgumentCaptor.getValue().getType()).isEqualTo("type update");
            assertThat(tradeArgumentCaptor.getValue().getBuyQuantity()).isEqualTo(10d);
            assertThat(tradeArgumentCaptor.getValue().getSellQuantity()).isEqualTo(20d);
            assertThat(tradeArgumentCaptor.getValue().getBuyPrice()).isEqualTo(307d);
            assertThat(tradeArgumentCaptor.getValue().getSellPrice()).isEqualTo(407d);
            assertThat(tradeArgumentCaptor.getValue().getBenchmark()).isEqualTo("benchmark");
            assertThat(tradeArgumentCaptor.getValue().getCommentary()).isEqualTo("commentary 2");
            assertThat(tradeArgumentCaptor.getValue().getSecurity()).isEqualTo("security 2");
            assertThat(tradeArgumentCaptor.getValue().getTrader()).isEqualTo("trader 2");
            assertThat(tradeArgumentCaptor.getValue().getBook()).isEqualTo("book 2");
            assertThat(tradeArgumentCaptor.getValue().getCreationName()).isEqualTo("creationName 2");
            assertThat(tradeArgumentCaptor.getValue().getRevisionName()).isEqualTo("revisionName 2");
            assertThat(tradeArgumentCaptor.getValue().getDealName()).isEqualTo("dealName 2");
            assertThat(tradeArgumentCaptor.getValue().getDealType()).isEqualTo("dealType 2");
            assertThat(tradeArgumentCaptor.getValue().getSourceListId()).isEqualTo("sourceListId 2");
            assertThat(tradeArgumentCaptor.getValue().getSide()).isEqualTo("side 2");

            assertThat(tradeArgumentCaptor.getValue().getTradeDate()).isEqualTo(now);
            assertThat(tradeArgumentCaptor.getValue().getCreationDate()).isEqualTo(creationDate);
            assertThat(tradeArgumentCaptor.getValue().getRevisionDate()).isEqualTo(now);

        }

        @Test
        void itShouldDeleteATrade() {
            // Given

            // When
            tradeService.delete(1);
            // Then
            then(tradeRepository).should().deleteById(1);

        }
    }
}
