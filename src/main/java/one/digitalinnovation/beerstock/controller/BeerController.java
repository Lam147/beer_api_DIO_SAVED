package one.digitalinnovation.beerstock.controller;

import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.dto.QuantityDTO;
import one.digitalinnovation.beerstock.exception.*;
import one.digitalinnovation.beerstock.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {

    private final BeerService service;

    @Autowired
    public BeerController(BeerService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeerDTO createBeer(@RequestBody @Valid BeerDTO dto) throws BeerAlreadyRegisteredException {
        return service.createBeer(dto);
    }

    @GetMapping("/{name}")
    public BeerDTO findByName(@PathVariable String name) throws BeerNotFoundException {
        return service.findByName(name);
    }

    @GetMapping
    public List<BeerDTO> listBeers() {
        return service.listAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws BeerNotFoundException {
        service.deleteById(id);
    }

    @PatchMapping("/{id}/increment")
    public BeerDTO increment(@PathVariable Long id, @RequestBody @Valid QuantityDTO dto)
            throws BeerNotFoundException, BeerStockExceededException {
        return service.increment(id, dto.getQuantity());
    }

    @PatchMapping("/{id}/decrement")
    public BeerDTO decrement(@PathVariable Long id, @RequestBody @Valid QuantityDTO dto)
            throws BeerNotFoundException, BeerStockExceededException {
        return service.decrement(id, dto.getQuantity());
    }
}
