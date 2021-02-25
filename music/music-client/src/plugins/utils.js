export const getCookie = cname => {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie)
    let ca = decodedCookie.split(';')
    for(let i = 0; i <ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
          return  c = c.substring(name.length, c.length);
        }
    }
    return undefined
}

export const storage = {
    dispatchEventStorage() {
        const signSetItem = localStorage.setItem
        localStorage.setItem = function(key, val) {
            let setEvent = new Event('setItemEvent')
            setEvent.key = key
            setEvent.newValue = val
            window.dispatchEvent(setEvent)
            signSetItem.apply(this, arguments)
        }
     },
    //sessionStorage
    dispatchEventStorage_S() {
        const signSetItem = sessionStorage.setItem
        sessionStorage.setItem = function(key, val) {
            let setEvent = new Event('setItemEvent_s')
            setEvent.key = key
            setEvent.newValue = val
            window.dispatchEvent(setEvent)
            signSetItem.apply(this, arguments)
        }
    }
}