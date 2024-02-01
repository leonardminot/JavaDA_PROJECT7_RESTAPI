package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
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
public class BidServiceTest {
    @Nested
    @DisplayName("Rule: it should add a new Bid into the repository")
    class ItShouldAddANewBidIntoTheRepository {
        private BidService bidService;
        @Mock
        private DateProviderImpl dateProvider;
        @Mock
        private BidListRepository bidListRepository;
        @Captor
        private ArgumentCaptor<BidList> bidListArgumentCaptor;

        @BeforeEach
        void setUp() {
            bidService = new BidService(bidListRepository, dateProvider);
        }

        @Test
        void itShouldAddANewBidListIntoRepository() {
            // Given
            Timestamp now = Timestamp.valueOf(LocalDateTime.of(2024, 1, 18, 12, 0, 0, 0));
            given(dateProvider.getNow()).willReturn(now);
            BidList bidList = new BidList(
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
            bidService.add(bidList);

            // Then
            then(bidListRepository).should().save(bidListArgumentCaptor.capture());
            assertThat(bidListArgumentCaptor.getValue().getBidListDate()).isEqualTo(now);
            assertThat(bidListArgumentCaptor.getValue().getBenchmark()).isEqualTo("benchmark");
            assertThat(bidListArgumentCaptor.getValue().getBid()).isEqualTo(30d);
            assertThat(bidListArgumentCaptor.getValue().getBidQuantity()).isEqualTo(10d);
            assertThat(bidListArgumentCaptor.getValue().getType()).isEqualTo("type");
            assertThat(bidListArgumentCaptor.getValue().getAsk()).isEqualTo(40d);
            assertThat(bidListArgumentCaptor.getValue().getAskQuantity()).isEqualTo(20d);
            assertThat(bidListArgumentCaptor.getValue().getAccount()).isEqualTo("account");
            assertThat(bidListArgumentCaptor.getValue().getCommentary()).isEqualTo("commentary");
            assertThat(bidListArgumentCaptor.getValue().getSecurity()).isEqualTo("security");
            assertThat(bidListArgumentCaptor.getValue().getTrader()).isEqualTo("trader");
            assertThat(bidListArgumentCaptor.getValue().getBook()).isEqualTo("book");
            assertThat(bidListArgumentCaptor.getValue().getCreationName()).isEqualTo("creationName");
            assertThat(bidListArgumentCaptor.getValue().getCreationDate()).isEqualTo(now);
            assertThat(bidListArgumentCaptor.getValue().getRevisionName()).isEqualTo("revisionName");
            assertThat(bidListArgumentCaptor.getValue().getRevisionDate()).isEqualTo(now);
            assertThat(bidListArgumentCaptor.getValue().getDealName()).isEqualTo("dealName");
            assertThat(bidListArgumentCaptor.getValue().getDealType()).isEqualTo("dealType");
            assertThat(bidListArgumentCaptor.getValue().getSourceListId()).isEqualTo("sourceListId");
            assertThat(bidListArgumentCaptor.getValue().getSide()).isEqualTo("side");
        }
    }

    @Nested
    @DisplayName("Rule: it should return all the BidList")
    class ItShouldReturnAllTheBidList {
        private BidService bidService;
        @Mock
        private DateProviderImpl dateProvider;
        @Mock
        private BidListRepository bidListRepository;
        @Captor
        private ArgumentCaptor<BidList> bidListArgumentCaptor;

        @BeforeEach
        void setUp() {
            bidService = new BidService(bidListRepository, dateProvider);
        }

        @Test
        void itShouldReturnAllTheBidLists() {
            // Given
            List<BidList> bidLists = List.of(
                    new BidList(
                            "account",
                            "type",
                            10d,
                            20d,
                            30d,
                            40d,
                            "benchmark"
                    ),
                    new BidList(
                            "account2",
                            "type2",
                            102d,
                            202d,
                            302d,
                            402d,
                            "benchmark2"
                    )
            );
            given(bidListRepository.findAll()).willReturn(bidLists);

            // When
            List<BidList> actualBidLists = bidService.getAll();

            // Then
            assertThat(actualBidLists).hasSameElementsAs(bidLists);

        }

        @Test
        void itShouldFetchBidListByItsId() {
            // Given
            given(bidListRepository.findById(2)).willReturn(Optional.of(new BidList(
                    "account2",
                    "type2",
                    102d,
                    202d,
                    302d,
                    402d,
                    "benchmark2"
            )));

            // When
            BidList actualBidList = bidService.getById(2);

            // Then
            assertThat(actualBidList.getAccount()).isEqualTo("account2");
            assertThat(actualBidList.getType()).isEqualTo("type2");
            assertThat(actualBidList.getBidQuantity()).isEqualTo(102d);
            assertThat(actualBidList.getAskQuantity()).isEqualTo(202d);
            assertThat(actualBidList.getBid()).isEqualTo(302d);
            assertThat(actualBidList.getAsk()).isEqualTo(402d);
            assertThat(actualBidList.getBenchmark()).isEqualTo("benchmark2");
        }
    }

    @Nested
    @DisplayName("Rule: it should update or delete Bid List")
    class ItShouldUpdateOrDeleteBidList {
        private BidService bidService;
        @Mock
        private DateProviderImpl dateProvider;
        @Mock
        private BidListRepository bidListRepository;
        @Captor
        private ArgumentCaptor<BidList> bidListArgumentCaptor;

        @BeforeEach
        void setUp() {
            bidService = new BidService(bidListRepository, dateProvider);
        }

        @Test
        void itShouldUpdateABidList() {
            // Given
            BidList currentBidList = new BidList(
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

            BidList expectedBidList = new BidList(
                    "account updated",
                    "type updated",
                    102d,
                    20d,
                    302d,
                    40d,
                    "benchmark updated",
                    "commentary 2",
                    "security 2",
                    "status 2",
                    "trader 2",
                    "book 2",
                    "creationName 2",
                    "revisionName 2",
                    "dealName 2",
                    "dealType 2",
                    "sourceListId 2",
                    "side 2"
            );
            given(bidListRepository.findById(1)).willReturn(Optional.of(currentBidList));

            // When
            BidList actualBidList = bidService.update(expectedBidList, 1);

            // Then
            then(bidListRepository).should().save(bidListArgumentCaptor.capture());
            assertThat(bidListArgumentCaptor.getValue().getBidListId()).isEqualTo(currentBidList.getBidListId());
            assertThat(bidListArgumentCaptor.getValue().getAccount()).isEqualTo("account updated");
            assertThat(bidListArgumentCaptor.getValue().getType()).isEqualTo("type updated");
            assertThat(bidListArgumentCaptor.getValue().getBidQuantity()).isEqualTo(102d);
            assertThat(bidListArgumentCaptor.getValue().getAskQuantity()).isEqualTo(20d);
            assertThat(bidListArgumentCaptor.getValue().getBid()).isEqualTo(302d);
            assertThat(bidListArgumentCaptor.getValue().getAsk()).isEqualTo(40d);
            assertThat(bidListArgumentCaptor.getValue().getBenchmark()).isEqualTo("benchmark updated");
            assertThat(bidListArgumentCaptor.getValue().getCommentary()).isEqualTo("commentary 2");
            assertThat(bidListArgumentCaptor.getValue().getSecurity()).isEqualTo("security 2");
            assertThat(bidListArgumentCaptor.getValue().getTrader()).isEqualTo("trader 2");
            assertThat(bidListArgumentCaptor.getValue().getBook()).isEqualTo("book 2");
            assertThat(bidListArgumentCaptor.getValue().getCreationName()).isEqualTo("creationName 2");
            assertThat(bidListArgumentCaptor.getValue().getRevisionName()).isEqualTo("revisionName 2");
            assertThat(bidListArgumentCaptor.getValue().getDealName()).isEqualTo("dealName 2");
            assertThat(bidListArgumentCaptor.getValue().getDealType()).isEqualTo("dealType 2");
            assertThat(bidListArgumentCaptor.getValue().getSourceListId()).isEqualTo("sourceListId 2");
            assertThat(bidListArgumentCaptor.getValue().getSide()).isEqualTo("side 2");
        }

        @Test
        void itShouldDeleteABidList() {
            // Given

            // When
            bidService.delete(1);

            // Then
            then(bidListRepository).should().deleteById(1);

        }
    }
}
