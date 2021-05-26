package struc;

import java.util.HashMap;

/**
 * @ClassName LRUCache
 * @Description 实现LRU算法
 * @Author liangxp
 * @Date 2021/5/10 16:08
 **/
public class LRUCache {

    class Node{
        public int key;
        public int val;
        public Node next;
        public Node prev;
        public Node(int k, int v){
            this.key = k;
            this.val = v;
        }
    }

    class DoubleList{
        private Node head, tail;

        public DoubleList(){
            head = new Node(0,0);
            tail = new Node(0,0);

            head.next = tail;
            tail.prev = head;
        }

        public void addFirst(Node node){
            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;
        }

        public int remove(Node node){
            int key =  node.key;
            node.next.prev = node.prev;
            node.prev.next = node.next;
            return key;
        }

        public int removeLast(){
            if (head.next == tail){
                return -1;
            }
            return remove(tail.prev);
        }

    }

    private HashMap<Integer,Node> map;
    private DoubleList cache;
    private int cap;

    public LRUCache(int capacity){
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key){
        if (!map.containsKey(key)){
            return -1;
        }
        int val = map.get(key).val;
        put(key,val);
        return val;
    }


    public void put(int key,int value){
        Node node = new Node(key,value);

        if (map.containsKey(key)){
            cache.remove(map.get(key));
            cache.addFirst(node);
            map.put(key,node);
        } else {
            if (cap == map.size()){
                int last = cache.removeLast();
                map.remove(last);
            }
            cache.addFirst(node);
            map.put(key,node);
        }

    }


    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.put(3,3);
        System.out.println(lruCache.get(2));
        lruCache.put(4,4);
        System.out.println(lruCache.map);
        System.out.println(lruCache.cache.removeLast());
        System.out.println(lruCache.cache.removeLast());
        System.out.println(lruCache.cache.removeLast());


    }

}
