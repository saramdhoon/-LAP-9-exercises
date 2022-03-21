public class BinaryTree <E>{
    private   Node<E> root=null;
    private int size=0;
    protected Node<E> createNode(E e, Node<E> p, Node<E>L, Node<E>R){
        return new Node<E>(e,p,L,R);

    }
    protected Node<E> validateNode(position<E>p){
        if (!(p instanceof Node))
            throw new IllegalArgumentException("P is not a valid position");
        Node<E> n= (Node<E>) p;
        if (n.getParent()==n)
            throw new IllegalArgumentException("p is not exisit anymore");
        return n;
    }

    public position<E>root (){
        return root;
    }
    public position<E> parent(position<E>p){
        Node<E> n = validateNode(p);
        return n.getParent();
    }

    public position<E> left(position<E>p){
        Node<E> n = validateNode(p);
        return n.getLeftChild();
    }
    public position<E> right (position<E>p){
        Node<E> n = validateNode(p);
        return n.getRightChild();
    }

    public int numOfChildren (position<E>p){
        int count=0;
        if (left(p)!=null)
            count++;
        if (right(p)!=null)
            count++;
        return count;
    }
    //------------------------------------------------------------------------
    public position<E> addRoot(E e){
        if (!(isEmpty()))
            throw new IllegalArgumentException("There is already root ");
        //ننشا نود
         root = createNode(e,null, null,null);
         size=1;
         //نعيد فيمةالpos الخاص بالنود الت تممت انشاءهاا
         return root ;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public position<E> addLeft(position<E>p,E e){
        //التحقق من pos هل هو valid
        Node<E> parent =validateNode(p);
        // نتاكد هل يوجد leftchild
        if(parent.getLeftChild()!=null)
            throw new IllegalArgumentException("there is a left child");
        // يتم انشاء نود من ال element االمرسل مع ال parent
        Node<E> child= createNode(e,parent,null,null);
        // يتم اعلام ال parent  ب ال new child
        parent.setLeftChild(child);
        size++;
        //نرجع قيمة النود الجديدة الي هى child
        return child;
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public position<E> addRight(position<E>p,E e){
        //التحقق من pos هل هو valid
        Node<E> parent =validateNode(p);
        // نتاكد هل يوجد leftchild
        if(parent.getRightChild()!=null)
            throw new IllegalArgumentException("there is a Right child");
        // يتم انشاء نود من ال element االمرسل مع ال parent
        Node<E> child= createNode(e,parent,null,null);
        // يتم اعلام ال parent  ب ال new child
        parent.setRightChild(child);
        size++;
        //نرجع قيمة النود الجديدة الي هى child
        return child;
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //لتغيير القيمة الموحودة في النود
    public E set(position<E>p,E e ) {

        Node<E> n = validateNode(p);
        E old = n.getElement();
        n.setElement(e);
        return old;
    }
     //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
public E remove (position<E>p){
        Node<E>n = validateNode(p);
        if(numOfChildren(p)==2)
            throw new IllegalArgumentException("p has tow child");
        Node<E>child = (n.getLeftChild()!=null?n.getLeftChild():n.getRightChild());
        if(child!=null)
            child.setParent(n.getParent());
        if (n==root)
            root=child;
        else {
            Node<E>parent =n.getParent();
            if (parent.getLeftChild()==n)
                parent.setLeftChild(child);
            else
                parent.setRightChild(child);
        }
        size--;
        n.setParent(n);
        E del=n.getElement();
        n.setElement(null);
        n.setLeftChild(null);
        n.setRightChild(null);
        return del;
}



    //-----------------------------------------------------------------------------------
    private static class Node<E> implements position <E>{


        E element ;
        Node<E> parent ;
        Node<E>LeftChild;
        Node<E>RightChild;

        public Node(E element, Node<E> parent, Node<E> leftChild, Node<E> rightChild) {
            this.element = element;
            this.parent = parent;
            LeftChild = leftChild;
            RightChild = rightChild;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public Node<E> getLeftChild() {
            return LeftChild;
        }

        public void setLeftChild(Node<E> leftChild) {
            LeftChild = leftChild;
        }

        public Node<E> getRightChild() {
            return RightChild;
        }
        public void setRightChild(Node<E> rightChild) {
            RightChild = rightChild;
        }
        @Override
        public E getElement() {
            return element;
        }
    }
}
