# Final Project
## Bank ATM
## Files
---------------------------------------------------------------------------
All .java files package ood/[subfolder]
Detailed description for each file is in the design document.

## How to compile and run
---------------------------------------------------------------------------
Environment: Java 1.8, maven 3.8.1, mysql 8.0.28.
1. Navigate to the directory "final/src/main/java/ood" after unzipping the files
2. Make sure you have the mysql database and change the pom.xml to your local mysql version
3. Add whole project as maven project and install packages.
4. Navigate to db folder, run in terminal : mysql -u root -p BANK < ./Bank.sql
5. Change the DB username and password in ood/utils/DBReader.java
6. Run the following instructions:
    find . -name "*.java" > sources.txt && javac -d bin @sources.txt
    javac ood/Main.java
    java ood.Main
    OR
    Open the project in idea. We used IntelliJ.

## GUI:

![image-20221219000326409](/Users/zhengxuwang/Library/Application Support/typora-user-images/image-20221219000326409.png)

![image-20221219000341496](/Users/zhengxuwang/Library/Application Support/typora-user-images/image-20221219000341496.png)

![image-20221219000411231](/Users/zhengxuwang/Library/Application Support/typora-user-images/image-20221219000411231.png)

![image-20221219000427320](/Users/zhengxuwang/Library/Application Support/typora-user-images/image-20221219000427320.png)

![image-20221219000451049](/Users/zhengxuwang/Library/Application Support/typora-user-images/image-20221219000451049.png)
