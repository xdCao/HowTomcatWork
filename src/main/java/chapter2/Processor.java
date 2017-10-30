package chapter2;

import chapter1.Request;
import chapter1.Response;

/**
 * created by xdCao on 2017/10/30
 */

public interface Processor {

    void process(Request request, Response response);

}
