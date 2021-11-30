import React, {Component} from "react"
// Enables JSX syntax
import ReactDOM from "react-dom"
// Allows interactions with DOM
import Chart from "./App.js"

class App extends Component {
    render()
    {
        return (<div><h2>Chart Time!</h2>
        <Chart/></div>);
    }
}

// Usage
ReactDOM.render(
	<App />,
    document.getElementById("root")
);