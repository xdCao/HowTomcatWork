package chapter2;

import chapter1.Request;
import chapter1.Response; /**
 * created by xdCao on 2017/10/30
 */

public class StaticResourceProcessor implements Processor{
    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
