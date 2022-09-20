import React, { useContext, useState } from 'react'
import '../../App.css';
import Button from '../Button';
import'./Login.css';
import axios from "axios"
import { ApiContext, CurrentUserContext } from '../../App'
import { login } from '../../Helpers';
import { useNavigate } from 'react-router-dom'


const Login = () => {
    let navigate = useNavigate();

    const [userData, setUserData] = useState({username: "", password: "",});

    const { setCurrentUser } = useContext(CurrentUserContext);
    const { api } = useContext(ApiContext)

    const qs = require('qs');

    function handleInput(e){
        let newUserData = userData;
        newUserData[e.target.name] = e.target.value; 
        console.log(newUserData);
        setUserData(newUserData);
    }

    function handleSubmit(event) {
      event.preventDefault();

      axios.post("http://localhost:8080/login", qs.stringify(userData)).then(response => {
          login(response.data.access_token);
          setCurrentUser(response.data.access_token);
          navigate("/");
      }).catch((error) => {
          console.log(error);
          alert("Korisnicko ime ili lozinka nisu validni!")
      });
  }

    return (
    <>
    <div className='login-container'>
    <form onSubmit={handleSubmit}>
            <input
              className='footer-input'
              name='username'
              type='username'
              placeholder='Your Email'
              onInput={handleInput}
            />
            <br />
            <input
              className='footer-input'
              name='password'
              type='password'
              placeholder='Your Password'
              onInput={handleInput}
            />
             <button className="btn btn-primary btn-lg" type="submit">Login</button> 
          </form>
    </div>
    </>
    )
}

export default Login;
