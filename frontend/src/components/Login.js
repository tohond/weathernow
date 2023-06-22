import {useRef,useState,useEffect} from 'react';
import axios from 'axios';

const Login = (props) => {
    const userRef = useRef();
    const errRef = useRef();

    const [correo,setCorreo] = useState('');
    const [contrasenia,setContrasenia] = useState('');
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
    console.log(JSON.stringify({correo,contrasenia}));
    try {
        const response = await axios.post("http://localhost:8080/api/usuario/login",
        JSON.stringify({correo,contrasenia}),
        {headers: {'Content-Type':'application/json'}
    } ) 
        

            console.log(response.data);
            props.onFormChange("weather");
    } catch (error) {
       
        console.log(error);

    }
    
    }

    return (
        <section>
            <p ref = {errRef} className = 
            {errMsg ? 'errMsg' : 'offscreen'} aria-live="asertive">{errMsg}
            </p>

        
            <h1>
                Log in
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

               <button type="submit">Iniciar sesion</button> 
             </form>
             <button onClick={()=> props.onFormChange("register")} type="button">Registrarse</button> 
            
        </section>
    );
};

export default Login;