package com.jt.web.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Date:2019/7/1 Description:com.jt.common.service
 */
@Service
public class HttpClientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientService.class);

    @Autowired(required=false)
    private CloseableHttpClient httpClient;

    @Autowired(required=false)
    private RequestConfig requestConfig;

    public String doGet
            (String url, Map<String,String> params, String charset){
        String result = null;

        //1.判断字符集编码是否为null
        if(StringUtils.isEmpty(charset)){
            charset = "UTF-8";
        }
        try {
            //2.判断是否有参数
            if(params != null){
                URIBuilder builder = new URIBuilder(url);

                for (Map.Entry<String,String> entry : params.entrySet()) {

                    builder.addParameter(entry.getKey(), entry.getValue());
                }
                //利用工具类实现url拼接
                url = builder.build().toString();
            }

            //发起http请求
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);
            CloseableHttpResponse httpResponse =
                    httpClient.execute(httpGet);

            if(httpResponse.getStatusLine()
                    .getStatusCode() == 200){
                //获取返回值结果
                result = EntityUtils.
                        toString(httpResponse.getEntity(),charset);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public String doGet(String url){
                return doGet(url,null,null);
    }
    public String doGet(String url, Map<String,String> params){
        return doGet(url,params,null);
    }
    /**
     * Post请求底层实现通过二进制形式传输数据.
     * 需要工具类模拟form表单格式进行数据提交
     * @param url
     * @param params
     * @param charset
     * @return
     */
    public String doPost(String url,Map<String,String> params,String charset){
        String result = null;

        //1.判断字符集编码是否为null
        if(StringUtils.isEmpty(charset)){
            charset = "UTF-8";
        }

        //2.准备请求对象
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);  //定义超时

        try {
            //3.判断参数是否为null
            if(params != null){
                //定义参数封装的List集合
                List<BasicNameValuePair> parameters = new ArrayList<>();
                for (Map.Entry<String,String> entry : params.entrySet()) {

                    BasicNameValuePair nameValuePair =
                            new BasicNameValuePair(entry.getKey(),entry.getValue());
                    parameters.add(nameValuePair);
                }

                //封装参数实体对象
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, charset);
                httpPost.setEntity(entity);
                //将实体对象添加到请求对象中
            }

            //发送post请求
            CloseableHttpResponse httpResponse =
                    httpClient.execute(httpPost);

            if(httpResponse.getStatusLine().getStatusCode() == 200){

                result = EntityUtils.toString(httpResponse.getEntity(),charset);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public String doPost(String url){

        return doPost(url, null, null);
    }

    public String doPost(String url,Map<String,String> params){

        return doPost(url, params, null);
    }
}
