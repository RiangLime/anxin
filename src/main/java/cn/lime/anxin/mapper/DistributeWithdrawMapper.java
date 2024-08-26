package cn.lime.anxin.mapper;

import cn.lime.anxin.model.entity.DistributeWithdraw;
import cn.lime.anxin.model.vo.distribute.DistributeWithdrawVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* @author riang
* @description 针对表【Distribute_Withdraw】的数据库操作Mapper
* @createDate 2024-08-26 12:18:11
* @Entity cn.lime.anxin.model.entity.DistributeWithdraw
*/
public interface DistributeWithdrawMapper extends BaseMapper<DistributeWithdraw> {
    Page<DistributeWithdrawVo> pageWithdraws(Long userId, Integer state, Integer priceStart, Integer priceEnd,
                                             Long applyTimeStart,Long applyTimeEnd,Page<?> page);
}




