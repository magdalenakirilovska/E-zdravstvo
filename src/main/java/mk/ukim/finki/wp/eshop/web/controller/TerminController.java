package mk.ukim.finki.wp.eshop.web.controller;


import mk.ukim.finki.wp.eshop.service.TerminService;
import mk.ukim.finki.wp.eshop.service.UserService;
import mk.ukim.finki.wp.eshop.model.Termin;
import mk.ukim.finki.wp.eshop.model.User;
import mk.ukim.finki.wp.eshop.model.exceptions.TerminNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.GregorianCalendar;
import java.util.List;

@Controller

@RequestMapping("/termin")
public class TerminController {
    private final TerminService terminService;
    private final UserService userService;

    public TerminController(TerminService terminService, UserService userService) {
        this.terminService = terminService;
        this.userService = userService;
    }

    @GetMapping
    public String getTerminPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Termin> termini = this.terminService.findAll();
        model.addAttribute("termini", termini);
        model.addAttribute("bodyContent", "termini");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.terminService.deleteById(id);
        return "redirect:/termin";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.terminService.findById(id).isPresent()) {
            Termin termin = this.terminService.findById(id).get();
            model.addAttribute("termin", termin);
            model.addAttribute("bodyContent", "add-product");
            return "master-template";
        }
        return "redirect:/termin?error=ProductNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProductPage(Model model) {
        model.addAttribute("bodyContent", "add-product");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveTermin(
            @RequestParam(required = false) Long id,
            @RequestParam Integer year,
            @RequestParam Integer month,
            @RequestParam Integer day,
            @RequestParam Integer hour,
            @RequestParam Integer min) {
        if (id != null) {
            this.terminService.edit(id, new GregorianCalendar(year, month-1, day, hour, min));
        } else {
            this.terminService.save(new GregorianCalendar(year, month-1, day, hour, min));
        }
        return "redirect:/termin";
    }

    @PostMapping("/add-termin/{id}")
    public String reserveTermin(@PathVariable Long id, Model model, HttpServletRequest req) {
        User user = (User) userService.loadUserByUsername(req.getRemoteUser());
        Termin termin = terminService.findById(id).orElseThrow(() -> new TerminNotFoundException(id));
        terminService.zakazhi(termin, user);
        return "redirect:/termin";
    }
}
