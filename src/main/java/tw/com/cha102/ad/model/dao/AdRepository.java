package tw.com.cha102.ad.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tw.com.cha102.ad.model.entity.AdVO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AdRepository extends JpaRepository<AdVO, Integer> {

    Optional<AdVO> findTopByOrderByAdIdDesc();

    @Query("SELECT ad.adId FROM AdVO ad WHERE ad.startTime <= :today AND ad.endTime >= :today")
    List<Integer> findAdIdsForToday(java.sql.Date today);

    @Query("SELECT ad.adId FROM AdVO ad WHERE :today BETWEEN ad.startTime AND ad.endTime")
    List<Integer> findAdIdsForToday(@Param("today") Date today);

    @Query("SELECT ad.productPic FROM AdVO ad WHERE ad.adId = :adId")
    byte[] getAdImage(@Param("adId") int adId);
}

