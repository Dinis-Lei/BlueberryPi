import React, { useEffect, useState } from "react";
import CanvasJSReact from '../canvasjs.react';
import { fetchData, processString } from "../App";
import { useParams } from "react-router-dom"

var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

export const getDataPoints = (JSONData) => {
    let ret = [];
    let counter = 1;
    let graphData;

    if (JSONData.length < 20) {
        graphData = JSONData;
    }
    else {
        let firstElem = JSONData.length - 10; 
        let lastElem = JSONData.length;
        graphData = JSONData.slice(firstElem, lastElem); // não inclui lastElem
    }

    for (const dataPoint of graphData) {
        let newElem = {x: counter, y: dataPoint["data"]};
        ret.push(newElem);
        counter += 1;
    }

    return ret;
}

const Graph = props =>{

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
        title:{
            text: graphTitle
        },
        axisY: {
            title: "medida idk o que raio a tensão leva",
            suffix: "%"
        },
        axisX: {
            title: "Week of Year",
            prefix: "W",
            interval: 2
        },
        data: [{
            type: "line",
            toolTipContent: "Week {x}: {y}%",
            dataPoints: myDataPoints
        }]
    }

    return (			
    <CanvasJSChart options = {options} style={{width: "25%"}}
        //onRef={ref => this.chart = ref}
    />)

}

export default Graph;