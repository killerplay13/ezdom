package tw.com.cha102.ad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.ad.model.dao.AdRepository;
import tw.com.cha102.ad.model.entity.AdVO;

import java.util.Date;
import java.util.List;

@Service
public class AdService1 {

    private final AdRepository adRepository;

    @Autowired
    public AdService1(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    public void createAd(AdVO adVO) {
        adRepository.save(adVO);
    }

    public AdVO getAdById(int adId) {
        return adRepository.findById(adId).orElse(null);
    }

    public List<AdVO> getAllAds() {
        return adRepository.findAll();
    }

    public void updateAd(AdVO adVO, byte[] bytes) {
        adRepository.save(adVO);
    }

    public void deleteAd(int adId) {
        adRepository.deleteById(adId);
    }

    public boolean shouldDisplayAd(int adId) {
        AdVO ad = adRepository.findById(adId).orElse(null);
        if (ad != null) {
            Date currentTime = new Date();
            return currentTime.after(ad.getStartTime()) && currentTime.before(ad.getEndTime());
        }
        return false;
    }

    public void updateAd(int adId, AdVO adVO) {
        AdVO existingAd = adRepository.findById(adId).orElseThrow(() -> new RuntimeException("Ad not found"));

        existingAd.setAdId(adVO.getAdId());
        existingAd.setStartTime(adVO.getStartTime());
        existingAd.setEndTime(adVO.getEndTime());

        adRepository.save(existingAd);
    }
}

