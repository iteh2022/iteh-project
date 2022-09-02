import React from 'react';
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
    return (
    <>
    <div className='login-container'>
        <form >
            <input
              className='footer-input'
              defaultValue={"20170418"}
              name='email'
              type='email'
              placeholder='Your Email'
            />
            <br />
            <input
              className='footer-input'
              defaultValue={"tajnasifra"}
              name='password'
              type='password'
              placeholder='Your Password'
            />
             <Button path='/login' buttonStyle='btn--primary'>Login</Button> 
          </form>
    </div>
    </>
    )
}

export {user};

export default Login;
