// just a class thats a string tuple, made making the shaped crafting easier.
public class STuple {

    String element1;
    String element2;

    public STuple(String ele1, String ele2) {
        element1 = ele1;
        element2 = ele2;
    }

    public String get_first() {
        return element1;
    }

    public String get_second() {
        return element2;
    }
}
