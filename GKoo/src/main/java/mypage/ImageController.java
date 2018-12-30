package mypage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

	private String image_folder_url = "C:\\Users\\moond_000\\Documents\\Dokgu\\item_";
	private String image_type = ".jpg";
	private String item = "C:\\Users\\moond_000\\Documents\\Dokgu\\item_124.jpg";
	
	public ImageController() {}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/getItemImage")
	public void getImage(HttpServletResponse response) throws IOException {
	    File file = new File(item);
	    if(file.exists()) {
	        String contentType = "application/octet-stream";
	        response.setContentType(contentType);
	        OutputStream out = response.getOutputStream();
	        FileInputStream in = new FileInputStream(file);
	        // copy from in to out
	        IOUtils.copy(in, out);
	        out.close();
	        in.close();
	    }else {
	        throw new FileNotFoundException();
	    }
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/getItemImage/{id}")
	public void getImageParam(HttpServletResponse response, @PathVariable("id") int id) throws IOException {
	    File file = new File(image_folder_url + id + image_type);
	    if(file.exists()) {
	        String contentType = "application/octet-stream";
	        response.setContentType(contentType);
	        OutputStream out = response.getOutputStream();
	        FileInputStream in = new FileInputStream(file);
	        // copy from in to out
	        IOUtils.copy(in, out);
	        out.close();
	        in.close();
	    }else {
	        throw new FileNotFoundException();
	    }
	}
}
