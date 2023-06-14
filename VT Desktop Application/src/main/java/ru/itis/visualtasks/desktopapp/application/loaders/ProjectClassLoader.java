package ru.itis.visualtasks.desktopapp.application.loaders;

import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.files.FilesManager;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ProjectClassLoader extends ClassLoader {

    private static final String CLASS_EXTENSION = ".class";
    public static final String TARGET_FOLDER = "target";

    public ProjectClassLoader(ClassLoader classLoader) {
        super(new URLClassLoader(
                getUrls(),
                classLoader
        ));
    }

    private static URL[] getUrls(){
        File file = new File(ConfigManager.getProjectPath() + File.separator + "libs");
        if (file.exists()){
            URL[] result = new URL[file.listFiles().length];
            int counter = 0;
            for (File f: file.listFiles()){
                try{
                    result[counter++] = f.toURI().toURL();
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
            return result;
        }
        return new URL[0];
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        File classFile = findFile(className);
        if (classFile == null) {
            return findSystemClass(className);
        }
        return getClassFromFile(classFile, className);
    }

    private File findFile(String className) {
        String pathToClass = getFilePathFromClassName(className);
        File file = new File(pathToClass);
        if (!file.exists()) {
            file = null;
        }
        return file;
    }

    private String getFilePathFromClassName(String className){
        return ConfigManager.getProjectPath() + File.separatorChar + TARGET_FOLDER + File.separatorChar
                + className.replace('.', File.separatorChar) + CLASS_EXTENSION;
    }

    private Class<?> getClassFromFile(File classFile, String className) throws ClassNotFoundException{
        try {
            return loadAndDefineClass(classFile, className);
        } catch (IOException e) {
            throw new ClassNotFoundException("Cannot load class " + className + ": " + e);
        } catch (ClassFormatError e) {
            throw new ClassNotFoundException("Format of class file incorrect for class " + className + " : " + e);
        }
    }

    private Class<?> loadAndDefineClass(File classFile, String className) throws IOException {
        byte[] classBytes = FilesManager.readFileBytes(classFile);
        return defineClass(className, classBytes, 0, classBytes.length);
    }

}
