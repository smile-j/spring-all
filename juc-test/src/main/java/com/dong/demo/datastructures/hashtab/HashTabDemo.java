package com.dong.demo.datastructures.hashtab;

import lombok.Data;

import java.util.Scanner;

public class HashTabDemo {

    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        String key ="";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add:添加雇员");
            System.out.println("list:遍历雇员");
            System.out.println("find:查找雇员");
            System.out.println("exit:退处系统");

            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入name");
                    String name = scanner.next();
                    Emp emp = new Emp(id,name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入id");
                    id = scanner.nextInt();
                    hashTab.findEmpByid(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:{
                    break;
                }
            }
        }

    }

}

//创建hashTab
class HashTab{
   private EmpLinkedList [] empLinkedListArray;
   private int size;
   public HashTab(int size){
       empLinkedListArray = new EmpLinkedList[size];
       for (int i=0;i<size;i++){
           empLinkedListArray[i] = new EmpLinkedList();
       }
       this.size = size;

   }

   public void add(Emp emp){

       int empLinkedListNo = getHash(emp.id);
       empLinkedListArray[empLinkedListNo].add(emp);

   }

   public void list(){
       for (int i=0;i<size;i++){
           empLinkedListArray[i].list(i);
       }
   }

   public void findEmpByid(int id){
       int empLinkedListNo = getHash(id);
       Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
       if (emp!=null){
           System.out.printf("在第%d条链表中找到 雇员id = %d\n",empLinkedListNo+1,id);
       }else {
           System.out.println("没有找到！！！");
       }
   }

   public int getHash(int id){
       return id % size;
   }
}

@Data
class Emp{

    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList{

    private Emp head;//默认null

    //添加 id自增加，因此直接加入到本链表最后即可
    public void add(Emp emp){
        if(head==null){
            head =emp;
            return;
        }
        Emp curEmp = head;
        while (true){
            if (curEmp.next==null){
                break;
            }
            curEmp = curEmp.next;
        }

        curEmp.next = emp;

    }

    public void list(int no){
        if (head==null){
            System.out.println("第"+(no+1)+"链表为空！！！");
            return;
        }
        System.out.println("第"+(no+1)+"链表的信息为：");
        Emp curEmp = head;
        while (true){
            System.out.printf("=> id=%d name=%s\t",curEmp.id,curEmp.name);
            if (curEmp.next==null){
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    public Emp findEmpById(int id){

        if (head == null){
            System.out.println("链表为空！！");
            return null;
        }

        Emp curEmp = head;
        while (true){
            if (curEmp.id == id){
                break;
            }
            if (curEmp.next == null){
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;

        }
        return curEmp;
    }

}
