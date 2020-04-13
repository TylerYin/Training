package com.fwzs.restful;

import com.mapper.JsonMapper;
import com.util.DateUtils;
import com.util.FileUtils;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Date;

/**
 * RestTemplate Test
 *
 * @author Tyler
 */
public class RestTemplateUtilsTest {

    @Test
    public void downloadResidentList() throws Exception {
        //String url = "http://192.168.1.138:8080/disease/api/yqfk/downloadResident";
        String url = "http://192.168.1.138:8080/disease/api/yqfk/downloadResident?companyId=3d2772c219694f25ac1ed9240faa7b1c";
        JsonMapper jsonMapper = new JsonMapper();

//        HashMap<String, String> paraMap = new HashMap<>(4);
//        paraMap.put("companyId", "3d2772c219694f25ac1ed9240faa7b1c");
//        paraMap.put("instituteId", null);
//        paraMap.put("beginDate", "2020-03-01");
//        paraMap.put("endDate", "2020-03-19");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(url, Object.class);
        //ResponseEntity<BaseBeanListJson> responseEntity = restTemplate.postForEntity(url, BaseBeanListJson.class, String.class,paraMap);

        FileUtils.write(new File("C:\\Users\\Administrator\\Desktop\\jsonObject_" + DateUtils.formatDate(new Date(), "yyyyMMddHHmmss") + ".txt"), jsonMapper.toJson(responseEntity.getBody()), "utf-8", false);
        System.out.println(jsonMapper.toJson(responseEntity.getBody()));
    }
}
