package tw.com.cha102.reserve.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.cha102.reserve.model.dao.ReserveItemRepository;
import tw.com.cha102.reserve.model.dao.ReserveRepository;
import tw.com.cha102.reserve.model.dao.ReserveTimeRepository;
import tw.com.cha102.reserve.model.entity.ReserveItemVO;
import tw.com.cha102.reserve.service.ReserveService;

@Service
public class ReserveServiceImpl implements ReserveService {

    @Autowired
    private ReserveRepository reserveRepository;

    @Autowired
    private ReserveItemRepository reserveItemRepository;

    @Autowired
    private ReserveTimeRepository reserveTimeRepository;


    @Override
    public ReserveItemVO insertReserveItem(ReserveItemVO reserveItemVO) {
        return reserveItemRepository.save(reserveItemVO);
    }
}
