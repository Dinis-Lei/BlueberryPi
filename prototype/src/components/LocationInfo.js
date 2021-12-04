import React from "react"
import { useParams } from "react-router-dom"
import Card from "react-bootstrap/Card";

const LocationInfo = () => {

    var today = new Date();
    var date = today.getDate() + '/' + today.getMonth() + '/' + today.getFullYear();
    var hours = today.getHours() + ':' + today.getMinutes()
    const { location } = useParams()

    return (
        <Card style={{ width: '18rem' }} border="primary">
            <Card.Body>
                <Card.Title>Current information</Card.Title>
                <Card.Subtitle className="mb-2 text-muted">{location}</Card.Subtitle>
                <Card.Text>Date: {date} </Card.Text>
                <Card.Text style={{marginTop: '-5%'}}>Time: {hours} </Card.Text>
            </Card.Body>
        </Card>
    )
}

export default LocationInfo;