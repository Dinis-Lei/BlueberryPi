import logo from './logo.svg';
import './App.css';
import React, { Component, useState } from 'react';
import { Routes, Route, Link } from "react-router-dom";
import { ToggleButton } from 'react-bootstrap';
import { ButtonGroup } from 'react-bootstrap';
import { useEffect } from "react";
import { Navigate } from 'react-router-dom';

import CanvasJSReact from './canvasjs.react';
import MyNavbar from './components/MyNavbar';
import LocationInfo from "./components/LocationInfo"
import Graph from './components/Graph';
import CircularGraph from './components/CircularGraph';
import SideAlerts from './components/SideAlerts';
import MyAccordion from './components/MyAccordion';

import { getLocations } from './components/MyAccordion';

let CanvasJS = CanvasJSReact.CanvasJS;
let CanvasJSChart = CanvasJSReact.CanvasJSChart;

export const fetchData = (str) => {
  return fetch("http://localhost:8080/api/" + str)
    .then((response) => response.json());
}

// "example_string" -> "Example string"
export const processString = (str) => {
  let splits = str.split("_");
  let ret = splits[0].charAt(0).toUpperCase() + splits[0].slice(1);
  for (const s of splits.slice(1)) {
    ret += " " + s;
  }
  return ret;
}

const App = () => {

  const [checked, setChecked] = useState(false);
  const [radioValue, setRadioValue] = useState('1');

  const radios = [
    { name: 'Plantation', value: '1' },
    { name: 'Storage', value: '2' },
  ];

  return (

    <div>
      <MyNavbar />
      <Routes>
        <Route path="/" element={<Navigate to="/dashboard" />} />
        <Route path="/dashboard">
          <Route path="" element={
            <div>
              <h1 style={{
                color: '#111',
                fontFamily: 'Helvetica Neue',
                fontSize: '225px',
                fontWeight: 'bold',
                letterSpacing: '-1px',
                lineHeight: '1',
                textAlign: 'center',
                marginTop: '30px'
              }}>BlueberryPi</h1>
              <h2 style={{
                color: '#111',
                fontFamily: 'Opens Sans',
                fontSize: '30px',
                fontWeight: '300',
                lineHeight: '32px',
                margin: '0 0 72px',
                textAlign: 'center',
                marginTop: '30px'
              }}>The one-shop stop for all your precision agriculture needs.</h2>

              <div>

                <div style={{ float: 'left', width: '50%', overflow: 'hidden', paddingLeft: '25%', paddingRight: '2%' }}>
                  <p style={{
                    color: '#111',
                    fontFamily: 'Helvetica Neue',
                    fontSize: '14px',
                    lineHeight: '24px',
                    margin: '0 0 24px',
                    textAlign: 'justify',
                    textJustify: 'inter-word',
                  }}>Nulla ut diam eleifend massa iaculis vehicula et ut nisl. Morbi quis odio cursus, gravida ipsum vel, vulputate erat. Pellentesque eu rhoncus ipsum, non eleifend ipsum. Phasellus pharetra magna vel gravida commodo. Aenean hendrerit neque non velit pretium, et aliquam justo facilisis. Aliquam ut risus mollis, imperdiet ex aliquet, euismod ligula. Aliquam erat volutpat. Nullam commodo tincidunt odio. Etiam ut metus id eros aliquam euismod. Fusce lacinia faucibus elit in egestas. Quisque ipsum eros, mattis consequat est venenatis, dictum luctus ipsum. Proin ultrices non metus sed sollicitudin. Nullam euismod molestie enim ac tristique. Donec sed tellus non ipsum auctor faucibus.
                  </p>
                </div>

                <div style={{ overflow: 'hidden', paddingRight: '25%', paddingLeft: '2%' }}>
                  <p style={{
                    color: '#111',
                    fontFamily: 'Helvetica Neue',
                    fontSize: '14px',
                    lineHeight: '24px',
                    margin: '0 0 24px',
                    textAlign: 'justify',
                    textJustify: 'inter-word',
                  }}>Suspendisse faucibus rhoncus nisi. Aliquam sed lobortis libero. Sed felis diam, placerat a scelerisque sed, efficitur sed lacus. Mauris et odio non ante ullamcorper hendrerit id id velit. Integer erat elit, iaculis et iaculis sit amet, semper id lorem. Maecenas at rutrum neque. Integer id tortor sagittis, iaculis lacus sed, dapibus velit. Mauris luctus, turpis elementum finibus gravida, justo est dapibus mi, at scelerisque lorem erat non neque. Aliquam mollis id leo vitae lobortis. Vestibulum finibus porttitor aliquet. Quisque sagittis sodales justo, nec auctor elit fermentum eget. Duis ut efficitur metus. Cras id nulla efficitur, pellentesque magna et, tempus libero.
                  </p>
                </div>

              </div>

            </div>
          } />

          <Route path="home" element={

            <div>
              <MyAccordion />
            </div>

          }></Route>

          <Route path=":location" element={
            <div>
              <div className="d-flex">
                <div style={{ width: '50%', marginLeft: '2%', marginTop: '2%' }}>
                  <LocationInfo />
                </div>
                <div style={{ width: '50%' }}>
                  <ButtonGroup style={{ position: "absolute", right: '5%', top: '10%' }}>
                    {radios.map((radio, idx) => (
                      <ToggleButton
                        key={idx}
                        id={`radio-${idx}`}
                        type="radio"
                        letiant='outline-dark'
                        name="radio"
                        value={radio.value}
                        checked={radioValue === radio.value}
                        onChange={(e) => {
                          setRadioValue(e.currentTarget.value);
                          if (e.currentTarget.value == 1) {
                            document.getElementById('plantation').style.display = 'block';
                            document.getElementById('storage').style.display = 'none';
                          }
                          else {
                            document.getElementById('plantation').style.display = 'none';
                            document.getElementById('storage').style.display = 'block';
                          }
                        }}
                      >
                        {radio.name}
                      </ToggleButton>
                    ))}
                  </ButtonGroup>
                </div>
              </div>

              {/* PLANTATION DATA */}
              <div id='plantation'>
                <div style={{ width: '40%', float: 'right' }}>
                  <SideAlerts/>
                </div>
                <div className="d-flex" style={{ marginTop: '50px', paddingLeft: '5%', width: '60%' }}>
                  <Graph dataType="soil_water_tension" />
                  <Graph dataType="plantation_temperature" />
                </div>
                <div className="d-flex" style={{ paddingLeft: '5%', width: '60%' }}>
                  <Graph dataType="soil_ph" />
                  <Graph dataType="net_harvest" />
                </div>
              </div>

              {/* STORAGE DATA */}
              <div id='storage' style={{ display: 'none' }}>
                <div style={{ width: '40%', float: 'right' }}>
                  <SideAlerts/>
                </div>
                <div className="d-flex" style={{ marginTop: '50px', paddingLeft: '5%', width: '60%' }}>
                  <Graph dataType="storage_humidity" />
                  <CircularGraph dataType="unit_loss" />
                </div>
                <div className="d-flex" style={{ paddingLeft: '5%', width: '60%' }}>
                  <Graph dataType="storage_temperature" />
                  <CircularGraph dataType="storage_temperature" />
                </div>
              </div>

            </div>
          }></Route>

        </Route>
      </Routes>
    </div>
  );

}

export default App;
