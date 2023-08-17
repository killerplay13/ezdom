package tw.com.cha102.support.model;

import ch.qos.logback.core.net.SyslogOutputStream;

public class test {
    public static void main(String[] args) {

        SupportDAOImpl sdi = new SupportDAOImpl();
        SupportVO supportVO = new SupportVO();

        // INSERT
//        supportVO.setFaqName("xxx");
//        supportVO.setFaqAns("yyy");
//        supportVO.setFaqTag("test");
//        sdi.add(supportVO);

        // UPDATE
//        supportVO.setFaqAns("aaa");
//        supportVO.setFaqId(5);
//        sdi.update(supportVO);

        // DELETE
//        sdi.deleteById(5);

        // SELECT_ID
//        System.out.println(sdi.findById(6));

        // SELECT_ALL
        System.out.println(sdi.findAll());
    }
}
