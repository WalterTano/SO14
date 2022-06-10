package grupo4.proyectoso;

import java.util.LinkedHashMap;

public class ColaMultiNivel<K, V> {

    private LinkedHashMap<K, V>[] colaMultiNivel;
    private int ultimoNivel;
    private boolean esVacia;

    public ColaMultiNivel(int cantNiveles) {
        this.colaMultiNivel = new LinkedHashMap[cantNiveles + 1];
        for (int i = 0; i <= cantNiveles; i++) {
            this.colaMultiNivel[i] = new LinkedHashMap<K, V>();
        }
        this.ultimoNivel = 0;
    }

    private boolean nivelValido(int nivel) {
        return nivel < this.colaMultiNivel.length && nivel >= 0;
    }

    public V agregar(K clave, V value, int nivel) {
        if (this.nivelValido(nivel)) {
            this.ultimoNivel = Math.min(this.ultimoNivel, nivel);
            return this.colaMultiNivel[nivel].put(clave, value);
        }
        throw new IllegalArgumentException("Nivel inválido, debe ser mayor o igual a 0 y menor a " + this.colaMultiNivel.length);
    }

    public V remover(K clave, int nivel) {
        if (this.nivelValido(nivel)) {
            return this.colaMultiNivel[nivel].remove(clave);
        }

        return null;
    }

    public V remover(K clave) {
        for (LinkedHashMap<K, V> colaNivel : this.colaMultiNivel) {
            if (colaNivel.containsKey(clave)) {
                return colaNivel.remove(clave);
            }
        }
        return null;
    }

    public V obtener(K clave, int nivel) {
        if (this.nivelValido(nivel)) {
            return this.colaMultiNivel[nivel].get(clave);
        }

        return null;
    }

    public V obtener(K clave) {
        for (LinkedHashMap<K, V> colaNivel : this.colaMultiNivel) {
            if (colaNivel.containsKey(clave)) {
                return colaNivel.get(clave);
            }
        }

        return null;
    }

    public V siguiente() {
        if (this.esVacia()) {
            return null;
        }

        for (;this.ultimoNivel < this.colaMultiNivel.length; this.ultimoNivel++) {
            if (!this.colaMultiNivel[this.ultimoNivel].isEmpty()) {
                K clave = this.colaMultiNivel[this.ultimoNivel].keySet().iterator().next();
                return this.colaMultiNivel[this.ultimoNivel].remove(clave);
            }
        }

        this.ultimoNivel = 0;
        this.esVacia = true;
        return null;
    }

    public boolean esVacia() {
        return this.esVacia;
    }
}
