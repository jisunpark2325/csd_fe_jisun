package com.ktds.clouddev.csd_fe_jisun02.service;

import com.ktds.clouddev.csd_fe_jisun02.dto.TestBusi;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class TestFeService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    //Get All Busi List
    public List<TestBusi> getAllBusiList() {
        List<TestBusi> busiList = null;
        log.info("==============CSD FE TFS==============getAllBusiList");

        try {
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setConnectTimeout(5000);
            factory.setReadTimeout(5000);

            RestTemplate restTemplate = new RestTemplate(factory);

            HttpHeaders header = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(header);
            String UrlGetAllBusi = "http://deploy-business.jisun-test.svc:8080/jisun/businesses?";

            UriComponents uri = UriComponentsBuilder.fromHttpUrl(UrlGetAllBusi).build();
            log.info("==============CSD FE TFS==============getAllBusiList uri = {}", uri.toString());

            ResponseEntity resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, String.class);
            log.info("==============CSD FE TFS==============getAllBusiList - return = {}", resultMap.getBody());

            JSONParser jsonParser = new JSONParser();
            JSONArray resultArray = (JSONArray) jsonParser.parse(resultMap.getBody().toString());
            JSONObject resultObj = (JSONObject) jsonParser.parse(resultArray.get(0).toString());
            log.info("==============CSD FE TFS==============getAllBusiList - return = {}", resultObj);


            //result_plat_code = Integer.parseInt(resultObj.get("plat_code").toString());
            //log.info("==============CSD CONT==============callOnePlat - plat_code = {}", result_plat_code);


        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.info("HttpClientErrorException | HttpServerErrorException error");
            System.out.println("HttpClientErrorException | HttpServerErrorException error");
            System.out.println(e.toString());
            e.printStackTrace();
        } catch (Exception e) {
            log.info("Exception");
            System.out.println(e.toString());
        }
        return null;

    }
}
