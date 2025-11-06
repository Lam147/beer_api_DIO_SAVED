package one.digitalinnovation.beerstock.service;

import one.digitalinnovation.beerstock.builder.BeerDTOBuilder;
import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.entity.Beer;
import one.digitalinnovation.beerstock.exception.BeerNotFoundException;
import one.digitalinnovation.beerstock.mapper.BeerMapper;
import one.digitalinnovation.beerstock.repository.BeerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BeerServiceFindByNameTest {

    @Mock private BeerRepository beerRepository;
    private final BeerMapper beerMapper = BeerMapper.INSTANCE;
    @InjectMocks private BeerService beerService;

    @Test void whenValidNameThenReturnBeer() throws BeerNotFoundException {
        BeerDTO dto = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer beer = beerMapper.toModel(dto);
        when(beerRepository.findByName(dto.getName())).thenReturn(java.util.Optional.of(beer));
        BeerDTO found = beerService.findByName(dto.getName());
        assertThat(found, is(equalTo(dto)));
    }
}
