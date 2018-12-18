/**
 *  LICENSE
 *
 *  This file is part of the GLPI API Client Library for Java,
 *  a subproject of GLPI. GLPI is a free IT Asset Management.
 *
 *  GLPI is free software: you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 3
 *  of the License, or (at your option) any later version.
 *
 *  GLPI is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  --------------------------------------------------------------------------------
 *  @copyright Copyright © 2018 Teclib. All rights reserved.
 *  @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
 *  @link      https://github.com/glpi-project/java-library-glpi
 *  @link      http://www.glpi-project.org/
 *  @link      https://glpi-project.github.io/java-library-glpi/
 *  @link      https://bintray.com/glpi-project/teclib-repository/java-library-glpi
 *  --------------------------------------------------------------------------------
 */

package org.glpi.glpiproject;

import android.os.Environment;
import com.orhanobut.logger.Logger;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This is a Log wrapper
 */
public class FlyveLog {

    private static final String FILE_NAME_FEEDBACK = "FlyveMDMFeedback.txt";
    public static final String FILE_NAME_LOG = "FlyveMDMLog.txt";

    /**
     * private constructor to prevent instances of this class
     */
    private FlyveLog() {
    }

    /**
     * Send a DEBUG log message
     * @param object Object to write
     */
    public static void d(Object object) {
            Logger.d(object);
    }

    /**
     * Send a DEBUG log message
     * @param message String message to log
     * @param args Objects
     */
    public static void d(String message, Object... args) {
            // do something for a debug build
            Logger.d(message,args);

    }

    /**
     * Send a VERBOSE log message
     * @param message String message
     * @param args Objects
     */
    public static void v(String message, Object... args) {
            Logger.v(message, args);
    }

    /**
     * Send INFORMATION log message
     * @param message String message
     * @param args Objects
     */
    public static void i(String message, Object... args) {
            Logger.i(message, args);
    }

    /**
     * Send ERROR log message
     * @param throwable Throwable error
     * @param message String message
     * @param args Objects
     */
    public static void e(Throwable throwable, String message, Object... args) {
            Logger.e(throwable, message, args);
    }

    /**
     * Send Error log message
     * @param message String message
     * @param args Objects
     */
    public static void e(String message, Object... args) {
            Logger.e(message, args);
    }

    /**
     * send What a Terrible Failure log message
     * @param message String message
     * @param args Objects
     */
    public static void wtf(String message, Object... args) {
            Logger.wtf(message, args);
    }

    /**
     * Send a JSON log message
     * @param json String the json to show
     */
    public static void json(String json) {
            Logger.json(json);
    }

    /**
     * Send a XML log message
     * @param xml String the xml to show
     */
    public static void xml(String xml) {
            Logger.xml(xml);
    }

    /**
     * Logs the message in a directory
     * @param string the message
     * @param string the filename
     */
    public static void f(String message, String filename) {
        String state = Environment.getExternalStorageState();

        File dir = new File("/sdcard/FlyveMDM");
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            if(!dir.exists()) {
                FlyveLog.d("Dir created ", "Dir created ");
                dir.mkdirs();
            }

            File logFile = new File("/sdcard/FlyveMDM/" + filename);

            if (!logFile.exists())  {
                try  {
                    FlyveLog.d("File created ", "File created ");
                    logFile.createNewFile();
                } catch (IOException ex) {
                    FlyveLog.e(ex.getMessage());
                }
            }

            FileWriter fw = null;
            try {
                //BufferedWriter for performance, true to set append to file flag
                fw = new FileWriter(logFile, true);
                BufferedWriter buf = new BufferedWriter(fw);

                buf.write(message);
                buf.newLine();
                buf.flush();
                buf.close();
                fw.close();
            }
            catch (IOException ex) {
                e(ex.getMessage());
            }
            finally {
                if(fw!=null) {
                    try {
                        fw.close();
                    } catch(Exception ex) {
                        FlyveLog.e(ex.getMessage());
                    }
                }
            }
        }
    }

    /**
     * Clear the log
     * @param string the file name
     * @throws Exception an error message
     */
    public static void clearLog(String filename) {
        String state = Environment.getExternalStorageState();

        File dir = new File("/sdcard/FlyveMDM");
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            if (!dir.exists()) {
                FlyveLog.d("Dir created ", "Dir created ");
                dir.mkdirs();
            }

            File logFile = new File("/sdcard/FlyveMDM/" + filename);

            FileWriter fw = null;
            try {
                //BufferedWriter for performance, true to set append to file flag
                fw = new FileWriter(logFile, false);
                PrintWriter pwOb = new PrintWriter(fw, false);
                pwOb.flush();
                pwOb.close();
            }
            catch (IOException ex) {
                e(ex.getMessage());
            }
            finally {
                if(fw!=null) {
                    try {
                        fw.close();
                    } catch(Exception ex) {
                        FlyveLog.e(ex.getMessage());
                    }
                }
            }

        }
    }

}
