class LRUCache {
    class Node{
        int key;
        int val;
        Node next; Node prev;
        public Node(int key, int val)
        {
            this.key=key;
            this.val=val;
        }
    }
    private Node head;
    private Node tail;
    private int cap;
    HashMap<Integer,Node> map;

    public LRUCache(int capacity) {
        this.map= new HashMap<>();
        this.head=new Node(-1,-1);
        this.tail= new Node(-1,-1);
        this.head.next=this.tail;
        this.tail.prev=this.head;
        this.cap=capacity;
    }
    private void removeNode(Node node)
    {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    private void addToHead(Node node)
    {
        node.prev=head;
        node.next = head.next;
        head.next=node;
        node.next.prev=node;
    }
    public int get(int key) {
        if(!map.containsKey(key))
        return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key))
        {
              Node node=  map.get(key);
              node.val=value;
              removeNode(node);
            addToHead(node);
        }
        else{
            if(map.size()==cap)
            {
                //remove tail prev
                Node tailprev = this.tail.prev;
                removeNode(tailprev);
                map.remove(tailprev.key);
            }
            Node newNode = new Node(key,value);
            addToHead(newNode);
            map.put(key,newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

//Time Complexity (TC):
//get(key): O(1)
//put(key, value): O(1)
//Space Complexity (SC): O(capacity)