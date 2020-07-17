package com.asg.root.salescrm.Views;

import org.json.JSONObject;

public interface DataView {

    void onDataFetched(JSONObject jsonObject);
    void onDataFetchedError(String err);
    void showProgress();
    void hideProgress();
}
