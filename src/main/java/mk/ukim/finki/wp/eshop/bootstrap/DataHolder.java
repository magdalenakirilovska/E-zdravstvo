package mk.ukim.finki.wp.eshop.bootstrap;

import lombok.Getter;
import mk.ukim.finki.wp.eshop.model.Role;
import mk.ukim.finki.wp.eshop.model.Termin;
import mk.ukim.finki.wp.eshop.model.User;
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

    public DataHolder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        users.add(new User("magdalena.kirilovska", passwordEncoder.encode("mk"),"Magdalena","Kirilovska", Role.ROLE_USER));
        users.add(new User("teona.kostova", passwordEncoder.encode("tk"),"Teona","Kostova", Role.ROLE_USER));
        users.add(new User("karolina.andovska",passwordEncoder.encode("ka"),"Karolina","Andovska", Role.ROLE_USER));
        users.add(new User("stefani.grbeva",passwordEncoder.encode("sg"),"Stefani","Grbeva", Role.ROLE_USER));
        users.add(new User("doktor1", passwordEncoder.encode("doktor1"), "Doktor1", "", Role.ROLE_USER));
        users.add(new User("doktor2", passwordEncoder.encode("doktor2"), "Doktor2", "", Role.ROLE_USER));
        users.add(new User("doktor3", passwordEncoder.encode("doktor3"), "Doktor3", "", Role.ROLE_USER));
        users.add(new User("admin", passwordEncoder.encode("admin"), "Admin", "", Role.ROLE_ADMIN));




        termini.add(new Termin(new GregorianCalendar(2021, 0, 5, 11, 15)));
        termini.add(new Termin(new GregorianCalendar(2021, 1, 9, 13, 0)));
        termini.add(new Termin(new GregorianCalendar(2021, 11, 9, 13, 0)));
        termini.add(new Termin(new GregorianCalendar(2021, 12, 9, 13, 0)));
    }
}
