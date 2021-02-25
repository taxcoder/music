//引入path模块
const path = require('path');
//path.join(__dirname)设置绝对路径
const resolve = dir => path.join(__dirname, dir)

module.exports = {
	outputDir: 'dist',   				// 打包的目录资源
	assetsDir: 'static', 				// 静态资源目录地址
	productionSourceMap: false,		 	// 去掉打包时生成的map文件
	chainWebpack: (config) => {
		config.resolve.alias
		//set第一个参数：设置的别名，第二个参数：设置的路径
		.set('@', resolve('./src'))
		.set('@views', resolve('src/views'))
		.set('@assets', resolve('src/assets'))
		.set('@components', resolve('./src/components'))
		.set('@router', resolve('./src/router'))
		.set('@store', resolve('./src/store'))
		.set('@network', resolve('./src/network'))
	},
	devServer: {
		port: 8090
	}
}