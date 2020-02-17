/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps.extended.concept.directory;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 * DirectoryUtil class for handeling all files that were retrieved drom the
 * MITRE databases CVE, CPE and CAPEC
 *
 * @author tarnschaf
 */
public class DirectoryUtil {

    public static File theDir;

    public static void createProjectFolder() {
        theDir = new File("CpsExtendedProject");

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory: " + theDir.getName());
            boolean result = false;

            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                //handle it
            }
            if (result) {
                System.out.println("Directory created");
            }
        }
    }

    public static File getProjectFolder() {
        return DirectoryUtil.theDir;
    }

    /**
     * Destroys the directory for temp files
     */
    public static void destroyDirectory() {
        try {
            FileUtils.deleteDirectory(getProjectFolder());
        } catch (IOException ex) {
            Logger.getLogger(DirectoryUtil.class.getName()).log(Level.WARNING, "Error deleting directory folder", ex);
        }
    }
}
