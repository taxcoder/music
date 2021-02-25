import {MUSIC_LIST, PLAY_INDEX,LENGTH,PLAY} from "@store/constant";

export default {
    [MUSIC_LIST](state,param){
        state.commit(MUSIC_LIST,param)
    },
    [PLAY_INDEX](state,param){
        state.commit(PLAY_INDEX,param)
    },
    [LENGTH](state,param){
        state.commit(LENGTH,param)
    },
    [PLAY](state,param){
        state.commit(PLAY,param)
    }
}