package com.dong.demo.datastructures.linkedlist;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {

        HeroNode2 heroNode1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 heroNode4 = new HeroNode2(4,"林冲","豹子头");

        DoubleLinkedList linkedList = new DoubleLinkedList();
        linkedList.addNodeOrder(heroNode1);
        linkedList.addNodeOrder(heroNode4);
        linkedList.addNodeOrder(heroNode3);
        linkedList.addNodeOrder(heroNode2);
        linkedList.list();
//        linkedList.updateByNo(new HeroNode2(4,"公孙胜","入云龙"));
//        System.out.println("修改后的--------------");
//        linkedList.list();
//        linkedList.delete(3);
//        System.out.println("delete后的--------------");
//        linkedList.list();


    }

}

class DoubleLinkedList{

    private HeroNode2 head = new HeroNode2(0,"","");
    public HeroNode2 getHead() {
        return head;
    }

    public void list(){
        if(head.next==null){
            System.out.println("链表为空........");
            return;
        }
        HeroNode2 temp = head.next;
        while (true){
            if(temp==null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }

    }

    //无序
    public void addNode(HeroNode2 node){
        HeroNode2 temp = head;
        while (true){
            if(temp.next==null){
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }
    public void addNodeOrder(HeroNode2 node){

        HeroNode2 temp = head;
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
        node.pre = temp;
        temp.next = node;
        temp.next.pre = node;

    }
    public void updateByNo(HeroNode2 node){
        if(head.next == null){
            System.out.println("链表为空....");
        }

        HeroNode2 temp = head.next;
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
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true){
            if(temp == null){
                break;
            }
            if(temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag){
            temp.pre.next = temp.next;
            if(temp.next!=null){
                temp.next.pre= temp.pre;
            }
        }else {
            System.out.println("不存在改节点。。。。"+no);
        }

    }
}

class HeroNode2{
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickName) {
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