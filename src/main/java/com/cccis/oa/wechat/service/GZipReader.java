package com.cccis.oa.wechat.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.GZIPInputStream;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.cccis.base.decrypt.CDS;
import com.cccis.oa.wechat.model.PartModel;

/**
 * Created by CCC on 2017/3/15.
 */
public class GZipReader {

    private static List<PartModel> partModels;

    public static List<PartModel> getParts() {
        List<PartModel> partModels = Lists.newArrayList();
        Gson gson = new Gson();
        String json = doUncompressFile("Z020010311.json");
        List<PartModel> models = gson.fromJson(json, new TypeToken<List<PartModel>>(){}.getType());
        partModels.addAll(models);

        json = doUncompressFile("Z020010314.json");
        models = gson.fromJson(json, new TypeToken<List<PartModel>>() {
        }.getType());
        partModels.addAll(models);

        json = doUncompressFile("Z020010324.json");
        models = gson.fromJson(json, new TypeToken<List<PartModel>>(){}.getType());
        partModels.addAll(models);

        return partModels;
    }

    private static String doUncompressFile(String inFileName) {
        String json = "";
        try {
            System.out.println("Opening the compressed file.");
            GZIPInputStream gin = null;
            try {
                String gzipFile = GZipReader.class.getClassLoader().getResource("/").getPath() + "/" + inFileName;
                gin = new GZIPInputStream(new FileInputStream(gzipFile));
            } catch(FileNotFoundException e) {
                System.err.println("File not found. " + inFileName);
                System.exit(1);
            }

            System.out.println("Open the output file.");
            // to String
            InputStreamReader reader = new InputStreamReader(gin, "utf-8");
            BufferedReader in = new BufferedReader(reader);

            String readed;
            while ((readed = in.readLine()) != null) {
                json += readed;
            }
/*
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
            out.close();*/

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return json;
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
        String key = "29";
        Integer number = 1;
        List<PartModel> partModels = getParts();
        for(int i=0; i < number;i++) {
            PartModel partModel = partModels.get(i);
            System.out.println(partModel);
            String laborType = CDS.decrypt(partModel.getLaborType(), key);
            String texture = CDS.decrypt(partModel.getTexture(), key);
            String repairPartFlag = CDS.decrypt(partModel.getRepairPartFlag(), key);
            String paintFlag = CDS.decrypt(partModel.getPaintFlag(), key);
            String lossDegree = CDS.decrypt(partModel.getLossDegree(), key);
            String easyAbradedFlag = CDS.decrypt(partModel.getEasyAbradedFlag(), key);
            String assemblyPartFlag = CDS.decrypt(partModel.getAssemblyPartFlag(), key);
        }
        /*doUncompressFile("Z020010324.json");*/
    }
}
