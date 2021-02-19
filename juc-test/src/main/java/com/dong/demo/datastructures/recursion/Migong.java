package com.dong.demo.datastructures.recursion;

public class Migong {

    public static void main(String[] args) {

        int [] [] map = new int[8][7];
        //使用1 表示墙
        //上下全部为1
        for (int i=0;i<7;i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全为1
        for (int i=0;i<8;i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1]=1;
        map[3][2]=1;

//        map[1][2]=1;
//        map[2][2]=1;

//        输出
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.printf(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("开始走迷宫.......");
        setWay(map,1,1);
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.printf(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯给小球找路
     * 从map[1][1]如果小球能到map[6][5]位置，则说明通路找到
     * 约定[1][1]=0表示该点没有走过 1表示是墙；2表示通路可以走；3表示已经走过 但是走不通
     * 走迷宫的策略：下-》右-》上-》左
     *
     * @param map
     * @param i
     * @param j
     * @return
     */
    public static boolean setWay(int map[][],int i,int j){
        if(map[6][5]==2){
            return true;
        }else {
            if(map[i][j]==0){
                map[i][j]=2;
                if(setWay(map,i+1,j)){//向下走
                    return true;
                }else  if(setWay(map,i,j+1)){//向右走
                    return true;
                }else  if(setWay(map,i-1,j)){//向上
                    return true;
                }else  if(setWay(map,i,j-1)){//向左
                    return true;
                }else {
                    map[i][j]=3;
                    return false;
                }
            }else {//可能是 1,2,3
                return false;
            }
        }
    }
}
