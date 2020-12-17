package dependency.repo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalUserTxMapper extends BaseMapper<UserTxDO> {
    @Update("update user_tx set rmb = rmb + #{rmb}, dollars = dollars - #{dollars} where id = #{id}")
    int passiveExchange(UserTxDO data);

    @Update("update user_tx set rmb = rmb - #{rmb}, dollars = dollars + #{dollars} where id = #{id}")
    int getDollarsForRmb(UserTxDO data);

    @Update("update user_tx set freeze_rmb = #{rmb}, freeze_dollars = #{dollars}, rmb = rmb - #{rmb} where id = #{id}")
    int initiativeFreeze(UserTxDO data);

    @Update("update user_tx set dollars = dollars + #{dollars}, freeze_rmb = freeze_rmb - #{rmb}, freeze_dollars = freeze_dollars - #{dollars} where id = #{id}")
    int txSuccess(UserTxDO money);


    @Update("update user_tx set rmb = rmb + #{rmb}, freeze_rmb = freeze_rmb - #{rmb}, freeze_dollars = freeze_dollars - #{dollars} where id = #{id}")
    int txFailed(UserTxDO money);
}
