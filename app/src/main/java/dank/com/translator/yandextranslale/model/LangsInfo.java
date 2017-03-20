package dank.com.translator.yandextranslale.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class LangsInfo {

    @SerializedName("langs")
    @Expose
    private HashMap<String, String> langs = null;

    @SerializedName("code")
    @Expose
    private Integer code;

    public HashMap<String, String> getLangs() {
        return langs;
    }

    public void setLangs(HashMap<String, String> langs) {
        this.langs = langs;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


}
