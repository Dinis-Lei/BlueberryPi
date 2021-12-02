import React from "react"
import {useParams} from "react-router-dom"

const LocationInfo = () => {
	
    var today = new Date();
    var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
    var hours = today.getHours()+':'+today.getMinutes()
    const { location } = useParams()

    return (
        <div style={{backgroundColor: "#4f86f7", borderRadius: "10px", width: "20%"}} className="m-3 px-3" > 
            <p>{date}</p>
            <p>{hours}</p>
            <p>{location}</p>
        </div>
    )
}

export default LocationInfo;