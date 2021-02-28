package com.dong.demo.datastructures.huffmantree;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树
 *
 */
public class HuffmanTree {

    public static void main(String[] args) {

        int arr [] ={13,7,8,3,29,6,1};
        Node root= createHuffmanTree(arr);
        root.preOrder();

    }

    public static Node createHuffmanTree(int [] arr){
        List<Node> nodes = new ArrayList<>();
        for (int value:arr){
            nodes.add(new Node(value));
        }
        Collections.sort(nodes);
        Node parent = null;
        while (nodes.size()>1){
            Node  left = nodes.get(0);
            Node  right = nodes.get(1);
            parent = new Node(left.getValue()+right.getValue());

            parent.setLeft(left);
            parent.setRight(right);
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
            Collections.sort(nodes);
            System.out.println(nodes);
        }

        return parent;
    }
}

@Data

class Node implements Comparable<Node>{
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    public void preOrder(){
        System.out.println(this);
        if (this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }


    @Override
    public int compareTo(Node o) {
        //从小到大
        return this.value-o.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}