import React, { useState } from 'react';
import '../../App.css';
import Button from '../Button';
import'./Login.css';

const user = ["", ""];

function handleLogin(user){
  user[0] = document.getElementsByName("email").defaultValue;
  user[1] = document.getElementsByName("password").defaultValue;
  alert(user);
}


function Login () {
  const [username, setUsername] = useState(null);
const [password, setPassword] = useState(null)

const handleSubmit= (e) => {
  e.preventDefault();
  user[0] =username;
  user[1] =password;
  alert(user);
  
}

    return (
    <>
    <div className='login-container'>
    <form onSubmit={handleSubmit}>
            <input
              className='footer-input'
              // defaultValue={"20170418"}
              name='email'
              type='email'
              placeholder='Your Email'
              onChange={(e) => setUsername(e.target.value)} 
            />
            <br />
            <input
              className='footer-input'
              // defaultValue={"tajnasifra"}
              name='password'
              type='password'
              placeholder='Your Password'
              onChange={(e) => setPassword(e.target.value)} 
            />
             <Button path='/login' buttonStyle='btn--primary' type="submit" onClick={(e)=> handleSubmit(e)}>Login</Button> 
          </form>
    </div>
    </>
    )
}

export {user};

export default Login;
