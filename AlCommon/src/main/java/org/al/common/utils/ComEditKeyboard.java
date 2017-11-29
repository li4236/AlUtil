package org.al.common.utils;

import android.content.Context;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 键盘设置
 * shu
 * 2016/11/5.
 */
public class ComEditKeyboard {

    private OnEditorActionListener onEditorActionListener;

    public void setActionDone(final EditText... editArray) {

        setEditKeyboard(EditorInfo.IME_ACTION_DONE, editArray);//最后完成
    }
    public void setActionSearch(final EditText... editArray) {

        setEditKeyboard(EditorInfo.IME_ACTION_SEARCH, editArray);//最后搜索
    }
    public void setAction(int editorInfo,final EditText... editArray) {

        setEditKeyboard(editorInfo,editArray);//自己传入
    }
    /**
     * xml: actionUnspecified  未指定，对应常量EditorInfo.IME_ACTION_UNSPECIFIED.效果：
     * xml: actionNone 没有动作,对应常量EditorInfo.IME_ACTION_NONE 效果：
     * xml: actionGo 去往，对应常量EditorInfo.IME_ACTION_GO 效果：
     * xml: actionSearch 搜索，对应常量EditorInfo.IME_ACTION_SEARCH 效果：
     * xml: actionSend 发送，对应常量EditorInfo.IME_ACTION_SEND 效果：
     * xml: actionNext 下一个，对应常量EditorInfo.IME_ACTION_NEXT 效果：
     * xml: actionDone 完成，对应常量EditorInfo.IME_ACTION_DONE 效果：
     * @param editorInfo
     * @param editArray
     */
    private void setEditKeyboard(final int editorInfo,final EditText []editArray){

        for (int a = 0; a < editArray.length; a++) {
            if (a < editArray.length - 1) {
                editArray[a].setImeOptions(EditorInfo.IME_ACTION_NEXT);
                final int next = a + 1;
                editArray[a].setOnEditorActionListener(new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                        editArray[next].setFocusable(true);
                        editArray[next].setFocusableInTouchMode(true);
                        editArray[next].requestFocus();
                        return true;

                    }
                });
            } else {
                editArray[a].setImeOptions(editorInfo);
                editArray[a].setOnEditorActionListener(new EditText.OnEditorActionListener() {

                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                      //  if(actionId ==editorInfo){
                            // 隐藏软键盘
                            InputMethodManager imm = (InputMethodManager) v
                                    .getContext().getSystemService(
                                            Context.INPUT_METHOD_SERVICE);
                            if (imm.isActive()) {
                                imm.hideSoftInputFromWindow(
                                        v.getApplicationWindowToken(), 0);
                            }

                            if(onEditorActionListener!=null){
                                onEditorActionListener.onEditorAction();
                            }

                            return true;
                      //  }
                       // return false;
                    }
                });

            }
        }
    }

    /**
     * 最后一步点击事件
     * @param onEditorActionListener
     */
    public void setOnEditorActionListener(OnEditorActionListener onEditorActionListener){
        this.onEditorActionListener=onEditorActionListener;
    }
    public interface OnEditorActionListener{
        public void onEditorAction();
    }
}
