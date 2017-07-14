package org.lengyan.crawler.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;

/**
 * Created by Night on 2015/10/21.
 */
public class HttpRequestSSLUtils {

    /**
     * 调用微信支付接口
     *
     * @param mchId         商户ID
     * @param inStream      文件输入流
     * @param wechatApiUrl  微信接口URL
     * @param data          POST提交时的参数
     * @return
     */
    public static String sendWechatSSLRequest(String mchId,InputStream inStream,String wechatApiUrl,String data){
        String result = null;

        if(mchId == null
                || inStream == null
                || wechatApiUrl == null){
            return result;
        }

        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            try {
                keyStore.load(inStream, mchId.toCharArray());
            } finally {
                inStream.close();
            }

            // Trust own CA and all self-signed certs
            SSLContext sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, mchId.toCharArray())
                    .build();

            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

            CloseableHttpClient httpclient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .build();

            try {
                CloseableHttpResponse response = null;

                if(data == null || "".equals(data)){//GET请求
                    HttpGet httpRequest = new HttpGet(wechatApiUrl);
                    setRequestHeader(httpRequest);
                    response = httpclient.execute(httpRequest);
                }else{//POST请求
                    HttpPost httpRequest = new HttpPost(wechatApiUrl);
                    setRequestHeader(httpRequest);
                    httpRequest.setEntity(new StringEntity(data, "UTF-8"));
                    response = httpclient.execute(httpRequest);
                }

                try {
                    HttpEntity entity = response.getEntity();
                    //微信返回的报文时GBK，直接使用httpcore解析乱码
                    String responseStr = toStringInfo(response.getEntity(),"UTF-8");
                    EntityUtils.consume(entity);
                    return responseStr;
                } finally {
                    response.close();
                }
            } finally {
                httpclient.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private static void setRequestHeader(HttpUriRequest httpRequest){
        httpRequest.addHeader("Connection", "keep-alive");
        httpRequest.addHeader("Accept", "*/*");
        httpRequest.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpRequest.addHeader("Host", "api.mch.weixin.qq.com");
        httpRequest.addHeader("X-Requested-With", "XMLHttpRequest");
        httpRequest.addHeader("Cache-Control", "max-age=0");
        httpRequest.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
    }

    private static String toStringInfo(HttpEntity entity, String defaultCharset) throws Exception, IOException{
        final InputStream instream = entity.getContent();
        if (instream == null) {
            return null;
        }
        try {
            Args.check(entity.getContentLength() <= Integer.MAX_VALUE,
                    "HTTP entity too large to be buffered in memory");
            int i = (int)entity.getContentLength();
            if (i < 0) {
                i = 4096;
            }
            Charset charset = null;

            if (charset == null) {
                charset = Charset.forName(defaultCharset);
            }
            if (charset == null) {
                charset = HTTP.DEF_CONTENT_CHARSET;
            }
            final Reader reader = new InputStreamReader(instream, charset);
            final CharArrayBuffer buffer = new CharArrayBuffer(i);
            final char[] tmp = new char[1024];
            int l;
            while((l = reader.read(tmp)) != -1) {
                buffer.append(tmp, 0, l);
            }
            return buffer.toString();
        } finally {
            instream.close();
        }
    }
}
