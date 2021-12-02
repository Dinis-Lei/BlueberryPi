import logo from './logo.svg';
import './App.css';
import React, {Component, useState} from 'react';
import { Routes, Route, Link } from "react-router-dom";
import { ToggleButton } from 'react-bootstrap';
import { ButtonGroup } from 'react-bootstrap';




import CanvasJSReact from './canvasjs.react';
import MyNavbar from './components/MyNavbar';
import LocationInfo from "./components/LocationInfo"
import Graph from './components/Graph';

//var CanvasJSReact = require('./canvasjs.react');
var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;





const App = () => {	

  const [checked, setChecked] = useState(false);
  const [radioValue, setRadioValue] = useState('1');

  const radios = [
    { name: 'Plantation', value: '1' },
    { name: 'Storage', value: '2' },
  ];
    
   return (

      
      <div>
        <MyNavbar/>
        <Routes>
          <Route path="/dashboard">
            <Route path=":location" element={
              <div>
                <div className="d-flex">
                  <LocationInfo/>
                  <ButtonGroup>
                    {radios.map((radio, idx) => (
                      <ToggleButton
                        key={idx}
                        id={`radio-${idx}`}
                        type="radio"
                        variant='outline-success'
                        name="radio"
                        value={radio.value}
                        checked={radioValue === radio.value}
                        onChange={(e) => setRadioValue(e.currentTarget.value)}
                      >
                        {radio.name}
                      </ToggleButton>
                    ))}
                  </ButtonGroup>
                </div>
                <div className="d-flex">
                  <Graph/>
                  <Graph/>
                </div>
                <div className="d-flex">
                  <Graph/>
                  <Graph/>
                </div>
              </div>
          }></Route>
          </Route>
        </Routes>
      </div>
    );
  
}



export default App;
