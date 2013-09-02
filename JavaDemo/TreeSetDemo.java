import java.util.*;

class Student implements Comparable<Student>
{
    private String name;
    private int age;
    Student(String name,int age)
    {
        this.name = name;
        this.age = age;
    }
    public int compareTo(Student stu)
    {
        if(!(stu instanceof Student))
        throw new RuntimeException("");
        Student s = stu;
        if(this.age>s.age)
        return 1;
        if(this.age==s.age)
        {
        return this.name.compareTo(s.name);
        }
        return -1;

    }
    public String getName()
    {
    return name;
    }
    public int getAge()
    {
    return age;
    }
}

public class TreeSetDemo
{
    public static void main(String[] args) 
    {
        TreeSet<Student> ts = new TreeSet<Student>(/*new MyCompare()*/);
        ts.add(new Student("lisi02",22));
        ts.add(new Student("lisi02",21));
        ts.add(new Student("lisi07",20));
        ts.add(new Student("lisi09",19));
        ts.add(new Student("lisi06",18));
        ts.add(new Student("lisi06",18));
        ts.add(new Student("lisi07",29));

        //ts.add(new Student("lisi007",20));

        //ts.add(new Student("lisi01",40));
        Iterator<Student>  it = ts.iterator();
        while(it.hasNext())
        {
            Student stu = it.next();
            System.out.println(stu.getName()+"..."+stu.getAge());
        }
    }
}




class MyCompare implements Comparator<Student> 
{
    public int compare(Student s1,Student s2)
    {
        int num = s1.getName().compareTo(s2.getName());
        if(num==0)
        {
            return new Integer(s1.getAge()).compareTo(new Integer(s2.getAge()));

        /*

        if(s1.getAge()>s2.getAge())

        return 1;

        if(s1.getAge()==s2.getAge())

        return 0;

        return -1;

        */

        }
        return num;
    }

}

