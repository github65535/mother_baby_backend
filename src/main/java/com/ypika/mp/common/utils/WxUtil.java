package com.ypika.mp.common.utils;//package com.ypika.common.utils;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.TypeReference;
//import com.google.common.base.Objects;
//import com.sunlands.feo.domain.TemplateMessage;
//import com.sunlands.feo.domain.User;
//import com.sunlands.feo.domain.enums.AppEnums;
//import com.sunlands.feo.domain.repository.TemplateMessageMapper;
//import com.ypika.common.ConstantConfig;
//import com.ypika.domain.exception.ServerStatus;
//import com.ypika.domain.exception.UserDefinedException;
//import org.apache.commons.lang.ObjectUtils;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.bouncycastle.util.Arrays;
//import org.codehaus.xfire.util.Base64;
//import org.mindrot.jbcrypt.BCrypt;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.UnsupportedEncodingException;
//import java.security.AlgorithmParameters;
//import java.security.Security;
//import java.util.Date;
//import java.util.Map;
//
///**
// * 微信小程序工具类
// * Created by huang on 2017/12/22.
// */
//public class WxUtil {
//
//    /**
//     * 小程序登陆授权
//     *
//     * @param code 微信登陆唯一标识
//     * @return 返回的openId和sessionKey
//     */
//    public static Map<String, Object> wxLogin(String code) throws UnsupportedEncodingException {
//        //微信端登录code值
//        StringBuilder sb = new StringBuilder(ConstantConfig.AUTHORIZATION_URL);
//        sb.append("?appid=").append(ConstantConfig.getAppId());
//        sb.append("&secret=").append(ConstantConfig.getAppSecret());
//        sb.append("&js_code=").append(code);
//        sb.append("&grant_type=").append("authorization_code");
//        String resultStr = HttpUtil.httpURLConectionGET(sb.toString());
//        Map<String, Object> wxMap = JSON.parseObject(resultStr, new TypeReference<Map<String, Object>>() {
//        });
//        if (StrUtil.isNotNull(wxMap) && wxMap.containsKey("errcode")) {
//            throw new UserDefinedException(ServerStatus.OPERATION_FAILED, wxMap.get("errcode") + ":" + wxMap.get("errmsg"));
//        }
//        return wxMap;
//    }
//
//    /**
//     * 公众号&app获取用户openId
//     *
//     * @param code 微信登陆唯一标识
//     * @return 返回的openId和sessionKey
//     */
//    public static Map<String, Object> getAccessTokenInfo(String code) throws UnsupportedEncodingException {
//        AssertUtil.isNotNull(code);
//        StringBuilder sb = new StringBuilder(ConstantConfig.APP_PUBLIC_OPENDID_URL);
//        sb.append("?appid=").append(ConstantConfig.getAppId());
//        sb.append("&secret=").append(ConstantConfig.getAppSecret());
//        sb.append("&code=").append(code);
//        sb.append("&grant_type=").append("authorization_code");
//        String resultStr = HttpUtil.httpURLConectionGET(sb.toString());
//        Map<String, Object> wxMap = JSON.parseObject(resultStr, new TypeReference<Map<String, Object>>() {
//        });
//        if (StrUtil.isNotNull(wxMap) && wxMap.containsKey("errcode")) {
//            throw new UserDefinedException(ServerStatus.OPERATION_FAILED, wxMap.get("errcode") + ":" + wxMap.get("errmsg"));
//        }
//        AssertUtil.isNotNull(wxMap);
//        return wxMap;
//    }
//
//    /**
//     * 获取授权刷新token
//     *
//     * @return 刷新token
//     */
//    public static Map<String, Object> getRefreshToken(String refreshToken) throws UnsupportedEncodingException {
//        StringBuilder sb = new StringBuilder(ConstantConfig.APP_PUBLIC_REFRESH_TOKEN_URL);
//        sb.append("?appid=").append(ConstantConfig.getAppId());
//        sb.append("&grant_type=").append("refresh_token");
//        sb.append("&refresh_token=").append(refreshToken);
//        String resultStr = HttpUtil.httpURLConectionGET(sb.toString());
//        Map<String, Object> wxMap = JSON.parseObject(resultStr, new TypeReference<Map<String, Object>>() {
//        });
//        if (StrUtil.isNotNull(wxMap) && wxMap.containsKey("errcode")) {
//            throw new UserDefinedException(ServerStatus.OPERATION_FAILED, wxMap.get("errcode") + ":" + wxMap.get("errmsg"));
//        }
//        AssertUtil.isNotNull(wxMap);
//        return wxMap;
//    }
//
//    /**
//     * 公众号获取用户基本信息
//     *
//     * @param accessToken 公众号token
//     * @param openid      微信登陆标识
//     * @return 用户基本信息
//     */
//    public static Map<String, Object> getUserInfo(String accessToken, String openid) throws UnsupportedEncodingException {
//        AssertUtil.isNotNull(accessToken);
//        AssertUtil.isNotNull(openid);
//        StringBuilder sb = new StringBuilder(ConstantConfig.HULUO_PUBLIC_USERINFO_URL);
//        sb.append("?access_token=").append(accessToken);
//        sb.append("&openid=").append(openid);
//        sb.append("&lang=").append("zh_CN");
//        String resultStr = HttpUtil.httpURLConectionGET(sb.toString());
//        Map<String, Object> wxMap = JSON.parseObject(resultStr, new TypeReference<Map<String, Object>>() {
//        });
//        if (StrUtil.isNotNull(wxMap) && wxMap.containsKey("errcode")) {
//            throw new UserDefinedException(ServerStatus.OPERATION_FAILED, wxMap.get("errcode") + ":" + wxMap.get("errmsg"));
//        }
//        AssertUtil.isNotNull(wxMap);
//        return wxMap;
//    }
//
//    /**
//     * 解密用户敏感数据获取用户信息
//     *
//     * @param sessionKey    数据进行加密签名的密钥
//     * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
//     * @param iv            加密算法的初始向量
//     * @return 解密用户敏感数据获取用户信息
//     */
//    public static Map<String, Object> getUserInfo(String encryptedData, String sessionKey, String iv) throws Exception {
//        //被加密的数据
//        byte[] dataByte = Base64.decode(encryptedData);
//        //加密秘钥
//        byte[] keyByte = Base64.decode(sessionKey);
//        //偏移量
//        byte[] ivByte = Base64.decode(iv);
//        //判断密钥是否符合16位
//        int base = 16;
//        if (keyByte.length % base != 0) {
//            int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
//            byte[] temp = new byte[groups * base];
//            Arrays.fill(temp, (byte) 0);
//            System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
//            keyByte = temp;
//        }
//        //初始化
//        Security.addProvider(new BouncyCastleProvider());
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
//        SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
//        AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
//        parameters.init(new IvParameterSpec(ivByte));
//        cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
//        try {
//            byte[] resultByte = cipher.doFinal(dataByte);
//            if (null != resultByte && resultByte.length > 0) {
//                String result = new String(resultByte, "UTF-8");
//                return JSON.parseObject(result, new TypeReference<Map<String, Object>>() {
//                });
//            }
//        } catch (Exception e) {
//            LogUtil.getLogger().error("解析微信加密信息出错：encryptedData:{},sessionKey:{},iv:{}", encryptedData, sessionKey, iv);
//        }
//        return null;
//    }
//
//    /**
//     * 封装用户实体
//     *
//     * @param openid 小程序对用户唯一标识
//     * @return 用户实体
//     */
//    public static User initUserEntity(String openid) {
//        AppEnums reqChannel = ApplicationContextUtil.getReqChannel();
//        String unique = StrUtil.getUnique(reqChannel, openid);
//        User user = new User();
//        user.setOpenid(openid);
//        user.setCreateDate(new Date());
//        user.setModifiDate(new Date());
//        user.setDelFlag(false);
//        user.setPassword(BCrypt.hashpw(openid, BCrypt.gensalt()));//密码默认openid
//        user.setRobot(false);//默认非机器人
//        user.setSource(reqChannel);
//        user.setUniqueFlag(unique);
//        return user;
//    }
//
//    /**
//     * 封装用户实体
//     *
//     * @param openid   小程序对用户唯一标识
//     * @param unionid  维系平台对用户唯一标识
//     * @param userInfo 用户个人信息
//     * @return 用户实体
//     */
//    public static User createUserEntity(String openid, String unionid, Map<String, Object> userInfo) {
//        User user = new User();
//        String defaultAvatarurl = ConstantConfig.getDefaultAvatarurl();
//        user.setOpenid(openid);
//        AppEnums appDeviceType = ApplicationContextUtil.getAppDeviceType();
//        AppEnums reqChannel = ApplicationContextUtil.getReqChannel();
//        String unique = StrUtil.getUnique(reqChannel, openid);
//        if (StrUtil.isNull(unionid)) {
//            unionid = String.valueOf(userInfo.get("unionid"));
//        }
//        user.setUnionid(unionid);
//        user.setCreateDate(new Date());
//        user.setModifiDate(new Date());
//        user.setDelFlag(false);
//        if (ObjectUtils.equals(appDeviceType, AppEnums.ios) || ObjectUtils.equals(appDeviceType, AppEnums.android)) {
//            user.setNickName(String.valueOf(userInfo.get("nickname")));
//            user.setGender(String.valueOf(userInfo.get("sex")));
//            user.setHeadImageUrl(StrUtil.isNotNull(userInfo.get("headimgurl")) ? String.valueOf(userInfo.get("headimgurl")) : defaultAvatarurl);
//        } else {
//            user.setNickName(String.valueOf(userInfo.get("nickName")));
//            user.setGender(String.valueOf(userInfo.get("gender")));
//            user.setHeadImageUrl(StrUtil.isNotNull(userInfo.get("avatarUrl")) ? String.valueOf(userInfo.get("avatarUrl")) : defaultAvatarurl);
//        }
//        user.setCountry(String.valueOf(userInfo.get("country")));
//        user.setProvince(String.valueOf(userInfo.get("province")));
//        user.setCity(String.valueOf(userInfo.get("city")));
//        user.setLanguage(String.valueOf(userInfo.get("language")));
//        user.setPassword(BCrypt.hashpw(openid, BCrypt.gensalt()));//密码默认openid
//        user.setRobot(false);//默认非机器人
//        user.setSource(reqChannel);
//        user.setUniqueFlag(unique);
//        return user;
//    }
//
//    /**
//     * 更新用户实体
//     *
//     * @param userEntity 更新前的用户实体
//     * @param userInfo   获取新的用户实体
//     * @return 更新用户实体
//     */
//    public static User updateUserEntity(User userEntity, Map<String, Object> userInfo) {
//        userEntity.setNickName(String.valueOf(userInfo.get("nickName")));
//        userEntity.setGender(String.valueOf(userInfo.get("gender")));
//        userEntity.setCountry(String.valueOf(userInfo.get("country")));
//        userEntity.setProvince(String.valueOf(userInfo.get("province")));
//        userEntity.setCity(String.valueOf(userInfo.get("city")));
//        userEntity.setLanguage(String.valueOf(userInfo.get("language")));
//        userEntity.setHeadImageUrl(StrUtil.isNotNull(userInfo.get("avatarUrl")) ? String.valueOf(userInfo.get("avatarUrl")) : ConstantConfig.getDefaultAvatarurl());
//        userEntity.setModifiDate(new Date());
//        userEntity.setLastRestPassword(null);
//        if (StrUtil.isNull(userEntity.getUnionid())) {
//            userEntity.setUnionid(String.valueOf(userInfo.get("unionId")));
//        }
//        if (StrUtil.isNull(userEntity.getSource())) {
//            userEntity.setSource(ApplicationContextUtil.getReqChannel());
//        }
//        return userEntity;
//    }
//
//    /**
//     * @return 微信获取token信息
//     */
//    private static Map<String, Object> getWXTokenFromWx() {
//        StringBuilder sb = new StringBuilder(ConstantConfig.ACCESS_TOKEN_URL);
//        sb.append("?appid=").append(ConstantConfig.getAppId());
//        sb.append("&secret=").append(ConstantConfig.getAppSecret());
//        sb.append("&grant_type=").append("client_credential");
//        String resultStr = HttpUtil.httpURLConectionGET(sb.toString());
//        Map<String, Object> map = JSON.parseObject(resultStr, new TypeReference<Map<String, Object>>() {
//        });
//        if (StrUtil.isNotNull(map) && map.containsKey("errcode")) {
//            throw new UserDefinedException(ServerStatus.OPERATION_FAILED, map.get("errcode") + ":" + map.get("errmsg"));
//        }
//        return map;
//    }
//
//    /**
//     * 缓存微信token
//     *
//     * @return 微信token
//     */
//    public static Map<String, Object> getWXToken() {
//        RedisUtil redisUtil = ApplicationContextUtil.getApplicationContext().getBean(RedisUtil.class);
//        Object token = redisUtil.get(ConstantConfig.getRedisWxAccessToken());
//        Date now = new Date();
//        if (StrUtil.isNotNull(token)) {
//            Map map = JsonUtil.objToMap(token);
//            Date create_token = DateUtil.timeStamp2Date((Long) map.get("create_token"));
//            Integer expires_in = (Integer) map.get("expires_in");
//            System.out.println(">> diffSecond :" + DateUtil.diffSecond(now, create_token));
//            //更换即将失效的token
//            if ((expires_in - DateUtil.diffSecond(now, create_token) >= 120)) {
//                return map;
//            }
//        }
//        Map<String, Object> wxTokenFromWx = getWXTokenFromWx();
//        wxTokenFromWx.put("create_token", now);
//        redisUtil.set(ConstantConfig.getRedisWxAccessToken(), wxTokenFromWx);
//        return wxTokenFromWx;
//    }
//
//    /**
//     * 推送至微信服务通知
//     *
//     * @param temp        消息模板
//     * @param accessToken token
//     */
//    public static boolean pushTemplateMessage(Map temp, String accessToken, TemplateMessage templateMessage) {
//        if (StrUtil.isNull(temp)) {
//            return false;
//        }
//        //LogUtil.getLogger().info(">> accessToken:"+accessToken);
//        //LogUtil.getLogger().info(">> temp:"+JsonUtil.objectToStr(temp));
//        StringBuilder sb = new StringBuilder(ConstantConfig.PUSH_TEMPLATE_MESSAGE_URL);
//        sb.append("?access_token=");
//        sb.append(accessToken);
//        String resultStr;
//        resultStr = HttpUtil.httpURLConectionPost(sb.toString(), temp);
//        Map<String, Object> map = JSON.parseObject(resultStr, new TypeReference<Map<String, Object>>() {
//        });
//        if (StrUtil.isNull(map) || !Objects.equal(String.valueOf(map.get("errcode")), "0")) {
//            if (Objects.equal(String.valueOf(map.get("errcode")), "40001")) {
//                RedisUtil redisUtil = ApplicationContextUtil.getApplicationContext().getBean(RedisUtil.class);
//                Map<String, Object> wxTokenFromWx = getWXTokenFromWx();
//                wxTokenFromWx.put("create_token", new Date());
//                redisUtil.set(ConstantConfig.getRedisWxAccessToken(), wxTokenFromWx);
//                pushTemplateMessage(temp, String.valueOf(wxTokenFromWx.get("access_token")), templateMessage);
//            }
//
//            if (Objects.equal(String.valueOf(map.get("errcode")), "41028") || Objects.equal(String.valueOf(map.get("errcode")), "41029")) {//41029:form id used count reach limit hint 已被使用
//                templateMessage.setDelFlag(true);
//                templateMessage.setModifiDate(new Date());
//                TemplateMessageMapper bean = ApplicationContextUtil.getApplicationContext().getBean(TemplateMessageMapper.class);
//                bean.updateByPrimaryKey(templateMessage);
//            }
//
//            LogUtil.getLogger().info("accessToken : " + accessToken);
//            LogUtil.getLogger().error("推送至微信服务通知,操作失败:" + map.get("errcode") + ":" + map.get("errmsg"));
//            return false;
//        }
//        return true;
//    }
//
//}
