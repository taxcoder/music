
export default (data,processed) => {
		if(data === null || data === ''){
			return []
		}

	if(typeof data === 'object'){
			for(let resource of data){
				resource[processed] = process.env["VUE_APP_URL"] + resource[processed]
			}
			return data
		}
		return []
}

export const splicing = (data,processed) => {
	if(data === null || data === ''){
		return undefined
	}

	if(typeof data === 'object'){
		data[processed] = process.env['VUE_APP_URL'] + data[processed]
	}
	return  data
}
