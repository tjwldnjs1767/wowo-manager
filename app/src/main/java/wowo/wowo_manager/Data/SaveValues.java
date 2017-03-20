package wowo.wowo_manager.Data;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by dsm037 on 2016-11-24.
 */

public class SaveValues extends Application {

    public String id;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void setId (String id) {
        this.id = id;
    }

    public String getId () {
        return id;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
