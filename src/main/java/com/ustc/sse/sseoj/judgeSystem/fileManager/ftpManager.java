package com.ustc.sse.sseoj.judgeSystem.fileManager;

import com.ustc.sse.sseoj.judgeSystem.constant;
import lombok.Getter;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/2 12:12
 */
public class ftpManager {


    @Getter
    FTPClient ftpClient =null;


    public ftpManager() {
        try {
            ftpClient = getFTPClient(constant.ftpServer, constant.ftpUser, constant.ftpPassword);
            System.out.println(ftpClient.getReplyString());
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                System.err.println("连接FTP服务器失败！");
                System.exit(1);
            }
//            createDir(ftpClient,"1000111");//创建文件夹
//           upload(ftpClient,"C:\\Users\\43441\\Desktop\\test3.in","1000111/");//创建文件

            //deleteFile(ftpClient,"1000111/test3.in");
           // deleteDirAndAllFile(ftpClient,"1000111/");

            //设置上传路径
            //ftpClient.changeWorkingDirectory("/home/judge/data");
            //ftpClient.deleteFile("/home/judge/data/1000111/test3.in");//删除文件
            // ftpClient.removeDirectory("/home/judge/data/1000111/");//删除目录//还不行,尝试直接删除底下的所以答案还没试，空目录删除成功了
            //createDir(ftpClient,"1000111");//创建文件夹
            //upload(ftpClient,"C:\\Users\\43441\\Desktop\\test3.in","/home/judge/data/1000111/");//创建文件
            //deleteDirAndAllFile(ftpClient,"/home/judge/data/1000111/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //断开ftp连接 yes
    public void closeFtpConnect()
    {
        if (Objects.nonNull(ftpClient)) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //获取ftp服务器 yes
    private  FTPClient getFTPClient(String server, String username, String password) throws IOException {
        FTPClient ftpClient = new FTPClient();
        FTPClientConfig config = new FTPClientConfig();
        config.setServerTimeZoneId("Asia/Shanghai");
        ftpClient.configure(config);
        ftpClient.connect(server);
        ftpClient.login(username, password);
        return ftpClient;
    }

    //上传文件 上传文件 dest只要给出文件夹名即可，需要加"/",自动放到/home/judge/data/里 yes
    public  boolean upload(String source, String dest) {
        dest=constant.ftpWorkPlace+dest;
        boolean flag = false;
        Path path = Paths.get(source);
        try (InputStream inputStream = Files.newInputStream(path)) {
            ftpClient.changeWorkingDirectory(dest);
            flag = ftpClient.storeFile(path.getFileName().toString(), inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }




    // 然后再利用ftpclient的makeDirectory方法创建文件夹,dirname直接用要起的名字即可，会自动的建在/home/judge/data/底下 yes
    public  void createDir(String dirname) {
        try{
            ftpClient.changeWorkingDirectory(constant.ftpWorkPlace);
            ftpClient.makeDirectory(dirname);
            System.out.println("在目标服务器上成功建立了文件夹: "+dirname);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    //删除某文件夹，及文件夹底下的所有文件 dirname给在/home/judge/data/底下的文件夹名字即可
    public  void deleteDirAndAllFile(String dirname)
    {
        List<String> filesName=getAllFileNameFromDir(dirname);
        for(String file:filesName)
        {
            deleteFile(file);
        }
        try {
            ftpClient.removeDirectory(constant.ftpWorkPlace+dirname);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取某文件夹下的所有文件  dirname 直接用 /home/judge/data/底下的文件夹名，需要加"/"，返回参数会包括data/底下的所有
    public List<String> getAllFileNameFromDir(String dirname)
    {
        String dirname1=constant.ftpWorkPlace+dirname;
        List<String> res=new ArrayList<>();
        try {
            ftpClient.changeWorkingDirectory(dirname1);
            for (FTPFile ftpFile : ftpClient.listFiles()) {
                res.add(dirname+ftpFile.getName());
                System.out.println(ftpFile.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //列出当前路径下的文件及其文件夹
        return res;
    }

    //删除某文件 需要给出文件路径加文件名 /home/judge/data/底下的文件夹名加文件名即可
    public boolean deleteFile( String filename)
    {
        filename=constant.ftpWorkPlace+filename;
        try {
            ftpClient.deleteFile(filename);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



}
