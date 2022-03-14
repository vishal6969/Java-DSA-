public class HashMap<K, V> {

    Pair<K, V>[] hash_table;
    int size;
    int records;

    @SuppressWarnings("unchecked")
    HashMap() {
        this.size = 13;
        this.hash_table = new Pair[size];
    }

    public void put(K key, V value) throws Exception {

        if (records >= size / 2) {
            resize();
        }
        int index = primary_hash(key);
        int prob_index = secondary_hash(key);
        int i = 0;

        while (hash_table[index] != null && hash_table[index].key != key) {
            index = (index + i * prob_index) % size;
            i++;
        }

        hash_table[index] = new Pair<K, V>(key, value);
        this.records++;
    }

    public V get(K key) throws Exception {

        int index = primary_hash(key);
        int prob_index = secondary_hash(key);
        int i = 0;
        while (hash_table[index] != null && !hash_table[index].key.equals(key)) {
            System.out.println(index);
            index = (index + i * prob_index) % size;
            i++;
        }

        return this.hash_table[index] != null ? this.hash_table[index].value : null;
    }

    public void display() {

        for (int i = 0; i < size; i++) {
            System.out.println(hash_table[i] != null ? hash_table[i] : null);
        }
        System.out.println("\nTotal Space: " + this.size);
    }

    public Integer primary_hash(K key) throws Exception {

        if (key instanceof String) {

            char[] ch = ((String) key).toCharArray();
            int sum = 0;

            for (int i = 0; i < ch.length; i++) {

                sum += ch[i];
            }

            return sum % this.size;
        }
        if (key instanceof Integer) {

            return ((Integer) key) % this.size;
        }
        if (key instanceof Character) {

            return ((Character)key - '\0') % this.size;
        }

        throw new UnsupportedOperationException("Keys other than String,Integer not allowed");
    }

    public Integer secondary_hash(K key) throws Exception {

        if (key instanceof String) {

            char[] ch = ((String) key).toCharArray();
            int sum = 0;

            for (int i = 0; i < ch.length; i++) {

                sum += ch[i];
            }

            return 7 - (sum % 7);
        }
        if (key instanceof Integer) {

            return 7 - (((Integer) key) % 7);
        }
        if (key instanceof Character) {
            
            return 7 - (((Character) key - '\0') % 7);
        }

        throw new UnsupportedOperationException("Keys other than [String,Integer,Character] not allowed");
    }

    @SuppressWarnings("unchecked")
    public void resize() throws Exception {

        int temp = size;
        this.size = nextPrime(temp + 1);
        Pair<K, V>[] prev_hash_table = this.hash_table;
        this.hash_table = new Pair[size];
        this.records = 0;

        for (int i = 0; i < temp; i++) {
            if (prev_hash_table[i] != null) {
                put(prev_hash_table[i].key, prev_hash_table[i].value);
            }
        }
    }

    public int nextPrime(int n) {

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return nextPrime(n + 1);
        }
        return n;
    }
}

class Pair<K, V> {

    K key;
    V value;

    Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {

        return key + " " + value;
    }
}
