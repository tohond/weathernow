import React, {useState,useEffect,useRef} from 'react';
import axios from 'axios';

const Register = (props) => {
    const userRef = useRef();
    const errRef = useRef();

    const [correo,setCorreo] = useState('');
    const [contrasenia,setContrasenia] = useState('');
    const [ciudad,setCiudad] = useState('');
    const [errMsg,setErrMsg] = useState('');
    const [success,setSuccess] = useState(false);

    useEffect(()=>{
        userRef.current.focus();
    },[]) 

    useEffect(()=>{
        setErrMsg('');
    },[correo,contrasenia]) 

    const handleSubmit = async (e) => {
        e.preventDefault();

        try{
             const data = await axios.get(
                `https://api.weatherapi.com/v1/forecast.json?key=${process.env.REACT_APP_KEY}&q=${ciudad}`
              );
              
                console.log(data );
                props.onFormChange("weather");

        }
        catch(err){
                console.log(err)
        }



    e.preventDefault();
    console.log(JSON.stringify({correo,contrasenia,ciudad}));
    }

    return (
        <section>
            <p ref = {errRef} className = 
            {errMsg ? 'errMsg' : 'offscreen'} aria-live="asertive">{errMsg}
            </p>

        
            <h1>
                Registro
            </h1>

            <form onSubmit = {handleSubmit}>
              <label htmlFor="correo">Correo</label>

              <input type="text" 
              id="correo" 
              autoComplete = "off"
              ref={userRef} 

               onChange={(e)=>setCorreo(e.target.value)}
               value={correo}
               required

               
               />  
              <label htmlFor="contrasenia">Contrasenia</label>

              <input type="contrasenia" 
              id="contrasenia" 
             
              

               onChange={(e)=>setContrasenia(e.target.value)}
               value={contrasenia}
               required

               
               /> 

               

               <label htmlFor="ciudad">Ciudad</label>

              <input type="text" 
              id="ciudad" 
             
              

               onChange={(e)=>setCiudad(e.target.value)}
               value={ciudad}
               required

               
               /> 

               <button onSubmit={(e)=>handleSubmit(e)} type="submit">Registrarse</button> 
             </form>
             <button onClick={()=> props.onFormChange("login")} type="button">Iniciar sesion</button> 
            
        </section>
    )
};


export default Register;