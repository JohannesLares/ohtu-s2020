package ohtu;

import java.util.*;

public class Komentotehdas {
    private HashMap<String, Komento> komennot;
    private Komento tuntematon;

    public Komentotehdas() {
        komennot = new HashMap<String, Komento>();
        komennot.put("+", new Add());
        komennot.put("-", new Subtract());
        komennot.put("Z", new Nollaa());
        tuntematon = new Nollaa();
    }

    public Komento hae(String o) {
        return komennot.getOrDefault(o, tuntematon);
    }
}