import React, { useEffect, useState } from "react";
import CanvasJSReact from '../canvasjs.react';
import { fetchData, processString } from "../App";
import { useParams } from "react-router-dom"
import { getDataPoints } from "./Graph";

var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

const CircularGraph = props =>{

    const [myDataPoints, setMyDataPoints] = useState([]);
    const [graphTitle, setGraphTitle] = useState("[no title]");
    const { location } = useParams()

    useEffect(() => {

        let dataType = props.dataType;
        let url = location + "/" + dataType;
        
        let plantation_temperature_data = fetchData(url); // data is a promise object
        plantation_temperature_data.then(function (result) {
            setMyDataPoints(getDataPoints(result));
            setGraphTitle(processString(dataType));
        });

    }, []);

    const options = {
        animationEnabled: true,
        width: 450,
        theme: "light2",
        title: {
            text: graphTitle
        },
        data: [{
            type: "doughnut",
            showInLegend: true,
            indexLabel: "{name}: {y}",
            yValueFormatString: "#,###'%'",
            dataPoints: myDataPoints
        }],
    }

    return (			
    <CanvasJSChart options = {options} style={{width: "25%"}}
        //onRef={ref => this.chart = ref}
    />)

}

export default CircularGraph;