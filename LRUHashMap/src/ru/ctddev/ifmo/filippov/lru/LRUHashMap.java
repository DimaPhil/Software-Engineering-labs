package ru.ctddev.ifmo.filippov.lru;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by dmitry on 10.09.16.
 */
class LRUHashMap<K, V> {
    private static final int MAX_ELEMENTS_COUNT = 1000;

    private class Node {
        Node next;
        Node prev;
        V value;
        K key;

        Node(Node next, Node prev, K key, V value) {
            this.next = next;
            this.prev = prev;
            this.value = value;
            this.key = key;
        }

        Node(K key, V value) {
            this(null, null, key, value);
        }

        V getValue() {
            return value;
        }

        K getKey() {
            return key;
        }
    }

    private class LinkedList {
        Node head;
        Node tail;
        int size;

        void addFirst(K key, V value) {
            size++;
            Node node = new Node(key, value);
            if (head == null) {
                head = node;
                tail = node;
                return;
            }
            head.prev = node;
            node.next = head;
            head = node;
            assert head.value == value : "Added element is not first";
        }

        void remove(Node node) {
            if (node == null) {
                throw new IllegalArgumentException("Can't remove null from list");
            }
            size--;
            if (node == head) {
                head = head.next;
                if (tail == head) {
                    tail = null;
                }
                return;
            }
            if (node == tail) {
                tail = tail.prev;
                if (head == tail) {
                    head = null;
                }
                return;
            }
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        Node getFirst() {
            return head;
        }

        Node getLast() {
            return tail;
        }

        int size() {
            return size;
        }
    }

    private Map<K, Node> map;
    private LinkedList cache;

    LRUHashMap() {
        map = new HashMap<>();
        cache = new LinkedList();
    }

    Optional<V> get(K key) {
        Node node = map.get(key);
        if (node == null) {
            return Optional.empty();
        }
        int size = size();
        cache.remove(node);
        cache.addFirst(key, node.getValue());
        map.put(key, cache.getFirst());
        assert size == size() : "Size of map should remain the same after get()";
        assert cache.getFirst().getKey() == key : "Asked element should become the first in list";
        return Optional.of(node.getValue());
    }

    void put(K key, V value) {
        if (cache.size() == MAX_ELEMENTS_COUNT) {
            Node last = cache.getLast();
            cache.remove(last);
            map.remove(last.getKey());
            cache.addFirst(key, value);
            map.put(key, cache.getFirst());
            assert !map.containsKey(last.getKey()) : "Old element should be removed from map";
            assert map.containsKey(key) : "New element should be added to the map";
            assert map.get(key).equals(cache.getFirst()) : "New element should become the first one";
            assert size() == MAX_ELEMENTS_COUNT : "Number of elements should remain MAX_ELEMENTS_COUNT after adding to full map";
            return;
        }
        int size = size();
        cache.addFirst(key, value);
        map.put(key, cache.getFirst());
        assert map.containsKey(key) : "New element should be added to the map";
        assert map.get(key).equals(cache.getFirst()) : "New element should become the first one";
        assert size == size() - 1 : "The size should increase by 1";
    }

    int size() {
        return map.size();
    }
}