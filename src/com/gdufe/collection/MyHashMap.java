package com.gdufe.collection;

import java.util.Objects;

public class MyHashMap<K, V> {

    // ============ 1. 数据结构 ============
    // HashMap 的真身 = 一个数组 + 每个格子里挂一条链表（桶）
    // 数组的格子叫「桶」；多个键撞进同一个桶时，用链表串起来（拉链法解决哈希冲突）

    // 内部类：只给 MyHashMap 自己用的辅助类。
    // static + 私有 = 不依赖外部对象、不对外暴露（封装，TreeNode 课的老知识）
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next; // 同桶的下一个节点——链表结构（ListNode 课的老知识）

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V>[] table; // 桶数组
    private int size;           // 实际键值对个数（size ≠ 容量，MyArrayList 同款）

    private static final int DEFAULT_CAPACITY = 16; // JDK 默认初始容量
    private static final double LOAD_FACTOR = 0.75; // 负载因子：装到 75% 就扩容

    public MyHashMap() {
        // 泛型数组不能直接 new Node<K,V>[16]（泛型擦除：运行时没有 K、V），
        // 只能造普通数组再强转。黄字 warning 是擦除的代价，预期内。
        @SuppressWarnings("unchecked")
        Node<K, V>[] t = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
        table = t;
    }

    // ============ 2. hash → 桶下标 ============
    private int indexFor(Object key) {
        // key.hashCode()：Object 给每个对象发的「指纹」整数（昨天学的 Object 是祖宗）
        // 下标 = 指纹 & (容量-1)：容量永远是 2 的幂（16/32/64...），
        // 此时「& (len-1)」等价于「% len」但快得多——位运算的价值，342 题你见过
        if (key == null) {
            return 0; // null 键固定放 0 号桶（真 HashMap 也这么干）
        }
        return key.hashCode() & (table.length - 1);
    }

    // ============ 3. put ============
    public void put(K key, V value) {
        int idx = indexFor(key);

        // 第一步：这个桶里有没有同 key？有 → 覆盖 value 就完事
        for (Node<K, V> n = table[idx]; n != null; n = n.next) {
            if (Objects.equals(n.key, key)) { // 比内容用 equals（老知识点）
                n.value = value;
                return;
            }
        }

        // 第二步：没有 → 头插新节点（头插最省事；
        // JDK 8 起改尾插是因为头插在多线程扩容时会成环，阶段 1 后期讲）
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = table[idx];
        table[idx] = newNode;
        size++;

        // 第三步：装得超过负载因子了？扩容（16×0.75=12，第 13 个触发）
        if (size > table.length * LOAD_FACTOR) {
            resize();
        }
    }

    // ============ 4. get ============
    public V get(Object key) {
        int idx = indexFor(key); // 先算出它在哪个桶
        for (Node<K, V> n = table[idx]; n != null; n = n.next) {
            if (Objects.equals(n.key, key)) {
                return n.value; // 桶里挨个比对（链表遍历，206 题的老技能）
            }
        }
        return null; // 找不到返回 null（真 HashMap 同款行为）
    }

    // ============ 5. remove ============
    public V remove(Object key) {
        int idx = indexFor(key);
        Node<K, V> prev = null; // 链表删除必须记前驱——不然接不上断点（老知识）
        for (Node<K, V> n = table[idx]; n != null; prev = n, n = n.next) {
            if (Objects.equals(n.key, key)) {
                if (prev == null) {
                    table[idx] = n.next; // 删的是链头 → 桶直接指向下家
                } else {
                    prev.next = n.next; // 删的是中间/尾巴 → 前驱跨过它
                }
                size--;
                return n.value;
            }
        }
        return null;
    }

    // ============ 6. 扩容 rehash（HashMap 最核心的动作）============
    // 为什么必须 rehash：下标 = hash & (len-1)，
    // 数组长度一变，同一个 key 算出的下标就变！
    // 所以扩容 = 造双倍大新数组 + 每个节点按「新长度」重算下标、重新挂桶
    private void resize() {
        @SuppressWarnings("unchecked")
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[table.length * 2];

        for (Node<K, V> bucket : table) {            // 遍历每个旧桶
            for (Node<K, V> n = bucket; n != null;) { // 遍历桶里的链
                Node<K, V> next = n.next;            // 先存下家再搬家（206 题：顺序是生死线）
                int idx = (n.key == null) ? 0 : n.key.hashCode() & (newTable.length - 1);
                n.next = newTable[idx]; // 头插进新桶
                newTable[idx] = n;
                n = next;
            }
        }
        table = newTable; // 换指向（MyArrayList.grow 同款动作）
    }

    public int size() {
        return size;
    }

    public int capacity() { // 测试用，真 HashMap 没有
        return table.length;
    }

    // ============ 7. 测试 ============
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        System.out.println("初始容量: " + map.capacity()); // 16

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        System.out.println(map.get("one"));   // 1
        System.out.println(map.get("two"));   // 2

        map.put("two", 22);                   // 同 key 再放 = 覆盖
        System.out.println(map.get("two"));   // 22

        map.remove("one");
        System.out.println(map.get("one"));   // null（已删除）

        // 扩容观察：容量 16 的 75% = 12，装到第 13 个时扩到 32，到 25 个再扩到 64
        for (int i = 0; i < 30; ++i) {
            map.put("k" + i, i);
        }
        System.out.println("大量元素后的容量: " + map.capacity());
        System.out.println("size: " + map.size());
        System.out.println("抽查 k27: " + map.get("k27")); // 27（扩容后还能取对 → rehash 正确）
    }
}
