package com.nor.yandex.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "history")
public class History {
    @PrimaryKey(autoGenerate = true)
    private long id;
    // text input
    @ColumnInfo
    private String strFrom;
    // text dịch
    @ColumnInfo
    private String strTo;
    // ngôn ngữ dịch
    @ColumnInfo
    private String lang;
    // thời điểm dịch
    @ColumnInfo
    private long timeTranslated = System.currentTimeMillis();
    // true nếu là favorite
    // false là history
    @ColumnInfo
    private boolean favorite;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStrFrom() {
        return strFrom;
    }

    public void setStrFrom(String strFrom) {
        this.strFrom = strFrom;
    }

    public String getStrTo() {
        return strTo;
    }

    public void setStrTo(String strTo) {
        this.strTo = strTo;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public long getTimeTranslated() {
        return timeTranslated;
    }

    public void setTimeTranslated(long timeTranslated) {
        this.timeTranslated = timeTranslated;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
