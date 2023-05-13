package ru.itis.visualtasks.desktopapp.application.compilers.java;

import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;
import java.util.Map;

public class SolutionJavaSourceFromString extends SimpleJavaFileObject {

    private final String code;

    protected SolutionJavaSourceFromString(URI uri, Kind kind, String code) {
        super(uri, kind);
        this.code = prepareCode(code);
    }

    private String prepareCode(String code){
        Map<String, String> wrappersNames = ConfigManager.getWrappersNames();
        if (wrappersNames != null){
            for (String wrapperName: wrappersNames.keySet()){
                code = code.replace(wrapperName, wrappersNames.get(wrapperName));
            }
        }
        return code;
    }


    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }

}
