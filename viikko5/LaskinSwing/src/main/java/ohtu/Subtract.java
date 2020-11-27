package ohtu;

public class Subtract implements Komento {

    private int ed = 0;

    @Override
    public int peru() {
        return ed;
    }

    @Override
    public int suorita(int luku, int tulos) {
        ed = tulos;
        return tulos-luku;
    }
}