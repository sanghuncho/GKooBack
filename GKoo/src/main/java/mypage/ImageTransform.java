package mypage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class ImageTransform {
	
	private static String image_folder_url = "C:\\Users\\moond_000\\Documents\\Dokgu\\item_";
	private static String image_type = ".jpg";
		
		
	public ImageTransform() {}
	
	public static String getImageBase64(String url) {
		String imagePath = image_folder_url + url + image_type;
		String imageBase64 = encoder(imagePath);
		
		if(imageBase64 != null){
			return imageBase64;
		}
		return null;
	}
	
	public static String encoder(String imagePath) {
	    File file = new File(imagePath);
	    try (FileInputStream imageInFile = new FileInputStream(file)) {
	        // Reading a Image file from file system
	    	String base64Image = "";
	        byte imageData[] = new byte[(int) file.length()];
	        imageInFile.read(imageData);
	        base64Image = Base64.getEncoder().encodeToString(imageData);
	        return base64Image;
	    } catch (FileNotFoundException e) {
	        System.out.println("Image not found" + e);
	    } catch (IOException ioe) {
	        System.out.println("Exception while reading the Image " + ioe);
	    }
	    return null;
	}
}
