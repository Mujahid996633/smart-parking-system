import React from 'react'

const ParkingGrid = ({ spots, onSpotSelect, selectedSpotId }) => {
  return (
    <div className="card">
      <h2>Parking Layout</h2>
      <div className="grid">
        {spots.map((spot) => (
          <button
            key={spot.id}
            className={
              'spot ' +
              (spot.occupied ? 'occupied' : 'free') +
              (selectedSpotId === spot.id ? ' selected' : '')
            }
            onClick={() => !spot.occupied && onSpotSelect(spot)}
            disabled={spot.occupied}
          >
            <div className="spot-code">{spot.spotCode}</div>
            <div className="spot-level">{spot.level}</div>
            <div className="spot-status">
              {spot.occupied ? 'Occupied' : 'Available'}
            </div>
          </button>
        ))}
      </div>
    </div>
  )
}

export default ParkingGrid
