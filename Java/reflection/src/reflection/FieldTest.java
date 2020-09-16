package reflection;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FieldTest {
    @Test
    public void testField() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class clazz = Person.class;
        Person p = (Person) clazz.newInstance();
        Field age = clazz.getDeclaredField("age");
        age.setAccessible(true);
        age.set(p, 33);
        Object o = age.get(p);
        System.out.println(o);

    }

    @Test
    public void testMethod() throws Exception {
        Class clazz = Student.class;
        Student student = (Student) clazz.newInstance();
        Method show = clazz.getDeclaredMethod("show", String.class);
        show.setAccessible(true);
        String str=(String)show.invoke(student,"zhangsan");
        System.out.println(str);
    }
}
