package one.digitalinnovation.beerstock.service;

import one.digitalinnovation.beerstock.builder.BeerDTOBuilder;
import one.digitalinnovation.beerstock.entity.Beer;
import one.digitalinnovation.beerstock.exception.BeerNotFoundException;
import one.digitalinnovation.beerstock.mapper.BeerMapper;
import one.digitalinnovation.beerstock.repository.BeerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BeerServiceDeleteTest {

    @Mock private BeerRepository beerRepository;
    private final BeerMapper beerMapper = BeerMapper.INSTANCE;
    @InjectMocks private BeerService beerService;

    @Test void whenValidIdThenDelete() throws BeerNotFoundException {
        Beer beer = beerMapper.toModel(BeerDTOBuilder.builder().build().toBeerDTO());
        when(beerRepository.findById(beer.getId())).thenReturn(java.util.Optional.of(beer));
        beerService.deleteById(beer.getId());
        verify(beerRepository, times(1)).delete(beer);
    }
}
