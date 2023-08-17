package tw.com.cha102.news.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@Table(name = "latest_news")
public class LatestNews {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NEWS_ID")
	private Integer newsId;

	@Column(name = "EMPLOYEE_ID")
	private Integer employeeId;

	@Column(name = "IMPORTANCE")
	private Integer importance;

	@Column(name = "NEWS_TITLE")
	private String newsTitle;

	@Column(name = "NEWS_CONTENT")
	private String newsContent;

	@Column(name = "RELEASE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date releaseTime;

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public void setImportance(Integer importance) {
		this.importance = importance;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
}
