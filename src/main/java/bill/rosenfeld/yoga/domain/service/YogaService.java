package bill.rosenfeld.yoga.domain.service;

import bill.rosenfeld.yoga.domain.data.Asana;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class YogaService implements Service {

    private final List<Asana> asanas;

    public YogaService() {
        this.asanas = new ArrayList<>();
        createAsanas();
    }

    public void getAsanas(String id) {

    }

    public void getChakras(String id) {

    }

    private void createAsanas() {
        this.asanas.add(Asana.of("Virabhadrasana I", "Warrior One"));
        this.asanas.add(Asana.of("Virabhadrasana II", "Warrior Two"));
    }

}
