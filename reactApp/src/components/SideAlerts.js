import React, { useEffect, useState } from "react"
import Toast from 'react-bootstrap/Toast'
import ToastContainer from 'react-bootstrap/ToastContainer'
import { useParams } from "react-router-dom"
import { fetchData, processString } from "../App"


const checkAlert = (alert) => {
    alert.seen = true
    const requestOptions = {
        method: 'Put',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(alert)
    };
    fetch('https://localhost:8080/api/alerts', requestOptions)
        .then(response => response.json())
        .then(data => this.setState({ postId: data.id }));
}

const getDate = (start) => {
    let start_Date = new Date(start * 1000);
    console.log(start)
    let start_date = start_Date.getDate()+
        "/"+(start_Date.getMonth()+1)+
        "/"+start_Date.getFullYear()+
        " "+start_Date.getHours()+
        ":"+start_Date.getMinutes()+
        ":"+start_Date.getSeconds();
    return start_date
}

const stringfyAlert = (alert) => {
    let ret = {}
  
    

    let location = alert["location"];
    console.log(alert.sensor)
    let sensor = processString(alert["sensor"]);
    let val = alert["val"];

    let start = alert["start"];
    let start_Date = new Date(start * 1000);
    let start_date = start_Date.getDate()+
        "/"+(start_Date.getMonth()+1)+
        "/"+start_Date.getFullYear()+
        " "+start_Date.getHours()+
        ":"+start_Date.getMinutes()+
        ":"+start_Date.getSeconds();

    let end = alert["end"];
    let end_Date = new Date(end * 1000);
    let end_date = end_Date.getDate()+
        "/"+(end_Date.getMonth()+1)+
        "/"+end_Date.getFullYear()+
        " "+end_Date.getHours()+
        ":"+end_Date.getMinutes()+
        ":"+end_Date.getSeconds();

    let alert_str = sensor + ": " + val + ", since: " + start_date + ", until: " + end_date;

    if (ret[location] != undefined) {
        ret[location] = ret[location].concat([alert_str]);
    }
    else {
        ret[location] = [alert_str];
    }
  
    
    console.log(ret)
    return ret;
}



const getLocations = (JSONData) => {
    let ret = [];
    for (let elem in JSONData) {
        let alert = JSONData[elem];
        let loc = alert["location"];
        if (!ret.includes(loc)){
            ret.push(loc);
        }
    }
    return ret;
}

const SideAlerts = props => {

    const [show, setShow] = React.useState(true);
    const toggleShow = () => setShow(!show);
    const [alerts_dict, setAlertsDict] = useState([]);
    const {location} = useParams();

    /* alerts_dict = {
        location1: [
        [
            sensor1: ...,
            val1: ...,
            start1: ...,
            end1: ...,
        ],
        [
            sensor2: ...,
            val2: ...,
            start2: ...,
            end2: ...,
        ],
        [...],
        [...],
        ...
        ],
        location2: [...],
        location3: [...],
        ...
    } */

    useEffect(() => {

        let alerts_data = fetchData("alerts");
        alerts_data.then(function(result){
            console.log("BBBBB")
            console.log(result)
            setAlertsDict(result);
            //setLocationsLst(getLocations(result));
        });

    }, []);

    if (!props.alerts) {
        return (<div></div>);
    }

    return (
        <div
            aria-live="polite"
            aria-atomic="true"
            className="position-relative"
            style={{ minHeight: '240px' }}
            >

            <ToastContainer style={{ paddingTop: '10%', paddingLeft: '10%' }}>
                <Toast show={show} onClose={toggleShow} style={{ width: '75%' }}>
                    <Toast.Header>
                        <img src="holder.js/20x20?text=%20" className="rounded mr-2" alt="" />
                        <strong className="me-auto">ALERT</strong>
                        <small className="text-muted">just now</small>
                    </Toast.Header>
                    <Toast.Body>
                    {
                        
                        
                        alerts_dict.map(
                            (alert) => {
                                console.log("AAAAA")
                                console.log(alert)
                                console.log(location)
                                if(alert.location != location){
                                    return (<div></div>);
                                }
                                return(
                                    !alert.seen && 
                                    <div>
                                        <p>{processString(alert.sensor) + ": " + alert.val + ", since: " + getDate(alert.start) + ", until: " + getDate(alert.end)}</p>
                                        <button onClick={checkAlert(alert)}></button>
                                    </div>
                                )
                            }
                        )
                        
                            
                        
                    }
                    </Toast.Body>
                </Toast>
            </ToastContainer>
        </div>
    );

}

export default SideAlerts;