import React, { useEffect, useState } from "react";
import Form from 'react-bootstrap/Form'

const processDate = (date) => {

    var day = date.getDate().toString().length==1 ? ("0" + date.getDate()) : date.getDate();
    var month = (date.getMonth()+1).toString().length==1 ? ("0" + (date.getMonth()+1)) : (date.getMonth()+1);
    var date_str = date.getFullYear() + '-' + month + '-' + day;

    return date_str;

}

class DatePicker extends React.Component {

    constructor(props) {
        super(props);
        this.state = {value: processDate(new Date())};
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) { 
        console.log(event.target.value)
        this.props.onChange(event.target.value); 
        this.state.value = event.target.value
    }

    render() {
        return(
            <Form>
                <Form.Group>
                    <Form.Control type="date" value={this.state.value} onChange={this.handleChange} />
                </Form.Group>
            </Form>
        )
    }

}

export default DatePicker;