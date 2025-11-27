import axios from 'axios'

const API_BASE = 'http://localhost:8080/api'

export const getAllSpots = () => axios.get(`${API_BASE}/spots`)
export const getReservations = () => axios.get(`${API_BASE}/reservations`)
export const createReservation = (data) => axios.post(`${API_BASE}/reservations`, data)
export const cancelReservation = (id) => axios.delete(`${API_BASE}/reservations/${id}`)
