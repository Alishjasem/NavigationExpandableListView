package com.example.navigationdarawerexpandablelistview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.navigationdarawerexpandablelistview.ui.MenuModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void prepareMenuData() {

        MenuModel menuModel = new MenuModel("Home", true, false,R.drawable.ic_arrow_down); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        menuModel = new MenuModel("Programmers", true, true,R.drawable.ic_arrow_down); //Menu of Java Tutorials
        headerList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel("Shisha", false, false,R.drawable.ic_menu_gallery);
        childModelsList.add(childModel);

        childModel = new MenuModel("Food", false, false,R.drawable.ic_menu_slideshow);
        childModelsList.add(childModel);

        childModel = new MenuModel("Health", false, false,R.drawable.ic_menu_camera);
        childModelsList.add(childModel);


        if (menuModel.hasChildren) {
            Log.d("API123", "here");
            childList.put(menuModel, childModelsList);
        }

        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("Python Tutorials", true, true,R.drawable.ic_arrow_down); //Menu of Python Tutorials
        headerList.add(menuModel);
        childModel = new MenuModel("Python AST – Abstract Syntax Tree", false, false,R.drawable.ic_menu_camera);
        childModelsList.add(childModel);

        childModel = new MenuModel("Python Fractions", false, false,R.drawable.ic_menu_slideshow);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
    }

    private void populateExpandableList() {

        expandableListAdapter = new com.example.navigationdarawerexpandablelistview.ui.ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        /*WebView webView = findViewById(R.id.webView);
                        webView.loadUrl(headerList.get(groupPosition).url);
                        onBackPressed();*/
                        Toast.makeText(MainActivity.this, "Group : " + headerList.get(groupPosition).menuName, Toast.LENGTH_SHORT).show();
                    }
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    Toast.makeText(MainActivity.this, "Child : " + model.menuName, Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });

    }

    public boolean toggleArrow(ImageView imageView) {
        if (imageView.getRotation() == 0) {
            imageView.animate().setDuration(200).rotation(180);
            return true;
        } else {
            imageView.animate().setDuration(200).rotation(0);
            return false;
        }
    }
}
