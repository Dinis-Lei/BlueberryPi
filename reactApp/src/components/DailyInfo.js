import React, { useEffect, useState } from "react";
// import { useParams } from "react-router-dom";
import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import { fetchData, processString } from "../App";

const getLatestDataPoint = (JSONData, location_name, units) => {

    let div = 1;
    console.log(JSONData);
    if (JSONData.length != 0) {
        return JSONData["data"] + " " + units;

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

const DailyInfo = props => {

    const title = processString(props.dataType)
    const [value, setValue] = useState("no information available");

    const aStyle = {
        'text-decoration': 'none'
    };

    useEffect(() => {
        
        let data = fetchData(props.location + "/" + props.dataType + "/latest"); // data is a promise object
        data.then(function (result) {
            console.log("PROPS LOCATION: " + props.location);
            setValue(getLatestDataPoint(result, props.location, props.units));
        });

    }, []);

    return(
        <a style={aStyle} href={"/dashboard/"+props.location+"/"+props.dataType}>
            <Card>
            <Card.Header>{title}</Card.Header>
            <ListGroup variant="flush">
                <ListGroup.Item>{value}</ListGroup.Item>
            </ListGroup>
            </Card> 
        </a>
    )

}

export default DailyInfo;