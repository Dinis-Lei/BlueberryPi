import React, { useEffect, useState } from "react";
import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav'
import NavDropdown from 'react-bootstrap/NavDropdown'
import { fetchData } from "../App";
import { getLocations } from "./MyAccordion";

const MyNavbar = () => {

    let logged = true
    let name = "Bob"

    const [locations_lst, setLocationsLst] = useState([]);

    useEffect(() => {
      let location_data = fetchData("locations");
      location_data.then(function (result) {
          setLocationsLst(getLocations(result));
      });
    }, []);

    return(
        <Navbar style={{backgroundColor: "#04235a"}} variant="dark"  expand="lg" className="d-flex">
            <Navbar.Brand href="/dashboard" className="px-3">BlueberryPi</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Nav className="me-auto">
              <Nav.Link href="/dashboard/home">Dashboard</Nav.Link>
            </Nav>
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav className="me-auto d-flex flex-grow-1">
                {!logged && <Nav.Link href="#login">Login</Nav.Link>}
                <NavDropdown title="History" id="basic-nav-dropdown" className="flex-grow-1">
                  {
                    locations_lst.map(
                      (elem) => { return ( <NavDropdown.Item href={"/dashboard/"+elem}>{elem}</NavDropdown.Item>) }
                    )
                  }
                </NavDropdown>
                {logged && <Navbar.Text className="ml-auto px-3" >Hello {name}!</Navbar.Text>}
              </Nav>
            </Navbar.Collapse>
        </Navbar>
    )

}

export default MyNavbar;