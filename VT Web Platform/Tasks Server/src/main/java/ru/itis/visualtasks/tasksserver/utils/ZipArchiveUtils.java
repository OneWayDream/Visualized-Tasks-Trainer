package ru.itis.visualtasks.tasksserver.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipArchiveUtils {

    public static Map<String, byte[]> unzipArchiveContent(byte[] compressedData) throws IOException{
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressedData);
             ZipInputStream zipInputStream = new ZipInputStream(byteArrayInputStream)) {
            Map<String, byte[]> result = new HashMap<>();
            byte[] buffer = new byte[1024];
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                String filename = zipEntry.getName();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int length;
                while ((length = zipInputStream.read(buffer)) > 0) {
                    byteArrayOutputStream.write(buffer, 0, length);
                }
                byte[] uncompressedData = byteArrayOutputStream.toByteArray();
                result.put(filename, uncompressedData);
            }
            return result;
        }
    }

}
