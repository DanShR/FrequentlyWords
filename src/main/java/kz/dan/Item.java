package kz.dan;

import java.util.List;

public class Item implements Comparable<Item> {
    public Item(int count, List<Character> characters) {
        this.count = count;
        this.characters = characters;
    }

    private final int count;
    private final List<Character> characters;

    public int getCount() {
        return count;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    @Override
    public int compareTo(Item otherItem) {
        int countCompare = Integer.compare(otherItem.getCount(), this.count);
        if (countCompare != 0) {
            return countCompare;
        }
        int minSize = Math.min(otherItem.getCount(), this.count);
        for (int i = 0; i < minSize; i++) {
            int compare = Character.compare(otherItem.characters.get(i), this.characters.get(i));
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(otherItem.getCharacters().size(), this.characters.size());
    }
}
