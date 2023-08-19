package tw.com.cha102.support.model;

public class Util {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL =
            "jdbc:mysql://localhost:3306/cha102g4_test?"
//			+ "useSSL=false&"                   // 不使用加密連線 (需有憑證才行)
//			+ "rewriteBatchedStatements=true&"  // 批次更新需要此資訊
            + "serverTimezone=Asia/Taipei";     // 設定時區資訊
// 			+ "allowPublicKeyRetrieval=true&"   // 配合MySQL 8以後版本對密碼儲存機制的設定
//			+ "useUnicode=true&"                // 使用Unicode編碼 (中文才不會亂碼)
//			+ "characterEncoding=utf-8";        // 字元採用UTF-8設定
    public static final String USER = "root";
    public static final String PASSWORD = "FENG880530";

}
