package bstmap;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private class Node {
        public Node l;
        public Node r;
        public Node parent;
        public K key;
        public V value;

        public Node(K k, V v) {
            l = null;
            r = null;
            key = k;
            value = v;
        }
    }

    private class BSTMapIter implements Iterator<K>{

        public boolean hasNext(){
            return false;
        }

        public K next(){
            return null;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        Node root = null;
        size = 0;
    }

    public void printInOrder() {

    }


    /** Removes all of the mappings from this map. */
    public void clear(){
        root = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        return process_containsKey(root, key);
    }

    private boolean process_containsKey(Node cur, K key) {
        if(cur == null) {
            return false;
        }
        if(key.equals(cur.key)) {
            return true;
        }
        else if(key.compareTo(cur.key) < 0) {
            return process_containsKey(cur.l, key);
        }
        else {
            return process_containsKey(cur.r, key);
        }
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        return process_get(root, key);
    }

    private V process_get(Node cur, K key) {
        if(cur == null) {
            return null;
        }
        if(key.equals(cur.key)) {
            return cur.value;
        }
        else if(key.compareTo(cur.key) < 0) {
            return process_get(cur.l, key);
        }
        else {
            return process_get(cur.r, key);
        }
    }

    /* Returns the number of key-value mappings in this map. */
    public int size(){
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value){
        Node cur =root;
        if(root == null) {
            root = new Node(key, value);
            size++;
            return;
        }
        while(true) {
            if(key.equals(cur.key)) {
                break;
            }
            if(key.compareTo(cur.key) < 0) {
                if(cur.l ==null) {
                    cur.l = new Node(key, value);
                    break;
                }
                cur = cur.l;
            }
            else {
                if(cur.r ==null) {
                    cur.r = new Node(key, value);
                    break;
                }
                cur = cur.r;
            }
        }
        size++;
    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet(){
//        UnsupportedOperationException UnsupportedOperationException = new UnsupportedOperationException();
//        throw UnsupportedOperationException;
        Set<K> set = new HashSet<K>();
        process_keySet(set, root);
        return set;
    }

    private void process_keySet(Set<K> set, Node cur){
        if(cur == null){
            return ;
        }
        set.add(cur.key);
        process_keySet(set, cur.l);
        process_keySet(set, cur.r);
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key){
//        UnsupportedOperationException UnsupportedOperationException = new UnsupportedOperationException();
//        throw UnsupportedOperationException;
        Node cur = root;
        Node pre = null;
        while(cur != null) {
            if(cur.key.equals(key)){
                V value = cur.value;
                process_remove(cur, pre);
                return value;
            }
            pre = cur;
            if(cur.key.compareTo(key) < 0){
                cur = cur.l;
            }
            else {
                cur = cur.r;
            }
        }
        return null;
    }

    private void process_remove(Node cur, Node pre) {
        if(pre == null){
            pre = new Node(null, null);
        }
        if(cur.l == null && cur.r == null){
            if(pre.l == cur){
                pre.l = null;
            }
            else{
                pre.r = null;
            }
        }
        else if(cur.l == null || cur.r == null){
            Node next = (cur.l == null) ? cur.r : cur.l;
            if(pre.l == cur){
                pre.l = next;
            }
            else{
                pre.r = next;
            }
        }
        else {
            cur = cur.l;
            Node preCur = null;
            while(cur.r != null){
                preCur = cur;
                cur = cur.r;
            }
            preCur.r = null;
            if(pre.l == cur){
                pre.l = cur;
            }
            else{
                pre.r = cur;
            }
        }
    }


    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value){
//        UnsupportedOperationException UnsupportedOperationException = new UnsupportedOperationException();
//        throw UnsupportedOperationException;
        Node cur = root;
        Node pre = null;
        while(cur != null) {
            if(cur.key.equals(key) && cur.value.equals(value)){
                process_remove(cur, pre);
                return value;
            }
            pre = cur;
            if(cur.key.compareTo(key) < 0){
                cur = cur.l;
            }
            else {
                cur = cur.r;
            }
        }
        return null;
    }

    public Iterator<K> iterator(){
        UnsupportedOperationException UnsupportedOperationException = new UnsupportedOperationException();
        throw UnsupportedOperationException;
    }
}
