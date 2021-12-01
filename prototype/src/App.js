import logo from './logo.svg';
import './App.css';
import React, {Component} from 'react';
import { Routes, Route, Link } from "react-router-dom";



import CanvasJSReact from './canvasjs.react';
import MyNavbar from './components/MyNavbar';
import LocationInfo from "./components/LocationInfo"
//var CanvasJSReact = require('./canvasjs.react');
var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;





const App = () => {	
  
    const options = {
      title: {
        text: "Basic Column Chart in React"
      },
      data: [{				
                type: "column",
                dataPoints: [
                    { label: "Apple",  y: 10  },
                    { label: "Orange", y: 15  },
                    { label: "Banana", y: 25  },
                    { label: "Mango",  y: 30  },
                    { label: "Grape",  y: 28  }
                ]
       }]
   }
    
   return (

      
      <div>
        <MyNavbar/>
        <Routes>
          {//<Route path="/" ><Redirect to="/dashboard" /></Route>
          }
          <Route path="/dashboard">
            {
              //TODO acess ":location" to display info
            }
            <Route path=":location" element={<LocationInfo />}></Route>
          </Route>
        </Routes>
        
        <CanvasJSChart options = {options}
            /* onRef = {ref => this.chart = ref} */
        />
      </div>
    );
  
}



export default App;
