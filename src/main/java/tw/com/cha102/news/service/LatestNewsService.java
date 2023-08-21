package tw.com.cha102.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.news.model.AddNews;
import tw.com.cha102.news.model.LatestNews;
import tw.com.cha102.news.model.dao.LatestNewsRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LatestNewsService {

    private final LatestNewsRepository latestNewsRepository;//使用 LatestNewsRepository 來執行實際的資料庫操作。
    @Autowired
    public LatestNewsService(LatestNewsRepository latestNewsRepository) {
        this.latestNewsRepository = latestNewsRepository;
        //建構子，將一個 LatestNewsRepository 實例注入到 LatestNewsService 中，以便在 LatestNewsService 的方法中可以使用該實例來執行資料庫操作。
    }
    public List<LatestNews> getAllLatestNews() {
        return (List<LatestNews>) latestNewsRepository.findAll();
        //getAllLatestNews()方法通過調用 latestNewsRepository 的 findAll 方法，從資料庫中獲取所有最新消息的記錄並以 List<LatestNews> 的形式返回。這個方法可以在應用程式的其他部分被呼叫，以取得最新消息的列表。
    }
    public LatestNews addNews(AddNews addNews) {
        LatestNews latestNews = new LatestNews();
        latestNews.setImportance(addNews.getImportance());
        latestNews.setNewsTitle(addNews.getNewsTitle());
        latestNews.setNewsContent(addNews.getNewsContent());
        latestNews.setReleaseTime(new Date()); // 設定發佈時間為目前時間
        return latestNewsRepository.save(latestNews);
    }
    public void deleteNews(Integer newsId) {
        latestNewsRepository.deleteById(newsId);
    }

    public LatestNews updateNews(Integer newsId, AddNews updatedNews) {
        Optional<LatestNews> existingNewsOptional = latestNewsRepository.findById(newsId);
        if (existingNewsOptional.isPresent()) {
            LatestNews existingNews = existingNewsOptional.get();
            existingNews.setNewsTitle(updatedNews.getNewsTitle());
            existingNews.setImportance(updatedNews.getImportance());
            existingNews.setNewsContent(updatedNews.getNewsContent());
            // 更新發布時間
            existingNews.setReleaseTime(new Date());

            return latestNewsRepository.save(existingNews);
        } else {
            return null; // 返回 null 表示找不到要修改的消息
        }
    }

    public Optional<LatestNews> getLatestNewsById(Integer newsId) {
        return latestNewsRepository.findById(newsId);
    }
}



