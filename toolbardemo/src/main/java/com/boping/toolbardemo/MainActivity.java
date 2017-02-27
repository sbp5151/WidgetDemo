package com.boping.toolbardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("App Title");
//        toolbar.setSubtitle("Sub Title");
        toolbar.setNavigationIcon(R.mipmap.navigation);
        toolbar.setBackgroundColor(getResources().getColor(R.color.material_blue_500));
//        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnMenuItemClickListener(mOnMenuItemClickListener);
    }

    Toolbar.OnMenuItemClickListener mOnMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int itemId = item.getItemId();
            if (itemId == R.id.action_item1) {
            } else if (itemId == R.id.action_item2) {
            } else if (itemId == R.id.action_notification) {
            } else if (itemId == R.id.action_search) {
            }
            return false;
        }
    };
}
