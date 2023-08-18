package tw.com.cha102.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.com.cha102.news.model.LatestNews;
import tw.com.cha102.news.service.LatestNewsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/news")
public class LatestNewsController {
    private final LatestNewsService latestNewsService;
    @Autowired
    public LatestNewsController(LatestNewsService latestNewsService) {
        this.latestNewsService = latestNewsService;
    }
    @PostMapping("/create")
    public LatestNews createNews(@RequestBody LatestNews news) {
        return latestNewsService.saveLatestNews(news);
    }
    @GetMapping("/list")
    public List<LatestNews> listNews() {
        return latestNewsService.getAllLatestNews();
    }
    @GetMapping("/get/{newsId}")
    public Optional<LatestNews> getNewsById(@PathVariable Integer newsId) {
        return latestNewsService.getLatestNewsById(newsId);
    }
    @PutMapping("/edit/{newsId}")
    public LatestNews editNews(@PathVariable Integer newsId, @RequestBody LatestNews news) {
        news.setNewsId(newsId);
        return latestNewsService.saveLatestNews(news);
    }
    @DeleteMapping("/delete/{newsId}")
    public void deleteNews(@PathVariable Integer newsId) {
        latestNewsService.deleteLatestNews(newsId);
    }
}
