package org.dong.start.rocketmq.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author dongjunpeng
 * @Description
 * @date 2021/7/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderVo {

    private long orderId;
    private String desc;

}
