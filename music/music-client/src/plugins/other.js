let base64 = require('js-base64').Base64;


export const activeMusicIdentification = 'data:image/gif;base64,R0lGODlhFAAwAIABAOQAdgAAACH/C05FVFNDQVBFMi4wAwEAAAAh/wtYTVAgRGF0YVhNUDw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDpiOWViMTI2Yy03YTAxLTQwYWUtOTY2NS02ZTljNTBkOTQ2MzciIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6MzJGNTU0MjY4NzA1MTFFN0E4NzJCMDVFQjAyQjUxRkUiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6MzJGNTU0MjU4NzA1MTFFN0E4NzJCMDVFQjAyQjUxRkUiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKE1hY2ludG9zaCkiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDpiOWViMTI2Yy03YTAxLTQwYWUtOTY2NS02ZTljNTBkOTQ2MzciIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6YjllYjEyNmMtN2EwMS00MGFlLTk2NjUtNmU5YzUwZDk0NjM3Ii8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8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'
export const url = "http://localhost:8090"
export const code = {
    /**
     * 加密
     * @param text
     * @returns {string}
     */
    encryption:text =>{
        return base64.encode(escape(base64.encode(escape(escape(text)))))
    },
    /**
     * 解密
     * @param text
     * @returns {string}
     */
    decrypt: text => {
        return unescape(unescape(base64.decode(unescape(base64.decode(text)))))
    }
}

