package mk.ukim.finki.wp.eshop.service.impl;

import mk.ukim.finki.wp.eshop.service.TerminService;
import mk.ukim.finki.wp.eshop.model.Termin;
import mk.ukim.finki.wp.eshop.model.User;
import mk.ukim.finki.wp.eshop.model.exceptions.TerminNotFoundException;
import mk.ukim.finki.wp.eshop.repository.impl.InMemoryTerminRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class TerminServiceImpl implements TerminService {

    private final InMemoryTerminRepository terminRepository;

    public TerminServiceImpl(InMemoryTerminRepository terminRepository) {
        this.terminRepository = terminRepository;
    }

    @Override
    public List<Termin> findAll() {
        return terminRepository.findAll();
    }

    @Override
    public Optional<Termin> findById(Long id) {
        return terminRepository.findById(id);
    }

    @Override
    public Optional<Termin> findByDate(Calendar date) {
        return terminRepository.findByDate(date);
    }

    @Override
    public Optional<Termin> save(Calendar date) {
        return Optional.of(terminRepository.save(date));
    }

    @Override
    public Optional<Termin> edit(Long id, Calendar date) {
        Termin termin = this.terminRepository.findById(id).orElseThrow(() -> new TerminNotFoundException(id));
        termin.setDate(date);
        return Optional.of(this.terminRepository.save(termin));
    }

    @Override
    public Optional<Termin> zakazhi(Termin termin, User user) {
        return terminRepository.zakazhi(termin, user);
    }

    @Override
    public void deleteById(Long id) {
        this.terminRepository.deleteById(id);
    }
}
