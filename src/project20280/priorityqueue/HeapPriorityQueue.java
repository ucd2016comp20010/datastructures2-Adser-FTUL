    package project20280.priorityqueue;

    /*
     */

    import project20280.interfaces.Entry;
    import project20280.tree.BinaryTreePrinter;

    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.Comparator;


    /**
     * An implementation of a priority queue using an array-based heap.
     */

    public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

        protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

        /**
         * Creates an empty priority queue based on the natural ordering of its keys.
         */
        public HeapPriorityQueue() {
            super();
        }

        /**
         * Creates an empty priority queue using the given comparator to order keys.
         *
         * @param comp comparator defining the order of keys in the priority queue
         */
        public HeapPriorityQueue(Comparator<K> comp) {
            super(comp);
        }

        /**
         * Creates a priority queue initialized with the respective key-value pairs. The
         * two arrays given will be paired element-by-element. They are presumed to have
         * the same length. (If not, entries will be created only up to the length of
         * the shorter of the arrays)
         *
         * @param keys   an array of the initial keys for the priority queue
         * @param values an array of the initial values for the priority queue
         */
        public HeapPriorityQueue(K[] keys, V[] values) {
            int len = keys.length;

            for (int i = 0; i < len; i++) {
                Entry<K, V> e = new PQEntry<>(keys[i], values[i]);
                heap.add(e);
            }
        }

        // protected utilities
        protected int parent(int j) {
            return (j - 1)/2;
        }

        protected int left(int j) {
            return (2 * j) + 1;
        }

        protected int right(int j) {
            return (2 * j) + 2;
        }

        protected boolean hasLeft(int j) {
            int l = left(j);
            return l < heap.size();
        }

        protected boolean hasRight(int j) {
            int r = right(j);
            return r < heap.size();
        }

        /**
         * Exchanges the entries at indices i and j of the array list.
         */
        protected void swap(int i, int j) {
            Entry<K, V> e1 = heap.get(i);
            Entry<K, V> e2 = heap.get(j);

            heap.set(i, e2);
            heap.set(j, e1);
        }

        /**
         * Moves the entry at index j higher, if necessary, to restore the heap
         * property.
         */
        protected void upheap(int j) {
            while (compare(heap.get(j), heap.get(parent(j))) < 0) {
                swap(j, parent(j));
                j = parent(j);
            }
        }

        /**
         * Moves the entry at index j lower, if necessary, to restore the heap property.
         */
        protected void downheap(int j) {
            while(hasLeft(j)) {
                int smallerIndex = left(j);
                if (hasRight(j)) {
                    if(compare(heap.get(left(j)), heap.get(right(j))) > 0) {
                        smallerIndex = right(j);
                    }
                }

                if (compare(heap.get(j), heap.get(smallerIndex)) > 0) {
                    swap(j, smallerIndex);
                    j = smallerIndex;
                }
                else break;
            }
        }

        /**
         * Performs a bottom-up construction of the heap in linear time.
         */
        public void heapify() {
            if (!heap.isEmpty()) {
                int i = parent(heap.size() - 1);

                for (int j = i; j >= 0; j--) {
                    downheap(j);
                }
            }
        }


        // public methods

        /**
         * Returns the number of items in the priority queue.
         *
         * @return number of items
         */
        @Override
        public int size() {
            return heap.size();
        }

        /**
         * Returns (but does not remove) an entry with minimal key.
         *
         * @return entry having a minimal key (or null if empty)
         */
        @Override
        public Entry<K, V> min() {
            return heap.getFirst();
        }

        /**
         * Inserts a key-value pair and return the entry created.
         *
         * @param key   the key of the new entry
         * @param value the associated value of the new entry
         * @return the entry storing the new key-value pair
         * @throws IllegalArgumentException if the key is unacceptable for this queue
         */
        @Override
        public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
            checkKey(key);
            Entry<K, V> newest = new PQEntry<>(key, value);
            heap.addLast(newest);
            upheap(heap.size() - 1);
            return newest;
        }

        /**
         * Removes and returns an entry with minimal key.
         *
         * @return the removed entry (or null if empty)
         */
        @Override
        public Entry<K, V> removeMin() {
            if (heap.isEmpty()) return null;
            Entry<K, V> minEntry= min();

            swap(0, heap.size() - 1);
            heap.removeLast();

            if (!heap.isEmpty()) {
                downheap(0);
            }

            return minEntry;
        }

        public String toString() {
            return heap.toString();
        }

        /**
         * Used for debugging purposes only
         */
        private void sanityCheck() {
            for (int j = 0; j < heap.size(); j++) {
                int left = left(j);
                int right = right(j);
                //System.out.println("-> " +left + ", " + j + ", " + right);
                Entry<K, V> e_left, e_right;
                e_left = left < heap.size() ? heap.get(left) : null;
                e_right = right < heap.size() ? heap.get(right) : null;
                if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0) {
                    System.out.println("Invalid left child relationship");
                    System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
                }
                if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0) {
                    System.out.println("Invalid right child relationship");
                    System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
                }
            }
        }

        public static void main(String[] args) {
            Integer[] rands = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
            HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>(rands, rands);

            System.out.println("elements: " + Arrays.toString(rands));
            System.out.println("after adding elements: " + pq);

            pq.removeMin();

            System.out.println(pq);


            pq.heapify();

            System.out.println(pq);

            pq.removeMin();

            System.out.println(pq);

            pq.heapify();

            System.out.println(pq);

            // [             1,
            //        2,            4,
            //   23,     21,      5, 12,
            // 24, 26, 35, 33, 15]
        }
    }
