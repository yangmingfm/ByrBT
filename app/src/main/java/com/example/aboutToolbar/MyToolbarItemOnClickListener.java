package com.example.aboutToolbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.Check.ClearCookie;
import com.example.aboutHTML.SendSuggestion;
import com.example.aboutShare.SharingToWX;
import com.example.aboutStringFilter.CookieStr2Id;
import com.example.cakes.FragmentSelsector;
import com.example.getData.GetLocalData;
import com.example.ymjkmaster.byrbta.MainActivity;
import com.example.ymjkmaster.byrbta.R;
import com.example.ymjkmaster.byrbta.StartActicity;

/**
 * Created by ymjkMaster on 2015/5/28 0028.
 */
public class MyToolbarItemOnClickListener implements Toolbar.OnMenuItemClickListener {
    private Context context;
    FragmentSelsector mSelector = new FragmentSelsector();
    private String tag = "==MyToolbarItemOnClickListener==";
    public MyToolbarItemOnClickListener(Context context){this.context = context;}
    private int order = 0;
    private String temp="";
    private TextView mIndicator;
    private MaterialDialog dialog;
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                         dialog = new MaterialDialog.Builder(context).title("标签设置")
                        .customView(R.layout.fragment_selector_layout, false)
                        .positiveText("欧了")
                        .negativeText("取消")
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                if (checkList(mSelector.getList().size())) {
                                    GetLocalData getLocalData = new GetLocalData();
                                    GetLocalData.saveSelectorList(mSelector.getList(), context);
                                    getLocalData.getBeans(context);
                                    mSelector.clearBeans();
                                    Intent intent = new Intent(context, MainActivity.class);
                                    context.startActivity(intent);
                                    Activity activity = (Activity)context;
                                    activity.finish();
                                } else {
                                    mSelector.clearBeans();
                                    Toast.makeText(context, "长度不对呀亲", Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();
                            }

                            @Override
                            public void onNegative(MaterialDialog dialog) {
                                super.onNegative(dialog);
                            }
                        })
                        .build();
                initCheckBox(dialog);
                dialog.show();
                break;
//            case R.id.action_aboutme:
//                dialog = new MaterialDialog.Builder(context).title("软件&作者")
//                        .customView(R.layout.about_author_layout, false)
//                        .positiveText("好")
//                        .callback(new MaterialDialog.ButtonCallback() {
//                            @Override
//                            public void onPositive(MaterialDialog dialog) {
//                                dialog.dismiss();
//                            }
//                        })
//                        .build();
//                dialog.show();
//                break;
            case R.id.action_introduction:
                dialog = new MaterialDialog.Builder(context).title("使用说明")
                        .customView(R.layout.introduction_layout, false)
                        .positiveText("叼！")
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                dialog.dismiss();
                            }
                        })
                        .build();
                dialog.show();
                break;
            case R.id.action_logout:
                ClearCookie.clearCookie(context);
                Intent intent = new Intent(context, StartActicity.class);
                context.startActivity(intent);
                Activity activity = (Activity)context;
                activity.finish();
                break;
            case R.id.action_suggestion:
                dialog = new MaterialDialog.Builder(context).title("建议&反馈")
                        .customView(R.layout.suggesttion_layout, false)
                        .positiveText("发之")
                        .negativeText("取消")
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                EditText editSuggestion = (EditText)dialog.getCustomView().findViewById(R.id.edittext_suggesttion);
                                SendSuggestion sendSuggestion = new SendSuggestion(CookieStr2Id.cookie2id(GetLocalData.getCookie(context)),editSuggestion.getText().toString(),dialog);
                                sendSuggestion.startSendSuggestion(context);
                            }
                            @Override
                            public void onNegative(MaterialDialog dialog) {
                                dialog.dismiss();
                            }
                        })

                        .build();
                dialog.show();
                break;
            case R.id.action_share:
                SharingToWX sharingToWX = new SharingToWX(context);
                sharingToWX.setReq();
                break;
            default:
                break;
        }
        return true;
    }
    public void initCheckBox(MaterialDialog dialog){
        CheckBox checkBox0;
        CheckBox checkBox1;
        CheckBox checkBox2;
        CheckBox checkBox3;
        CheckBox checkBox4;
        CheckBox checkBox5;
        CheckBox checkBox6;
        CheckBox checkBox7;
        CheckBox checkBox8;
        CheckBox checkBox9;
        CheckBox checkBox10;
        CheckBox checkBox11;
        CheckBox checkBox12;
        CheckBox checkBox13;
        checkBox0 =(CheckBox)dialog.getCustomView().findViewById(R.id.checkbox0);
        checkBox1 =(CheckBox)dialog.getCustomView().findViewById(R.id.checkbox1);
        checkBox2 =(CheckBox)dialog.getCustomView().findViewById(R.id.checkbox2);
        checkBox3 =(CheckBox)dialog.getCustomView().findViewById(R.id.checkbox3);
        checkBox4 =(CheckBox)dialog.getCustomView().findViewById(R.id.checkbox4);
        checkBox5 =(CheckBox)dialog.getCustomView().findViewById(R.id.checkbox5);
        checkBox6 =(CheckBox)dialog.getCustomView().findViewById(R.id.checkbox6);
        checkBox7 =(CheckBox)dialog.getCustomView().findViewById(R.id.checkbox7);
        checkBox8 =(CheckBox)dialog.getCustomView().findViewById(R.id.checkbox8);
        checkBox9 =(CheckBox)dialog.getCustomView().findViewById(R.id.checkbox9);
        checkBox10 =(CheckBox)dialog.getCustomView().findViewById(R.id.checkbox10);
        checkBox11 =(CheckBox)dialog.getCustomView().findViewById(R.id.checkbox11);
        checkBox12 =(CheckBox)dialog.getCustomView().findViewById(R.id.checkbox12);
        checkBox13 =(CheckBox)dialog.getCustomView().findViewById(R.id.checkbox13);
        checkBox0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked)
                   mSelector.addBean(0);
                else
                   mSelector.removeBean(0);

               }
        });
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    mSelector.addBean(1);
                else
                    mSelector.removeBean(1);
            }
        });
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    mSelector.addBean(2);
                else
                    mSelector.removeBean(2);
            }
        });
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    mSelector.addBean(3);
                else
                    mSelector.removeBean(3);
            }
        });
        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    mSelector.addBean(4);
                else
                    mSelector.removeBean(4);
            }
        });
        checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    mSelector.addBean(5);
                else
                    mSelector.removeBean(5);
            }
        });
        checkBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    mSelector.addBean(6);
                else
                    mSelector.removeBean(6);
            }
        });
        checkBox7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    mSelector.addBean(7);
                else
                    mSelector.removeBean(7);
            }
        });
        checkBox8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    mSelector.addBean(8);
                else
                    mSelector.removeBean(8);
            }
        });
        checkBox9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    mSelector.addBean(9);
                else
                    mSelector.removeBean(9);
            }
        });
        checkBox10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    mSelector.addBean(10);
                else
                    mSelector.removeBean(10);
            }
        });
        checkBox11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    mSelector.addBean(11);
                else
                    mSelector.removeBean(11);
            }
        });
        checkBox12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    mSelector.addBean(12);
                else
                    mSelector.removeBean(12);
            }
        });
        checkBox13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    mSelector.addBean(13);
                else
                    mSelector.removeBean(13);
            }
        });
    }
    public boolean checkList(int  m){
        if(m>=3&&m<=5) {
            return true;
        }
        else
            return false;
    }
    public void refreshIndicator(FragmentSelsector mSelector,MaterialDialog dialog){
        for(int i=0;i<mSelector.getList().size();i++)
        {
            temp+=mSelector.getList().get(i)+" ";
        }
        mIndicator = (TextView)dialog.getCustomView().findViewById(R.id.indicator1);
        mIndicator.setText(temp);
    }
}
