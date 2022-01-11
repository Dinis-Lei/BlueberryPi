import React from "react"
import Toast from 'react-bootstrap/Toast'
import ToastContainer from 'react-bootstrap/ToastContainer'

const SideAlerts = () => {

    const [show, setShow] = React.useState(true);
    const toggleShow = () => setShow(!show);

    return (
        <div
            aria-live="polite"
            aria-atomic="true"
            className="position-relative"
            style={{ minHeight: '240px' }}
            >

            {/* position="top-end" className="p-3" */}
            <ToastContainer style={{ paddingTop: '10%', paddingLeft: '10%' }}>
                <Toast show={show} onClose={toggleShow} style={{ width: '75%' }}>
                    <Toast.Header>
                        <img src="holder.js/20x20?text=%20" className="rounded mr-2" alt="" />
                        <strong className="me-auto">ALERT</strong>
                        <small className="text-muted">just now</small>
                    </Toast.Header>
                    <Toast.Body>Interdum et malesuada fames ac ante ipsum primis in faucibus. Phasellus molestie euismod iaculis. Fusce finibus metus ut commodo mattis. Sed in congue nulla. Nullam vitae nunc vitae lorem fermentum mollis. Integer faucibus feugiat imperdiet. Morbi tincidunt gravida pretium. Duis massa dui, mollis ut faucibus vel, fringilla id turpis. Vestibulum mollis metus vel velit pulvinar, ut mollis libero sagittis. Cras consectetur egestas dignissim. Ut non mollis eros, vitae vestibulum lacus. Pellentesque fringilla arcu vel ipsum semper consectetur. Cras non nulla metus. Sed sed lectus id elit blandit mollis vel et massa.</Toast.Body>
                </Toast>
            </ToastContainer>
        </div>
    );

}

export default SideAlerts;