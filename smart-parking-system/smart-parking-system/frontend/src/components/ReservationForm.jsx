import React, { useState, useEffect } from 'react'

const ReservationForm = ({ selectedSpot, onCreate }) => {
  const [vehicleNumber, setVehicleNumber] = useState('')
  const [vehicleType, setVehicleType] = useState('CAR')
  const [startTime, setStartTime] = useState('')
  const [endTime, setEndTime] = useState('')

  useEffect(() => {
    setStartTime('')
    setEndTime('')
  }, [selectedSpot])

  const handleSubmit = (e) => {
    e.preventDefault()
    if (!selectedSpot) {
      alert('Please select a parking spot.')
      return
    }
    if (!vehicleNumber || !startTime || !endTime) {
      alert('Please fill all fields.')
      return
    }

    onCreate({
      vehicleNumber,
      vehicleType,
      spotId: selectedSpot.id,
      startTime,
      endTime
    })

    setVehicleNumber('')
    setStartTime('')
    setEndTime('')
  }

  return (
    <div className="card">
      <h2>Create Reservation</h2>
      {selectedSpot ? (
        <p>
          Selected Spot: <strong>{selectedSpot.spotCode}</strong> ({' '}
          {selectedSpot.level})
        </p>
      ) : (
        <p className="muted">Select a free spot from the layout.</p>
      )}

      <form onSubmit={handleSubmit} className="form">
        <div className="form-group">
          <label>Vehicle Number</label>
          <input
            type="text"
            value={vehicleNumber}
            onChange={(e) => setVehicleNumber(e.target.value)}
            placeholder="TS09AB1234"
          />
        </div>

        <div className="form-group">
          <label>Vehicle Type</label>
          <select
            value={vehicleType}
            onChange={(e) => setVehicleType(e.target.value)}
          >
            <option value="CAR">Car</option>
            <option value="BIKE">Bike</option>
          </select>
        </div>

        <div className="form-group">
          <label>Start Time</label>
          <input
            type="datetime-local"
            value={startTime}
            onChange={(e) => setStartTime(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label>End Time</label>
          <input
            type="datetime-local"
            value={endTime}
            onChange={(e) => setEndTime(e.target.value)}
          />
        </div>

        <button type="submit" className="btn primary" disabled={!selectedSpot}>
          Reserve Spot
        </button>
      </form>
    </div>
  )
}

export default ReservationForm
