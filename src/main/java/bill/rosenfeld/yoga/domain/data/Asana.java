package bill.rosenfeld.yoga.domain.data;

public class Asana {

    private final String name;
    private final String description;

    public static final Asana of(String name, String description) {
        return new Asana(name, description);
    }

    private Asana(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
