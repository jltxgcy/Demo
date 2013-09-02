import java.io.*;
 class DataStreamDemo {
    
    public static void main(String[] args) throws Exception{
        
        /**
         * DataInputStream与DataOutputStream
         * 
         * 1.DataInputStream → FileInputStream → InputStream
         *     (数据输入流允许应用程序以机器无关的方式从底层输入流中读取基本java数据类型)
         *   
         *   DataOutStream → FileOutputStream → OutputStream
         *   (数据输出流允许应用程序以适当方式将基本Java数据类型写入输出流中。
         *       然后，应用程序可以使用数据输入流将数据读入。)
         *   
         *   2.流：传输的二进制。
         */
        /**
         * 1.写
         */
        File file = new File("1.txt");
        //文件输出流
        FileOutputStream outputStream = new FileOutputStream(file);
        //数据输出流
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        
        //给person.data这个文件写入数据
        dataOutputStream.writeInt(123);
        dataOutputStream.writeUTF("写入中文，ok");
        dataOutputStream.writeFloat(234.4f);
        
        //关闭流
        dataOutputStream.flush();
        dataOutputStream.close();
        
        /**
         * 2.读
         */
        //文件输入流
        FileInputStream inputStream = new FileInputStream(file);
        //数据输入流
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        
        //读出
        int firstInt = dataInputStream.readInt();
        String str = dataInputStream.readUTF();
        float secFlt = dataInputStream.readFloat();
        
        System.out.println(firstInt+"---"+str+"-----"+secFlt);
        dataInputStream.close();
        
        /**
         * ★注意事项：读取和写入的顺序必须一样，否则抛出错误，也不能少读取。
         */
    }

}
