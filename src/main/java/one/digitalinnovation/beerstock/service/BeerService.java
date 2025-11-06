package one.digitalinnovation.beerstock.service;

import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.dto.QuantityDTO;
import one.digitalinnovation.beerstock.entity.Beer;
import one.digitalinnovation.beerstock.exception.*;
import one.digitalinnovation.beerstock.mapper.BeerMapper;
import one.digitalinnovation.beerstock.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper = BeerMapper.INSTANCE;

    @Autowired
    public BeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public BeerDTO createBeer(BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(beerDTO.getName());
        Beer beer = beerMapper.toModel(beerDTO);
        return beerMapper.toDTO(beerRepository.save(beer));
    }

    public BeerDTO findByName(String name) throws BeerNotFoundException {
        return beerMapper.toDTO(beerRepository.findByName(name)
                .orElseThrow(() -> new BeerNotFoundException(name)));
    }

    public List<BeerDTO> listAll() {
        return beerRepository.findAll().stream()
                .map(beerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) throws BeerNotFoundException {
        Beer beer = verifyIfExists(id);
        beerRepository.delete(beer);
    }

    public BeerDTO increment(Long id, int amount) throws BeerNotFoundException, BeerStockExceededException {
        Beer beer = verifyIfExists(id);
        int newQty = beer.getQuantity() + amount;
        if (newQty > beer.getMax()) throw new BeerStockExceededException(id, amount);
        beer.setQuantity(newQty);
        return beerMapper.toDTO(beerRepository.save(beer));
    }

    public BeerDTO decrement(Long id, int amount) throws BeerNotFoundException, BeerStockExceededException {
        Beer beer = verifyIfExists(id);
        int newQty = beer.getQuantity() - amount;
        if (newQty < 0) throw new BeerStockExceededException(id, amount);
        beer.setQuantity(newQty);
        return beerMapper.toDTO(beerRepository.save(beer));
    }

    private Beer verifyIfExists(Long id) throws BeerNotFoundException {
        return beerRepository.findById(id)
                .orElseThrow(() -> new BeerNotFoundException(id));
    }

    private void verifyIfIsAlreadyRegistered(String name) throws BeerAlreadyRegisteredException {
        if (beerRepository.findByName(name).isPresent())
            throw new BeerAlreadyRegisteredException(name);
    }
}
