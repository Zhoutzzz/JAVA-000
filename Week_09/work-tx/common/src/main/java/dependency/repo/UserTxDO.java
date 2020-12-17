package dependency.repo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("user_tx")
public class UserTxDO implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private BigDecimal dollars;

    private BigDecimal rmb;

    private BigDecimal freezeRmb;

    private BigDecimal freezeDollars;

    private String userName;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;
}
