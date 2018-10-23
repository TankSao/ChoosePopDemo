package com.example.administrator.choosepopdemo;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.choosepopdemo.Utils.DensityUtil;
import com.example.administrator.choosepopdemo.Utils.DialogCircle;
import com.example.administrator.choosepopdemo.Utils.MyListViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView show;
    private ArrayList<String> listClass;//班级
    private ArrayList<String> listSubject;//科目
    private TextView btnQd;
    private RelativeLayout llClassChoose;
    private RelativeLayout llSubjectChoose;
    private TextView bjTv, kmTv;
    private TextView close;
    private DialogCircle newDialog;
    private Dialog bottomDialog;
    private TextView czTV;
    private ListView xkSxLv;
    int width,height;
    private String subjectName,className;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initClass();
        initSubject();
        show = (TextView)findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPop();
            }
        });
    }

    private void showPop() {
        className = "";
        subjectName = "";
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int wscreenWidth = (int) (width / density);  // 屏幕宽度(dp)
        int screenHeight = (int) (height / density);// 屏幕高度(dp)
        View view = View.inflate(this, R.layout.pop_shang_ke, null);
        close = view.findViewById(R.id.nosure);
        btnQd = view.findViewById(R.id.sure);
        llClassChoose = view.findViewById(R.id.ll_class_choose);
        llSubjectChoose = view.findViewById(R.id.ll_subject_choose);
        bjTv = view.findViewById(R.id.xz_bj);
        kmTv = view.findViewById(R.id.xz_km);
        llClassChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPop(0);
            }
        });
        llSubjectChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPop(1);
            }
        });
        newDialog = new DialogCircle(this, DensityUtil.dip2px(this, wscreenWidth / 4 * 3), DensityUtil.dip2px(this, screenHeight / 2), view,
                R.style.dialog2);
        newDialog.setCancelable(false);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newDialog.dismiss();
            }
        });
        btnQd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(className) || TextUtils.isEmpty(subjectName)) {
                    Toast.makeText(MainActivity.this, "请选择班级和科目", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, className+":"+subjectName, Toast.LENGTH_SHORT).show();
                    newDialog.dismiss();
                }
            }
        });
        newDialog.show();
    }
    private void initSubject() {
        listSubject = new ArrayList<>();
        listSubject.add("语文");
        listSubject.add("数学");
        listSubject.add("英语");
        listSubject.add("化学");
        listSubject.add("物理");
        listSubject.add("地理");
        listSubject.add("政治");
        listSubject.add("历史");
        listSubject.add("生物");
        listSubject.add("道德与法治");
    }

    private void initClass() {
        listClass = new ArrayList<>();
        listClass.add("一年级一班");
        listClass.add("一年级二班");
        listClass.add("一年级三班");
        listClass.add("一年级四班");
        listClass.add("一年级五班");
        listClass.add("一年级六班");
    }
    private void initPop(int type) {
        bottomDialog = new Dialog(this, R.style.BottomDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.pop_nj, null);
        xkSxLv = view.findViewById(R.id.xk_sx_lv);
        czTV = view.findViewById(R.id.xk_sx_nj);
        bottomDialog.setContentView(view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        view.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        if (type == 0) {
            showNj();
        } else {
            showKm();
        }
    }
    private void showKm() {
        try {
            MyListViewAdapter myListViewAdapter = new MyListViewAdapter(this, listSubject, "km");
            xkSxLv.setAdapter(myListViewAdapter);
            myListViewAdapter.notifyDataSetChanged();
            xkSxLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    bottomDialog.dismiss();
                    kmTv.setText(listSubject.get(i));
                    subjectName = listSubject.get(i);

                }
            });
            bottomDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        czTV.setText("科目");
    }

    private void showNj() {
        {
            try {
                MyListViewAdapter myListViewAdapter = new MyListViewAdapter(this, listClass, "nj");
                xkSxLv.setAdapter(myListViewAdapter);
                myListViewAdapter.notifyDataSetChanged();
                xkSxLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        bottomDialog.dismiss();
                        bjTv.setText(listClass.get(i));
                        className = listClass.get(i);

                    }
                });
                bottomDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            czTV.setText("班级");
        }
    }
}
