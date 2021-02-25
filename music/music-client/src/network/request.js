import network from "@/network/axios";

export const get = {
	Get(url,data){
		return network.get(url,data);
	}
}

export const post = {
	Post(url,param){
		return network.post(url,param);
	}
}

export const put = {
	Put(url,data){
		let params = new URLSearchParams();
		let keys = params.keys();
		return network.put(url,data);
	}
}

export const del = {
	Delete(url,data){
		return network.delete(url,data);
	}
}

export const all = {
	indexAll(http){
		return new Promise((resolve, reject) => {
			network.all(http).then(network.spread((songSheet,song,singer,region) => {
				const data = {
					songSheet,
					song,
					singer,
					region
				}
				resolve(data)
			})).catch(error => {
				reject(error)
			})
		})
	},
	songSheetsAll(http){
		return new Promise((resolve, reject) => {
			network.all(http).then(network.spread((types,songSheet) => {
				const data = {
					types,
					songSheet,
				}
				resolve(data)
			})).catch(error => {
				reject(error)
			})
		})
	},
	singerInfo(http){
		return new Promise((resolve, reject) => {
			network.all(http).then(network.spread((info,songs) => {
				const data = {
					info,
					songs,
				}
				resolve(data)
			})).catch(error => {
				reject(error)
			})
		})
	},
	songSheetAll(http){
		return new Promise((resolve, reject) => {
			network.all(http).then(network.spread((info,songs) => {
				const data = {
					info,
					songs,
				}
				resolve(data)
			})).catch(error => {
				reject(error)
			})
		})
	}
}

network.interceptors.response.use(response => {
	if (response.status === 200) {
		if(response.data.code === 0){
			return Promise.reject(response.data);
		}
		return Promise.resolve(response);
	} else {
		return Promise.reject(response);
	}
},error => {
	if(error.response){
		return Promise.reject(error.response.data);
	}
	return Promise.reject(error);
})

network.interceptors.request.use(request => {
	return request
})


