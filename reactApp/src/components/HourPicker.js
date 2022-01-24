import { TimePicker } from 'antd';
import moment from 'moment';
import 'antd/dist/antd.css';

function onChange(time, timeString) {
  console.log(time, timeString);
}

const HourPicker = () => {
    return(
        <TimePicker onChange={onChange} defaultOpenValue={moment('00:00:00', 'HH:mm:ss')} />
    )
}

export default HourPicker;