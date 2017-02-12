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
 * Created by Admin on 12-02-2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(value = {
        "action",
        "status",
        "message"
})
public class Forgot implements Parcelable
{

    @JsonProperty("action")
    private String action;
    @JsonProperty("status")
    private String status;
    @JsonProperty("message")
    private String message;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Parcelable.Creator<Forgot> CREATOR = new Creator<Forgot>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Forgot createFromParcel(Parcel in) {
            Forgot instance = new Forgot();
            instance.action = ((String) in.readValue((String.class.getClassLoader())));
            instance.status = ((String) in.readValue((String.class.getClassLoader())));
            instance.message = ((String) in.readValue((String.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Forgot[] newArray(int size) {
            return (new Forgot[size]);
        }

    }
            ;

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
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
        dest.writeValue(action);
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return 0;
    }

}
