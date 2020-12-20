package base.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 服务器相关信息
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServerInfo {

    private String url;


}
