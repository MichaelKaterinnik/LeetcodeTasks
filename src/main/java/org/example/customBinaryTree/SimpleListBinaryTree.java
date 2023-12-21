package org.example.customBinaryTree;

import java.io.Serializable;
import java.util.*;

/**
 * Simple non-selfBalanced Binary Tree with using List and nodes.
 */
public class SimpleListBinaryTree extends AbstractList<String> implements Cloneable, Serializable {
    List<Entry<String>> entryList = new ArrayList<>();
    Entry<String> root;

    public SimpleListBinaryTree() {
        this.root = new Entry<>("0");
        entryList.add(root);
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }
    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }
    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }
    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return entryList.size() - 1;
    }

    @Override
    public boolean add(String s) {
        for (Entry<String> entry : entryList) {
            if (entry.isAvailableToAddChildren()) {
                return insertElement(entry, s);
            }
        }
        return false;
    }
    private boolean insertElement(Entry<String> root, String s) {
        Entry<String> child = new Entry<>(s);
        if (root.availableToAddLeftChildren) {
            root.leftChild = child;
            child.parent = root;
            root.availableToAddLeftChildren = false;
            entryList.add(child);
            return true;
        }
        if (root.availableToAddRightChildren) {
            root.rightChild = child;
            child.parent = root;
            root.availableToAddRightChildren = false;
            entryList.add(child);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof String)) throw new UnsupportedOperationException();
        for (Entry<String> entry : entryList) {
            if (entry.elementName.equals(o)) {
                Entry<String> parentEntry = entry.parent;
                Entry<String> leftChild = entry.leftChild;
                Entry<String> rightChild = entry.rightChild;

                if (parentEntry.leftChild != null && parentEntry.leftChild.equals(entry)) {
                    parentEntry.leftChild = null;
                    parentEntry.availableToAddLeftChildren = true;
                    entry.parent = null;
                    entryList.remove(entry);

                    if (leftChild != null) {
                        removeChildren(leftChild);
                    }
                    if (rightChild != null) {
                        removeChildren(rightChild);
                    }
                }
                if (parentEntry.rightChild != null && parentEntry.rightChild.equals(entry)) {
                    parentEntry.rightChild = null;
                    parentEntry.availableToAddRightChildren = true;
                    entryList.remove(entry);

                    if (leftChild != null) {
                        removeChildren(leftChild);
                    }
                    if (rightChild != null) {
                        removeChildren(rightChild);
                    }
                }
                return true;
            }
        }
        return false;
    }
    public void removeChildren(Entry<String> s) {
        Entry<String> leftChild = s.leftChild;
        Entry<String> rightChild = s.rightChild;
        entryList.remove(s);
        if (leftChild != null) {
            removeChildren(leftChild);
        }
        if (rightChild != null) {
            removeChildren(rightChild);        }

    }

    public String getParent(String s) {
        for (Entry<String> entry : entryList) {
            if (entry.parent != null && entry.elementName.equals(s)) {
                return entry.parent.elementName;
            }
        }
        return null;
    }


    static class Entry<T> implements Serializable {
        String elementName;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren | availableToAddRightChildren;
        }
    }
}
