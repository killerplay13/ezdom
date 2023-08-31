package tw.com.cha102.ad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tw.com.cha102.ad.model.entity.AdVO;
import tw.com.cha102.ad.service.AdService;
import tw.com.cha102.ad.service.AdService1;
import tw.com.cha102.news.model.LatestNews;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ads")
public class AdController {

    private final AdService1 adService;

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
    public ResponseEntity<String> createAd(@RequestBody AdVO adVO) {
        adService.createAd(adVO); // 執行創建廣告的邏輯
        return new ResponseEntity<>("Ad created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/edit/{adId}")
    public ResponseEntity<String> updateAd(@PathVariable int adId, @RequestBody AdVO adVO) {
        adService.updateAd(adId, adVO); // 執行更新廣告的邏輯
        return new ResponseEntity<>("Ad updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{adId}")
    public ResponseEntity<String> deleteAd(@PathVariable int adId) {
        adService.deleteAd(adId); // 執行刪除廣告的邏輯
        return new ResponseEntity<>("Ad deleted successfully", HttpStatus.OK);
    }
}


