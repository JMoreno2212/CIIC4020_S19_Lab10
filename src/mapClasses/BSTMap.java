package mapClasses;

import java.util.ArrayList;
import java.util.Comparator;

import interfaces.Entry;
import interfaces.Map;
import positionalStructures.Position;
import treeClasses.LinkedBST;

public class BSTMap<K, V> implements Map<K, V> {
	// the binary search tree supporting this implementation of the map
	private LinkedBST<K, V> tree;   	
	
	/**
	 * Creates an instance of BSTMap. 
	 * @param cmp the comparator of keys that is received and which shall
	 * be used to compare keys of entries. 
	 */
	public BSTMap(Comparator<K> cmp) { 
		tree = new LinkedBST<>(cmp); 
	}
	
	@Override
	/**
	 * the size of the map is the size of the tree. 
	 */
	public int size() {
		return tree.size();
	}

	@Override
	/** 
	 * the map is empty if the tree is empry
	 */
	public boolean isEmpty() {
		return tree.isEmpty();
	}

	@Override
	/**
	 * Get operation of the map. 
	 */
	public V get(K key) {
		// for the entry having given key in the tree, if any
		Entry<K, V> result = tree.get(key); 
		
		// if not found, return null
		if (result == null) return null; 
		
		// if found, the value of the element that matches the search.
		return result.getValue();
	}

	@Override
	/**
	 * put of the map...
	 */
	public V put(K key, V value) {
		ModifiableEntry<K, V> result = (ModifiableEntry<K, V>) tree.get(key); 
		if(result == null) {
			tree.add(key, value);
			return null;
		} else {
			V vtr = result.getValue();
			result.setValue(value);
			return vtr;
		}
	}

	@Override
	/**
	 * remove of the map
	 */
	public V remove(K key) {
		Entry<K, V> result = tree.get(key);
		if(result == null) return null;
		tree.remove(key);
		return result.getValue();
	}

	@Override
	public Iterable<K> keySet() {
		ArrayList<K> keys = new ArrayList<>();
		for(Position<Entry<K,V>> entry : tree.positions()) {
			keys.add(entry.getElement().getKey());
		}
		return keys; 
	}

	@Override
	public Iterable<V> values() {
		ArrayList<V> values = new ArrayList<>();
		for(Position<Entry<K, V>> entry : tree.positions()) {
			values.add(entry.getElement().getValue());
		}
		return values;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K, V>> entrySet = new ArrayList<>();
		for(Position<Entry<K, V>> entry : tree.positions()) {
			entrySet.add(entry.getElement());
		}
		return entrySet;
	}

	public void displayMAPTree() {   // This operation has been added just for testing
		this.tree.display();
	}
		
}
