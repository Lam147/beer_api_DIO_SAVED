package one.digitalinnovation.beerstock.service;

import one.digitalinnovation.beerstock.builder.BeerDTOBuilder;
import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.entity.Beer;
import one.digitalinnovation.beerstock.mapper.BeerMapper;
import one.digitalinnovation.beerstock.repository.BeerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BeerServiceListTest {

    @Mock private BeerRepository beerRepository;
    private final BeerMapper beerMapper = BeerMapper.INSTANCE;
    @InjectMocks private BeerService beerService;

    @Test void whenListCalledThenReturnList() {
        BeerDTO dto = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer beer = beerMapper.toModel(dto);
        when(beerRepository.findAll()).thenReturn(Collections.singletonList(beer));
        List<BeerDTO> list = beerService.listAll();
        assertThat(list, hasSize(1));
        assertThat(list.get(0), equalTo(dto));
    }

    @Test void whenListCalledThenReturnEmpty() {
        when(beerRepository.findAll()).thenReturn(Collections.emptyList());
        assertThat(beerService.listAll(), empty());
    }
}
