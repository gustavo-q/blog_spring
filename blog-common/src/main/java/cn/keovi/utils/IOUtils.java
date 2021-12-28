package cn.keovi.utils;

import java.io.*;

public class IOUtils {
	
    private IOUtils(){		
    }


    public static void closeQuietly(Reader input) {
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }
    public static String toString(InputStream input) throws IOException {
        return toString(input, null);
    }

    public static void closeQuietly(Writer output) {
        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }


    public static void closeQuietly(InputStream input) {
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }


    public static void closeQuietly(OutputStream output) {
        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }


	public static String toString(InputStream in, String encoding){
		BufferedReader reader = null;
		StringBuilder sb = null; 
		try{
			reader = new BufferedReader(new InputStreamReader(in,encoding));
			sb = new StringBuilder();
			int c;
			c = reader.read();			
			while(c!=-1){
				sb.append((char)c);
				c = reader.read();				
			}	
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			IOUtils.closeQuietly(reader);
		}
		return sb.toString();
	}
}
