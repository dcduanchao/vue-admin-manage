package com.example.dc.utils;

import com.jcraft.jsch.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @ Author     ：duanchao
 * @ Date       ： 17:38 2020/11/4
 * @ Description：  没有经过测试
 */
public class SshUtils {

    private static final int DEFAULT_PORT = 80;
    private static final int DEF_WAIT_SECONDS = 30;

    private static String username = "root";
    private static String host = "49.232.1.162";
    private static String password = "Dc@673836112";
    public static final String SFTP_PROTOCAL = "sftp";

    //源文件


        public  static void upFile(MultipartFile multipartFile, String path) throws Exception {

            ChannelSftp connet = connet();

            connet.put(multipartFile.getInputStream(),path);
        }






    public static ChannelSftp connet() throws Exception {
        Channel channel = null;

        ChannelSftp sftp = null;
        Session session = createSession();

        channel = session.openChannel(SFTP_PROTOCAL);
        channel.connect(1000);
        sftp = (ChannelSftp) channel;
        return sftp;
    }

    public static Session createSession() throws Exception {

        Session session = null;
        JSch jsch = new JSch();

        session = jsch.getSession(username, host, DEFAULT_PORT);
        // 如果服务器连接不上，则抛出异常
        if (session == null) {
            throw new Exception(host + "session is null");
        }
        // 设置第一次登陆的时候提示，可选值：(ask | yes | no)
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(password);
        // 设置登陆超时时间
        session.connect(15000);

        return session;

    }

}