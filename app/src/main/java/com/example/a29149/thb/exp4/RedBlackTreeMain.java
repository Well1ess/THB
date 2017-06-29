package com.example.a29149.thb.exp4;

import java.util.Random;

/**
 * Created by 张丽华 on 2017/6/14.
 */
public class RedBlackTreeMain<T extends Comparable<T>> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private RBTreeNode<T> root;

    public RedBlackTreeMain() {
        root = null;
    }

    private RBTreeNode<T> parentOf(RBTreeNode<T> node) {
        return node != null ? node.parent : null;
    }

    private boolean colorOf(RBTreeNode<T> node) {
        return node != null ? node.color : BLACK;
    }

    private boolean isRed(RBTreeNode<T> node) {
        return ((node != null) && (node.color == RED)) ? true : false;
    }

    private boolean isBlack(RBTreeNode<T> node) {
        return !isRed(node);
    }

    private void setBlack(RBTreeNode<T> node) {
        if (node != null)
            node.color = BLACK;
    }

    private void setRed(RBTreeNode<T> node) {
        if (node != null)
            node.color = RED;
    }

    private void setParent(RBTreeNode<T> node, RBTreeNode<T> parent) {
        if (node != null)
            node.parent = parent;
    }

    private void setColor(RBTreeNode<T> node, boolean color) {
        if (node != null)
            node.color = color;
    }

    public void leftrotate(RBTreeNode<T> x) {
        RBTreeNode<T> y = x.rightchild;
        x.rightchild = y.leftchild;
        if (y.leftchild != null) {
            y.leftchild.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else {
            if (x.parent.leftchild == x) {
                x.parent.leftchild = y;
            } else {
                x.parent.rightchild = y;
            }
        }
        y.leftchild = x;
        x.parent = y;
    }

    public void rightrotate(RBTreeNode<T> y) {
        RBTreeNode<T> x = y.leftchild;

        y.leftchild = x.rightchild;
        if (x.rightchild != null)
            x.rightchild.parent = y;

        x.parent = y.parent;

        if (y.parent == null) {
            this.root = x;
        } else {
            if (y == y.parent.rightchild)
                y.parent.rightchild = x;
            else
                y.parent.leftchild = x;
        }
        x.rightchild = y;

        y.parent = x;
    }

    public void insert(T key) {
        RBTreeNode<T> node = new RBTreeNode<T>(key, BLACK, null, null, null);
        if (node != null) {
            insert(node);
        }
    }

    public void insert(RBTreeNode<T> node) {
        int cmp;
        RBTreeNode<T> y = null;
        RBTreeNode<T> x = this.root;
        while (x != null) {
            y = x;
            cmp = node.key.compareTo(x.key);
            if (cmp < 0) {
                x = x.leftchild;
            } else {
                x = x.rightchild;
            }
        }

        node.parent = y;

        if (y != null) {
            cmp = node.key.compareTo(y.key);
            if (cmp < 0) {
                y.leftchild = node;
            } else {
                y.rightchild = node;
            }
        } else {
            this.root = node;
        }

        node.color = RED;

        insertFixup(node);
    }

    public void insertFixup(RBTreeNode<T> node) {
        RBTreeNode<T> parent, gparent;
        while (((parent = parentOf(node)) != null) && isRed(parent)) {
            gparent = parentOf(parent);
            if (parent == gparent.leftchild) {
                RBTreeNode<T> uncle = gparent.rightchild;
                if ((uncle != null) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }
                if (parent.rightchild == node) {
                    RBTreeNode<T> tmp;
                    leftrotate(parent);//左旋
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }
                setBlack(parent);
                setRed(gparent);
                rightrotate(gparent);
            } else {

                RBTreeNode<T> uncle = gparent.leftchild;
                if ((uncle != null) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }
                if (parent.leftchild == node) {
                    RBTreeNode<T> tmp;
                    rightrotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }
                setBlack(parent);
                setRed(gparent);
                leftrotate(gparent);
            }
        }
        setBlack(this.root);
    }

    private RBTreeNode<T> search(RBTreeNode<T> x, T key) {
        // TODO Auto-generated method stub
        if (x == null) return x;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return search(x.leftchild, key);
        } else if (cmp > 0) {
            return search(x.rightchild, key);
        } else {
            return x;
        }
    }

    public void delete(T key) {
        RBTreeNode<T> node;
        if ((node = search(root, key)) != null) {
            delete(node);
        }
    }

    public void delete(RBTreeNode<T> node) {
        RBTreeNode<T> child, parent;
        boolean color;
        if ((node.leftchild != null) && (node.rightchild != null)) {
            RBTreeNode<T> replace = node;
            replace = replace.rightchild;
            while (replace.leftchild != null) {
                replace = replace.leftchild;
            }
            if (parentOf(node) != null) {
                if (parentOf(node).leftchild == node) {
                    parentOf(node).leftchild = replace;
                } else {
                    parentOf(node).rightchild = replace;
                }
            } else {
                this.root = replace;
            }
            child = replace.rightchild;
            parent = parentOf(replace);
            color = colorOf(replace);
            if (parent == node) {
                parent = replace;
            } else {
                if (child != null) {
                    setParent(child, parent);
                }
                parent.leftchild = child;
                replace.rightchild = node.rightchild;
                setParent(node.rightchild, replace);
            }
            replace.parent = node.parent;
            replace.color = node.color;
            replace.leftchild = node.leftchild;
            node.leftchild.parent = replace;

            if (color == BLACK) {
                deleteFixup(child, parent);
            }

            node = null;
            return;
        }
        if (node.leftchild != null) {
            child = node.leftchild;
        } else {
            child = node.rightchild;
        }
        parent = node.parent;
        color = node.color;
        if (child != null) {
            child.parent = parent;
        }
        if (parent != null) {
            if (parent.leftchild == node)
                parent.leftchild = child;
            else
                parent.rightchild = child;
        } else {
            this.root = child;
        }

        if (color == BLACK)
            deleteFixup(child, parent);
        node = null;
    }

    public void deleteFixup(RBTreeNode<T> node, RBTreeNode<T> parent) {
        RBTreeNode<T> other;
        while ((node == null || isBlack(node)) && (node != this.root)) {
            if (parent.leftchild == node) {
                other = parent.rightchild;
                if (isRed(other)) {
                    setBlack(other);
                    setRed(parent);
                    leftrotate(parent);
                    other = parent.rightchild;
                }

                if ((other.leftchild == null || isBlack(other.leftchild)) && (other.rightchild == null || isBlack(other.rightchild))) {
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {
                    if (other.rightchild == null || isBlack(other.rightchild)) {
                        setBlack(other.leftchild);
                        setRed(other);
                        rightrotate(other);
                        other = parent.rightchild;
                    }
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.rightchild);
                    leftrotate(parent);
                    node = this.root;
                    break;

                }
            } else {
                other = parent.leftchild;
                if (isRed(other)) {
                    setBlack(other);
                    setRed(parent);
                    rightrotate(parent);
                    other = parent.leftchild;
                }
                if ((other.leftchild == null || isBlack(other.leftchild)) && (other.rightchild == null || isBlack(other.rightchild))) {
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {
                    if (other.leftchild == null || isBlack(other.leftchild)) {
                        setBlack(other.rightchild);
                        setRed(other);
                        leftrotate(other);
                        other = parent.leftchild;
                    }

                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.leftchild);
                    rightrotate(parent);
                    node = this.root;
                    break;
                }
            }
        }
        if (node != null) {
            setBlack(node);
        }

    }

    private void print(RBTreeNode<T> tree, T key, int direction) {
        if (tree != null) {
            if (direction == 0)
                System.out.printf("%2d(B) 是根节点\n", tree.key);
            else
                System.out.printf("%2d(%s) 是 %2d 的   %6s 孩子\n", tree.key, isRed(tree) ? "R" : "B", key, direction == 1 ? "right" : "left");

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
        int a[] = new int[10];
        int i, ilen = a.length;
        RedBlackTreeMain<Integer> rbtree = new RedBlackTreeMain<Integer>();
        System.out.print("原始数据:");
        for (i = 0; i < ilen; i++) {
            a[i] = random.nextInt(100);
            System.out.printf("%d ", a[i]);
        }
        System.out.println();
        System.out.println("————————————————————————————");
        System.out.println("初始化中：");
        for (i = 0; i < ilen; i++) {
            rbtree.insert(a[i]);
            System.out.println("增加结点为：" + a[i]);
            System.out.println("详细信息如下：");
            rbtree.print();
            System.out.println();
        }
        System.out.println("————————————————————————————");
        System.out.println("删除中：");
        rbtree.delete(a[0]);
        System.out.println("删除节点：" + a[0]);
        System.out.println("删除后详细信息如下：");
        rbtree.print();
    }

}

