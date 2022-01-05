import React from "react"
import { useParams } from "react-router-dom"
import Alert from 'react-bootstrap/Alert'

const LocationInfo = () => {

    var today = new Date();
    var date = today.getDate() + '/' + today.getMonth() + '/' + today.getFullYear();
    var hours = today.getHours() + ':' + (today.getMinutes().toString().length==1 ? ("0" + today.getMinutes()) : today.getMinutes())
    const { location } = useParams()

    return (

        <Alert variant="light" style={{ width: '300px' }}>
            <Alert.Heading style={{ color: 'black' }}>Current information</Alert.Heading>
            <p>
                {location}
            </p>
            <hr />
            <p className="mb-0">
                <strong>Date:</strong> {date}
            </p>
            <p className="mb-0">
                <strong>Time:</strong> {hours}
            </p>
        </Alert>
    )
}

export default LocationInfo;