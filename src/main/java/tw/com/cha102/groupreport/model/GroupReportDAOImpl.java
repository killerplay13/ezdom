package tw.com.cha102.groupreport.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupReportDAOImpl implements GroupReportDAO{
    static {
        try {
            Class.forName(Util.DRIVER);
        } catch( ClassNotFoundException ce) {
            ce.printStackTrace();
        }
    }

    @Override
    public void insert(GroupReportVO groupReportVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DriverManager.getConnection(Util.URL, Util.USER,Util.PASSWORD);
            pstmt = con.prepareStatement("INSERT INTO GROUP_REPORT VALUES(?, ?, ?, ?, ?, ?)");

            pstmt.setInt(1,groupReportVO.getGroupId());
            pstmt.setInt(2,groupReportVO.getGroupReportId());
            pstmt.setInt(3,groupReportVO.getReportedMemberId());
            pstmt.setInt(4,groupReportVO.getGroupId());
            pstmt.setInt(5,groupReportVO.getEmployeeId());
            pstmt.setInt(6,groupReportVO.getGroupReportStatus());
            pstmt.executeUpdate();
        }catch(SQLException se) {
            se.printStackTrace();
        }finally {
            Util.closeResources(con,pstmt,null);
        }
    }

    @Override
    public void update(GroupReportVO groupReportVO) {

    }

    @Override
    public List<GroupReportVO> getAll() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<GroupReportVO> groupReportList = new ArrayList<GroupReportVO>();
        GroupReportVO groupReport = null;
        try {
            con = DriverManager.getConnection(Util.URL,Util.USER ,Util.PASSWORD);
            pstmt = con.prepareStatement("SELECT * FROM GROUP_REPORT ORDER BY GROUP_REPORT_ID");
            rs = pstmt.executeQuery();

            while(rs.next()) {
                groupReport = new GroupReportVO();
                groupReport.setGroupReportId(rs.getInt("GROUP_REPORT_ID"));
                groupReport.setReportMemberId(rs.getInt("REPORT_MEMBER_ID"));
                groupReport.setReportedMemberId(rs.getInt("REPORTED_MEMBER_ID"));
                groupReport.setGroupId(rs.getInt("GROUP_ID"));
                groupReport.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
                groupReport.setGroupReportStatus(rs.getInt("GROUP_REPORT_STATUS"));
                groupReport.setRejectReason(rs.getString("REJECT_REASON"));
                groupReportList.add(groupReport);
            }
        }catch(SQLException se) {
            se.printStackTrace();
        }finally {
            Util.closeResources(con, pstmt, rs);
        }

        return groupReportList;
    }
}
