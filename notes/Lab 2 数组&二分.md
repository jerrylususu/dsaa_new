# Lab 2 数组&二分

先说说能够使用二分的条件：有序+结果单调

注意 二分退出后并不就一定成功了 有的时候需要用一个while循环上下调整一下

## A

已经排序过了 显然是二分搜索

但是注意需要使用快读 不然会被卡在读入

## B

本质上还是二分 不过套了一个数学题的壳

注意到在定义域 (0<x<100) 内 二阶导数恒>0 则一阶导数呈上升 则一阶导数的0点 即为函数在此定义域内的最小值点

然后就用一阶导数为0二分 找到导数为0的x值 带入原公式求值即可

注意：dx不要太小 不然会T 可以适当增大 2e-7~1e-5内都没问题

## C

题意：找两个数和为指定值的组数

注意到输入数据已经排序了 用左右光标法向中间逼近即可

## D

有坑 需要用二分

最直观想法：每次遇到新的订单 先检查空房数量是否允许插入 允许就进行录入操作 不行就跳出

然而这样做会WA而不是T？原因未知？？？

但是因为这样需要遍历每个订单时间段中的每个日期 效率较低



修改之后：二分检查 检查点是最后一个可行订单的编号 

具体做法：用一个tmp数组作为标记（已经占用的房间数） 对每个订单 开始日的占用房间数+订单房间数 结束日占用房间数-订单房间数

这样只需要检查一次tmp数组 就可以判定有没有某一天超出了可用房间数了

## E

还是用二分 把每个人最多需要跑的距离作为二分需要求出的值 能否跑完全程作为二分验证标准 对每个运动员用贪心（尽可能往前跑 当加入下一段会导致自己超出最长距离的时候再换人）

## F

通过计算 可以得到总距离是一个类抛物线（凸性）函数 有一个特定的最小值点

思想：三分：二分的加强版本 可以搜索一个类抛物线函数的最小/最大值点

具体做法：对一个left,right区间 先求mid 再求mid,right区间的终点midmid 

在每次迭代中比较mid和midmid谁更接近最值点 然后舍弃掉对侧的区间

```java
mid = (left + right) / 2;
midmid = (mid + right) / 2;
if (cal(mid) < cal(midmid)) // 假设求最大值
	left = mid; // midmid更大 抛弃左侧区间
else
	right = midmid; // mid更大 抛弃右侧区间
```

## G

一个很神奇的题目...这题居然也可以用二分...

一开始的想法：计算每个科目对GPA的贡献 删除那些贡献为负值或者很低的 （问题：找不到一个很好的量度来衡量贡献值）

（例子 如果考了两科 学分-成绩为 10-1 100-5 如果单纯按照分数/学分 然后删除低的 似乎应该删除后者 但是实际上删除后者后GPA为1 删除前者后GPA为5）

具体的二分做法：把能达到的最高GPA作为二分需要得到的值 把是否能在删除k个或以下成绩后到达此GPA作为二分的判定标准

实现的时候 还是要计算每个成绩对GPA的贡献：res = credit*(score-goal)  每个计算完之后拍一次序 直接删除前k个 然后计算剩下科目的GPA 看是否能够达到要求