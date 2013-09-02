import java.util.*;
class Person
{
    private String name;
    private int age;

    Person(String name,int age)
    {
        this.name = name;
        this.age = age;
    }
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Person))
        return false;
        Person p = (Person)obj;
        return this.name.equals(p.name) && this.age == p.age;
    }
    public String getName()
    {
        return name;
    }
    public int getAge()
    {
        return age;
    }
    public String toString()
    {
        return name+age;
    }

}

public class ArrayListDemo

{
    public static void sop(Object obj)
    {
        System.out.println(obj);
    }

    public static void main(String[] args)
    {
        ArrayList<Person> al = new ArrayList<Person>();
        al.add(new Person("lisi01",30));
        al.add(new Person("lisi02",32));
        al.add(new Person("lisi02",32));
        al.add(new Person("lisi04",35));
        al.add(new Person("lisi03",33));
        al.add(new Person("lisi04",35));
        sop(al);
        ArrayList<Person> al1 = singleElement(al);
        sop(al1);
    }

     public static ArrayList<Person> singleElement(ArrayList<Person> al)
     {
            ArrayList<Person> newAl = new ArrayList<Person>();
            Iterator<Person> it = al.iterator();
           while(it.hasNext())
            {
                Person p = it.next();
                if(!newAl.contains(p))
                newAl.add(p);
            }
        return newAl;
      }
}

