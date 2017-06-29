package com.example.a29149.thb.exp4;

/**
 * Created by 张丽华 on 2017/6/14.
 */
public class AVLTreeNode<T> {

    T key;
    AVLTreeNode<T> leftchild;
    AVLTreeNode<T> rightchild;
    int height;

    public AVLTreeNode(T key) {
        this.key = key;
    }


    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public AVLTreeNode<T> getLeftchild() {
        return leftchild;
    }

    public void setLeftchild(AVLTreeNode<T> leftchild) {
        this.leftchild = leftchild;
    }

    public AVLTreeNode<T> getRightchild() {
        return rightchild;
    }

    public void setRightchild(AVLTreeNode<T> rightchild) {
        this.rightchild = rightchild;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AVLTreeNode(T key, AVLTreeNode<T> leftchild, AVLTreeNode<T> rightchild, int height) {
        this.key = key;
        this.leftchild = leftchild;
        this.rightchild = rightchild;
        this.height = height;
    }
}
