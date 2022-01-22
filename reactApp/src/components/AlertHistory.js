import React, { useEffect, useState } from "react";
import { Table, Container } from "react-bootstrap";
import { useParams } from "react-router-dom";
import { fetchData, processString } from "../App";





const AlertHistory = props => {

    const {location} = useParams();
    const {sensor} = useParams();
    const [alerts, setAlerts] = useState([]);

    useEffect(() => {
        console.log(location)
        console.log(sensor)
        
        let url = location + "/" + sensor + "/alert";

        let data = fetchData(url); // data is a promise object
        data.then(function (result) {
            console.log(result)
            setAlerts(result)
        });

    }, []) 




    return (
        <div >	
            	
            <Container className="justify-content-md-center">

                <h2 >Alerts</h2>
                <Table  striped bordered hover size="md">
                    <thead>
                        <tr>
                            <th>#</th>
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