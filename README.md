# fileio-exercises
数据库课老师布置的文件读写练习

- 1.建两个文本文件
- 2.插入学生信息
- 3.查询学生对应的奖励
- 4.增删改信息

## 文件1 学生基本信息

| 学号       | 姓名 | 性别 | 年龄 | 专业     | 位置 | 长度 |
| ---------- | ---- | ---- | ---- | -------- | ---- | ---- |
| 2017901006 | 杨啸 | 男   | 21   | 软件工程 | 0    | 30   |
| ……         | ……   | ……   | ……   | ……       | ……   | ……   |

- 位置：奖励文件内对应的位置
- 长度：奖励文件内对应的奖励字段的长度

## 文件2 奖励

记录学生获得的奖励

|             奖励             |
| :--------------------------: |
| 2011校奖学金，2012国家奖学金 |
|        2012校优秀学生        |

## 使用方法

### 源代码入口

view包中的ConsoleMenu类的main函数。

### jar包

在jar包所在目录下使用命令：

```bash
$java -jar xxx.jar
```

以运行打包好的程序

## 测试文件

- Rewards.csv
- StudentInfoList.csv

如果这两个文件出现问题，可以运行StudentInfoSystem.java这个类来重新生成，也可以自行录入学生信息，并使用保存到文件选项。

