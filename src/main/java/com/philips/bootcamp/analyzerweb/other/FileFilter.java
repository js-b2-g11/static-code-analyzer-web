package com.philips.bootcamp.analyzerweb.other;

import java.io.File;
import java.io.FilenameFilter;

public class FileFilter {
		
		   public void cleartxtFiles(String filedir, String ext){
				
		     GenericExtFilter filter = new GenericExtFilter(ext);
		     File dir = new File(filedir);
			

		     String[] list = dir.list(filter);
			     
		     if (list.length == 0) return;

		     File fileDelete;
			    
		     for (String file : list){
		   	String temp = new StringBuffer(filedir)
		                      .append(File.separator)
		                      .append(file).toString();
		    	fileDelete = new File(temp);
		    	boolean isdeleted = fileDelete.delete();
//		    	System.out.println("file : " + temp + " is deleted : " + isdeleted);
		     }
		   }
		  
		   
		   public class GenericExtFilter implements FilenameFilter {
			
		       private String ext;
			
		       public GenericExtFilter(String ext) {
		         this.ext = ext;             
		       }
			       
		       public boolean accept(File dir, String name) {
		         return (name.endsWith(ext));
		       }
		    }
}
