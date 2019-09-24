package com.aaron.customkeyboarddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.aaron.keyboardlibrary.KeyboardManager;

public class MainActivity extends AppCompatActivity {
    private KeyboardManager keyboardManager;
    private LinearLayout rootView;
    private ScrollView scrollView;
    private EditText edt_password; // 密码输入框
    private EditText edt_normal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootView = (LinearLayout) findViewById(R.id.rootView);
        scrollView = (ScrollView) findViewById(R.id.sv_main);
        edt_normal = (EditText) findViewById(R.id.edt_normal);
        edt_password = (EditText) findViewById(R.id.edt_password);
        initMoveKeyBoard();
    }

    private void initMoveKeyBoard() {

        keyboardManager = new KeyboardManager(this, rootView, scrollView)
                .setTitle("安全键盘")
                .setKeyboardType(KeyboardManager.INPUTTYPE_NUM)
                .create();
        keyboardManager.setUseCustomKeyboardEdittext(edt_password)
//                .setOtherEdittext(edt_normal)
                .setKeyBoardStateChangeListener(new KeyBoardStateListener())
                .setInputOverListener(new inputOverListener());
    }

    class KeyBoardStateListener implements KeyboardManager.KeyBoardStateChangeListener {

        @Override
        public void KeyBoardStateChange(int state, EditText editText) {
//            System.out.println("state" + state);
//            System.out.println("editText" + editText.getText().toString());
        }
    }

    class inputOverListener implements KeyboardManager.InputFinishListener {

        @Override
        public void inputHasOver(int onclickType, EditText editText) {
//            System.out.println("onclickType" + onclickType);
//            System.out.println("editText" + editText.getText().toString());
        }
    }


    /**
     * 处理返回按钮隐藏输入框逻辑
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (keyboardManager.isShow) {
                keyboardManager.hideAllKeyBoard();
            } else {
                return super.onKeyDown(keyCode, event);
            }
            return false;
        } else
            return super.onKeyDown(keyCode, event);
    }
}
