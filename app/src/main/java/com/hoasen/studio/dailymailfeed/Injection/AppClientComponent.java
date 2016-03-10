package com.hoasen.studio.dailymailfeed.Injection;

import com.hoasen.studio.dailymailfeed.Networks.DMNetworkClient;

/**
 * Created by Harry Nguyen on 09-Mar-16.
 */
public interface AppClientComponent {
    void inject(DMNetworkClient client);
}
