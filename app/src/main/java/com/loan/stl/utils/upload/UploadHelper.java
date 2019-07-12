package com.loan.stl.utils.upload;

//上传七牛云文件

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.loan.stl.module.user.dataModel.receive.OauthTokenMo;
import com.loan.stl.network.HttpClient;
import com.loan.stl.network.ResponseCallback;
import com.loan.stl.network.entity.HttpResult;
import com.loan.stl.utils.SPreferences.SharedInfo;
import com.loan.stl.utils.device.DeviceInfoUtils;
import com.qiniu.android.common.AutoZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Response;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Random;

import static java.lang.String.valueOf;

public class UploadHelper {





    private Handler handler;

    private UploadManager uploadManager;

    private String name="orc/image/jilong/";


    //上传所需要的token
    private String token="";
    public UploadHelper(Handler handler){
        this.handler=handler;
        Configuration config = new Configuration.Builder()
                .zone(AutoZone.autoZone)
                .build();
        uploadManager = new UploadManager(config);
        OauthTokenMo mo = SharedInfo.getInstance().getEntity(OauthTokenMo.class);
        if (mo != null) {
            name+=mo.getUsername()+"/";
        }
    }

    public void start(View view){
        HashMap<String, String> head=new HashMap<>();
        //client-version-code:"2000"   client-type:"iOS"  client-device-id:设备唯一标识
        head.put("client-version-code","2000");
        head.put("client-type","android");
        head.put("client-device-id", DeviceInfoUtils.getIMEI(view.getContext()));
//        Call<HttpResult<UploadRec>> callInit = HttpClient.getService(MineService.class).getToken(head);
//        NetworkUtil.showCutscenes(callInit);
//        callInit.enqueue(new ResponseCallback<HttpResult<UploadRec>>(){
//           @Override
//           public void onSuccess(Call<HttpResult<UploadRec>> call, Response<HttpResult<UploadRec>> response) {
//
//
//
//               if (response.body() != null && response.body().getData() != null) {
//                   UploadRec rec=response.body().getData();
//                   token=rec.getToken();
//                 //  ToastUtil.toast(String.valueOf(token==null));
//                       handler.sendEmptyMessage(3);
//               }else {
//                   ToastUtil.toast("图片上传失败,请稍后再试");
//               }
//
//
//           }
//       });





    }




    public void upload(final Bitmap bitmap, final int type){
        new Thread(new Runnable() {
            @Override
            public void run() {
                uploadManager.put(Bitmap2Bytes(bitmap), getKey(type), token,
                        new UpCompletionHandler() {
                            @Override
                            public void complete(String key, ResponseInfo info, JSONObject res) {
                                Message mMessage=new Message();                 //res包含hash、key等信息，具体字段取决于上传策略的设置
                                if(info.isOK()) {
                                    mMessage.what=1;
                                    mMessage.obj=key;
                                } else {
                                    //失败
                                    mMessage.what=2;
                                }
                                handler.sendMessage(mMessage);
                            }
                        }, null);

            }
        }).start();

    }


    public void workPhoto(final String url, final String myKey){
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file=new File(url);
                uploadManager.put(file, getPhotoAddress(myKey), token,
                        new UpCompletionHandler() {
                            @Override
                            public void complete(String key, ResponseInfo info, JSONObject res) {
                                Message mMessage=new Message();                 //res包含hash、key等信息，具体字段取决于上传策略的设置
                                if(info.isOK()) {
                                    mMessage.what=1;
                                    mMessage.obj=key;
                                } else {
                                    //失败
                                    mMessage.what=2;
                                }
                                handler.sendMessage(mMessage);
                            }
                        }, null);

            }
        }).start();

    }








    private String getKey(int flag){
        if(flag==0){
            return name+"auth_backImg"+"_"+getNumber()+".jpg";
        }else if(flag==1){
            return name+"auth_frontImg"+"_"+getNumber()+".jpg";
        }else if(flag==2){
            return name+"auth_livingImg"+"_"+getNumber()+".jpg";
        }else if(flag==3){
            return name+"auth_blinkImg"+"_"+getNumber()+".jpg";
        }else if(flag==4){
            return name+"auth_smileImg"+"_"+getNumber()+".jpg";
        }else if(flag==5){
            return name+"auth_faceToLeftImg"+"_"+getNumber()+".jpg";
        }else if(flag==6){
            return name+"auth_ocrImg"+"_"+getNumber()+".jpg";
        }
        return "";
    }




    private String getPhotoAddress(String url){
        if(url.contains("workFirst.jpg")){
            return name+"workFirst"+"_"+getNumber()+".jpg";
        }else  if(url.contains("workSecond.jpg")){
            return name+"workSecond"+"_"+getNumber()+".jpg";
        }else  if(url.contains("workThird.jpg")){
            return name+"workThird"+"_"+getNumber()+".jpg";
        }

        return name+url;
    }


    private String getNumber(){
        int max=30000;
        int min=0;
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return valueOf(s);
    }

    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }



}
