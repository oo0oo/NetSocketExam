package com.hand;

import java.io.DataInputStream; 

import java.io.FileOutputStream; 

import java.io.IOException; 

import java.net.HttpURLConnection; 

import java.net.URL; 

  

public class App { 

  

    private static final String REMOTE_FILE_URL = "http://files.saas.hand-china.com/java/target.pdf"; 

    private static final String LOCAL_FILE_PATH = "C:/Users/Prince/NetSocketExam/exam1/target.pdf"; // 改成你保存 文件的路径 

  

    public static void main(String[] args) { 

        new App(REMOTE_FILE_URL, LOCAL_FILE_PATH).download(); 

    } 

  

    private String remoteFileUrl; 

    private String localFilePath; 

  

    public App(String remoteFileUrl, String localFilePath) { 

        this.remoteFileUrl = remoteFileUrl; 

        this.localFilePath = localFilePath; 

    } 

  

    public void download() { 

        try { 

            URL url = new URL(remoteFileUrl); 

  

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); 

            httpURLConnection.setConnectTimeout(5 * 1000); // 5000 <a class="baidu-highlight" href="https://www.baidu.com/s?wd=%E6%AF%AB%E7%A7%92&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHcvrjTdrH00T1dWPycYmvRkrAFbmW-Wn1md0ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6KdThsqpZwYTjCEQLGCpyw9Uz4Bmy-bIi4WUvYETgN-TLwGUv3EPWD4njRsnHczrHm3P1f3PHnz" target="_blank">毫秒</a>内没有连接上 则放弃连接 

            httpURLConnection.connect(); // 连接 

            System.out.println("连接 URL 成功~"); 

  

            int fileLenght = httpURLConnection.getContentLength(); 

            System.out.println("文件大小：" + (fileLenght / 1024.0) + " KB"); 

  

            System.out.println("开始下载..."); 

            try (DataInputStream dis = new DataInputStream(httpURLConnection.getInputStream()); 

                    FileOutputStream fos = new FileOutputStream(localFilePath)) { 

                byte[] buf = new byte[10240]; // 根据实际情况可以 增大 buf 大小 

                for (int readSize; (readSize = dis.read(buf)) > 0;) { 

                    fos.write(buf, 0, readSize); 

                } 

                System.out.println("下载完毕~"); 

            } catch (IOException ex) { 

                System.out.println("下载时出错"); 

            } 

  

            httpURLConnection.disconnect(); 

        } catch (IOException ex) { 

            System.out.println("URL 不存在或者连接超时"); 

        } 

    } 

}