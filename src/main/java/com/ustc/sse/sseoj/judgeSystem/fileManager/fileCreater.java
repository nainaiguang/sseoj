package com.ustc.sse.sseoj.judgeSystem.fileManager;

import com.ustc.sse.sseoj.judgeSystem.constant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/2 19:09
 */
public class fileCreater {
    public static void main(String[] args) {
        //mkFile("lalala");

        //modifyFileContent("lalala","爱java","也爱小鱼儿");

        //deleteFile("lalala");

    }
    //新建文件夹以及文件
    public   void mkFile(String filename) {//给出answerid即可+.in 或.out

         String mkDirectoryPath = constant.cacheFileLocation;

         File splits = new File(mkDirectoryPath);//给新机器创建文件夹
         if (!splits.exists())
         {
             splits.mkdir();
         }

          File writeFile = new File(mkDirectoryPath, filename);
          if(!writeFile.exists()){
              try {
                  writeFile.createNewFile();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }

         }
   /**
     * 修改文件内容的三种方式    文件名，不加in和out,in文件输入内容 out文件输入内容
     * @throws IOException
     */
   public  void modifyFileContent(String filename,String content) {//给出answerid即可+.in 或 out即可

        try {
            FileWriter fw = new FileWriter(constant.cacheFileLocation + filename);
            fw.write(content, 0, content.length());
            fw.flush();
            fw.close();

        }catch (IOException e)
        {
            e.printStackTrace();
        }
//           OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(
//                         "E://a//s//PLAYLIST.LY"));
//           osw.write(s, 0, s.length());
//           osw.flush();
//
//           PrintWriter pw = new PrintWriter(new OutputStreamWriter(
//                       new FileOutputStream("E://a//s//PLAYLIST.LY")), true);
//           pw.println(s);
//
//           fw.close();
//           osw.close();
//           pw.close();
       }

    /**
     * 删除文件
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public  void deleteFile(String filename){//给出answerid即可+.in 或.out

        File file1 = new File(constant.cacheFileLocation+filename);
        if (file1.exists()) {
            file1.delete();
            System.out.println(filename+"文件已经被成功删除");
        }


    }


}
