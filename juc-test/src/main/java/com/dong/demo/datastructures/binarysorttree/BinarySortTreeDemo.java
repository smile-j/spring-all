package com.dong.demo.datastructures.binarysorttree;

import lombok.Data;


/**
 * 二叉排序树
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {

//        int [] arr ={7,3,10,12,5,1,9};
        int [] arr ={7,3,10,12,5,1,9,2};
        BinarySortTree tree = new BinarySortTree();
        for (int i:arr){
            tree.add(new Node(i));
        }
        System.out.println("中序遍历");//1，3，5，6，9，10，12
        tree.infixOrder();

        tree.delNode(7);
        System.out.println("删除后 遍历");//1，3，5，6，9，10，12
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
