package collections;

import java.util.Comparator;

public class KeywordComparator implements Comparator<KeywordFrequency> {
    @Override
    public int compare(KeywordFrequency obj1, KeywordFrequency obj2) {
        return obj1.getFreq() - obj2.getFreq();
    }
}
