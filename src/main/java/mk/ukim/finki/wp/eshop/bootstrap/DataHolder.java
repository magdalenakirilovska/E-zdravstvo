package mk.ukim.finki.wp.eshop.bootstrap;

import lombok.Getter;
import mk.ukim.finki.wp.eshop.model.Role;
import mk.ukim.finki.wp.eshop.model.Termin;
import mk.ukim.finki.wp.eshop.model.User;
import mk.ukim.finki.wp.eshop.repository.impl.TerminRepository;
import mk.ukim.finki.wp.eshop.repository.impl.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<User> users = new ArrayList<>();
    public static List<Termin> termini = new ArrayList<>();
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TerminRepository terminRepository;


    public DataHolder(PasswordEncoder passwordEncoder, UserRepository userRepository, TerminRepository terminRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.terminRepository = terminRepository;
    }

    @PostConstruct
    public void init() {
        /*users.add(new User("magdalena.kirilovska", passwordEncoder.encode("mk"),"Magdalena","Kirilovska", Role.ROLE_USER));
        users.add(new User("admin", passwordEncoder.encode("admin"), "Admin", "", Role.ROLE_ADMIN));
        userRepository.saveAll(users);

        termini.add(new Termin(new GregorianCalendar(2021, 0, 5, 11, 15)));
        termini.add(new Termin(new GregorianCalendar(2021, 1, 9, 13, 0)));
        termini.add(new Termin(new GregorianCalendar(2021, 11, 9, 13, 0)));
        termini.add(new Termin(new GregorianCalendar(2021, 12, 9, 13, 0)));
        terminRepository.saveAll(termini);*/
    }
}
