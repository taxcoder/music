import {MUSIC_LIST} from "@store/constant";

export default class Map {
    constructor() {
        if (!Map.instance) {
            Map.instance = this
        }
    }

    // 获取数据
    get(key) {
        let data = this.typeof(key)
        return data == null ? null : (typeof data === "object" ? data : data);
    }

    // 判断localStorage的类别
    typeof(key) {
        if (key === null) {
            return null
        }

        let data = localStorage.getItem(key)
        if (data === null) {
            return null
        }
        if (data.charAt(0) === '[' && data.substring(data.length - 1) === ']') {
            try {
                return JSON.parse(data)
            }catch (error){
                return null
            }
        }
        return data
    }

    typeofCompare(key, type) {
        let data = this.typeof(key)
        return data == null ? true : typeof data === type
    }

    // 获取数据的长度
    ArraySize(key) {
        if (this.typeofCompare(key, 'string')) {
            throw new Error("数据不为数组！")
        }
        return this.get(key).length
    }

    // 获取数组指定位置的数据
    ArrayDataByIndex(key, index) {
        if (this.typeofCompare(key, 'string')) {
            throw new Error("数据不为数组！")
        }
        return this.get(key)[index]
    }

    ArrayDataByValue(key, value, condition) {
        if (this.typeofCompare(key, 'string')) {
            throw new Error("数据不为数组！")
        }
        let length = 0
        for (let data of this.get(key)) {
            if (data[value] === condition) {
                return data
            }
            length++;
        }
        return undefined
    }

    ArrayByValueReturnIndex(arrays,keyword,condition){
        let length = 0
        for(let array of arrays){
            if(array[keyword] === condition){
                return length;
            }
            length ++;
        }
    }

    // 在localStorage创建k-v键值对数据，value为普通字符串
    addString(key, value) {
        if (key === null || value === null || key.length === 0) {
            throw new Error("参数错误！")
        }
        localStorage.setItem(key, value)
    }

    arrayIsKeyEmpty(key, keyword, condition) {
        let data = this.typeof(key)
        if (data == null) {
            return true
        }
        if (typeof data === "string") {
            return true
        }

        // object情况
        for (let result of data) {
            if (result[keyword] === condition) {
                return false
            }
        }
        return true
    }

    // 在localStorage创建k-v键值对数据，value为数组转字符串或在数组末尾追加数据
    pushArray(key, value, keyword) {

        let data = this.typeof(key)
        if (data == null) {
            localStorage.setItem(key, typeof value === "object" ? JSON.stringify(value) : value)
        } else if (typeof data === 'string') {
            throw new Error("传入参数必须为数组！")
        } else {
            if (typeof value === "object") {
                let temp = []
                for (let i = 0; i < data.length; ++i) {
                    for (let j = 0; j < value.length; ++j) {
                        if (value[j][keyword] === data[i][keyword]) {
                            temp.push(j)
                        }
                    }
                }
                value = this.removeArrayValue(value, temp)
                if (value.length === 0) {
                    return false;
                }
                for (let i of value) {
                    data.push(i)
                }
                localStorage.setItem(key, JSON.stringify(data))
                return true;
            }
        }
    }

    // 在localStorage创建k-v键值对数据，value为数组转字符串或在数组头部追加数据
    pushArrayTop(key, value, keyword) {
        let data = this.typeof(key)

        if (data == null) {
            localStorage.setItem(key, value instanceof "Array" ? JSON.stringify(value) : value)
            return false;
        } else if (typeof data === 'string') {
            throw new Error("传入参数必须为数组！")
        } else {
            if (typeof value === "object") {
                let temp = []
                for (let i = 0; i < data.length; ++i) {
                    for (let j = 0; j < value.length; ++j) {
                        if (value[j][keyword] === data[i][keyword]) {
                            temp.push(j)
                        }
                    }
                }
                value = this.removeArrayValue(value, temp)
                if (value.length === 0) {
                    return false;
                }
                for (let i of value) {
                    data.unshift(i)
                }
                localStorage.setItem(key, JSON.stringify(data))
                return true;
            }
        }
    }

    // 删除指定数据
    clear(key) {
        localStorage.removeItem(key)
    }

    // 删除数组之中的某一条数据，key为存储key，keyword为关键字，condition为判断条件
    removeArrayData(key, keyword, condition) {
        let length = 0
        let flag = this.typeofCompare(key, "string")
        if (!flag) {
            let datas = this.get(key)
            for (let data of datas) {
                if (data[keyword] === condition) {
                    datas.splice(length, 1)
                    localStorage.setItem(key, JSON.stringify(datas))
                    return true;
                }
                length++;
            }
        }
        return false;
    }
    // 删除数组之中的某一条数据，key为存储key，keyword为关键字，condition为判断条件
    removeArrayDataDouble(key, keyword, arrays) {
        let flag = this.typeofCompare(key, "string")
        if (!flag) {
            let datas = this.get(key)
            let data;
            for (let array of arrays) {
                data = datas.filter(data => {
                    if(data[keyword] !== array[keyword]){
                        return true
                    }
                })
            localStorage.setItem(key, JSON.stringify(data))
            }
            return true;
        }
        return false;
    }


    removeArrayValue(value, array) {
        let object = {
            sortNumber: function (a, b) {
                return a - b
            },

            duplicateRemoval(data, length) {
                if (length >= data.length) {
                    return;
                }
                if (data[length] - data[length + 1] === 0) {
                    data.splice(length, 1)
                    length = length - 1
                }

                object.duplicateRemoval(data, length + 1)
            }
        }


        array.sort(object.sortNumber)
        object.duplicateRemoval(array, 0)

        let length = 0;
        for (let i = 0; i < array.length; ++i) {
            value.splice(array[i] - length, 1)
            length++;
        }

        return value
    }

    static getInstance() {
        if (!Map.instance) {
            return this.instance = new Map()
        }
        return this.instance
    }
}
