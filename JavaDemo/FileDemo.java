import java.io.File;
 
  public class FileDemo {
 
   public static void main(String[] args) throws Exception {
	File f = new File("a/b/c");
	f.mkdirs();
	//File f1 = new File("a/b/c","1.txt");
	File f1 = new File(f,"1.txt");//两种方式均可
	f1.createNewFile();
	File f2 = new File("1.txt");//在当前目录下创建
   }
 
}
