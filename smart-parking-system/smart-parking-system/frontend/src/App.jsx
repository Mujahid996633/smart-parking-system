import React, { useEffect, useState } from 'react'
import Navbar from './components/Navbar'
import ParkingGrid from './components/ParkingGrid'
import ReservationForm from './components/ReservationForm'
import ReservationList from './components/ReservationList'
import {
  getAllSpots,
  getReservations,
  createReservation,
  cancelReservation
} from './api'

const App = () => {
  const [spots, setSpots] = useState([])
  const [reservations, setReservations] = useState([])
  const [selectedSpot, setSelectedSpot] = useState(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')

  const fetchData = async () => {
    try {
      setLoading(true)
      const [spotsRes, reservationsRes] = await Promise.all([
        getAllSpots(),
        getReservations()
      ])
      setSpots(spotsRes.data)
      setReservations(reservationsRes.data)
      setError('')
    } catch (e) {
      console.error(e)
      setError('Failed to load data from backend.')
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    fetchData()
  }, [])

  const handleCreateReservation = async (data) => {
    try {
      setLoading(true)
      await createReservation(data)
      await fetchData()
      setSelectedSpot(null)
    } catch (e) {
      alert(e.response?.data || 'Failed to create reservation')
    } finally {
      setLoading(false)
    }
  }

  const handleCancelReservation = async (id) => {
    if (!window.confirm('Cancel this reservation?')) return
    try {
      setLoading(true)
      await cancelReservation(id)
      await fetchData()
    } catch (e) {
      alert(e.response?.data || 'Failed to cancel reservation')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div>
      <Navbar />
      <main className="container">
        {loading && <div className="banner info">Loading...</div>}
        {error && <div className="banner danger">{error}</div>}

        <div className="layout">
          <ParkingGrid
            spots={spots}
            selectedSpotId={selectedSpot?.id}
            onSpotSelect={setSelectedSpot}
          />
          <ReservationForm
            selectedSpot={selectedSpot}
            onCreate={handleCreateReservation}
          />
        </div>

        <ReservationList
          reservations={reservations}
          onCancel={handleCancelReservation}
        />
      </main>
    </div>
  )
}

export default App
