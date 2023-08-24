package tw.com.cha102.groupreport.model;

import java.util.List;

public interface GroupReportDAO {
    void insert(GroupReportVO groupReportVO);

    void update(GroupReportVO groupReportVO);
    List<GroupReportVO> getAll();
}
