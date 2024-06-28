class LRUCache {
    int capacity;
    Node head;
    Node tail;
    HashMap<Integer,Node> map;

    class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node(int key,int val) {
            this.key = key;
            this.val = val;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);

        head.next = tail;
        tail.prev = head;
    }

    // TC - O(1)
    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // TC - O(1)
    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }

    // TC-  O(1)
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);

        return node.val;
    }

    // TC -O(1)
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }else{
            if(map.size() == capacity) {
                Node tailPrev = tail.prev;
                map.remove(tailPrev.key);
                removeNode(tailPrev);
            }
            Node newNode = new Node(key,value);
            addToHead(newNode);
            map.put(key, newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
