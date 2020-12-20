package base.interfaces;

import base.beans.MethodInfo;
import base.beans.ServerInfo;

public interface RestHandler {
    Object invokeRest(MethodInfo methodInfo);

    void init(ServerInfo serverInfo);
}
