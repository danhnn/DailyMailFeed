package com.hoasen.studio.dailymailfeed;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.view.Window;


import com.hoasen.studio.dailymailfeed.AppIntro.AppIntroActivity;
import com.hoasen.studio.dailymailfeed.MainNews.MainNewsFragment;
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
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_news);
        context = getApplicationContext();

        setupFragmentManager();
        settupDrawer();
    }

    public Drawer getDrawer(){
        return result;
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
                .withBadge("7").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));
        PrimaryDrawerItem itemTutorial = new PrimaryDrawerItem().withName(R.string.drawer_item_tutorial).withIcon(GoogleMaterial.Icon.gmd_account_box);
        SecondaryDrawerItem itemGithub =  new SecondaryDrawerItem().withName(R.string.drawer_item_github).withIcon(FontAwesome.Icon.faw_github);

        //create the drawer and remember the `Drawer` result object
        result = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(this)
                .withToolbar(myToolbar)
                .addDrawerItems(
                        itemHome,
                        itemTutorial,
                        new DividerDrawerItem(),
                        itemGithub
                )
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    switch (position){
                        case 1:
                            DMFragmentManager.getInstance().transactionTo(ConstantValue.FRAGMENT_HOME);
                            break;
                        case 2:
                            launchAppintro();
                            break;
                        case 4:
                            openGithubPage();
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

    void launchAppintro(){
        Intent i = new Intent(MainNewsActivity.this, AppIntroActivity.class);
        startActivity(i);
    }

    void openGithubPage(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/harry-nguyen-88"));
        startActivity(browserIntent);
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
