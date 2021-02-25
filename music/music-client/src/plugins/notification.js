import {Notification} from "element-ui";

export const errorNotification = message => setTimeout(() => {
		Notification.error({
			title: '错误',
			message: message
		})
},0);

export const successNotification = message => setTimeout(() => {
			Notification.success({
				title: '正确',
				message: message
		})
},0);

export const warningNotification = message => setTimeout(() => {
	Notification.warning({
		title: '警告',
		message: message
	});
},0)

export const infoNotification = message => setTimeout(() => {
	Notification.info({
		title: '消息',
		message: message
	})
},0);
