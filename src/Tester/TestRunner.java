package Tester;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {
    public boolean runTest(Constructor<?> constructor, Method method) {
        try {
            Object result = method.invoke(constructor.newInstance());
            System.out.println("Method " + method.getName() + " successfully invoked");
            return true;
        } catch (InvocationTargetException|IllegalAccessException e) {
            System.out.println("Unable to invoke method " + method.getName());
            System.out.println("Error occurred: " + e);
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.out.println("Unable to create instance of test class");
            System.out.println("Error occured: " + e);
            e.printStackTrace();
        }

        return false;
    }
}
