package ru.ctddev.ifmo.filippov.lru;

import java.util.Optional;
import java.util.Random;

public class Main {

    private static <K, V> V get(LRUHashMap<K, V> map, K key) {
        Optional<V> value = map.get(key);
        if (value.isPresent()) {
            return value.get();
        }
        return null;
    }

    public static void main(String[] args) {
        LRUHashMap<String, Integer> map = new LRUHashMap<>();
        map.put("masha", 18);
        map.put("dasha", 21);
        map.put("evgeny", 21);
        map.put("dima", 19);
        System.out.println(get(map, "dima"));
        System.out.println(get(map, "masha"));
        System.out.println(get(map, "evgeny"));
        System.out.println(get(map, "dasha"));
        final int length = 10;
        final Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            StringBuilder randomString = new StringBuilder();
            for (int j = 0; j < length; j++) {
                randomString.append((char)(random.nextInt('z' - 'a' + 1) + 'a'));
            }
            int value = random.nextInt(100) + 1;
            map.put(randomString.toString(), value);
            assert map.size() <= 1000 : "Too large map size";
            Optional<Integer> v = map.get(randomString.toString());
            assert v.isPresent() && v.get() == value;
        }
    }
}
