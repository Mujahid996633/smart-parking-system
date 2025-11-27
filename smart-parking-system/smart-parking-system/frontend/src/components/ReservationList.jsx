import React from 'react'

const formatDate = (str) => {
  if (!str) return ''
  return str.replace('T', ' ').slice(0, 16)
}

const ReservationList = ({ reservations, onCancel }) => {
  return (
    <div className="card">
      <h2>Active Reservations</h2>
      {reservations.length === 0 ? (
        <p className="muted">No reservations yet.</p>
      ) : (
        <table className="table">
          <thead>
            <tr>
              <th>Vehicle</th>
              <th>Type</th>
              <th>Spot</th>
              <th>Level</th>
              <th>Start</th>
              <th>End</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {reservations.map((r) => (
              <tr key={r.id}>
                <td>{r.vehicleNumber}</td>
                <td>{r.vehicleType}</td>
                <td>{r.parkingSpot?.spotCode}</td>
                <td>{r.parkingSpot?.level}</td>
                <td>{formatDate(r.startTime)}</td>
                <td>{formatDate(r.endTime)}</td>
                <td>
                  <button
                    className="btn danger"
                    onClick={() => onCancel(r.id)}
                  >
                    Cancel
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  )
}

export default ReservationList
