package com.dong.demo.datastructures;

/**
 * 稀疏数组
 *
 * 二维数组转稀疏数组的思路
 * 1.遍历原始的二维数组，得到有效数据的个数sum
 * 2.根据sum就可以创建稀疏数组sparseArr int[sum+ 1][3]
 * 3.将二维数组的有效数据数据存入到稀疏数组
 * 稀疏数组转原始的二维数组的思路
 * 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的 chessArr2 =int [11][11]
 *
 * 2.在读取稀疏数组后几行的数据，并赋给原始的二维数组即可.
 */
public class SparseArray {


    public static void main(String[] args) {

        //创建一个原始的二维数组 11*11
        //0：表示没有棋子，1 表示黑子  2 表示蓝子
        int chessArr1 [] [] = new int [11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        System.out.println("原始二维数组");
        for (int [] row : chessArr1){
            for (int data: row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //1. 得到非0数据的个数
        int sum = 0;
        for(int i=0 ; i<11;i++){
            for (int j=0;j<11;j++){
                if(chessArr1[i][j]!=0){
                    sum++;
                }
            }

        }
        System.out.println("sum:"+sum);
        //2. 创建稀疏数组
        int spaseArr [] []  = new int[sum+1][3];
        //给稀疏数组赋值
        spaseArr[0][0] = 11;
        spaseArr[0][1] = 11;
        spaseArr[0][2] = sum;

        //遍历二维数组 赋值
        int count=0;//用于记录是第几个非0数组
        for(int i=0 ; i<11;i++){
            for (int j=0;j<11;j++){
                if(chessArr1[i][j]!=0){
                    count++;
                    spaseArr[count][0] = i;
                    spaseArr[count][1] = j;
                    spaseArr[count][2] = chessArr1[i][j];
                }
            }

        }
        // 输出稀疏数组
        System.out.println();
        System.out.println("得到稀疏数组为~~~~~");
        for(int i=0 ; i<spaseArr.length;i++){
            System.out.printf("%d\t%d\t%d\t",spaseArr[i][0],spaseArr[i][1],spaseArr[i][2]);
            System.out.println();
        }
        //3. 将稀疏数组恢复二维数组
        int arr2 [][] = new int[spaseArr[0][0]][spaseArr[0][1]];
        for(int i=1 ; i<spaseArr.length;i++){
            arr2[spaseArr[i][0]][spaseArr[i][1]] = spaseArr[i][2];
        }
        //输出恢复后的二维数组
        System.out.println("恢复后的二维数组--------");
        for (int [] row : arr2){
            for (int data: row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }


}
