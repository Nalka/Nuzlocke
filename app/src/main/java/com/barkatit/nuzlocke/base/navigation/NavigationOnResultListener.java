package com.barkatit.nuzlocke.base.navigation;

import android.os.Bundle;

public interface NavigationOnResultListener {
    void onResult(int result, int requestCode, Bundle data);
}
