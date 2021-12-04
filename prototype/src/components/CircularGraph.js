import React from "react";
import CanvasJSReact from '../canvasjs.react';

var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;
var dataPoints =[];

const CircularGraph = () =>{
    const options = {
        animationEnabled: true,
        title: {
            text: "Unit Loss"
        },
        data: [{
            type: "doughnut",
            showInLegend: true,
            indexLabel: "{name}: {y}",
            yValueFormatString: "#,###'%'",
            dataPoints: [
                { name: "Lost", y: 14 },
                { name: "Sold", y: 86 }
            ]
        }],
    }

    return (			
    <CanvasJSChart options = {options} style={{width: "25%"}}
        //onRef={ref => this.chart = ref}
    />)

}

export default CircularGraph;