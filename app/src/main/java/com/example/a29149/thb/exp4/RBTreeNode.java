package com.example.a29149.thb.exp4;

/**
 * Created by 张丽华 on 2017/6/14.
 */
public class RBTreeNode<T extends Comparable<T>> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    T key;
    boolean color;
    RBTreeNode<T> leftchild;
    RBTreeNode<T> rightchild;
    RBTreeNode<T> parent;

    public RBTreeNode(T key, boolean color, RBTreeNode<T> leftchild, RBTreeNode<T> rightchild, RBTreeNode<T> parent) {
        this.key = key;
        this.color = color;
        this.leftchild = leftchild;
        this.rightchild = rightchild;
        this.parent = parent;
    }

    public RBTreeNode() {
        // TODO Auto-generated constructor stub
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public RBTreeNode<T> getLeftchild() {
        return leftchild;
    }

    public void setLeftchild(RBTreeNode<T> leftchild) {
        this.leftchild = leftchild;
    }

    public RBTreeNode<T> getRightchild() {
        return rightchild;
    }

    public void setRightchild(RBTreeNode<T> rightchild) {
        this.rightchild = rightchild;
    }

    public RBTreeNode<T> getParent() {
        return parent;
    }

    public void setParent(RBTreeNode<T> parent) {
        this.parent = parent;
    }

    public String toString() {
        return "" + key + (this.color == RED ? "(R)" : "B");
    }

}
