@SuppressWarnings("unchecked")
public class DictionaryOpenAddressing<K, V> implements Dictionary<K, V> {

    private Entry<K, V>[] table;
    private int size;

    private static final int DEFAULT_CAPACITY = 16;

    private final Entry<K, V> DELETED = new Entry<>(null, null);

    public DictionaryOpenAddressing() {
        table = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    @Override
    public V put(K key, V value) {

        int index = hash(key);

        while (table[index] != null && table[index] != DELETED) {

            if (table[index].key.equals(key)) {
                V oldValue = table[index].value;
                table[index].value = value;
                return oldValue;
            }

            index = (index + 1) % table.length;
        }

        table[index] = new Entry<>(key, value);
        size++;

        return null;
    }

    @Override
    public V get(K key) {

        int index = hash(key);

        while (table[index] != null) {

            if (table[index] != DELETED && table[index].key.equals(key)) {
                return table[index].value;
            }

            index = (index + 1) % table.length;
        }

        return null;
    }

    @Override
    public V remove(K key) {

        int index = hash(key);

        while (table[index] != null) {

            if (table[index] != DELETED && table[index].key.equals(key)) {

                V value = table[index].value;
                table[index] = DELETED;
                size--;

                return value;
            }

            index = (index + 1) % table.length;
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}