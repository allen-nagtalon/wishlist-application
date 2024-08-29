import axios from 'axios'

const API_URL = 'http://localhost:8085'

const ApiInstance = axios.create({
  baseURL: API_URL,
  timeout: 5000,
  headers: {
    Authorization: window.localStorage.getItem('access_token')
      ? 'JWT ' + window.localStorage.getItem('access_token')
      : null,
    'Content-Type': 'application/json',
    accept: 'application/json'
  }
})

export default ApiInstance
