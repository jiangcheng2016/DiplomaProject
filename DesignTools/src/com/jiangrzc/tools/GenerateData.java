package com.jiangrzc.tools;

import java.io.*;

public class GenerateData {
    public static void main(String[] args) {

        handleFile();
       // writeFile();

    }

    //读文件
    public static void handleFile(){
        String pathFile = "D:\\data.txt";

        String outPathFile = "D:\\data.dat";


        try{
            File writeName = new File(outPathFile);         //相对路径，如果没有则建立一个新的 output.txt
            writeName.createNewFile(); //创建新文件，有同名的文件的话直接覆盖

            //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
            //不关闭文件会导致资源的泄露，读写文件都同理
            try (
                    FileReader reader = new FileReader(pathFile);
                    BufferedReader br = new BufferedReader(reader);  //建立一个对象，把文件内容转化

                    FileWriter writer = new FileWriter(writeName);
                    BufferedWriter out = new BufferedWriter(writer)

            ){
                String line;
                StringBuilder strBuilder = new StringBuilder();

                while( (line = br.readLine()) != null ){
                    //1.一次读入一行数据
                    System.out.println(line);

                    //2.处理数据-按照想要的格式
                    String[] strs = line.split("\t");
                    for(int i = 0; i < strs.length-1; i ++)
                    {
                        strBuilder.append(strs[i] + ",");
                    }
                    strBuilder.append(strs[strs.length-1]);

                    //写入文件
                    out.write(strBuilder.toString() + "\r\n");

                    //写入文件后清空strBuilder
                    strBuilder.delete(0,strBuilder.length());

                    out.flush();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 写入文件
     */
    public static void writeFile(String line){
        String outPathFile = "D:\\out.txt";

        try {
            File writeName = new File(outPathFile);         //相对路径，如果没有则建立一个新的 output.txt
            writeName.createNewFile();  //创建新文件，有同名的文件的话直接覆盖

            try(FileWriter writer = new FileWriter(writeName);
                BufferedWriter out = new BufferedWriter(writer)
            ){
                out.write(line + "\r\n");
//                out.write("写入文件第1行\r\n");
//                out.write("写入文件第2行\r\n");
                out.flush();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
