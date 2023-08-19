package tw.com.cha102.support.model.dao.impl;

import tw.com.cha102.support.model.entity.SupportVO;
import tw.com.cha102.support.model.Util;
import tw.com.cha102.support.model.dao.SupportDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupportDAOImpl implements SupportDAO {
    public static final String INSERT = "insert into FAQ (FAQ_NAME, FAQ_ANS, FAQ_TAG) values (?, ?, ?)";
    public static final String UPDATE = "update FAQ set FAQ_ANS = ? where FAQ_ID = ?";
    public static final String DELETE = "delete from FAQ where FAQ_ID = ?";
    public static final String SELECT_ID = "select * from FAQ where FAQ_ID = ?";
    public static final String SELECT_ALL = "select * from FAQ";

    static {
        try {
            Class.forName(Util.DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int add(SupportVO supportVO) {

        try (Connection conn = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(INSERT);)
        {
            pstmt.setString(1, supportVO.getFaqName());
            pstmt.setString(2, supportVO.getFaqAns());
            pstmt.setString(3, supportVO.getFaqTag());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    @Override
    public int update(SupportVO supportVO) {

        try (Connection conn = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(UPDATE);)
        {
            pstmt.setString(1, supportVO.getFaqAns());
            pstmt.setInt(2, supportVO.getFaqId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    @Override
    public int deleteById(Integer id) {

        try (Connection conn = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(DELETE);)
        {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    @Override
    public SupportVO findById(Integer id) {
        SupportVO supportVO = new SupportVO();
        ResultSet rs = null;

        try (Connection conn = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ID);)
        {
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()){
                supportVO.setFaqId(rs.getInt("FAQ_ID"));
                supportVO.setFaqName(rs.getString("FAQ_NAME"));
                supportVO.setFaqAns(rs.getString("FAQ_ANS"));
                supportVO.setFaqTag(rs.getString("FAQ_TAG"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return supportVO;
    }

    @Override
    public List<SupportVO> findAll() {
        List<SupportVO> datas = new ArrayList<>();
        ResultSet rs = null;

        try (Connection conn = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);)
        {
            rs = pstmt.executeQuery();

            while (rs.next()){
                SupportVO supportVO = new SupportVO();
                supportVO.setFaqId(rs.getInt("FAQ_ID"));
                supportVO.setFaqName(rs.getString("FAQ_NAME"));
                supportVO.setFaqAns(rs.getString("FAQ_ANS"));
                supportVO.setFaqTag(rs.getString("FAQ_TAG"));
                datas.add(supportVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return datas;
    }
}
