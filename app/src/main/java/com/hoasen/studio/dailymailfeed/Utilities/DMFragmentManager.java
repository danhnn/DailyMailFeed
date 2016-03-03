package com.hoasen.studio.dailymailfeed.Utilities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.view.View;

import com.hoasen.studio.dailymailfeed.R;

import java.util.HashMap;

/**
 * Created by Harry Nguyen on 01-Mar-16.
 */
public class DMFragmentManager {
    int holderContainerID;
    AppCompatActivity context;
    static DMFragmentManager instance = null;
    HashMap<String,Fragment> fragmentDictionary;
    FragmentManager fragmentManager;

    public static DMFragmentManager getInstance(){
        instance = instance == null ? new DMFragmentManager() : instance;
        return instance;
    }

    DMFragmentManager(){
        fragmentDictionary = new HashMap<>();
    }

    public void settupHolderContainer(int resID){
        holderContainerID = resID;
    }

    public void setContext(AppCompatActivity context){
       this.context = context;
        fragmentManager = context.getSupportFragmentManager();
    }

    public FragmentManager getFragmentManager(){
        return fragmentManager;
    }

    public void addFragment(String name, Fragment fragment){
        fragmentDictionary.put(name,fragment);
    }

    public Fragment getFragment(String name){
        return fragmentDictionary.get(name);
    }

    public void transactionTo(Fragment destinationFragment){
        goTo(destinationFragment);
    }

    public void transactionTo(String fragmentName){
        Fragment fragment = fragmentDictionary.get(fragmentName);
        goTo(fragment);
    }

    public void transactionWithAnimationTo(Fragment destinationFragment,View shareElement, String animName){
        goToWithSharedAnimation(destinationFragment,shareElement,animName);
    }

    public void transactionWithAnimationTo(String fragmentName,View shareElement, String animName){
        Fragment fragment = fragmentDictionary.get(fragmentName);
        goToWithSharedAnimation(fragment,shareElement,animName);
    }

     void goTo(Fragment destinationFragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        doTransaction(transaction,destinationFragment);
    }

    void goToWithSharedAnimation(Fragment destinationFragment, View shareElement, String animName){
        FragmentTransaction transaction = fragmentManager.beginTransaction().addSharedElement(shareElement,animName);
        doTransaction(transaction,destinationFragment);
    }

    void doTransaction(FragmentTransaction transaction,Fragment destinationFragment){
        transaction.replace(holderContainerID, destinationFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
