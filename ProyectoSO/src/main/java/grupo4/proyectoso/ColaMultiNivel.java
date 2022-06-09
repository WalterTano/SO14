package grupo4.proyectoso;

import java.util.Collection;
import java.util.LinkedHashMap;

public class ColaMultiNivel<K, V> {

    private LinkedHashMap<K, V>[] colaMultiNivel;

    public ColaMultiNivel(int cantNiveles) {
        this.colaMultiNivel = new LinkedHashMap[cantNiveles];
    }

    public boolean agregar(K key, V value, int nivel) {
        return this.colaMultiNivel[nivel].put(key, value) != null;
    }

    public V remover(K key, int nivel) {
        return this.colaMultiNivel[nivel].remove(key);
    }

    public V remover(K key) {
        for (LinkedHashMap<K, V> colaNivel : this.colaMultiNivel) {
            if (colaNivel.containsKey(key)) {
                return colaNivel.remove(key);
            }
        }
        return null;
    }
}
