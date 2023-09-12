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
    public void insert(GroupReportCreate groupReportCreate) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DriverManager.getConnection(Util.URL, Util.USER,Util.PASSWORD);
            pstmt = con.prepareStatement("INSERT INTO GROUP_REPORT (REPORT_MEMBER_ID, REPORTED_MEMBER_ID, GROUP_ID, REPORT_REASON, GROUP_REPORT_STATUS)\n" +
                    "VALUES (?, ?, ?, ?, ?)\n");
            pstmt.setInt(1,groupReportCreate.getReportMemberId());
            pstmt.setInt(2,groupReportCreate.getReportedMemberId());
            pstmt.setInt(3,groupReportCreate.getGroupId());
            pstmt.setString(4,groupReportCreate.getReportReason());
            pstmt.setInt(5,groupReportCreate.getGroupReportStatus());
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
                con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
                pstmt = con.prepareStatement("UPDATE GROUP_REPORT SET REPORT_MEMBER_ID=?,REPORTED_MEMBER_ID=?,GROUP_ID=?,REPORT_REASON=?,EMPLOYEE_ID=?,GROUP_REPORT_STATUS=?,REJECT_REASON=? WHERE GROUP_REPORT_ID=?");
                pstmt.setInt(1, groupReportVO.getReportMemberId());
                pstmt.setInt(2, groupReportVO.getReportedMemberId());
                pstmt.setInt(3, groupReportVO.getGroupId());
                pstmt.setString(4, groupReportVO.getReportReason());
                pstmt.setString(5, groupReportVO.getEmployeeId());
                pstmt.setInt(6, groupReportVO.getGroupReportStatus());
                pstmt.setString(7, groupReportVO.getRejectReason());
                pstmt.setInt(8, groupReportVO.getGroupReportId());
                pstmt.executeUpdate();
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                Util.closeResources(con, pstmt, null);
            }
        }


    @Override
    public GroupReportVO findById(Integer groupReportId) {
        GroupReportVO groupReportVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
            pstmt = con.prepareStatement("SELECT * FROM cha102g4_test.group_report WHERE GROUP_REPORT_ID = ?");
            pstmt.setInt(1, groupReportId); // 设置查询参数
            rs = pstmt.executeQuery();
            if (rs.next()) {
                groupReportVO = new GroupReportVO();
                groupReportVO.setGroupReportId(rs.getInt("GROUP_REPORT_ID"));
                groupReportVO.setReportMemberId(rs.getInt("REPORT_MEMBER_ID"));
                groupReportVO.setReportedMemberId(rs.getInt("REPORTED_MEMBER_ID"));
                groupReportVO.setGroupId(rs.getInt("GROUP_ID"));
                groupReportVO.setReportReason(rs.getString("REPORT_REASON"));
                groupReportVO.setEmployeeId(rs.getString("EMPLOYEE_ID"));
                groupReportVO.setGroupReportStatus(rs.getInt("GROUP_REPORT_STATUS"));
                groupReportVO.setRejectReason(rs.getString("REJECT_REASON"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Util.closeResources(con, pstmt, rs);
        }
        return groupReportVO;
    }

    @Override
    public GroupReportJoin findMoreById(Integer groupReportId) {
        GroupReportJoin groupReportJoin = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
            pstmt = con.prepareStatement("select group_report_id,group_report.REPORT_MEMBER_ID, REPORT_MEMBER.member_name as REPORT_name, REPORT_MEMBER.member_email as REPORT_Email," +
                    " group_report.REPORTED_MEMBER_ID, REPORTED_MEMBER.member_name as REPORTED_name,REPORTED_MEMBER.member_email as REPORTED_Email, group_report.GROUP_ID, `group`.group_name,REPORT_REASON," +
                    " group_report.EMPLOYEE_ID,GROUP_REPORT_STATUS,REJECT_REASON from group_report" +
                    " join `member` as REPORT_MEMBER on group_report.REPORT_MEMBER_ID = REPORT_MEMBER.MEMBER_ID" +
                    " join `member` as REPORTED_MEMBER on group_report.REPORTED_MEMBER_ID = REPORTED_MEMBER .MEMBER_ID " +
                    " join `group` on group_report.group_id = `group`.group_id WHERE GROUP_REPORT_ID = ? ");
            pstmt.setInt(1, groupReportId); // 设置查询参数
            rs = pstmt.executeQuery();
            if (rs.next()){
                groupReportJoin = new GroupReportJoin();
                groupReportJoin.setGroupReportId(rs.getInt("GROUP_REPORT_ID"));
                groupReportJoin.setReportMemberId(rs.getInt("REPORT_MEMBER_ID"));
                groupReportJoin.setReportName(rs.getString("REPORT_name"));
                groupReportJoin.setReportEmail(rs.getString("REPORT_Email"));
                groupReportJoin.setReportedMemberId(rs.getInt("REPORTED_MEMBER_ID"));
                groupReportJoin.setReportedName(rs.getString("REPORTED_name"));
                groupReportJoin.setReportedEmail(rs.getString("REPORTED_Email"));
                groupReportJoin.setGroupId(rs.getInt("GROUP_ID"));
                groupReportJoin.setGroupName(rs.getString("group_name"));
                groupReportJoin.setReportReason(rs.getString("REPORT_REASON"));
                groupReportJoin.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
                groupReportJoin.setGroupReportStatus(rs.getInt("GROUP_REPORT_STATUS"));
                groupReportJoin.setRejectReason(rs.getString("REJECT_REASON"));
            }
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Util.closeResources(con, pstmt, rs);
        }
        return groupReportJoin;
    }


        @Override
    public List<GroupReportJoin> getAll() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<GroupReportJoin> groupReportList = new ArrayList<GroupReportJoin>();
        GroupReportJoin groupReport = null;
        try {
            con = DriverManager.getConnection(Util.URL,Util.USER ,Util.PASSWORD);
            pstmt = con.prepareStatement("select group_report_id,group_report.REPORT_MEMBER_ID, REPORT_MEMBER.member_name as REPORT_name, REPORT_MEMBER.member_email as REPORT_Email," +
                    " group_report.REPORTED_MEMBER_ID, REPORTED_MEMBER.member_name as REPORTED_name,REPORTED_MEMBER.member_email as REPORTED_Email, group_report.GROUP_ID, `group`.group_name,REPORT_REASON," +
                    " group_report.EMPLOYEE_ID,GROUP_REPORT_STATUS,REJECT_REASON from group_report" +
                    " join `member` as REPORT_MEMBER on group_report.REPORT_MEMBER_ID = REPORT_MEMBER.MEMBER_ID" +
                    " join `member` as REPORTED_MEMBER on group_report.REPORTED_MEMBER_ID = REPORTED_MEMBER .MEMBER_ID" +
                    " join `group` on group_report.group_id = `group`.group_id");
            rs = pstmt.executeQuery();

            while(rs.next()) {
                groupReport = new GroupReportJoin();
                groupReport.setGroupReportId(rs.getInt("GROUP_REPORT_ID"));
                groupReport.setReportMemberId(rs.getInt("REPORT_MEMBER_ID"));
                groupReport.setReportName(rs.getString("REPORT_name"));
                groupReport.setReportEmail(rs.getString("REPORT_Email"));
                groupReport.setReportedMemberId(rs.getInt("REPORTED_MEMBER_ID"));
                groupReport.setReportedName(rs.getString("REPORTED_name"));
                groupReport.setReportedEmail(rs.getString("REPORTED_Email"));
                groupReport.setGroupId(rs.getInt("GROUP_ID"));
                groupReport.setGroupName(rs.getString("group_name"));
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
                groupReport.setEmployeeId(rs.getString("EMPLOYEE_ID"));
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
