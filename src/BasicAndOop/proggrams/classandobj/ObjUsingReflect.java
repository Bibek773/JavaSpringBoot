package BasicAndOop.proggrams.classandobj;

import java.lang.reflect.*;

class Student {
    String name = "Bibek";

    public void show() {
        System.out.println("Hello " + name);
    }
}

public class ObjUsingReflect {
    public static void main(String[] args) throws Exception {

        Class<?> cls = Class.forName("BasicAndOop.proggrams.classandobj.Student");

        Object obj = cls.getDeclaredConstructor().newInstance();

        Method method = cls.getMethod("show");
        method.invoke(obj);
    }
}