package com.novamaday.novalib.api.file;

import java.io.*;

@SuppressWarnings({"ResultOfMethodCallIgnored", "unused", "ConstantConditions", "WeakerAccess", "UnusedReturnValue"})
public class FileUtils {

    /**
     * Copies the specified folder and all of its contents to the specified destination
     *
     * @param src  The folder to copy
     * @param dest The folder to place the copy into.
     * @throws IOException if files are corrupted or don't exist.
     */
    public static void copyFolder(File src, File dest) throws IOException {
        if (src.isDirectory()) {
            //if directory not exists, create it
            if (!dest.exists()) {
                dest.mkdir();
            }
            //list all the directory contents
            String files[] = src.list();
            for (String file : files) {
                //construct the src and dest file structure
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                //recursive copy
                copyFolder(srcFile, destFile);
            }
        } else {
            //if file, then copy it
            //Use bytes stream to support all file types
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
        }
    }

    /**
     * Deletes the specified file. If a folder, deletes all contents.
     * @param pFile The file or folder to delete.
     * @return Whether or not the deletion was successful.
     */
    public static boolean deleteFile(File pFile) {
        if (pFile.exists()) {
            if (pFile.isDirectory()) {
                if (pFile.list().length == 0) {
                    pFile.delete();
                } else {
                    String[] strFiles = pFile.list();
                    for (String strFilename : strFiles) {
                        File fileToDelete = new File(pFile, strFilename);
                        deleteFile(fileToDelete);
                    }
                    pFile.delete();
                }
            } else {
                pFile.delete();
            }
        }
        return false;
    }
}