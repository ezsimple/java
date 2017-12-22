package kr.or.voj.webapp.session;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserInfo<K,V> implements Map<K,V>{
	Map<K, V> map = null;
	public UserInfo(Map<K, V> map){
		this.map = map;
	}
	public V get(Object key) {
		// TODO Auto-generated method stub
		return map.get(key);
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}


	public V put(K key, V value) {
    	throw new RuntimeException("You can not modify user information.");
	}

	public V remove(Object key) {
    	throw new RuntimeException("You can not modify user information.");
	}

	public void putAll(Map<? extends K, ? extends V> m) {
    	throw new RuntimeException("You can not modify user information.");
	}

	public void clear() {
		map.clear();
	}

	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return map.keySet();
	}

	public Collection<V> values() {
		// TODO Auto-generated method stub
		return map.values();
	}

	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

}
