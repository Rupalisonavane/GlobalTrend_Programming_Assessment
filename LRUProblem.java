import java.util.*;

class LRUCache {
    class ListNode {
        int key;
        int value;
        ListNode prev;
        ListNode next;
        
        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private Map<Integer, ListNode> cache;
    private int capacity;
    private ListNode head;
    private ListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new ListNode(-1, -1); // dummy head
        this.tail = new ListNode(-1, -1); // dummy tail
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        ListNode node = cache.get(key);
        moveToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            ListNode node = cache.get(key);
            node.value = value;
            moveToHead(node); 
        } else {
            ListNode newNode = new ListNode(key, value);
            cache.put(key, newNode);
            addToFront(newNode);
            if (cache.size() > capacity) {
                ListNode toRemove = removeTail();
                cache.remove(toRemove.key);
            }
        }
    }
    
    private void moveToHead(ListNode node) {
        removeNode(node);
        addToFront(node);
    }
    
    private void removeNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void addToFront(ListNode node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    
    private ListNode removeTail() {
        ListNode toRemove = tail.prev;
        removeNode(toRemove);
        return toRemove;
    }
}
public class LRUProblem {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));  
        cache.put(3, 3);    
        System.out.println(cache.get(2));       
        cache.put(4, 4);    
        System.out.println(cache.get(1));       
        System.out.println(cache.get(3));       
        System.out.println(cache.get(4));       
    }
}