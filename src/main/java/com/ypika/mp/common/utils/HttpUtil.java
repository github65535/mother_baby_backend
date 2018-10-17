package com.ypika.mp.common.utils;

import com.alibaba.fastjson.JSON;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * http工具类
 * Created by huang on 2017/12/20.
 */
public class HttpUtil {

    /**
     * 接口调用 GET
     *
     * @param urlStr 地址
     * @return 返回值
     */
    public static String httpURLConectionGET(String urlStr) {
        try {
            URL url = new URL(urlStr);    // 把字符串转换为URL请求地址
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
            connection.connect();// 连接会话
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }
            br.close();// 关闭流
            connection.disconnect();// 断开连接
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
    }

    /**
     * 接口调用 POST
     *
     * @param urlStr 地址
     * @param map    数据
     * @return 返回值
     */
    public static String httpURLConectionPost(String urlStr, Map<String, Object> map){
        String params = JSON.toJSONString(map);
        if (StrUtil.isNotNull(params)) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(urlStr).openConnection();
                httpURLConnection.setConnectTimeout(3 * 1000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("charset", "UTF-8");
                httpURLConnection.setDoInput(true);// 从服务器获取数据
                httpURLConnection.setDoOutput(true);// 向服务器写入数据
                // 获得上传信息的字节大小及长度
                byte[] mydata = params.getBytes();
                // 设置请求体的类型
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                // 获得输出流，向服务器输出数据
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(mydata);
                // 获得服务器响应的结果和状态码
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    // 获得输入流，从服务器端获得数据
                    InputStream inputStream = httpURLConnection.getInputStream();
                    return (changeInputStream(inputStream, "UTF-8"));
                } else {
                    throw new RuntimeException("POST请求失败 ," + responseCode);

                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("POST请求失败");
            }
        }
        throw new RuntimeException("POST请求失败 ,map 参数为空");
    }

    /**
     * 转换写流
     *
     * @param inputStream 写流
     * @param encode      字节码
     * @return 返回字符串
     * @throws IOException 异常处理
     */
    private static String changeInputStream(InputStream inputStream, String encode) throws IOException {
        byte[] bytes = readInputStream(inputStream);
        return new String(bytes, encode);
    }

    /**
     * 读字节流
     *
     * @param inputStream 写流
     * @return 返回字节
     * @throws IOException 异常处理
     */
    private static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    //链接url下载图片
    public static void download(String urlString, String filename, String savePath) {
        try {
            // 构造URL
            URL url = new URL(urlString);
            // 打开连接
            URLConnection con = url.openConnection();
            //设置请求超时为5s
            con.setConnectTimeout(5 * 1000);
            // 输入流
            InputStream is = con.getInputStream();

            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            File sf = new File(savePath);
            if (!sf.exists()) {
                sf.mkdirs();
            }
            OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过网络获取图片
     *
     * @param url
     * @return
     */
    public static BufferedImage getUrlByBufferedImage(String url) {
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            // 连接超时
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(25000);
            // 读取超时 --服务器响应比较慢,增大时间
            conn.setReadTimeout(25000);
            conn.setRequestMethod("GET");
            conn.addRequestProperty("Accept-Language", "zh-cn");
            conn.addRequestProperty("Content-type", "image/jpeg");
            conn.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; .NET CLR 2.0.50727)");
            conn.connect();
            BufferedImage bufImg = ImageIO.read(conn.getInputStream());
            conn.disconnect();
            return bufImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
