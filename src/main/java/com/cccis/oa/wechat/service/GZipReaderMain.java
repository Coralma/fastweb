package com.cccis.oa.wechat.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * Created by CCC on 2017/3/15.
 */
public class GZipReaderMain {

    private static void doUncompressFile(String inFileName) {
        try {
            System.out.println("Opening the compressed file.");
            GZIPInputStream gin = null;
            try {
                String gzipFile = GZipReaderMain.class.getClassLoader().getResource(inFileName).getFile();
                gin = new GZIPInputStream(new FileInputStream(gzipFile));
            } catch(FileNotFoundException e) {
                System.err.println("File not found. " + inFileName);
                System.exit(1);
            }

            System.out.println("Open the output file.");
            /*// to String
            InputStreamReader reader = new InputStreamReader(gin);
            BufferedReader in = new BufferedReader(reader);

            String json = "", readed;
            while ((readed = in.readLine()) != null) {
                json += readed;
            }*/

            // to file
            String outFileName = getFileName(inFileName);
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(outFileName);
            } catch (FileNotFoundException e) {
                System.err.println("Could not write to file. " + outFileName);
                System.exit(1);
            }

            System.out.println("Transfering bytes from compressed file to the output file.");
            byte[] buf = new byte[1024];
            int len;
            while((len = gin.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            System.out.println("Closing the file and stream");
            gin.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Used to extract and return the extension of a given file.
     * @param f Incoming file to get the extension of
     * @return <code>String</code> representing the extension of the incoming
     *         file.
     */
    public static String getExtension(String f) {
        String ext = "";
        int i = f.lastIndexOf('.');

        if (i > 0 &&  i < f.length() - 1) {
            ext = f.substring(i+1);
        }
        return ext;
    }

    /**
     * Used to extract the filename without its extension.
     * @param f Incoming file to get the filename
     * @return <code>String</code> representing the filename without its
     *         extension.
     */
    public static String getFileName(String f) {
        String fname = "";
        int i = f.lastIndexOf('.');

        if (i > 0 &&  i < f.length() - 1) {
            fname = f.substring(0,i);
        }
        return fname;
    }

    /**
     * Sole entry point to the class and application.
     * @param args Array of String arguments.
     */
    public static void main(String[] args) {
        doUncompressFile("Z020010311.json");
        /*doUncompressFile("Z020010324.json");*/
    }
}
