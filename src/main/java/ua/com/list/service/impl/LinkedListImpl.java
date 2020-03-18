package ua.com.list.service.impl;

import ua.com.list.service.interfaces.LinkedList;

import java.util.*;

public class LinkedListImpl<T> implements LinkedList<T> {
    private Item<T> firstInList = null;

    private Item<T> lastInList = null;

    private int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(final Object o) {
        if (firstInList == null) return false;
        for (T item : this)
            if (Objects.equals(o, item)) return true;

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return (Iterator<T>) new ElementsIterator(0);
    }

    @Override
    public Object[] toArray() {
        final T[] newM = (T[]) new Object[this.size()];
        int i = 0;
        for (T element : this) newM[i++] = element;

        return newM;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            a = (T1[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        }
        int i = 0;

        Object[] result = a;
        for (Item<T> e = firstInList; e != null; e = e.nextItem)
            result[i++] = e.element;
        if (a.length > size) a[size] = null;

        return a;
    }

    @Override
    public boolean add(final T newElement) {
        if (this.size() == 0) {
            this.firstInList = new Item<>(newElement, null, null);
            this.lastInList = firstInList;
        } else if (this.size() == 1) {
            this.lastInList = new Item<>(newElement, this.firstInList, null);
            this.firstInList.nextItem = this.lastInList;
        } else {
            final Item<T> newLast = new Item<>(newElement, this.lastInList, null);
            this.lastInList.nextItem = newLast;
            this.lastInList = newLast;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(final Object o) {
        if (size == 0) return false;

        Item<T> current = this.firstInList;
        while (current.nextItem != null && !current.element.equals(o))
            current = current.nextItem;

        if (current.element.equals(o)) {
            if (this.size() == 1) {
                this.firstInList = null;
                this.lastInList = null;
            } else {
                if (current == firstInList) {
                    firstInList = current.nextItem;
                    firstInList.prevItem = null;
                }
                if (current == lastInList) {
                    lastInList = current.prevItem;
                    lastInList.nextItem = null;
                }
                if (current.nextItem != null && current.prevItem != null) {
                    current.prevItem.nextItem = current.nextItem;
                    current.nextItem.prevItem = current.prevItem;
                }
            }
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        for (final Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        for (final T item : this) {
            if (!c.contains(item)) this.remove(item);
        }
        return true;
    }

    @Override
    public void clear() {
        this.firstInList = null;
        this.lastInList = null;
        size = 0;
    }

    @Override
    public T remove(final int index) throws IndexOutOfBoundsException {
        int i = 0;
        if (size <= index || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Item<T> current = this.firstInList;

        while (i != index) {
            current = current.nextItem;
            i++;
        }

        if (current != null) {
            if (this.size() == 1) {
                this.firstInList = null;
                this.lastInList = null;
            } else {
                if (current == firstInList) {
                    firstInList = current.nextItem;
                    firstInList.prevItem = null;
                }
                if (current == lastInList) {
                    lastInList = current.prevItem;
                    lastInList.nextItem = null;
                }
                if (current.nextItem != null && current.prevItem != null) {
                    current.prevItem.nextItem = current.nextItem;
                    current.nextItem.prevItem = current.prevItem;
                }
            }
            size--;
            return current.element;
        }
        return null;
    }


    private void remove(final Item<T> current) {
        if (current != null) {
            if (this.size() == 1) {
                this.firstInList = null;
                this.lastInList = null;
            } else {
                if (current == firstInList) {
                    firstInList = current.nextItem;
                    firstInList.prevItem = null;
                }
                if (current == lastInList) {
                    lastInList = current.prevItem;
                    lastInList.nextItem = null;
                }
                if (current.nextItem != null && current.prevItem != null) {
                    current.prevItem.nextItem = current.nextItem;
                    current.nextItem.prevItem = current.prevItem;
                }
            }
            size--;
        }
    }

    @Override
    public List<T> subList(final int start, final int end) {
        return null;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ElementsIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(final int index) {
        return new ElementsIterator(index);
    }

    @Override
    public int lastIndexOf(final Object target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(final Object o) {
        int i = 0;
        if (o == null) {
            for (Item<T> current = firstInList; current != null; current = current.nextItem) {
                if (current.element == null)
                    return i;
                i++;
            }
        } else {
            for (Item<T> current = firstInList; current != null; current = current.nextItem) {
                if (o.equals(current.element))
                    return i;
                i++;
            }
        }
        return -1;
    }

    @Override
    public void add(final int index, final T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(final int index, final Collection elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T set(final int index, final T element) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        final Item<T> item = getItemByIndex(index);
        T tempElement = item.element;
        item.element = element;
        return tempElement;
    }

    @Override
    public T get(final int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        int i = 0;
        Item<T> current = firstInList;
        while (i != index) {
            current = current.nextItem;
            i++;
        }
        return current.element;
    }

    @Override
    public LinkedList<T> reverseList() {
        ElementsIterator elementsIterator = new ElementsIterator(size());
        LinkedListImpl<T> newList = new LinkedListImpl<>();
        while (elementsIterator.hasPrevious()){
            newList.add(elementsIterator.previous());
        }

        return newList;
    }

    private Item<T> getItemByIndex(final int index) {
        int i = 0;
        Item<T> current = firstInList;
        while (i != index) {
            current = current.nextItem;
            i++;
        }
        return current;
    }

    private class ElementsIterator implements ListIterator<T> {

        private Item<T> currentItemInIterator;

        private Item<T> lastReturnedItemFromIterator;

        private int index;

        ElementsIterator(final int index) {
            this.currentItemInIterator = (index == size) ? null : getItemByIndex(index);
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return this.index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();

            lastReturnedItemFromIterator = currentItemInIterator;
            currentItemInIterator = currentItemInIterator.getNextItem();
            index++;
            return lastReturnedItemFromIterator.element;
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) throw new NoSuchElementException();

            if (currentItemInIterator == null) {
                lastReturnedItemFromIterator = currentItemInIterator = lastInList;
            } else {
                lastReturnedItemFromIterator = currentItemInIterator = currentItemInIterator.getPrevItem();
            }
            index--;
            return lastReturnedItemFromIterator.element;
        }

        @Override
        public void add(final T element) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(final T element) {
            if (lastReturnedItemFromIterator == null) throw new IllegalStateException();
            lastReturnedItemFromIterator.element = element;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public int nextIndex() {
            return index;
        }


        @Override
        public void remove() {
            if (lastReturnedItemFromIterator == null) throw new IllegalStateException();
            LinkedListImpl.this.remove(lastReturnedItemFromIterator);
            lastReturnedItemFromIterator = null;
            index--;
        }
    }

    private static class Item<T> {

        private T element;

        private Item<T> nextItem;

        private Item<T> prevItem;

        Item(final T element, final Item<T> prevItem, final Item<T> nextItem) {
            this.element = element;
            this.nextItem = nextItem;
            this.prevItem = prevItem;
        }


        public Item<T> getNextItem() {
            return nextItem;
        }

        public Item<T> getPrevItem() {
            return prevItem;
        }
    }
}
