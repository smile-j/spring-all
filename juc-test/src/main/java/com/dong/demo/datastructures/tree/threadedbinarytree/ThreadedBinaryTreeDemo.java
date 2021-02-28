package com.dong.demo.datastructures.tree.threadedbinarytree;

import lombok.Data;

public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {

        HeroNode root = new HeroNode(1,"tom");
        HeroNode node2 = new HeroNode(3,"tom3");
        HeroNode node3 = new HeroNode(6,"tom6");
        HeroNode node4 = new HeroNode(8,"tom8");
        HeroNode node5 = new HeroNode(10,"tom10");
        HeroNode node6 = new HeroNode(14,"tom14");

        //后续递归创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试线索化
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(root);

        binaryTree.threadNodes();

        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号节点的前驱节点是："+leftNode);
        System.out.println("10号节点的后继节点是："+rightNode);

        System.out.println("使用线索化的方法遍历 线索化二叉树");
        binaryTree.threadedList();//8,3,10,1,14,
    }

}

@Data
class BinaryTree{

    private HeroNode root;

    //为了实现线索化，需要创建要给指向当前节点的前驱节点的指针
    private HeroNode pre;

    public void threadNodes(){
        this.threadNodes(root);
    }

    //遍历线索化二叉树的方法
    public void threadedList(){

        HeroNode node = root;
        while (node!=null){
            while (node.getLeftType()==0){
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType()==1){
                node = node.getRight();
                System.out.println(node);
            }
            //
            node = node.getRight();
        }

    }
    //编写对二叉树进行中序线索化的方法
    /**
     *
     * @param node  需要线索化的节点
     */
    public void threadNodes(HeroNode node){

        if(node == null){
            return;
        }
        //1.先线索化左子树
        threadNodes(node.getLeft());
        //2.线索化当前节点

        //处理前驱节点
        if (node.getLeft()==null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //处理后继节点
        if(pre!=null && pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        //3.线索化右子树
        threadNodes(node.getRight());

    }

    public void delNode(int no){
        if (root!=null){
            if (this.root.getNo()==no){
                root = null;
            }else {
                this.root.delNode(no);
            }
        }else {
            System.out.println("空树，不能删除");
        }
    }

    //前序遍历
    public void preOrder(){

        if (this.root!=null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }

    }
    //中序遍历
    public void infixOrder(){
        if (this.root!=null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //后序遍历
    public void postOrder(){
        if (this.root!=null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no){
        if (root!=null){
            return root.preOrderSearch(no);
        }else {
            return null;
        }
    }
    //中序查找
    public HeroNode infixOrderSearch(int no){
        if(root!=null){
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //后序查找
    public HeroNode postOrderSearch(int no){
        if(root!=null){
            return root.postOrderSearch(no);
        }else {
            return null;
        }
    }

}

@Data
class HeroNode{

    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //0 表示是左右子树 1表示是前驱节点、后继节点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no + ", name='" + name + "'}";
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        //递归左子树
        if (this.left!=null){
            this.left.preOrder();
        }
        //递归右子树
        if(this.right!=null){
            this.right.preOrder();
        }
    }
    //中序遍历
    public void infixOrder(){

        if (this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        //递归右子树
        if(this.right!=null){
            this.right.infixOrder();
        }

    }
    //后序遍历
    public void postOrder(){
        if (this.left!=null){
            this.left.postOrder();
        }

        //递归右子树
        if(this.right!=null){
            this.right.postOrder();
        }
        System.out.println(this);
    }
    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        System.out.println("进入前序遍历！");
        if(this.no==no){
            return this;
        }
        HeroNode node = null;
        if(this.left!=null){
            node = this.left.preOrderSearch(no);
        }
        if (node != null){
            return node;
        }
        if(this.right!=null){
            node = this.right.preOrderSearch(no);
        }
        return node;

    }
    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        HeroNode node = null;
        if(this.left!=null){
            node = this.left.infixOrderSearch(no);
        }
        if (node!=null){
            return node;
        }
        if (this.no==no){
            return this;
        }
        if(this.right!=null){
            node = this.right.infixOrderSearch(no);
        }
        return node;

    }
    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        HeroNode node = null;
        if (this.left!=null){
            node = this.left.postOrderSearch(no);
        }
        if (node!=null){
            return node;
        }
        if (this.right!=null){
            node = this.right.postOrderSearch(no);
        }
        if (node!=null){
            return node;
        }
        if (this.no==no){
            return this;
        }
        return node;
    }

    /**
     *  删除的规定：
     *
     * 如果删除的节点是叶子节点，则删除该节点
     * 如果删除的节点是非叶子节点，则删除该子树.
     * 测试，删除掉 5号叶子节点 和 3号子树.
     */

    public void delNode(int no){
        if(this.left!=null && this.left.no==no){
            this.left = null;
            return;
        }
        if(this.right!=null&&this.right.no==no){
            this.right = null;
            return;
        }
        //向左子树递归
        if (this.left!=null){
            this.left.delNode(no);
        }
        //向右子树递归
        if (this.right!=null){
            this.right.delNode(no);
        }
    }
}
