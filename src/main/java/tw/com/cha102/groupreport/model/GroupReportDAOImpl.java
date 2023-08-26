package tw.com.cha102.groupreport.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupReportDAOImpl implements GroupReportDAO{
    //要問老師一下我把util傳上git會怎麼樣嗎?
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
            pstmt = con.prepareStatement("INSERT INTO GROUP_REPORT VALUES(?, ?, ?, ?, ?, ?, ?, ?)");

            pstmt.setInt(1,groupReportVO.getGroupId());
            pstmt.setInt(2,groupReportVO.getGroupReportId());
            pstmt.setInt(3,groupReportVO.getReportedMemberId());
            pstmt.setInt(4,groupReportVO.getGroupId());
            pstmt.setString(5,groupReportVO.getReportReason());
            pstmt.setInt(6,groupReportVO.getEmployeeId());
            pstmt.setInt(7,groupReportVO.getGroupReportStatus());
            pstmt.setString(8,groupReportVO.getRejectReason());
            pstmt.executeUpdate();
        }catch(SQLException se) {
            se.printStackTrace();
        }finally {
            Util.closeResources(con,pstmt,null);
        }
    }

    @Override
    public void update(GroupReportVO groupReportVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DriverManager.getConnection(Util.URL, Util.USER,Util.PASSWORD);
            pstmt =con.prepareStatement("UPDATE GROUP_REPORT SET REPORT_MEMBER_ID=?,REPORTED_MEMBER_ID=?,GROUP_ID=?,REPORT_REASON=?,EMPLOYEE_ID=?,GROUP_REPORT_STATUS=?,REJECT_REASON=? WHERE GROUP_REPORT_ID=?");

            pstmt.setInt(1,groupReportVO.getGroupId());
            pstmt.setInt(2,groupReportVO.getGroupReportId());
            pstmt.setInt(3,groupReportVO.getReportedMemberId());
            pstmt.setInt(4,groupReportVO.getGroupId());
            pstmt.setString(5,groupReportVO.getReportReason());
            pstmt.setInt(6,groupReportVO.getEmployeeId());
            pstmt.setInt(7,groupReportVO.getGroupReportStatus());
            pstmt.setString(8,groupReportVO.getRejectReason());
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            Util.closeResources(con,pstmt,null);
        }

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
                groupReport.setReportReason(rs.getString("REPORT_REASON"));
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

//複合查詢，視情況補上
    @Override
    public List<GroupReportVO> getAll(Map<String, String[]> map) {
        List<GroupReportVO> list = new ArrayList<GroupReportVO>();
        GroupReportVO groupReport = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(Util.URL,Util.USER ,Util.PASSWORD);
            String finalSQL = "SELECT * FROM GROUP_REPORT"+Util.getWhereCondition(map)+"ORDER BY GROUP_REPORT_ID";
            pstmt = con.prepareStatement(finalSQL);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                groupReport = new GroupReportVO();
                groupReport.setGroupReportId(rs.getInt("GROUP_REPORT_ID"));
                groupReport.setReportMemberId(rs.getInt("REPORT_MEMBER_ID"));
                groupReport.setReportedMemberId(rs.getInt("REPORTED_MEMBER_ID"));
                groupReport.setGroupId(rs.getInt("GROUP_ID"));
                groupReport.setReportReason(rs.getString("REPORT_REASON"));
                groupReport.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
                groupReport.setGroupReportStatus(rs.getInt("GROUP_REPORT_STATUS"));
                groupReport.setRejectReason(rs.getString("REJECT_REASON"));
                list.add(groupReport);
            }

        } catch(SQLException se) {
            se.printStackTrace();
        }finally {
            Util.closeResources(con, pstmt, rs);
        }
        return null;
    }
}
