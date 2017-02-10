package com.rakesh.hashkeynretrofit.retrofit.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 10-02-2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "channelName",
        "imgUrl",
        "simgUrl"
})
public class Channel implements Parcelable {

    @JsonProperty("channelName")
    private String channelName;
    @JsonProperty("imgUrl")
    private String imgUrl;
    @JsonProperty("simgUrl")
    private String simgUrl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Parcelable.Creator<Channel> CREATOR = new Creator<Channel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Channel createFromParcel(Parcel in) {
            Channel instance = new Channel();
            instance.channelName = ((String) in.readValue((String.class.getClassLoader())));
            instance.imgUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.simgUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object>) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Channel[] newArray(int size) {
            return (new Channel[size]);
        }

    };

    @JsonProperty("channelName")
    public String getChannelName() {
        return channelName;
    }

    @JsonProperty("channelName")
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @JsonProperty("imgUrl")
    public String getImgUrl() {
        return imgUrl;
    }

    @JsonProperty("imgUrl")
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @JsonProperty("simgUrl")
    public String getSimgUrl() {
        return simgUrl;
    }

    @JsonProperty("simgUrl")
    public void setSimgUrl(String simgUrl) {
        this.simgUrl = simgUrl;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(channelName);
        dest.writeValue(imgUrl);
        dest.writeValue(simgUrl);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return 0;
    }
}
