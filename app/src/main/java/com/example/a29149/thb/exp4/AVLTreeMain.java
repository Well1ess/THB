package com.example.a29149.thb.exp4;

import java.util.Random;

/**
 * Created by 张丽华 on 2017/6/14.
 */
public class AVLTreeMain<T extends Comparable<? super T>> {
    private AVLTreeNode<T> root;

    public AVLTreeMain() {
        root = null;
    }

    public void insert(T x) {
        root = insert(x, root);
    }

    private AVLTreeNode<T> insert(T x, AVLTreeNode<T> t) {
        if (t == null) {
            return new AVLTreeNode<T>(x);
        }
        int compareresult = x.compareTo(t.key);
        if (compareresult < 0) {
            t.leftchild = insert(x, t.leftchild);
            if (height(t.leftchild) - height(t.rightchild) == 2) {
                if (x.compareTo(t.leftchild.key) < 0) {
                    t = rotatewithleftchild(t);
                } else {
                    t = doublerotatewithleftchild(t);
                }
            }
        } else if (compareresult > 0) {
            t.rightchild = insert(x, t.rightchild);
            if (height(t.rightchild) - height(t.leftchild) == 2)
                if (x.compareTo(t.rightchild.key) > 0)
                    t = rotatewithrightchild(t);
                else
                    t = doublerotatewithrightchild(t);
        } else {

        }
        t.height = Math.max(height(t.leftchild), height(t.rightchild)) + 1;
        return t;
    }


    public void delete(T x) {
        root = delete(x, root);
    }

    private AVLTreeNode<T> delete(T x, AVLTreeNode<T> t) {
        if (t == null) return null;
        int compareresult = x.compareTo(t.key);
        if (compareresult < 0) {
            t.leftchild = delete(x, t.leftchild);
            if (t.rightchild != null) {
                if (t.leftchild == null) {
                    if (height(t.rightchild) - t.height == 2) {
                        AVLTreeNode<T> k = t.rightchild;
                        if (k.rightchild != null) {
                            t = rotatewithrightchild(t);
                        } else {
                            t = doublerotatewithrightchild(t);
                        }
                    }
                } else {

                    AVLTreeNode<T> k = t.leftchild;

                    if (k.rightchild != null) {
                        if (height(k.leftchild) - height(k.rightchild) == 2) {
                            AVLTreeNode<T> m = k.leftchild;
                            if (m.leftchild != null) {
                                k = rotatewithleftchild(k);
                            } else {
                                k = doublerotatewithleftchild(k);
                            }
                        }
                    } else {
                        if (height(k.leftchild) - k.height == 2) {
                            AVLTreeNode<T> m = k.leftchild;
                            if (m.leftchild != null) {
                                k = rotatewithleftchild(k);
                            } else {
                                k = doublerotatewithleftchild(k);
                            }
                        }
                    }
                    if (height(t.rightchild) - height(t.leftchild) == 2) {
                        t = rotatewithrightchild(t);
                    }
                }
            }
            t.height = Math.max(height(t.leftchild), height(t.rightchild)) + 1;
        } else if (compareresult > 0) {
            t.rightchild = delete(x, t.rightchild);
            if (t.leftchild != null) {
                if (t.rightchild == null) {
                    if (height(t.leftchild) - t.height == 2) {
                        AVLTreeNode<T> k = t.leftchild;
                        if (k.leftchild != null) {
                            t = rotatewithleftchild(t);
                        } else {
                            t = doublerotatewithleftchild(t);
                        }
                    }
                } else {
                    AVLTreeNode<T> k = t.rightchild;
                    if (k.leftchild != null) {
                        if (height(k.rightchild) - height(k.leftchild) == 2) {
                            AVLTreeNode<T> m = k.rightchild;
                            if (m.rightchild != null) {
                                k = rotatewithrightchild(k);
                            } else {
                                k = doublerotatewithrightchild(k);
                            }
                        }
                    } else {
                        if (height(k.rightchild) - k.height == 2) {
                            AVLTreeNode<T> m = k.rightchild;
                            if (m.rightchild != null) {
                                k = rotatewithrightchild(k);
                            } else {
                                k = doublerotatewithrightchild(k);
                            }
                        }
                    }
                    if (height(t.leftchild) - height(t.rightchild) == 2) {
                        t = rotatewithleftchild(t);
                    }
                }
            }
            t.height = Math.max(height(t.leftchild), height(t.rightchild)) + 1;
        } else if (t.leftchild != null && t.rightchild != null) {

            t.key = findmin(t.rightchild).key;
            t.rightchild = delete(t.key, t.rightchild);
            if (t.rightchild == null) {
                if (height(t.leftchild) - t.height == 2) {
                    AVLTreeNode<T> k = t.leftchild;
                    if (k.leftchild != null) {
                        t = rotatewithleftchild(t);
                    } else {
                        t = doublerotatewithleftchild(t);
                    }
                }
            } else {
                AVLTreeNode<T> k = t.rightchild;
                if (k.leftchild != null) {
                    if (height(k.rightchild) - height(k.leftchild) == 2) {
                        AVLTreeNode<T> m = k.rightchild;
                        if (m.rightchild != null) {
                            k = rotatewithrightchild(k);
                        } else {
                            k = doublerotatewithrightchild(k);
                        }
                    }
                } else {
                    if (height(k.rightchild) - k.height == 2) {
                        AVLTreeNode<T> m = k.rightchild;
                        if (m.rightchild != null) {
                            k = rotatewithrightchild(k);
                        } else {
                            k = doublerotatewithrightchild(k);
                        }
                    }
                }

                if (height(t.leftchild) - height(t.rightchild) == 2) {
                    t = rotatewithleftchild(t);
                }
            }

            t.height = Math.max(height(t.leftchild), height(t.rightchild)) + 1;
        } else {
            t = (t.leftchild != null) ? t.leftchild : t.rightchild;
        }
        return t;
    }


    private AVLTreeNode<T> doublerotatewithrightchild(AVLTreeNode<T> t) {
        t.rightchild = rotatewithleftchild(t.rightchild);
        return rotatewithrightchild(t);
    }

    private AVLTreeNode<T> rotatewithrightchild(AVLTreeNode<T> t) {
        AVLTreeNode<T> k2 = t.rightchild;
        t.rightchild = k2.leftchild;
        k2.leftchild = t;
        t.height = Math.max(height(t.leftchild), height(t.rightchild)) + 1;
        k2.height = Math.max(height(k2.rightchild), t.height) + 1;
        return k2;
    }

    private AVLTreeNode<T> doublerotatewithleftchild(AVLTreeNode<T> t) {
        t.leftchild = rotatewithrightchild(t.leftchild);
        return rotatewithleftchild(t);
    }

    private AVLTreeNode<T> rotatewithleftchild(AVLTreeNode<T> t) {
        AVLTreeNode<T> k1 = t.leftchild;
        t.leftchild = k1.rightchild;
        k1.rightchild = t;
        t.height = Math.max(height(t.leftchild), height(t.rightchild)) + 1;
        k1.height = Math.max(height(k1.leftchild), t.height) + 1;
        return k1;
    }

    private int height(AVLTreeNode<T> t) {
        return t == null ? -1 : t.height;
    }

    private AVLTreeNode<T> findmin(AVLTreeNode<T> t) {
        if (t == null) return null;
        if (t.leftchild == null) return t;
        return findmin(t.leftchild);
    }

    private void print(AVLTreeNode<T> tree, T key, int direction) {
        if (tree != null) {
            if (direction == 0)
                System.out.printf("%2d 是根节点\n", tree.key);
            else
                System.out.printf("%2d 是 %2d 的 %6s 孩子\n", tree.key, key, direction == 1 ? "right" : "left");

            print(tree.leftchild, tree.key, -1);
            print(tree.rightchild, tree.key, 1);
        }
    }

    public void print() {
        if (root != null)
            print(root, root.key, 0);
    }

    public static void main(String[] args) {
        Random random = new Random();
        AVLTreeMain<Integer> avltree = new AVLTreeMain<Integer>();
        int a[] = new int[10];
        System.out.print("原始数据：");
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(100);
            System.out.printf("%d ", a[i]);
        }
        System.out.println();
        System.out.println("————————————————————————————");
        System.out.println("初始化中：");
        for (int i = 0; i < a.length; i++) {
            System.out.println("增加结点为：" + a[i]);
            avltree.insert(a[i]);
            System.out.println("详细信息如下：");
            avltree.print();
            System.out.println();
        }

        System.out.println("————————————————————————————");
        System.out.println("删除中：");
        System.out.println("删除结点：" + a[0]);
        avltree.delete(a[0]);
        System.out.println("删除后详细信息如下：");
        avltree.print();

    }

}


