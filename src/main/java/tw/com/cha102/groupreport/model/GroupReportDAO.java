package tw.com.cha102.groupreport.model;

import java.util.List;
import java.util.Map;

public interface GroupReportDAO {
    void insert(GroupReportVO groupReportVO);

    void update(GroupReportVO groupReportVO);

    GroupReportVO findById(Integer groupReportId);

    GroupReportJoin findMoreById(Integer groupReportId);

    List<GroupReportJoin> getAll();

    //複合查詢，視情況補上
    List<GroupReportVO> getAll(Map<String, String[]> map);
}
