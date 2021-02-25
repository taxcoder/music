package com.tanx.music.service.collectionSong;

import com.tanx.music.dao.CollectionSongMapper;
import com.tanx.music.pojo.model.CollectionSong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CollectionSongImpl implements ICollectionSong{

    @Autowired
    private CollectionSongMapper collectionSongMapper;

    @Override
    public int addLikeMusic(String collectionSongId,String songId,String userId) {
        Integer integer = collectionSongMapper.addLikeMusic(collectionSongId,songId, userId);
        return integer == null ? 0 :integer;
    }

    @Override
    public int deleteLikeMusic(String songId,String userId) {
        Integer integer = collectionSongMapper.deleteLikeMusic(songId, userId);
        return integer == null ? 0 :integer;
    }

    @Override
    public List<CollectionSong> likeMusic(String userId) {
        return collectionSongMapper.likeMusic(userId);
    }

    @Override
    public CollectionSong selectCollectionSong(String songId, String userId) {
        return collectionSongMapper.selectCollectionSong(songId,userId);
    }
}
