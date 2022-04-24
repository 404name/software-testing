package com.juelunn.class05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @program: softTest1
 * @description:
 * @author: CTGU_LLZ(404name)
 * @create: 2022-04-24 18:12
 **/
class LinkedListTest {
    public LinkedList<String> list1 = new LinkedList<String>();
    public LinkedList<String> list2 = new LinkedList<String>();
    public LinkedList<String> list3 = new LinkedList<String>();
    public LinkedList<String> list4 = new LinkedList<String>();
    @Test
    void remove() {
        list2.add("a");
        list2.add("b");
        list2.add("c");
        list3.add("a");
        list3.add("b");
        list3.add(null);
        list4.add(null);
        list4.add("a");
        list4.add("b");

        //o=null
        assertEquals(list1.remove((String)null),false);//链表list为null
        assertEquals(list2.remove((String)null),false);//链表list不为null，且无null节点
        assertEquals(list3.remove((String)null),true);//链表list不为空，且存在null节点
        assertEquals(list4.remove((String)null),true);//链表list不为空，但第一个节点为null

        //o！=null
        assertEquals(list1.remove((String)"a"),false);//链表为null
        assertEquals(list2.remove((String)"a"),true);//链表不为null，且链表首节点与传入对象相等
        assertEquals(list2.remove((String)"c"),true);//链表不为null，且链表内存在与传入对象相等的节点
        assertEquals(list2.remove((String)"e"),false);//链表不为null，且链表内不存在与传入对象相等的节点
    }
}
