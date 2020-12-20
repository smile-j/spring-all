package base.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MethodInfo {

    private String url;
    private HttpMethod method;
    /**
     * 请求参数
     */
    private Map<String,Object> params;

    /**
     * 请求body
     */
    private Mono body;
    /**
     * 请求body的类型
     */
    private Class<?> bodyElementType;

    /**
     * 返回是flux还是mono
     */
    private boolean returnFlux;

    /**
     * 返回对象类型
     */
    private Class<?> returnElementType;

}
