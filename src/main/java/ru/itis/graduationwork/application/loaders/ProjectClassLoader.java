package ru.itis.graduationwork.application.loaders;

import ru.itis.graduationwork.application.managers.files.ConfigManager;
import ru.itis.graduationwork.application.managers.files.FilesManager;

import java.io.File;
import java.io.IOException;

public class ProjectClassLoader extends ClassLoader {

    private static final String CLASS_EXTENSION = ".class";
    public static final String TARGET_FOLDER = "target";

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
