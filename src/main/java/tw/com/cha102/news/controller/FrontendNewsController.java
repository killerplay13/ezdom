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
@RequestMapping("/news")
public class FrontendNewsController {
    private final LatestNewsService latestNewsService;
    @Autowired
    public FrontendNewsController(LatestNewsService latestNewsService) {
        this.latestNewsService = latestNewsService;
    }
    @GetMapping("/list")//瀏覽最新消息列表
    public ResponseEntity<List<LatestNews>> listNews() {
        List<LatestNews> latestNewsList = latestNewsService.getAllLatestNews();
        return new ResponseEntity<>(latestNewsList, HttpStatus.OK);
    }
}



