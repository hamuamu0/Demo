package com.webank.cloudface.demo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.webank.facelight.contants.WbCloudFaceContant;
import com.webank.facelight.contants.WbFaceError;
import com.webank.facelight.contants.WbFaceVerifyResult;
import com.webank.facelight.listerners.WbCloudFaceVeirfyLoginListner;
import com.webank.facelight.listerners.WbCloudFaceVeirfyResultListener;
import com.webank.facelight.tools.IdentifyCardValidate;
import com.webank.facelight.tools.WbCloudFaceVerifySdk;
import com.webank.facelight.ui.FaceVerifyStatus;
import com.webank.mbank.wehttp.WeLog;
import com.webank.mbank.wehttp.WeOkHttp;
import com.webank.mbank.wehttp.WeReq;

import java.io.IOException;

public class FaceVerifyDemoActivity extends Activity {
    private static final String TAG = "FaceVerifyDemoActivity";
    private static final int SETTING_ACTIVITY = 2;

    private ImageView faceVerifyMid;
    private ImageView faceVerifyAvd;
    private ImageView faceVerifyReflect;

    private EditText nameEt;
    private EditText idNoEt;
    private TextView sitEnv;
    private ImageView settingIv;

    private ProgressDialog progressDlg;

    private boolean isShowSuccess;
    private boolean isShowFail;
    private boolean isRecordVideo;
    private boolean isEnableCloseEyes;
    private boolean isPlayVoice;
    private String color;

    private AppHandler appHandler;
    private SignUseCase signUseCase;

    private String name;
    private String id;

    private String userId = "WbFaceVerifyAll" + System.currentTimeMillis();
    private String nonce = "52014832029547845621032584562012";
    private String compareType;
    private String keyLicence = "NwKivlx4CuaA0r1Ri/x7VGugcN5bfIUm9Q0ZfUHmr2R6mjwuZUGRUNL+ydQhfRjaCl4s+YdUnVPxGGBfxCeSYpHk0AZIRUHUy5TETKUlSKrolSR+svPde8ZImxQhXIK5Tyr+zweHGZpPzOsuYglLuPeECYNGtDfw+4pTEIXFkwBbUMuoAt/RcLBxGpjB8Ol5meMP/8A10YfWJwPvuhVttMxXX7fIqPVxrC7bMRG8Y0AXUJQtJmFR8u35BvCY1YZYrQ3puOHTVvAdOJH53+w+kKVt1sMzVaa/1qnjgNHtC8DkHJ6+FJx5nOn2Etah7oWKE4dQrd+HOjXQeWFRdb9/ww==";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        appHandler = new AppHandler(this);
        signUseCase = new SignUseCase(appHandler);

        initViews();
        initHttp();
        setListeners();
    }

    private void initViews() {
        initProgress();

        faceVerifyMid = (ImageView) findViewById(R.id.faceVerifyMid);
        faceVerifyAvd = (ImageView) findViewById(R.id.faceVerifyAdv);
        faceVerifyReflect = (ImageView) findViewById(R.id.faceVerifyLight);
        nameEt = (EditText) findViewById(R.id.et_name);
        idNoEt = (EditText) findViewById(R.id.et_idNo);

        settingIv = (ImageView) findViewById(R.id.wbcf_demo_setting);
        sitEnv = (TextView) findViewById(R.id.sit_env_logo);
    }

    private void initProgress() {
        if (progressDlg != null) {
            progressDlg.dismiss();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            progressDlg = new ProgressDialog(this);
        } else {
            progressDlg = new ProgressDialog(this);
            progressDlg.setInverseBackgroundForced(true);
        }
        progressDlg.setMessage("加载中...");
        progressDlg.setIndeterminate(true);
        progressDlg.setCanceledOnTouchOutside(false);
        progressDlg.setCancelable(true);
        progressDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDlg.setCancelable(false);
    }

    private void setListeners() {
        sitEnv.setText("3.1.7");

        //默认选择黑色模式
        color = WbCloudFaceContant.BLACK;
        //默认展示成功/失败页面
        isShowSuccess = true;
        isShowFail = true;
        //默认录制视频
        isRecordVideo = true;
        //默认播放提示语
        isPlayVoice = true;
        //默认不检测闭眼
        isEnableCloseEyes = false;
        //默认身份证对比
        compareType = WbCloudFaceContant.ID_CARD;

        settingIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle data = new Bundle();
                data.putBoolean(WbCloudFaceContant.SHOW_SUCCESS_PAGE, isShowSuccess);
                data.putBoolean(WbCloudFaceContant.SHOW_FAIL_PAGE, isShowFail);
                data.putBoolean(WbCloudFaceContant.VIDEO_UPLOAD, isRecordVideo);
                data.putBoolean(WbCloudFaceContant.PLAY_VOICE, isPlayVoice);
                data.putString(WbCloudFaceContant.COMPARE_TYPE, compareType);
                data.putString(WbCloudFaceContant.COLOR_MODE, color);
                data.putBoolean(WbCloudFaceContant.ENABLE_CLOSE_EYES, isEnableCloseEyes);

                intent.putExtras(data);
                intent.setClass(FaceVerifyDemoActivity.this, SettingActivity.class);
                startActivityForResult(intent, SETTING_ACTIVITY);
            }
        });

        faceVerifyMid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOnId(AppHandler.DATA_MODE_ACT_DESENSE);
            }
        });

        faceVerifyAvd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOnId(AppHandler.DATA_MODE_NUM);
            }
        });

        faceVerifyReflect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOnId(AppHandler.DATA_MODE_REFLECT_DESENSE);
            }
        });
    }

    private WeOkHttp myOkHttp = new WeOkHttp();

    private void initHttp() {
        //拿到OkHttp的配置对象进行配置
        //WeHttp封装的配置
        myOkHttp.config()
                //配置超时,单位:s
                .timeout(20, 20, 20)
                //添加PIN
                .log(WeLog.Level.BODY);
    }

    public void getFaceId(final FaceVerifyStatus.Mode mode, final String sign) {
        Log.d(TAG, "start getFaceId");

        final String order = "testReflect" + System.currentTimeMillis();

        final String appId = "TIDA0001";


        if (compareType.equals(WbCloudFaceContant.NONE)) {
            Log.d(TAG, "仅活体检测不需要faceId，直接拉起sdk");
            openCloudFaceService(mode, sign);
            return;
        }

        String url = "https://idasc.webank.com/api/server/getfaceid";

        Log.d(TAG, "get faceId url=" + url);

        GetFaceId.GetFaceIdParam param = new GetFaceId.GetFaceIdParam();
        param.orderNo = order;
        param.webankAppId = appId;
        param.version = "1.0.0";
        param.userId = userId;
        param.sign = sign;
        if (compareType.equals(WbCloudFaceContant.ID_CARD)) {
            Log.d(TAG, "身份证对比" + url);
            param.name = name;
            param.idNo = id;

        }

        GetFaceId.requestExec(myOkHttp, url, param, new WeReq.WeCallback<GetFaceId.GetFaceIdResponse>() {
            @Override
            public void onStart(WeReq weReq) {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onFailed(WeReq weReq, int i, int code, String message, IOException e) {
                progressDlg.dismiss();
                Log.d(TAG, "faceId请求失败:code=" + code + ",message=" + message);
                Toast.makeText(FaceVerifyDemoActivity.this, "登录异常(faceId请求失败:code=" + code + ",message=" + message + ")", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSuccess(WeReq weReq, GetFaceId.GetFaceIdResponse getFaceIdResponse) {
                if (getFaceIdResponse != null) {
                    String code = getFaceIdResponse.code;
                    if (code.equals("0")) {
                        GetFaceId.Result result = getFaceIdResponse.result;
                        if (result != null) {
                            String faceId = result.faceId;
                            if (!TextUtils.isEmpty(faceId)) {
                                Log.d(TAG, "faceId请求成功:" + faceId);
                                openCloudFaceService(mode, appId, order, sign, faceId);
                            } else {
                                progressDlg.dismiss();
                                Log.e(TAG, "faceId为空");
                                Toast.makeText(FaceVerifyDemoActivity.this, "登录异常(faceId为空)", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            progressDlg.dismiss();
                            Log.e(TAG, "faceId请求失败:getFaceIdResponse result is null.");
                            Toast.makeText(FaceVerifyDemoActivity.this, "登录异常(faceId请求失败:getFaceIdResponse result is null)", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        progressDlg.dismiss();
                        Log.e(TAG, "faceId请求失败:code=" + code + "msg=" + getFaceIdResponse.msg);
                        Toast.makeText(FaceVerifyDemoActivity.this, "登录异常(faceId请求失败:code=" + code + "msg=" + getFaceIdResponse.msg + ")", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDlg.dismiss();
                    Log.e(TAG, "faceId请求失败:getFaceIdResponse is null.");
                    Toast.makeText(FaceVerifyDemoActivity.this, "登录异常(faceId请求失败:getFaceIdResponse is null)", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void checkOnId(String mode) {
        String appId = "TIDA0001";

        if (compareType.equals(WbCloudFaceContant.ID_CARD)) {
            name = nameEt.getText().toString().trim();
            id = idNoEt.getText().toString().trim();
            if (name != null && name.length() != 0) {
                if (id != null && id.length() != 0) {
                    if (id.contains("x")) {
                        id = id.replace('x', 'X');
                    }

                    IdentifyCardValidate vali = new IdentifyCardValidate();
                    String msg = vali.validate_effective(id);
                    if (msg.equals(id)) {
                        Log.i(TAG, "Param right!");
                        Log.i(TAG, "Called Face Verify Sdk MODE=" + mode);
                        progressDlg.show();
                        signUseCase.execute(mode, appId, userId, nonce);
                    } else {
                        Toast.makeText(FaceVerifyDemoActivity.this, "用户证件号错误", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    Toast.makeText(FaceVerifyDemoActivity.this, "用户证件号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
            } else {
                Toast.makeText(FaceVerifyDemoActivity.this, "用户姓名不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            //自带源和活体对比不需要姓名 身份证
            Log.i(TAG, "No need check Param!");
            name = "";
            id = "";
            Log.i(TAG, "Called Face Verify Sdk!" + mode);
            progressDlg.show();
            signUseCase.execute(mode, appId, userId, nonce);
        }
    }

    //拉起刷脸sdk
    public void openCloudFaceService(FaceVerifyStatus.Mode mode, String appId, String order, String sign, String faceId) {
        Bundle data = new Bundle();
        WbCloudFaceVerifySdk.InputData inputData = new WbCloudFaceVerifySdk.InputData(
                faceId,
                order,
                appId,
                "1.0.0",
                nonce,
                userId,
                sign,
                mode,
                keyLicence);

        data.putSerializable(WbCloudFaceContant.INPUT_DATA, inputData);
        //是否展示刷脸成功页面，默认展示
        data.putBoolean(WbCloudFaceContant.SHOW_SUCCESS_PAGE, isShowSuccess);
        //是否展示刷脸失败页面，默认展示
        data.putBoolean(WbCloudFaceContant.SHOW_FAIL_PAGE, isShowFail);
        //颜色设置
        data.putString(WbCloudFaceContant.COLOR_MODE, color);
        //是否需要录制上传视频 默认需要
        data.putBoolean(WbCloudFaceContant.VIDEO_UPLOAD, isRecordVideo);
        //是否开启闭眼检测，默认不开启
        data.putBoolean(WbCloudFaceContant.ENABLE_CLOSE_EYES, isEnableCloseEyes);
        //是否播放提示音，默认播放
        data.putBoolean(WbCloudFaceContant.PLAY_VOICE, isPlayVoice);
        //设置选择的比对类型  默认为公安网纹图片对比
        //公安网纹图片比对 WbCloudFaceVerifySdk.ID_CRAD
        //自带比对源比对  WbCloudFaceVerifySdk.SRC_IMG
        //仅活体检测  WbCloudFaceVerifySdk.NONE
        //默认公安网纹图片比对
        data.putString(WbCloudFaceContant.COMPARE_TYPE, compareType);

        WbCloudFaceVerifySdk.getInstance().initSdk(FaceVerifyDemoActivity.this, data, new WbCloudFaceVeirfyLoginListner() {
            @Override
            public void onLoginSuccess() {
                Log.i(TAG, "onLoginSuccess");
                progressDlg.dismiss();

                WbCloudFaceVerifySdk.getInstance().startWbFaceVeirifySdk(FaceVerifyDemoActivity.this, new WbCloudFaceVeirfyResultListener() {
                    @Override
                    public void onFinish(WbFaceVerifyResult result) {
                        if (result != null) {
                            if (result.isSuccess()) {
                                Log.d(TAG, "刷脸成功! Sign=" + result.getSign() + "; liveRate=" + result.getLiveRate() +
                                        "; similarity=" + result.getSimilarity() + "userImageString=" + result.getUserImageString());
                                if (!isShowSuccess) {
                                    Toast.makeText(FaceVerifyDemoActivity.this, "刷脸成功", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                WbFaceError error = result.getError();
                                if (error != null) {
                                    Log.d(TAG, "刷脸失败！domain=" + error.getDomain() + " ;code= " + error.getCode()
                                            + " ;desc=" + error.getDesc() + ";reason=" + error.getReason());
                                    if (error.getDomain().equals(WbFaceError.WBFaceErrorDomainCompareServer)) {
                                        Log.d(TAG, "对比失败，liveRate=" + result.getLiveRate() +
                                                "; similarity=" + result.getSimilarity());
                                    }
                                    if (!isShowSuccess) {
                                        Toast.makeText(FaceVerifyDemoActivity.this, "刷脸失败!" + error.getDesc(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Log.e(TAG, "sdk返回error为空！");
                                }
                            }
                        } else {
                            Log.e(TAG, "sdk返回结果为空！");
                        }
                        //测试用代码
                        //不管刷脸成功失败，只要结束了，自带对比和活体检测都更新userId
                        if (!compareType.equals(WbCloudFaceContant.ID_CARD)) {
                            Log.d(TAG, "更新userId");
                            userId = "WbFaceVerifyREF" + System.currentTimeMillis();
                        }
                    }
                });
            }

            @Override
            public void onLoginFailed(WbFaceError error) {
                Log.i(TAG, "onLoginFailed!");
                progressDlg.dismiss();
                if (error != null) {
                    Log.d(TAG, "登录失败！domain=" + error.getDomain() + " ;code= " + error.getCode()
                            + " ;desc=" + error.getDesc() + ";reason=" + error.getReason());
                    if (error.getDomain().equals(WbFaceError.WBFaceErrorDomainParams)) {
                        Toast.makeText(FaceVerifyDemoActivity.this, "传入参数有误！" + error.getDesc(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FaceVerifyDemoActivity.this, "登录刷脸sdk失败！" + error.getDesc(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e(TAG, "sdk返回error为空！");
                }
            }
        });
    }

    //仅活体检测
    public void openCloudFaceService(final FaceVerifyStatus.Mode mode, String sign) {

        String order = "testReflect" + System.currentTimeMillis();
        String appId = "TIDA0001";

        Bundle data = new Bundle();
        WbCloudFaceVerifySdk.InputData inputData = new WbCloudFaceVerifySdk.InputData(
                name,
                "01",
                id,
                order,
                appId,
                "1.0.0",
                nonce,
                userId,
                sign,
                mode,
                keyLicence);

        data.putSerializable(WbCloudFaceContant.INPUT_DATA, inputData);
        //是否展示刷脸成功页面，默认展示
        data.putBoolean(WbCloudFaceContant.SHOW_SUCCESS_PAGE, isShowSuccess);
        //是否展示刷脸失败页面，默认展示
        data.putBoolean(WbCloudFaceContant.SHOW_FAIL_PAGE, isShowFail);
        //颜色设置
        data.putString(WbCloudFaceContant.COLOR_MODE, color);
        //是否需要录制上传视频 默认需要
        data.putBoolean(WbCloudFaceContant.VIDEO_UPLOAD, isRecordVideo);
        //是否开启闭眼检测，默认不开启
        data.putBoolean(WbCloudFaceContant.ENABLE_CLOSE_EYES, isEnableCloseEyes);
        //是否播放提示音，默认播放
        data.putBoolean(WbCloudFaceContant.PLAY_VOICE, isPlayVoice);

        //设置选择的比对类型  默认为公安网纹图片对比
        //公安网纹图片比对 WbCloudFaceVerifySdk.ID_CRAD
        //自带比对源比对  WbCloudFaceVerifySdk.SRC_IMG
        //仅活体检测  WbCloudFaceVerifySdk.NONE
        //默认公安网纹图片比对
        data.putString(WbCloudFaceContant.COMPARE_TYPE, compareType);

        Log.i(TAG, "init");
        WbCloudFaceVerifySdk.getInstance().init(FaceVerifyDemoActivity.this, data, new WbCloudFaceVeirfyLoginListner() {
            @Override
            public void onLoginSuccess() {
                Log.i(TAG, "onLoginSuccess");
                progressDlg.dismiss();

                WbCloudFaceVerifySdk.getInstance().startWbFaceVeirifySdk(FaceVerifyDemoActivity.this, new WbCloudFaceVeirfyResultListener() {
                    @Override
                    public void onFinish(WbFaceVerifyResult result) {
                        if (result != null) {
                            if (result.isSuccess()) {
                                Log.d(TAG, "刷脸成功! Sign=" + result.getSign() + "; liveRate=" + result.getLiveRate() +
                                        "; similarity=" + result.getSimilarity() + "userImageString=" + result.getUserImageString());
                                if (!isShowSuccess) {
                                    Toast.makeText(FaceVerifyDemoActivity.this, "刷脸成功", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                WbFaceError error = result.getError();
                                if (error != null) {
                                    Log.d(TAG, "刷脸失败！domain=" + error.getDomain() + " ;code= " + error.getCode()
                                            + " ;desc=" + error.getDesc() + ";reason=" + error.getReason() + ";sign=" + result.getSign());
                                    if (error.getDomain().equals(WbFaceError.WBFaceErrorDomainCompareServer)) {
                                        Log.d(TAG, "对比失败，liveRate=" + result.getLiveRate() +
                                                "; similarity=" + result.getSimilarity() + ";sign=" + result.getSign());
                                    }
                                    if (!isShowSuccess) {
                                        Toast.makeText(FaceVerifyDemoActivity.this, "刷脸失败!" + error.getDesc(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Log.e(TAG, "sdk返回error为空！");
                                }
                            }
                        } else {
                            Log.e(TAG, "sdk返回结果为空！");
                        }
                        //测试用代码
                        //不管刷脸成功失败，只要结束了，自带对比和活体检测都更新userId
                        if (!compareType.equals(WbCloudFaceContant.ID_CARD)) {
                            Log.d(TAG, "更新userId");
                            userId = "WbFaceVerifyREF" + System.currentTimeMillis();
                        }
                    }
                });
            }

            @Override
            public void onLoginFailed(WbFaceError error) {
                Log.i(TAG, "onLoginFailed!");
                progressDlg.dismiss();
                if (error != null) {
                    Log.d(TAG, "登录失败！domain=" + error.getDomain() + " ;code= " + error.getCode()
                            + " ;desc=" + error.getDesc() + ";reason=" + error.getReason());
                    if (error.getDomain().equals(WbFaceError.WBFaceErrorDomainParams)) {
                        Toast.makeText(FaceVerifyDemoActivity.this, "传入参数有误！" + error.getReason(), Toast.LENGTH_SHORT).show();
                    } else if (error.getDomain().equals(WbFaceError.WBFaceErrorDomainDevices)) {
                        Toast.makeText(FaceVerifyDemoActivity.this, error.getDesc(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FaceVerifyDemoActivity.this, "登录刷脸sdk失败！" + error.getReason(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e(TAG, "sdk返回error为空！");
                }
            }
        });
    }


    public void hideLoading() {
        if (progressDlg != null) {
            progressDlg.dismiss();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SETTING_ACTIVITY) {
            isShowSuccess = data.getBooleanExtra(WbCloudFaceContant.SHOW_SUCCESS_PAGE, true);
            isShowFail = data.getBooleanExtra(WbCloudFaceContant.SHOW_FAIL_PAGE, true);
            isRecordVideo = data.getBooleanExtra(WbCloudFaceContant.VIDEO_UPLOAD, false);
            isEnableCloseEyes = data.getBooleanExtra(WbCloudFaceContant.ENABLE_CLOSE_EYES, false);
            isPlayVoice = data.getBooleanExtra(WbCloudFaceContant.PLAY_VOICE, true);
            compareType = data.getStringExtra(WbCloudFaceContant.COMPARE_TYPE);
            color = data.getStringExtra(WbCloudFaceContant.COLOR_MODE);

            if (compareType.equals(WbCloudFaceContant.SRC_IMG) ||
                    compareType.equals(WbCloudFaceContant.NONE)) {
                nameEt.setText("");
                idNoEt.setText("");
            }
        }
    }
}
