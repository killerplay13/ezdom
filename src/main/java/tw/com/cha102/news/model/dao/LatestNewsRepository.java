package tw.com.cha102.news.model.dao;
//操作資料庫中的 latest_news 表

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tw.com.cha102.news.model.LatestNews;
//JpaRepository已經有提供很多操作資料庫的方法
//只有特殊的查詢需求或操作，而 Spring Data JPA 沒有提供相應的方法時，
// 您才需要在Repository 中自定義方法。如果需要進行較為複雜的查詢，可以使用 @Query 注解來定義自定義的 SQL 查詢語句。
@Repository
public interface LatestNewsRepository extends CrudRepository<LatestNews, Integer> {

}
