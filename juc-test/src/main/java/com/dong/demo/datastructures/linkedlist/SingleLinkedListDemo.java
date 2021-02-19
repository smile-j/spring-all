package com.dong.demo.datastructures.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args) {

//        HeroNode heroNode1 = new HeroNode(1,"宋江","及时雨");
//        HeroNode heroNode2 = new HeroNode(2,"卢俊义","玉麒麟");
//        HeroNode heroNode3 = new HeroNode(3,"吴用","智多星");
//        HeroNode heroNode4 = new HeroNode(4,"林冲","豹子头");

        SingleLinkedLikedList linkedLikedList = new SingleLinkedLikedList();
        SingleLinkedLikedList linkedLikedList2 = new SingleLinkedLikedList();
//        linkedLikedList.addNode(heroNode1);
//        linkedLikedList.addNode(heroNode2);
//        linkedLikedList.addNode(heroNode3);
//        linkedLikedList.addNode(heroNode4);



        HeroNode heroNode1 = new HeroNode(1,"宋江","及时雨");
        HeroNode heroNode2 = new HeroNode(9,"卢俊义","玉麒麟");
        HeroNode heroNode3 = new HeroNode(6,"吴用","智多星");
        HeroNode heroNode4 = new HeroNode(2,"林冲","豹子头");

        HeroNode heroNode5 = new HeroNode(3,"宋江","及时雨");
        HeroNode heroNode6 = new HeroNode(7,"卢俊义","玉麒麟");
        HeroNode heroNode7 = new HeroNode(5,"吴用","智多星");
        HeroNode heroNode8 = new HeroNode(4,"林冲","豹子头");

        linkedLikedList.addNodeOrder(heroNode1);
        linkedLikedList.addNodeOrder(heroNode4);
        linkedLikedList.addNodeOrder(heroNode2);
        linkedLikedList.addNodeOrder(heroNode3);

        linkedLikedList2.addNodeOrder(heroNode5);
        linkedLikedList2.addNodeOrder(heroNode6);
        linkedLikedList2.addNodeOrder(heroNode7);
        linkedLikedList2.addNodeOrder(heroNode8);

        linkedLikedList.list();
        System.out.println("----------------");
        linkedLikedList2.list();
        System.out.println("======================");
        sortLinked(linkedLikedList.getHead(),linkedLikedList2.getHead()).list();
//        reversList(linkedLikedList.getHead());
//        reversPrint(linkedLikedList.getHead());
//        linkedLikedList.list();


    }
    //链表反转
    public static void  reversList(HeroNode head){
        if(head.next==null||head.next.next==null){
            return;
        }
        HeroNode reversNode = new HeroNode(-1,"","");
        HeroNode cur = null;
        while (head.next!=null){
            cur = head.next;
            head.next = cur.next;
            cur.next = reversNode.next;
            reversNode.next = cur;
        }
        head.next = reversNode.next;
        System.out.println("---------reversList---------end-----");
    }
    //链表从后到前打印
    public static void  reversPrint(HeroNode head){

        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur!=null){
            stack.add(cur);
            cur = cur.next;
        }
        System.out.println("反好转打印-------");
        while (stack.size()>0){
            System.out.println(stack.pop());
        }

    }

    public static SingleLinkedLikedList sortLinked(HeroNode head1,HeroNode head2){
        SingleLinkedLikedList singleLinkedLikedList = new SingleLinkedLikedList();
        HeroNode cur1 = head1.next;
        HeroNode cur2 = head2.next;
        HeroNode min = null;
        while (cur1!=null||cur2!=null){
            if(cur1!=null&&cur2!=null){
                if(cur1.no>cur2.no){
                    min = cur2.next;
                    singleLinkedLikedList.addNode(min);
                }
            }
        }
        return singleLinkedLikedList;
    }


}

class SingleLinkedLikedList{

    //先初始化一个头结点，头结点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    //无序
    public void addNode(HeroNode node){
        HeroNode temp = head;
        while (true){
            if(temp.next==null){
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }

    public void addNodeOrder(HeroNode node){
        HeroNode temp = head;
        boolean flag = false;//标识添加的标号是否存在
        while (true){

            if (temp.next==null){
                break;
            }
            if (temp.next.no>node.no){
                break;
            }
            if (temp.next.no == node.no){
                flag = true;
                break;
            }
            temp = temp.next;

        }

        if(flag){
            System.out.println("准备插入的数据 已经存在。。。。");
            return;
        }
        node.next = temp.next;
        temp.next = node;
    }

    public void updateByNo(HeroNode node){
        if(head.next == null){
            System.out.println("链表为空....");
        }

        HeroNode temp = head.next;
        boolean flag = false;
        while (true){
            if (temp ==null){
                break;
            }
            if (temp.no==node.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp .name = node.name;
            temp.nickName = node.nickName;
        }else {
            System.out.println("没有找到修改的节点。。。"+node);
        }
    }

    public void delete(int no){
        if(head.next==null){
            System.out.println("空链表。。。。");
        }
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag){
            temp.next = temp.next.next;
        }else {
            System.out.println("不存在改节点。。。。"+no);
        }

    }

    public void list(){
        if(head.next==null){
            System.out.println("链表为空........");
            return;
        }
        HeroNode temp = head.next;
        while (true){
            if(temp==null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }

    }

    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }
}

class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName +
                '}';
    }
}
