import React, { useEffect, useState } from "react";
// import { useParams } from "react-router-dom";
import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import {fetchData, processString} from '../App';

const getLatestDataPoint = (JSONData, location_id, units) => {

    let div = 1;

    while (div != null) {
        if (JSONData.length != 0) {

            let location = "Location " + location_id;
            let inner_counter = JSONData.length - 1; // start by latest info (the most recent)
            let found_info = false;

            while (inner_counter) {
                const new_info = JSONData[inner_counter];
                if (new_info["location"]==location) { // check for correct 
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
    const [value, setValue] = useState(0);

    useEffect(() => {
        
        let data = fetchData(props.dataType); // data is a promise object
        data.then(function (result) {
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