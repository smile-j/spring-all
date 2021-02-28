package com.dong.demo.datastructures.binarysorttree;

import lombok.Data;


/**
 * 二叉排序树
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {

        int [] arr ={7,3,10,12,5,1,9};
        BinarySortTree tree = new BinarySortTree();
        for (int i:arr){
            tree.add(new Node(i));
        }
        System.out.println("中序遍历");//1，3，5，6，9，10，12
        tree.infixOrder();

    }

}

class BinarySortTree{

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

}

@Data
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
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
    public void del(int value){

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
