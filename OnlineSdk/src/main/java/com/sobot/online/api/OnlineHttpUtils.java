package com.sobot.online.api;

import android.text.TextUtils;

import com.sobot.online.OnlineConstant;
import com.sobot.onlinecommon.frame.apiUtils.ZhiChiUrlApi;
import com.sobot.onlinecommon.frame.http.SobotOkHttpUtils;
import com.sobot.onlinecommon.frame.http.builder.PostFormBuilder;
import com.sobot.onlinecommon.frame.http.builder.PostMultipartFormBuilder;
import com.sobot.onlinecommon.frame.http.callback.StringCallback;
import com.sobot.onlinecommon.frame.http.download.SobotDownload;
import com.sobot.onlinecommon.frame.http.download.SobotDownloadTask;
import com.sobot.onlinecommon.frame.http.request.RequestCall;
import com.sobot.onlinecommon.frame.http.upload.SobotUpload;
import com.sobot.onlinecommon.frame.http.upload.SobotUploadTask;
import com.sobot.onlinecommon.utils.SobotOnlineLogUtils;
import com.sobot.onlinecommon.utils.SobotSPUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Response;

public class OnlineHttpUtils {
    private static OnlineHttpUtils client = null;

    private OnlineHttpUtils() {
    }

    public static OnlineHttpUtils getInstance() {
        if (client == null) {
            client = new OnlineHttpUtils();
        }
        return client;
    }

    private static String getTokenId(String url) {
        String tokenId;
        String tokenType;
        //KEY_TOKEN
        if (url.startsWith(SobotOnlineBaseUrl.getBaseIp())) {
            tokenType = OnlineConstant.KEY_TEMP_ID;
        } else {
            tokenType = OnlineConstant.KEY_TOKEN;
        }

        tokenId = SobotSPUtils.getInstance().getString(tokenType, "");
        return tokenId;
    }

    public void doPost(Object cancelTag, final String url_str, final Map<String, String> map,
                       final StringCallBack callback) {
        SobotOnlineLogUtils.i("请求URL: --> " + url_str);
        SobotOnlineLogUtils.i("请求参数: --> " + map);
        SobotOnlineLogUtils.i("请求token: --> " + getTokenId(url_str));
        SobotOkHttpUtils
                .post()
                .tag(cancelTag)
                .url(url_str)
                .params(map)
                .addHeader(OnlineConstant.KEY_TEMP_ID, getTokenId(url_str))
                .addParams("from", ZhiChiUrlApi.SOBOT_FROM)
                .addParams("version", ZhiChiUrlApi.VERSION)
                .build()
                .readTimeOut(8 * 1000)
                .writeTimeOut(8 * 1000)
                .connTimeOut(8 * 1000).execute(new StringCallback() {

            @Override
            public void onResponse(String response) {
                SobotOnlineLogUtils.i(url_str + "----请求返回结果: --> " + response);
                callback.onResponse(response);

            }

            @Override
            public void onError(Call call, Exception e) {
                SobotOnlineLogUtils.i(call.toString());
                e.printStackTrace();
                callback.onError(e, call.toString(), -1);
            }
        });
    }
    public void doPostWithFile(Object cancelTag, final String url_str, final Map<String, String> map,String path,
                       final StringCallBack callback) {
        SobotOnlineLogUtils.i("请求URL: --> " + url_str);
        SobotOnlineLogUtils.i("请求参数: --> " + map);
        SobotOnlineLogUtils.i("请求token: --> " + getTokenId(url_str));

        PostMultipartFormBuilder post = SobotOkHttpUtils.postMultipart();
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                post.addFile("file", file.getName(), file);
            }
        }

        post.tag(cancelTag)
                .url(url_str)
                .params(map)
                .addHeader(OnlineConstant.KEY_TEMP_ID, getTokenId(url_str))
                .addParams("from", ZhiChiUrlApi.SOBOT_FROM)
                .addParams("version", ZhiChiUrlApi.VERSION)
                .build()
                .readTimeOut(8 * 1000)
                .writeTimeOut(8 * 1000)
                .connTimeOut(8 * 1000).execute(new StringCallback() {

            @Override
            public void onResponse(String response) {
                SobotOnlineLogUtils.i(url_str + "----请求返回结果: --> " + response);
                callback.onResponse(response);

            }

            @Override
            public void onError(Call call, Exception e) {
                SobotOnlineLogUtils.i(call.toString());
                e.printStackTrace();
                callback.onError(e, call.toString(), -1);
            }

            @Override
            public void inProgress(float progress) {
                super.inProgress(progress);
                callback.inProgress((int) (progress * 100));
            }
        });
    }

    public void doGet(Object cancelTag, final String url_str, final Map<String, String> map,
                       final StringCallBack callback) {
        SobotOnlineLogUtils.i("请求URL: --> " + url_str);
        SobotOnlineLogUtils.i("请求参数: --> " + map);
        SobotOnlineLogUtils.i("请求token: --> " + getTokenId(url_str));
        SobotOkHttpUtils
                .get()
                .tag(cancelTag)
                .url(url_str)
                .params(map)
                .addHeader(OnlineConstant.KEY_TEMP_ID, getTokenId(url_str))
                .addParams("from", ZhiChiUrlApi.SOBOT_FROM)
                .addParams("version", ZhiChiUrlApi.VERSION)
                .build()
                .readTimeOut(8 * 1000)
                .writeTimeOut(8 * 1000)
                .connTimeOut(8 * 1000).execute(new StringCallback() {

            @Override
            public void onResponse(String response) {
                SobotOnlineLogUtils.i(url_str + "----请求返回结果: --> " + response);
                callback.onResponse(response);

            }

            @Override
            public void onError(Call call, Exception e) {
                SobotOnlineLogUtils.i(call.toString());
                e.printStackTrace();
                callback.onError(e, call.toString(), -1);
            }


        });
    }

    public void doGetWithNoHeader(Object cancelTag, final String url_str, final Map<String, String> map,
                                  final StringCallBack callback) {
        SobotOnlineLogUtils.i("请求URL: --> " + url_str);
        SobotOnlineLogUtils.i("请求参数: --> " + map);
        SobotOkHttpUtils
                .get()
                .tag(cancelTag)
                .url(url_str)
                .params(map)
                .addParams("from", ZhiChiUrlApi.SOBOT_FROM)
                .addParams("version", ZhiChiUrlApi.VERSION)
                .build()
                .readTimeOut(8 * 1000)
                .writeTimeOut(8 * 1000)
                .connTimeOut(8 * 1000).execute(new StringCallback() {

            @Override
            public void onResponse(String response) {
                SobotOnlineLogUtils.i(url_str + "----请求返回结果: --> " + response);
                callback.onResponse(response);

            }

            @Override
            public void onError(Call call, Exception e) {
                SobotOnlineLogUtils.i(call.toString());
                e.printStackTrace();
                callback.onError(e, call.toString(), -1);
            }
        });
    }

    private void printLog(String url_str, Map<String, String> map) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("sobot---请求参数： url = " + url_str + "  ");
            for (String key : map.keySet()) {
                sb.append(key + "=" + map.get(key) + ", ");
            }
            SobotOnlineLogUtils.i(sb.toString().substring(0, sb.toString().length() - 2));
        } catch (Exception e) {
            //ignor
        }
    }

    public void download(String url_str, File file, Map<String, String> map,
                         final FileCallBack callback) {
        SobotOnlineLogUtils.i("下载地址：" + url_str);
        SobotOkHttpUtils
                .get()
                .url(url_str)
                .addParams("from", ZhiChiUrlApi.SOBOT_FROM)
                .addParams("version", ZhiChiUrlApi.VERSION)
                .build()
                .connTimeOut(1000 * 30)
                .readTimeOut(1000 * 30)
                .writeTimeOut(1000 * 30)
                .execute(new com.sobot.onlinecommon.frame.http.callback.FileCallBack(file.getAbsolutePath()) {

                    @Override
                    public void onError(Call call, Exception e) {
                        callback.onError(e, call.toString(), -1);
                    }

                    @Override
                    public void onResponse(File file) {
                        callback.onResponse(file);
                    }

                    @Override
                    public void inProgress(float progress, long total) {
                        callback.inProgress((int) (progress * 100));
                    }
                });

    }

    public void uploadFile(Object cancelTag, String url_str, Map<String, String> map,
                           String path, final StringCallBack callback) {
        SobotOnlineLogUtils.i("请求URL: --> " + url_str);
        SobotOnlineLogUtils.i("请求参数: --> " + map);
        PostFormBuilder post = SobotOkHttpUtils.post();
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                post.addFile("file", file.getName(), file);
            }
        }

        post.url(url_str)//
                .params(map)
                .tag(cancelTag)
                .addHeader(OnlineConstant.KEY_TEMP_ID, getTokenId(url_str))
                .addParams("from", ZhiChiUrlApi.SOBOT_FROM)
                .addParams("version", ZhiChiUrlApi.VERSION)
                .build()
                .connTimeOut(1000 * 30)
                .readTimeOut(1000 * 30)
                .writeTimeOut(1000 * 30)
                .execute(new StringCallback() {

                    @Override
                    public void onResponse(String response) {
                        callback.onResponse(response);
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        callback.onError(e, call.toString(), -1);
                    }

                    @Override
                    public void inProgress(float progress) {
                        super.inProgress(progress);
                        callback.inProgress((int) (progress * 100));
                    }
                });
    }

    /**
     * 添加上传文件任务
     *
     * @param msgTag    上传文件的识别码 这里相当于msgId
     * @param url_str   上传文件的地址
     * @param map       参数
     * @param path      文件本地地址
     * @param imageFile 视频文件的快照本地地址
     * @return
     */
    public SobotUploadTask addUploadFileTask(String msgTag, String url_str, Map<String, String> map,
                                             String path, String imageFile) {
        SobotOnlineLogUtils.i("上传文件 请求URL: --> " + url_str);
        SobotOnlineLogUtils.i("上传文件 请求参数: --> " + map);
        PostFormBuilder post = SobotOkHttpUtils.post();
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            post.addFile("file", file.getName(), file);
        }

        if (!TextUtils.isEmpty(imageFile)) {
            File file2 = new File(imageFile);
            post.addFile("imageFile", file2.getName(), file2);
        }

        RequestCall requestCall = post.url(url_str)//
                .params(map)
                .addParams("from", ZhiChiUrlApi.SOBOT_FROM)
                .addParams("version", ZhiChiUrlApi.VERSION)
                .build()
                .connTimeOut(1000 * 30)
                .readTimeOut(1000 * 30)
                .writeTimeOut(1000 * 30);
        Random random = new Random();
        // msgid
        return SobotUpload.request(msgTag, requestCall)
                .priority(random.nextInt(100))
                .tmpTag(msgTag)
                .filePath(path).start();
    }

    /**
     * 添加下载文件任务
     *
     * @param msgTag  上传文件的识别码 这里相当于msgId
     * @param url_str 文件的下载地址
     * @param map     参数
     * @return
     */
    public SobotDownloadTask addDownloadFileTask(String msgTag, String url_str, String fileName, Map<String, String> map) {

        if (TextUtils.isEmpty(msgTag) || TextUtils.isEmpty(url_str)) {
            return null;
        }
        Random random = new Random();
        // msgid
        return SobotDownload.request(msgTag, obtainGetRequest(url_str, map))
                .priority(random.nextInt(100))
                .fileName(fileName)
                .save();
    }

    public RequestCall obtainGetRequest(String url_str, Map<String, String> map) {
        return SobotOkHttpUtils.get().url(url_str)
                .params(map)
                .addParams("from", ZhiChiUrlApi.SOBOT_FROM)
                .addParams("version", ZhiChiUrlApi.VERSION)
                .build()
                .connTimeOut(1000 * 30)
                .readTimeOut(1000 * 30)
                .writeTimeOut(1000 * 30);
    }

    //同步
    public Response doPostSync(Object cancelTag, String url_str, Map<String, String> map) throws IOException {
        return SobotOkHttpUtils
                .post()
                .tag(cancelTag)
                .url(url_str)
                .params(map)
                .addParams("from", ZhiChiUrlApi.SOBOT_FROM)
                .addParams("version", ZhiChiUrlApi.VERSION)
                .build()
                .readTimeOut(8 * 1000)
                .writeTimeOut(8 * 1000)
                .connTimeOut(8 * 1000).execute();
    }

    public interface StringCallBack {
        void onResponse(String result);

        void onError(Exception e, String msg, int responseCode);

        void inProgress(int progress);
    }

    public interface FileCallBack {
        void onResponse(File result);

        void onError(Exception e, String msg, int responseCode);

        void inProgress(int progress);
    }
}
