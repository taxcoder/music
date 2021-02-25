package com.tanx.music.service.collectionSong;

import com.tanx.music.pojo.model.CollectionSong;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ICollectionSong {

    /**
     * 将歌曲加入收藏
     * @param collectionSongId 歌曲收藏ID
     * @param songId 歌曲ID
     * @param userId 用户ID
     * @return 返回加入的结果
     */
    int addLikeMusic(String collectionSongId,String songId,String userId);

    /**
     * 将歌曲从收藏中删除
     * @param songId 歌曲ID
     * @param userId 用户ID
     * @return 返回删除的结果
     */
    int deleteLikeMusic(String songId,String userId);

    /**
     * 获取所有的收藏歌曲信息
     * @param userId 用户ID
     * @return 返回所有的收藏歌曲信息
     */
    List<CollectionSong> likeMusic(String userId);

    /**
     * 查询歌曲是否已经添加
     * @param songId 歌曲ID
     * @param userId 用户ID
     * @return 返回查询的结果
     */
    CollectionSong selectCollectionSong(String songId,String userId);
}
