import React from "react"
import { useParams } from "react-router-dom"
import Alert from 'react-bootstrap/Alert'

const LocationInfo = () => {

    var today = new Date();

    var day = today.getDate().toString().length==1 ? ("0" + today.getDate()) : today.getDate();
    var month = (today.getMonth()+1).toString().length==1 ? ("0" + (today.getMonth()+1)) : (today.getMonth()+1);
    var date = day + '/' + month + '/' + today.getFullYear();

    var Hs = today.getHours().toString().length==1 ? ("0" + today.getHours()) : today.getHours();
    var Ms = today.getMinutes().toString().length==1 ? ("0" + today.getMinutes()) : today.getMinutes();
    var hours = Hs + ':' + Ms

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