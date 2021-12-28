import React from "react";
import Accordion from 'react-bootstrap/Accordion';
import LocationInfo from "./LocationInfo";

const MyAccordion = () => {

    let arr = [1,2,3]
    let imgs = [
        "https://www.tecnologiahorticola.com/wp-content/uploads/2019/03/New_Plantation_Croatia-2.jpg",
        "https://projarinternational.com/wp-content/uploads/2020/06/vivero_arandano-scaled.jpg",
        "https://s3.envato.com/files/e641f05c-c3ce-4f19-8eb6-12bdb53ea528/inline_image_preview.jpg"
    ]

    return(

        <div>
            <div style={{margin: '3%' }}>
                <LocationInfo />
            </div>
            <div style={{ width: '50%', margin: 'auto', marginTop: '1%' }}>
                <Accordion defaultActiveKey="0" flush>
                    
                    {
                        arr.map(
                        (elem) => { return (

                            <Accordion.Item eventKey={(elem - 1).toString()}>
                                
                                <Accordion.Header>{"Location " + elem}</Accordion.Header>

                                <Accordion.Body>
                                <div style={{ position: 'relative' }}>
                                    <img src={imgs[elem-1]} style={{ width: '100%', height: '400px', objectFit: 'cover', opacity: '0.3' }}/>
                                    <div id={"plantation_temperature" + elem} style={{ position: 'absolute', top: '5%', left: '16px', fontSize: '18px', width: '50%' }}>[plantation_temperature]</div>
                                    <div id={"net_harvest" + elem} style={{ position: 'absolute', top: '11%', left: '16px', fontSize: '18px', width: '50%' }}>[net_harvest]</div>
                                    <div id={"soil_ph" + elem} style={{ position: 'absolute', top: '17%', left: '16px', fontSize: '18px', width: '50%' }}>[soil_ph]</div>
                                    <div id={"soil_water_tension" + elem} style={{ position: 'absolute', top: '23%', left: '16px', fontSize: '18px', width: '50%' }}>[soil_water_tension]</div>
                                    <div id={"unit_loss" + elem} style={{ position: 'absolute', top: '29%', left: '16px', fontSize: '18px', width: '50%' }}>[unit_loss]</div>
                                    <div id={"storage_temperature" + elem} style={{ position: 'absolute', top: '35%', left: '16px', fontSize: '18px', width: '50%' }}>[storage_temperature]</div>
                                    <div id={"storage_humidity" + elem} style={{ position: 'absolute', top: '41%', left: '16px', fontSize: '18px', width: '50%' }}>[storage_humidity]</div>
                                </div>
                                </Accordion.Body>

                            </Accordion.Item>
                        
                        ) }
                        )
                    }

                </Accordion>
            </div>
        </div>

    )

}

export default MyAccordion;