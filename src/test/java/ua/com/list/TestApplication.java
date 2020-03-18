package ua.com.list;


import org.junit.Before;
import org.junit.Test;
import ua.com.list.service.impl.LinkedListImpl;
import ua.com.list.service.interfaces.LinkedList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestApplication {

    private LinkedList<String> list = new LinkedListImpl<>();

    @Before
    public void cleanList() {
        list.clear();
    }

    @Test
    public void sizeTest() {
        assertEquals(list.size(), 0);

        fillList();

        assertEquals(list.size(), 3);
    }

    @Test
    public void emptyTest() {
        assertTrue(list.isEmpty());

        fillList();

        assertTrue(!list.isEmpty());
    }

    @Test
    public void addTest(){
        assertEquals(list.size(), 0);
        list.add("Test1");
        list.add("Test2");
        list.add("Test3");
        list.add("Test4");
        list.add("Test5");
        assertEquals(list.size(), 5);
    }

    @Test
    public void containsTest() {
        assertTrue(!list.contains("Text1"));

        list.add("Text1");

        assertTrue(list.contains("Text1"));
    }

    @Test
    public void removeByObjectTest() {
        fillList();

        assertEquals(list.size(), 3);

        list.remove("Test1");

        assertEquals(list.size(), 2);

        list.remove(0);

        assertEquals(list.size(), 1);
    }

    @Test
    public void containsAllTest() {
        fillList();

        List<String> testList = new ArrayList<>();
        testList.add("Test1");
        testList.add("Test2");

        assertTrue(list.containsAll(testList));
    }

    @Test
    public void addAllTest() {
        List<String> testList = new ArrayList<>();
        testList.add("Test1");
        testList.add("Test2");

        list.addAll(testList);

        assertEquals(list.size(), 2);
    }

    @Test
    public void removeAll() {
        fillList();

        assertEquals(list.size(), 3);

        List<String> testList = new ArrayList<>();
        testList.add("Test1");
        testList.add("Test2");

        list.removeAll(testList);

        assertEquals(list.size(), 1);
    }

    @Test
    public void retainAllTest() {
        fillList();

        assertEquals(list.size(), 3);

        List<String> testList = new ArrayList<>();
        testList.add("Test1");
        testList.add("Test2");

        list.retainAll(testList);

        assertEquals(list.size(), 2);
    }

    @Test
    public void clearTest() {
        fillList();
        assertEquals(list.size(), 3);
        list.clear();
        assertEquals(list.size(), 0);
    }

    @Test
    public void indexOfTest() {
        fillList();
        assertEquals(list.indexOf("Test2"), 1);
    }

    @Test
    public void setTest() {
        fillList();
        assertEquals(list.indexOf("Test2"), 1);
        list.set(1, "Test4");
        assertEquals(list.indexOf("Test4"), 1);
    }

    @Test
    public void getTest() {
        fillList();
        assertEquals(list.get(1), "Test2");
    }

    @Test
    public void reverseListTest() {
        fillList();
        LinkedList<String> testList = list.reverseList();
        assertEquals(testList.get(0), "Test3");
        assertEquals(testList.get(1), "Test2");
        assertEquals(testList.get(2), "Test1");
    }

    private void fillList() {
        list.add("Test1");
        list.add("Test2");
        list.add("Test3");
    }
}
