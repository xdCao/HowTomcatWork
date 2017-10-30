package chapter1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream; /**
 * created by xdCao on 2017/10/30
 */

public class Response {

    private static final int BUFFER_SIZE=1024;
    private Request request;
    OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream=outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() {
        byte[] bytes=new byte[BUFFER_SIZE];
        FileInputStream fileInputStream = null;
        try {
            File file=new File(HttpServer.WEB_ROOT,request.getUri());
            System.out.println("File: ----- "+HttpServer.WEB_ROOT+"\\"+request.getUri());
            if (file.exists()){

                fileInputStream=new FileInputStream(file);
                int ch=fileInputStream.read(bytes,0,BUFFER_SIZE);
                if(ch!=-1){
                    String header="HTTP/1.1 200 OK\r\n"+
                            "Content-Type: text/html\r\n"+
                            "Content-Length: "+bytes.length+"\r\n"+
                            "\r\n";
                   StringBuilder stringBuilder=new StringBuilder();
                   stringBuilder.append(header);
                   stringBuilder.append(bytes);
                   outputStream.write(stringBuilder.toString().getBytes());
//                    outputStream.write(bytes,0,ch);
//                    ch=fileInputStream.read(bytes,0,BUFFER_SIZE);
                }
            }else {
                String errorMsg="HTTP/1.1 404 File Not Found\r\n"+
                        "Content-Type: text/html\r\n"+
                        "Content-Length: 23\r\n"+
                        "\r\n"+
                        "<h1>File Not Found</h1>";
                outputStream.write(errorMsg.getBytes());
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }finally {
            if (fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
