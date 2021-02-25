import {LEFT, LENGTH, PLAY} from "@store/constant";
import store from "@/store";

export default () => {
	store.commit(LENGTH,0)
	store.commit(PLAY,false)
}
