package one.digitalinnovation.beerstock.service;

import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.entity.Beer;
import one.digitalinnovation.beerstock.exception.*;
import one.digitalinnovation.beerstock.mapper.BeerMapper;
import one.digitalinnovation.beerstock.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BeerService {

    private final BeerRepository repo;
    private final BeerMapper mapper = BeerMapper.INSTANCE;

    @Autowired
    public BeerService(BeerRepository repo) {
        this.repo = repo;
    }

    public BeerDTO create(BeerDTO dto) throws BeerAlreadyRegisteredException {
        if (repo.findByName(dto.getName()).isPresent()) throw new BeerAlreadyRegisteredException(dto.getName());
        return mapper.toDTO(repo.save(mapper.toModel(dto)));
    }

    public BeerDTO findByName(String name) throws BeerNotFoundException {
        return mapper.toDTO(repo.findByName(name).orElseThrow(() -> new BeerNotFoundException(name)));
    }

    public List<BeerDTO> listAll() {
        return repo.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public void deleteById(Long id) throws BeerNotFoundException {
        repo.findById(id).orElseThrow(() -> new BeerNotFoundException(id));
        repo.deleteById(id);
    }

    public BeerDTO increment(Long id, int qty) throws BeerNotFoundException, BeerStockExceededException {
        Beer beer = repo.findById(id).orElseThrow(() -> new BeerNotFoundException(id));
        int newQty = beer.getQuantity() + qty;
        if (newQty > beer.getMax()) throw new BeerStockExceededException(id, qty);
        beer.setQuantity(newQty);
        return mapper.toDTO(repo.save(beer));
    }

    public BeerDTO decrement(Long id, int qty) throws BeerNotFoundException, BeerStockExceededException {
        Beer beer = repo.findById(id).orElseThrow(() -> new BeerNotFoundException(id));
        int newQty = beer.getQuantity() - qty;
        if (newQty < 0) throw new BeerStockExceededException(id, qty);
        beer.setQuantity(newQty);
        return mapper.toDTO(repo.save(beer));
    }
}
