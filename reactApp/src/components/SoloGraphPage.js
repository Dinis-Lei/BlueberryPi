import React, { useEffect, useState } from "react";
import CanvasJSReact from '../canvasjs.react';
import { fetchData, processString } from "../App";
import { useParams } from "react-router-dom"
import HourPicker from "./HourPicker";
import DatePicker from "./DatePicker";
import { Button } from "react-bootstrap";
import RangeSlider from 'react-bootstrap-range-slider';
import { Form, Col, Row } from "react-bootstrap";

var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

const units = {
    "Plantation temperature": "ºC",
    "Net harvest": "kg",
    "Soil ph": "pH",
    "Soil water tension": "cb",
    "Unit loss": "%",
    "Storage temperature": "ºC",
    "Storage humidity": "%"
}

const process_date = (timestamp) => {
    let date = new Date(timestamp * 1000);
    let date_str = date.getDate()+
        "/"+(date.getMonth()+1)+
        "/"+date.getFullYear()+
        " "+date.getHours()+
        ":"+date.getMinutes()+
        ":"+date.getSeconds();
    return date_str;
}

export const getDataPoints = (JSONData) => {
    let ret = [];
    let counter = 1;
    let graphData;

    console.log(JSONData)

    if (JSONData.length < 1){
        return 
    }


    graphData = JSONData;

    for (const dataPoint of graphData) {
        let newElem = {x: counter, y: dataPoint["data"], label: process_date(dataPoint["timestamp"])};
        ret.push(newElem);
        counter += 1;
    }

    return ret;
}


const SoloGraphPage = props =>{

    const [myDataPoints, setMyDataPoints] = useState([]);
    const [graphTitle, setGraphTitle] = useState("[no title]");
    const { location } = useParams()
    const [flg, setFlg] = useState(true);
    const { sensor } = useParams(); 
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [startHour, setStartHour] = useState('00:00:00');
    const [endHour, setEndHour] = useState('00:00:00');
    
    const [ nDataPoints, setnDataPoints ] = useState(100); 
    const [ maxDataPoints, setMaxDataPoints] = useState(200);
    // const [times, setTimes] = useState([]);

    const getDataPoints = (JSONData) => {
        let ret = [];
        let counter = 1;
        let graphData;
    
        if (JSONData.length < nDataPoints) {
            graphData = JSONData;
            setnDataPoints(JSONData.length)
        }
        else {
            let firstElem = 0; 
            let lastElem = nDataPoints;
            graphData = JSONData.slice(firstElem, lastElem); // não inclui lastElem
        }
        graphData.reverse()
        for (const dataPoint of graphData) {
            let newElem = {x: counter, y: dataPoint["data"], label: process_date(dataPoint["timestamp"])};
            ret.push(newElem);
            counter += 1;
        }
    
        return ret;
    }

    useEffect(() => {
        //while(true){
            setInterval(
            () => {
                //console.log(flg)
                setFlg(!flg)
            }, 60000) 
        //} 
        
    })

    useEffect(() => {
        console.log("LOCATION")
        console.log(location)
        
        let url = location + "/" + sensor + "?limit=" + nDataPoints;
        
        let data = fetchData(url); // data is a promise object
        data.then(function (result) {
            setMyDataPoints(getDataPoints(result));
            setGraphTitle(processString(sensor));
        });

    }, [flg, nDataPoints]);

    

    const options = {
        animationEnabled: true,
        width: 1800,
        theme: "light2",
        title:{
            text: graphTitle
        },
        axisY: {
            title: graphTitle,
            suffix: units[graphTitle]
        },
        axisX: {
            title: "Time",
            interval: 2
        },
        data: [{
            type: "spline",
            //indexLabel: "{x}: {y}",
            // toolTipContent: "Week {x}: {y}%",
            dataPoints: myDataPoints
        }]
    }

    const filterByDate = () => {
        let url = location + "/" + sensor + "?";
        if(startDate != ''){
            url += "start=" + startDate + "-" + startHour + "&";
        }
        if(endDate != ''){
            url += "end=" + endDate + "-" + endHour
        }

        console.log(url)

        let data = fetchData(url); // data is a promise object
        data.then(function (result) {
            console.log(result)
            setMyDataPoints(getDataPoints(result));
        });

    }

    return (	
        <div>

            <div style={{marginLeft: '5%'}}>
                <table>
                    <thead>
                        <tr>
                            <td>
                                <label>Start date:</label>
                                <DatePicker onChange={setStartDate} />
                                <HourPicker onChange={setStartHour} />
                            </td>
                            <td>
                                <label>End date:</label>
                                <DatePicker onChange={setEndDate} />
                                <HourPicker onChange={setEndHour}/>
                            </td>
                        </tr>
                        <Button onClick={filterByDate}>Filter</Button>
                    </thead>
                </table>
            </div>

            <CanvasJSChart options = {options} 
                //onRef={ref => this.chart = ref}
            />

            <div className="col-6 mx-auto">
                <label for="customRange3" class="form-label">Data Points</label>
                <Form>
                <RangeSlider
                                max={maxDataPoints }
                                value={nDataPoints}
                                onChange={e => setnDataPoints(e.target.value)}
                                tooltip='on'
                            />
                </Form>
            </div>
            
        </div>			
    )

}

export default SoloGraphPage;