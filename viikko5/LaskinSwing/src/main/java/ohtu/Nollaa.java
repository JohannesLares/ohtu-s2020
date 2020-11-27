package ohtu;

public class Nollaa implements Komento {

    private int ed = 0;

    @Override
    public int peru() {
        return 0;
    }

    @Override
    public int suorita(int a, int b) {
        ed = b;
        return 0;
    }
}