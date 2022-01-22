import React, { useEffect, useState } from "react";
import Accordion from 'react-bootstrap/Accordion';
import LocationInfo from "./LocationInfo";
import DailyInfo from "./DailyInfo";
import { fetchData } from "../App";
import { Container, Table } from "react-bootstrap";

export const getLocations = (JSONData) => {
    let ret = [];
    for (let loc of JSONData) {
        ret.push(loc["name"]);
    }
    console.log(JSONData)
    console.log(ret)
    return ret;
}

const MyAccordion = () => {

    const [locations_lst, setLocationsLst] = useState([]);

    useEffect(() => {
        let location_data = fetchData("locations");
        location_data.then(function (result) {
            setLocationsLst(getLocations(result));
        });
    }, []);

    let imgs = [
        "https://www.tecnologiahorticola.com/wp-content/uploads/2019/03/New_Plantation_Croatia-2.jpg",
        "https://projarinternational.com/wp-content/uploads/2020/06/vivero_arandano-scaled.jpg",
        "https://s3.envato.com/files/e641f05c-c3ce-4f19-8eb6-12bdb53ea528/inline_image_preview.jpg"
    ]

    let pt = "plantation_temperature";
    let pt_u = "ºC";
    let nh = "net_harvest";
    let nh_u = "kg";
    let sp = "soil_ph";
    let sp_u = "pH";
    let swt = "soil_water_tension";
    let swt_u = "cb";
    let ul = "unit_loss";
    let ul_u = "%";
    let st = "storage_temperature";
    let st_u = "ºC";
    let sh = "storage_humidity";
    let sh_u = "%";

    return(

        <div>
            <div style={{margin: '3%' }}>
                <LocationInfo />
            </div>
            <Container style={{ width: '50%', margin: 'auto', marginTop: '1%' }}>
                <Accordion defaultActiveKey="0" flush>
                    
                    {
                        locations_lst.map(
                        (elem) => { return (

                            <Accordion.Item eventKey={(locations_lst.indexOf(elem)).toString()}>
                                
                                <Accordion.Header>{elem}</Accordion.Header>

                                <Accordion.Body style={{ backgroundImage: 'url(' + imgs[locations_lst.indexOf(elem)] + ')' }}>
                                    <div style={{ position: 'relative' }}>
                                        <Table style={{ margin: '0 auto' }} responsive>
                                            <tbody>
                                                <tr>
                                                    <td> <DailyInfo dataType={pt} units={pt_u} location={elem} /> </td>
                                                    <td> <DailyInfo dataType={nh} units={nh_u} location={elem} /> </td>
                                                    <td> <DailyInfo dataType={sp} units={sp_u} location={elem} /> </td>
                                                </tr>
                                                <tr>
                                                    <td> <DailyInfo dataType={swt} units={swt_u} location={elem} /> </td>
                                                    <td> <DailyInfo dataType={ul} units={ul_u} location={elem} /> </td>
                                                    <td> <DailyInfo dataType={st} units={st_u} location={elem} /> </td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td> <DailyInfo dataType={sh} units={sh_u} location={elem} /> </td>
                                                    <td></td>
                                                </tr>
                                            </tbody>
                                        </Table>
                                    </div>
                                </Accordion.Body>

                            </Accordion.Item>
                        
                        ) }
                        )
                    }

                </Accordion>
            </Container>
        </div>

    )

}

export default MyAccordion;