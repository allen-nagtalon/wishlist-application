import axios from 'axios'

const API_URL = 'http://localhost:8080/wishes'

export async function saveWish (wish) {
  return await axios.post(API_URL, wish)
}

export async function getWishes (page = 0, size = 10) {
  return await axios.get(`${API_URL}?page=${page}&size=${size}`)
}

export async function getWish (id) {
  return await axios.get(`${API_URL}/${id}`)
}

export async function updateWish (wish) {
  return await axios.post(API_URL, wish)
}

export async function updatePhoto (formData) {
  return await axios.put(`${API_URL}/photo`, formData)
}

export async function deleteWish (id) {
  return await axios.delete(`${API_URL}/${id}`)
}
