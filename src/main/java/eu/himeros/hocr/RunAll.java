/*
 * This file is part of eu.himeros_hocraggregator_jar_1.0-SNAPSHOT
 *
 * Copyright (C) 2012 federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package eu.himeros.hocr;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public class RunAll {

    HocrInfoAggregator hocrInfoAggregator;
    public static String configpath = "";

    public RunAll() {
    }

    public void run(File dir) throws Exception {
        hocrInfoAggregator = new HocrInfoAggregator();
        List<File> listFiles=Arrays.asList(dir.listFiles());
        Collections.sort(listFiles);
        for (File file : listFiles) {
            try {
                if (file.getName().endsWith(".1.html") && !file.getName().endsWith(".ngt.html")) {
                    System.out.println(file.getAbsolutePath());
                    hocrInfoAggregator.initFile(file.getAbsolutePath());
                    hocrInfoAggregator.parse();
                    try {
                    	hocrInfoAggregator.alignToGroundTruth();
                    } catch (Exception e) {
                    	// solving problems by ignoring them
                    }
                    //hocrInfoAggregator.output(file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 5) + "-out.html");
                    hocrInfoAggregator.output(file.getAbsolutePath().replaceAll("/o(....)\\.1\\.html", "/p$1\\.html"));
                }
            } catch (Exception ex) {
                System.err.println("ERROR: " + file.getName());
                ex.printStackTrace(System.err);
            }
        }
    }

    /**
     * 
     * @param args [0]: Path to assets, [1]: Path to .book/csv/xml
     * @throws Exception
     */
    
    public static void main(String[] args) throws Exception {
        
        configpath = args[0];
        if (!configpath.endsWith("/")) configpath = configpath + "/";

        if(System.getProperty("grc.lucene.spellchecker")==null)System.setProperty("grc.lucene.spellchecker",configpath+"lucene-grc/");
        
    	 try {

             File dir = new File(args[1]);
             if (!dir.isDirectory()) throw new Exception();
             File[] files = dir.listFiles();
             HashMap<String,String> filenames = new HashMap<>();
             for (File f : files) {
            	 String filename = f.getAbsolutePath();
            	 String[] extension = filename.split("\\.");
            	 filenames.put(extension[extension.length-1], filename);
             }
             
             try {
             FlatXml.main(new String[]{filenames.get("book")});
             XmlWordListExtractor.main(new String[]{filenames.get("xml"),filenames.get("xml").replaceAll("\\.xml", "\\.ngt\\.csv")});
             NgtMaker.main(new String[]{filenames.get("csv"),filenames.get("book")});
             } catch (Exception e) {
            	 // solving problems by ignoring them
             }
             RunAll ra = new RunAll();
             ra.run(new File(filenames.get("book")));

         } catch (Exception e) {
        	 System.err.println(e.toString());
         }
    	
    }
}
