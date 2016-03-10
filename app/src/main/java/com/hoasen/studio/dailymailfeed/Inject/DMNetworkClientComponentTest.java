package com.hoasen.studio.dailymailfeed.Inject;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Harry Nguyen on 09-Mar-16.
 */
@Singleton
@Component(modules = {DMNetworkClientModuleTest.class})
public interface DMNetworkClientComponentTest extends AppClientComponent {

}
