import java.util.*;
public class  CollectionDemo
{
    public static void main(String[] args) 
    {
        sortDemo();
    }
    public static void sortDemo()
    {
        ArrayList<String> list = new ArrayList<String>();
        list.add("abcd");
        list.add("aaa");
        list.add("zz");
        list.add("kkkkk");
        list.add("qq");
        list.add("z");
        sop(list);
        Collections.sort(list,new StrLenComparator());
        sop(list);
    }
    public static void sop(Object obj)
    {
        System.out.println(obj);
    }
}
    class StrLenComparator implements Comparator<String>
    {
        public int compare(String s1,String s2)
        {
            if(s1.length()>s2.length())
                return 1;
            if(s1.length()<s2.length())
                return -1;
                return s1.compareTo(s2);
        }
    }
