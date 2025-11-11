import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClassParser {
    private final ClassLoader classLoader;
    private File root;

    public ClassParser() {
        classLoader = getClass().getClassLoader();
    }

    // Public methods

    public List<Class<?>> getTestClasses() {
        try {
            Enumeration<URL> urls = classLoader.getResources("");

            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                root = new File(url.getPath());

                List<Class<?>> testClasses = handleDirOrFile(root);

                return testClasses;
            }
        } catch (IOException e) {
            System.out.println("Unable to find test classes.");
            System.out.println("Error occurred: " + e);
            e.printStackTrace();
        }

        return null;
    }

    public List<String> getClassNames(List<Class<?>> classes) {
        List<String> classNames = new ArrayList<>();

        for (Class<?> class_ : classes) {
            classNames.add(class_.getName());
        }

        return classNames;
    }

    // Private methods

    private List<Class<?>> handleDirOrFile(File entry) {
        List<Class<?>> testClasses = new ArrayList<>();

        if (entry.isDirectory()) {
            List<Class<?>> subTestClasses = handleDirectory(entry);
            testClasses.addAll(subTestClasses);
        } else if (entry.isFile()) {
            if (isTestClass(entry)) {
                testClasses.add(getAsClass(entry));
            }
        }

        return testClasses;
    }

    private List<Class<?>> handleDirectory(File dir) {
        List<Class<?>> testClasses = new ArrayList<>();

        for (File entry : dir.listFiles()) {
            List<Class<?>> subTestClasses = handleDirOrFile(entry);
            testClasses.addAll(subTestClasses);
        }

        return testClasses;
    }

    private boolean isTestClass(File file) {
        // Check that file is a java class
        if (!file.getName().endsWith(".class"))
            return false;

        // Convert to class
        Class<?> class_ = getAsClass(file);
        if (class_ == null)
            return false;

        // Check that the class implements TestClass
        if (!implementsTestClass(class_))
            return false;

        // Check that the class has a constructor without parameters
        return hasEligibleConstructor(class_);
    }

    private Class<?> getAsClass(File file) {
        try {
            return classLoader.loadClass(toClassName(file));
        }
        catch (ClassNotFoundException e) {
            System.out.println("Unable to load class " + toClassName(file));
            System.out.println("Error: " + e);
        }
        return null;
    }

    private boolean implementsTestClass(Class<?> class_) {
        Class<?>[] interfaces = class_.getInterfaces();

        for (Class<?> interface_ : interfaces) {
            if (interface_.getName().equals("se.umu.cs.unittest.TestClass"))
                return true;
        }

        return false;
    }

    private boolean hasEligibleConstructor(Class<?> class_) {
        for (Constructor<?> constructor : class_.getConstructors()) {
            if (constructor.getParameterCount() == 0)
                return true;
        }

        return false;
    }

    private String toClassName(File file) {
        String absPath = file.getAbsolutePath();
        String rootPath = root.getAbsolutePath();

        String relativePath = absPath.substring(rootPath.length()+1);
        return relativePath.replace(File.separatorChar, '.').replaceAll("\\.class", "");
    }

}
