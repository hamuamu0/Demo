package com.webank.cloudface.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.webank.facelight.contants.WbCloudFaceContant;


public class SettingActivity extends Activity {

    private static final String TAG = "SettingActivity";

    private boolean isShowSuccess;
    private boolean isShowFail;
    private boolean isRecord;
    private boolean isEnableCloseEyes;
    private boolean isPlayVoice;
    private String compareType;
    private String colorMode;

    private SlipButton showSuccessPage;
    private SlipButton showFailPage;
    private SlipButton recordVideo;
    private SlipButton closeEyes;
    private SlipButton colorChoose;
    private SlipButton playVoice;
    private ImageView back;

    private RadioGroup srcChoose;
    private RadioButton noneSrcBtn;
    private RadioButton idSrcBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        if (getIntent() != null) {
            Bundle data = getIntent().getExtras();
            if (data != null) {
                isShowSuccess = data.getBoolean(WbCloudFaceContant.SHOW_SUCCESS_PAGE, true);
                isShowFail = data.getBoolean(WbCloudFaceContant.SHOW_FAIL_PAGE, true);
                isRecord = data.getBoolean(WbCloudFaceContant.VIDEO_UPLOAD, false);
                isEnableCloseEyes = data.getBoolean(WbCloudFaceContant.ENABLE_CLOSE_EYES, false);
                isPlayVoice = data.getBoolean(WbCloudFaceContant.PLAY_VOICE, true);
                colorMode = data.getString(WbCloudFaceContant.COLOR_MODE, WbCloudFaceContant.BLACK);
                compareType = data.getString(WbCloudFaceContant.COMPARE_TYPE, WbCloudFaceContant.ID_CARD);
            }
        }
        init();
    }

    private void init() {
        showSuccessPage = (SlipButton) findViewById(R.id.wbcf_demo_show_success_slip_btn);
        showFailPage = (SlipButton) findViewById(R.id.wbcf_demo_show_fail_slip_btn);
        recordVideo = (SlipButton) findViewById(R.id.wbcf_demo_record_btn);
        closeEyes = (SlipButton) findViewById(R.id.wbcf_demo_close_eyes_btn);
        playVoice = (SlipButton) findViewById(R.id.wbcf_demo_voice_btn);
        colorChoose = (SlipButton) findViewById(R.id.wbcf_demo_color_btn);
        back = (ImageView) findViewById(R.id.wbcf_demo_setting_back);

        srcChoose = (RadioGroup) findViewById(R.id.wbcf_demo_src_choose_rg);
        noneSrcBtn = (RadioButton) findViewById(R.id.wbcf_demo_none_src_btn);
        idSrcBtn = (RadioButton) findViewById(R.id.wbcf_demo_id_src_btn);

        if (isShowSuccess) {
            showSuccessPage.setCheck(true);
        } else {
            showSuccessPage.setCheck(false);
        }
        showSuccessPage.SetOnChangedCallBack(new SlipButton.onChangedCallBack() {
            @Override
            public void OnChanged(boolean CheckState) {
                isShowSuccess = CheckState;
            }
        });

        if (isShowFail) {
            showFailPage.setCheck(true);
        } else {
            showFailPage.setCheck(false);
        }
        showFailPage.SetOnChangedCallBack(new SlipButton.onChangedCallBack() {
            @Override
            public void OnChanged(boolean CheckState) {
                isShowFail = CheckState;
            }
        });

        if (isRecord) {
            recordVideo.setCheck(true);
        } else {
            recordVideo.setCheck(false);
        }
        recordVideo.SetOnChangedCallBack(new SlipButton.onChangedCallBack() {
            @Override
            public void OnChanged(boolean CheckState) {
                isRecord = CheckState;
            }
        });

        if (isEnableCloseEyes) {
            closeEyes.setCheck(true);
        } else {
            closeEyes.setCheck(false);
        }
        closeEyes.SetOnChangedCallBack(new SlipButton.onChangedCallBack() {
            @Override
            public void OnChanged(boolean CheckState) {
                isEnableCloseEyes = CheckState;
            }
        });

        if (isPlayVoice) {
            playVoice.setCheck(true);
        } else {
            playVoice.setCheck(false);
        }
        playVoice.SetOnChangedCallBack(new SlipButton.onChangedCallBack() {
            @Override
            public void OnChanged(boolean CheckState) {
                isPlayVoice = CheckState;
            }
        });

        if (colorMode.equals(WbCloudFaceContant.WHITE)) {
            colorChoose.setCheck(true);
        } else if (colorMode.equals(WbCloudFaceContant.BLACK)) {
            colorChoose.setCheck(false);
        }
        colorChoose.SetOnChangedCallBack(new SlipButton.onChangedCallBack() {
            @Override
            public void OnChanged(boolean CheckState) {
                if (CheckState) {
                    colorMode = WbCloudFaceContant.WHITE;
                } else {
                    colorMode = WbCloudFaceContant.BLACK;
                }
            }
        });

        if (WbCloudFaceContant.ID_CARD.equals(compareType)) {
            idSrcBtn.setChecked(true);
        } else if (compareType.equals(WbCloudFaceContant.NONE)) {
            noneSrcBtn.setChecked(true);
        }
        srcChoose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.wbcf_demo_id_src_btn) {
                    compareType = WbCloudFaceContant.ID_CARD;
                } else if (checkedId == R.id.wbcf_demo_none_src_btn) {
                    compareType = WbCloudFaceContant.NONE;
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();

                // 监听到返回按钮点击事件
                data.putExtra(WbCloudFaceContant.SHOW_SUCCESS_PAGE, isShowSuccess);
                data.putExtra(WbCloudFaceContant.SHOW_FAIL_PAGE, isShowFail);
                data.putExtra(WbCloudFaceContant.VIDEO_UPLOAD, isRecord);
                data.putExtra(WbCloudFaceContant.ENABLE_CLOSE_EYES, isEnableCloseEyes);
                data.putExtra(WbCloudFaceContant.PLAY_VOICE, isPlayVoice);
                data.putExtra(WbCloudFaceContant.COMPARE_TYPE, compareType);
                data.putExtra(WbCloudFaceContant.COLOR_MODE, colorMode);

                setResult(RESULT_OK, data);
                SettingActivity.this.finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent data = new Intent();

            // 监听到返回按钮点击事件
            data.putExtra(WbCloudFaceContant.SHOW_SUCCESS_PAGE, isShowSuccess);
            data.putExtra(WbCloudFaceContant.SHOW_FAIL_PAGE, isShowFail);
            data.putExtra(WbCloudFaceContant.VIDEO_UPLOAD, isRecord);
            data.putExtra(WbCloudFaceContant.ENABLE_CLOSE_EYES, isEnableCloseEyes);
            data.putExtra(WbCloudFaceContant.PLAY_VOICE, isPlayVoice);
            data.putExtra(WbCloudFaceContant.COMPARE_TYPE, compareType);
            data.putExtra(WbCloudFaceContant.COLOR_MODE, colorMode);

            setResult(RESULT_OK, data);
            SettingActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
