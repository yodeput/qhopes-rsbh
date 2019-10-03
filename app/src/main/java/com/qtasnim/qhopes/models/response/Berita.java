package com.qtasnim.qhopes.models.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Berita implements Parcelable {
    @Expose
    @SerializedName("datetime")
    private String datetime;
    @Expose
    @SerializedName("image")
    private String image;
    @Expose
    @SerializedName("content")
    private String content;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("id")
    private int id;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Berita(int id, String datetime, String title, String content, String image){
        this.image=image;
        this.content=content;
        this.title=title;
        this.id=id;
        this.datetime=datetime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(image);
        dest.writeString(datetime);
    }

    public Berita(){
    }

    protected Berita(Parcel in){
        id = in.readInt();
        title = in.readString();
        content = in.readString();
        image = in.readString();
        datetime = in.readString();
    }

    public static final Creator<Berita> CREATOR = new Creator<Berita>() {
        @Override
        public Berita createFromParcel(Parcel parcel) {
            return new Berita(parcel);
        }

        @Override
        public Berita[] newArray(int i) {
            return new Berita[0];
        }
    };

}
