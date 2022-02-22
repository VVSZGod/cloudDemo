package com.cloud.ribbon.consumer.util;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class EAMail {

    private String SMTP_HOST_NAME = "mail.xlsgrid.net";
    private String SMTP_PORT = "25";
    private String sendTo = "";//数组的问题
    private String from="xlsgrid@xlsgrid.net";
    private String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private String userPassword="Eru43wPo";
    private String userId="xlsgrid";
    private String filepath="";
    private String[] files = null;
    private int m_flen = 10; //默认附件数量是10个
    private MimeMessage msg=null;


    /**
     * 默认采用xlsgrid邮件服务器的xlsgrid帐号登录并进行发送邮件
     */
    public EAMail(){
        this.SMTP_HOST_NAME = "smtp.exmail.qq.com";
        this.SMTP_PORT = "25";
        this.from="xlsgrid@xlsgrid.net";
        this.userPassword="Eru43wPo";
        this.userId="xlsgrid@xlsgrid.net";

    }

    /**
     * 没有使用安全ssl协议连接smtp进行发送邮件
     *
     * @param SMTP_HOST_NAME  smtp服务器地址
     * @param SMTP_PORT       smtp服务器端口
     * @param from            发件人邮件地址
     * @param userPassword    发件人登陆密码
     * @param userId          发件人登陆账号
     */
    public EAMail(String SMTP_HOST_NAME,
                  String SMTP_PORT,
                  //String SSL_FACTORY,    //是ssl协议安全连接
                  String from,
                  String userPassword,
                  String userId){
        this.SMTP_HOST_NAME = SMTP_HOST_NAME;
        this.SMTP_PORT = SMTP_PORT;
        this.from=from;
        this.userPassword=userPassword;
        this.userId=userId;
    }
    public void setServer(String SMTP_HOST_NAME,
                          String SMTP_PORT,
                          //String SSL_FACTORY,    //是ssl协议安全连接
                          String from,
                          String userPassword,
                          String userId){
        this.SMTP_HOST_NAME = SMTP_HOST_NAME;
        this.SMTP_PORT = SMTP_PORT;
        this.from=from;
        this.userPassword=userPassword;
        this.userId=userId;
    }

    public static void main(String args[]) throws Exception {
        //G:\Download\data
        //Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        //"smtp.exmail.qq.com","25","xlsgrid@xlsgrid.net","Eru43wPo","xlsgrid@xlsgrid.net"
        try {
            EAMail a=new EAMail();
            //a.setFilepath("E:/data/sheet001.htm^E:/data/sheet003.htm^E:/data/sheet004.htm");//添加附件
            //a.setFiles("G:/Download/data/sh03.txt");
            //a.setFiles("G:/Download/data/sh031.txt");
            int i=0;
            i=a.sendMail("foolraty@xlsgrid.net,1115394982@qq.com",//发送给谁
                    "测试多个附件",//标题
                    "3个附件" //内容
            );
            //a.getInBox();
            System.out.println("Sucessfully Sent mail to All Users Sended"+i);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }



    public int sendMail(String sendto, String subject,
                        String message) throws MessagingException, UnsupportedEncodingException {
        int sended=0;
        boolean debug = true;
        String recipients[]=sendto.split(",");
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", SMTP_PORT);

        if(!SMTP_PORT.equalsIgnoreCase("25")){
            props.put("mail.smtp.socketFactory.port", SMTP_PORT);//用于ssl安全连接
            props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        }

        props.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {//认证用户名密码
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userId, userPassword);
                    }
                }
        );
        session.setDebug(debug);
        this.msg = new MimeMessage(session);
        InternetAddress addressFrom = new InternetAddress(from);
        this.msg.setFrom(addressFrom);
        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++) {
            addressTo[i] = new InternetAddress(recipients[i]);
            sended++;
        }
        this.msg.setRecipients(Message.RecipientType.TO, addressTo);
        // Setting the Subject and Content Type
        sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
        subject = "[FRACAS]"+subject;

        this.msg.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B") );
        message=new String(message);

        this.msg.setContent(message, "text/html;charset=GBK");

        /*///////////////////////////////////////////////
        if(!filepath.equalsIgnoreCase("")){
          Multipart mp = new MimeMultipart();
              MimeBodyPart mbp = new MimeBodyPart();
              mbp.setContent(message, "text/html;charset=GBk");
              mp.addBodyPart(mbp);
                      mbp=new MimeBodyPart();
                      int pos = filepath.lastIndexOf("\\");
                      if(pos<0)pos = filepath.lastIndexOf("/");
                      String attachment = filepath.substring(pos + 1);
                      FileDataSource fds=new FileDataSource(filepath); //得到数据源
                      mbp.setFileName(MimeUtility.encodeText(attachment));//设置附件的名称
//                    会有linux下乱码问题  mbp.setFileName(fds.getName());  //得到文件名同样至入BodyPart
                      mbp.setDataHandler(new DataHandler(fds)); //得到附件本身并至入BodyPart

                      mp.addBodyPart(mbp);

              this.msg.setContent(mp); //Multipart加入到信件
        }
        *////////////////////////////////////////////////

        if(!filepath.equalsIgnoreCase("")){
            String fp[] = filepath.split("^");
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            html.setContent(message, "text/html;charset=GBk");
            mainPart.addBodyPart(html);
            msg.setContent(mainPart);
            for (int i=0;i<fp.length;i++) {
                if (!fp[i].equals("")) {
                    // 建立第二部分：附件
                    html = new MimeBodyPart();
                    // 获得附件
                    DataSource source = new FileDataSource(fp[i]);
                    //设置附件的数据处理器
                    html.setDataHandler(new DataHandler(source));
                    // 设置附件文件名
                    String fname = fp[i].substring(fp[i].lastIndexOf("/")+1);
                    html.setFileName(fname);
                    // 加入第二部分
                    mainPart.addBodyPart(html);
                }
            }
        }
        if (files != null && files.length > 0) {
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            html.setContent(message, "text/html;charset=GBk");
            mainPart.addBodyPart(html);
            msg.setContent(mainPart);
            for (int i=0;i<files.length;i++) {
                if (files[i] != null && !files[i].equals("") && files[i].length() > 1) {
                    // 建立第二部分：附件
                    html = new MimeBodyPart();
                    // 获得附件
                    DataSource source = new FileDataSource(files[i]);
                    //设置附件的数据处理器
                    html.setDataHandler(new DataHandler(source));
                    // 设置附件文件名
                    String fname = files[i].substring(files[i].lastIndexOf("/")+1);
                    html.setFileName(fname);
                    // 加入第二部分
                    mainPart.addBodyPart(html);
                }
            }
        }

        Transport.send(msg);
        return sended;
    }

    //设置上传的附件
    public void setFiles(String fileName) {
        if (files == null) {
            files = new String[m_flen];
        }
        for (int i=0;i<files.length;i++) {
            if (files[i] == null || files[i].equals("") || files[i].length() == 0) {
                files[i] = fileName;
                break;
            }
        }
    }

    //设置最大附件数
    public void setMaxFiles(int len) {
        this.m_flen = len;
    }

    /**
     *  设置上传文件的地址
     * @param filepath
     */
    public void setFilepath(String filepath)
    {
        this.filepath = filepath;
    }
    /*
     *
     * */
   /* public EADS getInBox()throws Exception{
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);//用于ssl安全连接
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {//认证用户名密码
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userId, userPassword);
                    }
                }
        );
        session.setDebug(true);
        URLName urln = new URLName("pop3", SMTP_HOST_NAME, 110, null,
                userId, userPassword);
        Store store = session.getStore(urln);
        store.connect();
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);
        Message message[] = folder.getMessages();
        System.out.println("Messages's length: " + message.length);
        for (int i = 0; i < message.length; i++) {
            Message onemsg = message[i];
            String subject=MimeUtility.decodeText(onemsg.getSubject());
            System.out.println(subject);
        }


        this.msg = new MimeMessage(session);

        EAXmlDS ds1 = new EAXmlDS();//EADbTool.FindBySQL(sql);

        // 以下是单据的查询条件
        int row = ds1.AddRow(-1);
        ds1.setValueAt(row,"ID","BILID");
        ds1.setValueAt(row,"NAME","单据编号");
        ds1.setValueAt(row,"NOTE","单据的号码");
        ds1.setValueAt(row,"COLTYP","VARCHAR2");
        row = ds1.AddRow(row);
        ds1.setValueAt(row,"ID","DAT");
        ds1.setValueAt(row,"NAME","单据日期");
        ds1.setValueAt(row,"NOTE","单据上显示的日期");
        ds1.setValueAt(row,"COLTYP","DATE");
        return ds1;

    }*/

}