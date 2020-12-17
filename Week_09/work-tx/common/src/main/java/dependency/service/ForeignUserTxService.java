package dependency.service;

import dependency.exception.InvalidDataException;
import dependency.repo.UserTxDO;
import org.dromara.hmily.annotation.Hmily;

public interface ForeignUserTxService {

    @Hmily
    UserTxDO getRmbForDollars(UserTxDO newRecord) throws InvalidDataException;

    @Hmily
    UserTxDO passiveFreeze(UserTxDO newRecord) throws InvalidDataException;
}
