import axios from 'axios'

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
axios.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
axios.defaults.headers.delete['Content-Type'] = 'application/x-www-form-urlencoded';
axios.defaults.headers.get['Content-Type'] = 'application/json';
axios.defaults.baseURL = process.env["VUE_APP_URL"]
axios.defaults.withCredentials = true
axios.defaults.timeout = 5000000

export default axios
