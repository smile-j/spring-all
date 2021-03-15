package com.dong.demo.datastructures.grap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 图
 */
public class Graph {

    private ArrayList<String> vertexList;//存储顶点
    private int [][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//边的个数

    //记录某个节点是否被访问
    private boolean [] isVisited;

    public static void main(String[] args) {
        int n =5;
        String [] vertexs = {"A","B","C","D","E"};

        Graph graph = new Graph(n);
        for (String vertex:vertexs){
            graph.insertVertex(vertex);
        }

        //A-B A-C ;B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        graph.showGrap();
//        System.out.println("深度遍历：");
//        graph.dfs();
        System.out.println("广度优先遍历");
        graph.bfs();
    }

    public Graph(int n){
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    public void bfs(){
        for (int i=0;i<getNumOfVertex();i++){
            for (int j=i+1;j<getNumOfVertex();j++){
                if (!isVisited[j]){
                    bfs(isVisited,i);
                }
            }
        }
    }



    //广度优先遍历
    private void bfs(boolean[] isVisited,int i){
        int u;//表示队列的头节点对应的下标
        int w;//邻接点
        //队列，记录节点访问的顺序
        LinkedList queue = new LinkedList();
        //
        System.out.print(getValueByIndex(i)+"=>");

        isVisited[i]=true;
        queue.addLast(i);
        while (!queue.isEmpty()){
            u = (int) queue.removeFirst();
            w = getFisrtNeighthbor(u);
            while (w!=-1){
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w)+"=>");
                    isVisited[w] = true;
                    queue.addLast(w);
                }else {
                    w = getNextNeighbor(u,w);

                }
            }
        }
    }

    //深度优先遍历算法
    public void dfs(boolean[] isVisited,int i){
        //
        System.out.printf(getValueByIndex(i)+"->");
        isVisited[i]=true;
        int w = getFisrtNeighthbor(i);
        while (w!=-1){
            if (!isVisited[w]){
                dfs(isVisited,w);
            }
            w = getNextNeighbor(i,w);
        }
    }

    //深度优先遍历算法
    public void dfs(){
        for (int i=0;i<getNumOfVertex();i++){
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    //得到第一个邻接点的下标w
    public int getFisrtNeighthbor(int index){
        for (int j=0;j<vertexList.size();j++){
            if(edges[index][j]>0){
                return j;
            }
        }
        return -1;
    }
    //根据前一个邻接节点的下标获取下一个邻接节点
    public int getNextNeighbor(int v1,int v2){
        for (int j=v2+1;j<vertexList.size();j++){
            if(edges[v1][j]>0){
                return j;
            }
        }
        return -1;
    }


    //返回节点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }

    public int getNumOfEdge(){
        return numOfEdges;
    }
    //返回下标所对应的值
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    //返回v1 v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGrap(){
        for (int [] link :edges){
            System.err.println(Arrays.toString(link));
        }
    }

    //插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }
    //添加边

    /**
     *
     * @param v1 表示点的下标即使第几个顶点 a-b
     * @param v2 第二个顶点对应的下标
     * @param weight
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;

    }
}
