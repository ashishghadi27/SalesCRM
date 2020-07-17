package com.asg.root.salescrm.presenter;

import com.asg.root.salescrm.Utils.Constants;
import com.asg.root.salescrm.Views.DataView;
import com.asg.root.salescrm.network.AppNetworkResponse;
import com.asg.root.salescrm.network.Volley;

import org.json.JSONObject;

public class VitalFunctionsPresenter implements AppNetworkResponse {

    Volley volley;
    DataView view;

    public VitalFunctionsPresenter(DataView view){
        this.view=view;
        this.volley= Volley.getInstance();
    }

    public void addSubscriber(JSONObject jsonObject){
        volley.post(Constants.apiAddSubscriber,jsonObject,this, Constants.ADD_SUBS_REQUEST_CODE);
    }

    public void addBouncer(JSONObject jsonObject){
        volley.post(Constants.apiAddBouncer,jsonObject,this, Constants.ADD_BOUNCER_REQUEST_CODE);
    }

    public void addConfig(JSONObject jsonObject){
        volley.post(Constants.apiAddConfig,jsonObject,this, Constants.ADD_CONFIG_REQUEST_CODE);
    }

    public void addCustomFields(JSONObject jsonObject){
        volley.post(Constants.apiAddCustomField,jsonObject,this, Constants.ADD_CUSTOM_FIELD_REQUEST_CODE);
    }

    public void addUSer(JSONObject jsonObject){
        volley.post(Constants.apiAddUser,jsonObject,this, Constants.ADD_USER_REQUEST_CODE);
    }

    @Override
    public void onResSuccess(JSONObject jsonObject, int reqCode) {
        switch (reqCode){
            case Constants.ADD_SUBS_REQUEST_CODE:
                view.onDataFetched(jsonObject);
                break;
            case Constants.ADD_BOUNCER_REQUEST_CODE:
                view.onDataFetched(jsonObject);
                break;
            case Constants.ADD_CONFIG_REQUEST_CODE:
                view.onDataFetched(jsonObject);
                break;
            case Constants.ADD_CUSTOM_FIELD_REQUEST_CODE:
                view.onDataFetched(jsonObject);
                break;
            case Constants.ADD_USER_REQUEST_CODE:
                view.onDataFetched(jsonObject);
                break;
        }
    }

    @Override
    public void onResFailure(String errCode, String errMsg, int reqCode, JSONObject jsonObject) {
        switch (reqCode){
            case Constants.ADD_SUBS_REQUEST_CODE:
                view.onDataFetchedError(errMsg);
                break;
            case Constants.ADD_BOUNCER_REQUEST_CODE:
                view.onDataFetchedError(errMsg);
                break;
            case Constants.ADD_CONFIG_REQUEST_CODE:
                view.onDataFetchedError(errMsg);
                break;
            case Constants.ADD_CUSTOM_FIELD_REQUEST_CODE:
                view.onDataFetchedError(errMsg);
                break;
            case Constants.ADD_USER_REQUEST_CODE:
                view.onDataFetchedError(errMsg);
                break;
        }
    }
}
