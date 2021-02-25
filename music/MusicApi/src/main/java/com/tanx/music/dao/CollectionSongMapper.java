package com.tanx.music.dao;


import com.tanx.music.pojo.model.CollectionSong;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CollectionSongMapper {

    /**
     * 将歌曲加入收藏
     * @param songId 歌曲ID
     * @param userId 用户ID
     * @return 返回加入的结果
     */
    Integer addLikeMusic(@Param("collection_song_id")String collectionSongId,@Param("songId")String songId,@Param("userId")String userId);

    /**
     * 将歌曲从收藏中删除
     * @param songId 歌曲ID
     * @param userId 用户ID
     * @return 返回删除的结果
     */
    Integer deleteLikeMusic(@Param("songId") String songId,@Param("userId") String userId);

    /**
     * 获取所有的收藏歌曲信息
     * @param userId 用户ID
     * @return 返回所有的收藏歌曲信息
     */
    List<CollectionSong> likeMusic(@Param("userId")String userId);

    /**
     * 查询歌曲是否已经添加
     * @param songId 歌曲ID
     * @param userId 用户ID
     * @return 返回查询的结果
     */
    CollectionSong selectCollectionSong(@Param("songId")String songId,@Param("userId")String userId);
}
