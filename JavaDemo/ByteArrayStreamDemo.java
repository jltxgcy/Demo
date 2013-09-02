import java.io.*;
class ByteArrayStreamDemo {
	
	public static void main(String[] args) {
		// TODO: Add your code here
		String tmp="abcdefjklmnopqrst";
		byte [] src=tmp.getBytes();
		ByteArrayInputStream input=new ByteArrayInputStream(src);
		ByteArrayOutputStream output=new ByteArrayOutputStream();//这里不用传递字节数组，ByteArrayOutputStream会自动创建一个32字节的缓冲区用来写入数据"Creates a new byte array output stream."
		transform(input,output);
		byte[] result=output.toByteArray(); //public byte[] toByteArray() "Creates a newly allocated byte array. Its size is the current size of this output stream and the valid contents of the buffer have been copied into it."

		System.out.println(new String(result));
		System.out.println(new String(src));
	}
	
	public static void transform(InputStream in,OutputStream out){
		int ch=0;
		try{
			while((ch=in.read())!=-1){
			int upperCh=/*(int)*/Character.toUpperCase((char)ch); //char表示的范围比int小
			out.write(upperCh);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
}
