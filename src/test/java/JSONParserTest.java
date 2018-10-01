import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

public class JSONParserTest {

//    @Spy
//    InputStream is ;
//    @Spy
    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
    JSONParser jsonParser = JSONParser.getJsonParser();

    @Test
    public void getJSONStringFromAPI(){

    }

    @Test
    public void readAll() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = JSONParser.class.getDeclaredMethod("readAll", Reader.class );
        method.setAccessible(true);
        Reader reader = new StringReader("applebanana");
        assertEquals("applebanana",method.invoke(jsonParser, reader));
        assertNotSame("apple banana",method.invoke(jsonParser, reader));
    }

    @Test
    public void getListFromJSONString(){
        String jsonString = "[{fff:1,ggg:2,hhh:3},{nn:44,mm:55}]";
        List result = jsonParser.getListFromJSONString(jsonString);
//        Map map = new HashMap();
//        map.put("fff","1.0");
//        map.put("ggg", "2.0");
//        map.put("hhh","3.0");
//        List expected = new ArrayList();
//        expected.add(map);
        assert( result instanceof List);
        assert(result.get(0) instanceof Map);
        assertEquals(1d, ((Map)result.get(0)).get("fff"));
    }
}