# Lab Bonus

## Bouns B

每次手动快排

如果等于K 直接返回

如果不等于 判断是在左边还是在右边 然后在对应区间递归

 

三个关键

1 随机数的使用

2 in-sute的解法

3 分治法 二分



## Bonus C

逆序对 -> 至少需要交换多少次

利用归并的思想

本质上是模拟冒泡排序 但是冒泡有n2会直接TLE

 

为什么用逆序对？ 

在模拟过程中，我们发现每次都是找到一个最小的然后移到最前面，但是除了这个最小的，其他数的相对次序并没有改变，所以我们可以将原始做法换一种表述方式：

找到最小的，统计它前面有多少个数比它小，然后加入结果，将这个最小的删去。

这时我们就发现，其实原题就是求数列的逆序对的个数！

 

来自 <<https://blog.csdn.net/no1_terminator/article/details/53024957>> 

 

 

怎么用归并求逆序对？

在合并的过程中（设l<=i<=mid，mid+1<=j<=h），当a[i]<=a[j]时，并不产生逆序数；当a[i]>a[j]时，在

前半部分中比a[i]大的数都比a[j]大，将a[j]放在a[i]前面的话，逆序数要加上mid+1-i。因此，可以在归并

排 序中的合并过程 中计 算逆序数：

 

来自 <<http://www.voidcn.com/article/p-oujususd-bqg.html>> 

 

 

 

## Bonus D

猜测 输入有序？

实际：每次归并最多有一个数作为逆序对

原因：如果有2个及以上的数字作为逆序对起始 那么就可以先换2再换1

 

## Bonus E

先排序 然后二分答案

如何check？滑动区间检测

如何滑动区间?参考4C

 

反正不能直接存diff 不然肯定要炸 （目测内存占用约400M）

 

最后：二分答案

这个题目的关键在于judge部分

而且就算二分结束了 对应的数字也可能不存在 需要再想办法另外判定

假设每次计算出来 有lessnum个数<=当前的mid 然后当前的一半的数的个数是half

那么如果lessnum>=half 这个数是可能可以选的 但是可能比真正的中位数大 所以要缩小

如果lessnum<half 这个数是肯定不能选的 而且肯定比真正的中位数要小 所以要扩大

但是为了确保这个mid真的是存在的 需要在判定的时候通过比较最终的arr[j]-arr[i]==mid来判定是否存在

为了防止不存在 一个想法是先把它逼到不行（逐渐减小） 在二分结束后再慢慢扩大 到第一个满足lessnum<=half而且存在的数

 

```
测试数据

SHOULD BE:
58
1
45
31
3
20
0
1
31
20


10
8
320 67 1 12 31 42 89 110 
4
1 2 3 4
10
1 4 9 99 12 17 32 57 88 120
9
17 1 12 32 57 88 99 4 9 
6
16 0 0 0 2 3
10
1 3 34 21 65 2 4 76 9 10
1
99999
4
1 2 3 4
10
1 3 34 21 65 2 45 76 9 10
20
1 2 3 4 1 3 34 21 65 2 45 76 9 10 90 12 4
```

 

## 快排

参考：[Quicksort](https://www.youtube.com/watch?v=aQiWF4E8flQ) （<https://www.youtube.com/watch?v=aQiWF4E8flQ>)

 

这个视频里说的其实有点问题 有一些特殊情况没有考虑到 例如存在相等的数字

快排本质上是随机算法 所以需要多测几次

快排/归并这种递归分治算法 一个麻烦的地方是left right值的确定

（一个方便的想法是把0=left len=right 然后就正常循环就好了）

在归并里 直接遵守一般的左闭右开就没问题

但是在快排里 虽然依然要遵守左闭右开 但是注意到每次一轮之后中间的值已经排好了 所以下一轮分治的时候需要跳过中间值 具体操作是左侧是 l,mid 右侧是 mid+1,r

 

一开始先随机换掉最后一个数 然后用最后一个数作为主元

关于wall 一开始要先推进到>pivot的地方 后面的替换才是正常的 同理cur的值也需要调整

中间具体的交换没啥问题 注意推进的顺序和防止越界就好了

注意不止cur可能越界 wall也有可能

（极端情况：pivot刚好是当前最大值）

（如果wall越界了(idx=r-1 实际上就是pivot的index) 那么说明所有数都比pivot小 本轮无需调换 不需要再swap）

最后结束的时候 实际上是把cur换到了wall的位置 所以currentmid就是wall

然后分成两半 递归到下一轮就算OK了

 