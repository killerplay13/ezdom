package tw.com.cha102.ad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tw.com.cha102.ad.model.dao.AdRepository;
import tw.com.cha102.ad.model.entity.AdVO;
import tw.com.cha102.ad.service.AdService1;
import tw.com.cha102.news.model.LatestNews;

import java.io.IOException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ads")
public class AdController {


    private final AdService1 adService;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    public AdController(AdService1 adService) {
        this.adService = adService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdVO>> listAds() {
        List<AdVO> adsList = adService.getAllAds();
        return new ResponseEntity<>(adsList, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> createAd(@RequestBody AdVO adVO) {
        adService.createAd(adVO);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Ad created successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{adId}")
    public ResponseEntity<String> updateAd(@PathVariable int adId, @RequestBody AdVO adVO) {
        adService.updateAd(adId, adVO);
        return new ResponseEntity<>("Ad updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{adId}")
    public ResponseEntity<Void> deleteAd(@PathVariable int adId) {
        adService.deleteAd(adId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/getMaxAdId")
    public ResponseEntity<Integer> getMaxAdId() {
        int maxAdId = adService.getMaxAdId(); // 使用 AdService 来获取最大的 ADID 值
        return new ResponseEntity<>(maxAdId, HttpStatus.OK);
    }

    @GetMapping("/images/{adId}")
    public ResponseEntity<String> getAdImageBase64(@PathVariable int adId) {
        // 根据广告ID从数据库中获取广告图片数据并将其转换为Base64格式
        String base64Image = adService.getAdImageBase64ById(adId);

        // 返回Base64格式的图片数据
        return ResponseEntity.ok(base64Image);
    }

    @GetMapping("/get/{adId}")
    public ResponseEntity<AdVO> getAdById(@PathVariable int adId) {
        AdVO ad = adService.getAdById(adId);
        if (ad != null) {
            return ResponseEntity.ok(ad);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getTodaysAds")
    public List<Integer> getTodaysAds() {
        Date today = new Date();
        // 查询在今天日期范围内的广告ID
        List<Integer> adIds = adRepository.findAdIdsForToday(today);
        return adIds;
    }


    @GetMapping("/getAdImage/{adId}")
    public ResponseEntity<byte[]> getAdImage(@PathVariable int adId) {
        // 根据adId从数据库中检索广告图像数据
        byte[] imageBytes = adRepository.getAdImage(adId);

        // 创建HTTP响应实体并设置图像数据
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // 设置图像类型
        headers.setContentLength(imageBytes.length); // 设置图像数据长度
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

}


