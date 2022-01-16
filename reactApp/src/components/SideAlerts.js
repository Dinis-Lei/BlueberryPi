import React, { useEffect, useState } from "react"
import Toast from 'react-bootstrap/Toast'
import ToastContainer from 'react-bootstrap/ToastContainer'
import { fetchData, processString } from "../App"

const getAlerts = (JSONData) => {

    let ret = {}
  
    for (let elem in JSONData) {
        
        let alert = JSONData[elem];

        let location = alert["location"];

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
    const [alerts_dict, setAlertsDict] = useState({});
    const [locations_lst, setLocationsLst] = useState([]);

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
            setAlertsDict(getAlerts(result));
            setLocationsLst(getLocations(result));
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
                        locations_lst.map(
                            (location) => {
                                return(
                                    alerts_dict[location].map(
                                        (alert) => {
                                            console.log(alert)
                                            return(
                                                <p>{alert}</p>
                                            )
                                        }
                                    )
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