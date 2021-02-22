package com.dong.demo.datastructures.tree;

import lombok.Data;

/**
 * 二叉树遍历
 *
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {

        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");
        HeroNode node5 = new HeroNode(5,"关胜");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        binaryTree.setRoot(root);

//        System.out.println("前序遍历");//1,2,3,5,4
//        binaryTree.preOrder();
//        System.out.println("中序遍历");//2,1,5,3,4
//        binaryTree.infixOrder();
//        System.out.println("后序遍历");//2,5,4,3,1
//        binaryTree.postOrder();

//        System.out.println("前序遍历方式~~~");
//        HeroNode resNode = binaryTree.preOrderSearch(5);
//        if (resNode!=null){
//            System.out.printf("找到了，信息为 no=%d name=%s ",resNode.getNo(),resNode.getName());
//        }else {
//            System.out.printf("没有找到 no=%d 的英雄",5);
//        }


        System.out.println("前序遍历");//1,2,3,5,4
        binaryTree.preOrder();
        binaryTree.delNode(5);
        System.out.println("前序遍历");//1,2,3,4
        binaryTree.preOrder();
    }

}

@Data
class BinaryTree{

    private HeroNode root;

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
