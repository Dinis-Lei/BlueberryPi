import { TimePicker } from 'antd';
import moment from 'moment';
import 'antd/dist/antd.css';

//function onChange(time, timeString) {
//  console.log(time, timeString);
//  this.props.onChange(time)
//}

const HourPicker = (props) => {

  const onChange = (time, timeString) => {
    console.log(time, timeString);
    props.onChange(timeString)
  }

    return(
        <TimePicker onChange={onChange} defaultOpenValue={moment('00:00:00', 'HH:mm:ss')} />
    )
}

export default HourPicker;