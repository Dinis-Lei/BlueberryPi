import React from "react";
import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav'
import NavDropdown from 'react-bootstrap/NavDropdown'

const MyNavbar = () => {

    let logged = true
    let name = "Bob"

    let arr = [1,2,3]

    return(
        <Navbar style={{backgroundColor: "#4f86f7"}} variant="dark"  expand="lg" className="d-flex">
            <Navbar.Brand href="/dashboard" className="px-3">BlueberryPi</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav className="me-auto d-flex flex-grow-1">
                {!logged && <Nav.Link href="#login">Login</Nav.Link>}
                <NavDropdown title="Locations" id="basic-nav-dropdown" className="flex-grow-1">
                  {
                    arr.map(
                      (elem) => { return ( <NavDropdown.Item href={"/dashboard/location"+elem}>Location {elem}</NavDropdown.Item>) }
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