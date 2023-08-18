package tw.com.cha102.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.news.model.LatestNews;
import tw.com.cha102.news.model.dao.LatestNewsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LatestNewsService {
    private final LatestNewsRepository latestNewsRepository;
    @Autowired
    public LatestNewsService(LatestNewsRepository latestNewsRepository) {
        this.latestNewsRepository = latestNewsRepository;
    }
    public LatestNews saveLatestNews(LatestNews latestNews) {
        return latestNewsRepository.save(latestNews);
    }
    public void deleteLatestNews(Integer newsId) {
        latestNewsRepository.deleteById(newsId);
    }
    public List<LatestNews> getAllLatestNews() {
        return (List<LatestNews>) latestNewsRepository.findAll();
    }
    public Optional<LatestNews> getLatestNewsById(Integer newsId) {
        return latestNewsRepository.findById(newsId);
    }
}

