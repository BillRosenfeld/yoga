package bill.rosenfeld.yoga.web.controller;

import bill.rosenfeld.yoga.domain.service.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping(value = "/asanas/{id}")
    public ResponseEntity<?> getAsanas(@PathVariable String id) {
        return null;
    }

    @GetMapping(value = "/chakras/{id}")
    public ResponseEntity<?> getChakras(@PathVariable String id) {
        return null;
    }

}
