package ohtu;

public class Add implements Komento {

    private int ed = 0;

    @Override
    public int peru() {
        return ed;
    }

    @Override
    public int suorita(int arvo, int tulos) {
        ed = tulos;
        return arvo+tulos;
    }
}