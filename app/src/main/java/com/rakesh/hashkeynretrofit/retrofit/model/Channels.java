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
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 10-02-2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "channels",
        "lastUpdatedAt"
})
public class Channels implements Parcelable
{

    @JsonProperty("channels")
    private List<Channel> channels = null;
    @JsonProperty("lastUpdatedAt")
    private long lastUpdatedAt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Parcelable.Creator<Channels> CREATOR = new Creator<Channels>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Channels createFromParcel(Parcel in) {
            Channels instance = new Channels();
            in.readList(instance.channels, (Channel.class.getClassLoader()));
            instance.lastUpdatedAt = ((int) in.readValue((int.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Channels[] newArray(int size) {
            return (new Channels[size]);
        }

    }
            ;

    @JsonProperty("channels")
    public List<Channel> getChannels() {
        return channels;
    }

    @JsonProperty("channels")
    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    @JsonProperty("lastUpdatedAt")
    public long getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    @JsonProperty("lastUpdatedAt")
    public void setLastUpdatedAt(long lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
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
        dest.writeList(channels);
        dest.writeValue(lastUpdatedAt);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return 0;
    }

}
