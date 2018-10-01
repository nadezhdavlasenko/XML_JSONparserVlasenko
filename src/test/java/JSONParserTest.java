import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Spy;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class JSONParserTest {

//    @Spy
//    InputStream is ;
//    @Spy
   // BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
    JSONParser jsonParser = JSONParser.getJsonParser();
    //@Spy

    int responsecode;

    @Test
    public void getJSONStringFromAPI() throws IOException {
        final String url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
        String resultString = jsonParser.getJSONStringFromAPI(url);
        assert (resultString.contains("\"txt\":\"Австралійський долар\","));
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testGetJSONStringFromAPIThrowsException() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("HttpResponseCode: 404");
        final String url = "https://flippingbook.com/404";
        String resultString = jsonParser.getJSONStringFromAPI(url);
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
        String jsonString = "[{fff:hi,ggg:2,hhh:grats},{fff:bye,ggg:1}]";
        List resultList = jsonParser.getListFromJSONString(jsonString);
        assert( resultList instanceof List);
        assert(resultList.get(0) instanceof Map);
        assertEquals("hi", ((Map)resultList.get(0)).get("fff"));
    }

    @Test
    public void getMapFromList(){
        Map map = new HashMap();
        map.put("fff","hi");
        map.put("ggg", 2d);
        map.put("hhh","3.0");
        List list = new ArrayList();
        list.add(map);
        Map resultMap = jsonParser.getMapFromList(list, "ggg", "fff");
        assert (resultMap instanceof Map);
        assertEquals ("hi",resultMap.get(2.0));
    }
}