import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import { BrowserRouter, BrowserRouter as Router } from 'react-router-dom'; // Import Router
import 'mdb-react-ui-kit/dist/css/mdb.min.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Login from './Component/User/Login';
import Home from './Component/Home/Home';
import { configstore } from './UserStore';
import { Provider } from 'react-redux';



const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  <Provider store={configstore}>
  <Router>  {/* Wrap the App component with Router */}
    <App/>
  </Router>
  </Provider>
);
