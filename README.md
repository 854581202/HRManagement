# HRManagement
Java窗体应用程序人事管理系统

大二时候的课程作业，代码来自清华大学出版设出版的《Java课程设计案例精编（第3版）》，不完全一样，里面一些东西已经改变了。
界面用Swing写的（已过时），仅仅是刚学Java时课程作业，老师要求做的，简单练手小项目。
<br>

开发工具：<br>
Eclipse <br>
Mysql   <br>

开发环境：<br>
jdk 1.8.0_141<br>
mysql 5.5.27<br>

代码组织结构：<br>

![Error](https://github.com/wenyaxinluoyang/HRManagement/blob/master/images/%E7%A8%8B%E5%BA%8F%E7%BB%93%E6%9E%84%E5%9B%BE.png)

运行效果图:<br>
添加员工界面：<br>
![Error载入出错](https://github.com/wenyaxinluoyang/HRManagement/blob/master/images/%E6%B7%BB%E5%8A%A0%E4%BA%BA%E5%91%98.png)
修改员工界面：<br>
![Error载入出错](https://github.com/wenyaxinluoyang/HRManagement/blob/master/images/%E4%BF%AE%E6%94%B9%E4%BA%BA%E5%91%98%E4%BF%A1%E6%81%AF.png)
删除员工界面：<br>
![Error载入出错](https://github.com/wenyaxinluoyang/HRManagement/blob/master/images/%E5%88%A0%E9%99%A4%E4%BA%BA%E5%91%98%E4%BF%A1%E6%81%AF.png)
显示所有员工信息：<br>
![Error载入出错](https://github.com/wenyaxinluoyang/HRManagement/blob/master/images/%E4%BA%BA%E5%91%98%E4%BF%A1%E6%81%AF%E6%9F%A5%E8%AF%A2.png)
部门管理界面：<br>
![Error载入出错](https://github.com/wenyaxinluoyang/HRManagement/blob/master/images/%E9%83%A8%E9%97%A8%E7%AE%A1%E7%90%86.png)
员工部门调动：<br>
![Error载入出错](https://github.com/wenyaxinluoyang/HRManagement/blob/master/images/%E4%BA%BA%E5%91%98%E8%B0%83%E5%8A%A8.png)
调动历史查询: <br>
![Error载入出错](https://github.com/wenyaxinluoyang/HRManagement/blob/master/images/%E8%B0%83%E5%8A%A8%E5%8E%86%E5%8F%B2%E6%9F%A5%E8%AF%A2.png)
员工考核界面：<br>
![Error载入出错](https://github.com/wenyaxinluoyang/HRManagement/blob/master/images/%E4%BA%BA%E5%91%98%E8%80%83%E6%A0%B8.png)
考核记录界面：<br>
![Error载入出错](https://github.com/wenyaxinluoyang/HRManagement/blob/master/images/%E4%BA%BA%E5%91%98%E8%80%83%E6%A0%B8%E5%8E%86%E5%8F%B2%E6%9F%A5%E8%AF%A2.png)
劳资分配管理界面：<br>
![Error载入出错](https://github.com/wenyaxinluoyang/HRManagement/blob/master/images/%E5%8A%B3%E8%B5%84%E5%88%86%E9%85%8D.png)
劳资历史查询：<br>
![Error载入出错](https://github.com/wenyaxinluoyang/HRManagement/blob/master/images/%E5%8A%B3%E8%B5%84%E7%AE%A1%E7%90%86%E5%8E%86%E5%8F%B2%E6%9F%A5%E8%AF%A2.png)

<br>
使用方法如下：<br>
1.下载项目压缩包并解压，将项目以java project导入。<br>
2.到自己的mysql中建立名为hrdatabase的数据库，字符集设为utf-8,然后运行文件夹内提供的hrdatabase.sql文件。<br>
3.打开项目，打开DataOperator包，修改Database类中的user，pass,user改为自己mysql的用户名，pass改为自己mysql的密码。<br>
4.如果自己的mysql位于其它主机，比如云服务器，还需修改url中的IP地址。<br>
5.打开HRPackage包，运行HrMain。<br>

<br>
<br>
使用过程中可能出现的问题，项目当时在eclipse中，默认的字符集是GBK编码，如果你的工具里面使用utf-8，运行后，<br>
界面正常显示但是界面上所有汉字都会呈现出乱码的状态。可以自己使用一些转编码的工具转换编码以保证中文的正常显示。<br>
如果导入后出现大量报错，且导入步骤无误，请检查自己的JDK版本，是否为1.8。<br>

<br>
<br>
如果发现有bug，请在Issues提问。<br>
如果觉得对你有用，请点star.<br>
如果有其他问题，欢迎发送邮件至572154391@qq.com询问 或到 https://blog.csdn.net/wyxeainn? csdn下留言。

