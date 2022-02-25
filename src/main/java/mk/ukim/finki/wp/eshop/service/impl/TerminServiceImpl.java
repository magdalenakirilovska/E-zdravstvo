package mk.ukim.finki.wp.eshop.service.impl;

import mk.ukim.finki.wp.eshop.service.TerminService;
import mk.ukim.finki.wp.eshop.model.Termin;
import mk.ukim.finki.wp.eshop.model.User;
import mk.ukim.finki.wp.eshop.model.exceptions.TerminNotFoundException;
import mk.ukim.finki.wp.eshop.repository.impl.TerminRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class TerminServiceImpl implements TerminService {

    private final TerminRepository terminRepository;

    public TerminServiceImpl(TerminRepository terminRepository) {
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

    @Transactional
    @Override
    public Optional<Termin> save(Calendar date) {
        Termin termin = new Termin(date);
        return Optional.of(terminRepository.save(termin));
    }

    @Transactional
    @Override
    public Optional<Termin> edit(Long id, Calendar date) {
        Termin termin = this.terminRepository.findById(id).orElseThrow(() -> new TerminNotFoundException(id));
        termin.setDate(date);
        return Optional.of(this.terminRepository.save(termin));
    }

    @Override
    public Termin zakazhi(Termin termin, User user) {
        termin.setZakazhanOd(user);
        //terminRepository.deleteById(termin.getId());
        return terminRepository.save(termin);
    }

    @Override
    public void deleteById(Long id) {
        this.terminRepository.deleteById(id);
    }
}
