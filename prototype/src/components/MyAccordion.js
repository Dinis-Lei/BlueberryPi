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
            <div style={{margin: '5px'}}>
                <LocationInfo />
            </div>
            <div style={{ width: '60%', margin: 'auto' }}>
                <Accordion defaultActiveKey="0" flush>
                    
                    {
                        arr.map(
                        (elem) => { return (

                            <Accordion.Item eventKey={elem - 1}>
                                
                                <Accordion.Header>{"Location " + elem}</Accordion.Header>

                                <Accordion.Body>
                                <div style={{ position: 'relative' }}>
                                    <img src={imgs[elem-1]} style={{ width: '1050px', height: '400px', objectFit: 'cover', opacity: '0.3' }}/> 
                                    <div style={{ position: 'absolute', top: '8px', left: '16px', fontSize: '18px', width: '50%' }}>Vestibulum accumsan, felis id vehicula egestas, sem elit gravida tortor, a vulputate nunc risus sed elit. Maecenas accumsan ultrices magna sit amet convallis. Pellentesque eget dui vestibulum, vestibulum nulla nec, sodales sem. Praesent suscipit orci eu erat ornare sodales in tincidunt risus. Donec et sapien a elit consectetur bibendum. Quisque sodales efficitur tellus, et vulputate lectus. Quisque mollis diam in placerat faucibus. Proin sed massa diam. Mauris vestibulum tellus et risus dignissim posuere.</div>
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