package tw.com.cha102.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.news.model.AddNews;
import tw.com.cha102.news.model.LatestNews;
import tw.com.cha102.news.service.LatestNewsService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/backend/news")
public class LatestNewsController {
    private final LatestNewsService latestNewsService;
    @Autowired
    public LatestNewsController(LatestNewsService latestNewsService) {
        this.latestNewsService = latestNewsService;
    }
    @GetMapping("/list")//瀏覽最新消息列表
    public ResponseEntity<List<LatestNews>> listNews() {
        List<LatestNews> latestNewsList = latestNewsService.getAllLatestNews();
        return new ResponseEntity<>(latestNewsList, HttpStatus.OK);
    }

    @GetMapping("/{newsId}") // 查詢單筆消息的詳細資訊
    public ResponseEntity<LatestNews> getNewsById(@PathVariable Integer newsId) {
        Optional<LatestNews> news = latestNewsService.getLatestNewsById(newsId);
        if (news.isPresent()) {
            return new ResponseEntity<>(news.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")//新增最新消息
    public ResponseEntity<LatestNews> addNews(@Valid @RequestBody AddNews addNews) {
        LatestNews newNews = latestNewsService.addNews(addNews);
        return new ResponseEntity<>(newNews, HttpStatus.CREATED);
    }
    @DeleteMapping("/{newsId}")//刪除最新消息
    public ResponseEntity<String> deleteNews(@PathVariable Integer newsId) {
        try {
            latestNewsService.deleteNews(newsId);
            return new ResponseEntity<>("消息已成功刪除", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("刪除消息失敗", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{newsId}") // 修改最新消息
    public ResponseEntity<LatestNews> updateNews(
            @PathVariable Integer newsId,
            @Valid @RequestBody AddNews updatedNews) {
        try {
            LatestNews updated = latestNewsService.updateNews(newsId, updatedNews);
            if (updated != null) {
                return new ResponseEntity<>(updated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



