package motherj.noticeModule;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {
	
	public NoticeController() {}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/notice")
	public List<Notice> requestNoticeList() {		
		NoticeDAO noticeDao = new NoticeDAO();//spring implement
		return noticeDao.getNoticeList();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/notice/{number}")
	@ResponseBody
	public Notice requestNoticeContent(@PathVariable String number) {		
		NoticeDAO noticeDao = new NoticeDAO();//spring implement
		return noticeDao.getNoticeContent(number);
	}
}
