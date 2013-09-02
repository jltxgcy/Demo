import java.io.*;

public class RandomAccessFileDemo {
	public static void main(String[] args) throws Exception {
		  RandomAccessFile file = new RandomAccessFile("file", "rw");//已经创建了文件
		  // 以下向file文件中写数据
		  file.writeInt(20);// 占4个字节
		  file.writeDouble(8.236598);// 占8个字节
		  file.writeUTF("这是一个UTF字符串");// 这个长度写在当前文件指针的前两个字节处，可用readShort()读取
		  file.writeBoolean(true);// 占1个字节
		  file.writeShort(395);// 占2个字节
		  file.writeLong(2325451l);// 占8个字节
		  file.writeUTF("又是一个UTF字符串");
		  file.writeFloat(35.5f);// 占4个字节
		  file.writeChar('a');// 占2个字节

		  file.seek(0);// 把文件指针位置设置到文件起始处

		  // 以下从file文件中读数据，要注意文件指针的位置
		  System.out.println("——————从file文件指定位置读数据——————");
		  System.out.println(file.readInt());
		  System.out.println(file.readDouble());
		  System.out.println(file.readUTF());

		  file.skipBytes(3);// 将文件指针跳过3个字节，本例中即跳过了一个boolean值和short值。
		  System.out.println(file.readLong());

		  file.skipBytes(file.readShort()); // 跳过文件中“又是一个UTF字符串”所占字节，注意readShort()方法会移动文件指针，所以不用加2。
		  System.out.println(file.readFloat());
		  System.out.println(file.readChar());
		  System.out.println(file.length());
	}
}
