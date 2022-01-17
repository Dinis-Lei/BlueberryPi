import React, { useEffect, useState } from "react";
// import { useParams } from "react-router-dom";
import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import { fetchData, processString } from "../App";

const getLatestDataPoint = (JSONData, location_name, units) => {

    let div = 1;

    while (div != null) {
        if (JSONData.length != 0) {

            let inner_counter = JSONData.length - 1; // start by latest info (the most recent)
            let found_info = false;

            while (inner_counter!=-1) {
                const new_info = JSONData[inner_counter];
                console.log("NEW INFO LOCATION: " + new_info["location"]);
                console.log("LOCATION NAME: " + location_name);
                if (new_info["location"]==location_name) { // check for correct 
                    found_info = true;
                    return (new_info["data"] + " " + units)
                }
                inner_counter -= 1;
            }
            if (!found_info) {
                return "no available information"
            }
        }
        else {
            return "no available information"
        }

    }

}

const DailyInfo = props => {

    const title = processString(props.dataType)
    const [value, setValue] = useState("no information available");

    useEffect(() => {
        
        let data = fetchData(props.location + "/" + props.dataType); // data is a promise object
        data.then(function (result) {
            console.log("PROPS LOCATION: " + props.location);
            setValue(getLatestDataPoint(result, props.location, props.units));
        });

    }, []);

    return(
        <Card style={{ width: 'auto' }}>
            <Card.Header>{title}</Card.Header>
            <ListGroup variant="flush">
                <ListGroup.Item>{value}</ListGroup.Item>
            </ListGroup>
        </Card>
    )

}

export default DailyInfo;