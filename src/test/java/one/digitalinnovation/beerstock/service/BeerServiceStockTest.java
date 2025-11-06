package one.digitalinnovation.beerstock.service;

import one.digitalinnovation.beerstock.builder.BeerDTOBuilder;
import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.dto.QuantityDTO;
import one.digitalinnovation.beerstock.entity.Beer;
import one.digitalinnovation.beerstock.exception.BeerNotFoundException;
import one.digitalinnovation.beerstock.exception.BeerStockExceededException;
import one.digitalinnovation.beerstock.mapper.BeerMapper;
import one.digitalinnovation.beerstock.repository.BeerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BeerServiceStockTest {

    @Mock private BeerRepository beerRepository;
    private final BeerMapper beerMapper = BeerMapper.INSTANCE;
    @InjectMocks private BeerService beerService;

    @Test void whenIncrementThenIncreaseStock() throws BeerNotFoundException, BeerStockExceededException {
        BeerDTO dto = BeerDTOBuilder.builder().quantity(10).max(50).build().toBeerDTO();
        Beer beer = beerMapper.toModel(dto);
        when(beerRepository.findById(dto.getId())).thenReturn(java.util.Optional.of(beer));
        when(beerRepository.save(beer)).thenReturn(beer);
        QuantityDTO quantityDTO = new QuantityDTO(5);
        BeerDTO updated = beerService.increment(dto.getId(), quantityDTO.getQuantity());
        assertThat(updated.getQuantity(), is(15));
    }

    @Test void whenDecrementThenDecreaseStock() throws BeerNotFoundException, BeerStockExceededException {
        BeerDTO dto = BeerDTOBuilder.builder().quantity(10).build().toBeerDTO();
        Beer beer = beerMapper.toModel(dto);
        when(beerRepository.findById(dto.getId())).thenReturn(java.util.Optional.of(beer));
        when(beerRepository.save(beer)).thenReturn(beer);
        QuantityDTO quantityDTO = new QuantityDTO(3);
        BeerDTO updated = beerService.decrement(dto.getId(), quantityDTO.getQuantity());
        assertThat(updated.getQuantity(), is(7));
    }
}
