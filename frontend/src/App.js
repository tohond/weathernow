import React, {useState,useEffect,useRef} from 'react';
import "./App.css";

import WeatherApp from "./components/WeatherApp";
import Login from "./components/Login.js";
import Register from "./components/Register.js";
function App() {
  const [currentForm,setCurrentForm]=useState('');

  useEffect(()=>{
     setCurrentForm('login');
  },[])

  
  

  const handleFormChange = (formName) => {
    setCurrentForm(formName);
  }
  return (
    
  
  <div className="App">
  {
    /*( currentForm === "login" ? <Login onFormChange={handleFormChange} /> : 
    ( currentForm === "register" ? <Register onFormChange={handleFormChange}/> : currentForm === "register"? <WeatherApp/>:) )*/
    //necesito un if else para que se muestre el login y el register, pasar el handleFormChange a los componentes y que se muestre el weatherApp en caso de que se loguee
    (currentForm === "login" ? <Login onFormChange={handleFormChange} /> : 
    ( currentForm === "register" ? <Register onFormChange={handleFormChange}/> : <WeatherApp/>)
    )
    
  }
    
</div>


)
  
  
}

export default App;
