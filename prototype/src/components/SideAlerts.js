import React from "react"
import Card from "react-bootstrap/Card";

const SideAlerts = () => {
    return (
        <Card style={{ width: '100%', height: '100%' }} border="primary">
            <Card.Body>
                <Card.Title>Alerts!</Card.Title>
                <Card.Subtitle className="mb-2 text-muted">Suspendisse sollicitudin tristique nunc eget consequat. Aenean imperdiet sagittis sem vel interdum.</Card.Subtitle>
                <Card.Text>Interdum et malesuada fames ac ante ipsum primis in faucibus. Phasellus molestie euismod iaculis. Fusce finibus metus ut commodo mattis. Sed in congue nulla. Nullam vitae nunc vitae lorem fermentum mollis. Integer faucibus feugiat imperdiet. Morbi tincidunt gravida pretium. Duis massa dui, mollis ut faucibus vel, fringilla id turpis. Vestibulum mollis metus vel velit pulvinar, ut mollis libero sagittis. Cras consectetur egestas dignissim. Ut non mollis eros, vitae vestibulum lacus. Pellentesque fringilla arcu vel ipsum semper consectetur. Cras non nulla metus. Sed sed lectus id elit blandit mollis vel et massa. </Card.Text>
            </Card.Body>
        </Card>
    )
}

export default SideAlerts;