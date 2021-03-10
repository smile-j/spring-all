package com.dong.demo.datastructures.avl;

import lombok.Data;

public class AVLTreeDemo {

    public static void main(String[] args) {

//        int [] arr ={4,3,6,5,7,8};
//        int [] arr ={10,12,8,9,7,6};
        int [] arr ={10,11,7,6,8,9};
        AVLTree avlTree = new AVLTree();
        for (int i:arr){
            avlTree.add(new Node(i));
        }

        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println("在没有平衡处理前");
        System.out.println("树的高度:"+avlTree.getRoot().height());
        System.out.println("左子树的高度:"+avlTree.getRoot().getLeftHeight());
        System.out.println("右子树的高度："+avlTree.getRoot().getRightHeight());
        System.out.println("根节点："+avlTree.getRoot().getValue());

    }
}

@Data
class AVLTree{

    private Node root;
    public void add(Node node){
        if (root ==null){
            root = node;
        }else {
            root.add(node);
        }
    }

    public void infixOrder(){
        if (root!=null){
            root.infixOrder();
        }else {
            System.out.println("二叉排序树为空！");
        }
    }

    public Node search(int value){
        if(root==null){
            return null;
        }else {
            return root.search(value);
        }
    }
    public Node searchParent(int value){
        if(root==null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    /**
     * 返回以node为根节点的二叉树的最小节点的值
     * 删除node为根节点的二叉树的最新节点
     *
     * @param node 传入的节点（当做二叉树的根节点）
     * @return 返回以node 为根节点的二叉树的最小节点
     */
    public Node delRightTreeMin(Node node){
        Node target = node;
        while (target.left!=null){
            target = target.left;
        }
        delNode(target.value);
        return target;
    }

    /**
     * 删除的3种情况
     * 1)删除叶子节点
     * 2）删除只有一颗子树的节点
     * 3）删除有两颗子树的节点
     *
     *
     * @param value
     */
    public void delNode(int value){
        if(root==null){
            return;
        }else {
            Node targetNode = search(value);
            if (targetNode==null){
                return;
            }
            if (root.left==null&&root.right==null){
                root = null;
                return;
            }
            Node paraentNode = searchParent(value);
            //targetNode  叶子节点
            if (targetNode.left==null&&targetNode.right==null){
                if(paraentNode.left!=null&&paraentNode.left.value==value){
                    paraentNode.left = null;
                }else if (paraentNode.right!=null&&paraentNode.right.value==value){
                    paraentNode.right = null;
                }

            }else if (targetNode.left!=null&&targetNode.right !=null){  //删除有两颗子树的节点

                Node minNode = delRightTreeMin(targetNode.right);
                targetNode.value = minNode.value;

            }else {//删除只有一颗子树的节点
                if (targetNode.left!=null){//删除的节点有左节点
                    if(paraentNode!=null){
                        if (paraentNode.left.value ==value){
                            paraentNode.left = targetNode.left;
                        }else {
                            paraentNode.right = targetNode.left;
                        }
                    }else {
                        root = targetNode.left;
                    }
                }else {//删除的节点有右节点
                    if(paraentNode!=null){
                        if (paraentNode.left.value ==value){
                            paraentNode.left = targetNode.right;
                        }else {
                            paraentNode.right = targetNode.right;
                        }
                    }else {
                        root = targetNode.right;
                    }

                }
            }




        }
    }

}

@Data
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public int getLeftHeight(){
        if (this.left==null){
            return 0;
        }
        return this.left.height();
    }

    public int getRightHeight(){
        if (this.right==null){
            return 0;
        }
        return this.right.height();
    }

    public int height(){
        return Math.max(this.left==null?0:this.left.height(),this.right==null?0:this.right.height())+1;
    }

    //左旋
    private void leftRotate(){

        Node newNode = new Node(this.value);

        newNode.left = left;
        newNode.right = right.left;
        value = right.value;
        this.right = this.right.right;
        this.left = newNode;

    }

    //右旋
    private void rightRotate(){

        Node newNode = new Node(this.value);

        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        this.left = this.left.left;
        this.right = newNode;

    }

    //添加  满足二叉排序树
    public void add(Node node){
        if (node ==null){
            return;
        }
        if(node.value<this.value){
            if (this.left==null){
                this.left = node;
            }else {
                this.left.add(node);
            }
        }else {
            if (this.right==null){
                this.right = node;
            }else {
                this.right.add(node);
            }
        }

        if(this.getRightHeight()-this.getLeftHeight()>1){
            if (this.right!=null&&right.getRightHeight()<right.getLeftHeight()){
//                //先对右子树进行旋转
                this.right.rightRotate();
                leftRotate();

            }else {
                leftRotate();
            }
        }

        if(this.getLeftHeight()-this.getRightHeight()>1){
            if (this.left!=null&&left.getRightHeight()>left.getLeftHeight()){
//                //先当前节点的右子树进行旋转
                left.leftRotate();
                rightRotate();

            }else {
                rightRotate();
            }

        }
    }


    public Node search(int value){
        if(value==this.value){
            return this;
        }else if(value<this.value){
            if(this.left==null){
                return null;
            }
            return this.left.search(value);
        }else {
            if (this.right==null){
                return null;
            }
            return this.right.search(value);
        }

    }

    /**
     * 删除节点的父节点
     * @param value
     * @return
     */
    public Node searchParent(int value){
        if((this.left!=null&&this.left.value==value)||
                (this.right!=null&&this.right.value==value)){
            return this;
        }else {
            if (value<this.value&&this.left!=null){
                return this.left.searchParent(value);
            }else if(value>this.value&&this.right!=null){
                return this.right.searchParent(value);
            }else {
                return null;
            }
        }

    }

    //中序遍历
    public void infixOrder(){
        if (this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right!=null){
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node[" +
                "value=" + value +
                ']';
    }
}
