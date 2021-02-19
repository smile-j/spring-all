package com.dong.demo.datastructures.stack;

public class Calculator {

    public static void main(String[] args) {

        String ex="300+20*6-2";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int index =0;
        int num1=0;
        int num2=0;
        int oper =0;
        int res =0;
        String keepNum ="";
        char ch = ' ';//将每次扫描得到char保存在ch
        while (true){
            ch = ex.substring(index,index+1).charAt(0);
            if (operStack.isOper(ch)){
                if(!operStack.isEmpty()){
                    if(operStack.priority(ch)<=operStack.priority(operStack.getTop())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        res = numStack.cal(num1,num2,operStack.pop());
                        numStack.push(res);
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }else {
                    operStack.push(ch);
                }
            }else {
                //
                //numStack.push(ch - 48 );
                keepNum += ch;
                if(index==ex.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    if (operStack.isOper(ex.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index = index+1;
            if(index>=ex.length()){
                break;
            }
        }
        while (!operStack.isEmpty()){
            oper = operStack.pop();
            num1 = numStack.pop();
            num2 = numStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        System.out.println("最终的结果："+numStack.pop());

    }
}

class ArrayStack2{

    private int maxSize ;
    private int[] stack;
    private int top=-1;//表示栈顶

    public ArrayStack2(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull(){
        return top==maxSize-1;
    }
    public boolean isEmpty(){
        return top ==-1;
    }

    public void push(int value){

        if(isFull()){
            System.out.println("栈满！！！");
            return;
        }
        top++;
        stack[top]=value;
    }

    public int pop(){
        if(isEmpty()){
            new RuntimeException("栈空了！！！");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //从栈顶开始
    public void list(){

        if(isEmpty()){
            System.out.println("栈空了！！！");
        }
        int count = top;
        while (count!=-1){
            System.out.printf("stack[%d]=%d \n",count,stack[count]);
            count--;
        }
    }
    //返回符合的优先级  数字越大 优先级越大
    public int priority(int oper){
        if(oper=='*'||oper=='/'){
            return 1;
        }else if(oper=='+'||oper=='-'){
            return 0;
        }else {
            return -1;
        }
    }

    public boolean isOper(char val){
        return val == '*'||val == '/'||val == '+'||val == '-';
    }

    public int cal(int num1,int num2,int oper){
        int result =0;
        switch (oper){
            case '+':
                result = num1+num2;
                break;
            case '-':
                result = num2-num1;
                break;
            case '*':
                result = num1*num2;
                break;
            case '/':
                result = num2/num1;
                break;
            default:{
                break;
            }
        }
        return result;
    }

    public int getTop(){
        return stack[top];
    }
}
