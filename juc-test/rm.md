**排序算法：**
   冒泡
   选择
   插入
   
   希尔 
   归并
   快速
   堆排序
   
   计数
   桶排序
   基数排序 

**查找算法：**
	在java中，我们常用的查找有四种:
   1) 顺序(线性)查找
   2) 二分查找/折半查找
   3) 插值查找
   4) 斐波那契查找

**二叉树遍历**
    前序遍历: 先输出父节点，再遍历左子树和右子树
    中序遍历: 先遍历左子树，再输出父节点，再遍历右子树
    后序遍历: 先遍历左子树，再遍历右子树，最后输出父节点
    小结: 看输出父节点的顺序，就确定是前序，中序还是后序

二叉树的概念
    1) 如果该二叉树的所有叶子节点都在最后一层，
    并且结点总数= 2^n -1 , n 为层数，则我们称为满二叉树。
    2) 如果该二叉树的所有叶子节点都在最后一层或者倒数第二层，
    而且最后一层的叶子节点在左边连续，倒数第二层的叶子节点在右边连续，我们称为完全二叉树。

顺序存储二叉树的特点:
    顺序二叉树通常只考虑完全二叉树
    第n个元素的左子节点为  2 * n + 1 
    第n个元素的右子节点为  2 * n + 2
    第n个元素的父节点为  (n-1) / 2
    
    n : 表示二叉树中的第几个元素(按0开始编号 如图所示)
