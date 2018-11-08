package com.nor.yandex.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.nor.yandex.model.History;

import java.util.List;

@Dao
public interface HistoryDao {
    /**
     * truy vấn insert
     *
     * @param history object thêm mới
     */
    @Insert
    void insert(History history);

    /**
     * truy vấn update
     *
     * @param history object update
     */
    @Update
    void update(History history);

    /**
     * truy vấn delete
     *
     * @param history object delete
     */
    @Delete
    void delete(History history);

    /**
     * lấy danh sách history
     */
    @Query("select * from history order by id desc")
    List<History> getHistories();

    /**
     * lấy danh sách favorite
     */
    @Query("select * from history where favorite = :favorite order by id desc")
    List<History> getFavorite(boolean favorite);
}
