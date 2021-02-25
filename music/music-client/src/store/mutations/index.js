import {LOOP_MUSIC,MUSIC_LIST,VOLUME,PLAY_INDEX,CLEAR,PLAY,PAUSED_MUSIC,CHECKED,LENGTH,PLAYER,LOGIN} from '@/store/constant'
export default {
	[LOOP_MUSIC](state,param){
		state.loopMusic = param
	},
	[MUSIC_LIST](state,param) {
		state.musicList = param
	},
	[VOLUME](state,param){
		state.volume = param
	},
	[PLAY_INDEX](state,param){
		state.playIndex = param
	},
	[CLEAR](state,param){
		state.clear = param
	},
	[PLAY](state,param){
		state.play = param
	},
	[PAUSED_MUSIC](state,param){
		state.paused = param
	},
	[LENGTH](state,param){
		state.length = param
	},
	[CHECKED](state,param){
		if(param.length === 0){
			state.checkboxChecked = param
			return;
		}
		for (let i = 0; i < param.length; i++) {
			state.checkboxChecked.push(param[i])
		}
	},
	[PLAYER](state,param){
		state.player = param
	},
	[LOGIN](state,param){
		state.login = param
	}
}
