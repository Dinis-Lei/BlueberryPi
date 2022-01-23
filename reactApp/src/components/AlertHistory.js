import React, { useEffect, useState } from "react";
import { Table, Container } from "react-bootstrap";
import { useParams } from "react-router-dom";
import { fetchData, processString } from "../App";


const AlertHistory = props => {

    const {location} = useParams();
    const {sensor} = useParams();
    const [alerts, setAlerts] = useState([]);
    const {all} = useState(props.all);
    const [flg, setFlg] = useState(true);

    useEffect(() => {
        console.log(all)
        console.log(props.all)
        if (props.all){
            let url = "alerts";
            let data = fetchData(url); // data is a promise object
            data.then(function (result) {
                console.log(result)
                setAlerts(result)
            }); 
        }
        else{
            console.log(location)
            console.log(sensor)
            
            let url = location + "/" + sensor + "/alerts";

            let data = fetchData(url); // data is a promise object
            data.then(function (result) {
                console.log(result)
                setAlerts(result)
            }); 
        }
    }, [flg]) 

    useEffect(() => {
        setInterval(
         () => {
             setFlg(!flg)
         }, 60000) 
 })

    return (
        <div >	 	
            <Container className="justify-content-md-center">
                <h2 >Alerts</h2>
                <Table  striped bordered hover size="md">
                    <thead>
                        <tr>
                            <th>#</th>
                            {props.all && <th>Location</th>}
                            {props.all && <th>Sensor</th>}
                            <th>Start</th>
                            <th>End</th>
                            <th>Deviation</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            alerts.map((data, idx) => {
                                return (
                                <tr>
                                    <td>{idx + 1}</td>
                                    {props.all && <td>{data.location}</td>}
                                    {props.all && <td>{data.sensor}</td>}
                                    <td>{data.start}</td>
                                    <td>{data.end}</td>
                                    <td>{data.val}</td>
                                </tr>
                                )
                            })
                        }
                    </tbody>
                </Table>
            </Container>
        </div>	
    )
}

export default AlertHistory