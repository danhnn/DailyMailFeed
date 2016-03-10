package com.hoasen.studio.dailymailfeed.Inject;

import com.hoasen.studio.dailymailfeed.Networks.DMNetworkClient;

/**
 * Created by Harry Nguyen on 09-Mar-16.
 */
public interface AppClientComponent {
    void inject(DMNetworkClient client);
}
