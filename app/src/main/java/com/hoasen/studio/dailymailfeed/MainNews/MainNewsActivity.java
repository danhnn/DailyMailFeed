package com.hoasen.studio.dailymailfeed.MainNews;

import android.graphics.Color;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.view.Window;


import com.hoasen.studio.dailymailfeed.NewsDetail.NewDetailFragment;
import com.hoasen.studio.dailymailfeed.R;
import com.hoasen.studio.dailymailfeed.Utilities.ConstantValue;
import com.hoasen.studio.dailymailfeed.Utilities.DMFragmentManager;
import com.hoasen.studio.dailymailfeed.Utilities.DMLog;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainNewsActivity extends AppCompatActivity {

    protected Drawer result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_news);

        setupFragmentManager();
        settupDrawer();
    }

    void setupFragmentManager(){
        DMFragmentManager.getInstance().setContext(this);
        DMFragmentManager.getInstance().settupHolderContainer(R.id.container);
        DMFragmentManager.getInstance().addFragment(ConstantValue.FRAGMENT_HOME,new MainNewsFragment());
        DMFragmentManager.getInstance().addFragment(ConstantValue.FRAGMENT_DETAIL,new NewDetailFragment());
    }

    void settupDrawer(){
        new DrawerBuilder().withActivity(this).build();

        //if you want to update the items at a later time it is recommended to keep it in a variable

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.mipmap.ic_user_background_second)
                .addProfiles(
                        new ProfileDrawerItem().withName("Harry Nguyen").withEmail("dnncntt@gmail.com").withIcon(getResources().getDrawable(R.mipmap.profile1))
                )
                .withOnAccountHeaderListener((view, profile, currentProfile) -> false)
                .build();

        PrimaryDrawerItem itemHome = new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(GoogleMaterial.Icon.gmd_wb_sunny)
                .withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));
        PrimaryDrawerItem itemAbout = new PrimaryDrawerItem().withName(R.string.drawer_item_about).withIcon(GoogleMaterial.Icon.gmd_account_box);
        PrimaryDrawerItem itemTutorial = new PrimaryDrawerItem().withName(R.string.drawer_item_tutorial).withIcon(GoogleMaterial.Icon.gmd_account_box);
        SecondaryDrawerItem itemSetting =  new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_github);

        //create the drawer and remember the `Drawer` result object
        result = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(this)
                .withToolbar(myToolbar)
                .addDrawerItems(
                        itemHome,
                        itemAbout,
                        itemTutorial,
                        new DividerDrawerItem(),
                        itemSetting
                )
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    switch (position){
                        case 1:
                            DMFragmentManager.getInstance().transactionTo(ConstantValue.FRAGMENT_HOME);
                            break;
                        default:
                            DMFragmentManager.getInstance().transactionTo(ConstantValue.FRAGMENT_HOME);
                            break;
                    }

                    this.result.closeDrawer();
                    return true;
                })
                .build();
        result.setSelection(1,true);
    }

    @Override
    public void onBackPressed() {

        if(DMFragmentManager.getInstance().getFragmentManager().getBackStackEntryCount() == 1){
            finish();
        }else{
            DMFragmentManager.getInstance().getFragmentManager().popBackStack();
        }

    }
}
