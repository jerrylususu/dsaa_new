# Lab 3 链表

## A

有序链表合并 双指针法

## B

多项式相加... 基本上就是A的改版 不过注意多项式输出的坑...

可能的坑包括常数项、系数为1和-1的处理、结果为0的输出

## C

题目写的有点晦涩

重点：有书的拿书看，进来发现没书就直接走了

可以直接用开一个26大小的数组 记录每个人的状态

简单题

## D

多项式求导 直接在每个节点上进行操作 或者也可以重新开一个

输出部分可以直接在B的基础上修改

算法实现的时候要注意和B的不同：存在负指数项、存在相同指数项、输入不是按照指数排序的 需要自己排序

## E

约瑟夫环问题

解法1：直接上环状链表模拟 反正数据量才100

解法2：公式递推：每次一个人退出后 剩下的人在重新编号之后又组成了一个大小为n-1的子问题

列出公式：$$ f[1]=0; f[i]=(f[i-1]+m+1) \mod  i  (i>1) $$

m 每次数的人数 f[i] 第i轮获胜者在当前轮的编号

每一轮中编号从0开始

## F

题目比C还难懂：统计每个大小>=k的区间中第k大的数的和

解法见下图

（credit: 李hn老师 姚yz老师）

![1545997508835](assets/1545997508835.png)

## G

动态区间中位数问题

加强版本：数据流中位数

可以用大小堆/对顶堆 也可以用数组+链表的实现

对顶堆操作：一个大顶堆（堆的顶部为最大值）+一个小顶堆 把这两个堆对顶的平均数作为分界线 比分界线小的放入大顶堆 大的放入小顶堆 放完之后检查两个堆是否元素个数差超过1 如果超过了 就把多的那个堆的堆顶放入少的那个堆 然后中位数要么是两个堆顶的平均值 要么是多的那一方的堆顶

或者也可以指定某个堆总是多一个元素 例如总是让小顶堆比大顶堆元素多 这样后面判定会相对简单一点

用数组按照输入顺序存储 链表按照实际大小顺序存储 每次删掉数组最后的两个 根据删掉数字和中位数的相对大小来决定中位数指针的移动方向