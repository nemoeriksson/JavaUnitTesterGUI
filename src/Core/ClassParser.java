package Core;

import java.io.*;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * A utility class for loading test classes from
 * the class path at runtime.
 *
 * @author c24nen
 * @version 25.11.17
 */
public class ClassParser {
    private final ClassLoader classLoader;
    private File root;

    public ClassParser() {
        classLoader = getClass().getClassLoader();
    }

    // Public methods

    /**
     * Searches for all example test classes
     * within the jar file.
     *
     * @return A list of test classes
     */
    public List<Class<?>> getInternalTestClasses() {
        List<Class<?>> testClasses = new ArrayList<>();

        try {
            // Get the location of the current class
            URL location = getClass().getProtectionDomain().getCodeSource().getLocation();
            File jarFile = new File(location.toURI());

            if (jarFile.isFile() && jarFile.getName().endsWith(".jar")) {
                try (JarInputStream jarStream = new JarInputStream(new FileInputStream(jarFile))) {
                    JarEntry entry;

                    while ((entry = jarStream.getNextJarEntry()) != null) {
                        String name = entry.getName();

                        // Look for class files in the Examples package
                        if (name.startsWith("Examples/") && name.endsWith(".class")) {
                            String className = name.replace('/', '.').replaceAll("\\.class$", "");
                            Class<?> clazz = classLoader.loadClass(className);

                            if (isTestClass(clazz)) {
                                testClasses.add(clazz);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading internal test classes: " + e);
            e.printStackTrace();
        }

        return testClasses;
    }

    /**
     * Searches for all valid test classes from the
     * class path.
     *
     * @return A list of test classes
     */
    public List<Class<?>> getTestClasses() {
        try {
            Enumeration<URL> urls = classLoader.getResources("");

            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                root = new File(url.getPath());

                return handleDirOrFile(root);
            }
        } catch (IOException e) {
            System.out.println("Unable to find test classes.");
            System.out.println("Error occurred: " + e);
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gets a list of class names from a list of classes.
     *
     * @param classes A list of classes
     * @return A list of strings containing class names
     */
    public List<String> getClassNames(List<Class<?>> classes) {
        List<String> classNames = new ArrayList<>();

        for (Class<?> class_ : classes) {
            classNames.add(class_.getName());
        }

        return classNames;
    }

    // Private methods

    /**
     * Returns a list of valid test classes inside a given
     * directory.
     * If a regular file is provided the list will only
     * contain the class it implements if it is a valid
     * test class.
     *
     * @param entry A file entry
     * @return A list of classes
     */
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

    /**
     * Returns a list of all valid test classes inside a
     * directory or one of its subdirectories.
     *
     * @param dir A directory file
     * @return A list of test classes
     */
    private List<Class<?>> handleDirectory(File dir) {
        List<Class<?>> testClasses = new ArrayList<>();

        for (File entry : dir.listFiles()) {
            List<Class<?>> subTestClasses = handleDirOrFile(entry);
            testClasses.addAll(subTestClasses);
        }

        return testClasses;
    }

    /**
     * Checks if a given file is a valid test class, i.e. follows
     * all requirements below:
     *  - Is a valid java '.class' file
     *  - Implements the se.umu.cs.unittest.TestClass interface
     *  - Has a constructor without any parameters
     *
     * @param file A file
     * @return true if the file represents a valid test class, else false
     */
    private boolean isTestClass(File file) {
        // Check that file is a java class
        if (!file.getName().endsWith(".class"))
            return false;

        // Convert to class
        Class<?> class_ = getAsClass(file);
        if (class_ == null)
            return false;

        return isTestClass(class_);
    }

    /**
     * Checks if a given class is a valid test class, i.e. follows
     * all requirements below:
     * - Implements the se.umu.cs.unittest.TestClass interface
     * - Has a constructor without any parameters
     *
     * @param class_ The class to validate
     * @return true if the file represents a valid test class, else false
     */
    private boolean isTestClass(Class<?> class_) {
        // Check that the class implements TestClass
        if (!se.umu.cs.unittest.TestClass.class.isAssignableFrom(class_))
            return false;

        // Check that the class has a constructor without parameters
        return hasEligibleConstructor(class_);
    }

    /**
     * Loads a class from a file and returns it.
     *
     * @param file A java '.class' file
     * @return A class
     */
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

    /**
     * Checks if a class has a valid constructor to be
     * a test class - i.e. one without any parameters.
     *
     * @param class_ A class
     * @return true if the class has a parameterless constructor, else false
     */
    private boolean hasEligibleConstructor(Class<?> class_) {
        for (Constructor<?> constructor : class_.getConstructors()) {
            if (constructor.getParameterCount() == 0)
                return true;
        }

        return false;
    }

    /**
     * Returns a string with the relative path (from
     * class path) to a class file.
     *
     * @param file A class file
     * @return String with relative path
     */
    private String toClassName(File file) {
        String absPath = file.getAbsolutePath();
        String rootPath = root.getAbsolutePath();

        String relativePath = absPath.substring(rootPath.length()+1);
        return relativePath.replace(File.separatorChar, '.').replaceAll("\\.class", "");
    }
}
