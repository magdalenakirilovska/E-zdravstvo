package mk.ukim.finki.wp.eshop.repository.impl;

import mk.ukim.finki.wp.eshop.bootstrap.DataHolder;
import mk.ukim.finki.wp.eshop.model.Termin;
import mk.ukim.finki.wp.eshop.model.User;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryTerminRepository {

    public List<Termin> findAll() {
        return DataHolder.termini;
    }

    public Optional<Termin> findById(Long id) {
        return DataHolder.termini.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public Optional<Termin> findByDate(Calendar date) {
        return DataHolder.termini.stream().filter(i -> i.getDate().equals(date)).findFirst();
    }

    public void deleteById(Long id) {
        DataHolder.termini.removeIf(i -> i.getId().equals(id));
    }

    public void deleteByDate(Calendar date){
        DataHolder.termini.removeIf(n -> n.getDate().equals(date));
    }

    public Termin save(Calendar date) {
        DataHolder.termini.removeIf(i -> i.getDate().equals(date));
        Termin termin = new Termin(date);
        DataHolder.termini.add(termin);
        return termin;
    }

    public Termin save(Termin termin){
        DataHolder.termini.removeIf(i -> i.getDate().equals(termin.getDate()));
        DataHolder.termini.add(termin);
        return termin;
    }

    public Optional<Termin> zakazhi(Termin termin, User user){
        Optional<Termin> t = DataHolder.termini.stream().filter(i -> i.getId().equals(termin.getId())).findFirst();
        System.out.println(t.get().toString());
        t.get().setZakazhanOd(user);
        return t;
    }

}
