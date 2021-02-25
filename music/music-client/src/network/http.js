import {get, post, put, del, all} from './request'

// index
export const index = {
    "regionSong": value => get.Get('/index/song', {params: {"info": value}}),
    "indexAll": () => {
        const song = get.Get("/index/song", {params: {"info": 'new'}})
        const region = get.Get("/index/region", {})
        const songSheet = get.Get("/index/songSheet", {})
        const singer = get.Get("/index/singer", {})
        return all.indexAll([songSheet, song, singer, region])
    }
}

// singers
export const singers = {
    "regionSinger": get.Get("/singers/region", {}),
    "allSingers": (regionId, status, abbreviation, current) => get.Get("/singers/allSinger", {
        params: {
            "regionId": regionId,
            "singerStatus": status,
            "abbreviation": abbreviation,
            "current": current
        }
    }),
}

// songSheets
export const songSheets = {
    "recommendAll": (type, current) => {
        let songSheet;
        if (type === '0') {
            songSheet = songSheets.recommend(current);
        } else {
            songSheet = songSheets.news(current);
        }
        const types = get.Get("/songSheets/types", {});
        return all.songSheetsAll([types, songSheet])
    },
    "tagAll": (tag, current) => {
        const types = get.Get("/songSheets/types", {});
        const tags = songSheets.tags(tag, current)
        return all.songSheetsAll([types, tags])
    },
    "recommend": current => get.Get("/songSheets/recommends", {params: {'current': current}}),
    "news": current => get.Get("/songSheets/news", {params: {'current': current}}),
    "tags": (tag, current) => get.Get("/songSheets/tagDatas", {params: {'current': current, "tagId": tag}})
}

// singer
export const singer = {
    // 点赞
    "good": "",
    // 获取新的评论
    "getNewComment": "",
    // 获取歌手信息和歌曲信息
    "singerInfo": (singerId, current) => {
        const info = get.Get("/singer/info", {params: {'singerId': singerId}})
        const songs = singer.songs(singerId, current)
        return all.singerInfo([info, songs])
    },
    // 增加新的评论
    "addComment": "",
    "songs": (singerId, current) => get.Get("/singer/songs", {params: {'singerId': singerId, "current": current}})
}

export const songSheet = {
    "songSheetInfo": (songSheetId, current) => {
        const info = get.Get("/songSheet/info", {params: {'songSheetId': songSheetId}})
        const songs = songSheet.newSong(songSheetId, current)
        return all.songSheetAll([info, songs])
    },
    "newSong": (songSheetId, current) => get.Get("/songSheet/songs", {
        params: {
            'songSheetId': songSheetId,
            "current": current
        }
    })
}

// 获取歌曲地址
export const address = {
    "address": songId => {
        let params = new URLSearchParams();
        params.append('songId', songId)
        return post.Post('/address/song', params)
    }
}

// 登录
export const user = {
    "login": (username, password, code) => {
        let params = new URLSearchParams();
        params.append('username', username)
        params.append('password', password)
        params.append('k', 'db22214f6b74452793804bb3ebb1a4b2f0f74b95a04048ff94beb8')
        params.append('code', code)
        return post.Post('/login', params)
    },
    "code": (date) => get.Get("/user/v1/get/code", {responseType: 'arraybuffer', data: {"date": date}}),
    "logout": () => get.Get('/user/logout', {})
}

// 注册
export const register = {
    "username": username => {
        return get.Get("/register/emptyUsername", {params: {"username": username}})
    },
    "code": username => {
        let param = new URLSearchParams();
        param.append("userEmail", username)
        return post.Post("/register/code", param)
    },
    "register": (username, password, code) => {
        let param = new URLSearchParams();
        param.append("username", username)
        param.append("password", password)
        param.append("code", code)
        return post.Post("/register/userRegister", param)
    }
}

// 找回密码
export const retrieve = {
    "sendEmail": email => {
        let param = new URLSearchParams();
        param.append("userEmail", email)
        return post.Post('/retrieve/send', param)
    },
    "changePassword": (userEmail, check, newPassword) => {
        let param = new URLSearchParams();
        param.append("username", userEmail)
        param.append("check", check)
        param.append("password", newPassword)
        return put.Put('/retrieve/newPassword', param)
    }
}

// 下载
export const download = {
    "downloadMusic": (path, name) => {
        let param = new URLSearchParams();
        param.append("path", path)
        param.append("name", name)
        return post.Post('/common/download', param)
    }
}

// 查询页面
export const search = {
    'search': (page, type, keyword) => {
        return get.Get("/search/data", {params: {"page": page, "type": type, "keyword": keyword}})
    }
}

// 将歌曲加入或删除我喜欢
export const operationMusic = {
    "addCollectionSong": (songId, username) => {
        let param = new URLSearchParams();
        param.append("songId", songId)
        param.append("username", username)
        return put.Put('/user/addLikeMusic', param)
    },
    "deleteCollectionSong": (songId, username) => {
        let param = new URLSearchParams();
        param.append("songId", songId)
        param.append("username", username)
        return del.Delete('/user/deleteLikeMusic', {data: param})
    },
    "likeMusic": username => {
        return get.Get("/user/likeMusic", {params: {'username': username}})
    },
    "searchMusicIsLike": (songId, username) => {
        return get.Get("/user/playIsLike", {params: {'songId': songId,'username': username}})
    }
}

