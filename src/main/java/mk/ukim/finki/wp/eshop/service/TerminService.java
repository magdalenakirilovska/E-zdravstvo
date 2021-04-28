package mk.ukim.finki.wp.eshop.service;


import mk.ukim.finki.wp.eshop.model.Termin;
import mk.ukim.finki.wp.eshop.model.User;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public interface TerminService {

    List<Termin> findAll();

    Optional<Termin> findById(Long id);

    Optional<Termin> findByDate(Calendar date);

    Optional<Termin> save(Calendar date);

    Optional<Termin> edit(Long id, Calendar date);

    Optional<Termin> zakazhi(Termin termin, User user);

    void deleteById(Long id);
}
