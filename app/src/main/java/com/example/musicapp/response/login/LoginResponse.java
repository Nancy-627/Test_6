package com.example.musicapp.response.login;

import android.accounts.Account;

import com.example.musicapp.response.common.BindingsItem;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;


@Data
public class LoginResponse implements Serializable {

    @SerializedName("code")
    private int code;

    @SerializedName("loginType")
    private int loginType;

    @SerializedName("profile")
    private Profile profile;

    @SerializedName("bindings")
    private List<BindingsItem> bindings;

    @SerializedName("account")
    private Account account;
}
