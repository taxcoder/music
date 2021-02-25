import {Message} from "element-ui";

export const successMessage = message => {
	setTimeout(() => {
		Message.success(message)
	},0)
}

export const errorMessage = message => {
	setTimeout(() => {
		Message.error(message)
	},0)
}

export const warningMessage = message => {
	setTimeout(() => {
		Message.warning(message)
	},0)
}

export const infoMessage = message => {
	setTimeout(() => {
		Message.info(message)
	},0)
}

