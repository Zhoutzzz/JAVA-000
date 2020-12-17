package dependency.service;

import dependency.exception.InvalidDataException;
import dependency.repo.UserTxDO;
import org.dromara.hmily.annotation.Hmily;

public interface LocalUserTxService {

    @Hmily
    public UserTxDO getDollarsForRmb(UserTxDO newRecord) throws InvalidDataException;

    @Hmily
    public UserTxDO passiveFreeze(UserTxDO newRecord) throws InvalidDataException;
}
