package com.loan.stl.utils.SPreferences;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * @author russell
 * @time 2019/3/7 - 20:35
 * @email igruwestbrook@gmail.com
 */
public class SoftHashMap<K, V> {
    private SoftReference<HashMap<K, V>> innerMap = new SoftReference<>(null);

    /**
     * 向map中存入Key-Value
     */
    public void put(K k, V v) {
        HashMap<K, V> map = innerMap.get();
        if (map == null) {
            map = new HashMap<>();
            map.put(k, v);
            innerMap = new SoftReference<>(map);
        } else {
            map.put(k, v);
        }
    }

    /**
     * 从map中获取Key对应的Value
     */
    public V get(K k) {
        final HashMap<K, V> map = innerMap.get();
        if (map != null) {
            return map.get(k);
        }
        return null;
    }

    /**
     * 从map中删除该Key对应的value
     */
    public void remove(K k) {
        final HashMap<K, V> map = innerMap.get();
        if (map != null && map.containsKey(k)) {
            map.remove(k);
        }
    }

    /**
     * map中是否有此键值对
     */
    public boolean containsKey(K k) {
        HashMap<K, V> map = innerMap.get();
        return map != null && map.containsKey(k);
    }
}

