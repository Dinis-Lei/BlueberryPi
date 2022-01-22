import React, { useEffect, useState } from "react";
import CanvasJSReact from '../canvasjs.react';
import { fetchData, processString } from "../App";
import { useParams } from "react-router-dom"

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

    if (JSONData.length < 10) {
        graphData = JSONData;
    }
    else {
        let firstElem = 0; 
        let lastElem = 10;
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

// const getTimes = (JSONData) => {
//     let ret = [];
//     let graphData;

//     if (JSONData.length < 20) {
//         graphData = JSONData;
//     }
//     else {
//         let firstElem = JSONData.length - 10; 
//         let lastElem = JSONData.length;
//         graphData = JSONData.slice(firstElem, lastElem); // não inclui lastElem
//     }

//     for (const dataPoint of graphData) {
//         let timestamp = dataPoint["timestamp"];
//         let date = new Date(timestamp * 1000);
//         let date_str = date.getDate()+
//             "/"+(date.getMonth()+1)+
//             "/"+date.getFullYear()+
//             " "+date.getHours()+
//             ":"+date.getMinutes()+
//             ":"+date.getSeconds();
//         ret.push(date_str);
//     }

//     return ret;
// }

const Graph = props =>{

    const [myDataPoints, setMyDataPoints] = useState([]);
    const [graphTitle, setGraphTitle] = useState("[no title]");
    const { location } = useParams()
    const [flg, setFlg] = useState(true);
    // const [times, setTimes] = useState([]);

    useEffect(() => {
        //while(true){
           setInterval(
            () => {
                console.log(flg)
                setFlg(!flg)
            }, 60000) 
        //} 
        
    })

    useEffect(() => {
        console.log("AAA")
        let dataType = props.dataType;
        let url = location + "/" + dataType;
        
        let plantation_temperature_data = fetchData(url); // data is a promise object
        plantation_temperature_data.then(function (result) {
            console.log(`${dataType}`)
            console.log(myDataPoints)
            setMyDataPoints(getDataPoints(result));
            console.log(`${dataType}`)
            console.log(myDataPoints)
            setGraphTitle(processString(dataType));
            // setTimes(getTimes(result));
        });

    }, [flg]);

    const options = {
        animationEnabled: true,
        width: 450,
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
            //prefix: "W",
            interval: 2
        },
        data: [{
            type: "spline",
            indexLabel: "{x}: {y}",
            // toolTipContent: "Week {x}: {y}%",
            dataPoints: myDataPoints
        }]
    }

    return (			
    <CanvasJSChart options = {options} style={{width: "25%"}}
        //onRef={ref => this.chart = ref}
    />)

}

export default Graph;