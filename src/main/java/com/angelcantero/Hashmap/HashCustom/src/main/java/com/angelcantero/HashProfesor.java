package com.angelcantero;

import java.util.ArrayList;
import java.util.List;

public class HashProfesor<K, V> {
    private int size;
    private Node<K, V>[][] data;

    public HashProfesor(int capacity) {
        data= new Node[capacity][capacity];
        size=0;
    }



    private int hash (K key){return  hash(key,data);}


    private int hash (K key,Node<K,V>[][] matrix ){
        int hash = key.hashCode();
        hash= hash*31;
        hash=hash & 0xFFFFFFF;
        hash = hash% matrix.length;
        return hash;
    }

    public V put(K key, V value){
        return put(key, value,data);
    }

    private V put(K key, V value,Node<K,V>[][] matrix ){
            int columna = hash(key,matrix);
        int row;
        for ( row = 0; row < matrix.length; row++) {
            Node<K, V> node = matrix[row][columna];
            if(node.getKey()== key ||node== null){
                break;
            }
        }
        if (row==matrix.length){
            resize();
        }
        Node<K, V> oldValue = matrix[row][columna];
        matrix[row][columna]= new Node<>(key, value);
        size++;
        return oldValue==null? null:oldValue.getValue();
    }


    public V remove(K key, V value){
        int columna = hash(key);
        int row;
        for ( row = 0; row < data[columna].length; row++) {
            Node<K, V> node = data[columna][row];
            if(node== null){
                return null;
            }
            if (node.getKey()==key){
                for (int i =row+1;i<data[row].length;i++){
                    data[i-1][columna]=data[i][columna];
                }
                Node<K, V> oldValue = data[row][columna];
                data[row][columna]= null;
                size--;
                return oldValue.getValue();
            }
        }
        return null;
    }



    public V get(K key){
        int columna = hash(key);
        int row;
        for (row = 0; row < data.length; row++) {
            Node<K, V> node = data[row][columna];
            if(node==null){
                return null;
            }


            if(node.getKey().equals(key)){
                return node.getValue();
            }

        }
        return null;
    }

    private void resize(){
        Node<K,V>[][] dataResize= new Node[data.length*2][data.length*2];
        size=0;
        for (int row = 0; row < data.length ; row++) {
            for (int column = 0; column < data[row].length; column++) {
                Node<K,V> node = data[row][column];
                put(node.key, node.value,dataResize);

            }

        }
        data=dataResize;

    }
    public List<V> values(){
        List<V> values = new ArrayList<>();
        for (int column = 0; column < data[0].length; column++) {
            for (int row = 0; row < data[column].length; row++) {
                Node<K, V> node = data[row][column];
                if (node != null) {
                    break;
                }
                values.add(node.getValue());

            }
        }
        return values;
    }
    public List<K> keys(){
        List<K> keys = new ArrayList<>();
        for (int column = 0; column < data[0].length; column++) {
            for (int row = 0; row < data[column].length; row++) {
                Node<K, V> node = data[row][column];
                if (node != null) {
                    break;
                }
                keys.add(node.getKey());

            }
        }
        return keys;
    }

    static class Node<K, V>{
        private K key;
        private V value;
        /*private Node<K, V> next;*/

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

}
