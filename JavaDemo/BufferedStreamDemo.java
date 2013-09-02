import java.io.*; 
public class BufferedStreamDemo {

  /**
    * @param args
    */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    File filein = new File("/home/jltxgcy/123.jpg");
    File fileout = new File("/home/jltxgcy/321.jpg");
    try {
      if (fileout.exists() == false) {
        fileout.createNewFile();
      }
      FileInputStream in = new FileInputStream(filein);
      FileOutputStream out = new FileOutputStream(fileout);
      byte[] b = new byte[1];
      BufferedInputStream bin = new BufferedInputStream(in);
      BufferedOutputStream bout = new BufferedOutputStream(out);
      while (bin.read(b)!=-1)
      {
        bout.write(b);
      }
      bout.close();
      bin.close();
      out.close();
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

