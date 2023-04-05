import com.ljj.common.utils.RandomUtil;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class test {


    @Test
    public void test01(){
        String fourBitRandom = RandomUtil.getFourBitRandom();

        String param = "{\"code\":" + "\"" + fourBitRandom + "\"}";
        System.out.println(param);
    }
}
