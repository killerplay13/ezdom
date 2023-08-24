package tw.com.cha102.support.model.dao.impl;

import tw.com.cha102.support.model.entity.FaqVO;
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
    public int add(FaqVO faqVO) {

        try (Connection conn = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(INSERT);)
        {
            pstmt.setString(1, faqVO.getFaqName());
            pstmt.setString(2, faqVO.getFaqAns());
            pstmt.setString(3, faqVO.getFaqTag());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    @Override
    public int update(FaqVO faqVO) {

        try (Connection conn = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(UPDATE);)
        {
            pstmt.setString(1, faqVO.getFaqAns());
            pstmt.setInt(2, faqVO.getFaqId());
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
    public FaqVO findById(Integer id) {
        FaqVO faqVO = new FaqVO();
        ResultSet rs = null;

        try (Connection conn = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ID);)
        {
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()){
                faqVO.setFaqId(rs.getInt("FAQ_ID"));
                faqVO.setFaqName(rs.getString("FAQ_NAME"));
                faqVO.setFaqAns(rs.getString("FAQ_ANS"));
                faqVO.setFaqTag(rs.getString("FAQ_TAG"));
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
        return faqVO;
    }

    @Override
    public List<FaqVO> findAll() {
        List<FaqVO> datas = new ArrayList<>();
        ResultSet rs = null;

        try (Connection conn = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);)
        {
            rs = pstmt.executeQuery();

            while (rs.next()){
                FaqVO faqVO = new FaqVO();
                faqVO.setFaqId(rs.getInt("FAQ_ID"));
                faqVO.setFaqName(rs.getString("FAQ_NAME"));
                faqVO.setFaqAns(rs.getString("FAQ_ANS"));
                faqVO.setFaqTag(rs.getString("FAQ_TAG"));
                datas.add(faqVO);
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
