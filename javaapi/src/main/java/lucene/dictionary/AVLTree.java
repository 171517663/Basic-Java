package lucene.dictionary;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AVLTree<E extends Comparable<E>> extends AbstractSet<E> implements Serializable {
    private static final long serialVersionUID = 3161750908987118873L;
    private AVLTree.Entry<E> root;
    private int nodeSize;

    public AVLTree() {
    }

    public boolean add(E elem) {
        if (this.root == null) {
            this.root = new Entry(elem, (Entry)null);
            ++this.nodeSize;
            return true;
        } else {
            Entry current = this.root;

            while(true) {
                int comp = elem.compareTo((E) current.elem);
                if (comp == 0) {
                    return false;
                }

                if (comp < 0) {
                    if (current.left == null) {
                        current.left = new Entry(elem, current);
                        ++this.nodeSize;
                        break;
                    }

                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new Entry(elem, current);
                        ++this.nodeSize;
                        break;
                    }

                    current = current.right;
                }
            }

            while(current != null) {
                current.bf = (byte)(h(current.left) - h(current.right));
                if (current.bf == -2) {
                    this.lBalance(current);
                } else if (current.bf == 2) {
                    this.rBalance(current);
                }

                if (current.bf == 0) {
                    break;
                }

                current = current.parent;
            }

            return true;
        }
    }

    public boolean remove(E elem) {
        Entry<E> removeNode = this.getEntry(elem);
        if (removeNode == null) {
            return false;
        } else {
            for(Entry current = this.removeEntry(removeNode); current != null; current = current.parent) {
                current.bf = (byte)(h(current.left) - h(current.right));
                if (current.bf == -2) {
                    this.lBalance(current);
                } else if (current.bf == 2) {
                    this.rBalance(current);
                }

                if (current.bf != 0) {
                    break;
                }
            }

            return true;
        }
    }

    public Entry<E> getEntry(E e) {
        Entry tmp = this.root;

        while(tmp != null) {
            int c = e.compareTo((E) tmp.elem);
            if (c == 0) {
                return tmp;
            }

            if (c < 0) {
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }
        }

        return null;
    }

    public E getNodeByKey(int key) {
        Entry tmp = this.root;

        while(tmp != null) {
            if (key == ((Comparable)tmp.elem).hashCode()) {
                return (E) tmp.elem;
            }

            if (key < ((Comparable)tmp.elem).hashCode()) {
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }
        }

        return null;
    }

    public int height() {
        return h(this.root);
    }

    public boolean isAVL() {
        return checkAVL(this.root);
    }

    public String traverseByLevel() {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("[");
        if (this.root != null) {
            sb.append(this.root.elem);
        }

        sb.append("]");
        List<Entry<E>> levelEntries = new ArrayList();
        ((List)levelEntries).add(this.root);
        int h = this.height();

        for(int i = 1; i < h; ++i) {
            int num = ((List)levelEntries).size();

            int k;
            Entry now;
            for(k = 0; k < num; ++k) {
                now = (Entry)((List)levelEntries).get(k);
                if (now != null) {
                    ((List)levelEntries).add(now.left);
                    ((List)levelEntries).add(now.right);
                } else {
                    ((List)levelEntries).add((Object)null);
                    ((List)levelEntries).add((Object)null);
                }
            }

            levelEntries = ((List)levelEntries).subList(num, ((List)levelEntries).size());

            for(k = 0; k < ((List)levelEntries).size(); ++k) {
                now = (Entry)((List)levelEntries).get(k);
                sb.append("[");
                if (now != null) {
                    sb.append(now.elem);
                }

                sb.append("]");
            }
        }

        sb.append("}");
        return sb.toString();
    }

    public Iterator<E> iterator() {
        return new TreeIterator();
    }

    public int size() {
        return this.nodeSize;
    }

    public static <E extends Comparable<E>> boolean checkAVL(Entry<E> p) {
        if (p == null) {
            return true;
        } else {
            return Math.abs(h(p.left) - h(p.right)) <= 1 && checkAVL(p.left) && checkAVL(p.right);
        }
    }

    private void lBalance(Entry<E> p) {
        if (p.right.bf == 1) {
            this.rRotate(p.right);
        }

        if (this.root == p) {
            this.root = p.right;
        }

        this.lRotate(p);
    }

    private void rBalance(Entry<E> p) {
        if (p.left.bf == -1) {
            this.lRotate(p.left);
        }

        if (this.root == p) {
            this.root = p.left;
        }

        this.rRotate(p);
    }

    private void lRotate(Entry<E> p) {
        Entry<E> t = p.right;
        t.parent = p.parent;
        p.parent = t;
        p.right = t.left;
        if (t.left != null) {
            t.left.parent = p;
        }

        t.left = p;
        p.bf -= t.bf < 0 ? t.bf : 0;
        ++p.bf;
        t.bf += p.bf > 0 ? p.bf : 0;
        ++t.bf;
        if (t.parent != null) {
            if (((Comparable)t.elem).compareTo((Comparable)t.parent.elem) < 0) {
                t.parent.left = t;
            } else {
                t.parent.right = t;
            }
        }

    }

    private void rRotate(Entry<E> p) {
        Entry<E> t = p.left;
        t.parent = p.parent;
        p.parent = t;
        p.left = t.right;
        if (t.right != null) {
            t.right.parent = p;
        }

        t.right = p;
        p.bf -= t.bf > 0 ? t.bf : 0;
        --p.bf;
        t.bf += p.bf < 0 ? p.bf : 0;
        --t.bf;
        if (t.parent != null) {
            if (((Comparable)t.elem).compareTo((Comparable)t.parent.elem) < 0) {
                t.parent.left = t;
            } else {
                t.parent.right = t;
            }
        }

    }

    public Entry<E> successor(Entry<E> e) {
        if (e == null) {
            return null;
        } else {
            Entry p;
            if (e.right != null) {
                for(p = e.right; p.left != null; p = p.left) {
                }

                return p;
            } else {
                p = e.parent;

                for(Entry c = e; p != null && c == p.right; p = p.parent) {
                    c = p;
                }

                return p;
            }
        }
    }

    private Entry<E> removeEntry(Entry<E> p) {
        Entry rep;
        if (p.left != null && p.right != null) {
            rep = this.successor(p);
            if (rep == null) {
                return null;
            }

            p.elem = (E) rep.elem;
            p = rep;
        }

        if (p.left == null && p.right == null) {
            if (p.parent == null) {
                this.root = null;
            } else if (p == p.parent.left) {
                p.parent.left = null;
            } else {
                p.parent.right = null;
            }
        } else {
            if (p.left != null) {
                rep = p.left;
            } else {
                rep = p.right;
            }

            rep.parent = p.parent;
            if (p.parent == null) {
                this.root = rep;
            } else if (p == p.parent.left) {
                p.parent.left = rep;
            } else {
                p.parent.right = rep;
            }
        }

        rep = p.parent;
        p.parent = null;
        p.left = null;
        p.right = null;
        --this.nodeSize;
        return rep;
    }

    protected static <E extends Comparable<E>> int h(Entry<E> p) {
        return p == null ? 0 : 1 + Math.max(h(p.left), h(p.right));
    }

    private static class Entry<T> implements Serializable {
        private static final long serialVersionUID = -3211318115011007475L;
        T elem;
        Entry<T> parent;
        Entry<T> left;
        Entry<T> right;
        byte bf = 0;

        public Entry(T elem, Entry<T> parent) {
            this.elem = elem;
            this.parent = parent;
        }
    }

    private class TreeIterator implements Iterator<E> {
        private Entry<E> lastReturned = null;
        private Entry<E> nextAVLNode;

        TreeIterator() {
            this.nextAVLNode = AVLTree.this.root;
            if (this.nextAVLNode != null) {
                while(this.nextAVLNode.left != null) {
                    this.nextAVLNode = this.nextAVLNode.left;
                }
            }

        }

        public boolean hasNext() {
            return this.nextAVLNode != null;
        }

        public E next() {
            this.lastReturned = this.nextAVLNode;
            this.nextAVLNode = AVLTree.this.successor(this.nextAVLNode);
            return this.lastReturned.elem;
        }

        public void remove() {
            if (this.lastReturned != null && this.lastReturned.left != null) {
                AVLTree.this.removeEntry(this.lastReturned);
                this.lastReturned = null;
            }

        }
    }
}

