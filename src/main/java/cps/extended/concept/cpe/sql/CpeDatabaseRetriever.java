/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.cpe.sql;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.zip.GZIPInputStream;

/**
 * Retrieves CPE data from NVD
 *
 * @author tarnschaf
 */
public class CpeDatabaseRetriever {

    private static final String cpeWebsite = "https://nvd.nist.gov/feeds/xml/cpe/dictionary/official-cpe-dictionary_v2.3.xml.gz";

    private File outputDir;
    private boolean downloadFailed = false;
    private boolean json = true;
    private boolean xml = true;

    public CpeDatabaseRetriever(String outputDirPath, String type) {
        outputDir = new File(outputDirPath);
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
        if (type != null) {
            if (type.equals("xml")) {
                json = false;
            } else if (type.equals("json")) {
                xml = false;
            } else {
                throw new IllegalArgumentException(String.format("Invalid type parameter '%s'. Usage: java CpeDatabaseRetriever outputDir [xml|json]", type));
            }
        }
    }

    public void mirror() {
        Date currentDate = new Date();
        System.out.println("Downloading files at " + currentDate);

        doDownload(cpeWebsite);

    }

    private long checkHead(String cveUrl) {
        try {
            URL url = new URL(cveUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            connection.getInputStream();
            return connection.getContentLengthLong();
        } catch (IOException e) {
            System.out.println("Failed to determine content length");
        }
        return 0;
    }

    private void doDownload(String cveUrl) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File file = null;
        boolean success = false;
        try {
            URL url = new URL(cveUrl);
            String filename = url.getFile();
            filename = filename.substring(filename.lastIndexOf('/') + 1);
            file = new File(outputDir, filename).getAbsoluteFile();

            if (file.exists()) {
                long fileSize = checkHead(cveUrl);
                if (file.length() == fileSize) {
                    System.out.println("Using cached version of " + filename);
                    return;
                }
            }

            URLConnection connection = url.openConnection();
            System.out.println("Downloading " + url.toExternalForm());
            bis = new BufferedInputStream(connection.getInputStream());
            file = new File(outputDir, filename);
            bos = new BufferedOutputStream(new FileOutputStream(file));

            int i;
            while ((i = bis.read()) != -1) {
                bos.write(i);
            }
            success = true;
        } catch (IOException e) {
            System.out.println("Download failed : " + e.getLocalizedMessage());
            downloadFailed = true;
        } finally {
            close(bis);
            close(bos);
        }
        if (file != null && success) {
            uncompress(file);
        }
    }

    private void uncompress(File file) {
        byte[] buffer = new byte[1024];
        GZIPInputStream gzis = null;
        FileOutputStream out = null;
        try {
            System.out.println("Uncompressing " + file.getName());
            gzis = new GZIPInputStream(new FileInputStream(file));
            out = new FileOutputStream(new File(file.getAbsolutePath().replaceAll(".gz", "")));
            int len;
            while ((len = gzis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            close(gzis);
            close(out);
        }
    }

    private void close(Closeable object) {
        if (object != null) {
            try {
                object.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
